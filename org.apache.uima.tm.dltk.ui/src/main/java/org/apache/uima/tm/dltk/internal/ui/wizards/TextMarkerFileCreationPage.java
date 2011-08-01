package org.apache.uima.tm.dltk.internal.ui.wizards;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;


public class TextMarkerFileCreationPage extends NewSourceModulePage {

  @Override
  protected String getPageDescription() {
    return "This wizard creates a new TextMarker script file.";
  }

  @Override
  protected String getFileContent() {
    StringBuilder sb = new StringBuilder();
    sb.append("PACKAGE ");
    IScriptFolder scriptFolder = getScriptFolder();
    IModelElement ancestor = scriptFolder.getAncestor(IModelElement.PROJECT_FRAGMENT);
    IPath path = ancestor.getPath().removeFirstSegments(1);
    String pathString = "";
    for (int i = 1; i < path.segments().length; i++) {
      pathString += path.segments()[i];
      if (i < path.segments().length - 1) {
        pathString += ".";
      }
    }
    sb.append(pathString);
    sb.append(";\n");
    return sb.toString();
  }

  @Override
  protected String getRequiredNature() {
    return TextMarkerNature.NATURE_ID;
  }

  @Override
  protected String getPageTitle() {
    return "Create a new TextMarker script file";
  }
}
