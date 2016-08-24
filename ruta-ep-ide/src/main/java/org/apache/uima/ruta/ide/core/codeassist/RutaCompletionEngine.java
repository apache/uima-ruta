/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.core.codeassist;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.ide.RutaIdeCorePlugin;
import org.apache.uima.ruta.ide.core.IRutaKeywords;
import org.apache.uima.ruta.ide.core.RutaExtensionManager;
import org.apache.uima.ruta.ide.core.RutaKeywordsManager;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.core.extensions.ICompletionExtension;
import org.apache.uima.ruta.ide.core.parser.RutaParseUtils;
import org.apache.uima.ruta.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.ruta.ide.parser.ast.ComponentReference;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaModuleDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.codeassist.ScriptCompletionEngine;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.SourceField;
import org.eclipse.dltk.internal.core.SourceMethod;
import org.eclipse.dltk.internal.core.SourceModule;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class RutaCompletionEngine extends ScriptCompletionEngine {

  protected RutaCompletionParser parser;

  protected IModuleSource sourceModule;

  protected final static boolean TRACE_COMPLETION_TIME = false;

  private ICompletionExtension[] extensions;

  private URLClassLoader classloader = null;

  public RutaCompletionEngine() {
    extensions = RutaExtensionManager.getDefault().getCompletionExtensions();
    this.parser = new RutaCompletionParser(extensions);
  }

  @Override
  protected int getEndOfEmptyToken() {
    return actualCompletionPosition;
  }

  @Override
  protected String processMethodName(IMethod method, String token) {
    return RutaParseUtils.processMethodName(method, token);
  }

  @Override
  protected String processTypeName(IType type, String token) {
    return RutaParseUtils.processTypeName(type, token);
  }

  public void complete(IModuleSource module, int position, int i) {
    this.sourceModule = module;
    this.actualCompletionPosition = position;
    this.offset = i;
    this.requestor.beginReporting();
    String content = module.getSourceContents();
    if (position < 0 || position > content.length()) {
      return;
    }

    TMAutoCompletionToolkit tk = new TMAutoCompletionToolkit(content, position);

    String startPart = tk.getWordPrefix();
    this.setSourceRange(position - startPart.length(), position);

    // 090813:
    RutaModuleDeclaration parsed = (RutaModuleDeclaration) this.parser.parse(module);

    // types = getShortNames(types);
    if (classloader == null) {
      IScriptProject scriptProject = sourceModule.getModelElement().getScriptProject();
      try {
        Collection<String> dependencies = RutaProjectUtils.getClassPath(scriptProject.getProject());
        URL[] urls = new URL[dependencies.size()];
        int counter = 0;
        for (String dep : dependencies) {
          urls[counter] = new File(dep).toURI().toURL();
          counter++;
        }
        classloader = new URLClassLoader(urls);
      } catch (MalformedURLException e) {
        RutaIdeCorePlugin.error(e);
      } catch (CoreException e) {
        RutaIdeCorePlugin.error(e);
      }
    }

    ASTNode node;
    if (parsed != null) {
      try {
        RutaReferenceVisitor referenceVisitor = new RutaReferenceVisitor(actualCompletionPosition);
        parsed.traverse(referenceVisitor);
        node = referenceVisitor.getResult();

        if (node == null) {
          doCompletionOnEmptyStatement(module, position, i);
          doCompletionOnDeclaration(module, startPart);
          doCompletionOnVarRef(module, parsed, startPart, RutaTypeConstants.RUTA_TYPE_AT, "");
        } else if (node instanceof RutaVariableReference) {
          int type = ((RutaVariableReference) node).getType();
          doCompletionOnVarRef(module, parsed, startPart, type,
                  ((RutaVariableReference) node).getName());
          if (RutaParseUtils.isAtLineStart(node, content)) {
            doCompletionOnDeclaration(module, startPart);
          }
        } else if (node instanceof ComponentDeclaration) {
          doCompletionOnComponentDeclaration(module, parsed, startPart,
                  ((ComponentDeclaration) node).getType(), startPart);
        } else if (node instanceof ComponentReference) {
          doCompletionOnComponentReference(module, parsed, startPart,
                  ((ComponentReference) node).getType(), startPart);
        } else if (node instanceof RutaAction) {
          doCompletionOnAction(module, parsed, startPart, RutaTypeConstants.RUTA_TYPE_A, startPart);
          doCompletionOnVarRef(module, parsed, startPart, RutaTypeConstants.RUTA_TYPE_AT, "");
        } else if (node instanceof RutaCondition) {
          doCompletionOnCondition(module, parsed, startPart, RutaTypeConstants.RUTA_TYPE_C,
                  startPart);
          doCompletionOnVarRef(module, parsed, startPart, RutaTypeConstants.RUTA_TYPE_AT, "");
        }
        // if(requestor.)
        // doCompletionOnKeyword(position, i, startPart);

        // }
      } catch (Exception e) {
        System.out.println("no completion node found");
      } finally {
        this.requestor.endReporting();
      }
    }
  }

  private void doCompletionOnComponentReference(IModuleSource cu, RutaModuleDeclaration parsed,
          String startPart, int type, String complString) {
    Collection<String> importedEngines = parsed.descriptorInfo.getImportedEngines();
    importedEngines.addAll(parsed.descriptorInfo.getImportedUimafitEngines());
    for (String string : importedEngines) {
      if (match(complString, string)) {
        addProposal(complString, string, CompletionProposal.PACKAGE_REF);
      }
      int indexOf = string.lastIndexOf(".");
      if (indexOf != -1) {
        String shortName = string.substring(indexOf + 1, string.length());
        if (match(complString, shortName)) {
          addProposal(complString, shortName, CompletionProposal.PACKAGE_REF);
        }
      }
    }
    Collection<String> importedScripts = parsed.descriptorInfo.getImportedScripts();
    for (String each : importedScripts) {
      String[] split = each.split("\\.");
      String string = split[split.length - 1];
      if (match(complString, string)) {
        addProposal(complString, string, CompletionProposal.PACKAGE_REF);
      }
    }
    List<IMethod> blocks = new ArrayList<IMethod>();
    try {
      IModelElement[] children = sourceModule.getModelElement().getModel().getChildren();
      for (IModelElement iModelElement : children) {
        if (iModelElement instanceof SourceMethod) {
          collectBlocks((SourceMethod) iModelElement, blocks);
        }
      }
    } catch (ModelException e) {
    }
    for (IMethod m : blocks) {
      String string = m.getElementName();
      if (match(complString, string)) {
        addProposal(complString, string, CompletionProposal.PACKAGE_REF);
      }
    }
  }

  private void doCompletionOnComponentDeclaration(IModuleSource cu, RutaModuleDeclaration parsed,
          String startPart, int type, String complString) throws Exception {
    if (type == ComponentDeclaration.SCRIPT) {
      List<String> scripts = new ArrayList<String>();

      List<IFolder> scriptFolders = RutaProjectUtils.getAllScriptFolders(sourceModule
              .getModelElement().getScriptProject());
      for (IFolder folder : scriptFolders) {
        try {
          scripts.addAll(collectScripts(folder, ""));
        } catch (CoreException e) {
        }
      }
      Resource[] resources = getFilesInClasspath(complString, "ruta");
      for (Resource resource : resources) {
        try {
          String string = getScriptRepresentation(resource, "ruta");
          if (string != null && !scripts.contains(string)) {
            scripts.add(string);
          }
        } catch (Exception e) {
          // do not report the exceptions
        }
      }

      for (String string : scripts) {
        if (match(complString, string)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    } else if (type == ComponentDeclaration.UIMAFIT_ENGINE) {
      List<String> engines = new ArrayList<String>();
      ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
              true);
      ResourceLoader resourceLoader = new DefaultResourceLoader(classloader);
      provider.setResourceLoader(resourceLoader);
      provider.addIncludeFilter(new AssignableTypeFilter(AnalysisComponent.class));

      String pack = complString.replaceAll("[.]", "/");
      if (pack.endsWith("/")) {
        pack = pack.substring(0, pack.length() - 1);
      }
      Set<BeanDefinition> components = provider.findCandidateComponents(pack);
      for (BeanDefinition component : components) {
        String beanClassName = component.getBeanClassName();
        engines.add(beanClassName);
      }
      for (String string : engines) {
        if (match(complString, string)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    } else if (type == ComponentDeclaration.ENGINE) {
      List<String> engines = new ArrayList<String>();
      Resource[] resources = getFilesInClasspath(complString, "xml");
      for (Resource resource : resources) {
        try {
          UIMAFramework.getXMLParser().parseAnalysisEngineDescription(
                  new XMLInputSource(resource.getURL()));
          String string = getScriptRepresentation(resource, "xml");
          if (string != null) {
            engines.add(string);
          }
        } catch (Exception e) {
          // do not report the exceptions
        }
      }
      if (StringUtils.isAllUpperCase(complString)) {
        List<IFolder> descriptorFolders = RutaProjectUtils.getAllDescriptorFolders(sourceModule
                .getModelElement().getScriptProject().getProject());
        for (IFolder folder : descriptorFolders) {
          try {
            engines.addAll(collectEngines(folder, ""));
          } catch (CoreException e) {
          }
        }
      }
      for (String string : engines) {
        if (match(complString, string)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    } else {
      List<String> tss = new ArrayList<String>();
      Resource[] resources = getFilesInClasspath(complString, "xml");
      for (Resource resource : resources) {
        try {
          UIMAFramework.getXMLParser().parseTypeSystemDescription(
                  new XMLInputSource(resource.getURL()));
          String string = getScriptRepresentation(resource, "xml");
          if (string != null) {
            tss.add(string);
          }
        } catch (Exception e) {
          // do not report the exceptions
        }
      }
      if (StringUtils.isAllUpperCase(complString)) {
        // fallback for camel case
        List<IFolder> descriptorFolders = RutaProjectUtils.getAllDescriptorFolders(sourceModule
                .getModelElement().getScriptProject().getProject());
        for (IFolder folder : descriptorFolders) {
          try {
            tss.addAll(collectTypeSystems(folder, ""));
          } catch (CoreException e) {
          }
        }
      }
      for (String string : tss) {
        if (match(complString, string)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    }
  }

  private Resource[] getFilesInClasspath(String prefix, String extension) throws IOException {
    String pack = prefix.replaceAll("[.]", "/");
    if (pack.endsWith("/")) {
      pack = pack.substring(0, pack.length() - 1);
    }
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
            classloader);
    String p = "classpath*:";
    String suffix = "/**/*." + extension;
    String pattern = p + pack + suffix;
    Resource[] resources = resolver.getResources(pattern);
    return resources;
  }

  private String getScriptRepresentation(Resource resource, String suffix) throws IOException {
    URL url = resource.getURL();
    URL[] urLs = classloader.getURLs();
    String externalForm = url.toExternalForm();
    for (URL each : urLs) {
      String eachExternalForm = each.toExternalForm();
      if (externalForm.startsWith("jar:")) {
        eachExternalForm = "jar:" + eachExternalForm + "!/";
      }
      if (externalForm.startsWith(eachExternalForm)) {
        String name = externalForm.substring(eachExternalForm.length(), externalForm.length()
                - (suffix.length() + 1));
        name = name.replaceAll("[/]", ".");
        return name;
      }
    }
    return null;
  }

  private List<String> collectEngines(IFolder folder, String prefix) throws CoreException {
    List<String> result = new ArrayList<String>();
    IResource[] members = folder.members();
    for (IResource iResource : members) {
      if (iResource instanceof IFolder) {
        IFolder folder2 = (IFolder) iResource;
        String newPrefix = prefix + folder2.getProjectRelativePath().lastSegment() + ".";
        result.addAll(collectEngines(folder2, newPrefix));
      } else if (iResource instanceof IFile) {
        IFile file = (IFile) iResource;
        if (file.getFileExtension().equals("xml")) {
          File f = new File(file.getLocation().toPortableString());
          if (f.exists()) {
            try {
              UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(f));
              result.add(prefix + file.getName().substring(0, file.getName().length() - 4));
            } catch (Exception e) {
            }
          }
        }
      }
    }
    return result;
  }

  private List<String> collectTypeSystems(IFolder folder, String prefix) throws CoreException {
    List<String> result = new ArrayList<String>();
    IResource[] members = folder.members();
    for (IResource iResource : members) {
      if (iResource instanceof IFolder) {
        IFolder folder2 = (IFolder) iResource;
        String newPrefix = prefix + folder2.getProjectRelativePath().lastSegment() + ".";
        result.addAll(collectTypeSystems(folder2, newPrefix));
      } else if (iResource instanceof IFile) {
        IFile file = (IFile) iResource;
        String fileExtension = file.getFileExtension();
        if (fileExtension != null && fileExtension.equals("xml")) {
          File f = new File(file.getLocation().toPortableString());
          if (f.exists()) {
            try {
              UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(f));
              result.add(prefix + file.getName().substring(0, file.getName().length() - 4));
            } catch (Exception e) {
            }
          }
        }
      }
    }
    return result;
  }

  private List<String> collectScripts(IFolder folder, String prefix) throws CoreException {
    List<String> result = new ArrayList<String>();
    IResource[] members = folder.members();
    for (IResource iResource : members) {
      if (iResource instanceof IFolder) {
        IFolder folder2 = (IFolder) iResource;
        String newPrefix = prefix + folder2.getProjectRelativePath().lastSegment() + ".";
        result.addAll(collectScripts(folder2, newPrefix));
      } else if (iResource instanceof IFile) {
        IFile file = (IFile) iResource;
        if (file.getFileExtension().equals("ruta")) {
          result.add(prefix + file.getName().substring(0, file.getName().length() - 5));
        }
      }
    }
    return result;
  }

  private void doCompletionOnDeclaration(IModuleSource cu, String startPart) {
    String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.DECLARATION);
    for (String string : keywords) {
      if (match(startPart, string)) {
        addProposal(startPart, string, CompletionProposal.KEYWORD);
      }
    }
  }


  private Set<String> getTypes(IPath typeSystemDescriptorPath) throws InvalidXMLException,
          IOException {
    Set<String> types = new HashSet<String>();
    URL url = URIUtil.toURI(typeSystemDescriptorPath.toPortableString()).toURL();
    try {
      ResourceManager resMgr = new ResourceManager_impl(classloader);
      types = getTypes(url, resMgr);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return types;
  }

  private Set<String> getTypes(URL resource, ResourceManager resMgr) throws IOException,
          InvalidXMLException {
    Set<String> types = new HashSet<String>();
    TypeSystemDescription typeSysDescr = null;
    typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
            new XMLInputSource(resource));
    typeSysDescr.resolveImports(resMgr);
    for (TypeDescription each : typeSysDescr.getTypes()) {
      String name = each.getName();
      String[] nameSpace = name.split("[.]");
      types.add(nameSpace[nameSpace.length - 1]);
      types.add(name);
    }
    return types;
  }

  private void doCompletionOnAction(IModuleSource cu, RutaModuleDeclaration parsed,
          String startPart, int type, String complString) {
    String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.ACTION);
    for (String string : keywords) {
      if (match(complString, string)) {
        addProposal(complString, string + "()", string, CompletionProposal.METHOD_NAME_REFERENCE);
      }
    }
  }

  private void doCompletionOnCondition(IModuleSource cu, RutaModuleDeclaration parsed,
          String startPart, int type, String complString) {
    if (complString.startsWith("-")) {
      complString = complString.substring(1, complString.length());
    }
    String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.CONDITION);
    for (String string : keywords) {
      if (match(complString, string)) {
        addProposal(complString, string + "()", string, CompletionProposal.METHOD_NAME_REFERENCE);
      }
    }
  }

  private void doCompletionOnVarRef(IModuleSource cu, RutaModuleDeclaration parsed,
          String startPart, int type, String complString) {
    Collection<String> types = new HashSet<String>();
    if (type == RutaTypeConstants.RUTA_TYPE_AT) {
      try {
        IPath path = sourceModule.getModelElement().getResource().getLocation();
        IPath typeSystemDescriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(path, sourceModule.getModelElement().getScriptProject().getProject(), classloader);
        types = getTypes(typeSystemDescriptorPath);
      } catch (Exception e) {
      }
      for (String string : types) {
        if (match(startPart, string)) {
          addProposal(startPart, string, CompletionProposal.TYPE_REF);
        }
      }
    } else {
      IModelElement modelElement = sourceModule.getModelElement();
      if (modelElement instanceof SourceModule) {
        SourceModule sm = (SourceModule) modelElement;
        try {
          IField[] fields = sm.getFields();
          for (IField iField : fields) {
            SourceField f = (SourceField) iField;
            int fieldType = RutaParseUtils.getTypeOfIModelElement(f);
            if (RutaTypeConstants.RUTA_TYPE_N == type) {
              if (fieldType == RutaTypeConstants.RUTA_TYPE_N
                      || fieldType == RutaTypeConstants.RUTA_TYPE_I
                      || fieldType == RutaTypeConstants.RUTA_TYPE_D
                      || fieldType == RutaTypeConstants.RUTA_TYPE_F) {
                addProposal(startPart, f.getElementName(), CompletionProposal.LOCAL_VARIABLE_REF);
              }
            } else if (type == fieldType) {
              addProposal(startPart, f.getElementName(), CompletionProposal.LOCAL_VARIABLE_REF);
            }

          }
        } catch (ModelException e) {
        }

      }
    }
  }

  private boolean match(String complString, String string) {
    return string.toLowerCase().startsWith(complString.toLowerCase())
            || removeLowerCase(string).startsWith(complString);
  }

  private void collectBlocks(SourceMethod sm, List<IMethod> blocks) throws ModelException {
    blocks.add(sm);
    IModelElement[] children = sm.getChildren();
    for (IModelElement me : children) {
      if (me instanceof SourceMethod) {
        collectBlocks((SourceMethod) me, blocks);
      }
    }
  }

  private String removeLowerCase(String string) {
    String replaceAll = string.replaceAll("\\p{Lower}|[.]", "");
    return replaceAll;
  }

  private void doCompletionOnEmptyStatement(IModuleSource cu, int position, int i) {
    int kind = CompletionProposal.LOCAL_VARIABLE_REF;
    if (!super.requestor.isIgnored(kind)) {
      suggestFields(cu);
    }
  }

  /**
   * @param cu
   */
  private void suggestFields(IModuleSource cu) {
    // try {
    // if (cu != null) {
    // IField[] fieldsArray = sourceModule.getFields();
    // for (IField field : fieldsArray) {
    // int relevance = RelevanceConstants.R_EXACT_EXPECTED_TYPE;
    // // accept result
    // super.noProposal = false;
    // int kind = CompletionProposal.LOCAL_VARIABLE_REF;
    // if (!super.requestor.isIgnored(kind)) {
    // CompletionProposal proposal = super.createProposal(kind, actualCompletionPosition);
    // proposal.setRelevance(relevance);
    // proposal.setModelElement(field);
    // proposal.setName(field.getElementName());
    // proposal.setCompletion(field.getElementName());
    // proposal.setReplaceRange(this.startPosition - this.offset, this.endPosition
    // - this.offset);
    // try {
    // proposal.setFlags(field.getFlags());
    // } catch (ModelException e) {
    // }
    // this.requestor.accept(proposal);
    // if (DEBUG) {
    // this.printDebug(proposal);
    // }
    // }
    // }
    // }
    // } catch (ModelException e) {
    // }
  }


  private void addProposal(String complString, String string, int kind) {
    addProposal(complString, string, string, kind);
  }

  @SuppressWarnings("deprecation")
  private void addProposal(String complString, String string, String name, int kind) {
    char[] fieldName = string.toCharArray();
    char[] complFragment = complString.toCharArray();

    char[] pattern = null;
    if (complString.length() > 0) {
      pattern = complString.toCharArray();
    }
    if (CharOperation.camelCaseMatch(pattern, fieldName) || match(complString, string)) {

      int relevance = RelevanceConstants.R_DEFAULT + 1;
      relevance += computeRelevanceForCaseMatching(complFragment, string);

      // accept result
      super.noProposal = false;
      if (!super.requestor.isIgnored(kind)) {
        CompletionProposal proposal = super.createProposal(kind, actualCompletionPosition);
        proposal.setRelevance(relevance);
        proposal.setName(name);
        proposal.setCompletion(string);
        proposal.setReplaceRange(this.startPosition - this.offset, this.endPosition - this.offset);
        // try {
        // proposal.setFlags(field.getFlags());
        // } catch (ModelException e) {
        // }
        this.requestor.accept(proposal);
        if (DEBUG) {
          this.printDebug(proposal);
        }
      }
    }
  }


}
