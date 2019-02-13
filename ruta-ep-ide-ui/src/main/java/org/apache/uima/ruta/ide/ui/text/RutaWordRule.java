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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

public class RutaWordRule implements IPredicateRule {

  /** Internal setting for the un-initialized column constraint */
  protected static final int UNDEFINED = -1;

  /** The word detector used by this rule */
  protected IWordDetector fDetector;

  /**
   * The default token to be returned on success and if nothing else has been specified.
   */
  protected IToken fDefaultToken;

  /** The column constraint */
  protected int fColumn = UNDEFINED;

  /** The table of predefined words and token for this rule */
  protected Map fWords = new HashMap();

  /** Buffer used for pattern detection */
  private StringBuffer fBuffer = new StringBuffer();

  // private IToken classNameToken;

  // private IToken funcNameToken;

  /**
   * Creates a rule which, with the help of a word detector, will return the token associated with
   * the detected word. If no token has been associated, the specified default token will be
   * returned.
   * 
   * @param detector
   *          the word detector to be used by this rule, may not be <code>null</code>
   * @param defaultToken
   *          the default token to be returned on success if nothing else is specified, may not be
   *          <code>null</code>
   * @param classNameToken
   *          - not in use
   * 
   * @see #addWord(String, IToken)
   */
  public RutaWordRule(IWordDetector detector, IToken defaultToken, IToken classNameToken) {

    Assert.isNotNull(detector);
    Assert.isNotNull(defaultToken);

    fDetector = detector;
    fDefaultToken = defaultToken;
    // this.classNameToken = classNameToken;
    // this.funcNameToken = funcNameToken;
  }

  /**
   * Adds a word and the token to be returned if it is detected.
   * 
   * @param word
   *          the word this rule will search for, may not be <code>null</code>
   * @param token
   *          the token to be returned if the word has been found, may not be <code>null</code>
   */
  public void addWord(String word, IToken token) {
    Assert.isNotNull(word);
    Assert.isNotNull(token);

    fWords.put(word, token);
  }

  /**
   * Sets a column constraint for this rule. If set, the rule's token will only be returned if the
   * pattern is detected starting at the specified column. If the column is smaller then 0, the
   * column constraint is considered removed.
   * 
   * @param column
   *          the column in which the pattern starts
   */
  public void setColumnConstraint(int column) {
    if (column < 0)
      column = UNDEFINED;
    fColumn = column;
  }

  private String lastFound = "";

  @Override
  public IToken evaluate(ICharacterScanner scanner) {
    int c = scanner.read();
    fBuffer.setLength(0);

    // special for '->'
    if (c == '-') {
      fBuffer.append((char) c);
      c = scanner.read();
      if (c == '>') {
        fBuffer.append((char) c);
        String str = fBuffer.toString();
        IToken token = (IToken) fWords.get(str);
        if (token != null) {
          lastFound = str;
          return token;
        }
      } else {
        // scanner.unread();

        fBuffer.setLength(0);
      }
    }
    // the rest
    if (fDetector.isWordStart((char) c)) {
      if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {
        fBuffer.setLength(0);

        do {
          fBuffer.append((char) c);
          c = scanner.read();
        } while (c != ICharacterScanner.EOF && fDetector.isWordPart((char) c));
        scanner.unread();

        String str = fBuffer.toString();
        IToken token = (IToken) fWords.get(str);
        if (token != null) {
          lastFound = str;
          return token;
        }

        if (fDefaultToken.isUndefined())
          unreadBuffer(scanner);

        return fDefaultToken;
      }
    }

    scanner.unread();
    return Token.UNDEFINED;
  }

  protected void unreadBuffer(ICharacterScanner scanner) {
    for (int i = fBuffer.length() - 1; i >= 0; i--)
      scanner.unread();
  }

  @Override
  public IToken evaluate(ICharacterScanner scanner, boolean resume) {
    return evaluate(scanner);
  }

  @Override
  public IToken getSuccessToken() {
    return this.fDefaultToken;
  }
}
