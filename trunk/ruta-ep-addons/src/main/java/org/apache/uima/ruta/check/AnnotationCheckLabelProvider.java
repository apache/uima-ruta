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

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class AnnotationCheckLabelProvider extends LabelProvider implements ILabelProvider {

  private AnnotationCheckComposite composite;

  public AnnotationCheckLabelProvider(AnnotationCheckComposite composite) {
    this.composite = composite;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element) {
    if (element instanceof IAnnotationCheckTreeNode) {
      IAnnotationCheckTreeNode node = (IAnnotationCheckTreeNode) element;
      CheckElement e = node.getElement();
      if (!e.checked) {
        return composite.getImage("help");
      } else {
        if (e.keep) {
          return composite.getImage("accept");
        } else {
          return composite.getImage("delete");
        }
      }
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element) {
    if (element instanceof AnnotationCheckTreeNode) {
      AnnotationCheckTreeNode node = (AnnotationCheckTreeNode) element;
      CheckElement ce = node.getElement();
      if (ce instanceof CheckAnnotation) {
        CheckAnnotation ca = (CheckAnnotation) ce;
        String normalizeSpace = StringUtils.normalizeSpace(ca.coveredText);
        return "[" + ca.shortType + "]: " + normalizeSpace;
      } else if (ce instanceof CheckDocument) {
        CheckDocument cd = (CheckDocument) ce;
        String name = new File(cd.source).getName();
        return name;
      }
    }
    return element.toString();
  }
}
