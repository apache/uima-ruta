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

package org.apache.uima.textmarker.query.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class QueryResultLabelProvider implements ILabelProvider {
  public Image getImage(Object element) {
    return null;
  }

  public String getText(Object element) {
    if (element instanceof QueryResult) {
      QueryResult qr = (QueryResult) element;
      String text = qr.getText();
      // if (text.length() > 100) {
      // text = text.substring(0, 100) + "...";
      // }
      text += " (in " + qr.getFile().getName() + ")";
      return text;
    }
    return "error";
  }

  public void addListener(ILabelProviderListener listener) {

  }

  public void dispose() {
  }

  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  public void removeListener(ILabelProviderListener listener) {

  }
}
