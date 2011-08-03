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

// $ANTLR 3.1.2 D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g 2009-08-17 15:43:14

package org.apache.uima.tm.textmarker.ui.convert;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class ConvertSyntaxLexer extends Lexer {
  public static final int STAR = 31;

  public static final int LBRACK = 34;

  public static final int FloatTypeSuffix = 68;

  public static final int TypeString = 19;

  public static final int TABLE = 24;

  public static final int GREATEREQUAL = 63;

  public static final int Exponent = 67;

  public static final int LOGN = 52;

  public static final int ASSIGN_EQUAL = 15;

  public static final int AND = 36;

  public static final int TypeSystemString = 9;

  public static final int BlockString = 27;

  public static final int IntString = 12;

  public static final int EOF = -1;

  public static final int HexDigit = 66;

  public static final int ACTION = 21;

  public static final int Identifier = 13;

  public static final int LPAREN = 25;

  public static final int IF = 41;

  public static final int NOTEQUAL = 58;

  public static final int RPAREN = 26;

  public static final int CREATE = 42;

  public static final int GREATER = 62;

  public static final int SLASH = 47;

  public static final int SIN = 53;

  public static final int EXP = 51;

  public static final int FILL = 43;

  public static final int COMMA = 14;

  public static final int COS = 54;

  public static final int TAN = 55;

  public static final int EQUAL = 57;

  public static final int LESS = 61;

  public static final int INLIST = 37;

  public static final int PLUS = 33;

  public static final int BooleanString = 18;

  public static final int COMMENT = 75;

  public static final int DOT = 45;

  public static final int ListIdentifier = 6;

  public static final int RBRACK = 35;

  public static final int XOR = 56;

  public static final int PERCENT = 48;

  public static final int PackageString = 7;

  public static final int LINE_COMMENT = 76;

  public static final int IntegerTypeSuffix = 65;

  public static final int LCURLY = 28;

  public static final int ConditionString = 20;

  public static final int MINUS = 39;

  public static final int LIST = 23;

  public static final int DecimalLiteral = 49;

  public static final int SEMI = 8;

  public static final int TRUE = 59;

  public static final int StringLiteral = 30;

  public static final int StringString = 17;

  public static final int ScriptString = 10;

  public static final int EngineString = 11;

  public static final int WS = 74;

  public static final int QUESTION = 32;

  public static final int UnicodeEscape = 70;

  public static final int FloatingPointLiteral = 50;

  public static final int OR = 40;

  public static final int RCURLY = 29;

  public static final int JavaIDDigit = 73;

  public static final int DECLARE = 22;

  public static final int DocComment = 4;

  public static final int CALL = 44;

  public static final int MOFN = 38;

  public static final int Annotation = 5;

  public static final int FALSE = 60;

  public static final int LESSEQUAL = 64;

  public static final int EscapeSequence = 69;

  public static final int OctalEscape = 71;

  public static final int Letter = 72;

  public static final int DoubleString = 16;

  public static final int T__77 = 77;

  public static final int BasicAnnotationType = 46;

  public int implicitLineJoiningLevel = 0;

  public int startPos = -1;

  public void emitErrorMessage(String msg) {
  }

  // delegates
  // delegators

  public ConvertSyntaxLexer() {
    ;
  }

  public ConvertSyntaxLexer(CharStream input) {
    this(input, new RecognizerSharedState());
  }

  public ConvertSyntaxLexer(CharStream input, RecognizerSharedState state) {
    super(input, state);

  }

  public String getGrammarFileName() {
    return "D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g";
  }

  // $ANTLR start "T__77"
  public final void mT__77() throws RecognitionException {
    try {
      int _type = T__77;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:18:7:
      // ( '|' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:18:9:
      // '|'
      {
        match('|');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "T__77"

  // $ANTLR start "DECLARE"
  public final void mDECLARE() throws RecognitionException {
    try {
      int _type = DECLARE;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1397:2:
      // ( 'DECLARE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1397:4:
      // 'DECLARE'
      {
        match("DECLARE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "DECLARE"

  // $ANTLR start "LIST"
  public final void mLIST() throws RecognitionException {
    try {
      int _type = LIST;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1401:2:
      // ( 'LIST' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1401:4:
      // 'LIST'
      {
        match("LIST");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LIST"

  // $ANTLR start "TABLE"
  public final void mTABLE() throws RecognitionException {
    try {
      int _type = TABLE;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1405:2:
      // ( 'TABLE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1405:4:
      // 'TABLE'
      {
        match("TABLE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "TABLE"

  // $ANTLR start "AND"
  public final void mAND() throws RecognitionException {
    try {
      int _type = AND;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1409:2:
      // ( 'AND' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1409:4:
      // 'AND'
      {
        match("AND");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "AND"

  // $ANTLR start "INLIST"
  public final void mINLIST() throws RecognitionException {
    try {
      int _type = INLIST;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1429:2:
      // ( 'INLIST' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1429:4:
      // 'INLIST'
      {
        match("INLIST");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "INLIST"

  // $ANTLR start "MOFN"
  public final void mMOFN() throws RecognitionException {
    try {
      int _type = MOFN;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1441:2:
      // ( 'MOFN' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1441:4:
      // 'MOFN'
      {
        match("MOFN");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "MOFN"

  // $ANTLR start "OR"
  public final void mOR() throws RecognitionException {
    try {
      int _type = OR;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1449:2:
      // ( 'OR' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1449:4:
      // 'OR'
      {
        match("OR");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "OR"

  // $ANTLR start "IF"
  public final void mIF() throws RecognitionException {
    try {
      int _type = IF;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1469:2:
      // ( 'IF' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1469:4:
      // 'IF'
      {
        match("IF");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "IF"

  // $ANTLR start "CREATE"
  public final void mCREATE() throws RecognitionException {
    try {
      int _type = CREATE;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1481:2:
      // ( 'CREATE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1481:4:
      // 'CREATE'
      {
        match("CREATE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "CREATE"

  // $ANTLR start "FILL"
  public final void mFILL() throws RecognitionException {
    try {
      int _type = FILL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1485:2:
      // ( 'FILL' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1485:4:
      // 'FILL'
      {
        match("FILL");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "FILL"

  // $ANTLR start "CALL"
  public final void mCALL() throws RecognitionException {
    try {
      int _type = CALL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1537:2:
      // ( 'CALL' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1537:4:
      // 'CALL'
      {
        match("CALL");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "CALL"

  // $ANTLR start "BasicAnnotationType"
  public final void mBasicAnnotationType() throws RecognitionException {
    try {
      int _type = BasicAnnotationType;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:2:
      // ( 'COLON' | 'SW' | 'MARKUP' | 'PERIOD' | 'CW' | 'NUM' | 'QUESTION' | 'SPECIAL' | 'CAP' |
      // 'COMMA' | 'EXCLAMATION' | 'SEMICOLON' | 'NBSP' | 'AMP' | '_' | 'SENTENCEEND' | 'W' | 'PM' |
      // 'ANY' | 'ALL' | 'SPACE' | 'BREAK' )
      int alt1 = 22;
      alt1 = dfa1.predict(input);
      switch (alt1) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:4:
          // 'COLON'
        {
          match("COLON");

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:13:
          // 'SW'
        {
          match("SW");

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:20:
          // 'MARKUP'
        {
          match("MARKUP");

        }
          break;
        case 4:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:31:
          // 'PERIOD'
        {
          match("PERIOD");

        }
          break;
        case 5:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:42:
          // 'CW'
        {
          match("CW");

        }
          break;
        case 6:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:48:
          // 'NUM'
        {
          match("NUM");

        }
          break;
        case 7:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:56:
          // 'QUESTION'
        {
          match("QUESTION");

        }
          break;
        case 8:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:69:
          // 'SPECIAL'
        {
          match("SPECIAL");

        }
          break;
        case 9:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:81:
          // 'CAP'
        {
          match("CAP");

        }
          break;
        case 10:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:89:
          // 'COMMA'
        {
          match("COMMA");

        }
          break;
        case 11:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:99:
          // 'EXCLAMATION'
        {
          match("EXCLAMATION");

        }
          break;
        case 12:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:115:
          // 'SEMICOLON'
        {
          match("SEMICOLON");

        }
          break;
        case 13:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:129:
          // 'NBSP'
        {
          match("NBSP");

        }
          break;
        case 14:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1574:137:
          // 'AMP'
        {
          match("AMP");

        }
          break;
        case 15:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:2:
          // '_'
        {
          match('_');

        }
          break;
        case 16:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:8:
          // 'SENTENCEEND'
        {
          match("SENTENCEEND");

        }
          break;
        case 17:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:24:
          // 'W'
        {
          match('W');

        }
          break;
        case 18:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:30:
          // 'PM'
        {
          match("PM");

        }
          break;
        case 19:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:37:
          // 'ANY'
        {
          match("ANY");

        }
          break;
        case 20:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:45:
          // 'ALL'
        {
          match("ALL");

        }
          break;
        case 21:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:53:
          // 'SPACE'
        {
          match("SPACE");

        }
          break;
        case 22:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1575:63:
          // 'BREAK'
        {
          match("BREAK");

        }
          break;

      }
      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "BasicAnnotationType"

  // $ANTLR start "PackageString"
  public final void mPackageString() throws RecognitionException {
    try {
      int _type = PackageString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1586:17:
      // ( 'PACKAGE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1586:19:
      // 'PACKAGE'
      {
        match("PACKAGE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "PackageString"

  // $ANTLR start "ScriptString"
  public final void mScriptString() throws RecognitionException {
    try {
      int _type = ScriptString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1587:14:
      // ( 'SCRIPT' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1587:16:
      // 'SCRIPT'
      {
        match("SCRIPT");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "ScriptString"

  // $ANTLR start "EngineString"
  public final void mEngineString() throws RecognitionException {
    try {
      int _type = EngineString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1588:14:
      // ( 'ENGINE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1588:16:
      // 'ENGINE'
      {
        match("ENGINE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "EngineString"

  // $ANTLR start "BlockString"
  public final void mBlockString() throws RecognitionException {
    try {
      int _type = BlockString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1589:14:
      // ( 'BLOCK' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1589:16:
      // 'BLOCK'
      {
        match("BLOCK");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "BlockString"

  // $ANTLR start "TypeString"
  public final void mTypeString() throws RecognitionException {
    try {
      int _type = TypeString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1590:13:
      // ( 'TYPE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1590:15:
      // 'TYPE'
      {
        match("TYPE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "TypeString"

  // $ANTLR start "IntString"
  public final void mIntString() throws RecognitionException {
    try {
      int _type = IntString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1591:11:
      // ( 'INT' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1591:13:
      // 'INT'
      {
        match("INT");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "IntString"

  // $ANTLR start "DoubleString"
  public final void mDoubleString() throws RecognitionException {
    try {
      int _type = DoubleString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1592:14:
      // ( 'DOUBLE' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1592:16:
      // 'DOUBLE'
      {
        match("DOUBLE");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "DoubleString"

  // $ANTLR start "StringString"
  public final void mStringString() throws RecognitionException {
    try {
      int _type = StringString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1593:14:
      // ( 'STRING' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1593:16:
      // 'STRING'
      {
        match("STRING");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "StringString"

  // $ANTLR start "BooleanString"
  public final void mBooleanString() throws RecognitionException {
    try {
      int _type = BooleanString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1594:15:
      // ( 'BOOLEAN' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1594:17:
      // 'BOOLEAN'
      {
        match("BOOLEAN");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "BooleanString"

  // $ANTLR start "TypeSystemString"
  public final void mTypeSystemString() throws RecognitionException {
    try {
      int _type = TypeSystemString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1595:17:
      // ( 'TYPESYSTEM' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1595:19:
      // 'TYPESYSTEM'
      {
        match("TYPESYSTEM");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "TypeSystemString"

  // $ANTLR start "ConditionString"
  public final void mConditionString() throws RecognitionException {
    try {
      int _type = ConditionString;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1597:17:
      // ( 'CONDITION' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1597:19:
      // 'CONDITION'
      {
        match("CONDITION");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "ConditionString"

  // $ANTLR start "ACTION"
  public final void mACTION() throws RecognitionException {
    try {
      int _type = ACTION;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1598:9:
      // ( 'ACTION' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1598:11:
      // 'ACTION'
      {
        match("ACTION");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "ACTION"

  // $ANTLR start "EXP"
  public final void mEXP() throws RecognitionException {
    try {
      int _type = EXP;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1600:6:
      // ( 'EXP' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1600:9:
      // 'EXP'
      {
        match("EXP");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "EXP"

  // $ANTLR start "LOGN"
  public final void mLOGN() throws RecognitionException {
    try {
      int _type = LOGN;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1601:6:
      // ( 'LOGN' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1601:8:
      // 'LOGN'
      {
        match("LOGN");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LOGN"

  // $ANTLR start "SIN"
  public final void mSIN() throws RecognitionException {
    try {
      int _type = SIN;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1602:5:
      // ( 'SIN' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1602:7:
      // 'SIN'
      {
        match("SIN");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "SIN"

  // $ANTLR start "COS"
  public final void mCOS() throws RecognitionException {
    try {
      int _type = COS;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1603:5:
      // ( 'COS' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1603:7:
      // 'COS'
      {
        match("COS");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "COS"

  // $ANTLR start "TAN"
  public final void mTAN() throws RecognitionException {
    try {
      int _type = TAN;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1604:5:
      // ( 'TAN' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1604:7:
      // 'TAN'
      {
        match("TAN");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "TAN"

  // $ANTLR start "XOR"
  public final void mXOR() throws RecognitionException {
    try {
      int _type = XOR;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1605:5:
      // ( 'XOR' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1605:8:
      // 'XOR'
      {
        match("XOR");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "XOR"

  // $ANTLR start "TRUE"
  public final void mTRUE() throws RecognitionException {
    try {
      int _type = TRUE;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1606:7:
      // ( 'true' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1606:9:
      // 'true'
      {
        match("true");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "TRUE"

  // $ANTLR start "FALSE"
  public final void mFALSE() throws RecognitionException {
    try {
      int _type = FALSE;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1607:8:
      // ( 'false' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1607:10:
      // 'false'
      {
        match("false");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "FALSE"

  // $ANTLR start "DecimalLiteral"
  public final void mDecimalLiteral() throws RecognitionException {
    try {
      int _type = DecimalLiteral;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:16:
      // ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:18:
      // ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:18:
        // ( '0' | '1' .. '9' ( '0' .. '9' )* )
        int alt3 = 2;
        int LA3_0 = input.LA(1);

        if ((LA3_0 == '0')) {
          alt3 = 1;
        } else if (((LA3_0 >= '1' && LA3_0 <= '9'))) {
          alt3 = 2;
        } else {
          NoViableAltException nvae = new NoViableAltException("", 3, 0, input);

          throw nvae;
        }
        switch (alt3) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:19:
            // '0'
          {
            match('0');

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:25:
            // '1' .. '9' ( '0' .. '9' )*
          {
            matchRange('1', '9');
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:34:
            // ( '0' .. '9' )*
            loop2: do {
              int alt2 = 2;
              int LA2_0 = input.LA(1);

              if (((LA2_0 >= '0' && LA2_0 <= '9'))) {
                alt2 = 1;
              }

              switch (alt2) {
                case 1:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:34:
                  // '0' .. '9'
                {
                  matchRange('0', '9');

                }
                  break;

                default:
                  break loop2;
              }
            } while (true);

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:45:
        // ( IntegerTypeSuffix )?
        int alt4 = 2;
        int LA4_0 = input.LA(1);

        if ((LA4_0 == 'L' || LA4_0 == 'l')) {
          alt4 = 1;
        }
        switch (alt4) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1616:45:
            // IntegerTypeSuffix
          {
            mIntegerTypeSuffix();

          }
            break;

        }

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "DecimalLiteral"

  // $ANTLR start "HexDigit"
  public final void mHexDigit() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1621:10:
      // ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1621:12:
      // ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
      {
        if ((input.LA(1) >= '0' && input.LA(1) <= '9')
                || (input.LA(1) >= 'A' && input.LA(1) <= 'F')
                || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

      }

    } finally {
    }
  }

  // $ANTLR end "HexDigit"

  // $ANTLR start "IntegerTypeSuffix"
  public final void mIntegerTypeSuffix() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1624:19:
      // ( ( 'l' | 'L' ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1624:21:
      // ( 'l' | 'L' )
      {
        if (input.LA(1) == 'L' || input.LA(1) == 'l') {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

      }

    } finally {
    }
  }

  // $ANTLR end "IntegerTypeSuffix"

  // $ANTLR start "Exponent"
  public final void mExponent() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1628:10:
      // ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1628:12:
      // ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
      {
        if (input.LA(1) == 'E' || input.LA(1) == 'e') {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1628:22:
        // ( '+' | '-' )?
        int alt5 = 2;
        int LA5_0 = input.LA(1);

        if ((LA5_0 == '+' || LA5_0 == '-')) {
          alt5 = 1;
        }
        switch (alt5) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:
          {
            if (input.LA(1) == '+' || input.LA(1) == '-') {
              input.consume();

            } else {
              MismatchedSetException mse = new MismatchedSetException(null, input);
              recover(mse);
              throw mse;
            }

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1628:33:
        // ( '0' .. '9' )+
        int cnt6 = 0;
        loop6: do {
          int alt6 = 2;
          int LA6_0 = input.LA(1);

          if (((LA6_0 >= '0' && LA6_0 <= '9'))) {
            alt6 = 1;
          }

          switch (alt6) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1628:34:
              // '0' .. '9'
            {
              matchRange('0', '9');

            }
              break;

            default:
              if (cnt6 >= 1)
                break loop6;
              EarlyExitException eee = new EarlyExitException(6, input);
              throw eee;
          }
          cnt6++;
        } while (true);

      }

    } finally {
    }
  }

  // $ANTLR end "Exponent"

  // $ANTLR start "FloatingPointLiteral"
  public final void mFloatingPointLiteral() throws RecognitionException {
    try {
      int _type = FloatingPointLiteral;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:5:
      // ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9'
      // )+ ( Exponent )? ( FloatTypeSuffix )? )
      int alt14 = 2;
      int LA14_0 = input.LA(1);

      if (((LA14_0 >= '0' && LA14_0 <= '9'))) {
        alt14 = 1;
      } else if ((LA14_0 == '.')) {
        alt14 = 2;
      } else {
        NoViableAltException nvae = new NoViableAltException("", 14, 0, input);

        throw nvae;
      }
      switch (alt14) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:9:
          // ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:9:
          // ( '0' .. '9' )+
          int cnt7 = 0;
          loop7: do {
            int alt7 = 2;
            int LA7_0 = input.LA(1);

            if (((LA7_0 >= '0' && LA7_0 <= '9'))) {
              alt7 = 1;
            }

            switch (alt7) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:10:
                // '0' .. '9'
              {
                matchRange('0', '9');

              }
                break;

              default:
                if (cnt7 >= 1)
                  break loop7;
                EarlyExitException eee = new EarlyExitException(7, input);
                throw eee;
            }
            cnt7++;
          } while (true);

          match('.');
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:25:
          // ( '0' .. '9' )*
          loop8: do {
            int alt8 = 2;
            int LA8_0 = input.LA(1);

            if (((LA8_0 >= '0' && LA8_0 <= '9'))) {
              alt8 = 1;
            }

            switch (alt8) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:26:
                // '0' .. '9'
              {
                matchRange('0', '9');

              }
                break;

              default:
                break loop8;
            }
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:37:
          // ( Exponent )?
          int alt9 = 2;
          int LA9_0 = input.LA(1);

          if ((LA9_0 == 'E' || LA9_0 == 'e')) {
            alt9 = 1;
          }
          switch (alt9) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:37:
              // Exponent
            {
              mExponent();

            }
              break;

          }

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:47:
          // ( FloatTypeSuffix )?
          int alt10 = 2;
          int LA10_0 = input.LA(1);

          if ((LA10_0 == 'D' || LA10_0 == 'F' || LA10_0 == 'd' || LA10_0 == 'f')) {
            alt10 = 1;
          }
          switch (alt10) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1631:47:
              // FloatTypeSuffix
            {
              mFloatTypeSuffix();

            }
              break;

          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:9:
          // '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
        {
          match('.');
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:13:
          // ( '0' .. '9' )+
          int cnt11 = 0;
          loop11: do {
            int alt11 = 2;
            int LA11_0 = input.LA(1);

            if (((LA11_0 >= '0' && LA11_0 <= '9'))) {
              alt11 = 1;
            }

            switch (alt11) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:14:
                // '0' .. '9'
              {
                matchRange('0', '9');

              }
                break;

              default:
                if (cnt11 >= 1)
                  break loop11;
                EarlyExitException eee = new EarlyExitException(11, input);
                throw eee;
            }
            cnt11++;
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:25:
          // ( Exponent )?
          int alt12 = 2;
          int LA12_0 = input.LA(1);

          if ((LA12_0 == 'E' || LA12_0 == 'e')) {
            alt12 = 1;
          }
          switch (alt12) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:25:
              // Exponent
            {
              mExponent();

            }
              break;

          }

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:35:
          // ( FloatTypeSuffix )?
          int alt13 = 2;
          int LA13_0 = input.LA(1);

          if ((LA13_0 == 'D' || LA13_0 == 'F' || LA13_0 == 'd' || LA13_0 == 'f')) {
            alt13 = 1;
          }
          switch (alt13) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1632:35:
              // FloatTypeSuffix
            {
              mFloatTypeSuffix();

            }
              break;

          }

        }
          break;

      }
      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "FloatingPointLiteral"

  // $ANTLR start "FloatTypeSuffix"
  public final void mFloatTypeSuffix() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1636:17:
      // ( ( 'f' | 'F' | 'd' | 'D' ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1636:19:
      // ( 'f' | 'F' | 'd' | 'D' )
      {
        if (input.LA(1) == 'D' || input.LA(1) == 'F' || input.LA(1) == 'd' || input.LA(1) == 'f') {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

      }

    } finally {
    }
  }

  // $ANTLR end "FloatTypeSuffix"

  // $ANTLR start "StringLiteral"
  public final void mStringLiteral() throws RecognitionException {
    try {
      int _type = StringLiteral;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1643:5:
      // ( '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1643:8:
      // '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"'
      {
        match('\"');
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1643:12:
        // ( EscapeSequence | ~ ( '\\\\' | '\"' ) )*
        loop15: do {
          int alt15 = 3;
          int LA15_0 = input.LA(1);

          if ((LA15_0 == '\\')) {
            alt15 = 1;
          } else if (((LA15_0 >= '\u0000' && LA15_0 <= '!') || (LA15_0 >= '#' && LA15_0 <= '[') || (LA15_0 >= ']' && LA15_0 <= '\uFFFF'))) {
            alt15 = 2;
          }

          switch (alt15) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1643:14:
              // EscapeSequence
            {
              mEscapeSequence();

            }
              break;
            case 2:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1643:31:
              // ~ ( '\\\\' | '\"' )
            {
              if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!')
                      || (input.LA(1) >= '#' && input.LA(1) <= '[')
                      || (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
                input.consume();

              } else {
                MismatchedSetException mse = new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }

            }
              break;

            default:
              break loop15;
          }
        } while (true);

        match('\"');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "StringLiteral"

  // $ANTLR start "EscapeSequence"
  public final void mEscapeSequence() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1648:5:
      // ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UnicodeEscape |
      // OctalEscape )
      int alt16 = 3;
      int LA16_0 = input.LA(1);

      if ((LA16_0 == '\\')) {
        switch (input.LA(2)) {
          case '\"':
          case '\'':
          case '\\':
          case 'b':
          case 'f':
          case 'n':
          case 'r':
          case 't': {
            alt16 = 1;
          }
            break;
          case 'u': {
            alt16 = 2;
          }
            break;
          case '0':
          case '1':
          case '2':
          case '3':
          case '4':
          case '5':
          case '6':
          case '7': {
            alt16 = 3;
          }
            break;
          default:
            NoViableAltException nvae = new NoViableAltException("", 16, 1, input);

            throw nvae;
        }

      } else {
        NoViableAltException nvae = new NoViableAltException("", 16, 0, input);

        throw nvae;
      }
      switch (alt16) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1648:9:
          // '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
        {
          match('\\');
          if (input.LA(1) == '\"' || input.LA(1) == '\'' || input.LA(1) == '\\'
                  || input.LA(1) == 'b' || input.LA(1) == 'f' || input.LA(1) == 'n'
                  || input.LA(1) == 'r' || input.LA(1) == 't') {
            input.consume();

          } else {
            MismatchedSetException mse = new MismatchedSetException(null, input);
            recover(mse);
            throw mse;
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1649:9:
          // UnicodeEscape
        {
          mUnicodeEscape();

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1650:9:
          // OctalEscape
        {
          mOctalEscape();

        }
          break;

      }
    } finally {
    }
  }

  // $ANTLR end "EscapeSequence"

  // $ANTLR start "OctalEscape"
  public final void mOctalEscape() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:5:
      // ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7'
      // ) | '\\\\' ( '0' .. '7' ) )
      int alt17 = 3;
      int LA17_0 = input.LA(1);

      if ((LA17_0 == '\\')) {
        int LA17_1 = input.LA(2);

        if (((LA17_1 >= '0' && LA17_1 <= '3'))) {
          int LA17_2 = input.LA(3);

          if (((LA17_2 >= '0' && LA17_2 <= '7'))) {
            int LA17_4 = input.LA(4);

            if (((LA17_4 >= '0' && LA17_4 <= '7'))) {
              alt17 = 1;
            } else {
              alt17 = 2;
            }
          } else {
            alt17 = 3;
          }
        } else if (((LA17_1 >= '4' && LA17_1 <= '7'))) {
          int LA17_3 = input.LA(3);

          if (((LA17_3 >= '0' && LA17_3 <= '7'))) {
            alt17 = 2;
          } else {
            alt17 = 3;
          }
        } else {
          NoViableAltException nvae = new NoViableAltException("", 17, 1, input);

          throw nvae;
        }
      } else {
        NoViableAltException nvae = new NoViableAltException("", 17, 0, input);

        throw nvae;
      }
      switch (alt17) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:9:
          // '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
        {
          match('\\');
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:14:
          // ( '0' .. '3' )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:15:
          // '0' .. '3'
          {
            matchRange('0', '3');

          }

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:25:
          // ( '0' .. '7' )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:26:
          // '0' .. '7'
          {
            matchRange('0', '7');

          }

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:36:
          // ( '0' .. '7' )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1655:37:
          // '0' .. '7'
          {
            matchRange('0', '7');

          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1656:9:
          // '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
        {
          match('\\');
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1656:14:
          // ( '0' .. '7' )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1656:15:
          // '0' .. '7'
          {
            matchRange('0', '7');

          }

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1656:25:
          // ( '0' .. '7' )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1656:26:
          // '0' .. '7'
          {
            matchRange('0', '7');

          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1657:9:
          // '\\\\' ( '0' .. '7' )
        {
          match('\\');
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1657:14:
          // ( '0' .. '7' )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1657:15:
          // '0' .. '7'
          {
            matchRange('0', '7');

          }

        }
          break;

      }
    } finally {
    }
  }

  // $ANTLR end "OctalEscape"

  // $ANTLR start "UnicodeEscape"
  public final void mUnicodeEscape() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1662:5:
      // ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1662:9:
      // '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
      {
        match('\\');
        match('u');
        mHexDigit();
        mHexDigit();
        mHexDigit();
        mHexDigit();

      }

    } finally {
    }
  }

  // $ANTLR end "UnicodeEscape"

  // $ANTLR start "Identifier"
  public final void mIdentifier() throws RecognitionException {
    try {
      int _type = Identifier;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1667:5:
      // ( Letter ( Letter | JavaIDDigit )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1667:9:
      // Letter ( Letter | JavaIDDigit )*
      {
        mLetter();
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1667:16:
        // ( Letter | JavaIDDigit )*
        loop18: do {
          int alt18 = 2;
          int LA18_0 = input.LA(1);

          if ((LA18_0 == '$' || (LA18_0 >= '0' && LA18_0 <= '9')
                  || (LA18_0 >= 'A' && LA18_0 <= 'Z') || LA18_0 == '_'
                  || (LA18_0 >= 'a' && LA18_0 <= 'z') || (LA18_0 >= '\u00C0' && LA18_0 <= '\u00D6')
                  || (LA18_0 >= '\u00D8' && LA18_0 <= '\u00F6')
                  || (LA18_0 >= '\u00F8' && LA18_0 <= '\u1FFF')
                  || (LA18_0 >= '\u3040' && LA18_0 <= '\u318F')
                  || (LA18_0 >= '\u3300' && LA18_0 <= '\u337F')
                  || (LA18_0 >= '\u3400' && LA18_0 <= '\u3D2D')
                  || (LA18_0 >= '\u4E00' && LA18_0 <= '\u9FFF') || (LA18_0 >= '\uF900' && LA18_0 <= '\uFAFF'))) {
            alt18 = 1;
          }

          switch (alt18) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:
            {
              if (input.LA(1) == '$' || (input.LA(1) >= '0' && input.LA(1) <= '9')
                      || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_'
                      || (input.LA(1) >= 'a' && input.LA(1) <= 'z')
                      || (input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')
                      || (input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')
                      || (input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')
                      || (input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')
                      || (input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')
                      || (input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')
                      || (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')
                      || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF')) {
                input.consume();

              } else {
                MismatchedSetException mse = new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }

            }
              break;

            default:
              break loop18;
          }
        } while (true);

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "Identifier"

  // $ANTLR start "Letter"
  public final void mLetter() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1673:5:
      // ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' .. '\\u007a' | '\\u00c0' ..
      // '\\u00d6' | '\\u00d8' .. '\\u00f6' | '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' |
      // '\\u3040' .. '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' | '\\u4e00' ..
      // '\\u9fff' | '\\uf900' .. '\\ufaff' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:
      {
        if (input.LA(1) == '$' || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_'
                || (input.LA(1) >= 'a' && input.LA(1) <= 'z')
                || (input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')
                || (input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')
                || (input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')
                || (input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')
                || (input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')
                || (input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')
                || (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')
                || (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF')) {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

      }

    } finally {
    }
  }

  // $ANTLR end "Letter"

  // $ANTLR start "JavaIDDigit"
  public final void mJavaIDDigit() throws RecognitionException {
    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1691:5:
      // ( '\\u0030' .. '\\u0039' | '\\u0660' .. '\\u0669' | '\\u06f0' .. '\\u06f9' | '\\u0966' ..
      // '\\u096f' | '\\u09e6' .. '\\u09ef' | '\\u0a66' .. '\\u0a6f' | '\\u0ae6' .. '\\u0aef' |
      // '\\u0b66' .. '\\u0b6f' | '\\u0be7' .. '\\u0bef' | '\\u0c66' .. '\\u0c6f' | '\\u0ce6' ..
      // '\\u0cef' | '\\u0d66' .. '\\u0d6f' | '\\u0e50' .. '\\u0e59' | '\\u0ed0' .. '\\u0ed9' |
      // '\\u1040' .. '\\u1049' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:
      {
        if ((input.LA(1) >= '0' && input.LA(1) <= '9')
                || (input.LA(1) >= '\u0660' && input.LA(1) <= '\u0669')
                || (input.LA(1) >= '\u06F0' && input.LA(1) <= '\u06F9')
                || (input.LA(1) >= '\u0966' && input.LA(1) <= '\u096F')
                || (input.LA(1) >= '\u09E6' && input.LA(1) <= '\u09EF')
                || (input.LA(1) >= '\u0A66' && input.LA(1) <= '\u0A6F')
                || (input.LA(1) >= '\u0AE6' && input.LA(1) <= '\u0AEF')
                || (input.LA(1) >= '\u0B66' && input.LA(1) <= '\u0B6F')
                || (input.LA(1) >= '\u0BE7' && input.LA(1) <= '\u0BEF')
                || (input.LA(1) >= '\u0C66' && input.LA(1) <= '\u0C6F')
                || (input.LA(1) >= '\u0CE6' && input.LA(1) <= '\u0CEF')
                || (input.LA(1) >= '\u0D66' && input.LA(1) <= '\u0D6F')
                || (input.LA(1) >= '\u0E50' && input.LA(1) <= '\u0E59')
                || (input.LA(1) >= '\u0ED0' && input.LA(1) <= '\u0ED9')
                || (input.LA(1) >= '\u1040' && input.LA(1) <= '\u1049')) {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

      }

    } finally {
    }
  }

  // $ANTLR end "JavaIDDigit"

  // $ANTLR start "LPAREN"
  public final void mLPAREN() throws RecognitionException {
    try {
      int _type = LPAREN;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1710:8:
      // ( '(' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1710:10:
      // '('
      {
        match('(');
        implicitLineJoiningLevel++;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LPAREN"

  // $ANTLR start "RPAREN"
  public final void mRPAREN() throws RecognitionException {
    try {
      int _type = RPAREN;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1712:8:
      // ( ')' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1712:10:
      // ')'
      {
        match(')');
        implicitLineJoiningLevel--;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "RPAREN"

  // $ANTLR start "LBRACK"
  public final void mLBRACK() throws RecognitionException {
    try {
      int _type = LBRACK;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1714:8:
      // ( '[' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1714:10:
      // '['
      {
        match('[');
        implicitLineJoiningLevel++;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LBRACK"

  // $ANTLR start "RBRACK"
  public final void mRBRACK() throws RecognitionException {
    try {
      int _type = RBRACK;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1716:8:
      // ( ']' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1716:10:
      // ']'
      {
        match(']');
        implicitLineJoiningLevel--;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "RBRACK"

  // $ANTLR start "LCURLY"
  public final void mLCURLY() throws RecognitionException {
    try {
      int _type = LCURLY;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1718:8:
      // ( '{' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1718:10:
      // '{'
      {
        match('{');
        implicitLineJoiningLevel++;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LCURLY"

  // $ANTLR start "RCURLY"
  public final void mRCURLY() throws RecognitionException {
    try {
      int _type = RCURLY;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1720:8:
      // ( '}' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1720:10:
      // '}'
      {
        match('}');
        implicitLineJoiningLevel--;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "RCURLY"

  // $ANTLR start "DOT"
  public final void mDOT() throws RecognitionException {
    try {
      int _type = DOT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1727:5:
      // ( '.' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1727:7:
      // '.'
      {
        match('.');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "DOT"

  // $ANTLR start "COMMA"
  public final void mCOMMA() throws RecognitionException {
    try {
      int _type = COMMA;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1731:7:
      // ( ',' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1731:9:
      // ','
      {
        match(',');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "COMMA"

  // $ANTLR start "SEMI"
  public final void mSEMI() throws RecognitionException {
    try {
      int _type = SEMI;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1733:6:
      // ( ';' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1733:8:
      // ';'
      {
        match(';');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "SEMI"

  // $ANTLR start "PLUS"
  public final void mPLUS() throws RecognitionException {
    try {
      int _type = PLUS;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1735:6:
      // ( '+' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1735:8:
      // '+'
      {
        match('+');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "PLUS"

  // $ANTLR start "MINUS"
  public final void mMINUS() throws RecognitionException {
    try {
      int _type = MINUS;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1737:7:
      // ( '-' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1737:9:
      // '-'
      {
        match('-');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "MINUS"

  // $ANTLR start "STAR"
  public final void mSTAR() throws RecognitionException {
    try {
      int _type = STAR;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1739:6:
      // ( '*' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1739:8:
      // '*'
      {
        match('*');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "STAR"

  // $ANTLR start "SLASH"
  public final void mSLASH() throws RecognitionException {
    try {
      int _type = SLASH;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1741:7:
      // ( '/' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1741:9:
      // '/'
      {
        match('/');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "SLASH"

  // $ANTLR start "LESS"
  public final void mLESS() throws RecognitionException {
    try {
      int _type = LESS;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1747:6:
      // ( '<' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1747:8:
      // '<'
      {
        match('<');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LESS"

  // $ANTLR start "GREATER"
  public final void mGREATER() throws RecognitionException {
    try {
      int _type = GREATER;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1749:9:
      // ( '>' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1749:11:
      // '>'
      {
        match('>');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "GREATER"

  // $ANTLR start "ASSIGN_EQUAL"
  public final void mASSIGN_EQUAL() throws RecognitionException {
    try {
      int _type = ASSIGN_EQUAL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1751:14:
      // ( '=' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1751:16:
      // '='
      {
        match('=');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "ASSIGN_EQUAL"

  // $ANTLR start "PERCENT"
  public final void mPERCENT() throws RecognitionException {
    try {
      int _type = PERCENT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1753:9:
      // ( '%' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1753:11:
      // '%'
      {
        match('%');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "PERCENT"

  // $ANTLR start "QUESTION"
  public final void mQUESTION() throws RecognitionException {
    try {
      int _type = QUESTION;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1755:10:
      // ( '?' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1755:12:
      // '?'
      {
        match('?');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "QUESTION"

  // $ANTLR start "EQUAL"
  public final void mEQUAL() throws RecognitionException {
    try {
      int _type = EQUAL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1757:7:
      // ( '==' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1757:9:
      // '=='
      {
        match("==");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "EQUAL"

  // $ANTLR start "NOTEQUAL"
  public final void mNOTEQUAL() throws RecognitionException {
    try {
      int _type = NOTEQUAL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1759:10:
      // ( '!=' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1759:12:
      // '!='
      {
        match("!=");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "NOTEQUAL"

  // $ANTLR start "LESSEQUAL"
  public final void mLESSEQUAL() throws RecognitionException {
    try {
      int _type = LESSEQUAL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1763:11:
      // ( '<=' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1763:13:
      // '<='
      {
        match("<=");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LESSEQUAL"

  // $ANTLR start "GREATEREQUAL"
  public final void mGREATEREQUAL() throws RecognitionException {
    try {
      int _type = GREATEREQUAL;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1766:14:
      // ( '>=' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1766:16:
      // '>='
      {
        match(">=");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "GREATEREQUAL"

  // $ANTLR start "WS"
  public final void mWS() throws RecognitionException {
    try {
      int _type = WS;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1768:5:
      // ( ( ' ' | '\\r' | '\\t' | '\ ' | '\\n' ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1768:8:
      // ( ' ' | '\\r' | '\\t' | '\ ' | '\\n' )
      {
        if ((input.LA(1) >= '\t' && input.LA(1) <= '\n')
                || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ') {
          input.consume();

        } else {
          MismatchedSetException mse = new MismatchedSetException(null, input);
          recover(mse);
          throw mse;
        }

        _channel = HIDDEN;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "WS"

  // $ANTLR start "COMMENT"
  public final void mCOMMENT() throws RecognitionException {
    try {
      int _type = COMMENT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1778:5:
      // ( '/*' ( options {greedy=false; } : . )* '*/' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1778:9:
      // '/*' ( options {greedy=false; } : . )* '*/'
      {
        match("/*");

        if (input.LA(1) == '*')
          _type = DocComment;
        else
          _channel = HIDDEN;
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1778:77:
        // ( options {greedy=false; } : . )*
        loop19: do {
          int alt19 = 2;
          int LA19_0 = input.LA(1);

          if ((LA19_0 == '*')) {
            int LA19_1 = input.LA(2);

            if ((LA19_1 == '/')) {
              alt19 = 2;
            } else if (((LA19_1 >= '\u0000' && LA19_1 <= '.') || (LA19_1 >= '0' && LA19_1 <= '\uFFFF'))) {
              alt19 = 1;
            }

          } else if (((LA19_0 >= '\u0000' && LA19_0 <= ')') || (LA19_0 >= '+' && LA19_0 <= '\uFFFF'))) {
            alt19 = 1;
          }

          switch (alt19) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1778:105:
              // .
            {
              matchAny();

            }
              break;

            default:
              break loop19;
          }
        } while (true);

        match("*/");

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "COMMENT"

  // $ANTLR start "LINE_COMMENT"
  public final void mLINE_COMMENT() throws RecognitionException {
    try {
      int _type = LINE_COMMENT;
      int _channel = DEFAULT_TOKEN_CHANNEL;
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1782:5:
      // ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1782:7:
      // '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
      {
        match("//");

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1782:12:
        // (~ ( '\\n' | '\\r' ) )*
        loop20: do {
          int alt20 = 2;
          int LA20_0 = input.LA(1);

          if (((LA20_0 >= '\u0000' && LA20_0 <= '\t') || (LA20_0 >= '\u000B' && LA20_0 <= '\f') || (LA20_0 >= '\u000E' && LA20_0 <= '\uFFFF'))) {
            alt20 = 1;
          }

          switch (alt20) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1782:12:
              // ~ ( '\\n' | '\\r' )
            {
              if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
                      || (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
                      || (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
                input.consume();

              } else {
                MismatchedSetException mse = new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }

            }
              break;

            default:
              break loop20;
          }
        } while (true);

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1782:26:
        // ( '\\r' )?
        int alt21 = 2;
        int LA21_0 = input.LA(1);

        if ((LA21_0 == '\r')) {
          alt21 = 1;
        }
        switch (alt21) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1782:26:
            // '\\r'
          {
            match('\r');

          }
            break;

        }

        match('\n');
        _channel = HIDDEN;

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "LINE_COMMENT"

  public void mTokens() throws RecognitionException {
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:8:
    // ( T__77 | DECLARE | LIST | TABLE | AND | INLIST | MOFN | OR | IF | CREATE | FILL | CALL |
    // BasicAnnotationType | PackageString | ScriptString | EngineString | BlockString | TypeString
    // | IntString | DoubleString | StringString | BooleanString | TypeSystemString |
    // ConditionString | ACTION | EXP | LOGN | SIN | COS | TAN | XOR | TRUE | FALSE | DecimalLiteral
    // | FloatingPointLiteral | StringLiteral | Identifier | LPAREN | RPAREN | LBRACK | RBRACK |
    // LCURLY | RCURLY | DOT | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | LESS | GREATER |
    // ASSIGN_EQUAL | PERCENT | QUESTION | EQUAL | NOTEQUAL | LESSEQUAL | GREATEREQUAL | WS |
    // COMMENT | LINE_COMMENT )
    int alt22 = 62;
    alt22 = dfa22.predict(input);
    switch (alt22) {
      case 1:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:10:
        // T__77
      {
        mT__77();

      }
        break;
      case 2:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:16:
        // DECLARE
      {
        mDECLARE();

      }
        break;
      case 3:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:24:
        // LIST
      {
        mLIST();

      }
        break;
      case 4:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:29:
        // TABLE
      {
        mTABLE();

      }
        break;
      case 5:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:35:
        // AND
      {
        mAND();

      }
        break;
      case 6:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:39:
        // INLIST
      {
        mINLIST();

      }
        break;
      case 7:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:46:
        // MOFN
      {
        mMOFN();

      }
        break;
      case 8:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:51:
        // OR
      {
        mOR();

      }
        break;
      case 9:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:54:
        // IF
      {
        mIF();

      }
        break;
      case 10:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:57:
        // CREATE
      {
        mCREATE();

      }
        break;
      case 11:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:64:
        // FILL
      {
        mFILL();

      }
        break;
      case 12:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:69:
        // CALL
      {
        mCALL();

      }
        break;
      case 13:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:74:
        // BasicAnnotationType
      {
        mBasicAnnotationType();

      }
        break;
      case 14:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:94:
        // PackageString
      {
        mPackageString();

      }
        break;
      case 15:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:108:
        // ScriptString
      {
        mScriptString();

      }
        break;
      case 16:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:121:
        // EngineString
      {
        mEngineString();

      }
        break;
      case 17:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:134:
        // BlockString
      {
        mBlockString();

      }
        break;
      case 18:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:146:
        // TypeString
      {
        mTypeString();

      }
        break;
      case 19:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:157:
        // IntString
      {
        mIntString();

      }
        break;
      case 20:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:167:
        // DoubleString
      {
        mDoubleString();

      }
        break;
      case 21:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:180:
        // StringString
      {
        mStringString();

      }
        break;
      case 22:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:193:
        // BooleanString
      {
        mBooleanString();

      }
        break;
      case 23:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:207:
        // TypeSystemString
      {
        mTypeSystemString();

      }
        break;
      case 24:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:224:
        // ConditionString
      {
        mConditionString();

      }
        break;
      case 25:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:240:
        // ACTION
      {
        mACTION();

      }
        break;
      case 26:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:247:
        // EXP
      {
        mEXP();

      }
        break;
      case 27:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:251:
        // LOGN
      {
        mLOGN();

      }
        break;
      case 28:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:256:
        // SIN
      {
        mSIN();

      }
        break;
      case 29:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:260:
        // COS
      {
        mCOS();

      }
        break;
      case 30:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:264:
        // TAN
      {
        mTAN();

      }
        break;
      case 31:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:268:
        // XOR
      {
        mXOR();

      }
        break;
      case 32:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:272:
        // TRUE
      {
        mTRUE();

      }
        break;
      case 33:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:277:
        // FALSE
      {
        mFALSE();

      }
        break;
      case 34:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:283:
        // DecimalLiteral
      {
        mDecimalLiteral();

      }
        break;
      case 35:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:298:
        // FloatingPointLiteral
      {
        mFloatingPointLiteral();

      }
        break;
      case 36:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:319:
        // StringLiteral
      {
        mStringLiteral();

      }
        break;
      case 37:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:333:
        // Identifier
      {
        mIdentifier();

      }
        break;
      case 38:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:344:
        // LPAREN
      {
        mLPAREN();

      }
        break;
      case 39:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:351:
        // RPAREN
      {
        mRPAREN();

      }
        break;
      case 40:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:358:
        // LBRACK
      {
        mLBRACK();

      }
        break;
      case 41:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:365:
        // RBRACK
      {
        mRBRACK();

      }
        break;
      case 42:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:372:
        // LCURLY
      {
        mLCURLY();

      }
        break;
      case 43:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:379:
        // RCURLY
      {
        mRCURLY();

      }
        break;
      case 44:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:386:
        // DOT
      {
        mDOT();

      }
        break;
      case 45:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:390:
        // COMMA
      {
        mCOMMA();

      }
        break;
      case 46:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:396:
        // SEMI
      {
        mSEMI();

      }
        break;
      case 47:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:401:
        // PLUS
      {
        mPLUS();

      }
        break;
      case 48:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:406:
        // MINUS
      {
        mMINUS();

      }
        break;
      case 49:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:412:
        // STAR
      {
        mSTAR();

      }
        break;
      case 50:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:417:
        // SLASH
      {
        mSLASH();

      }
        break;
      case 51:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:423:
        // LESS
      {
        mLESS();

      }
        break;
      case 52:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:428:
        // GREATER
      {
        mGREATER();

      }
        break;
      case 53:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:436:
        // ASSIGN_EQUAL
      {
        mASSIGN_EQUAL();

      }
        break;
      case 54:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:449:
        // PERCENT
      {
        mPERCENT();

      }
        break;
      case 55:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:457:
        // QUESTION
      {
        mQUESTION();

      }
        break;
      case 56:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:466:
        // EQUAL
      {
        mEQUAL();

      }
        break;
      case 57:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:472:
        // NOTEQUAL
      {
        mNOTEQUAL();

      }
        break;
      case 58:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:481:
        // LESSEQUAL
      {
        mLESSEQUAL();

      }
        break;
      case 59:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:491:
        // GREATEREQUAL
      {
        mGREATEREQUAL();

      }
        break;
      case 60:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:504:
        // WS
      {
        mWS();

      }
        break;
      case 61:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:507:
        // COMMENT
      {
        mCOMMENT();

      }
        break;
      case 62:
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1:515:
        // LINE_COMMENT
      {
        mLINE_COMMENT();

      }
        break;

    }

  }

  protected DFA1 dfa1 = new DFA1(this);

  protected DFA22 dfa22 = new DFA22(this);

  static final String DFA1_eotS = "\37\uffff";

  static final String DFA1_eofS = "\37\uffff";

  static final String DFA1_minS = "\2\101\1\105\1\uffff\1\105\1\102\2\uffff\1\114\3\uffff\1\114\3"
          + "\uffff\1\101\1\115\15\uffff";

  static final String DFA1_maxS = "\1\137\2\127\1\uffff\1\115\1\125\2\uffff\1\116\3\uffff\1\115\3"
          + "\uffff\1\105\1\116\15\uffff";

  static final String DFA1_acceptS = "\3\uffff\1\3\2\uffff\1\7\1\13\1\uffff\1\17\1\21\1\26\1\uffff\1"
          + "\5\1\11\1\2\2\uffff\1\4\1\22\1\6\1\15\1\16\1\23\1\24\1\1\1\12\1"
          + "\10\1\25\1\14\1\20";

  static final String DFA1_specialS = "\37\uffff}>";

  static final String[] DFA1_transitionS = {
      "\1\10\1\13\1\1\1\uffff\1\7\7\uffff\1\3\1\5\1\uffff\1\4\1\6"
              + "\1\uffff\1\2\3\uffff\1\12\7\uffff\1\11", "\1\16\15\uffff\1\14\7\uffff\1\15",
      "\1\21\12\uffff\1\20\6\uffff\1\17", "", "\1\22\7\uffff\1\23", "\1\25\22\uffff\1\24", "", "",
      "\1\30\1\26\1\27", "", "", "", "\1\31\1\32", "", "", "", "\1\34\3\uffff\1\33", "\1\35\1\36",
      "", "", "", "", "", "", "", "", "", "", "", "", "" };

  static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);

  static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);

  static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);

  static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);

  static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);

  static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);

  static final short[][] DFA1_transition;

  static {
    int numStates = DFA1_transitionS.length;
    DFA1_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
    }
  }

  class DFA1 extends DFA {

    public DFA1(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 1;
      this.eot = DFA1_eot;
      this.eof = DFA1_eof;
      this.min = DFA1_min;
      this.max = DFA1_max;
      this.accept = DFA1_accept;
      this.special = DFA1_special;
      this.transition = DFA1_transition;
    }

    public String getDescription() {
      return "1573:1: BasicAnnotationType : ( 'COLON' | 'SW' | 'MARKUP' | 'PERIOD' | 'CW' | 'NUM' | 'QUESTION' | 'SPECIAL' | 'CAP' | 'COMMA' | 'EXCLAMATION' | 'SEMICOLON' | 'NBSP' | 'AMP' | '_' | 'SENTENCEEND' | 'W' | 'PM' | 'ANY' | 'ALL' | 'SPACE' | 'BREAK' );";
    }
  }

  static final String DFA22_eotS = "\2\uffff\16\32\2\120\4\32\2\127\1\132\15\uffff\1\135\1\137\1\141"
          + "\1\143\4\uffff\13\32\1\162\2\32\1\165\3\32\1\120\1\32\1\120\6\32"
          + "\1\120\6\32\1\uffff\6\32\2\uffff\1\127\12\uffff\5\32\1\u0098\1\32"
          + "\1\u009a\3\120\2\32\1\u009d\1\uffff\2\32\1\uffff\2\32\1\120\3\32"
          + "\1\u00a5\7\32\1\u00ad\2\32\1\120\3\32\1\u00b3\4\32\1\u00b8\4\32"
          + "\1\u00bd\1\u00be\1\32\1\uffff\1\u00c1\1\uffff\2\32\1\uffff\1\u00c4"
          + "\2\32\1\u00c7\3\32\1\uffff\1\u00cb\6\32\1\uffff\2\32\1\120\2\32"
          + "\1\uffff\4\32\1\uffff\1\u00da\3\32\2\uffff\1\u00de\1\32\1\uffff"
          + "\2\32\1\uffff\2\32\1\uffff\2\120\1\32\1\uffff\1\32\1\120\11\32\1"
          + "\120\1\u00ef\1\32\1\uffff\1\u00f1\1\32\1\u00f3\1\uffff\1\32\1\u00f5"
          + "\1\u00f6\1\120\1\u00f7\4\32\1\u00fc\1\u00fd\1\120\3\32\1\u0101\1"
          + "\uffff\1\32\1\uffff\1\u0103\1\uffff\1\32\3\uffff\1\32\1\120\2\32"
          + "\2\uffff\1\u0108\2\32\1\uffff\1\u010b\1\uffff\4\32\1\uffff\1\120"
          + "\1\32\1\uffff\1\32\1\u0112\1\120\2\32\1\u0115\1\uffff\2\32\1\uffff" + "\2\120";

  static final String DFA22_eofS = "\u0118\uffff";

  static final String DFA22_minS = "\1\11\1\uffff\1\105\1\111\1\101\1\103\1\106\1\101\1\122\1\101\1"
          + "\111\1\103\1\101\1\102\1\125\1\116\2\44\1\114\1\117\1\162\1\141"
          + "\2\56\1\60\15\uffff\1\52\3\75\4\uffff\1\103\1\125\1\123\1\107\1"
          + "\102\1\120\1\104\1\120\1\114\1\124\1\114\1\44\1\106\1\122\1\44\1"
          + "\105\2\114\1\44\1\114\1\44\1\101\1\115\2\122\1\116\1\122\1\44\1"
          + "\103\1\115\1\123\1\105\1\103\1\107\1\uffff\1\105\2\117\1\122\1\165"
          + "\1\154\2\uffff\1\56\12\uffff\1\114\1\102\1\124\1\116\1\114\1\44"
          + "\1\105\4\44\2\111\1\44\1\uffff\1\116\1\113\1\uffff\1\101\1\114\1"
          + "\44\1\117\1\115\1\104\1\44\1\114\2\103\1\111\1\124\2\111\1\44\1"
          + "\111\1\113\1\44\1\120\1\123\1\114\1\44\1\111\1\101\1\103\1\114\1"
          + "\44\1\145\1\163\1\101\1\114\2\44\1\105\1\uffff\1\44\1\uffff\1\117"
          + "\1\123\1\uffff\1\44\1\125\1\124\1\44\1\116\1\101\1\111\1\uffff\1"
          + "\44\1\111\1\105\1\103\1\105\1\120\1\116\1\uffff\1\117\1\101\1\44"
          + "\1\124\1\101\1\uffff\1\116\2\113\1\105\1\uffff\1\44\1\145\1\122"
          + "\1\105\2\uffff\1\44\1\131\1\uffff\1\116\1\124\1\uffff\1\120\1\105"
          + "\1\uffff\2\44\1\124\1\uffff\1\101\1\44\1\117\1\116\1\124\1\107\1"
          + "\104\1\107\1\111\1\115\1\105\2\44\1\101\1\uffff\1\44\1\105\1\44"
          + "\1\uffff\1\123\4\44\1\111\2\114\1\103\3\44\1\105\1\117\1\101\1\44"
          + "\1\uffff\1\116\1\uffff\1\44\1\uffff\1\124\3\uffff\1\117\1\44\1\117"
          + "\1\105\2\uffff\1\44\1\116\1\124\1\uffff\1\44\1\uffff\1\105\2\116"
          + "\1\105\1\uffff\1\44\1\111\1\uffff\1\115\2\44\1\116\1\117\1\44\1"
          + "\uffff\1\104\1\116\1\uffff\2\44";

  static final String DFA22_maxS = "\1\ufaff\1\uffff\2\117\1\131\2\116\1\117\1\122\1\127\1\111\1\127"
          + "\1\115\2\125\1\130\2\ufaff\1\122\1\117\1\162\1\141\3\71\15\uffff"
          + "\1\57\3\75\4\uffff\1\103\1\125\1\123\1\107\1\116\1\120\1\131\1\120"
          + "\1\114\2\124\1\ufaff\1\106\1\122\1\ufaff\1\105\1\120\1\123\1\ufaff"
          + "\1\114\1\ufaff\1\105\1\116\2\122\1\116\1\122\1\ufaff\1\103\1\115"
          + "\1\123\1\105\1\120\1\107\1\uffff\1\105\2\117\1\122\1\165\1\154\2"
          + "\uffff\1\71\12\uffff\1\114\1\102\1\124\1\116\1\114\1\ufaff\1\105"
          + "\4\ufaff\2\111\1\ufaff\1\uffff\1\116\1\113\1\uffff\1\101\1\114\1"
          + "\ufaff\1\117\1\115\1\104\1\ufaff\1\114\2\103\1\111\1\124\2\111\1"
          + "\ufaff\1\111\1\113\1\ufaff\1\120\1\123\1\114\1\ufaff\1\111\1\101"
          + "\1\103\1\114\1\ufaff\1\145\1\163\1\101\1\114\2\ufaff\1\105\1\uffff"
          + "\1\ufaff\1\uffff\1\117\1\123\1\uffff\1\ufaff\1\125\1\124\1\ufaff"
          + "\1\116\1\101\1\111\1\uffff\1\ufaff\1\111\1\105\1\103\1\105\1\120"
          + "\1\116\1\uffff\1\117\1\101\1\ufaff\1\124\1\101\1\uffff\1\116\2\113"
          + "\1\105\1\uffff\1\ufaff\1\145\1\122\1\105\2\uffff\1\ufaff\1\131\1"
          + "\uffff\1\116\1\124\1\uffff\1\120\1\105\1\uffff\2\ufaff\1\124\1\uffff"
          + "\1\101\1\ufaff\1\117\1\116\1\124\1\107\1\104\1\107\1\111\1\115\1"
          + "\105\2\ufaff\1\101\1\uffff\1\ufaff\1\105\1\ufaff\1\uffff\1\123\4"
          + "\ufaff\1\111\2\114\1\103\3\ufaff\1\105\1\117\1\101\1\ufaff\1\uffff"
          + "\1\116\1\uffff\1\ufaff\1\uffff\1\124\3\uffff\1\117\1\ufaff\1\117"
          + "\1\105\2\uffff\1\ufaff\1\116\1\124\1\uffff\1\ufaff\1\uffff\1\105"
          + "\2\116\1\105\1\uffff\1\ufaff\1\111\1\uffff\1\115\2\ufaff\1\116\1"
          + "\117\1\ufaff\1\uffff\1\104\1\116\1\uffff\2\ufaff";

  static final String DFA22_acceptS = "\1\uffff\1\1\27\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\53\1"
          + "\55\1\56\1\57\1\60\1\61\4\uffff\1\66\1\67\1\71\1\74\42\uffff\1\15"
          + "\6\uffff\1\42\1\43\1\uffff\1\54\1\75\1\76\1\62\1\72\1\63\1\73\1"
          + "\64\1\70\1\65\16\uffff\1\11\2\uffff\1\10\42\uffff\1\36\1\uffff\1"
          + "\5\2\uffff\1\23\7\uffff\1\35\7\uffff\1\34\5\uffff\1\32\4\uffff\1"
          + "\37\4\uffff\1\3\1\33\2\uffff\1\22\2\uffff\1\7\2\uffff\1\14\3\uffff"
          + "\1\13\16\uffff\1\40\3\uffff\1\4\20\uffff\1\21\1\uffff\1\41\1\uffff"
          + "\1\24\1\uffff\1\31\1\6\1\12\4\uffff\1\17\1\25\3\uffff\1\20\1\uffff"
          + "\1\2\4\uffff\1\16\2\uffff\1\26\6\uffff\1\30\2\uffff\1\27\2\uffff";

  static final String DFA22_specialS = "\u0118\uffff}>";

  static final String[] DFA22_transitionS = {
      "\2\55\1\uffff\2\55\22\uffff\1\55\1\54\1\31\1\uffff\1\32\1\52"
              + "\2\uffff\1\33\1\34\1\45\1\43\1\41\1\44\1\30\1\46\1\26\11\27"
              + "\1\uffff\1\42\1\47\1\51\1\50\1\53\1\uffff\1\5\1\22\1\11\1\2"
              + "\1\17\1\12\2\32\1\6\2\32\1\3\1\7\1\15\1\10\1\14\1\16\1\32\1"
              + "\13\1\4\2\32\1\21\1\23\2\32\1\35\1\uffff\1\36\1\uffff\1\20\1"
              + "\uffff\5\32\1\25\15\32\1\24\6\32\1\37\1\1\1\40\102\uffff\27"
              + "\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff\u0150\32\u0170"
              + "\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff\u5200\32\u5900"
              + "\uffff\u0200\32",
      "",
      "\1\56\11\uffff\1\57",
      "\1\60\5\uffff\1\61",
      "\1\62\27\uffff\1\63",
      "\1\67\10\uffff\1\66\1\65\1\64",
      "\1\71\7\uffff\1\70",
      "\1\73\15\uffff\1\72",
      "\1\74",
      "\1\76\15\uffff\1\77\2\uffff\1\75\4\uffff\1\100",
      "\1\101",
      "\1\105\1\uffff\1\104\3\uffff\1\107\6\uffff\1\103\3\uffff\1" + "\106\2\uffff\1\102",
      "\1\112\3\uffff\1\110\7\uffff\1\111",
      "\1\114\22\uffff\1\113",
      "\1\115",
      "\1\117\11\uffff\1\116",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\122\2\uffff\1\123\2\uffff\1\121",
      "\1\124",
      "\1\125",
      "\1\126",
      "\1\130\1\uffff\12\130",
      "\1\130\1\uffff\12\131",
      "\12\130",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "\1\133\4\uffff\1\134",
      "\1\136",
      "\1\140",
      "\1\142",
      "",
      "",
      "",
      "",
      "\1\144",
      "\1\145",
      "\1\146",
      "\1\147",
      "\1\150\13\uffff\1\151",
      "\1\152",
      "\1\153\24\uffff\1\154",
      "\1\155",
      "\1\156",
      "\1\157",
      "\1\160\7\uffff\1\161",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\163",
      "\1\164",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\166",
      "\1\167\3\uffff\1\170",
      "\1\171\1\172\1\173\4\uffff\1\174",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\175",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\177\3\uffff\1\176",
      "\1\u0080\1\u0081",
      "\1\u0082",
      "\1\u0083",
      "\1\u0084",
      "\1\u0085",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u0086",
      "\1\u0087",
      "\1\u0088",
      "\1\u0089",
      "\1\u008a\14\uffff\1\u008b",
      "\1\u008c",
      "",
      "\1\u008d",
      "\1\u008e",
      "\1\u008f",
      "\1\u0090",
      "\1\u0091",
      "\1\u0092",
      "",
      "",
      "\1\130\1\uffff\12\131",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "\1\u0093",
      "\1\u0094",
      "\1\u0095",
      "\1\u0096",
      "\1\u0097",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u0099",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u009b",
      "\1\u009c",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u009e",
      "\1\u009f",
      "",
      "\1\u00a0",
      "\1\u00a1",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00a2",
      "\1\u00a3",
      "\1\u00a4",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00a6",
      "\1\u00a7",
      "\1\u00a8",
      "\1\u00a9",
      "\1\u00aa",
      "\1\u00ab",
      "\1\u00ac",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00ae",
      "\1\u00af",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00b0",
      "\1\u00b1",
      "\1\u00b2",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00b4",
      "\1\u00b5",
      "\1\u00b6",
      "\1\u00b7",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00b9",
      "\1\u00ba",
      "\1\u00bb",
      "\1\u00bc",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00bf",
      "",
      "\1\32\13\uffff\12\32\7\uffff\22\32\1\u00c0\7\32\4\uffff\1"
              + "\32\1\uffff\32\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08"
              + "\32\u1040\uffff\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e"
              + "\32\u10d2\uffff\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u00c2",
      "\1\u00c3",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00c5",
      "\1\u00c6",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00c8",
      "\1\u00c9",
      "\1\u00ca",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00cc",
      "\1\u00cd",
      "\1\u00ce",
      "\1\u00cf",
      "\1\u00d0",
      "\1\u00d1",
      "",
      "\1\u00d2",
      "\1\u00d3",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00d4",
      "\1\u00d5",
      "",
      "\1\u00d6",
      "\1\u00d7",
      "\1\u00d8",
      "\1\u00d9",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00db",
      "\1\u00dc",
      "\1\u00dd",
      "",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00df",
      "",
      "\1\u00e0",
      "\1\u00e1",
      "",
      "\1\u00e2",
      "\1\u00e3",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00e4",
      "",
      "\1\u00e5",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00e6",
      "\1\u00e7",
      "\1\u00e8",
      "\1\u00e9",
      "\1\u00ea",
      "\1\u00eb",
      "\1\u00ec",
      "\1\u00ed",
      "\1\u00ee",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00f0",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00f2",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u00f4",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00f8",
      "\1\u00f9",
      "\1\u00fa",
      "\1\u00fb",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u00fe",
      "\1\u00ff",
      "\1\u0100",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u0102",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u0104",
      "",
      "",
      "",
      "\1\u0105",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u0106",
      "\1\u0107",
      "",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u0109",
      "\1\u010a",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u010c",
      "\1\u010d",
      "\1\u010e",
      "\1\u010f",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u0110",
      "",
      "\1\u0111",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\u0113",
      "\1\u0114",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "",
      "\1\u0116",
      "\1\u0117",
      "",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32",
      "\1\32\13\uffff\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32"
              + "\32\105\uffff\27\32\1\uffff\37\32\1\uffff\u1f08\32\u1040\uffff"
              + "\u0150\32\u0170\uffff\u0080\32\u0080\uffff\u092e\32\u10d2\uffff"
              + "\u5200\32\u5900\uffff\u0200\32" };

  static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);

  static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);

  static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);

  static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);

  static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);

  static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);

  static final short[][] DFA22_transition;

  static {
    int numStates = DFA22_transitionS.length;
    DFA22_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
    }
  }

  class DFA22 extends DFA {

    public DFA22(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 22;
      this.eot = DFA22_eot;
      this.eof = DFA22_eof;
      this.min = DFA22_min;
      this.max = DFA22_max;
      this.accept = DFA22_accept;
      this.special = DFA22_special;
      this.transition = DFA22_transition;
    }

    public String getDescription() {
      return "1:1: Tokens : ( T__77 | DECLARE | LIST | TABLE | AND | INLIST | MOFN | OR | IF | CREATE | FILL | CALL | BasicAnnotationType | PackageString | ScriptString | EngineString | BlockString | TypeString | IntString | DoubleString | StringString | BooleanString | TypeSystemString | ConditionString | ACTION | EXP | LOGN | SIN | COS | TAN | XOR | TRUE | FALSE | DecimalLiteral | FloatingPointLiteral | StringLiteral | Identifier | LPAREN | RPAREN | LBRACK | RBRACK | LCURLY | RCURLY | DOT | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | LESS | GREATER | ASSIGN_EQUAL | PERCENT | QUESTION | EQUAL | NOTEQUAL | LESSEQUAL | GREATEREQUAL | WS | COMMENT | LINE_COMMENT );";
    }
  }

}
