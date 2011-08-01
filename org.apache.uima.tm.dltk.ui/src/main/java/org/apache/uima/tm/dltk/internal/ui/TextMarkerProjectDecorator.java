package org.apache.uima.tm.dltk.internal.ui;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.eclipse.dltk.ui.AbstractScriptProjectDecorator;
import org.eclipse.jface.resource.ImageDescriptor;


public class TextMarkerProjectDecorator extends AbstractScriptProjectDecorator {

  /*
   * @see org.eclipse.dltk.ui.AbstractScriptProjectDecorator#getNatureId()
   */
  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  /*
   * @see org.eclipse.dltk.ui.AbstractScriptProjectDecorator#getProjectDecorator()
   */
  @Override
  protected ImageDescriptor getProjectDecorator() {
    return TextMarkerImages.PROJECT_DECARATOR;
  }
}