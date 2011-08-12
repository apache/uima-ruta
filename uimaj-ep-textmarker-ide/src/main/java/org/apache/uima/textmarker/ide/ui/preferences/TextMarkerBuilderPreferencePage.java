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

package org.apache.uima.textmarker.ide.ui.preferences;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TextMarkerBuilderPreferencePage extends AbstractConfigurationBlockPreferencePage {
  @Override
  protected String getHelpId() {

    return null;
  }

  @Override
  protected void setDescription() {
    setDescription("Builder");
  }

  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerIdePlugin.getDefault().getPreferenceStore());
  }

  @Override
  protected Label createDescriptionLabel(Composite parent) {
    return null;
  }

  @Override
  protected IPreferenceConfigurationBlock createConfigurationBlock(
          OverlayPreferenceStore overlayPreferenceStore) {
    overlayPreferenceStore.addPropertyChangeListener(new IPropertyChangeListener() {

      public void propertyChange(PropertyChangeEvent event) {
        String property = event.getProperty();
        Object newValue = event.getNewValue();
        if (newValue instanceof Boolean) {
          TextMarkerIdePlugin.getDefault().getPluginPreferences()
                  .setValue(property, (Boolean) newValue);
        }
      }
    });
    return new TextMarkerBuilderConfigurationBlock(this, overlayPreferenceStore,
            TextMarkerNature.NATURE_ID);
  }
}
