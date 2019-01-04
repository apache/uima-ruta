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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

public class SelectTypesDialogCheck extends TitleAreaDialog implements Listener {

  private boolean featureMode;

  private Text typeNameUI;

  public Tree tree;

  public String typeName = "error-TypeName-never-set";

  private List<String> allTypes;

  private Map<String, List<String>> allTypesMapping;

  private Map<String, Set<String>> selectedTypes;

  private int swtTreeModeFlag;

  private boolean check;

  private String choosenType;

  public SelectTypesDialogCheck(Shell shell, TypeSystemDescription tsd,
          Map<String, Set<String>> selectedTypes, boolean featureMode, int swtTreeModeFlag,
          boolean check) {
    super(shell);
    this.check = check;
    this.swtTreeModeFlag = swtTreeModeFlag;
    allTypes = new LinkedList<String>();
    allTypesMapping = new HashMap<String, List<String>>();
    TypeDescription[] systemTypes = tsd.getTypes();
    for (TypeDescription typeDescription : systemTypes) {
      List<String> typeFeatures = new LinkedList<String>();
      for (FeatureDescription fd : typeDescription.getFeatures()) {
        typeFeatures.add(fd.getName());
      }
      String supertypeName = typeDescription.getSupertypeName();
      if (supertypeName != null) {
        TypeDescription superTypeDesc = tsd.getType(supertypeName);
        if (superTypeDesc != null) {
          for (FeatureDescription fd : superTypeDesc.getFeatures()) {
            typeFeatures.add(fd.getName());
          }
        }
      }
      String name = typeDescription.getName();
      allTypes.add(name);
      allTypesMapping.put(name, typeFeatures);
    }
    if (selectedTypes != null) {
      this.selectedTypes = selectedTypes;
    } else {
      this.selectedTypes = new HashMap<String, Set<String>>();
    }
    this.featureMode = featureMode;
  }

  @Override
  protected Control createContents(Composite parent) {
    Control contents = super.createContents(parent);
    if (!check) {
      setMessage("Please select the new type:");
      setTitle("Type selection dialog!");
    } else if (!featureMode) {
      setMessage("Select the types that should be copied unchecked:");
      setTitle("Type selection dialog!");
    } else {
      setMessage("Select the types and features that you want to check:");
      setTitle("Type and feature selection dialog");
    }
    return contents;
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite composite = (Composite) super.createDialogArea(parent);
    typeNameUI = newText(composite, SWT.SINGLE, "Specify the type name");
    typeNameUI.addListener(SWT.Modify, this);
    createWideLabel(composite, "  Matching Types:");
    if (check) {
      tree = newTree(composite, SWT.CHECK);
    } else {
      tree = newTree(composite, SWT.NONE);
    }
    tree.setBounds(10, 10, 50, 50);
    displayFilteredTypes("");
    return composite;
  }

  @Override
  public void handleEvent(Event event) {
    if (event.widget == typeNameUI && event.type == SWT.Modify) {
      displayFilteredTypes(typeNameUI.getText());
    } else if (event.widget == tree) {
      Widget item = event.item;
      if (item instanceof TreeItem) {
        TreeItem ti = (TreeItem) item;
        this.choosenType = ti.getText();
        int level = getLevel(ti);
        TreeItem[] selection = tree.getSelection();
        String text = ti.getText();
        if (level == 1) {
          for (TreeItem treeItem : selection) {
            if (treeItem != ti) {
              if (ti.getChecked()) {
                treeItem.setChecked(true);
                selectedTypes.put(treeItem.getText(), null);
              } else {
                selectedTypes.remove(treeItem.getText());
                treeItem.setChecked(false);
              }
            }
          }
          if (ti.getChecked()) {
            selectedTypes.put(text, new HashSet<String>());
          } else {
            selectedTypes.remove(text);
          }
        }
        if (level == 2) {
          TreeItem parentItem = ti.getParentItem();
          String parentName = parentItem.getText();
          if (!selectedTypes.keySet().contains(parentName)) {
            selectedTypes.put(parentName, new HashSet<String>());
          }
          Set<String> set = selectedTypes.get(parentName);
          if (set == null) {
            set = new HashSet<String>();
            selectedTypes.put(parentName, set);
          }
          if (ti.getChecked()) {
            if (!set.contains(text)) {
              set.add(text);
            }
            parentItem.setChecked(true);
          } else {
            set.remove(text);
          }
        }
      }
    }

  }

  private int getLevel(TreeItem ti) {
    int level = 1;
    TreeItem parent = ti.getParentItem();
    while (parent != null) {
      parent = parent.getParentItem();
      level++;
    }
    return level;
  }

  protected Label createWideLabel(Composite parent, String message) {
    Label label = null;
    label = new Label(parent, SWT.WRAP);
    label.setText(null != message ? message : "");
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
    data.verticalAlignment = SWT.CENTER;
    label.setLayoutData(data);
    return label;
  }

  protected Text newText(Composite parent, int style, String tip) {
    Text t = new Text(parent, style | SWT.BORDER);
    t.setToolTipText(tip);
    t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    t.addListener(SWT.KeyUp, this);
    t.addListener(SWT.MouseUp, this);
    return t;
  }

  protected Tree newTree(Composite parent, int style) {
    Tree tree = new Tree(parent, style | SWT.BORDER | swtTreeModeFlag);
    GridData gd = new GridData(GridData.FILL_BOTH);
    tree.setLayoutData(gd);
    tree.addListener(SWT.Selection, this);
    tree.addListener(SWT.KeyUp, this);
    tree.addListener(SWT.MouseDown, this);
    return tree;
  }

  public boolean isValid() {
    return true;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  @Override
  protected Point getInitialSize() {
    return new Point(450, 450);
  }

  public Map<String, Set<String>> getSelectedTypes() {
    return selectedTypes;
  }

  public Set<String> getSelectedTypesSet() {
    return selectedTypes.keySet();
  }

  public void setSelectedTypes(Map<String, Set<String>> selectedTypes) {
    this.selectedTypes = selectedTypes;
  }

  private void displayFilteredTypes(String aTypeName) {
    tree.setRedraw(false);
    tree.removeAll();
    String topEntry = "";
    aTypeName = aTypeName.toLowerCase();
    for (String type : allTypes) {

      String candidateTypeName = type.toLowerCase();
      if (StringUtils.isBlank(aTypeName)
              || candidateTypeName.toLowerCase().indexOf(aTypeName.toLowerCase()) != -1) {

        if (StringUtils.isEmpty(topEntry)) {
          topEntry = type;
        }
        TreeItem item = new TreeItem(tree, SWT.NULL);
        item.setText(type);
        if (selectedTypes.keySet().contains(item.getText())) {
          item.setChecked(true);
        }
        if (featureMode) {
          List<String> featuresType = allTypesMapping.get(type);
          Set<String> selectedFeaturesType = selectedTypes.get(type);
          for (String feature : featuresType) {
            TreeItem itemFeat = new TreeItem(item, SWT.NULL);
            itemFeat.setText(feature);
            if (selectedFeaturesType != null) {
              if (selectedFeaturesType.contains(feature)) {
                itemFeat.setChecked(true);
              }
            }
          }
        }
      }
    }
    tree.setRedraw(true);
  }

  public String getChoosenType() {
    return this.choosenType;
  }
}
