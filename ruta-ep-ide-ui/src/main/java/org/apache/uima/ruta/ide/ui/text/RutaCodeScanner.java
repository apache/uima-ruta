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

package org.apache.uima.ruta.ide.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.ide.core.IRutaKeywords;
import org.apache.uima.ruta.ide.core.RutaKeywordsManager;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class RutaCodeScanner extends AbstractScriptScanner {

  private static String fgTokenProperties[] = new String[] {
      RutaColorConstants.RUTA_SINGLE_LINE_COMMENT, RutaColorConstants.RUTA_DEFAULT,
      RutaColorConstants.RUTA_DEFAULT_ECLIPSE, RutaColorConstants.RUTA_KEYWORD,
      RutaColorConstants.RUTA_KEYWORD_RETURN, RutaColorConstants.RUTA_NUMBER,
      RutaColorConstants.RUTA_FUNCTION, RutaColorConstants.RUTA_CONDITION,
      RutaColorConstants.RUTA_ACTION, RutaColorConstants.RUTA_RULE,
      RutaColorConstants.RUTA_DECLARATION, RutaColorConstants.RUTA_BASICSYMBOL,
      RutaColorConstants.RUTA_THEN };

  public RutaCodeScanner(IColorManager manager, IPreferenceStore store) {
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
    IToken keyword = getToken(RutaColorConstants.RUTA_KEYWORD);
    IToken rule = getToken(RutaColorConstants.RUTA_RULE);
    IToken comment = getToken(RutaColorConstants.RUTA_SINGLE_LINE_COMMENT);
//    IToken other = getToken(RutaColorConstants.RUTA_DEFAULT);
    IToken defaultToken = getToken(RutaColorConstants.RUTA_DEFAULT_ECLIPSE);
    IToken declaration = getToken(RutaColorConstants.RUTA_DECLARATION);
    IToken basicSymbol = getToken(RutaColorConstants.RUTA_BASICSYMBOL);
    IToken function = getToken(RutaColorConstants.RUTA_FUNCTION);
    IToken condition = getToken(RutaColorConstants.RUTA_CONDITION);
    IToken action = getToken(RutaColorConstants.RUTA_ACTION);
    IToken then = getToken(RutaColorConstants.RUTA_THEN);
    IToken number = getToken(RutaColorConstants.RUTA_NUMBER);
    IToken string = getToken(RutaColorConstants.RUTA_STRING);

    // rules.add(new MultiLineRule("/*", "*/", comment, '\\', true));
    // rules.add(new EndOfLineRule("//", comment));
    rules.add(new WhitespaceRule(new RutaWhitespaceDetector()));
    RutaWordRule wordRule = new RutaWordRule(new RutaWordDetector(), defaultToken, rule);

    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.CONDITION)) {
      wordRule.addWord(each, condition);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.DECLARATION)) {
      wordRule.addWord(each, declaration);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.ACTION)) {
      wordRule.addWord(each, action);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.BASIC)) {
      wordRule.addWord(each, basicSymbol);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.BOOLEANFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.NUMBERFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.STRINGFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.TYPEFUNCTION)) {
      wordRule.addWord(each, function);
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.THEN)) {
      wordRule.addWord(each, then);
    }
    rules.add(wordRule);

    setDefaultReturnToken(defaultToken);
    return rules;
  }
}
