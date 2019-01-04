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

package org.apache.uima.ruta.ide.debug.ui.launchConfiguration;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.IDebugHelpContextIds;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationManager;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationsMessages;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchGroupExtension;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchHistory;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.ILaunchGroup;
import org.eclipse.debug.ui.StringVariableSelectionDialog;
import org.eclipse.dltk.core.environment.EnvironmentManager;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.internal.ui.util.SWTUtil;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchEncoding;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.ide.IDEEncoding;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceSorter;

/**
 * Launch configuration tab used to specify the location a launch configuration is stored in,
 * whether it should appear in the favorites list, and perspective switching behavior for an
 * associated launch.
 * <p>
 * Clients may instantiate this class. This class is not intended to be subclassed.
 * </p>
 * 
 * 
 */
public class RutaCommonTab extends AbstractLaunchConfigurationTab {

  /**
   * Provides a persistable dialog for selecting the shared project location
   */
  class SharedLocationSelectionDialog extends ContainerSelectionDialog {
    private final String SETTINGS_ID = IDebugUIConstants.PLUGIN_ID
            + ".SHARED_LAUNCH_CONFIGURATON_DIALOG"; //$NON-NLS-1$

    public SharedLocationSelectionDialog(Shell parentShell, IContainer initialRoot,
            boolean allowNewContainerName, String message) {
      super(parentShell, initialRoot, allowNewContainerName, message);
    }

    @Override
    protected IDialogSettings getDialogBoundsSettings() {
      IDialogSettings settings = DebugUIPlugin.getDefault().getDialogSettings();
      IDialogSettings section = settings.getSection(SETTINGS_ID);
      if (section == null) {
        section = settings.addNewSection(SETTINGS_ID);
      }
      return section;
    }
  }

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  // Local/shared UI widgets
  private Button fLocalRadioButton;

  private Button fSharedRadioButton;

  private Text fSharedLocationText;

  private Button fSharedLocationButton;

  private Button fLaunchInBackgroundButton;

  private Button fDefaultEncodingButton;

  private Button fAltEncodingButton;

  private Combo fEncodingCombo;

  private Button fConsoleOutput;

  private Button fFileOutput;

  private Button fFileBrowse;

  private Text fFileText;

  private Button fVariables;

  private Button fAppend;

  private Button fWorkspaceBrowse;

  private Button fUseDltkRadio;

  private Button fNotUseDltkRatio;

  /**
   * Check box list for specifying favorites
   */
  private CheckboxTableViewer fFavoritesTable;

  /**
   * Modify listener that simply updates the owning launch configuration dialog.
   */
  private ModifyListener fBasicModifyListener = new ModifyListener() {
    public void modifyText(ModifyEvent evt) {
      updateLaunchConfigurationDialog();
    }
  };

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    Composite comp = new Composite(parent, SWT.NONE);
    setControl(comp);
    PlatformUI.getWorkbench().getHelpSystem()
            .setHelp(getControl(), IDebugHelpContextIds.LAUNCH_CONFIGURATION_DIALOG_COMMON_TAB);
    comp.setLayout(new GridLayout(2, true));
    comp.setFont(parent.getFont());

