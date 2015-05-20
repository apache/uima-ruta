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

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class RutaStringScanner extends AbstractScriptScanner {

  private static final String[] tokenProperties = new String[] { RutaColorConstants.RUTA_STRING,
      RutaColorConstants.RUTA_NUMBER, RutaColorConstants.RUTA_VARIABLE };

  public RutaStringScanner(IColorManager manager, IPreferenceStore store) {
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
    rules.add(new WhitespaceRule(new RutaWhitespaceDetector()));

    setDefaultReturnToken(getToken(RutaColorConstants.RUTA_STRING));

    return rules;
  }

}
