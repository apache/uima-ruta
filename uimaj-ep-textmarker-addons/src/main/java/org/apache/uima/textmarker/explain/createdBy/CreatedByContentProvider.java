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

package org.apache.uima.textmarker.explain.createdBy;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CreatedByContentProvider implements ITreeContentProvider {

  public void dispose() {

  }

  public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

  }

  public Object[] getChildren(Object arg0) {
    return new Object[0];
  }

  public Object[] getElements(Object arg0) {
    return new Object[]{arg0};
  }

  public Object getParent(Object arg0) {
    return null;
  }

  public boolean hasChildren(Object arg0) {
    return false;
  }

}
