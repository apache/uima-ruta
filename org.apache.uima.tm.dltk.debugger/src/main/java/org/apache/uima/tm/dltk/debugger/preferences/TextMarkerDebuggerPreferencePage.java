package org.apache.uima.tm.dltk.debugger.preferences;

import org.apache.uima.tm.dltk.debugger.TextMarkerDebuggerPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


public class TextMarkerDebuggerPreferencePage extends
        AbstractConfigurationBlockPropertyAndPreferencePage {

  private static String PREFERENCE_PAGE_ID = "org.apache.uima.tm.dltk.textmarker.preferences.debug.engines.debugger";

  private static String PROPERTY_PAGE_ID = "org.apache.uima.tm.dltk.textmarker.propertyPage.debug.engines.debugger";

  /*
   * @seeorg.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#
   * createOptionsBlock(org.eclipse.dltk.ui.util.IStatusChangeListener,
   * org.eclipse.core.resources.IProject, org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
   */
  @Override
  protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
          IProject project, IWorkbenchPreferenceContainer container) {
    return new AbstractOptionsBlock(newStatusChangedListener, project, new PreferenceKey[] {},
            container) {

      @Override
      protected Control createOptionsBlock(Composite parent) {
        Composite composite = SWTFactory.createComposite(parent, parent.getFont(), 1, 1,
                GridData.FILL);
        SWTFactory.createLabel(composite, PreferenceMessages.NoSettingsAvailable, 1);
        return composite;
      }
    };
  }

  /*
   * @see
   * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
   */
  @Override
  protected String getHelpId() {
    return null;
  }

  /*
   * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
   */
  @Override
  protected String getPreferencePageId() {
    return PREFERENCE_PAGE_ID;
  }

  /*
   * @seeorg.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#
   * getProjectHelpId()
   */
  @Override
  protected String getProjectHelpId() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
   */
  @Override
  protected String getPropertyPageId() {
    return PROPERTY_PAGE_ID;
  }

  /*
   * @see
   * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setDescription
   * ()
   */
  @Override
  protected void setDescription() {
    setDescription(PreferenceMessages.PreferencesDescription);
  }

  /*
   * @seeorg.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#
   * setPreferenceStore()
   */
  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerDebuggerPlugin.getDefault().getPreferenceStore());
  }
}
