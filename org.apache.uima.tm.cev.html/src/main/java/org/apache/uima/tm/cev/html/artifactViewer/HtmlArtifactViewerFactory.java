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

package org.apache.uima.tm.cev.html.artifactViewer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.AbstractArtifactViewerFactory;
import org.apache.uima.tm.cev.extension.ICEVArtifactViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class HtmlArtifactViewerFactory extends AbstractArtifactViewerFactory {

  private final static Pattern tagPattern = Pattern.compile("</?(\\w+)([^>]*)>");

  @Override
  public ICEVArtifactViewer createArtifactViewer(CEVViewer viewer, CTabItem tabItem, CEVData casData) {
    Composite composite = new Composite(tabItem.getParent(), SWT.NONE);
    FillLayout layout = new FillLayout();
    composite.setLayout(layout);
    Browser browser = new Browser(composite, SWT.MOZILLA);
    HtmlArtifactViewer artifactViewer = new HtmlArtifactViewer(browser, viewer);
    try {
      browser.addMouseListener(viewer);
      CEVDataHtmlExtension extension = new CEVDataHtmlExtension(casData);
      extension.setBrowser(browser, artifactViewer);
      extension.initialize();
      if (casData.getHTMLSource() != null) {
        browser.setText(casData.getHTMLSource());
      }
      browser.addProgressListener(extension);

    } catch (SWTError e) {
      CEVPlugin.error(e);
    }
    tabItem.setControl(composite);
    tabItem.setText("Browser");
    return artifactViewer;
  }

  public boolean isAble(CAS cas) {
    String text = cas.getDocumentText();
    if (text == null) {
      return false;
    }
    if (text.split("<html|<HTML|<table|<TABLE").length <= 1) {
      return false;
    }
    Matcher m = tagPattern.matcher(text);
    if (m.find()) {
      return true;
    }
    return false;
  }

}
