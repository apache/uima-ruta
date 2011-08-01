package org.apache.uima.tm.dltk.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class TextMarkerStringScanner extends AbstractScriptScanner {

  private static final String[] tokenProperties = new String[] {
      TextMarkerColorConstants.TM_STRING, TextMarkerColorConstants.TM_NUMBER,
      TextMarkerColorConstants.TM_VARIABLE };

  public TextMarkerStringScanner(IColorManager manager, IPreferenceStore store) {
    super(manager, store);

    initialize();
  }

  @Override
  protected String[] getTokenProperties() {
    return tokenProperties;
  }

  @Override
  protected List createRules() {
    List/* <IRule> */rules = new ArrayList/* <IRule> */();

    // Add generic whitespace rule.
    rules.add(new WhitespaceRule(new TextMarkerWhitespaceDetector()));

    setDefaultReturnToken(getToken(TextMarkerColorConstants.TM_STRING));

    return rules;
  }

}
