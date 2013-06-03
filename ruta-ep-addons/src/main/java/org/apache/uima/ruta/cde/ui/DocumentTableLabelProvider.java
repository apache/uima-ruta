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

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.cde.utils.DocumentData;
import org.apache.uima.ruta.cde.utils.EvaluationMeasures;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class DocumentTableLabelProvider implements ITableLabelProvider {
  
  private final Image green = ConstraintSelectComposite.createImage("/icons/bullet_green.png"); 

  private final Image yellow = ConstraintSelectComposite.createImage("/icons/bullet_yellow.png"); 

  private final Image red = ConstraintSelectComposite.createImage("/icons/bullet_red.png"); 


  public void addListener(ILabelProviderListener arg0) {
  }

  public void dispose() {
  }

  public boolean isLabelProperty(Object arg0, String arg1) {
    return false;
  }

  public void removeListener(ILabelProviderListener arg0) {
  }

  public Image getColumnImage(Object element, int columnIndex) {
    if (element instanceof DocumentData && columnIndex == 0) {
      DocumentData data = (DocumentData) element;
      Image image = red;
      
      if (data.getAugmentedResult() * 100 > RutaAddonsPlugin.getDefault().getPreferenceStore().
              getInt(CDEPreferenceConstants.AVERAGE_RESULT_THRESHOLD)) {
        image = yellow;
      }
      if (data.getAugmentedResult() * 100 > RutaAddonsPlugin.getDefault().getPreferenceStore().
              getInt(CDEPreferenceConstants.GOOD_RESULT_THRESHOLD)) {
        image = green;
      }
      return image;
    }
    return null;
  }

  public String getColumnText(Object element, int columnIndex) {
    if (element instanceof DocumentData) {
      DocumentData data = (DocumentData) element;
      switch (columnIndex) {
        case 0:
          return null;
        case 1:
          return data.getDocument().getName();
        case 2:
          return String.valueOf(EvaluationMeasures.round(data.getAugmentedResult()));
        case 3:
          return String.valueOf(EvaluationMeasures.round(data.getFMeasure()));
      }
    }
    return "failure";
  }

}
