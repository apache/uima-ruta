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

package org.apache.uima.cev.views.palette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cev.CEVPlugin;
import org.apache.uima.cev.data.CEVData;
import org.apache.uima.cev.data.CEVDocument;
import org.apache.uima.cev.editor.CEVViewer;
import org.apache.uima.cev.extension.ICEVEditor;
import org.apache.uima.cev.preferences.CEVPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

public class CEVAnnotationMarkerPalettePage extends Page implements
        ICEVAnnotationMarkerPalettePage, ICEVEditor {

  private class AnnotationTypeButtonPanel {
    private Composite composite;

    private Button button;

    private Type type;

    private static final int LETTER_WIDTH_FACTOR = 8;

    private static final int BUTTON_WIDTH_OFFSET = 25;

    private static final int ICON_SIZE = 10;

    private static final int MAX_TITLE_LENGTH = 40;

    private AnnotationTypeButtonPanel(Composite parent, Type type) {
      this(parent, type, MAX_TITLE_LENGTH);
    }

    private AnnotationTypeButtonPanel(Composite parent, Type type, int maxTitleLenght) {
      this.type = type;
      composite = new Composite(parent, SWT.NULL | SWT.LEFT);
      composite.setLayout(new RowLayout(SWT.HORIZONTAL));
      button = new Button(composite, SWT.TOGGLE | SWT.LEFT);
      button.setText(type.getShortName());
      final int width = BUTTON_WIDTH_OFFSET + maxTitleLenght * LETTER_WIDTH_FACTOR;
      button.setLayoutData(new RowData(width, SWT.DEFAULT));
      button.setAlignment(SWT.LEFT);
      updateColor();

      composite.layout(true);
    }

    private void updateColor() {
      Color bgCol = new Color(composite.getDisplay(), getCurrentCEVData().getBackgroundColor(
              this.type).getRGB());
      RGB[] rgbs = { bgCol.getRGB() };
      ImageData imd = new ImageData(ICON_SIZE, ICON_SIZE, 8, new PaletteData(rgbs));
      Image image = new Image(composite.getDisplay(), imd);
      button.setImage(image);
    }

    public Button getButton() {
      return this.button;
    }

    public Type getType() {
      return this.type;
    }

    public void dispose() {
      button.dispose();
      composite.dispose();
      type = null;
    }

  }

  private class TypeComparator implements Comparator<Type> {
    public int compare(Type type0, Type type1) {
      return type0.getShortName().compareToIgnoreCase(type1.getShortName());
    }

  }

  private class AnnotationTypeCheckButtonPanel {
    private Composite composite;

    private Button button;

    private Type type;

    private static final int LETTER_WIDTH_FACTOR = 8;

    private static final int BUTTON_WIDTH_OFFSET = 25;

    private static final int ICON_SIZE = 10;

    private static final int MAX_TITLE_LENGTH = 40;

    private AnnotationTypeCheckButtonPanel(Composite parent, Type type) {
      this(parent, type, MAX_TITLE_LENGTH);
    }

    private AnnotationTypeCheckButtonPanel(Composite parent, Type type, int maxTitleLenght) {
      this.type = type;
      composite = new Composite(parent, SWT.NULL | SWT.LEFT);
      composite.setLayout(new RowLayout(SWT.HORIZONTAL));
      button = new Button(composite, SWT.TOGGLE | SWT.LEFT);
      button.setText(type.getShortName());
      final int width = BUTTON_WIDTH_OFFSET + maxTitleLenght * LETTER_WIDTH_FACTOR;
      button.setLayoutData(new RowData(width, SWT.DEFAULT));
      button.setAlignment(SWT.LEFT);
      updateColor();

      composite.layout(true);
    }

    private void updateColor() {
      Color backgroundColor = getCurrentCEVData().getBackgroundColor(this.type);
      if (backgroundColor == null) {
        backgroundColor = new Color(composite.getDisplay(), 128, 128, 128);
      }
      Color bgCol = new Color(composite.getDisplay(), backgroundColor.getRGB());
      RGB[] rgbs = { bgCol.getRGB() };
      ImageData imd = new ImageData(ICON_SIZE, ICON_SIZE, 8, new PaletteData(rgbs));
      Image image = new Image(composite.getDisplay(), imd);
      button.setImage(image);
    }

    public Button getButton() {
      return this.button;
    }

    public Type getType() {
      return this.type;
    }

    public void dispose() {
      button.dispose();
      composite.dispose();
      type = null;
    }

  }

  private ScrolledComposite sc;

  private Composite pane;

  private CEVDocument casDocument;

  private List<Type> types;

  private List<AnnotationTypeCheckButtonPanel> toggleButtons = new ArrayList<AnnotationTypeCheckButtonPanel>();

  private int current;

  public CEVAnnotationMarkerPalettePage(CEVViewer casView, CEVDocument casDocument, int index) {
    super();
    current = index;
    this.casDocument = casDocument;
    this.getCurrentCEVData().addAnnotationListener(this);
    types = new ArrayList<Type>();
    updateTypes();
  }

  public List<AnnotationTypeCheckButtonPanel> getToggledButtons() {
    if (toggleButtons == null) {
      return new ArrayList<AnnotationTypeCheckButtonPanel>();
    }
    List<AnnotationTypeCheckButtonPanel> toggledButtons = new ArrayList<AnnotationTypeCheckButtonPanel>();
    for (AnnotationTypeCheckButtonPanel buttonP : toggleButtons) {
      if (buttonP.getButton().getSelection()) {
        toggledButtons.add(buttonP);
      }
    }
    return toggledButtons;
  }

  private void updateControl(int maxTitleLength) {
    for (AnnotationTypeCheckButtonPanel it : toggleButtons) {
      it.dispose();
    }
    toggleButtons.clear();
    for (Iterator<Type> iterator = types.iterator(); iterator.hasNext();) {
      toggleButtons.add(new AnnotationTypeCheckButtonPanel(pane, iterator.next(), maxTitleLength));
    }
    pane.layout(true);
    sc.setContent(pane);
    sc.setMinSize(pane.computeSize(SWT.DEFAULT, SWT.DEFAULT));
  }

  private int updateTypes() {
    if (this.getCurrentCEVData() == null) {
      return 0;
    }
    Iterator typeIterator = this.getCurrentCEVData().getCAS().getTypeSystem().getTypeIterator();
    int maxTitleLength = 0;
    types.clear();

    while (typeIterator.hasNext()) {
      Type type = (Type) typeIterator.next();
      // TODO find a generic way to filter these types:
      if (!(type.getName().startsWith("org.apache.uima.textmarker.type")
              || type.getName().startsWith("org.apache.uima.type")
              || type.getName().startsWith("uima.cas") || type.getName().startsWith("uima.tcas"))) {
        types.add(type);
        int typeShortName = type.getShortName().length();
        if (typeShortName > maxTitleLength) {
          maxTitleLength = typeShortName;
        }
      }
    }
    Collections.sort(types, new TypeComparator());
    return maxTitleLength;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
   */
  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent) {

    sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
    pane = new Composite(sc, SWT.NULL);
    pane.setLayout(new RowLayout(SWT.VERTICAL));

    sc.setExpandHorizontal(true);
    sc.setExpandVertical(true);
    sc.setContent(pane);
    sc.setMinSize(pane.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    casDataChanged();
  }

  private void annotateAllForSelection(int start, int end) {
    List<AnnotationTypeCheckButtonPanel> toggledButtons = getToggledButtons();
    for (AnnotationTypeCheckButtonPanel buttonP : toggledButtons) {
      boolean update = toggledButtons.indexOf(buttonP) == toggledButtons.size() - 1;
      annotateSingleForSelection(start, end, buttonP.getType(), update);
    }
  }

  private void annotateSingleForSelection(int start, int end, Type type, boolean update) {
    if (type == null) {
      return;
    }
    getCurrentCEVData().addAnnotation(type, start, end, update);
  }

  public void casDataChanged() {
    final int maxTitleLength = updateTypes();
    updateControl(maxTitleLength);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    sc.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#getControl()
   */
  @Override
  public Control getControl() {
    return sc;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#setFocus()
   */
  @Override
  public void setFocus() {
    sc.setFocus();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationStateChanged(org
   * .apache.uima.cas.Type)
   */
  public void annotationStateChanged(Type type) {
  }

  protected CEVData getCurrentCEVData() {
    return casDocument.getCASData(current);
  }

  public void viewChanged(int newIndex) {
    current = newIndex;
    casDataChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#colorChanged(org.apache. uima.cas.Type)
   */
  public void colorChanged(Type type) {
    for (Iterator<AnnotationTypeCheckButtonPanel> iterator = toggleButtons.iterator(); iterator
            .hasNext();) {
      AnnotationTypeCheckButtonPanel button = iterator.next();
      if (button.getType().equals(type)) {
        button.updateColor();
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationAdded(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public void annotationsAdded(List<AnnotationFS> annots) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationRemoved(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public void annotationsRemoved(List<AnnotationFS> annots) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.cev.data.ICEVAnnotationListener#annotationStateChanged(org
   * .apache.uima.cas.text.AnnotationFS)
   */
  public void annotationStateChanged(AnnotationFS annot) {
  }

  public void textSelected(int start, int end) {
    String text = getCurrentCEVData().getDocumentText().substring(start, end);
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    boolean trim = store.getBoolean(CEVPreferenceConstants.P_ANNOTATION_EDITOR_TRIM);

    if (trim) {
      String trimmed = text.trim();

      int indexOf = text.indexOf(trimmed);

      int trimmedStart = start + indexOf;
      int trimmedEnd = end - (text.length() - (indexOf + trimmed.length()));

      start = trimmedStart;
      end = trimmedEnd;
    }
    annotateAllForSelection(start, end);
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDocument = casDocument;
  }
}
