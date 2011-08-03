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

package org.apache.uima.tm.dltk.internal.ui.text.folding;

import java.util.ArrayList;

import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class TextMarkerFoldingPreferenceBlock extends AbstractConfigurationBlock implements
        IFoldingPreferenceBlock {

  protected class ListBlock {
    private ListViewer fList;

    private String fKey;

    private Button fAddButton;

    private Button fRemoveButton;

    public ListBlock(Composite parent, String key) {
      fKey = key;
      createControl(parent);
    }

    private Control createControl(Composite parent) {
      Font font = parent.getFont();
      Composite comp = new Composite(parent, SWT.NONE);
      GridLayout topLayout = new GridLayout();
      topLayout.numColumns = 2;
      topLayout.marginHeight = 0;
      topLayout.marginWidth = 0;
      comp.setLayout(topLayout);
      GridData gd = new GridData(GridData.FILL_BOTH);
      comp.setLayoutData(gd);
      fList = new ListViewer(comp);
      gd = new GridData(GridData.FILL_BOTH);
      gd.heightHint = 6;
      fList.getControl().setLayoutData(gd);
      Composite pathButtonComp = new Composite(comp, SWT.NONE);
      GridLayout pathButtonLayout = new GridLayout();
      pathButtonLayout.marginHeight = 0;
      pathButtonLayout.marginWidth = 0;
      pathButtonComp.setLayout(pathButtonLayout);
      gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
      pathButtonComp.setLayoutData(gd);
      pathButtonComp.setFont(font);
      fAddButton = createPushButton(pathButtonComp,
              TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_0);
      fAddButton.addSelectionListener(new SelectionListener() {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
          IInputValidator validator = new IInputValidator() {
            public String isValid(String newText) {
              if (newText.trim().length() > 0 && newText.matches("[_a-zA-Z]*")) //$NON-NLS-1$
                return null;
              return TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_2;
            }
          };
          InputDialog dlg = new InputDialog(null,
                  TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_3,
                  TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_4, "", validator);
          if (dlg.open() == Window.OK) {
            fList.add(dlg.getValue());
            save();
          }
        }
      });
      fRemoveButton = createPushButton(pathButtonComp,
              TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_6);
      fRemoveButton.addSelectionListener(new SelectionListener() {
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        public void widgetSelected(SelectionEvent e) {
          ISelection s = fList.getSelection();
          if (s instanceof IStructuredSelection) {
            IStructuredSelection sel = (IStructuredSelection) s;
            fList.remove(sel.toArray());
            save();
          }
        }
      });
      return comp;
    }

    protected Button createPushButton(Composite parent, String label) {
      Button button = new Button(parent, SWT.PUSH);
      button.setFont(parent.getFont());
      if (label != null) {
        button.setText(label);
      }
      GridData gd = new GridData();
      button.setLayoutData(gd);
      gd.widthHint = getButtonWidthHint(button);
      gd.horizontalAlignment = GridData.FILL;
      return button;
    }

    /**
     * Returns a width hint for a button control.
     */
    public int getButtonWidthHint(Button button) {
      button.setFont(JFaceResources.getDialogFont());
      PixelConverter converter = new PixelConverter(button);
      int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
      return Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
    }

    private String[] getEntries() {
      return fList.getList().getItems();
    }

    private void setEntries(String items[]) {
      fList.remove(fList.getList().getItems());
      for (int i = 0; i < items.length; i++) {
        if (items[i].trim().length() > 0)
          fList.add(items[i]);
      }
    }

    public void save() {
      String items[] = getEntries();
      StringBuffer buf = new StringBuffer();
      for (int i = 0; i < items.length; i++) {
        buf.append(items[i]);
        if (i != items.length - 1)
          buf.append(","); //$NON-NLS-1$
      }
      getPreferenceStore().setValue(fKey, buf.toString());
    }

    public void initialize() {
      String val = getPreferenceStore().getString(fKey);
      if (val != null) {
        String items[] = val.split(","); //$NON-NLS-1$
        setEntries(items);
      }

    }

    public void performDefault() {
      String val = getPreferenceStore().getDefaultString(fKey);
      if (val != null) {
        String items[] = val.split(","); //$NON-NLS-1$
        setEntries(items);
      }
    }
  }

  private ListBlock fExcludePatterns;

  private ListBlock fIncludePatterns;

  private OverlayPreferenceStore fOverlayStore;

  private OverlayKey[] fKeys;

  public TextMarkerFoldingPreferenceBlock(OverlayPreferenceStore store,
          PreferencePage mainPreferencePage) {
    super(store, mainPreferencePage);
    fOverlayStore = store;
    fKeys = createKeys();
    fOverlayStore.addKeys(fKeys);
  }

  private OverlayKey[] createKeys() {
    ArrayList overlayKeys = new ArrayList();
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.INT,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.INT,
            PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_BLOCKS));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_NAMESPACES));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED));
    overlayKeys.add(new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES));
    OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
            .size()];
    overlayKeys.toArray(keys);
    return keys;
  }

  public Control createControl(Composite composite) {
    Composite inner = new Composite(composite, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    inner.setLayout(layout);

    Composite blockFolding = createSubsection(inner, null,
            TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_10);
    blockFolding.setLayout(new GridLayout());

    addRadioButton(blockFolding, TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_11,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_OFF);
    addRadioButton(blockFolding, TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_12,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_EXCLUDE);
    fExcludePatterns = new ListBlock(blockFolding,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST);
    addRadioButton(blockFolding, TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_13,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_INCLUDE);
    fIncludePatterns = new ListBlock(blockFolding,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST);

    IInputValidator val = new IInputValidator() {

      public String isValid(String number) {
        if (number.length() == 0) {
          return PreferencesMessages.DLTKEditorPreferencePage_empty_input;
        } else {
          try {
            int value = Integer.parseInt(number);
            if (value < 2)
              return "You may input numbers >= 2.";
          } catch (NumberFormatException e) {
            return "Input is not a number";
          }
        }
        return null;
      }

    };

    addLabelledTextField(blockFolding, "Minimal amount of lines to be folded(>=2):",
            PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 3, 1, true, val);

    Composite commentFolding = createSubsection(inner, null,
            TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_14);
    commentFolding.setLayout(new GridLayout());

    addCheckBox(commentFolding, TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_15,
            PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED, 0);

    Composite initialFolding = createSubsection(inner, null,
            TextMarkerFoldingMessages.TextMarkerFoldingPreferenceBlock_16);
    initialFolding.setLayout(new GridLayout());

    addCheckBox(initialFolding, TextMarkerFoldingMessages.DefaultFoldingPreferenceBlock_headers,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS, 0);
    addCheckBox(initialFolding, TextMarkerFoldingMessages.DefaultFoldingPreferenceBlock_innerTypes,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_NAMESPACES, 0);
    addCheckBox(initialFolding, TextMarkerFoldingMessages.DefaultFoldingPreferenceBlock_methods,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_BLOCKS, 0);

    return inner;
  }

  @Override
  public void initialize() {
    super.initialize();
    fExcludePatterns.initialize();
    fIncludePatterns.initialize();
  }

  @Override
  public void performDefaults() {
    super.performDefaults();
    fExcludePatterns.performDefault();
    fIncludePatterns.performDefault();
  }
}
