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

package org.apache.uima.tm.dltk.internal.testing;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.testing.ITextMarkerTestingEngine;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.internal.testing.util.ExceptionHandler;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.testing.DLTKTestingConstants;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


public class TextMarkerTestingLaunchShortcut implements ILaunchShortcut {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  /**
   * Default constructor.
   */
  public TextMarkerTestingLaunchShortcut() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.uniwue.debug.ui.ILaunchShortcut#launch(de.uniwue.ui.IEditorPart, java.lang.String)
   */
  public void launch(IEditorPart editor, String mode) {
    IModelElement element = DLTKUIPlugin.getEditorInputModelElement(editor.getEditorInput());
    if (element != null) {
      launch(new Object[] { element }, mode);
    } else {
      showNoTestsFoundDialog();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.uniwue.debug.ui.ILaunchShortcut#launch(de.uniwue.jface.viewers.ISelection,
   * java.lang.String)
   */
  public void launch(ISelection selection, String mode) {
    if (selection instanceof IStructuredSelection) {
      launch(((IStructuredSelection) selection).toArray(), mode);
    } else {
      showNoTestsFoundDialog();
    }
  }

  private void launch(Object[] elements, String mode) {
    try {
      IModelElement elementToLaunch = null;

      if (elements.length == 1) {
        Object selected = elements[0];
        if (selected instanceof IFolder) {
          performLaunch((IFolder) selected, mode);
          return;
        }
        if (!(selected instanceof IModelElement) && selected instanceof IAdaptable) {
          selected = ((IAdaptable) selected).getAdapter(IModelElement.class);
        }
        if (selected instanceof IModelElement) {
          IModelElement element = (IModelElement) selected;
          switch (element.getElementType()) {
            case IModelElement.SCRIPT_PROJECT: {
              IProject project = ((IScriptProject) element).getProject();
              IFolder specFolder = project.getFolder("test");
              if (specFolder != null && specFolder.exists()) {
                performLaunch(specFolder, mode);
                return;
              }
            }
              break;
            case IModelElement.PROJECT_FRAGMENT:
            case IModelElement.SCRIPT_FOLDER: {
              performLaunch((IFolder) element.getResource(), mode);
              return;
            }
            case IModelElement.SOURCE_MODULE:
            case IModelElement.METHOD:
              elementToLaunch = element;
              break;
          }
        }
      }
      if (elementToLaunch == null) {
        showNoTestsFoundDialog();
        return;
      }
      performLaunch(elementToLaunch, mode);
    } catch (InterruptedException e) {
      // OK, silently move on
    } catch (CoreException e) {
      ExceptionHandler.handle(e, getShell(), "XUnit Launch",
              "Launching of XUnit tests unexpectedly failed. Check log for details.");
    }
  }

  private void showNoTestsFoundDialog() {
    MessageDialog.openInformation(getShell(), "XUnit Launch", "No XUnit tests found.");
  }

  private void performLaunch(IModelElement element, String mode) throws InterruptedException,
          CoreException {
    ILaunchConfigurationWorkingCopy temparary = createLaunchConfiguration(element);
    if (temparary == null) {
      return;
    }
    ILaunchConfiguration config = findExistingLaunchConfiguration(temparary, mode);
    if (config == null) {
      // no existing found: create a new one
      config = temparary.doSave();
    }
    DebugUITools.launch(config, mode);
  }

  private void performLaunch(IFolder folder, String mode) throws InterruptedException,
          CoreException {
    String name = folder.getName();
    String testName = name.substring(name.lastIndexOf(IPath.SEPARATOR) + 1);

    ILaunchConfigurationType configType = getLaunchManager().getLaunchConfigurationType(
            getLaunchConfigurationTypeId());
    ILaunchConfigurationWorkingCopy wc = configType.newInstance(null, getLaunchManager()
            .generateUniqueLaunchConfigurationNameFrom(testName));

    wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME, folder.getProject()
            .getName());

    // wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_TEST_NAME, EMPTY_STRING);
    // wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_CONTAINER_PATH,
    // folder.getFullPath().toPortableString());
    // wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_TEST_ELEMENT_NAME, EMPTY_STRING);

    ILaunchConfiguration config = findExistingLaunchConfiguration(wc, mode);
    if (config == null) {
      // no existing found: create a new one
      config = wc.doSave();
    }
    DebugUITools.launch(config, mode);
  }

