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

package org.apache.uima.textmarker.ide.core.codeassist;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.ide.core.ITextMarkerKeywords;
import org.apache.uima.textmarker.ide.core.TextMarkerExtensionManager;
import org.apache.uima.textmarker.ide.core.TextMarkerKeywordsManager;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.core.extensions.ICompletionExtension;
import org.apache.uima.textmarker.ide.core.parser.TextMarkerParseUtils;
import org.apache.uima.textmarker.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.ComponentReference;
import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerModuleDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.codeassist.ScriptCompletionEngine;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.SourceField;
import org.eclipse.dltk.internal.core.SourceMethod;

public class TextMarkerCompletionEngine extends ScriptCompletionEngine {

  protected TextMarkerCompletionParser parser;

  protected org.eclipse.dltk.core.ISourceModule sourceModule;

  protected final static boolean TRACE_COMPLETION_TIME = false;

  private ICompletionExtension[] extensions;

  public TextMarkerCompletionEngine() {
    extensions = TextMarkerExtensionManager.getDefault().getCompletionExtensions();
    this.parser = new TextMarkerCompletionParser(extensions);
  }

  @Override
  protected int getEndOfEmptyToken() {
    return actualCompletionPosition;
  }

  @Override
  protected String processFieldName(IField field, String token) {
    return TextMarkerParseUtils.processFieldName(field, token);
  }

  @Override
  protected String processMethodName(IMethod method, String token) {
    return TextMarkerParseUtils.processMethodName(method, token);
  }

  @Override
  protected String processTypeName(IType type, String token) {
    return TextMarkerParseUtils.processTypeName(type, token);
  }

  @Override
  public IAssistParser getParser() {
    return parser;
  }

  public void complete(ISourceModule cu, int position, int i) {
    this.sourceModule = (org.eclipse.dltk.core.ISourceModule) cu.getModelElement();
    this.actualCompletionPosition = position;
    this.offset = i;
    this.requestor.beginReporting();
    String content = cu.getSourceContents();
    if (position < 0 || position > content.length()) {
      return;
    }

    TMAutoCompletionToolkit tk = new TMAutoCompletionToolkit(content, position);

    String startPart = tk.getWordPrefix();
    this.setSourceRange(position - startPart.length(), position);

    // 090813:
    TextMarkerModuleDeclaration parsed = (TextMarkerModuleDeclaration) this.parser.parse(cu);

    // types = getShortNames(types);

    ASTNode node;
    if (parsed != null) {
      try {
        TextMarkerReferenceVisitor referenceVisitor = new TextMarkerReferenceVisitor(
                actualCompletionPosition);
        parsed.traverse(referenceVisitor);
        node = referenceVisitor.getResult();
        if (node == null) {
          doCompletionOnEmptyStatement(cu, position, i);
          doCompletionOnDeclaration(cu, startPart);
        } else if (node instanceof TextMarkerVariableReference) {
          int type = ((TextMarkerVariableReference) node).getType();
          doCompletionOnVarRef(cu, parsed, startPart, type,
                  ((TextMarkerVariableReference) node).getName());
          // TODO: only if first rule element
          doCompletionOnDeclaration(cu, startPart);
        } else if (node instanceof ComponentDeclaration) {
          doCompletionOnComponentDeclaration(cu, parsed, startPart,
                  ((ComponentDeclaration) node).getType(), startPart);
        } else if (node instanceof ComponentReference) {
          doCompletionOnComponentReference(cu, parsed, startPart,
                  ((ComponentReference) node).getType(), startPart);
        } else if (node instanceof TextMarkerAction) {
          doCompletionOnAction(cu, parsed, startPart, TMTypeConstants.TM_TYPE_A, startPart);
        } else if (node instanceof TextMarkerCondition) {
          doCompletionOnCondition(cu, parsed, startPart, TMTypeConstants.TM_TYPE_C, startPart);
        }
        // if(requestor.)
        // doCompletionOnKeyword(position, i, startPart);

        // }
      } catch (Exception e) {
        System.out.println("no completion node found");
        System.out.println("");
      } finally {
        this.requestor.endReporting();
      }
    }
  }

