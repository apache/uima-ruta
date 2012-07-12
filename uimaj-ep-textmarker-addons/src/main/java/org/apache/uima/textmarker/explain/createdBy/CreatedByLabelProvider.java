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

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.visitor.CreatedByVisitor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class CreatedByLabelProvider extends LabelProvider implements ILabelProvider {

  private CreatedByViewPage owner;

  public CreatedByLabelProvider(CreatedByViewPage owner) {
    super();
    this.owner = owner;
  }

  @Override
  public Image getImage(Object element) {
    return owner.getImage(CreatedByViewPage.TM_RULE);
  }

  @Override
  public String getText(Object element) {
    if (element instanceof FeatureStructure) {
      FeatureStructure fs = (FeatureStructure) element;
      CAS cas = fs.getCAS();
      Type t = cas.getTypeSystem().getType(CreatedByVisitor.TYPE);
      Feature featureRule = t.getFeatureByBaseName(CreatedByVisitor.FEATURE_RULE);
      Feature featureScript = t.getFeatureByBaseName(CreatedByVisitor.FEATURE_SCRIPT);
      String rule = fs.getStringValue(featureRule);
      String script = fs.getStringValue(featureScript);
      String result = rule ;
      if(!"".equals(script)){
        result += " (in " + script + ")";
      }
      return result;
    }
    return element.toString();
  }
}
