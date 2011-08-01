package org.apache.uima.tm.dltk.internal.testing;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.tm.dltk.internal.debug.ui.launchConfigurations.TextMarkerMainLaunchConfigurationTab;
import org.apache.uima.tm.dltk.testing.ITextMarkerTestingEngine;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.debug.ui.messages.DLTKLaunchConfigurationsMessages;
import org.eclipse.dltk.testing.DLTKTestingConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


public class TextMarkerTestingMainLaunchConfigurationTab extends
        TextMarkerMainLaunchConfigurationTab {
  public TextMarkerTestingMainLaunchConfigurationTab(String mode) {
    super(mode);
  }

  private Button detect;

  private Combo engineType;

  private Map nameToId = new HashMap();

  @Override
  protected void doCreateControl(Composite composite) {
    createMainModuleEditor(composite, DLTKLaunchConfigurationsMessages.mainTab_mainModule);
    createVerticalSpacer(composite, 1);
    createTestEngineEditor(composite, "TextMarker Testing engine");

  }

  protected void createTestEngineEditor(Composite parent, String text) {
    Font font = parent.getFont();
    Group mainGroup = new Group(parent, SWT.NONE);
    mainGroup.setText(text);
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    mainGroup.setLayoutData(gd);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    mainGroup.setLayout(layout);
    mainGroup.setFont(font);
    engineType = new Combo(mainGroup, SWT.SINGLE | SWT.BORDER | SWT.DROP_DOWN);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    engineType.setLayoutData(gd);
    engineType.setFont(font);
    engineType.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        updateLaunchConfigurationDialog();
      }
    });
    detect = createPushButton(mainGroup, "Detect", null);

    ITextMarkerTestingEngine[] engines = TextMarkerTestingEngineManager.getEngines();
    for (int i = 0; i < engines.length; i++) {
      String name = engines[i].getName();
      this.engineType.add(name);
      nameToId.put(name, engines[i].getId());
    }
    detect.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        handleDetectButtonSelected();
      }
    });
    handleDetectButtonSelected();
  }

  private void handleDetectButtonSelected() {
    ITextMarkerTestingEngine[] engines = TextMarkerTestingEngineManager.getEngines();
    // this.engineType.select(0);
    ISourceModule module = getSourceModule();
    if (module != null && module.exists()) {
      for (int i = 0; i < engines.length; i++) {
        if (engines[i].isValidModule(module)) {
          this.engineType.select(i);
        }
      }
    }
  }

  // private ISourceModule getSourceModule() {
  // IScriptProject project = this.getProject();
  // if (project == null) {
  // return null;
  // }
  // IProject prj = project.getProject();
  // String scriptName = this.getScriptName();
  // ISourceModule module = null;
  // IResource res = prj.getFile(scriptName);
  // module = (ISourceModule) DLTKCore.create(res);
  // return module;
  // }
  private boolean validateEngine() {
    ISourceModule module = getSourceModule();
    if (module != null) {
      ITextMarkerTestingEngine[] engines = TextMarkerTestingEngineManager.getEngines();
      for (int i = 0; i < engines.length; i++) {
        String selectedEngine = this.getEngineId();
        if (engines[i].getId().equals(selectedEngine) && engines[i].isValidModule(module)) {
          return true;
        }
      }
    }
    setErrorMessage("Testing engine not support specified script");
    return true;
  }

  @Override
  protected void doPerformApply(ILaunchConfigurationWorkingCopy config) {
    super.doPerformApply(config);
    config.setAttribute(DLTKTestingConstants.ATTR_ENGINE_ID, getEngineId());
  }

  private String getEngineId() {
    return (String) this.nameToId.get(this.engineType.getText());
  }

  @Override
  protected void doInitializeForm(ILaunchConfiguration config) {
    super.doInitializeForm(config);
    ITextMarkerTestingEngine[] engines = TextMarkerTestingEngineManager.getEngines();
    String id = null;
    try {
      id = config.getAttribute(DLTKTestingConstants.ATTR_ENGINE_ID, "");
    } catch (CoreException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    if (id == null || id.length() == 0) {
      handleDetectButtonSelected();
    } else {
      // this.engineType.select(0);
      for (int i = 0; i < engines.length; i++) {
        if (engines[i].getId().equals(id)) {
          this.engineType.select(i);
        }
      }
    }
    // handleDetectButtonSelected();
  }
}
