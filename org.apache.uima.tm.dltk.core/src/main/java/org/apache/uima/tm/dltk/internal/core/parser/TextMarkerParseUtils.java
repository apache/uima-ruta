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

package org.apache.uima.tm.dltk.internal.core.parser;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;


public class TextMarkerParseUtils {

  public static int endLineOrSymbol(int from, String content) {
    int pos = 0;
    for (pos = from; pos < content.length(); ++pos) {
      char c = content.charAt(pos);
      if (c == '\n' || c == '\r' || c == ';') {
        return pos;
      }
      if (!Character.isWhitespace(c)) {
        return pos;
      }
    }
    if (pos == content.length()) {
      return pos;
    }
    return from;
  }

  public static int startLineOrSymbol(int from, String content) {
    if (from == -1) {
      from = 0;
    }
    if (from >= content.length())
      from--;
    for (int pos = from - 1; pos > 0; --pos) {
      char c = content.charAt(pos);
      if (c == '\n' || c == '\r' || c == ';') {
        return pos + 1;
      }
      if (!Character.isWhitespace(c)) {
        return pos + 1;
      }
    }
    return from;
  }

  public static int endLineOrNoSymbol(int from, String content) {
    int pos = 0;
    if (from == -1) {
      from = 0;
    }
    if (from >= content.length())
      from--;
    for (pos = from; pos < content.length(); ++pos) {
      if (checkBounds(content, pos)) {
        return pos;
      }
    }
    if (pos == content.length()) {
      return pos;
    }
    return pos;
  }

  private static boolean checkBounds(String content, int pos) {
    char[] syms = { ' ', '\t', '\n', '\r', ']', '[', '}', '{', '(', ')' };
    char c = content.charAt(pos);
    for (int i = 0; i < syms.length; ++i) {
      if (syms[i] == c) {
        return true;
      }
    }
    return false;
  }

  public static int startLineOrNoSymbol(int from, String content) {
    if (from == -1) {
      from = 0;
    }
    if (from >= content.length())
      from--;
    int pos;
    for (pos = from; pos > 0; --pos) {
      if (checkBounds(content, pos)) {
        return pos + 1;
      }
    }
    return pos;
  }

  public static String[] returnVariable(Statement node) {
    // TODO Auto-generated method stub
    return null;
  }

  public static SimpleReference extractVariableFromString(int sourceStart, int sourceEnd, int i,
          String name) {
    // TODO Auto-generated method stub
    return null;
  }

  public static SimpleReference extractVariableFromString(StringLiteral completionNode, int pos) {
    // TODO Auto-generated method stub
    return null;
  }

  public static ASTNode getScopeParent(ModuleDeclaration module, Expression s) {
    // TODO Auto-generated method stub
    return null;
  }

  public static String processFieldName(IField field, String token) {
    // TODO Auto-generated method stub
    return null;
  }

  public static String processMethodName(IMethod method, String token) {
    // TODO Auto-generated method stub
    return null;
  }

  public static String processTypeName(IType method, String token) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * @param token
   * @return (start, end)
   * @throws IllegalArgumentException
   *           when token==null or !(token instanceof CommonToken)
   */
  public static final int[] getBounds(Token token) throws IllegalArgumentException {
    if (token == null) {
      throw new IllegalArgumentException();
    }
    if (!(token instanceof CommonToken)) {
      throw new IllegalArgumentException();
    }
    CommonToken ct = (CommonToken) token;
    int[] bounds = { ct.getStartIndex(), ct.getStopIndex() + 1 };
    return bounds;
  }

  /**
   * @param tokenA
   *          startToken
   * @param tokenB
   *          endToken
   * @return positions of a.start // b.end
   * @throws IllegalArgumentException
   *           when some token is null or not instanceof CommonToken
   */
  public static final int[] getBounds(Token tokenA, Token tokenB) throws IllegalArgumentException {
    if (!((tokenA instanceof CommonToken) && (tokenB instanceof CommonToken || tokenB == null))) {
      throw new IllegalArgumentException();
    }
    CommonToken ctA = (CommonToken) tokenA;
    if (tokenB == null) {
      int[] bounds = { ctA.getStartIndex(), ctA.getStopIndex() + 1 };
      return bounds;
    } else {
      CommonToken ctB = (CommonToken) tokenB;
      int[] bounds = { ctA.getStartIndex(), ctB.getStopIndex() + 1 };
      return bounds;
    }
  }

  /**
   * @param member
   * @return see {@link TMTypeConstants}, -1 if not valid
   */
  public static final int getTypeOfIModelElement(IMember member) {
    int type;
    try {
      type = member.getFlags();
    } catch (ModelException e) {
      return -1;
    }
    type &= TMTypeConstants.TM_TYPE_BITMASK;
    return type;
  }
}
