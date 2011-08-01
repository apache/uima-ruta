package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.NoSuchElementException;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public class DummyFSIterator implements FSIterator<AnnotationFS> {

  boolean valid = true;

  private TextMarkerBasic element;

  public DummyFSIterator(TextMarkerBasic first) {
    element = first;
  }

  @Override
  public FSIterator<AnnotationFS> copy() {
    return new DummyFSIterator(element);
  }

  @Override
  public AnnotationFS get() throws NoSuchElementException {
    return element;
  }

  @Override
  public boolean isValid() {
    return valid;
  }

  @Override
  public void moveTo(FeatureStructure arg0) {
  }

  @Override
  public void moveToFirst() {
    valid = true;
  }

  @Override
  public void moveToLast() {
    valid = true;
  }

  @Override
  public void moveToNext() {
    valid = false;
  }

  @Override
  public void moveToPrevious() {
    valid = false;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public AnnotationFS next() {
    return null;
  }

  @Override
  public void remove() {

  }

}
