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
package org.apache.uima.ruta.rule;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.FSIteratorImplBase;
import org.apache.uima.cas.impl.LowLevelIndex;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.TOP;

public class AnnotationListFSIterator extends FSIteratorImplBase<AnnotationFS> {

  private final List<AnnotationFS> list;

  private int pointer = 0;

  public AnnotationListFSIterator(List<AnnotationFS> list) {
    super();
    this.list = list;
  }

  @Override
  public boolean isValid() {

    return pointer >= 0 && pointer < list.size();
  }

  @Override
  public AnnotationFS get() throws NoSuchElementException {
    return list.get(pointer);
  }

  @Override
  public void moveToNext() {
    pointer++;

  }

  @Override
  public void moveToPrevious() {
    pointer--;

  }

  @Override
  public void moveToFirst() {
    pointer = 0;

  }

  @Override
  public void moveToLast() {
    pointer = list.size()-1;

  }

  @Override
  public void moveTo(FeatureStructure fs) {
    for (int i = 0; i < list.size(); i++) {
      AnnotationFS each = list.get(i);
      if(each.equals(fs)) {
        pointer = i;
        break;
      }
    }
  }

  @Override
  public FSIterator<AnnotationFS> copy() {
    
    return new AnnotationListFSIterator(list);
  }

  @Override
  public int ll_indexSizeMaybeNotCurrent() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public LowLevelIndex<AnnotationFS> ll_getIndex() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public int ll_maxAnnotSpan() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public boolean isIndexesHaveBeenUpdated() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public boolean maybeReinitIterator() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void moveToFirstNoReinit() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void moveToLastNoReinit() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void moveToNoReinit(FeatureStructure fs) {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public Comparator<TOP> getComparator() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public AnnotationFS getNvc() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void moveToNextNvc() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void moveToPreviousNvc() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public boolean hasPrevious() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public AnnotationFS previous() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public int nextIndex() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public int previousIndex() {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void set(AnnotationFS e) {
    throw new NotImplementedException("Not supported.");
  }

  @Override
  public void add(AnnotationFS e) {
    throw new NotImplementedException("Not supported.");
  }

}
