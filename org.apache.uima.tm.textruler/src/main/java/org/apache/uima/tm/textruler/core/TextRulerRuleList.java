package org.apache.uima.tm.textruler.core;

import java.io.File;
import java.util.ArrayList;

import org.apache.uima.util.FileUtils;

/**
 * 
 * TextRulerRuleList can hold a list of rules and provides some extra functionality like saving them
 * to a TextMarker rule file...
 * 
 */
public class TextRulerRuleList extends ArrayList<TextRulerRule> {

  private static final long serialVersionUID = 1L;

  public void saveToRulesFile(String filename, String fileHeader) {
    File file = new File(filename);
    try {
      FileUtils.saveString2File(getTMFileString(fileHeader), file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean addRule(TextRulerRule rule) {
    if (!this.contains(rule)) {
      this.add(rule);
      return true;
    }
    return false;
  }

  public String getRulesString(String linePrefix) {
    return getRulesString(linePrefix, Integer.MAX_VALUE);
  }

  public String getRulesString(String linePrefix, int maxRuleStringLength) {
    StringBuffer str = new StringBuffer();
    for (TextRulerRule rule : this) {
      String theRuleString = rule.getRuleString();
      String rStr = theRuleString.length() > maxRuleStringLength ? "<too long to display>"
              : theRuleString;
      str.append(linePrefix + rStr + "\t// " + rule.getCoveringStatistics() + "\n");
    }
    return str.toString();
  }

  public String getTMFileString(String header) {
    return header + getRulesString("", Integer.MAX_VALUE);
  }

  public String getTMFileString(String header, int maxRuleStringLength) {
    return header + getRulesString("", maxRuleStringLength);
  }

}
