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

import org.apache.uima.cas.CAS;
import org.apache.uima.textmarker.textruler.core.TextRulerTarget.MLTargetType;


/**
 * 
 * TextRulerExample encapsulates a single-slot, multi-slot or single-slot-boundary problem instance.
 * This can be positive or negative examples from a example document, or it can be coverings of a
 * rule or multiple rules that were applied to a document...
 * 
 *         hint: this could be renamed to MLInstance ?
 * 
 */
public class TextRulerExample {

  protected TextRulerExampleDocument document;

  protected TextRulerAnnotation annotations[]; // single-slot has only one

  // annotation...
  protected boolean isPositive;

  protected TextRulerTarget target;

  public TextRulerExample(TextRulerExampleDocument document, TextRulerAnnotation annotation,
          boolean isPositive, TextRulerTarget target) {
    TextRulerAnnotation singleAnnot[] = { annotation };
    this.document = document;
    this.isPositive = isPositive;
    this.target = target;
    this.annotations = singleAnnot;
  }

  public TextRulerExample(TextRulerExampleDocument document, TextRulerAnnotation annotations[],
          boolean isPositive, TextRulerTarget target) {
    this.document = document;
    this.isPositive = isPositive;
    this.target = target;
    this.annotations = annotations;
  }

  public TextRulerExampleDocument getDocument() {
    return document;
  }

  public CAS getDocumentCAS() {
    return document.getCAS();
  }

  public TextRulerAnnotation getAnnotation() {
    return annotations[0];
  }

  public TextRulerAnnotation[] getAnnotations() {
    return annotations;
  }

  public boolean isPositive() {
    return isPositive;
  }

  public void setPositive(boolean flag) {
    isPositive = flag;
  }

  public TextRulerTarget getTarget() {
    return target;
  }

  @Override
  public String toString() {
    if (target.type != MLTargetType.MULTI_SLOT) {
      if (annotations != null) {
        if (target.type == MLTargetType.SINGLE_WHOLE_SLOT)
          return getAnnotation().getCoveredText();
        else
          return "START at " + getAnnotation().getBegin(); // +","+getAnnotation().getEnd();
      } else
        return "<no text>";
    } else {
      String str = "";
      for (TextRulerAnnotation a : annotations) {
        if (a == null)
          str += "<NULL>";
        else
          str += a.getType().getShortName() + ":" + a.getCoveredText() + ";";
      }
      return str;
    }
  }

  @Override
  public boolean equals(Object ob) {
    TextRulerExample o = (TextRulerExample) ob;

    boolean result = document.getCasFileName().equals(o.document.getCasFileName())
            && (isPositive == o.isPositive) && target.equals(o.target);

    if (!result)
      return false;

    if (annotations.length != o.annotations.length)
      return false;

    for (int i = 0; i < annotations.length; i++) {
      if (!annotations[i].equals(o.annotations[i]))
        return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = document.getCasFileName().hashCode()
            * (isPositive ? 2 : 1)
            * (target.type == MLTargetType.MULTI_SLOT ? 1
                    : (target.type == MLTargetType.SINGLE_WHOLE_SLOT ? 2
                            : (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY ? 3 : 4)));
    int i = 1;
    for (TextRulerAnnotation a : annotations) {
      result *= i * (a.getBegin() + 1) * (a.getEnd() + 1);
      i++;
    }

    return result;
  }
}