  private IType chooseType(IType[] types, String mode) throws InterruptedException {
    ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(),
            new ModelElementLabelProvider(ModelElementLabelProvider.SHOW_POST_QUALIFIED));
    dialog.setElements(types);
    dialog.setTitle("Test Selection");
    if (mode.equals(ILaunchManager.DEBUG_MODE)) {
      dialog.setMessage("Select Test to debug");
    } else {
      dialog.setMessage("Select Test to run");
    }
    dialog.setMultipleSelection(false);
    if (dialog.open() == Window.OK) {
      return (IType) dialog.getFirstResult();
    }
    throw new InterruptedException(); // cancelled by user
  }

  private Shell getShell() {
    return DLTKUIPlugin.getActiveWorkbenchShell();
  }

  private ILaunchManager getLaunchManager() {
    return DebugPlugin.getDefault().getLaunchManager();
  }

  /**
   * Show a selection dialog that allows the user to choose one of the specified launch
   * configurations. Return the chosen config, or <code>null</code> if the user cancelled the
   * dialog.
   * 
   * @param configList
   * @param mode
   * @return ILaunchConfiguration
   * @throws InterruptedException
   */
  private ILaunchConfiguration chooseConfiguration(List configList, String mode)
          throws InterruptedException {
    IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
    ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
    dialog.setElements(configList.toArray());
    dialog.setTitle("Select a Test Configuration");
    if (mode.equals(ILaunchManager.DEBUG_MODE)) {
      dialog.setMessage("Select configuration to debug");
    } else {
      dialog.setMessage("Select configuration to run");
    }
    dialog.setMultipleSelection(false);
    int result = dialog.open();
    if (result == Window.OK) {
      return (ILaunchConfiguration) dialog.getFirstResult();
    }
    throw new InterruptedException(); // cancelled by user
  }

  /**
   * Returns the launch configuration type id of the launch configuration this shortcut will create.
   * Clients can override this method to return the id of their launch configuration.
   * 
   * @return the launch configuration type id of the launch configuration this shortcut will create
   */
  protected String getLaunchConfigurationTypeId() {
    return "org.apache.uima.tm.dltk.testing.launchConfig";
  }

  /**
   * Creates a launch configuration working copy for the given element. The launch configuration
   * type created will be of the type returned by {@link #getLaunchConfigurationTypeId}. The element
   * type can only be of type {@link IJavaProject}, {@link IPackageFragmentRoot},
   * {@link IPackageFragment}, {@link IType} or {@link IMethod}.
   * 
   * Clients can extend this method (should call super) to configure additional attributes on the
   * launch configuration working copy.
   * 
   * @return a launch configuration working copy for the given element
   */
  protected ILaunchConfigurationWorkingCopy createLaunchConfiguration(IModelElement element)
          throws CoreException {
    String testFileName;
    String containerHandleId;
    String testElementName;

    String name = ScriptElementLabels.getDefault().getTextLabel(element,
            ScriptElementLabels.F_FULLY_QUALIFIED);
    String testName = name.substring(name.lastIndexOf(IPath.SEPARATOR) + 1);

    switch (element.getElementType()) {
      case IModelElement.SOURCE_MODULE: {
        containerHandleId = EMPTY_STRING;
        testFileName = element.getResource().getProjectRelativePath().toPortableString();
        testElementName = EMPTY_STRING;
      }
        break;
      case IModelElement.METHOD: {
        containerHandleId = EMPTY_STRING;
        testFileName = element.getResource().getProjectRelativePath().toPortableString();
        testElementName = element.getElementName();
        // testName+= "[" + testElementName + "]";
      }
        break;
      default:
        throw new IllegalArgumentException(
                "Invalid element type to create a launch configuration: " + element.getClass().getName()); //$NON-NLS-1$
    }

    ILaunchConfigurationType configType = getLaunchManager().getLaunchConfigurationType(
            getLaunchConfigurationTypeId());
    ILaunchConfigurationWorkingCopy wc = configType.newInstance(null, getLaunchManager()
            .generateUniqueLaunchConfigurationNameFrom(testName));

    wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME, element
            .getScriptProject().getElementName());
    // wc.setAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND, "#");
    wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME, testFileName);
    wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE,
            TextMarkerNature.NATURE_ID);
    wc.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_CONSOLE, "true");
    // wc.setAttribute(XUnitLaunchConfigurationConstants.ATTR_TEST_NAME, testFileName);
    // wc.setAttribute(ScriptLaunchConfigurationConstants.ATTR_CONTAINER_PATH, containerHandleId);
    // wc.setAttribute(XUnitLaunchConfigurationConstants.ATTR_TEST_ELEMENT_NAME, testElementName);
    // XUnitMigrationDelegate.mapResources(wc);
    ITextMarkerTestingEngine[] engines = TextMarkerTestingEngineManager.getEngines();
    ISourceModule module = (ISourceModule) element.getAncestor(IModelElement.SOURCE_MODULE);
    boolean engineFound = false;
    for (int i = 0; i < engines.length; i++) {
      if (engines[i].isValidModule(module)) {// TODO!!!
        wc.setAttribute(DLTKTestingConstants.ATTR_ENGINE_ID, engines[i].getId());
        engineFound = true;
        break;
      }
    }
    // if( engineFound == false ) {
    // return null;
    // }

    return wc;
  }

  /**
   * Returns the attribute names of the attributes that are compared when looking for an existing
   * similar launch configuration. Clients can override and replace to customize.
   * 
   * @return the attribute names of the attributes that are compared
   */
  protected String[] getAttributeNamesToCompare() {
    return new String[] { ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME,
        ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME,
        // IDLTKTestingConstants.ENGINE_ID_ATR,
        ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE
    // XUnitLaunchConfigurationConstants.ATTR_TEST_NAME,
    // XUnitLaunchConfigurationConstants.ATTR_TEST_CONTAINER,
    // XUnitLaunchConfigurationConstants.ATTR_TEST_ELEMENT_NAME
    };
  }

  private static boolean hasSameAttributes(ILaunchConfiguration config1,
          ILaunchConfiguration config2, String[] attributeToCompare) {
    try {
      for (int i = 0; i < attributeToCompare.length; i++) {
        String val1 = config1.getAttribute(attributeToCompare[i], EMPTY_STRING);
        String val2 = config2.getAttribute(attributeToCompare[i], EMPTY_STRING);
        if (!val1.equals(val2)) {
          return false;
        }
      }
      return true;
    } catch (CoreException e) {
      // ignore access problems here, return false
    }
    return false;
  }

  private ILaunchConfiguration findExistingLaunchConfiguration(
          ILaunchConfigurationWorkingCopy temporary, String mode) throws InterruptedException,
          CoreException {
    ILaunchConfigurationType configType = temporary.getType();

    ILaunchConfiguration[] configs = getLaunchManager().getLaunchConfigurations(configType);
    String[] attributeToCompare = getAttributeNamesToCompare();

    ArrayList candidateConfigs = new ArrayList(configs.length);
    for (int i = 0; i < configs.length; i++) {
      ILaunchConfiguration config = configs[i];
      if (hasSameAttributes(config, temporary, attributeToCompare)) {
        candidateConfigs.add(config);
      }
    }

    // If there are no existing configs associated with the IType, create
    // one.
    // If there is exactly one config associated with the IType, return it.
    // Otherwise, if there is more than one config associated with the
    // IType, prompt the
    // user to choose one.
    int candidateCount = candidateConfigs.size();
    if (candidateCount == 0) {
      return null;
    } else if (candidateCount == 1) {
      return (ILaunchConfiguration) candidateConfigs.get(0);
    } else {
      // Prompt the user to choose a config. A null result means the user
      // cancelled the dialog, in which case this method returns null,
      // since cancelling the dialog should also cancel launching
      // anything.
      ILaunchConfiguration config = chooseConfiguration(candidateConfigs, mode);
      if (config != null) {
        return config;
      }
    }
    return null;
  }

}
