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

/**
 * 
 * This class introduces the special additional information of an example for learning
 * LP2-Correction Rules. It needs besides the original, correct slot boundary annotation the wrong
 * annotation, or the other way round, it needs the correct annotation where to shift the boundary
 * tag.
 * 
 * Since TextRulerExample provides a possibility to store more than one Annotation for
 * MultiSlot-Exmaples, we easily can use this storage for those two annotations. But for the sake of
 * code reading and better understanding, this additional class with named accessors is created.
 * 
 */
public class TextRulerShiftExample extends TextRulerExample {

  public TextRulerShiftExample(TextRulerExampleDocument document,
          TextRulerAnnotation wrongAnnotation, TextRulerAnnotation correctAnnotation,
          boolean isPositive, TextRulerTarget target) {
    super(document, (TextRulerAnnotation[]) null, isPositive, target);
    annotations = new TextRulerAnnotation[2];
    annotations[0] = wrongAnnotation;
    annotations[1] = correctAnnotation;
  }

  public TextRulerAnnotation wrongAnnotation() {
    return annotations[0];
  }

  public TextRulerAnnotation correctAnnotation() {
    return annotations[1];
  }

  @Override
  public String toString() {
    String wrongStr = "" + wrongAnnotation().getBegin();
    String correctStr = "" + correctAnnotation().getBegin();
    return wrongStr + " --> " + correctStr;
  }

}
