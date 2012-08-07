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

package org.apache.uima.textmarker.textruler.core;

import java.io.File;
import java.util.ArrayList;

import org.apache.uima.textmarker.textruler.TextRulerPlugin;
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
      TextRulerPlugin.error(e);
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
