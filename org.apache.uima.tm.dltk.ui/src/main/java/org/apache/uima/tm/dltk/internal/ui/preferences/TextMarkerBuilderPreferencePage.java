package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
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
    setPreferenceStore(TextMarkerUI.getDefault().getPreferenceStore());
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
          TextMarkerPlugin.getDefault().getPluginPreferences().setValue(property,
                  (Boolean) newValue);
        }
      }
    });
    return new TextMarkerBuilderConfigurationBlock(this, overlayPreferenceStore,
            TextMarkerNature.NATURE_ID);
  }
}
