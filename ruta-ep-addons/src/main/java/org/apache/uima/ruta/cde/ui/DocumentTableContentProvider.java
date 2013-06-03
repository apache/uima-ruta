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

package org.apache.uima.ruta.cde.ui;

import java.io.File;
import java.util.ArrayList;

import org.apache.uima.ruta.cde.utils.DocumentData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DocumentTableContentProvider implements IStructuredContentProvider {

  public void dispose() {
  }

  public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
  }

  public Object[] getElements(Object inputElement) {
    Object[] o = new Object[0];
    if (inputElement instanceof ArrayList) {
      ArrayList<DocumentData> documentList = (ArrayList<DocumentData>) inputElement;
      return documentList.toArray();
    }
    /*
     * TODO Not sure if the following code will be needed in future. Keep it for now, delete once it's clear if 
     * DocumentData will be used
     */
    if (inputElement instanceof File[]) {
      return (Object[]) inputElement;
    }
    
    return o;
  }
}
