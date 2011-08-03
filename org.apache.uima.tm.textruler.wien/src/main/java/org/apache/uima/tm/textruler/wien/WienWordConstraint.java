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

package org.apache.uima.tm.textruler.wien;

import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerWordConstraint;

public class WienWordConstraint extends TextRulerWordConstraint {

  protected boolean generalizeLinkMarkUp;

  public WienWordConstraint(TextRulerAnnotation tokenAnnotation) {
    super(tokenAnnotation);
    generalizeLinkMarkUp = false;
  }

  public WienWordConstraint(WienWordConstraint copyFrom) {
    super(copyFrom);
    generalizeLinkMarkUp = copyFrom.generalizeLinkMarkUp;
  }

  @Override
  public WienWordConstraint copy() {
    return new WienWordConstraint(this);
  }

  public void setGeneralizeLinkMarkUp(boolean b) {
    generalizeLinkMarkUp = b;
  }

  @Override
  public String toString() {
    if (isRegExpConstraint()) {
      String theText = tokenAnnotation.getCoveredText();
      if (generalizeLinkMarkUp && typeShortName().equals("MARKUP")
              && (theText.startsWith("<a") || theText.startsWith("<A"))) {
        // special case for A HREF stuff, since hrefs are often
        // different but the struct is the same!
        // this is quick hack that should be configurable, but for now
        // it works!
        return theText.substring(0, 2) + ".*>";
      } else
        return super.toString();
    } else
      return super.toString();
  }

}
