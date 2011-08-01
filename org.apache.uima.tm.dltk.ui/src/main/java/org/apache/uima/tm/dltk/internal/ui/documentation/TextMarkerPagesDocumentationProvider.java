package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;


public class TextMarkerPagesDocumentationProvider implements IScriptDocumentationProvider {

  private List folders = null;

  public Reader getInfo(IMember element, boolean lookIntoParents, boolean lookIntoExternal) {
    return null;
  }

  public Reader getInfo(String content) {
    initalizeLocations(false);

    if (folders != null) {
      for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
        TextMarkerPageFolder f = (TextMarkerPageFolder) iterator.next();
        HashMap pages = f.getPages();
        String ans = (String) pages.get(content);
        if (ans != null) {
          IPath filePath = new Path(f.getPath()).append(ans);
          File file = filePath.toFile();
          if (file != null && file.isFile()) {
            try {
              return new FileReader(file);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
          break;
        }
      }
    }

    return null;
  }

  private void initalizeLocations(boolean force) {
    if (!force && this.folders != null)
      return;

    TextMarkerUI.getDefault().getPreferenceStore().addPropertyChangeListener(
            new IPropertyChangeListener() {

              public void propertyChange(PropertyChangeEvent event) {
                initalizeLocations(true);
              }

            });

    String value = TextMarkerUI.getDefault().getPreferenceStore().getString(
            TextMarkerPreferenceConstants.DOC_TM_PAGES_LOCATIONS);

    try {
      if (value != null && !value.equals("")) {
        // value = "<Pages></Pages>";
        this.folders = TextMarkerPageFolder.readXML(value);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
