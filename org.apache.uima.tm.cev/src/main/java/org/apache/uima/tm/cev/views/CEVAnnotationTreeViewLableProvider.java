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

package org.apache.uima.tm.cev.views;

import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationOrderedTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVFeatureTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVTreeNode;
import org.apache.uima.tm.cev.preferences.CEVPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.util.Translate;

public class CEVAnnotationTreeViewLableProvider extends LabelProvider implements ILabelProvider {

  private CEVData casData;

  private boolean text_repr;

  public CEVAnnotationTreeViewLableProvider(CEVData casData) {
    this.casData = casData;
    setTextRepr();
  }

  public void setTextRepr() {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    String repr = store.getString(CEVPreferenceConstants.P_ANNOTATION_REPR);

    if (repr.equals(CEVPreferenceConstants.P_ANNOTATION_REPR_TEXT))
      text_repr = true;
    else if (repr.equals(CEVPreferenceConstants.P_ANNOTATION_REPR_HTML))
      text_repr = false;
    else
      text_repr = true;
  }

  public void setCASData(CEVData casData) {
    this.casData = casData;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element) {
    if (element instanceof ICEVTreeNode && !(element instanceof CEVFeatureTreeNode))
      return casData.getIcon(((ICEVTreeNode) element).getType());
    /*
     * if (element instanceof CEVFeatureTreeNode) { if (((CEVFeatureTreeNode) element).hasChildren()
     * == true ) { return casData.getIcon() } }
     */
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

    if (element instanceof ICEVTreeNode) {
      if (element instanceof CEVAnnotationOrderedTreeNode) {
        text = ((CEVAnnotationOrderedTreeNode) element).getType().getShortName() + ": ";
      }

      String name = ((ICEVTreeNode) element).getName();

      if (name != null && text_repr) {
        name = ParserUtils.trimSpacesBeginEnd(
                ParserUtils.trimAllTags(Translate.decode(name), false), "");
        name = name.replaceAll("[\\n]", "").replaceAll("[\\r]", "");
      }
      if (element instanceof CEVTypeTreeNode) {
        text += TextUtils.shrinkNamespace(name);
      } else if (name != null) {
        text += name;
      }
      if (element instanceof CEVAnnotationTreeNode) {
        ICEVTreeNode parent = ((CEVAnnotationTreeNode) element).getParent();
        if (parent instanceof CEVTypeTreeNode) {
          text = name;
        } else if (parent instanceof CEVAnnotationTreeNode) {
          text = ((CEVAnnotationTreeNode) element).getType().getShortName() + ": " + name;
        }
      } else if (element instanceof CEVTypeTreeNode) {
        text += " [" + ((CEVTypeTreeNode) element).getChildren().length + "]";
      } else {
      }
    }

    return text;
  }
}
