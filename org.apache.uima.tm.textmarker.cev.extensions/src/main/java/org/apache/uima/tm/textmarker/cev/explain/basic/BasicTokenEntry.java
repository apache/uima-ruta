package org.apache.uima.tm.textmarker.cev.explain.basic;

import org.apache.uima.cas.text.AnnotationFS;

public class BasicTokenEntry {

  private int number;

  private AnnotationFS annotation;

  public BasicTokenEntry(AnnotationFS annotation, int number) {
    this.annotation = annotation;
    this.number = number;
  }

  public int getNumber() {
    return this.number;
  }

  public String getTyp() {
    return annotation.getType().getShortName();
  }

  public String getText() {
    return annotation.getCoveredText();
  }

  public AnnotationFS getAnnotation() {
    return annotation;
  }

}