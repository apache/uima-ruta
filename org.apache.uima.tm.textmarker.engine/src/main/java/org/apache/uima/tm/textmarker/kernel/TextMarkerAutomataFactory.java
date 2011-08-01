package org.apache.uima.tm.textmarker.kernel;

import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.cas.CAS;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;


public class TextMarkerAutomataFactory extends TextMarkerScriptFactory {

  public TextMarkerBlock createAutomataBlock(Token id, TextMarkerRuleElement re,
          List<TextMarkerStatement> body, TextMarkerBlock env, CAS cas) {
    return createScriptBlock(id, re, body, env, cas);
  }

}
