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