  private void doCompletionOnComponentReference(ISourceModule cu,
          TextMarkerModuleDeclaration parsed, String startPart, int type, String complString) {
    Collection<String> importedEngines = parsed.descriptorInfo.getImportedEngines();
    for (String string : importedEngines) {
      if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
        addProposal(complString, string, CompletionProposal.PACKAGE_REF);
      }
    }
    Collection<String> importedScripts = parsed.descriptorInfo.getImportedScripts();
    for (String each : importedScripts) {
      String[] split = each.split("\\.");
      String string = split[split.length - 1];
      if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
        addProposal(complString, string, CompletionProposal.PACKAGE_REF);
      }
    }
    List<IMethod> blocks = new ArrayList<IMethod>();
    try {
      IModelElement[] children = sourceModule.getChildren();
      for (IModelElement iModelElement : children) {
        if (iModelElement instanceof SourceMethod) {
          collectBlocks((SourceMethod) iModelElement, blocks);
        }
      }
    } catch (ModelException e) {
    }
    for (IMethod m : blocks) {
      String string = m.getElementName();
      if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
        addProposal(complString, string, CompletionProposal.PACKAGE_REF);
      }
    }
  }

  private void doCompletionOnComponentDeclaration(ISourceModule cu,
          TextMarkerModuleDeclaration parsed, String startPart, int type, String complString)
          throws CoreException {
    if (type == ComponentDeclaration.SCRIPT) {
      List<IFolder> scriptFolders = TextMarkerProjectUtils.getAllScriptFolders(sourceModule
              .getScriptProject());

      List<String> scripts = new ArrayList<String>();
      for (IFolder folder : scriptFolders) {
        try {
          scripts.addAll(collectScripts(folder, ""));
        } catch (CoreException e) {
        }
      }
      for (String string : scripts) {
        if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    } else if (type == ComponentDeclaration.ENGINE) {
      List<IFolder> descriptorFolders = TextMarkerProjectUtils.getAllDescriptorFolders(sourceModule
              .getScriptProject().getProject());
      List<String> engines = new ArrayList<String>();
      for (IFolder folder : descriptorFolders) {
        try {
          engines.addAll(collectEngines(folder, ""));
        } catch (CoreException e) {
        }
      }
      for (String string : engines) {
        if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    } else {
      List<IFolder> descriptorFolders = TextMarkerProjectUtils.getAllDescriptorFolders(sourceModule
              .getScriptProject().getProject());
      List<String> tss = new ArrayList<String>();
      for (IFolder folder : descriptorFolders) {
        try {
          tss.addAll(collectTypeSystems(folder, ""));
        } catch (CoreException e) {
        }
      }
      for (String string : tss) {
        if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
          addProposal(complString, string, CompletionProposal.PACKAGE_REF);
        }
      }
    }
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
        if (file.getFileExtension().equals("xml")) {
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
        if (file.getFileExtension().equals("tm")) {
          result.add(prefix + file.getName().substring(0, file.getName().length() - 3));
        }
      }
    }
    return result;
  }

  private void doCompletionOnDeclaration(ISourceModule cu, String startPart) {
    String[] keywords = TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.DECLARATION);
    for (String string : keywords) {
      if (string.startsWith(startPart) || removeLowerCase(string).startsWith(startPart)) {
        addProposal(startPart, string, CompletionProposal.KEYWORD);
      }
    }
  }

  private Set<String> importTypeSystem(String xmlFilePath, IProject project)
          throws InvalidXMLException, IOException {
    IFolder folder = project.getProject().getFolder(
            TextMarkerProjectUtils.getDefaultDescriptorLocation());
    xmlFilePath = xmlFilePath.substring(0, xmlFilePath.length() - 3) + "TypeSystem.xml";
    return getTypes(folder, xmlFilePath);
  }

  private Set<String> getTypes(IFolder folder, String filePath) throws InvalidXMLException,
          IOException {
    Set<String> types = new HashSet<String>();
    IFile iFile = getFile(folder, filePath);
    URL url;
    try {
      url = iFile.getLocationURI().toURL();
      ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
      resMgr.setDataPath(folder.getLocation().toPortableString());
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
      StringBuffer fullName = new StringBuffer();
      if (nameSpace.length > 0) {
        fullName.append(nameSpace[0]);
      }
      for (int i = 1; i < nameSpace.length; i++) {
        // TODO fix workaround
        if (!nameSpace[i].equals("type")) {
          fullName.append('.');
          fullName.append(nameSpace[i]);
        }
      }
      types.add(fullName.toString());
    }
    return types;
  }

  private IFile getFile(IFolder folder, String filePath) {
    int lastDot = filePath.lastIndexOf('.');
    int sndLastDot = filePath.lastIndexOf('.', lastDot - 1);
    String fName = filePath;
    if (sndLastDot >= 0) {
      String subFolder = filePath.substring(0, sndLastDot);
      folder = folder.getFolder(subFolder);
      fName = filePath.substring(sndLastDot + 1);
    }
    return folder.getFile(fName);
  }

  private void doCompletionOnAction(ISourceModule cu, TextMarkerModuleDeclaration parsed,
          String startPart, int type, String complString) {
    String[] keywords = TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.ACTION);
    for (String string : keywords) {
      if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
        addProposal(complString, string, CompletionProposal.METHOD_NAME_REFERENCE);
      }
    }
  }

  private void doCompletionOnCondition(ISourceModule cu, TextMarkerModuleDeclaration parsed,
          String startPart, int type, String complString) {
    if (complString.startsWith("-")) {
      complString = complString.substring(1, complString.length());
    }
    String[] keywords = TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.CONDITION);
    for (String string : keywords) {
      if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
        addProposal(complString, string, CompletionProposal.METHOD_NAME_REFERENCE);
      }
    }
  }

  private void doCompletionOnVarRef(ISourceModule cu, TextMarkerModuleDeclaration parsed,
          String startPart, int type, String complString) {
    Collection<String> types = new HashSet<String>();
    try {
      IPath path = sourceModule.getPath();
      path = path.removeFirstSegments(2);
      types = importTypeSystem(path.toPortableString(), sourceModule.getScriptProject()
              .getProject());
    } catch (Exception e) {
    }
    for (String string : types) {
      if (string.startsWith(complString) || removeLowerCase(string).startsWith(complString)) {
        addProposal(complString, string, CompletionProposal.TYPE_REF);
      }
    }
    try {
      if (cu != null) {
        IField[] fieldsArray = sourceModule.getFields();
        for (IField field : fieldsArray) {
          // if (type != TMTypeConstants.TM_TYPE_AT && Flags.isPrivate(field.getFlags())
          // || (type & TMTypeConstants.TM_TYPE_AT) != 0 && Flags.isPublic(field.getFlags())) {
          if ((type & TextMarkerParseUtils.getTypeOfIModelElement(field)) != 0) {
            addProposal(complString, new ArrayList<String>(), field);
          }
        }
        List<IField> fields = new ArrayList<IField>();
        IModelElement[] children = sourceModule.getChildren();
        for (IModelElement iModelElement : children) {
          if (iModelElement instanceof SourceMethod) {
            collectFields((SourceMethod) iModelElement, fields);
          }
        }
        for (IField field : fields) {
          // if (type != TMTypeConstants.TM_TYPE_AT && Flags.isPrivate(field.getFlags())
          // || (type & TMTypeConstants.TM_TYPE_AT) != 0 && Flags.isPublic(field.getFlags())) {
          // if ((type & TextMarkerParseUtils.getTypeOfIModelElement(field)) != 0) {
          if (!types.contains(field.getElementName())) {
            addProposal(complString, new ArrayList<String>(), field);
          }
          // }
        }
      }
    } catch (ModelException e) {
    }
  }

  private void collectFields(SourceMethod sm, List<IField> fields) throws ModelException {
    IModelElement[] children = sm.getChildren();
    for (IModelElement me : children) {
      if (me instanceof SourceMethod) {
        collectFields((SourceMethod) me, fields);
      } else if (me instanceof SourceField) {
        fields.add((IField) me);
      }
    }
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

  @SuppressWarnings({ "unchecked" })
  private void doCompletionOnEmptyStatement(ISourceModule cu, int position, int i) {
    int kind = CompletionProposal.LOCAL_VARIABLE_REF;
    // doCompletionOnKeyword(position, i, "");
    if (!super.requestor.isIgnored(kind)) {
      suggestFields(cu);
    }
  }

  /**
   * @param cu
   */
  private void suggestFields(ISourceModule cu) {
    List names = new ArrayList();
    try {
      if (cu != null) {
        IField[] fieldsArray = sourceModule.getFields();
        for (IField field : fieldsArray) {
          int relevance = RelevanceConstants.R_EXACT_EXPECTED_TYPE;
          // accept result
          super.noProposal = false;
          int kind = CompletionProposal.LOCAL_VARIABLE_REF;
          if (!super.requestor.isIgnored(kind)) {
            CompletionProposal proposal = super.createProposal(kind, actualCompletionPosition);
            proposal.setRelevance(relevance);
            proposal.setModelElement(field);
            char[] fieldName = field.getElementName().toCharArray();
            proposal.setName(fieldName);
            proposal.setCompletion(fieldName);
            proposal.setReplaceRange(this.startPosition - this.offset, this.endPosition
                    - this.offset);
            try {
              proposal.setFlags(field.getFlags());
            } catch (ModelException e) {
            }
            this.requestor.accept(proposal);
            if (DEBUG) {
              this.printDebug(proposal);
            }
          }
        }
      }
    } catch (ModelException e) {
    }
  }

  /**
   * @param complString
   * @param names
   * @param field
   */
  private void addProposal(String complString, List names, IField field) {
    char[] fieldName = field.getElementName().toCharArray();
    char[] complFragment = complString.toCharArray();

    if (CharOperation.camelCaseMatch(complString.toCharArray(), fieldName)) {

      int relevance = RelevanceConstants.R_DEFAULT;
      relevance += computeRelevanceForCaseMatching(complFragment, fieldName);

      // accept result
      super.noProposal = false;
      int kind = CompletionProposal.LOCAL_VARIABLE_REF;
      if (!super.requestor.isIgnored(kind)) {
        CompletionProposal proposal = super.createProposal(kind, actualCompletionPosition);
        proposal.setRelevance(relevance);
        proposal.setModelElement(field);
        proposal.setName(fieldName);
        proposal.setCompletion(fieldName);
        proposal.setReplaceRange(this.startPosition - this.offset, this.endPosition - this.offset);
        try {
          proposal.setFlags(field.getFlags());
        } catch (ModelException e) {
        }
        this.requestor.accept(proposal);
        if (DEBUG) {
          this.printDebug(proposal);
        }
      }
    }
  }

  private void addProposal(String complString, String string, int kind) {
    char[] fieldName = string.toCharArray();
    char[] complFragment = complString.toCharArray();

    char[] pattern = null;
    if (complString.length() > 0) {
      pattern = complString.toCharArray();
    }
    if (CharOperation.camelCaseMatch(pattern, fieldName)) {

      int relevance = RelevanceConstants.R_DEFAULT;
      relevance += computeRelevanceForCaseMatching(complFragment, fieldName);

      // accept result
      super.noProposal = false;
      if (!super.requestor.isIgnored(kind)) {
        CompletionProposal proposal = super.createProposal(kind, actualCompletionPosition);
        proposal.setRelevance(relevance);
        proposal.setName(fieldName);
        proposal.setCompletion(fieldName);
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

  private void doCompletionOnKeyword(int position, int pos, String startPart) {
    List<String> keywords = new ArrayList<String>();
    for (int i = 0; i < ITextMarkerKeywords.END_INDEX; i++) {
      keywords.addAll(Arrays.asList(TextMarkerKeywordsManager.getKeywords(i)));
    }
    char[][] keyWordsArray = new char[keywords.size()][];
    for (int a = 0; a < keywords.size(); a++) {
      keyWordsArray[a] = keywords.get(a).toCharArray();
    }
    findKeywords(startPart.toCharArray(), keyWordsArray, true);
  }

}
