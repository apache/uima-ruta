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

package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.dialogs.TimeTriggeredProgressMonitorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class TextMarkerPagesLocationsBlock implements SelectionListener, ISelectionChangedListener {

  protected static final String LAST_PATH_SETTING = "LAST_PATH_SETTING"; //$NON-NLS-1$

  protected static final String DIALOG_SETTINGS_PREFIX = "TextMarkerPagesLocationsBlock"; //$NON-NLS-1$

  protected boolean fInCallback = false;

  protected File fHome;

  // widgets
  protected TreeViewer fLocationsViewer;

  private Button fClearButton;

  private Button fRemoveButton;

  private Button fAddButton;

  protected Button fDefaultButton;

  private ManLocationsContentProvider fLocationsContentProvider;

  private PreferencePage fPage;

  private IPreferenceStore fStore;

  public TextMarkerPagesLocationsBlock(IPreferenceStore store, PreferencePage page) {
    fPage = page;
    fStore = store;
  }

  protected IBaseLabelProvider getLabelProvider() {
    return new LabelProvider() {

      @Override
      public Image getImage(Object element) {
        if (element instanceof TextMarkerPageFolder) {
          return DLTKPluginImages.DESC_OBJS_LIBRARY.createImage();
        }
        return DLTKPluginImages.DESC_OBJS_INFO_OBJ.createImage();
      }

      @Override
      public String getText(Object element) {
        if (element instanceof TextMarkerPageFolder) {
          TextMarkerPageFolder folder = (TextMarkerPageFolder) element;
          return folder.getPath();
        }
        return super.getText(element);
      }

    };
  }

  private List folders = null;

  private String getFoldersAsXML() {
    if (folders == null)
      return null;

    // Create the Document and the top-level node
    DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder;
    try {
      docBuilder = dfactory.newDocumentBuilder();
    } catch (ParserConfigurationException e1) {
      e1.printStackTrace();
      return null;
    }
    Document doc = docBuilder.newDocument();

    Element topElement = doc.createElement("manPages");
    doc.appendChild(topElement);

    for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
      TextMarkerPageFolder f = (TextMarkerPageFolder) iterator.next();
      Element location = doc.createElement("location");
      topElement.appendChild(location);
      location.setAttribute("path", f.getPath());
      for (Iterator iterator2 = f.getPages().keySet().iterator(); iterator2.hasNext();) {
        String name = (String) iterator2.next();
        String file = (String) f.getPages().get(name);
        Element page = doc.createElement("page");
        location.appendChild(page);
        page.setAttribute("keyword", name);
        page.setAttribute("file", file);
      }
    }

    ByteArrayOutputStream s = new ByteArrayOutputStream();

    try {
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer;
      transformer = factory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
      transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$

      DOMSource source = new DOMSource(doc);
      StreamResult outputTarget = new StreamResult(s);
      transformer.transform(source, outputTarget);
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }

    String result = null;
    try {
      result = s.toString("UTF8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return result;
  }

  private class ManLocationsContentProvider implements ITreeContentProvider {

    public Object[] getChildren(Object parentElement) {
      if (parentElement instanceof TextMarkerPageFolder) {
        TextMarkerPageFolder folder = (TextMarkerPageFolder) parentElement;
        String[] ch = new String[folder.getPages().size()];
        int i = 0;
        for (Iterator iterator = folder.getPages().keySet().iterator(); iterator.hasNext();) {
          String kw = (String) iterator.next();
          String file = (String) folder.getPages().get(kw);
          ch[i++] = kw + " (" + file + ")";
        }
        return ch;
      }
      return new Object[0];
    }

    public Object getParent(Object element) {
      return null;
    }

    public boolean hasChildren(Object element) {
      if (element instanceof TextMarkerPageFolder)
        return true;
      return false;
    }

    public Object[] getElements(Object inputElement) {
      if (folders == null)
        return new Object[0];
      return folders.toArray(new Object[folders.size()]);
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

  }

  /**
   * Creates and returns the source lookup control.
   * 
   * @param parent
   *          the parent widget of this control
   */
  public Control createControl(Composite parent) {
    Font font = parent.getFont();

    Composite comp = new Composite(parent, SWT.NONE);
    GridLayout topLayout = new GridLayout();
    topLayout.numColumns = 2;
    topLayout.marginHeight = 0;
    topLayout.marginWidth = 0;
    comp.setLayout(topLayout);
    GridData gd = new GridData(GridData.FILL_BOTH);
    comp.setLayoutData(gd);

    fLocationsViewer = new TreeViewer(comp);
    gd = new GridData(GridData.FILL_BOTH);
    gd.heightHint = 6;
    fLocationsViewer.getControl().setLayoutData(gd);
    fLocationsContentProvider = new ManLocationsContentProvider();
    fLocationsViewer.setSorter(new ViewerSorter());
    fLocationsViewer.setContentProvider(fLocationsContentProvider);
    fLocationsViewer.setLabelProvider(getLabelProvider());
    fLocationsViewer.setInput(this);
    fLocationsViewer.addSelectionChangedListener(this);

    Composite pathButtonComp = new Composite(comp, SWT.NONE);
    GridLayout pathButtonLayout = new GridLayout();
    pathButtonLayout.marginHeight = 0;
    pathButtonLayout.marginWidth = 0;
    pathButtonComp.setLayout(pathButtonLayout);
    gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
    pathButtonComp.setLayoutData(gd);
    pathButtonComp.setFont(font);

    fAddButton = createPushButton(pathButtonComp, "Add folder...");
    fAddButton.addSelectionListener(this);

    fRemoveButton = createPushButton(pathButtonComp, "Remove");
    fRemoveButton.addSelectionListener(this);

    fClearButton = createPushButton(pathButtonComp, "Remove All");
    fClearButton.addSelectionListener(this);

    return comp;
  }

  /**
   * Creates and returns a button
   * 
   * @param parent
   *          parent widget
   * @param label
   *          label
   * @return Button
   */
  protected Button createPushButton(Composite parent, String label) {
    Button button = new Button(parent, SWT.PUSH);
    button.setFont(parent.getFont());
    button.setText(label);
    setButtonLayoutData(button);
    return button;
  }

  protected void setButtonLayoutData(Button button) {
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    int widthHint = 80;
    Point minSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
    data.widthHint = Math.max(widthHint, minSize.x);
    button.setLayoutData(data);
  }

  /**
   * Create some empty space
   */
  protected void createVerticalSpacer(Composite comp, int colSpan) {
    Label label = new Label(comp, SWT.NONE);
    GridData gd = new GridData();
    gd.horizontalSpan = colSpan;
    label.setLayoutData(gd);
  }

  /**
   * Updates buttons and status based on current mans
   */
  public void update() {
    updateButtons();

    if (folders != null) {
      for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
        TextMarkerPageFolder v = (TextMarkerPageFolder) iterator.next();
        if (!v.verify()) {
          iterator.remove();
        }
      }
    }

    fLocationsViewer.refresh();

    updatePageStatus(Status.OK_STATUS);
  }

  public void setDefaults() {
    String res = fStore.getDefaultString(TextMarkerPreferenceConstants.DOC_TM_PAGES_LOCATIONS);
    fStore.setValue(TextMarkerPreferenceConstants.DOC_TM_PAGES_LOCATIONS, res);
    initialize();
  }

  protected void updatePageStatus(IStatus status) {
    if (fPage == null)
      return;
    fPage.setValid(status.isOK());
    if (!status.isOK())
      fPage.setErrorMessage(status.getMessage());
    else
      fPage.setErrorMessage(null);
  }

  public void initialize() {
    String value = fStore.getString(TextMarkerPreferenceConstants.DOC_TM_PAGES_LOCATIONS);
    try {
      this.folders = TextMarkerPageFolder.readXML(value);
    } catch (IOException e) {
      e.printStackTrace();
    }

    update();
  }

  /**
   * Saves settings
   */
  public void performApply() {
    String xml = this.getFoldersAsXML();
    if (xml != null)
      fStore.setValue(TextMarkerPreferenceConstants.DOC_TM_PAGES_LOCATIONS, xml);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetSelected(SelectionEvent e) {
    Object source = e.getSource();
    if (source == fClearButton) {
      folders.clear();
    } else if (source == fRemoveButton) {
      IStructuredSelection selection = (IStructuredSelection) fLocationsViewer.getSelection();
      Object[] array = selection.toArray();
      for (int i = 0; i < array.length; i++) {
        if (array[i] instanceof TextMarkerPageFolder) {
          for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
            TextMarkerPageFolder f = (TextMarkerPageFolder) iterator.next();
            if (f == array[i]) {
              iterator.remove();
              break;
            }
          }
        }
      }
    } else if (source == fAddButton) {
      add();
    }

    update();
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.
   * SelectionEvent)
   */
  public void widgetDefaultSelected(SelectionEvent e) {
  }

  /**
   * Open the file selection dialog, and add the return locations.
   */
  protected void add() {
    DirectoryDialog dialog = new DirectoryDialog(fLocationsViewer.getControl().getShell());
    dialog.setMessage("Select directory to search into");
    String result = dialog.open();
    if (result != null) {
      final File file = new File(result);
      if (this.folders == null)
        this.folders = new ArrayList();
      if (file != null && file.isDirectory()) {
        ProgressMonitorDialog dialog2 = new TimeTriggeredProgressMonitorDialog(null, 500);
        try {
          dialog2.run(true, true, new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) {
              monitor.beginTask("Searching for man pages", 1);
              performSearch(file);
              monitor.done();
            }
          });
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }

  private void performSearch(File dir) {
    if (!dir.isDirectory())
      return;

    String name = dir.getName();

    if (name.equals("TkLib") || name.equals("TextMarkerLib") || name.equals("Keywords")
            || name.equals("UserCmd"))
      return;

    File[] childs = dir.listFiles(new FileFilter() {

      public boolean accept(File file) {
        if (file.isDirectory())
          return true;
        if (file.getName().startsWith("contents.htm"))
          return true;
        return false;
      }

    });
    for (int i = 0; i < childs.length; i++) {
      if (childs[i].isDirectory()) {
        performSearch(childs[i]);
      }
      if (childs[i].getName().startsWith("contents.htm")) {
        TextMarkerPageFolder folder = new TextMarkerPageFolder(dir.getAbsolutePath());
        parseContentsFile(childs[i], folder);
        if (folder.getPages().size() > 0 && !folders.contains(folder)) {
          this.folders.add(folder);
        }
      }
    }
  }

  private void parseContentsFile(File c, TextMarkerPageFolder folder) {
    FileReader reader;
    try {
      reader = new FileReader(c);
    } catch (FileNotFoundException e) {
      return;
    }
    StringBuffer buf = new StringBuffer();
    while (true) {
      char cbuf[] = new char[1024];
      try {
        int read = reader.read(cbuf);
        if (read >= 0) {
          buf.append(cbuf, 0, read);
        } else
          break;
      } catch (IOException e) {
        break;
      }
    }
    String result = buf.toString();
    Pattern pattern = Pattern.compile("<a\\s+href=\"([a-zA-Z_0-9]+\\.html?)\"\\s*>(\\w+)</a>",
            Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(result);
    while (matcher.find()) {
      String file = matcher.group(1);
      if (file.equalsIgnoreCase("Copyright.htm"))
        continue;
      String word = matcher.group(2);
      folder.addPage(word, file);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers
   * .SelectionChangedEvent)
   */
  public void selectionChanged(SelectionChangedEvent event) {
    updateButtons();
  }

  /**
   * Refresh the enable/disable state for the buttons.
   */
  private void updateButtons() {
    fClearButton.setEnabled(folders != null && folders.size() > 0);
    IStructuredSelection selection = (IStructuredSelection) fLocationsViewer.getSelection();

    boolean canRemove = true;
    if (folders == null)
      canRemove = false;
    else {
      List list = selection.toList();
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
        Object o = iterator.next();
        if (!folders.contains(o))
          canRemove = false;
        break;
      }
      if (selection.isEmpty())
        canRemove = false;
    }

    fRemoveButton.setEnabled(canRemove);
  }

}
