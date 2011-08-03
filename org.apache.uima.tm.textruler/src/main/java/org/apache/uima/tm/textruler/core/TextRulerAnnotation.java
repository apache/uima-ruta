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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

/**
 * 
 * This class encapsulates the for us important information about an Annotation, since AnnotationFS
 * keeps its corresponding CAS alive or gets killed when the CAS gets reset.
 * 
 */
public class TextRulerAnnotation {

  private TextRulerExampleDocument document; // we also keep a reference to

  // our document, but this does
  // not hold the CAS forever, it
  // loads it on demand!
  private int begin;

  private int end;

  private Type type;

  private String coveredText;

  public TextRulerAnnotation(AnnotationFS afs, TextRulerExampleDocument document) {
    this.document = document;
    this.begin = afs.getBegin();
    this.end = afs.getEnd();
    this.type = afs.getType();
    this.coveredText = afs.getCoveredText();
  }

  public TextRulerAnnotation(AnnotationFS afs) {
    this.document = null;
    this.begin = afs.getBegin();
    this.end = afs.getEnd();
    this.type = afs.getType();
    this.coveredText = afs.getCoveredText();
  }

  public TextRulerExampleDocument getDocument() {
    return document;
  }

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  public Type getType() {
    return type;
  }

  public String getCoveredText() {
    return coveredText;
  }

  @Override
  public String toString() {
    return getCoveredText();
  }

  @Override
  public boolean equals(Object o) {
    TextRulerAnnotation a = (TextRulerAnnotation) o;
    return (document == a.document) && (begin == a.begin) && (end == a.end)
            && coveredText.equals(a.coveredText);
  }

  @Override
  public int hashCode() {
    return begin * 17 * end + coveredText.hashCode();
  }

}
