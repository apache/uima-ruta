package org.apache.uima.tm.dltk.internal.ui.documentation;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;

public class ScriptDocumentationProvider {

  public ScriptDocumentationProvider() {
    super();
  }

  protected String getLine(Document d, int line) throws BadLocationException {
    return d.get(d.getLineOffset(line), d.getLineLength(line));
  }

  protected String convertToHTML(String header) {
    StringBuffer result = new StringBuffer();
    // result.append("<p>\n");
    Document d = new Document(header);
    for (int line = 0;; line++) {
      try {
        String str = getLine(d, line).trim();
        if (str == null)
          break;
        while (str.length() > 0 && str.startsWith("#"))
          str = str.substring(1);
        while (str.length() > 0 && str.endsWith("#"))
          str = str.substring(0, str.length() - 1);
        if (str.length() == 0)
          result.append("<p>");
        else {
          if (str.trim().matches("\\w*:")) {
            result.append("<h4>");
            result.append(str);
            result.append("</h4>");
          } else
            result.append(str + "<br>");
        }
      } catch (BadLocationException e) {
        break;
      }

    }
    // result.append("</p>\n");
    return result.toString();
  }

}