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

package org.apache.uima.tm.textruler.core;

import java.util.ArrayList;

/**
 * 
 * TextRulerRulePattern is an ordered list of rule items and provides some special functionality for
 * dealing with rule patterns like finding sub patterns or such.
 * 
 *         hint: this is a very basic implementation and could surely be optimized ;-)
 */
public class TextRulerRulePattern extends ArrayList<TextRulerRuleItem> {

  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    String result = "";
    for (TextRulerRuleItem i : this)
      result += " " + i;
    return result.trim();
  }

  public int find(TextRulerRulePattern subPattern) {
    if (subPattern.size() == 0)
      return -1;
    if (size() < subPattern.size())
      return -1;
    int maxIndex = size() - subPattern.size();
    for (int i = 0; i <= maxIndex; i++)
      if (get(i).toString().equals(subPattern.get(0).toString())) {
        // test the rest of the items:
        boolean isEqual = true;
        for (int i2 = 1; i2 < subPattern.size(); i2++) {
          if (!get(i + i2).toString().equals(subPattern.get(i2).toString())) {
            isEqual = false;
            break;
          }
        }
        if (isEqual)
          return i;

      }
    return -1;
  }

  public TextRulerRulePattern subPattern(int start, int length) {
    TextRulerRulePattern result = new TextRulerRulePattern();
    if (length < 0)
      length = size();
    for (int i = 0; i < length; i++) {
      int theIndex = start + i;
      if (theIndex >= size())
        break;
      result.add(get(theIndex));
    }
    return result;
  }

  public TextRulerRulePattern copy() {
    TextRulerRulePattern result = new TextRulerRulePattern();
    for (TextRulerRuleItem i : this)
      result.add(i.copy());
    return result;
  }

  public TextRulerRuleItem lastItem() {
    if (size() > 0)
      return get(size() - 1);
    else
      return null;
  }

  public TextRulerRuleItem firstItem() {
    if (size() > 0)
      return get(0);
    else
      return null;
  }
}
