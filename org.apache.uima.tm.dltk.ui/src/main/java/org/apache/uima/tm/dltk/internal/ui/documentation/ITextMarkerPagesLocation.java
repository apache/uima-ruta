package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.Reader;

public interface ITextMarkerPagesLocation {

  /**
   * Should find inside location for an information about keyword
   * 
   * @param keyword
   * @return Reader with html code
   */
  public Reader getHtmlInfo(String keyword);

}
