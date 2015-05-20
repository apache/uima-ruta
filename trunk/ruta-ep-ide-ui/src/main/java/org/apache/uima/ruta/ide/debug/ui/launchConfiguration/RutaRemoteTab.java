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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class RutaRemoteTab extends AbstractLaunchConfigurationTab {
  private static final int DEFAULT_PORT = 9000;

  private static final String DEFAULT_SESSION_ID = "ruta_debug";

  private static final int DEFAULT_TIMEOUT = 30 * 1000; // 30 min

  private Text portText;

  private Text sessionIdText;

  private Text timeoutText;

  private int getPort() {
    return Integer.parseInt(portText.getText());
  }

  private void setPort(int port) {
    portText.setText(Integer.toString(port));
  }

  private String getSessionId() {
    return sessionIdText.getText();
  }

  private void setSessionId(String id) {
    sessionIdText.setText(id);
  }

  private int getTimeout() {
    return Integer.parseInt(timeoutText.getText());
  }

  private void setTimeout(int timeout) {
    timeoutText.setText(Integer.toString(timeout));
  }

  protected void createInstruction(Composite parent, Object data) {
    Label instruction = new Label(parent, SWT.NONE);
    instruction.setLayoutData(data);
    instruction.setText("To start Ruta debugging engine use this command temlate:");
  }

  protected void createPathTemplate(Composite parent, Object data) {
  }

  protected void createConnectionPropertiesGroup(Composite parent, Object data) {
    Group group = new Group(parent, SWT.NONE);
    group.setText("Connection Properties");
    group.setLayoutData(data);

    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    group.setLayout(layout);

    // Local port
    Label portLabel = new Label(group, SWT.NONE);
    portLabel.setText("Local port:");

    portText = new Text(group, SWT.BORDER);
    portText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {

        updateLaunchConfigurationDialog();
      }
    });
    portText.setLayoutData(new GridData(GridData.FILL, SWT.NONE, true, false));

    // Id string
    Label idLabel = new Label(group, SWT.NONE);
    idLabel.setText("Connection id:");

    sessionIdText = new Text(group, SWT.BORDER);
    sessionIdText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        updateLaunchConfigurationDialog();
      }
    });

    sessionIdText.setLayoutData(new GridData(GridData.FILL, SWT.NONE, true, false));

    // Timeout
    Label timeoutLabel = new Label(group, SWT.NONE);
    timeoutLabel.setText("Waiting timeout:");

    timeoutText = new Text(group, SWT.BORDER);
    timeoutText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        updateLaunchConfigurationDialog();
      }
    });

    timeoutText.setLayoutData(new GridData(GridData.FILL, SWT.NONE, true, false));
  }

  public void createControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    setControl(composite);

    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    composite.setLayout(layout);

    createConnectionPropertiesGroup(composite, new GridData(GridData.FILL, SWT.NONE, true, false));

    createInstruction(composite, new GridData(GridData.FILL, SWT.NONE, true, false));

    createPathTemplate(composite, new GridData(GridData.FILL, SWT.NONE, true, false));
  }

  public String getName() {
    return "Ruta Remote Properties";
  }

  public void initializeFrom(ILaunchConfiguration configuration) {
    try {
      // Port
      int port = configuration.getAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT,
              -1);

      setPort(port != -1 ? port : DEFAULT_PORT);

      // Session id
      String sessionId = configuration.getAttribute(
              ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID, (String) null);

      setSessionId(sessionId != null ? sessionId : DEFAULT_SESSION_ID);

      // Timeout
      int timeout = configuration.getAttribute(
              ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT, -1);
      setTimeout(timeout != -1 ? timeout : DEFAULT_TIMEOUT);
    } catch (CoreException e) {
      // TODO: Log this
    }
  }

  public void performApply(ILaunchConfigurationWorkingCopy configuration) {
    try {
      setErrorMessage(null);

      configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_REMOTE, true);

      configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT, getPort());

      configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
              getSessionId());

      configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT,
              getTimeout());
    } catch (NumberFormatException e) {
      setErrorMessage("Should be a number instead of string");
    }
  }

  public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    configuration
            .setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT, DEFAULT_PORT);

    configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
            DEFAULT_SESSION_ID);

    configuration.setAttribute(ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_WAITING_TIMEOUT,
            DEFAULT_TIMEOUT);
  }
}
