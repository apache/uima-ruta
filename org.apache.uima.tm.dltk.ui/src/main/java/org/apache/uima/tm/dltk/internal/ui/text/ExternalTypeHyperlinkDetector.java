package org.apache.uima.tm.dltk.internal.ui.text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.apache.uima.tm.dltk.internal.core.codeassist.TextMarkerReferenceVisitor;
import org.apache.uima.tm.dltk.internal.core.codeassist.TextMarkerSelectionParser;
import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerModuleDeclaration;
import org.apache.uima.tm.dltk.parser.ast.expressions.TextMarkerVariableReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.text.ScriptWordFinder;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.ui.texteditor.ITextEditor;


public class ExternalTypeHyperlinkDetector implements IHyperlinkDetector {
  private ITextEditor fTextEditor;

  public ExternalTypeHyperlinkDetector(ITextEditor editor) {
    Assert.isNotNull(editor);
    fTextEditor = editor;
  }

  @Override
  public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region,
          boolean canShowMultipleHyperlinks) {
    if (region == null || !(fTextEditor instanceof ScriptEditor))
      return null;

    int offset = region.getOffset();

    IModelElement input = EditorUtility.getEditorInputModelElement(fTextEditor, false);
    if (input == null)
      return null;

    try {
      IDocument document = fTextEditor.getDocumentProvider().getDocument(
              fTextEditor.getEditorInput());
      IRegion wordRegion = ScriptWordFinder.findWord(document, offset);
      if (wordRegion == null)
        return null;
      // System.out.println(wordRegion);
      if (input instanceof ISourceModule) {
        ISourceModule sm = (ISourceModule) input;
        IModelElement modelElement = sm.getModelElement();
        TextMarkerSelectionParser parser = new TextMarkerSelectionParser();
        ModuleDeclaration moduleDeclaration = parser.parse(sm);
        String word = document.get(wordRegion.getOffset(), wordRegion.getLength());
        TextMarkerReferenceVisitor referenceVisitor = new TextMarkerReferenceVisitor(wordRegion
                .getOffset());
        moduleDeclaration.traverse(referenceVisitor);
        ASTNode node = referenceVisitor.getResult();
        if (node instanceof TextMarkerVariableReference
                && moduleDeclaration instanceof TextMarkerModuleDeclaration) {
          TextMarkerVariableReference vr = (TextMarkerVariableReference) node;
          TextMarkerModuleDeclaration parsed = (TextMarkerModuleDeclaration) moduleDeclaration;
          if (vr.getType() == TMTypeConstants.TM_TYPE_AT) {
            String nodeText = vr.getStringRepresentation();
            Collection<String> importedTypeSystems = parsed.descriptorInfo.getImportedTypeSystems();
            List<IHyperlink> result = new ArrayList<IHyperlink>();
            for (String tsString : importedTypeSystems) {
              IFolder folder = modelElement.getScriptProject().getProject().getFolder(
                      TextMarkerProjectUtils.getDefaultDescriptorLocation());
              String xmlFilePath = tsString.replaceAll("\\.", "/");
              xmlFilePath = xmlFilePath.substring(0, xmlFilePath.length()) + ".xml";
              Set<String> types = getTypes(folder, xmlFilePath);
              if (types.contains(nodeText)) {
                IFile iFile = getFile(folder, xmlFilePath);
                IHyperlink link = new ExternalTypeHyperlink(nodeText, wordRegion, iFile, tsString,
                        fTextEditor);
                result.add(link);
              }
            }
            if (!result.isEmpty()) {
              return result.toArray(new IHyperlink[] {});
            } else {
              return null;
            }
          }
        }
      }
    } catch (Exception e) {
      return null;
    }
    return null;
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
}
