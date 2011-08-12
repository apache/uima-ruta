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

package org.apache.uima.textmarker.ide.core;

public class TextMarkerKeywords implements ITextMarkerKeywords {

  private static String[] condition = { "CONTAINS", "IF", "INLIST", "ISINTAG", "PARTOF",
      "TOTALCOUNT", "CURRENTCOUNT", "CONTEXTCOUNT", "LAST", "VOTE", "COUNT", "NEAR", "REGEXP",
      "POSITION", "SCORE", "ISLISTEMPTY", "MOFN", "AND", "OR", "FEATURE", "PARSE", "IS", "BEFORE",
      "AFTER", "STARTSWITH", "ENDSWITH", "PARTOFNEQ", "SIZE" };

  private static String[] declaration = { "WORDLIST", "DECLARE", "BOOLEAN", "PACKAGE", "TYPE",
      "TYPESYSTEM", "INT", "DOUBLE", "STRING", "SCRIPT", "WORDTABLE", "ENGINE", "ACTION",
      "CONDITION", "BLOCK", "RULES", "BOOLEANLIST", "INTLIST", "DOUBLELIST", "STRINGLIST",
      "TYPELIST" };

  private static String[] action = { "DEL", "CALL", "MARK", "MARKSCORE", "COLOR", "LOG", "TAG",
      "REPLACE", "FILLOBJECT", "RETAINMARKUP", "RETAINTYPE", "SETFEATURE", "ASSIGN", "PUTINLIST",
      "ATTRIBUTE", "MARKFAST", "FILTERTYPE", "FILTERMARKUP", "CREATE", "FILL", "MARKTABLE",
      "UNMARK", "TRANSFER", "MARKONCE", "TRIE", "GATHER", "EXEC", "MARKLAST", "ADD", "REMOVE",
      "MERGE", "GET", "GETLIST", "REMOVEDUPLICATE", "GETFEATURE", "MATCHEDTEXT", "CLEAR",
      "UNMARKALL", "EXPAND" };

  private static String[] basic = { "ALL", "ANY", "AMP", "BREAK", "W", "NUM", "PM", "Document",
      "MARKUP", "SW", "CW", "CAP", "PERIOD", "NBSP", "SENTENCEEND", "COLON", "COMMA", "SEMICOLON",
      "WS", "_", "SPACE", "SPECIAL", "EXCLAMATION", "QUESTION", };

  private static String[] booleanFunction = { "true", "false" };

  private static String[] numberFunction = { "EXP", "SIN", "COS", "TAN", "LOGN" };

  private static String[] stringFunction = { "REMOVESTRING" };

  private static String[] typeFunction = {};

  private static String[] then = { "->" };

  public String[] getKeywords(int type) {
    if (type == CONDITION) {
      return condition;
    } else if (type == ACTION) {
      return action;
    } else if (type == DECLARATION) {
      return declaration;
    } else if (type == BASIC) {
      return basic;
    } else if (type == BOOLEANFUNCTION) {
      return booleanFunction;
    } else if (type == NUMBERFUNCTION) {
      return numberFunction;
    } else if (type == STRINGFUNCTION) {
      return stringFunction;
    } else if (type == TYPEFUNCTION) {
      return typeFunction;
    } else if (type == THEN) {
      return then;
    }
    return getKeywords();
  }

  public static String[] append(String prefix, String[] a, String[] b) {
    int len = 0;
    if (a != null) {
      len = a.length;
    }
    String[] ns = new String[len + b.length];
    if (a != null) {
      System.arraycopy(a, 0, ns, 0, len);
    }
    for (int i = 0; i < b.length; i++) {
      ns[len + i] = prefix + b[i];
    }
    return ns;
  }

  public static String[] append(String[] a, String[] b) {
    if (b == null) {
      return a;
    }
    String[] ns = new String[a.length + b.length];
    System.arraycopy(a, 0, ns, 0, a.length);
    System.arraycopy(b, 0, ns, a.length, b.length);
    return ns;
  }

  public String[] getKeywords() {
    return null;
  }
}
