package org.apache.uima.tm.dltk.core;

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.AbstractLanguageToolkit;

public class TextMarkerLanguageToolkit extends AbstractLanguageToolkit {
  private static final String[] languageExtensions = new String[] { "tm" };

  private static TextMarkerLanguageToolkit sInstance = new TextMarkerLanguageToolkit();

  public TextMarkerLanguageToolkit() {
  }

  @Override
  public boolean languageSupportZIPBuildpath() {
    return true;
  }

  public boolean validateSourcePackage(IPath path) {
    File file = new File(path.toOSString());
    if (file != null) {
      String members[] = file.list(new FilenameFilter() {

        public boolean accept(File dir, String name) {
          if (name.toLowerCase().equals("__init__.tm")) {
            return true;
          }
          return false;
        }
      });
      if (members.length > 0) {
        return true;
      }
    }
    return false;
  }

  public String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  public String[] getLanguageFileExtensions() {
    return languageExtensions;
  }

  public String getLanguageName() {
    return "TextMarker";
  }

  public String getLanguageContentType() {
    return "de.uniwue.dltk.tmContentType";
  }

  public static TextMarkerLanguageToolkit getDefault() {
    return sInstance;
  }

  @Override
  public String getPreferenceQualifier() {
    return TextMarkerPlugin.PLUGIN_ID;
  }

}
