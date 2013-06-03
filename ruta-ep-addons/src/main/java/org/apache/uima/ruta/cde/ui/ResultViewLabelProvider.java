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

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class ResultViewLabelProvider implements ITableLabelProvider {

  public void addListener(ILabelProviderListener arg0) {
  }

  public void dispose() {
  }

  public boolean isLabelProperty(Object arg0, String arg1) {
    return false;
  }

  public void removeListener(ILabelProviderListener arg0) {

  }

  public Image getColumnImage(Object arg0, int arg1) {
    return null;
  }

  public String getColumnText(Object input, int column) {
    if(input instanceof String[]) {
      String[] results = (String[])input;
      switch(column) {
        case 0 :
          return results[0];
        case 1 : 
          return results[1];
      }
    }
    return null;
  }

}
