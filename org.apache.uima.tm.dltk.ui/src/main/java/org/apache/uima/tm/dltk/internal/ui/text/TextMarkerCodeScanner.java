package org.apache.uima.tm.dltk.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.core.ITextMarkerKeywords;
import org.apache.uima.tm.dltk.core.TextMarkerKeywordsManager;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;


public class TextMarkerCodeScanner extends AbstractScriptScanner {

  private static String fgTokenProperties[] = new String[] {
      TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT, TextMarkerColorConstants.TM_DEFAULT,
      TextMarkerColorConstants.TM_KEYWORD, TextMarkerColorConstants.TM_KEYWORD_RETURN,
      TextMarkerColorConstants.TM_NUMBER, TextMarkerColorConstants.TM_FUNCTION,
      TextMarkerColorConstants.TM_CONDITION, TextMarkerColorConstants.TM_ACTION,
      TextMarkerColorConstants.TM_RULE, TextMarkerColorConstants.TM_DECLARATION,
      TextMarkerColorConstants.TM_BASICSYMBOL, TextMarkerColorConstants.TM_THEN };

  public TextMarkerCodeScanner(IColorManager manager, IPreferenceStore store) {
    super(manager, store);
    initialize();
  }

  @Override
  protected String[] getTokenProperties() {
    return fgTokenProperties;
  }

  @Override
  protected List createRules() {
    List<IRule> rules = new ArrayList<IRule>();
    IToken keyword = getToken(TextMarkerColorConstants.TM_KEYWORD);
    IToken rule = getToken(TextMarkerColorConstants.TM_RULE);
    IToken comment = getToken(TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT);
    IToken other = getToken(TextMarkerColorConstants.TM_DEFAULT);
    IToken declaration = getToken(TextMarkerColorConstants.TM_DECLARATION);
    IToken basicSymbol = getToken(TextMarkerColorConstants.TM_BASICSYMBOL);
    IToken function = getToken(TextMarkerColorConstants.TM_FUNCTION);
    IToken condition = getToken(TextMarkerColorConstants.TM_CONDITION);
    IToken action = getToken(TextMarkerColorConstants.TM_ACTION);
    IToken then = getToken(TextMarkerColorConstants.TM_THEN);
    IToken number = getToken(TextMarkerColorConstants.TM_NUMBER);
    IToken string = getToken(TextMarkerColorConstants.TM_STRING);

    // rules.add(new MultiLineRule("/*", "*/", comment, '\\', true));
    // rules.add(new EndOfLineRule("//", comment));
    rules.add(new WhitespaceRule(new TextMarkerWhitespaceDetector()));
    TextMarkerWordRule wordRule = new TextMarkerWordRule(new TextMarkerWordDetector(), other, rule);

    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.CONDITION)) {
      wordRule.addWord(each, condition);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.DECLARATION)) {
      wordRule.addWord(each, declaration);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.ACTION)) {
      wordRule.addWord(each, action);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.BASIC)) {
      wordRule.addWord(each, basicSymbol);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.BOOLEANFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.NUMBERFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.STRINGFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.TYPEFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.THEN)) {
      wordRule.addWord(each, then);
    }
    rules.add(wordRule);

    setDefaultReturnToken(other);
    return rules;
  }
}
