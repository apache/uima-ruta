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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.core.parser.TextMarkerParseUtils;
import org.apache.uima.textmarker.ide.parser.ast.TMActionConstants;
import org.apache.uima.textmarker.ide.parser.ast.TMStatementConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerImportStatement;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.ScriptSelectionEngine;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IOpenable;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;

public class TextMarkerSelectionEngine extends ScriptSelectionEngine {

  private class ModelElementFinder extends ASTVisitor {
    private int offset;

    private List results;

    private Set<String> engines;

    private Set<String> typesystems;

    private Map<String, org.eclipse.dltk.core.ISourceModule> scripts;

    private Map<String, IField> impFields;

    private String curFileName;

    public ModelElementFinder(int offset, List results, String curFileName) {
      super();
      this.offset = offset;
      this.results = results;
      this.engines = new HashSet<String>();
      this.typesystems = new HashSet<String>();
      this.scripts = new HashMap<String, org.eclipse.dltk.core.ISourceModule>();
      this.impFields = new HashMap<String, IField>();
      this.curFileName = curFileName;
      try {
        importScript(parseFileName(curFileName));
      } catch (Exception e) {
      }
    }

    private String parseFileName(String curFileName2) {
      int i = curFileName2.indexOf("script");
      String s = curFileName2.substring(i + "scripts".length() - 1);
      s = s.replace('/', '.');
      if (s.endsWith(".tm")) {
        s = s.substring(1, s.length() - 3);
      }
      return s;
    }

    @Override
    public boolean visit(Statement s) throws Exception {
      if (s instanceof TextMarkerImportStatement) {
        // handle engine imports
        if (((TextMarkerImportStatement) s).getType() == TMStatementConstants.S_IMPORT_ENGINE) {
          SimpleReference sRef = (SimpleReference) ((TextMarkerImportStatement) s).getExpression();
          importEngine(sRef.getName());
        }
        // handle script imports
        if (((TextMarkerImportStatement) s).getType() == TMStatementConstants.S_IMPORT_SCRIPT) {
          importScript(s);
        }
        // handle type system imports
        if (((TextMarkerImportStatement) s).getType() == TMStatementConstants.S_IMPORT_TYPESYSTEM) {
          importTypesystem(s);
        }
        return false;
      }
      return true;
    }

    @Override
    public boolean visit(Expression s) throws Exception {
      if (s.sourceStart() <= offset && offset <= s.sourceEnd()) {
        if (s instanceof VariableReference) {
          VariableReference ref = (VariableReference) s;
          // TODO refactor: extern declaration always checked, even if
          // local decl found
          String name = ((VariableReference) s).getName();
          findLocalDeclaration(name, results, IField.class);
          if (impFields.containsKey(name)) {
            results.add(impFields.get(name));
          }
        }
        if (s.getKind() == TMActionConstants.A_CALL) {
          SimpleReference sr = (SimpleReference) s.getChilds().get(0);
          if (sr != null) {
            String name = sr.getName();
            if (engines.contains(name)) {
              // referenceEngineCall(name);
            } else if (scripts.containsKey(name)) {
              findImportedDeclaration(name, results, IMethod.class);
            } else {
              checkInnerBlockRef(name, "");
              name = name.substring(name.lastIndexOf('.') + 1);
              findLocalDeclaration(name, results, IMethod.class);
            }
          }
        }
      }
      return super.visit(s);
    }

    @Override
    public boolean visit(MethodDeclaration s) throws Exception {
      if (s.getNameStart() <= offset && offset <= s.getNameEnd()) {
        findLocalDeclaration(s.getName(), results, IMethod.class);
      }
      return super.visit(s);
    }

    @Override
    public boolean visit(TypeDeclaration s) throws Exception {
      if (s.getNameStart() <= offset && offset <= s.getNameEnd()) {
        findLocalDeclaration(s.getName(), results, IField.class);
        // TODO ??
      }
      return super.visit(s);
    }

    private void checkInnerBlockRef(String head, String tail) {
      int li = head.lastIndexOf('.');
      if (li > 0) {
        String frontPart = head.substring(0, li);
        if (tail.equals("")) {
          tail = head.substring(li + 1);
        }
        if (scripts.containsKey(frontPart)) {
          findDeclaration(scripts.get(frontPart), tail, results, IMethod.class);
        } else {
          checkInnerBlockRef(frontPart, tail);
        }
      }
    }