    createSharedConfigComponent(comp);
    createFavoritesComponent(comp);
    createEncodingComponent(comp);
    createOutputCaptureComponent(comp);
    createLaunchInBackgroundComponent(comp);
  }

  /**
   * Creates the favorites control
   * 
   * @param parent
   *          the parent composite to add this one to
   * 
   */
  private void createFavoritesComponent(Composite parent) {
    Group favComp = SWTUtil.createGroup(parent,
            LaunchConfigurationsMessages.CommonTab_Display_in_favorites_menu__10, 1, 1,
            GridData.FILL_BOTH);
    fFavoritesTable = CheckboxTableViewer.newCheckList(favComp, SWT.CHECK | SWT.BORDER | SWT.MULTI
            | SWT.FULL_SELECTION);
    Control table = fFavoritesTable.getControl();
    GridData gd = new GridData(GridData.FILL_BOTH);
    table.setLayoutData(gd);
    table.setFont(parent.getFont());
    fFavoritesTable.setContentProvider(new FavoritesContentProvider());
    fFavoritesTable.setLabelProvider(new FavoritesLabelProvider());
    fFavoritesTable.addCheckStateListener(new ICheckStateListener() {
      public void checkStateChanged(CheckStateChangedEvent event) {
        updateLaunchConfigurationDialog();
      }
    });
  }

  /**
   * Creates the shared config component
   * 
   * @param parent
   *          the parent composite to add this component to
   * 
   */
  private void createSharedConfigComponent(Composite parent) {
    Group group = SWTUtil.createGroup(parent, LaunchConfigurationsMessages.CommonTab_0, 3, 2,
            GridData.FILL_HORIZONTAL);
    fLocalRadioButton = createRadioButton(group, LaunchConfigurationsMessages.CommonTab_L_ocal_3);
    GridData gd = new GridData();
    gd.horizontalSpan = 3;
    fLocalRadioButton.setLayoutData(gd);
    fSharedRadioButton = createRadioButton(group, LaunchConfigurationsMessages.CommonTab_S_hared_4);
    fSharedRadioButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent evt) {
        handleSharedRadioButtonSelected();
      }
    });
    fSharedLocationText = SWTUtil.createSingleText(group, 1);
    fSharedLocationText.addModifyListener(fBasicModifyListener);
    fSharedLocationButton = createPushButton(group,
            LaunchConfigurationsMessages.CommonTab__Browse_6, null);
    fSharedLocationButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent evt) {
        handleSharedLocationButtonSelected();
      }
    });

    fLocalRadioButton.setSelection(true);
    setSharedEnabled(false);
  }

  /**
   * Creates the component set for the capture output composite
   * 
   * @param data
   *          .parent the parent to add this component to
   */

  private void test() {

  }

  private void createOutputCaptureComponent(Composite parent) {
    Group group = SWTUtil.createGroup(parent, "Input and Output", 1, 2, GridData.FILL_HORIZONTAL);

    fUseDltkRadio = createRadioButton(group, "Use DLTK Input and Output");
    fUseDltkRadio.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        updateLaunchConfigurationDialog();
      }
    });

    fNotUseDltkRatio = createRadioButton(group, "Use Standard Input and Output");
    fNotUseDltkRatio.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        updateLaunchConfigurationDialog();
      }
    });

    Group standardGroup = SWTUtil.createGroup(group, "", 5, 2, GridData.FILL_HORIZONTAL);

    fConsoleOutput = createCheckButton(standardGroup, LaunchConfigurationsMessages.CommonTab_5);
    GridData gd = new GridData(SWT.BEGINNING, SWT.NORMAL, true, false);
    gd.horizontalSpan = 5;
    fConsoleOutput.setLayoutData(gd);
    fConsoleOutput.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        updateLaunchConfigurationDialog();
      }
    });

    fFileOutput = createCheckButton(standardGroup, LaunchConfigurationsMessages.CommonTab_6);
    fFileOutput.setLayoutData(new GridData(SWT.BEGINNING, SWT.NORMAL, false, false));
    fFileOutput.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        enableOuputCaptureWidgets(fFileOutput.getSelection());
        updateLaunchConfigurationDialog();
      }
    });
    fFileText = SWTUtil.createSingleText(standardGroup, 4);
    fFileText.addModifyListener(fBasicModifyListener);

    SWTUtil.createLabel(standardGroup, EMPTY_STRING, 2);

    fWorkspaceBrowse = createPushButton(standardGroup, LaunchConfigurationsMessages.CommonTab_12,
            null);
    fWorkspaceBrowse.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(),
                new WorkbenchLabelProvider(), new WorkbenchContentProvider());
        dialog.setTitle(LaunchConfigurationsMessages.CommonTab_13);
        dialog.setMessage(LaunchConfigurationsMessages.CommonTab_14);
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        dialog.setSorter(new ResourceSorter(ResourceSorter.NAME));
        if (dialog.open() == IDialogConstants.OK_ID) {
          IResource resource = (IResource) dialog.getFirstResult();
          String arg = resource.getFullPath().toString();
          String fileLoc = VariablesPlugin.getDefault().getStringVariableManager()
                  .generateVariableExpression("workspace_loc", arg); //$NON-NLS-1$
          fFileText.setText(fileLoc);
        }
      }
    });
    fFileBrowse = createPushButton(standardGroup, LaunchConfigurationsMessages.CommonTab_7, null);
    fFileBrowse.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        String filePath = fFileText.getText();
        FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
        filePath = dialog.open();
        if (filePath != null) {
          fFileText.setText(filePath);
        }
      }
    });
    fVariables = createPushButton(standardGroup, LaunchConfigurationsMessages.CommonTab_9, null);
    fVariables.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        StringVariableSelectionDialog dialog = new StringVariableSelectionDialog(getShell());
        dialog.open();
        String variable = dialog.getVariableExpression();
        if (variable != null) {
          fFileText.insert(variable);
        }
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    fAppend = createCheckButton(standardGroup, LaunchConfigurationsMessages.CommonTab_11);
    gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
    gd.horizontalSpan = 4;
    fAppend.setLayoutData(gd);
    fAppend.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        updateLaunchConfigurationDialog();
      }
    });
  }

  /**
   * Enables or disables the output capture widgets based on the the specified enablement
   * 
   * @param enable
   *          if the output capture widgets should be enabled or not
   * 
   */
  private void enableOuputCaptureWidgets(boolean enable) {
    fFileText.setEnabled(enable);
    fFileBrowse.setEnabled(enable);
    fWorkspaceBrowse.setEnabled(enable);
    fVariables.setEnabled(enable);
    fAppend.setEnabled(enable);
  }

  /**
   * Creates the encoding component
   * 
   * @param parent
   *          the parent to add this composite to
   */
  private void createEncodingComponent(Composite parent) {
    List allEncodings = IDEEncoding.getIDEEncodings();
    String defaultEncoding = WorkbenchEncoding.getWorkbenchDefaultEncoding();
    Group group = SWTUtil.createGroup(parent, LaunchConfigurationsMessages.CommonTab_1, 2, 1,
            GridData.FILL_BOTH);

    fDefaultEncodingButton = createRadioButton(group, MessageFormat.format(
            LaunchConfigurationsMessages.CommonTab_2, new String[] { defaultEncoding }));
    GridData gd = new GridData(SWT.BEGINNING, SWT.NORMAL, true, false);
    gd.horizontalSpan = 2;
    fDefaultEncodingButton.setLayoutData(gd);

    fAltEncodingButton = createRadioButton(group, LaunchConfigurationsMessages.CommonTab_3);
    fAltEncodingButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

    fEncodingCombo = new Combo(group, SWT.READ_ONLY);
    fEncodingCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    fEncodingCombo.setFont(parent.getFont());
    String[] encodingArray = (String[]) allEncodings.toArray(new String[0]);
    fEncodingCombo.setItems(encodingArray);
    if (encodingArray.length > 0) {
      fEncodingCombo.select(0);
    }
    SelectionListener listener = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        updateLaunchConfigurationDialog();
        fEncodingCombo.setEnabled(fAltEncodingButton.getSelection() == true);
      }
    };
    fAltEncodingButton.addSelectionListener(listener);
    fDefaultEncodingButton.addSelectionListener(listener);
    fEncodingCombo.addSelectionListener(listener);
  }

  /**
   * Creates the controls needed to edit the launch in background attribute of an external tool
   * 
   * @param parent
   *          the composite to create the controls in
   */
  protected void createLaunchInBackgroundComponent(Composite parent) {
    fLaunchInBackgroundButton = createCheckButton(parent, LaunchConfigurationsMessages.CommonTab_10);
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    data.horizontalSpan = 2;
    fLaunchInBackgroundButton.setLayoutData(data);
    fLaunchInBackgroundButton.setFont(parent.getFont());
    fLaunchInBackgroundButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        updateLaunchConfigurationDialog();
      }
    });
  }

  /**
   * handles the shared radio button being selected
   */
  private void handleSharedRadioButtonSelected() {
    setSharedEnabled(isShared());
    updateLaunchConfigurationDialog();
  }

  /**
   * Sets the widgets for specifying that a launch configuration is to be shared to the enable value
   * 
   * @param enable
   *          the enabled value for
   */
  private void setSharedEnabled(boolean enable) {
    fSharedLocationText.setEnabled(enable);
    fSharedLocationButton.setEnabled(enable);
  }

  private String getDefaultSharedConfigLocation(ILaunchConfiguration config) {
    String path = EMPTY_STRING;
    try {
      IResource[] res = config.getMappedResources();
      if (res != null) {
        IProject proj;
        for (int i = 0; i < res.length; i++) {
          proj = res[i].getProject();
          if (proj.isAccessible()) {
            return proj.getFullPath().toOSString();
          }
        }
      }
    } catch (CoreException e) {
      DebugUIPlugin.log(e);
    }
    return path;
  }

  /**
   * if the shared radio button is selected, indicating that the launch configuration is to be
   * shared
   * 
   * @return true if the radio button is selected, false otherwise
   */
  private boolean isShared() {
    return fSharedRadioButton.getSelection();
  }

  /**
   * Handles the shared location button being selected
   */
  private void handleSharedLocationButtonSelected() {
    String currentContainerString = fSharedLocationText.getText();
    IContainer currentContainer = getContainer(currentContainerString);
    SharedLocationSelectionDialog dialog = new SharedLocationSelectionDialog(
            getShell(),
            currentContainer,
            false,
            LaunchConfigurationsMessages.CommonTab_Select_a_location_for_the_launch_configuration_13);
    dialog.showClosedProjects(false);
    dialog.open();
    Object[] results = dialog.getResult();
    if ((results != null) && (results.length > 0) && (results[0] instanceof IPath)) {
      IPath path = (IPath) results[0];
      String containerName = path.toOSString();
      fSharedLocationText.setText(containerName);
    }
  }

  /**
   * gets the container form the specified path
   * 
   * @param path
   *          the path to get the container from
   * @return the container for the specified path or null if one cannot be determined
   */
  private IContainer getContainer(String path) {
    Path containerPath = new Path(path);
    return (IContainer) getWorkspaceRoot().findMember(containerPath);
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.
   * ILaunchConfiguration)
   */
  public void initializeFrom(ILaunchConfiguration configuration) {
    boolean isShared = !configuration.isLocal();
    fSharedRadioButton.setSelection(isShared);
    fLocalRadioButton.setSelection(!isShared);
    setSharedEnabled(isShared);
    fSharedLocationText.setText(getDefaultSharedConfigLocation(configuration));
    if (isShared) {
      String containerName = EMPTY_STRING;
      IFile file = configuration.getFile();
      if (file != null) {
        IContainer parent = file.getParent();
        if (parent != null) {
          containerName = parent.getFullPath().toOSString();
        }
      }
      fSharedLocationText.setText(containerName);
    }
    updateFavoritesFromConfig(configuration);
    updateLaunchInBackground(configuration);
    updateEncoding(configuration);
    updateConsoleOutput(configuration);
  }

  /**
   * Updates the console output form the local configuration
   * 
   * @param configuration
   *          the local configuration
   */
  private void updateConsoleOutput(ILaunchConfiguration configuration) {
    boolean outputToConsole = true;
    String outputFile = null;
    boolean append = false;

    boolean dltkOutput = false;

    try {
      dltkOutput = configuration.getAttribute(
              ScriptLaunchConfigurationConstants.ATTR_USE_INTERACTIVE_CONSOLE, false);

      outputToConsole = configuration.getAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_CONSOLE, true);
      outputFile = configuration
              .getAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE, (String) null);
      append = configuration.getAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, false);
    } catch (CoreException e) {
    }

    fUseDltkRadio.setSelection(dltkOutput);
    fNotUseDltkRatio.setSelection(!dltkOutput);

    fConsoleOutput.setSelection(outputToConsole);
    fAppend.setSelection(append);
    boolean haveOutputFile = outputFile != null;
    if (haveOutputFile) {
      fFileText.setText(outputFile);
    }
    fFileOutput.setSelection(haveOutputFile);
    enableOuputCaptureWidgets(haveOutputFile);
  }

  /**
   * Updates the launch on background check button
   * 
   * @param configuration
   *          the local launch configuration
   */
  protected void updateLaunchInBackground(ILaunchConfiguration configuration) {
    fLaunchInBackgroundButton.setSelection(isLaunchInBackground(configuration));
  }

  /**
   * Updates the encoding
   * 
   * @param configuration
   *          the local configuration
   */
  private void updateEncoding(ILaunchConfiguration configuration) {
    String encoding = null;
    try {
      encoding = configuration.getAttribute(IDebugUIConstants.ATTR_CONSOLE_ENCODING, (String) null);
    } catch (CoreException e) {
    }

    if (encoding != null) {
      fAltEncodingButton.setSelection(true);
      fDefaultEncodingButton.setSelection(false);
      fEncodingCombo.setText(encoding);
      fEncodingCombo.setEnabled(true);
    } else {
      fDefaultEncodingButton.setSelection(true);
      fAltEncodingButton.setSelection(false);
      fEncodingCombo.setEnabled(false);
    }
  }

  /**
   * Returns whether the given configuration should be launched in the background.
   * 
   * @param configuration
   *          the configuration
   * @return whether the configuration is configured to launch in the background
   */
  public static boolean isLaunchInBackground(ILaunchConfiguration configuration) {
    boolean launchInBackground = true;
    try {
      launchInBackground = configuration.getAttribute(IDebugUIConstants.ATTR_LAUNCH_IN_BACKGROUND,
              true);
    } catch (CoreException ce) {
      DebugUIPlugin.log(ce);
    }
    return launchInBackground;
  }

  /**
   * Updates the favorites selections from the local configuration
   * 
   * @param config
   *          the local configuration
   */
  private void updateFavoritesFromConfig(ILaunchConfiguration config) {
    fFavoritesTable.setInput(config);
    fFavoritesTable.setCheckedElements(new Object[] {});
    try {
      List groups = config.getAttribute(IDebugUIConstants.ATTR_FAVORITE_GROUPS, new ArrayList());
      if (groups.isEmpty()) {
        // check old attributes for backwards compatible
        if (config.getAttribute(IDebugUIConstants.ATTR_DEBUG_FAVORITE, false)) {
          groups.add(IDebugUIConstants.ID_DEBUG_LAUNCH_GROUP);
        }
        if (config.getAttribute(IDebugUIConstants.ATTR_RUN_FAVORITE, false)) {
          groups.add(IDebugUIConstants.ID_RUN_LAUNCH_GROUP);
        }
      }
      if (!groups.isEmpty()) {
        List list = new ArrayList();
        Iterator iterator = groups.iterator();
        while (iterator.hasNext()) {
          String id = (String) iterator.next();
          LaunchGroupExtension extension = getLaunchConfigurationManager().getLaunchGroup(id);
          list.add(extension);
        }
        fFavoritesTable.setCheckedElements(list.toArray());
      }
    } catch (CoreException e) {
      DebugUIPlugin.log(e);
    }
  }

  /**
   * Updates the configuration form the local shared config working copy
   * 
   * @param config
   *          the local shared config working copy
   */
  private void updateConfigFromLocalShared(ILaunchConfigurationWorkingCopy config) {
    if (isShared()) {
      String containerPathString = fSharedLocationText.getText();
      IContainer container = getContainer(containerPathString);
      config.setContainer(container);
    } else {
      config.setContainer(null);
    }
  }

  /**
   * Convenience accessor
   */
  protected LaunchConfigurationManager getLaunchConfigurationManager() {
    return DebugUIPlugin.getDefault().getLaunchConfigurationManager();
  }

  /**
   * Update the favorite settings.
   * 
   * NOTE: set to <code>null</code> instead of <code>false</code> for backwards compatibility when
   * comparing if content is equal, since 'false' is default and will be missing for older
   * configurations.
   */
  private void updateConfigFromFavorites(ILaunchConfigurationWorkingCopy config) {
    try {
      Object[] checked = fFavoritesTable.getCheckedElements();
      boolean debug = config.getAttribute(IDebugUIConstants.ATTR_DEBUG_FAVORITE, false);
      boolean run = config.getAttribute(IDebugUIConstants.ATTR_RUN_FAVORITE, false);
      if (debug || run) {
        // old attributes
        List groups = new ArrayList();
        int num = 0;
        if (debug) {
          groups.add(getLaunchConfigurationManager().getLaunchGroup(
                  IDebugUIConstants.ID_DEBUG_LAUNCH_GROUP));
          num++;
        }
        if (run) {
          num++;
          groups.add(getLaunchConfigurationManager().getLaunchGroup(
                  IDebugUIConstants.ID_DEBUG_LAUNCH_GROUP));
        }
        // see if there are any changes
        if (num == checked.length) {
          boolean different = false;
          for (int i = 0; i < checked.length; i++) {
            if (!groups.contains(checked[i])) {
              different = true;
              break;
            }
          }
          if (!different) {
            return;
          }
        }
      }
      config.setAttribute(IDebugUIConstants.ATTR_DEBUG_FAVORITE, (String) null);
      config.setAttribute(IDebugUIConstants.ATTR_RUN_FAVORITE, (String) null);
      List groups = null;
      for (int i = 0; i < checked.length; i++) {
        LaunchGroupExtension group = (LaunchGroupExtension) checked[i];
        if (groups == null) {
          groups = new ArrayList();
        }
        groups.add(group.getIdentifier());
      }
      config.setAttribute(IDebugUIConstants.ATTR_FAVORITE_GROUPS, groups);
    } catch (CoreException e) {
      DebugUIPlugin.log(e);
    }
  }

  /**
   * Convenience method for getting the workspace root.
   */
  private IWorkspaceRoot getWorkspaceRoot() {
    return ResourcesPlugin.getWorkspace().getRoot();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration
   * )
   */
  @Override
  public boolean isValid(ILaunchConfiguration config) {
    setMessage(null);
    setErrorMessage(null);

    return validateLocalShared() && validateRedirectFile() && validateEncoding();
  }

  /**
   * validates the encoding selection
   * 
   * @return true if the validate encoding is allowable, false otherwise
   */
  private boolean validateEncoding() {
    if (fAltEncodingButton.getSelection()) {
      if (fEncodingCombo.getSelectionIndex() == -1) {
        // TODO constant was removed in Eclipse 3.7, find a substitute
        // setErrorMessage(LaunchConfigurationsMessages.CommonTab_No_Encoding_Selected);
        return false;
      }
    }
    return true;
  }

  /**
   * Validates if the redirect file is valid
   * 
   * @return true if the filename is not zero, false otherwise
   */
  private boolean validateRedirectFile() {
    if (fFileOutput.getSelection()) {
      int len = fFileText.getText().trim().length();
      if (len == 0) {
        setErrorMessage(LaunchConfigurationsMessages.CommonTab_8);
        return false;
      }
    }
    return true;
  }

  /**
   * validates the local shared config file location
   * 
   * @return true if the local shared file exists, false otherwise
   */
  private boolean validateLocalShared() {
    if (isShared()) {
      String path = fSharedLocationText.getText().trim();
      IContainer container = getContainer(path);
      if (container == null || container.equals(ResourcesPlugin.getWorkspace().getRoot())) {
        setErrorMessage(LaunchConfigurationsMessages.CommonTab_Invalid_shared_configuration_location_14);
        return false;
      } else if (!container.getProject().isOpen()) {
        setErrorMessage(LaunchConfigurationsMessages.CommonTab_Cannot_save_launch_configuration_in_a_closed_project__1);
        return false;
      }
    }

    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.
   * ILaunchConfigurationWorkingCopy)
   */
  public void setDefaults(ILaunchConfigurationWorkingCopy config) {
    config.setContainer(null);
    config.setAttribute(IDebugUIConstants.ATTR_LAUNCH_IN_BACKGROUND, true);
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.
   * ILaunchConfigurationWorkingCopy)
   */
  public void performApply(ILaunchConfigurationWorkingCopy configuration) {
    updateConfigFromLocalShared(configuration);
    updateConfigFromFavorites(configuration);
    setAttribute(IDebugUIConstants.ATTR_LAUNCH_IN_BACKGROUND, configuration,
            fLaunchInBackgroundButton.getSelection(), true);
    String encoding = null;
    if (fAltEncodingButton.getSelection()) {
      encoding = fEncodingCombo.getText();
    }
    configuration.setAttribute(IDebugUIConstants.ATTR_CONSOLE_ENCODING, encoding);

    boolean captureOutput = false;

    if (fConsoleOutput.getSelection()) {
      captureOutput = true;
      configuration.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_CONSOLE, (String) null);
    } else {
      configuration.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_CONSOLE, false);
    }

    if (fFileOutput.getSelection()) {
      captureOutput = true;
      String file = fFileText.getText();
      configuration.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE, file);
      if (fAppend.getSelection()) {
        configuration.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, true);
      } else {
        configuration.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, (String) null);
      }
    } else {
      configuration.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE, (String) null);
    }

    boolean useDltk = false;
    if (fUseDltkRadio.getSelection()) {
      configuration.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, (String) null);

      configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_CONSOLE_ID,
              Long.toString(System.currentTimeMillis()));
      IFileHandle proxyFile;
      try {
        IExecutionEnvironment exeEnv = (IExecutionEnvironment) EnvironmentManager
                .getLocalEnvironment().getAdapter(IExecutionEnvironment.class);
        proxyFile = RutaIdeUIPlugin.getDefault().getConsoleProxy(exeEnv);
        configuration.setAttribute("environmentId", proxyFile.getEnvironment().getId());
        configuration.setAttribute("proxy_path", proxyFile.toOSString());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      captureOutput = false;
      useDltk = true;
    }

    configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_USE_INTERACTIVE_CONSOLE,
            useDltk);

    // Last option
    if (captureOutput) {
      configuration.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, (String) null);
    } else {
      configuration.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, false);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
   */
  public String getName() {
    return LaunchConfigurationsMessages.CommonTab__Common_15;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.debug.ui.ILaunchConfigurationTab#canSave()
   */
  @Override
  public boolean canSave() {
    return validateLocalShared();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
   */
  @Override
  public Image getImage() {
    return DebugUITools.getImage(IInternalDebugUIConstants.IMG_OBJS_COMMON_TAB);
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.debug.ui.ILaunchConfigurationTab#activated(org.eclipse.debug.core.
   * ILaunchConfigurationWorkingCopy)
   */
  @Override
  public void activated(ILaunchConfigurationWorkingCopy workingCopy) {
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.debug.ui.ILaunchConfigurationTab#deactivated(org.eclipse.debug.core.
   * ILaunchConfigurationWorkingCopy)
   */
  @Override
  public void deactivated(ILaunchConfigurationWorkingCopy workingCopy) {
  }

  /**
   * Content provider for the favorites table
   */
  class FavoritesContentProvider implements IStructuredContentProvider {

    public Object[] getElements(Object inputElement) {
      ILaunchGroup[] groups = DebugUITools.getLaunchGroups();
      List possibleGroups = new ArrayList();
      ILaunchConfiguration configuration = (ILaunchConfiguration) inputElement;
      for (int i = 0; i < groups.length; i++) {
        ILaunchGroup extension = groups[i];
        LaunchHistory history = getLaunchConfigurationManager().getLaunchHistory(
                extension.getIdentifier());
        if (history != null && history.accepts(configuration)) {
          possibleGroups.add(extension);
        }
      }
      return possibleGroups.toArray();
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

  }

  /**
   * Provides the labels for the favorites table
   * 
   */
  class FavoritesLabelProvider implements ITableLabelProvider {

    private Map fImages = new HashMap();

    public Image getColumnImage(Object element, int columnIndex) {
      Image image = (Image) fImages.get(element);
      if (image == null) {
        ImageDescriptor descriptor = ((LaunchGroupExtension) element).getImageDescriptor();
        if (descriptor != null) {
          image = descriptor.createImage();
          fImages.put(element, image);
        }
      }
      return image;
    }

    public String getColumnText(Object element, int columnIndex) {
      String label = ((LaunchGroupExtension) element).getLabel();
      return DebugUIPlugin.removeAccelerators(label);
    }

    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {
      Iterator images = fImages.values().iterator();
      while (images.hasNext()) {
        Image image = (Image) images.next();
        image.dispose();
      }
    }

    public boolean isLabelProperty(Object element, String property) {
      return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }
  }

}
