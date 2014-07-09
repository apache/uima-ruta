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

package org.apache.uima.ruta.check;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class FeatureCheckTreeNodeComparator extends ViewerComparator {

  private AnnotationCheckTreeNodeComparator annotCheckTreeNodeComp;

  public FeatureCheckTreeNodeComparator() {
    super();
    this.annotCheckTreeNodeComp = new AnnotationCheckTreeNodeComparator();
  }

  @Override
  public int compare(Viewer v, Object o1, Object o2) {
    int category1 = category(o1);
    int category2 = category(o2);
    if (category1 == 1 && category1 == category2) {
      FeatureCheckTreeNode featTreeNode1 = (FeatureCheckTreeNode) o1;
      FeatureCheckTreeNode featTreeNode2 = (FeatureCheckTreeNode) o2;
      return featTreeNode1.getFeature().getShortName()
              .compareToIgnoreCase(featTreeNode2.getFeature().getShortName());
    }
    if (category1 == 2 && category1 == category2) {
      AnnotationCheckTreeNode annotCheckTreeNode1 = (AnnotationCheckTreeNode) o1;
      AnnotationCheckTreeNode annotCheckTreeNode2 = (AnnotationCheckTreeNode) o2;
      return annotCheckTreeNodeComp.compare(annotCheckTreeNode1, annotCheckTreeNode2);
    }
    return o1.toString().compareToIgnoreCase(o2.toString());
  }

  @Override
  public int category(Object element) {
    if (element == null) {
      return -1;
    }
    if (element instanceof FeatureCheckTreeNode) {
      return 1;
    }
    if (element instanceof AnnotationCheckTreeNode) {
      return 2;
    }
    return -1;
  }

}