    private void importEngine(String name) {
      engines.add(name);
      int i = name.lastIndexOf('.');
      if (i > 1) {
        String lastPart = name.substring(i + 1);
        if (lastPart != null && lastPart.length() != 0) {
          engines.add(lastPart);
        }
      }
    }

    private void referenceEngineCall(String name) {
      // name = "";
      IScriptProject scriptProject = sourceModule.getScriptProject();
      IFile engine = getEngine(name, scriptProject);
      if (engine == null) {
        return;
      }
      results.add(engine);
      // IScriptProject scriptProject = sourceModule.getScriptProject();
      // IPath path = scriptProject.g;
      // path = path.append(name);
      // IModelElement element;
      // try {
      // element = scriptProject.findElement(path);
      // System.out.println(element.exists() + "bla");
      // } catch (ModelException e) {
      // e.printStackTrace();
      // }
      // sourceModule.
      // scriptProject.findType(packageName, name);
    }

    public IFile getFile(IFolder folder, String filePath) {
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

    /**
     * @param xmlFilePath
     *          absolute full path. i.e.: "org.apache.uima.myengine" ".xml" will be added.
     * @return file.exists
     */
    public IFile getEngine(String xmlFilePath, IScriptProject project) {
      IFolder folder = project.getProject().getFolder(
              TextMarkerProjectUtils.getDefaultDescriptorLocation());
      IFolder f = folder.getFolder("de.martin");
      boolean b = f.exists();
      // xmlFilePath = xmlFilePath.replace('.', '/');
      String fileExtended = xmlFilePath + ".xml";
      IFile file = getFile(folder, fileExtended);
      if (file.exists()) {
        IOpenable opena = (IOpenable) file.getAdapter(IOpenable.class);
        return file;
      }
      return null;
    }

    /**
     * @param s
     * @throws ModelException
     */
    private void importScript(Statement s) throws ModelException {
      SimpleReference sRef = (SimpleReference) ((TextMarkerImportStatement) s).getExpression();
      String sRefName = sRef.getName();
      importScript(sRefName);
    }

    private void importTypesystem(Statement s) {
      SimpleReference sRef = (SimpleReference) ((TextMarkerImportStatement) s).getExpression();
      String sRefName = sRef.getName();

      // TODO not working yet
      // importTypesystem(sRefName);

    }

    private void importTypesystem(String sRefName) {
      typesystems.add(sRefName);
      IFolder folder = sourceModule.getScriptProject().getProject()
              .getFolder(TextMarkerProjectUtils.getDefaultDescriptorLocation());
      if (folder != null) {
        int lastDot = sRefName.lastIndexOf('.');
        String fileNameShort = sRefName.substring(lastDot + 1);
        String folderName = sRefName.substring(0, lastDot);
        String fileName = fileNameShort + ".xml";
        IFolder tsFolder = folder.getFolder(folderName);
        if (tsFolder != null) {
          IFile file = tsFolder.getFile(fileName);
          if (file != null) {
            File tsFile = file.getLocation().toFile();
            try {
              TypeSystemDescription typeSystemDescription = UIMAFramework.getXMLParser()
                      .parseTypeSystemDescription(new XMLInputSource(tsFile));
              TypeDescription[] types = typeSystemDescription.getTypes();
              for (TypeDescription each : types) {
                impFields.put(each.getName(), null);
              }
            } catch (InvalidXMLException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }

    /**
     * @param sRefName
     * @throws ModelException
     */
    private void importScript(String sRefName) throws ModelException {
      int lastDot = sRefName.lastIndexOf('.');
      String fileNameShort = sRefName.substring(lastDot + 1);
      String fileName = fileNameShort + ".tm";

      org.eclipse.dltk.core.ISourceModule sm = null;
      IScriptFolder[] scriptFolders = sourceModule.getScriptProject().getScriptFolders();
      for (int i = 0; i < scriptFolders.length; i++) {
        sm = scriptFolders[i].getSourceModule(fileName);
        if (sm.exists() && sm.getResource() != null && sm.getResource().exists()) {
          scripts.put(sRefName, sm);
          scripts.put(fileNameShort, sm);
          importFields(sm.getFields());
          break;
        }
      }
    }

    private void importFields(IField[] fields) {
      if (fields == null) {
        return;
      }
      for (int i = 0; i < fields.length; i++) {
        if (fields[i] == null) {
          continue;
        }
        IField iField = fields[i];
        IPath path = iField.getPath();
        String fullyQualifiedName = parseFileName(path.toString());
        impFields.put(iField.getElementName(), iField);
        impFields.put(fullyQualifiedName + "." + iField.getElementName(), iField);
      }
    }

    @SuppressWarnings("unchecked")
    private void findImportedDeclaration(final String name, final List results, final Class type) {
      org.eclipse.dltk.core.ISourceModule module = this.scripts.get(name);
      if (module != null && module.exists()) {
        results.add(module);
        // findDeclaration(module, name, results, type);
      }
    }

    @SuppressWarnings("unchecked")
    private void findDeclaration(org.eclipse.dltk.core.ISourceModule sm, final String name,
            final List results, final Class type) {
      try {
        sm.accept(new IModelElementVisitor() {
          public boolean visit(IModelElement element) {
            Class e = element.getClass();
            boolean classCheck = type.isAssignableFrom(e);
            if (element.getElementName().equals(name) && classCheck) {
              results.add(element);
              return false;
            }
            return true;
          }
        });
      } catch (ModelException e) {
        if (DLTKCore.DEBUG) {
          e.printStackTrace();
        }
      }
    }

    @SuppressWarnings("unchecked")
    private void findLocalDeclaration(final String name, final List results, final Class type) {
      try {
        sourceModule.accept(new IModelElementVisitor() {
          public boolean visit(IModelElement element) {
            Class e = element.getClass();
            boolean classCheck = type.isAssignableFrom(e);
            if (element.getElementName().equals(name) && classCheck) {
              results.add(element);
            }
            return true;
          }
        });
      } catch (ModelException e) {
        if (DLTKCore.DEBUG) {
          e.printStackTrace();
        }
      }
    }
  }

  public static boolean DEBUG = DLTKCore.DEBUG_SELECTION;

  protected int actualSelectionStart;

  protected int actualSelectionEnd;

  protected List selectionElements = new ArrayList();

  protected TextMarkerSelectionParser parser = new TextMarkerSelectionParser();

  protected org.eclipse.dltk.core.ISourceModule sourceModule;

  protected IDLTKLanguageToolkit toolkit;

  @Override
  public IAssistParser getParser() {
    return parser;
  }

  public IModelElement[] select(ISourceModule module, final int offset, int i) {
    sourceModule = (org.eclipse.dltk.core.ISourceModule) module.getModelElement();
    ModuleDeclaration moduleDeclaration = this.parser.parse(module);

    final List results = new ArrayList();
    try {
      moduleDeclaration.traverse(new ModelElementFinder(offset, results, String.copyValueOf(module
              .getFileName())));
    } catch (Exception e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return (IModelElement[]) results.toArray(new IModelElement[results.size()]);
  }

  protected boolean checkSelection(String source, int selectionSourceStart, int selectionSourceEnd) {

    boolean cheat = false;
    if (selectionSourceEnd < selectionSourceStart) {
      selectionSourceEnd = selectionSourceStart;
      cheat = true;
    }

    int start = TextMarkerParseUtils.startLineOrNoSymbol(selectionSourceStart, source);
    int end = TextMarkerParseUtils.endLineOrNoSymbol(selectionSourceEnd, source);
    if (end <= start) {
      if (cheat)
        return checkSelection(source, selectionSourceEnd - 1, selectionSourceEnd - 1);
      return false;
    }
    if (start > source.length() || end > source.length()) {
      if (cheat)
        return checkSelection(source, selectionSourceEnd - 1, selectionSourceEnd - 1);
      return false;
    }

    String sub = source.substring(start, end);

    // If contain tabs or spaces, then from start.
    if ((sub.indexOf(' ') != -1 || sub.indexOf('\t') != -1 || sub.indexOf('\n') != -1)) {
      if (cheat)
        return checkSelection(source, selectionSourceEnd - 1, selectionSourceEnd - 1);
      return false;
    }
    this.actualSelectionStart = start;
    this.actualSelectionEnd = end;
    return true;
  }

  @Override
  protected ASTNode parseBlockStatements(ModuleDeclaration unit, int position) {
    // TODO Auto-generated method stub
    return super.parseBlockStatements(unit, position);
  }

}
