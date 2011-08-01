package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.Reader;

public class HtmlTextMarkerPagesLocation implements ITextMarkerPagesLocation {

  private File fLocation;

  /**
   * @param location
   *          directory(!)
   */
  public HtmlTextMarkerPagesLocation(File location) {
    if (!location.isDirectory()) {
      throw new IllegalArgumentException();
    }

    fLocation = location;
  }

  public Reader getHtmlInfo(String keyword) {
    final String pattern = keyword + ".htm";
    File[] result = fLocation.listFiles(new FilenameFilter() {

      public boolean accept(File dir, String name) {
        if (name.equals(pattern))
          return true;
        return false;
      }

    });
    if (result != null && result.length >= 1 && result[0] != null) {
      try {
        FileReader reader = new FileReader(result[0]);
        return reader;
      } catch (FileNotFoundException e) {
        // hmmm! but nothing to do.
      }
    }
    return null;
  }

  public File getLocation() {
    return fLocation;
  }

  public void setLocation(File location) {
    if (!location.isDirectory())
      return;
    fLocation = location;
  }

}
