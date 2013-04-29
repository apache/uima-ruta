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

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.launching.RutaLaunchConstants;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class RutaMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

  private Text inputText;

  private Button recursivelyButton;

  private Text outputFolderText;

  private Text viewText;

  public RutaMainLaunchConfigurationTab(String mode) {
    super(mode);
  }

  /*
   * @seeorg.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#
   * breakOnFirstLinePrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
   */
  @Override
  protected boolean breakOnFirstLinePrefEnabled(PreferencesLookupDelegate delegate) {
    return delegate.getBoolean(RutaIdePlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);
  }

  /*
   * @see
   * org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#dbpgLoggingPrefEnabled
   * (org.eclipse.dltk.core.PreferencesLookupDelegate)
   */
  @Override
  protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
    return delegate.getBoolean(RutaIdePlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
  }

  @Override
  public String getNatureID() {
    return RutaNature.NATURE_ID;
  }

  @Override
  protected void doCreateControl(Composite composite) {
    super.doCreateControl(composite);
    // Input Resource Group
    Group inputResourceGroup = new Group(composite, SWT.None);
    inputResourceGroup.setText("Input Folder:");

    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false)
            .applyTo(inputResourceGroup);

    GridLayout inputResourceGroupLayout = new GridLayout(4, false);
    inputResourceGroup.setLayout(inputResourceGroupLayout);

    inputText = new Text(inputResourceGroup, SWT.BORDER);
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(3,1).applyTo(inputText);
    inputText.addModifyListener(new ModifyListener() {

      public void modifyText(ModifyEvent event) {
        updateLaunchConfigurationDialog();
      }
    });
    Button browseInputResource = new Button(inputResourceGroup, SWT.NONE);
    browseInputResource.setText("Browse ...");
    browseInputResource.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(),
                new WorkbenchLabelProvider(), new WorkbenchContentProvider());
        dialog.setTitle("Select input folder");
        dialog.setMessage("Select input folder");

        dialog.setInput(getProject().getProject());
        dialog.setInitialSelection(getWorkspaceRoot().findMember(inputText.getText()));
        if (dialog.open() == IDialogConstants.OK_ID) {
          IResource resource = (IResource) dialog.getFirstResult();
          if (resource != null) {
            String fileLoc = resource.getFullPath().toString();
            inputText.setText(fileLoc);
          }
        }
      }
    });

    recursivelyButton = new Button(inputResourceGroup, SWT.CHECK);
    recursivelyButton.setText("Recursively");
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false)
            .applyTo(recursivelyButton);
    recursivelyButton.addSelectionListener(new SelectionListener() {

      public void widgetSelected(SelectionEvent event) {
        updateLaunchConfigurationDialog();
      }

      public void widgetDefaultSelected(SelectionEvent event) {
      }
    });

    
    
    Label viewLabel = new Label(inputResourceGroup, SWT.NONE);
    viewLabel.setText("View Name:");
    viewText = new Text(inputResourceGroup, SWT.BORDER);
    GridDataFactory.swtDefaults().hint(250, SWT.DEFAULT).align(SWT.LEFT, SWT.CENTER)
            .grab(true, false).applyTo(viewText);
    viewText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent event) {
        updateLaunchConfigurationDialog();
      }
    });

    Group outputFolderGroup = new Group(composite, SWT.None);
    outputFolderGroup.setText("Output Folder:");
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false)
            .applyTo(outputFolderGroup);
    GridLayout outputFolderGroupLayout = new GridLayout(2, false);
    outputFolderGroup.setLayout(outputFolderGroupLayout);
    outputFolderText = new Text(outputFolderGroup, SWT.BORDER);
    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false)
            .applyTo(outputFolderText);
    outputFolderText.addModifyListener(new ModifyListener() {

      public void modifyText(ModifyEvent event) {
        updateLaunchConfigurationDialog();
      }
    });

    Button browseOutputFolderButton = new Button(outputFolderGroup, SWT.NONE);
    browseOutputFolderButton.setText("Browse ...");
    browseOutputFolderButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        String currentContainerString = outputFolderText.getText();
        IContainer currentContainer = getContainer(currentContainerString);
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(),
                currentContainer, false, "Select output folder");
        dialog.showClosedProjects(false);
        dialog.open();
        Object[] results = dialog.getResult();
        if ((results != null) && (results.length > 0) && (results[0] instanceof IPath)) {
          IPath path = (IPath) results[0];
          String containerName = path.toOSString();
          outputFolderText.setText(containerName);
        }
      }
    });

  }

  private IContainer getContainer(String path) {
    Path containerPath = new Path(path);
    IResource resource = getWorkspaceRoot().findMember(containerPath);
    if (resource instanceof IContainer)
      return (IContainer) resource;

    return null;
  }

  @Override
  public void doInitializeForm(ILaunchConfiguration config) {
    super.doInitializeForm(config);
    
    
    IScriptProject proj = null;
    try {
      proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(config);
    } catch (CoreException e) {
      RutaIdePlugin.error(e);
    }
    String defaultInputLocation = RutaProjectUtils.getDefaultInputLocation();
    String defaultOutputLocation = RutaProjectUtils.getDefaultOutputLocation();
    IResource inputFolder = proj.getProject().findMember(defaultInputLocation);
    IResource outputFolder = proj.getProject().findMember(defaultOutputLocation);
    
    
    try {
      recursivelyButton.setSelection(config.getAttribute(RutaLaunchConstants.RECURSIVE, false));
    } catch (CoreException e) {
      recursivelyButton.setSelection(false);
    }
    
    try {
      viewText.setText(config.getAttribute(RutaLaunchConstants.VIEW, CAS.NAME_DEFAULT_SOFA));
    } catch (CoreException e) {
      viewText.setText(CAS.NAME_DEFAULT_SOFA);
    }
    
    try {
      inputText.setText(config.getAttribute(RutaLaunchConstants.INPUT_FOLDER, inputFolder.getFullPath().toPortableString()));
    } catch (CoreException e) {
      inputText.setText(inputFolder.getFullPath().toPortableString());
    }
    
    try {
      outputFolderText.setText(config.getAttribute(RutaLaunchConstants.OUTPUT_FOLDER, outputFolder.getFullPath().toPortableString()));
    } catch (CoreException e) {
      outputFolderText.setText(outputFolder.getFullPath().toPortableString());
    }
    
  }

  @Override
  public void doPerformApply(ILaunchConfigurationWorkingCopy config) {
    super.doPerformApply(config);
    
    config.setAttribute(RutaLaunchConstants.RECURSIVE, recursivelyButton.getSelection());
    config.setAttribute(RutaLaunchConstants.VIEW, viewText.getText());
    config.setAttribute(RutaLaunchConstants.INPUT_FOLDER, inputText.getText());
    config.setAttribute(RutaLaunchConstants.OUTPUT_FOLDER, outputFolderText.getText());
    
  }

  @Override
  public void setDefaults(ILaunchConfigurationWorkingCopy config) {
    super.setDefaults(config);
    IScriptProject proj = null;
    try {
      proj = AbstractScriptLaunchConfigurationDelegate.getScriptProject(config);
    } catch (CoreException e) {
      RutaIdePlugin.error(e);
    }
    String defaultInputLocation = RutaProjectUtils.getDefaultInputLocation();
    String defaultOutputLocation = RutaProjectUtils.getDefaultOutputLocation();
    IResource inputFolder = proj.getProject().findMember(defaultInputLocation);
    IResource outputFolder = proj.getProject().findMember(defaultOutputLocation);
    config.setAttribute(RutaLaunchConstants.RECURSIVE, false);
    config.setAttribute(RutaLaunchConstants.INPUT_FOLDER, inputFolder.getFullPath().toPortableString());
    config.setAttribute(RutaLaunchConstants.OUTPUT_FOLDER, outputFolder.getFullPath().toPortableString());
  }
}
