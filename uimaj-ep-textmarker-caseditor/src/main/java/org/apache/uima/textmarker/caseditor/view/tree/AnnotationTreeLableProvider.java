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

package org.apache.uima.textmarker.caseditor.view.tree;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class AnnotationTreeLableProvider extends LabelProvider implements ILabelProvider {

  private AnnotationTreeViewPage page;

  public AnnotationTreeLableProvider(AnnotationTreeViewPage page) {
    this.page = page;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element) {
    if (element instanceof TypeTreeNode) {
      return page.getIcon(((TypeTreeNode) element).getType());
    } else if (element instanceof FSTreeNode) {
      return page.getIcon(((FSTreeNode) element).getType());
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

    String text = "";

    if (element instanceof ITreeNode) {

      String name = ((ITreeNode) element).getName();

      if (name != null) {
        // TODO reactive html stuff
        // name = ParserUtils.trimSpacesBeginEnd(
        // ParserUtils.trimAllTags(Translate.decode(name), false), "");
        name = name.replaceAll("[\\n]", "").replaceAll("[\\r]", "");
      }
      if (element instanceof TypeTreeNode) {
        text += TextUtils.shrinkNamespace(name);
      } else if (name != null) {
        text += name;
      }
      if (element instanceof AnnotationTreeNode) {
        ITreeNode parent = ((AnnotationTreeNode) element).getParent();
        if (parent instanceof TypeTreeNode) {
          text = name;
        } else if (parent instanceof AnnotationTreeNode) {
          text = ((AnnotationTreeNode) element).getType().getShortName() + ": " + name;
        }
      } else if (element instanceof TypeTreeNode) {
        text += " [" + ((TypeTreeNode) element).getChildren().length + "]";
      } else {
      }
    }

    return text;
  }
}
