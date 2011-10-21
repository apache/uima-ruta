// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g 2011-10-21 15:04:41

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

	package org.apache.uima.textmarker.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TextMarkerLexer extends Lexer {
    public static final int EOF=-1;
    public static final int ACTION=4;
    public static final int ADD=5;
    public static final int AFTER=6;
    public static final int ALT_NOTEQUAL=7;
    public static final int AMPER=8;
    public static final int AND=9;
    public static final int ASSIGN=10;
    public static final int ASSIGN_EQUAL=11;
    public static final int AT=12;
    public static final int ATTRIBUTE=13;
    public static final int Annotation=14;
    public static final int AutomataBlockString=15;
    public static final int BEFORE=16;
    public static final int BOOLEANLIST=17;
    public static final int BasicAnnotationType=18;
    public static final int BlockString=19;
    public static final int BooleanString=20;
    public static final int CALL=21;
    public static final int CIRCUMFLEX=22;
    public static final int CLEAR=23;
    public static final int COLON=24;
    public static final int COLOR=25;
    public static final int COMMA=26;
    public static final int COMMENT=27;
    public static final int CONDITION=28;
    public static final int CONFIGURE=29;
    public static final int CONTAINS=30;
    public static final int CONTEXTCOUNT=31;
    public static final int COS=32;
    public static final int COUNT=33;
    public static final int CREATE=34;
    public static final int CURRENTCOUNT=35;
    public static final int CharacterLiteral=36;
    public static final int DECLARE=37;
    public static final int DEL=38;
    public static final int DOT=39;
    public static final int DOUBLELIST=40;
    public static final int DYNAMICANCHORING=41;
    public static final int DecimalLiteral=42;
    public static final int DocComment=43;
    public static final int DoubleString=44;
    public static final int ENDSWITH=45;
    public static final int EQUAL=46;
    public static final int EXEC=47;
    public static final int EXP=48;
    public static final int EXPAND=49;
    public static final int EngineString=50;
    public static final int EscapeSequence=51;
    public static final int Exponent=52;
    public static final int FALSE=53;
    public static final int FEATURE=54;
    public static final int FILL=55;
    public static final int FILTERMARKUP=56;
    public static final int FILTERTYPE=57;
    public static final int FloatTypeSuffix=58;
    public static final int FloatingPointLiteral=59;
    public static final int GATHER=60;
    public static final int GET=61;
    public static final int GETFEATURE=62;
    public static final int GETLIST=63;
    public static final int GREATER=64;
    public static final int GREATEREQUAL=65;
    public static final int HexDigit=66;
    public static final int HexLiteral=67;
    public static final int IF=68;
    public static final int INLIST=69;
    public static final int INTLIST=70;
    public static final int IS=71;
    public static final int ISINTAG=72;
    public static final int Identifier=73;
    public static final int IntString=74;
    public static final int IntegerTypeSuffix=75;
    public static final int JavaIDDigit=76;
    public static final int LAST=77;
    public static final int LBRACK=78;
    public static final int LCURLY=79;
    public static final int LESS=80;
    public static final int LESSEQUAL=81;
    public static final int LINE_COMMENT=82;
    public static final int LOG=83;
    public static final int LOGN=84;
    public static final int LPAREN=85;
    public static final int Letter=86;
    public static final int ListIdentifier=87;
    public static final int LogLevel=88;
    public static final int MARK=89;
    public static final int MARKFAST=90;
    public static final int MARKLAST=91;
    public static final int MARKONCE=92;
    public static final int MARKSCORE=93;
    public static final int MARKTABLE=94;
    public static final int MATCHEDTEXT=95;
    public static final int MERGE=96;
    public static final int MINUS=97;
    public static final int MOFN=98;
    public static final int NEAR=99;
    public static final int NOT=100;
    public static final int NOTEQUAL=101;
    public static final int OR=102;
    public static final int OctalEscape=103;
    public static final int OctalLiteral=104;
    public static final int OldColor=105;
    public static final int PARSE=106;
    public static final int PARTOF=107;
    public static final int PARTOFNEQ=108;
    public static final int PERCENT=109;
    public static final int PLUS=110;
    public static final int POSITION=111;
    public static final int PackageString=112;
    public static final int QUESTION=113;
    public static final int RBRACK=114;
    public static final int RCURLY=115;
    public static final int REGEXP=116;
    public static final int REMOVE=117;
    public static final int REMOVEDUPLICATE=118;
    public static final int REMOVESTRING=119;
    public static final int REPLACE=120;
    public static final int RETAINMARKUP=121;
    public static final int RETAINTYPE=122;
    public static final int RPAREN=123;
    public static final int RessourceLiteral=124;
    public static final int SCORE=125;
    public static final int SEMI=126;
    public static final int SETFEATURE=127;
    public static final int SIN=128;
    public static final int SIZE=129;
    public static final int SLASH=130;
    public static final int STAR=131;
    public static final int STARTSWITH=132;
    public static final int STRINGLIST=133;
    public static final int ScriptString=134;
    public static final int StringLiteral=135;
    public static final int StringString=136;
    public static final int SymbolString=137;
    public static final int TAN=138;
    public static final int THEN=139;
    public static final int TOTALCOUNT=140;
    public static final int TRANSFER=141;
    public static final int TRIE=142;
    public static final int TRUE=143;
    public static final int TYPELIST=144;
    public static final int TypeString=145;
    public static final int TypeSystemString=146;
    public static final int UNMARK=147;
    public static final int UNMARKALL=148;
    public static final int UnicodeEscape=149;
    public static final int VBAR=150;
    public static final int VOTE=151;
    public static final int WORDLIST=152;
    public static final int WORDTABLE=153;
    public static final int WS=154;
    public static final int XOR=155;

    	public int implicitLineJoiningLevel = 0;
    	public int startPos=-1;
    	public void emitErrorMessage(String msg) {
    	}


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public TextMarkerLexer() {} 
    public TextMarkerLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TextMarkerLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g"; }

    // $ANTLR start "TRIE"
    public final void mTRIE() throws RecognitionException {
        try {
            int _type = TRIE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:45:2: ( 'TRIE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:45:4: 'TRIE'
            {
            match("TRIE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRIE"

    // $ANTLR start "CONTAINS"
    public final void mCONTAINS() throws RecognitionException {
        try {
            int _type = CONTAINS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:49:2: ( 'CONTAINS' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:49:4: 'CONTAINS'
            {
            match("CONTAINS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTAINS"

    // $ANTLR start "DECLARE"
    public final void mDECLARE() throws RecognitionException {
        try {
            int _type = DECLARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:53:2: ( 'DECLARE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:53:4: 'DECLARE'
            {
            match("DECLARE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECLARE"

    // $ANTLR start "WORDLIST"
    public final void mWORDLIST() throws RecognitionException {
        try {
            int _type = WORDLIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:57:2: ( 'WORDLIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:57:4: 'WORDLIST'
            {
            match("WORDLIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WORDLIST"

    // $ANTLR start "WORDTABLE"
    public final void mWORDTABLE() throws RecognitionException {
        try {
            int _type = WORDTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:61:2: ( 'WORDTABLE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:61:4: 'WORDTABLE'
            {
            match("WORDTABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WORDTABLE"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:65:2: ( 'AND' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:65:4: 'AND'
            {
            match("AND"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "CONTEXTCOUNT"
    public final void mCONTEXTCOUNT() throws RecognitionException {
        try {
            int _type = CONTEXTCOUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:69:2: ( 'CONTEXTCOUNT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:69:4: 'CONTEXTCOUNT'
            {
            match("CONTEXTCOUNT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTEXTCOUNT"

    // $ANTLR start "COUNT"
    public final void mCOUNT() throws RecognitionException {
        try {
            int _type = COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:73:2: ( 'COUNT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:73:4: 'COUNT'
            {
            match("COUNT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COUNT"

    // $ANTLR start "TOTALCOUNT"
    public final void mTOTALCOUNT() throws RecognitionException {
        try {
            int _type = TOTALCOUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:77:2: ( 'TOTALCOUNT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:77:4: 'TOTALCOUNT'
            {
            match("TOTALCOUNT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TOTALCOUNT"

    // $ANTLR start "CURRENTCOUNT"
    public final void mCURRENTCOUNT() throws RecognitionException {
        try {
            int _type = CURRENTCOUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:81:2: ( 'CURRENTCOUNT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:81:4: 'CURRENTCOUNT'
            {
            match("CURRENTCOUNT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CURRENTCOUNT"

    // $ANTLR start "INLIST"
    public final void mINLIST() throws RecognitionException {
        try {
            int _type = INLIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:85:2: ( 'INLIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:85:4: 'INLIST'
            {
            match("INLIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INLIST"

    // $ANTLR start "ISINTAG"
    public final void mISINTAG() throws RecognitionException {
        try {
            int _type = ISINTAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:89:2: ( 'ISINTAG' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:89:4: 'ISINTAG'
            {
            match("ISINTAG"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ISINTAG"

    // $ANTLR start "LAST"
    public final void mLAST() throws RecognitionException {
        try {
            int _type = LAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:93:2: ( 'LAST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:93:4: 'LAST'
            {
            match("LAST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LAST"

    // $ANTLR start "MOFN"
    public final void mMOFN() throws RecognitionException {
        try {
            int _type = MOFN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:97:2: ( 'MOFN' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:97:4: 'MOFN'
            {
            match("MOFN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MOFN"

    // $ANTLR start "NEAR"
    public final void mNEAR() throws RecognitionException {
        try {
            int _type = NEAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:101:2: ( 'NEAR' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:101:4: 'NEAR'
            {
            match("NEAR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEAR"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:105:2: ( 'OR' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:105:4: 'OR'
            {
            match("OR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "PARTOF"
    public final void mPARTOF() throws RecognitionException {
        try {
            int _type = PARTOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:109:2: ( 'PARTOF' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:109:4: 'PARTOF'
            {
            match("PARTOF"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PARTOF"

    // $ANTLR start "PARTOFNEQ"
    public final void mPARTOFNEQ() throws RecognitionException {
        try {
            int _type = PARTOFNEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:113:2: ( 'PARTOFNEQ' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:113:4: 'PARTOFNEQ'
            {
            match("PARTOFNEQ"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PARTOFNEQ"

    // $ANTLR start "POSITION"
    public final void mPOSITION() throws RecognitionException {
        try {
            int _type = POSITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:117:2: ( 'POSITION' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:117:4: 'POSITION'
            {
            match("POSITION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "POSITION"

    // $ANTLR start "REGEXP"
    public final void mREGEXP() throws RecognitionException {
        try {
            int _type = REGEXP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:121:2: ( 'REGEXP' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:121:4: 'REGEXP'
            {
            match("REGEXP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REGEXP"

    // $ANTLR start "SCORE"
    public final void mSCORE() throws RecognitionException {
        try {
            int _type = SCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:125:2: ( 'SCORE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:125:4: 'SCORE'
            {
            match("SCORE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SCORE"

    // $ANTLR start "VOTE"
    public final void mVOTE() throws RecognitionException {
        try {
            int _type = VOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:129:2: ( 'VOTE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:129:4: 'VOTE'
            {
            match("VOTE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VOTE"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:133:2: ( 'IF' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:133:4: 'IF'
            {
            match("IF"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "FEATURE"
    public final void mFEATURE() throws RecognitionException {
        try {
            int _type = FEATURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:137:2: ( 'FEATURE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:137:4: 'FEATURE'
            {
            match("FEATURE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FEATURE"

    // $ANTLR start "PARSE"
    public final void mPARSE() throws RecognitionException {
        try {
            int _type = PARSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:141:2: ( 'PARSE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:141:4: 'PARSE'
            {
            match("PARSE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PARSE"

    // $ANTLR start "CREATE"
    public final void mCREATE() throws RecognitionException {
        try {
            int _type = CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:145:2: ( 'CREATE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:145:4: 'CREATE'
            {
            match("CREATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CREATE"

    // $ANTLR start "GATHER"
    public final void mGATHER() throws RecognitionException {
        try {
            int _type = GATHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:149:2: ( 'GATHER' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:149:4: 'GATHER'
            {
            match("GATHER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GATHER"

    // $ANTLR start "FILL"
    public final void mFILL() throws RecognitionException {
        try {
            int _type = FILL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:153:2: ( 'FILL' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:153:4: 'FILL'
            {
            match("FILL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILL"

    // $ANTLR start "ATTRIBUTE"
    public final void mATTRIBUTE() throws RecognitionException {
        try {
            int _type = ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:157:2: ( 'ATTRIBUTE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:157:4: 'ATTRIBUTE'
            {
            match("ATTRIBUTE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ATTRIBUTE"

    // $ANTLR start "COLOR"
    public final void mCOLOR() throws RecognitionException {
        try {
            int _type = COLOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:161:2: ( 'COLOR' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:161:4: 'COLOR'
            {
            match("COLOR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLOR"

    // $ANTLR start "DEL"
    public final void mDEL() throws RecognitionException {
        try {
            int _type = DEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:165:2: ( 'DEL' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:165:4: 'DEL'
            {
            match("DEL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEL"

    // $ANTLR start "LOG"
    public final void mLOG() throws RecognitionException {
        try {
            int _type = LOG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:169:2: ( 'LOG' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:169:4: 'LOG'
            {
            match("LOG"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LOG"

    // $ANTLR start "MARK"
    public final void mMARK() throws RecognitionException {
        try {
            int _type = MARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:173:2: ( 'MARK' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:173:4: 'MARK'
            {
            match("MARK"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MARK"

    // $ANTLR start "MARKSCORE"
    public final void mMARKSCORE() throws RecognitionException {
        try {
            int _type = MARKSCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:177:2: ( 'MARKSCORE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:177:4: 'MARKSCORE'
            {
            match("MARKSCORE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MARKSCORE"

    // $ANTLR start "MARKONCE"
    public final void mMARKONCE() throws RecognitionException {
        try {
            int _type = MARKONCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:181:2: ( 'MARKONCE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:181:4: 'MARKONCE'
            {
            match("MARKONCE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MARKONCE"

    // $ANTLR start "MARKFAST"
    public final void mMARKFAST() throws RecognitionException {
        try {
            int _type = MARKFAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:185:2: ( 'MARKFAST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:185:4: 'MARKFAST'
            {
            match("MARKFAST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MARKFAST"

    // $ANTLR start "MARKTABLE"
    public final void mMARKTABLE() throws RecognitionException {
        try {
            int _type = MARKTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:189:2: ( 'MARKTABLE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:189:4: 'MARKTABLE'
            {
            match("MARKTABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MARKTABLE"

    // $ANTLR start "MARKLAST"
    public final void mMARKLAST() throws RecognitionException {
        try {
            int _type = MARKLAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:193:2: ( 'MARKLAST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:193:4: 'MARKLAST'
            {
            match("MARKLAST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MARKLAST"

    // $ANTLR start "REPLACE"
    public final void mREPLACE() throws RecognitionException {
        try {
            int _type = REPLACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:197:2: ( 'REPLACE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:197:4: 'REPLACE'
            {
            match("REPLACE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REPLACE"

    // $ANTLR start "RETAINMARKUP"
    public final void mRETAINMARKUP() throws RecognitionException {
        try {
            int _type = RETAINMARKUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:201:2: ( 'RETAINMARKUP' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:201:4: 'RETAINMARKUP'
            {
            match("RETAINMARKUP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RETAINMARKUP"

    // $ANTLR start "RETAINTYPE"
    public final void mRETAINTYPE() throws RecognitionException {
        try {
            int _type = RETAINTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:205:2: ( 'RETAINTYPE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:205:4: 'RETAINTYPE'
            {
            match("RETAINTYPE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RETAINTYPE"

    // $ANTLR start "FILTERMARKUP"
    public final void mFILTERMARKUP() throws RecognitionException {
        try {
            int _type = FILTERMARKUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:209:2: ( 'FILTERMARKUP' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:209:4: 'FILTERMARKUP'
            {
            match("FILTERMARKUP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILTERMARKUP"

    // $ANTLR start "FILTERTYPE"
    public final void mFILTERTYPE() throws RecognitionException {
        try {
            int _type = FILTERTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:213:2: ( 'FILTERTYPE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:213:4: 'FILTERTYPE'
            {
            match("FILTERTYPE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILTERTYPE"

    // $ANTLR start "CALL"
    public final void mCALL() throws RecognitionException {
        try {
            int _type = CALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:217:2: ( 'CALL' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:217:4: 'CALL'
            {
            match("CALL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CALL"

    // $ANTLR start "EXEC"
    public final void mEXEC() throws RecognitionException {
        try {
            int _type = EXEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:222:2: ( 'EXEC' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:222:4: 'EXEC'
            {
            match("EXEC"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXEC"

    // $ANTLR start "CONFIGURE"
    public final void mCONFIGURE() throws RecognitionException {
        try {
            int _type = CONFIGURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:226:2: ( 'CONFIGURE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:226:4: 'CONFIGURE'
            {
            match("CONFIGURE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONFIGURE"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:230:2: ( 'ASSIGN' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:230:4: 'ASSIGN'
            {
            match("ASSIGN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "SETFEATURE"
    public final void mSETFEATURE() throws RecognitionException {
        try {
            int _type = SETFEATURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:234:2: ( 'SETFEATURE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:234:4: 'SETFEATURE'
            {
            match("SETFEATURE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SETFEATURE"

    // $ANTLR start "GETFEATURE"
    public final void mGETFEATURE() throws RecognitionException {
        try {
            int _type = GETFEATURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:238:2: ( 'GETFEATURE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:238:4: 'GETFEATURE'
            {
            match("GETFEATURE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GETFEATURE"

    // $ANTLR start "UNMARK"
    public final void mUNMARK() throws RecognitionException {
        try {
            int _type = UNMARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:242:2: ( 'UNMARK' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:242:4: 'UNMARK'
            {
            match("UNMARK"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNMARK"

    // $ANTLR start "UNMARKALL"
    public final void mUNMARKALL() throws RecognitionException {
        try {
            int _type = UNMARKALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:246:2: ( 'UNMARKALL' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:246:4: 'UNMARKALL'
            {
            match("UNMARKALL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNMARKALL"

    // $ANTLR start "TRANSFER"
    public final void mTRANSFER() throws RecognitionException {
        try {
            int _type = TRANSFER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:250:2: ( 'TRANSFER' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:250:4: 'TRANSFER'
            {
            match("TRANSFER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRANSFER"

    // $ANTLR start "EXPAND"
    public final void mEXPAND() throws RecognitionException {
        try {
            int _type = EXPAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:255:2: ( 'EXPAND' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:255:4: 'EXPAND'
            {
            match("EXPAND"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPAND"

    // $ANTLR start "DYNAMICANCHORING"
    public final void mDYNAMICANCHORING() throws RecognitionException {
        try {
            int _type = DYNAMICANCHORING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:259:2: ( 'DYNAMICANCHORING' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:259:4: 'DYNAMICANCHORING'
            {
            match("DYNAMICANCHORING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DYNAMICANCHORING"

    // $ANTLR start "BEFORE"
    public final void mBEFORE() throws RecognitionException {
        try {
            int _type = BEFORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:263:2: ( 'BEFORE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:263:4: 'BEFORE'
            {
            match("BEFORE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BEFORE"

    // $ANTLR start "AFTER"
    public final void mAFTER() throws RecognitionException {
        try {
            int _type = AFTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:267:2: ( 'AFTER' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:267:4: 'AFTER'
            {
            match("AFTER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AFTER"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:271:2: ( 'IS' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:271:4: 'IS'
            {
            match("IS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "STARTSWITH"
    public final void mSTARTSWITH() throws RecognitionException {
        try {
            int _type = STARTSWITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:276:2: ( 'STARTSWITH' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:276:4: 'STARTSWITH'
            {
            match("STARTSWITH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STARTSWITH"

    // $ANTLR start "ENDSWITH"
    public final void mENDSWITH() throws RecognitionException {
        try {
            int _type = ENDSWITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:280:2: ( 'ENDSWITH' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:280:4: 'ENDSWITH'
            {
            match("ENDSWITH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENDSWITH"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:286:2: ( 'NOT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:286:4: 'NOT'
            {
            match("NOT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "ADD"
    public final void mADD() throws RecognitionException {
        try {
            int _type = ADD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:289:5: ( 'ADD' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:289:7: 'ADD'
            {
            match("ADD"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ADD"

    // $ANTLR start "REMOVE"
    public final void mREMOVE() throws RecognitionException {
        try {
            int _type = REMOVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:290:8: ( 'REMOVE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:290:10: 'REMOVE'
            {
            match("REMOVE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REMOVE"

    // $ANTLR start "REMOVEDUPLICATE"
    public final void mREMOVEDUPLICATE() throws RecognitionException {
        try {
            int _type = REMOVEDUPLICATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:291:17: ( 'REMOVEDUPLICATE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:291:19: 'REMOVEDUPLICATE'
            {
            match("REMOVEDUPLICATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REMOVEDUPLICATE"

    // $ANTLR start "MERGE"
    public final void mMERGE() throws RecognitionException {
        try {
            int _type = MERGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:292:8: ( 'MERGE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:292:10: 'MERGE'
            {
            match("MERGE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MERGE"

    // $ANTLR start "GET"
    public final void mGET() throws RecognitionException {
        try {
            int _type = GET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:293:5: ( 'GET' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:293:7: 'GET'
            {
            match("GET"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GET"

    // $ANTLR start "GETLIST"
    public final void mGETLIST() throws RecognitionException {
        try {
            int _type = GETLIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:294:9: ( 'GETLIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:294:11: 'GETLIST'
            {
            match("GETLIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GETLIST"

    // $ANTLR start "SIZE"
    public final void mSIZE() throws RecognitionException {
        try {
            int _type = SIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:295:6: ( 'SIZE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:295:8: 'SIZE'
            {
            match("SIZE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIZE"

    // $ANTLR start "MATCHEDTEXT"
    public final void mMATCHEDTEXT() throws RecognitionException {
        try {
            int _type = MATCHEDTEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:296:13: ( 'MATCHEDTEXT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:296:15: 'MATCHEDTEXT'
            {
            match("MATCHEDTEXT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MATCHEDTEXT"

    // $ANTLR start "REMOVESTRING"
    public final void mREMOVESTRING() throws RecognitionException {
        try {
            int _type = REMOVESTRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:297:14: ( 'REMOVESTRING' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:297:16: 'REMOVESTRING'
            {
            match("REMOVESTRING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REMOVESTRING"

    // $ANTLR start "CLEAR"
    public final void mCLEAR() throws RecognitionException {
        try {
            int _type = CLEAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:298:8: ( 'CLEAR' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:298:11: 'CLEAR'
            {
            match("CLEAR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLEAR"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:301:2: ( '->' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:301:5: '->'
            {
            match("->"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "BasicAnnotationType"
    public final void mBasicAnnotationType() throws RecognitionException {
        try {
            int _type = BasicAnnotationType;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:2: ( 'COLON' | 'SW' | 'MARKUP' | 'PERIOD' | 'CW' | 'NUM' | 'QUESTION' | 'SPECIAL' | 'CAP' | 'COMMA' | 'EXCLAMATION' | 'SEMICOLON' | 'NBSP' | 'AMP' | '_' | 'SENTENCEEND' | 'W' | 'PM' | 'ANY' | 'ALL' | 'SPACE' | 'BREAK' )
            int alt1=22;
            switch ( input.LA(1) ) {
            case 'C':
                {
                switch ( input.LA(2) ) {
                case 'O':
                    {
                    int LA1_12 = input.LA(3);

                    if ( (LA1_12=='L') ) {
                        alt1=1;
                    }
                    else if ( (LA1_12=='M') ) {
                        alt1=10;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 12, input);

                        throw nvae;

                    }
                    }
                    break;
                case 'W':
                    {
                    alt1=5;
                    }
                    break;
                case 'A':
                    {
                    alt1=9;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }

                }
                break;
            case 'S':
                {
                switch ( input.LA(2) ) {
                case 'W':
                    {
                    alt1=2;
                    }
                    break;
                case 'P':
                    {
                    int LA1_16 = input.LA(3);

                    if ( (LA1_16=='E') ) {
                        alt1=8;
                    }
                    else if ( (LA1_16=='A') ) {
                        alt1=21;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 16, input);

                        throw nvae;

                    }
                    }
                    break;
                case 'E':
                    {
                    int LA1_17 = input.LA(3);

                    if ( (LA1_17=='M') ) {
                        alt1=12;
                    }
                    else if ( (LA1_17=='N') ) {
                        alt1=16;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 17, input);

                        throw nvae;

                    }
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;

                }

                }
                break;
            case 'M':
                {
                alt1=3;
                }
                break;
            case 'P':
                {
                int LA1_4 = input.LA(2);

                if ( (LA1_4=='E') ) {
                    alt1=4;
                }
                else if ( (LA1_4=='M') ) {
                    alt1=18;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 4, input);

                    throw nvae;

                }
                }
                break;
            case 'N':
                {
                int LA1_5 = input.LA(2);

                if ( (LA1_5=='U') ) {
                    alt1=6;
                }
                else if ( (LA1_5=='B') ) {
                    alt1=13;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 5, input);

                    throw nvae;

                }
                }
                break;
            case 'Q':
                {
                alt1=7;
                }
                break;
            case 'E':
                {
                alt1=11;
                }
                break;
            case 'A':
                {
                switch ( input.LA(2) ) {
                case 'M':
                    {
                    alt1=14;
                    }
                    break;
                case 'N':
                    {
                    alt1=19;
                    }
                    break;
                case 'L':
                    {
                    alt1=20;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 8, input);

                    throw nvae;

                }

                }
                break;
            case '_':
                {
                alt1=15;
                }
                break;
            case 'W':
                {
                alt1=17;
                }
                break;
            case 'B':
                {
                alt1=22;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:4: 'COLON'
                    {
                    match("COLON"); 



                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:13: 'SW'
                    {
                    match("SW"); 



                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:20: 'MARKUP'
                    {
                    match("MARKUP"); 



                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:31: 'PERIOD'
                    {
                    match("PERIOD"); 



                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:42: 'CW'
                    {
                    match("CW"); 



                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:48: 'NUM'
                    {
                    match("NUM"); 



                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:56: 'QUESTION'
                    {
                    match("QUESTION"); 



                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:69: 'SPECIAL'
                    {
                    match("SPECIAL"); 



                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:81: 'CAP'
                    {
                    match("CAP"); 



                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:89: 'COMMA'
                    {
                    match("COMMA"); 



                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:99: 'EXCLAMATION'
                    {
                    match("EXCLAMATION"); 



                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:115: 'SEMICOLON'
                    {
                    match("SEMICOLON"); 



                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:129: 'NBSP'
                    {
                    match("NBSP"); 



                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:305:137: 'AMP'
                    {
                    match("AMP"); 



                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:2: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:8: 'SENTENCEEND'
                    {
                    match("SENTENCEEND"); 



                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:24: 'W'
                    {
                    match('W'); 

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:30: 'PM'
                    {
                    match("PM"); 



                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:37: 'ANY'
                    {
                    match("ANY"); 



                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:45: 'ALL'
                    {
                    match("ALL"); 



                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:53: 'SPACE'
                    {
                    match("SPACE"); 



                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:306:63: 'BREAK'
                    {
                    match("BREAK"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BasicAnnotationType"

    // $ANTLR start "LogLevel"
    public final void mLogLevel() throws RecognitionException {
        try {
            int _type = LogLevel;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:309:9: ( 'finest' | 'finer' | 'fine' | 'config' | 'info' | 'warning' | 'severe' )
            int alt2=7;
            switch ( input.LA(1) ) {
            case 'f':
                {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='i') ) {
                    int LA2_6 = input.LA(3);

                    if ( (LA2_6=='n') ) {
                        int LA2_7 = input.LA(4);

                        if ( (LA2_7=='e') ) {
                            switch ( input.LA(5) ) {
                            case 's':
                                {
                                alt2=1;
                                }
                                break;
                            case 'r':
                                {
                                alt2=2;
                                }
                                break;
                            default:
                                alt2=3;
                            }

                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 2, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 6, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;

                }
                }
                break;
            case 'c':
                {
                alt2=4;
                }
                break;
            case 'i':
                {
                alt2=5;
                }
                break;
            case 'w':
                {
                alt2=6;
                }
                break;
            case 's':
                {
                alt2=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:2: 'finest'
                    {
                    match("finest"); 



                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:13: 'finer'
                    {
                    match("finer"); 



                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:23: 'fine'
                    {
                    match("fine"); 



                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:32: 'config'
                    {
                    match("config"); 



                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:43: 'info'
                    {
                    match("info"); 



                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:52: 'warning'
                    {
                    match("warning"); 



                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:310:64: 'severe'
                    {
                    match("severe"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LogLevel"

    // $ANTLR start "OldColor"
    public final void mOldColor() throws RecognitionException {
        try {
            int _type = OldColor;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:2: ( 'black' | 'maroon' | 'green' | 'olive' | 'navy' | 'purple' | 'teal' | 'gray' | 'silver' | 'red' | 'lime' | 'yellow' | 'blue' | 'fuchsia' | 'aqua' )
            int alt3=15;
            switch ( input.LA(1) ) {
            case 'b':
                {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='l') ) {
                    int LA3_14 = input.LA(3);

                    if ( (LA3_14=='a') ) {
                        alt3=1;
                    }
                    else if ( (LA3_14=='u') ) {
                        alt3=13;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 14, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }
                }
                break;
            case 'm':
                {
                alt3=2;
                }
                break;
            case 'g':
                {
                int LA3_3 = input.LA(2);

                if ( (LA3_3=='r') ) {
                    int LA3_15 = input.LA(3);

                    if ( (LA3_15=='e') ) {
                        alt3=3;
                    }
                    else if ( (LA3_15=='a') ) {
                        alt3=8;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 15, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;

                }
                }
                break;
            case 'o':
                {
                alt3=4;
                }
                break;
            case 'n':
                {
                alt3=5;
                }
                break;
            case 'p':
                {
                alt3=6;
                }
                break;
            case 't':
                {
                alt3=7;
                }
                break;
            case 's':
                {
                alt3=9;
                }
                break;
            case 'r':
                {
                alt3=10;
                }
                break;
            case 'l':
                {
                alt3=11;
                }
                break;
            case 'y':
                {
                alt3=12;
                }
                break;
            case 'f':
                {
                alt3=14;
                }
                break;
            case 'a':
                {
                alt3=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:4: 'black'
                    {
                    match("black"); 



                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:14: 'maroon'
                    {
                    match("maroon"); 



                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:25: 'green'
                    {
                    match("green"); 



                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:35: 'olive'
                    {
                    match("olive"); 



                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:45: 'navy'
                    {
                    match("navy"); 



                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:54: 'purple'
                    {
                    match("purple"); 



                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:65: 'teal'
                    {
                    match("teal"); 



                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:74: 'gray'
                    {
                    match("gray"); 



                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:83: 'silver'
                    {
                    match("silver"); 



                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:94: 'red'
                    {
                    match("red"); 



                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:102: 'lime'
                    {
                    match("lime"); 



                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:111: 'yellow'
                    {
                    match("yellow"); 



                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:122: 'blue'
                    {
                    match("blue"); 



                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:131: 'fuchsia'
                    {
                    match("fuchsia"); 



                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:314:143: 'aqua'
                    {
                    match("aqua"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OldColor"

    // $ANTLR start "PackageString"
    public final void mPackageString() throws RecognitionException {
        try {
            int _type = PackageString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:317:17: ( 'PACKAGE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:317:19: 'PACKAGE'
            {
            match("PACKAGE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PackageString"

    // $ANTLR start "ScriptString"
    public final void mScriptString() throws RecognitionException {
        try {
            int _type = ScriptString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:318:14: ( 'SCRIPT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:318:16: 'SCRIPT'
            {
            match("SCRIPT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ScriptString"

    // $ANTLR start "EngineString"
    public final void mEngineString() throws RecognitionException {
        try {
            int _type = EngineString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:319:14: ( 'ENGINE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:319:16: 'ENGINE'
            {
            match("ENGINE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EngineString"

    // $ANTLR start "BlockString"
    public final void mBlockString() throws RecognitionException {
        try {
            int _type = BlockString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:320:14: ( 'BLOCK' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:320:16: 'BLOCK'
            {
            match("BLOCK"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BlockString"

    // $ANTLR start "AutomataBlockString"
    public final void mAutomataBlockString() throws RecognitionException {
        try {
            int _type = AutomataBlockString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:321:22: ( 'RULES' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:321:24: 'RULES'
            {
            match("RULES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AutomataBlockString"

    // $ANTLR start "TypeString"
    public final void mTypeString() throws RecognitionException {
        try {
            int _type = TypeString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:322:13: ( 'TYPE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:322:15: 'TYPE'
            {
            match("TYPE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TypeString"

    // $ANTLR start "IntString"
    public final void mIntString() throws RecognitionException {
        try {
            int _type = IntString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:323:11: ( 'INT' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:323:13: 'INT'
            {
            match("INT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IntString"

    // $ANTLR start "DoubleString"
    public final void mDoubleString() throws RecognitionException {
        try {
            int _type = DoubleString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:324:14: ( 'DOUBLE' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:324:16: 'DOUBLE'
            {
            match("DOUBLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DoubleString"

    // $ANTLR start "StringString"
    public final void mStringString() throws RecognitionException {
        try {
            int _type = StringString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:325:14: ( 'STRING' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:325:16: 'STRING'
            {
            match("STRING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "StringString"

    // $ANTLR start "BooleanString"
    public final void mBooleanString() throws RecognitionException {
        try {
            int _type = BooleanString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:326:15: ( 'BOOLEAN' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:326:17: 'BOOLEAN'
            {
            match("BOOLEAN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BooleanString"

    // $ANTLR start "TypeSystemString"
    public final void mTypeSystemString() throws RecognitionException {
        try {
            int _type = TypeSystemString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:327:17: ( 'TYPESYSTEM' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:327:19: 'TYPESYSTEM'
            {
            match("TYPESYSTEM"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TypeSystemString"

    // $ANTLR start "SymbolString"
    public final void mSymbolString() throws RecognitionException {
        try {
            int _type = SymbolString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:328:14: ( 'SYMBOL' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:328:16: 'SYMBOL'
            {
            match("SYMBOL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SymbolString"

    // $ANTLR start "CONDITION"
    public final void mCONDITION() throws RecognitionException {
        try {
            int _type = CONDITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:329:11: ( 'CONDITION' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:329:13: 'CONDITION'
            {
            match("CONDITION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONDITION"

    // $ANTLR start "ACTION"
    public final void mACTION() throws RecognitionException {
        try {
            int _type = ACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:330:9: ( 'ACTION' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:330:11: 'ACTION'
            {
            match("ACTION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION"

    // $ANTLR start "BOOLEANLIST"
    public final void mBOOLEANLIST() throws RecognitionException {
        try {
            int _type = BOOLEANLIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:332:2: ( 'BOOLEANLIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:332:5: 'BOOLEANLIST'
            {
            match("BOOLEANLIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEANLIST"

    // $ANTLR start "INTLIST"
    public final void mINTLIST() throws RecognitionException {
        try {
            int _type = INTLIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:333:9: ( 'INTLIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:333:11: 'INTLIST'
            {
            match("INTLIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTLIST"

    // $ANTLR start "DOUBLELIST"
    public final void mDOUBLELIST() throws RecognitionException {
        try {
            int _type = DOUBLELIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:335:2: ( 'DOUBLELIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:335:5: 'DOUBLELIST'
            {
            match("DOUBLELIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOUBLELIST"

    // $ANTLR start "STRINGLIST"
    public final void mSTRINGLIST() throws RecognitionException {
        try {
            int _type = STRINGLIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:337:2: ( 'STRINGLIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:337:4: 'STRINGLIST'
            {
            match("STRINGLIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRINGLIST"

    // $ANTLR start "TYPELIST"
    public final void mTYPELIST() throws RecognitionException {
        try {
            int _type = TYPELIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:338:9: ( 'TYPELIST' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:338:11: 'TYPELIST'
            {
            match("TYPELIST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPELIST"

    // $ANTLR start "EXP"
    public final void mEXP() throws RecognitionException {
        try {
            int _type = EXP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:342:6: ( 'EXP' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:342:8: 'EXP'
            {
            match("EXP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXP"

    // $ANTLR start "LOGN"
    public final void mLOGN() throws RecognitionException {
        try {
            int _type = LOGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:343:6: ( 'LOGN' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:343:8: 'LOGN'
            {
            match("LOGN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LOGN"

    // $ANTLR start "SIN"
    public final void mSIN() throws RecognitionException {
        try {
            int _type = SIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:344:5: ( 'SIN' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:344:7: 'SIN'
            {
            match("SIN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIN"

    // $ANTLR start "COS"
    public final void mCOS() throws RecognitionException {
        try {
            int _type = COS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:345:5: ( 'COS' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:345:7: 'COS'
            {
            match("COS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COS"

    // $ANTLR start "TAN"
    public final void mTAN() throws RecognitionException {
        try {
            int _type = TAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:346:5: ( 'TAN' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:346:7: 'TAN'
            {
            match("TAN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TAN"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:347:5: ( 'XOR' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:347:8: 'XOR'
            {
            match("XOR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:348:7: ( 'true' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:348:9: 'true'
            {
            match("true"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:349:8: ( 'false' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:349:10: 'false'
            {
            match("false"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "HexLiteral"
    public final void mHexLiteral() throws RecognitionException {
        try {
            int _type = HexLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:351:12: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:351:14: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 

            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:351:28: ( HexDigit )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'F')||(LA4_0 >= 'a' && LA4_0 <= 'f')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:351:38: ( IntegerTypeSuffix )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='L'||LA5_0=='l') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexLiteral"

    // $ANTLR start "DecimalLiteral"
    public final void mDecimalLiteral() throws RecognitionException {
        try {
            int _type = DecimalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:16: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:18: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:18: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='0') ) {
                alt7=1;
            }
            else if ( ((LA7_0 >= '1' && LA7_0 <= '9')) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:19: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:25: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:34: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:353:45: ( IntegerTypeSuffix )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='L'||LA8_0=='l') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DecimalLiteral"

    // $ANTLR start "OctalLiteral"
    public final void mOctalLiteral() throws RecognitionException {
        try {
            int _type = OctalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:355:14: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:355:16: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:355:20: ( '0' .. '7' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= '0' && LA9_0 <= '7')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:355:32: ( IntegerTypeSuffix )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='L'||LA10_0=='l') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalLiteral"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:358:10: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "IntegerTypeSuffix"
    public final void mIntegerTypeSuffix() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:361:19: ( ( 'l' | 'L' ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IntegerTypeSuffix"

    // $ANTLR start "FloatingPointLiteral"
    public final void mFloatingPointLiteral() throws RecognitionException {
        try {
            int _type = FloatingPointLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0 >= '0' && LA18_0 <= '9')) ) {
                alt18=1;
            }
            else if ( (LA18_0=='.') ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:9: ( '0' .. '9' )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    match('.'); 

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:25: ( '0' .. '9' )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:37: ( Exponent )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:37: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:364:47: ( FloatTypeSuffix )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='D'||LA14_0=='F'||LA14_0=='d'||LA14_0=='f') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:365:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                    {
                    match('.'); 

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:365:13: ( '0' .. '9' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0 >= '0' && LA15_0 <= '9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:365:25: ( Exponent )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='E'||LA16_0=='e') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:365:25: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:365:35: ( FloatTypeSuffix )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='D'||LA17_0=='F'||LA17_0=='d'||LA17_0=='f') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FloatingPointLiteral"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:371:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:371:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:371:22: ( '+' | '-' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='+'||LA19_0=='-') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:371:33: ( '0' .. '9' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0 >= '0' && LA20_0 <= '9')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "FloatTypeSuffix"
    public final void mFloatTypeSuffix() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:374:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            {
            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FloatTypeSuffix"

    // $ANTLR start "CharacterLiteral"
    public final void mCharacterLiteral() throws RecognitionException {
        try {
            int _type = CharacterLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:377:5: ( '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\'' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:377:9: '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:377:14: ( EscapeSequence |~ ( '\\'' | '\\\\' ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\\') ) {
                alt21=1;
            }
            else if ( ((LA21_0 >= '\u0000' && LA21_0 <= '&')||(LA21_0 >= '(' && LA21_0 <= '[')||(LA21_0 >= ']' && LA21_0 <= '\uFFFF')) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:377:16: EscapeSequence
                    {
                    mEscapeSequence(); 


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:377:33: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CharacterLiteral"

    // $ANTLR start "StringLiteral"
    public final void mStringLiteral() throws RecognitionException {
        try {
            int _type = StringLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:381:5: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:381:8: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:381:12: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
            loop22:
            do {
                int alt22=3;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\\') ) {
                    alt22=1;
                }
                else if ( ((LA22_0 >= '\u0000' && LA22_0 <= '!')||(LA22_0 >= '#' && LA22_0 <= '[')||(LA22_0 >= ']' && LA22_0 <= '\uFFFF')) ) {
                    alt22=2;
                }


                switch (alt22) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:381:14: EscapeSequence
            	    {
            	    mEscapeSequence(); 


            	    }
            	    break;
            	case 2 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:381:31: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "StringLiteral"

    // $ANTLR start "RessourceLiteral"
    public final void mRessourceLiteral() throws RecognitionException {
        try {
            int _type = RessourceLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:385:5: ( '\\'' ( EscapeSequence |~ ( '\\\\' | '\\'' ) )* '\\'' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:385:8: '\\'' ( EscapeSequence |~ ( '\\\\' | '\\'' ) )* '\\''
            {
            match('\''); 

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:385:13: ( EscapeSequence |~ ( '\\\\' | '\\'' ) )*
            loop23:
            do {
                int alt23=3;
                int LA23_0 = input.LA(1);

                if ( (LA23_0=='\\') ) {
                    alt23=1;
                }
                else if ( ((LA23_0 >= '\u0000' && LA23_0 <= '&')||(LA23_0 >= '(' && LA23_0 <= '[')||(LA23_0 >= ']' && LA23_0 <= '\uFFFF')) ) {
                    alt23=2;
                }


                switch (alt23) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:385:15: EscapeSequence
            	    {
            	    mEscapeSequence(); 


            	    }
            	    break;
            	case 2 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:385:32: ~ ( '\\\\' | '\\'' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RessourceLiteral"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:390:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UnicodeEscape | OctalEscape )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt24=1;
                    }
                    break;
                case 'u':
                    {
                    alt24=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt24=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:390:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:391:9: UnicodeEscape
                    {
                    mUnicodeEscape(); 


                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:392:9: OctalEscape
                    {
                    mOctalEscape(); 


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:397:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt25=3;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='\\') ) {
                int LA25_1 = input.LA(2);

                if ( ((LA25_1 >= '0' && LA25_1 <= '3')) ) {
                    int LA25_2 = input.LA(3);

                    if ( ((LA25_2 >= '0' && LA25_2 <= '7')) ) {
                        int LA25_4 = input.LA(4);

                        if ( ((LA25_4 >= '0' && LA25_4 <= '7')) ) {
                            alt25=1;
                        }
                        else {
                            alt25=2;
                        }
                    }
                    else {
                        alt25=3;
                    }
                }
                else if ( ((LA25_1 >= '4' && LA25_1 <= '7')) ) {
                    int LA25_3 = input.LA(3);

                    if ( ((LA25_3 >= '0' && LA25_3 <= '7')) ) {
                        alt25=2;
                    }
                    else {
                        alt25=3;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:397:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:398:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:399:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "UnicodeEscape"
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:404:5: ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:404:9: '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
            {
            match('\\'); 

            match('u'); 

            mHexDigit(); 


            mHexDigit(); 


            mHexDigit(); 


            mHexDigit(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UnicodeEscape"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:409:5: ( Letter ( Letter | JavaIDDigit )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:409:9: Letter ( Letter | JavaIDDigit )*
            {
            mLetter(); 


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:409:16: ( Letter | JavaIDDigit )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='$'||(LA26_0 >= '0' && LA26_0 <= '9')||(LA26_0 >= 'A' && LA26_0 <= 'Z')||LA26_0=='_'||(LA26_0 >= 'a' && LA26_0 <= 'z')||(LA26_0 >= '\u00C0' && LA26_0 <= '\u00D6')||(LA26_0 >= '\u00D8' && LA26_0 <= '\u00F6')||(LA26_0 >= '\u00F8' && LA26_0 <= '\u1FFF')||(LA26_0 >= '\u3040' && LA26_0 <= '\u318F')||(LA26_0 >= '\u3300' && LA26_0 <= '\u337F')||(LA26_0 >= '\u3400' && LA26_0 <= '\u3D2D')||(LA26_0 >= '\u4E00' && LA26_0 <= '\u9FFF')||(LA26_0 >= '\uF900' && LA26_0 <= '\uFAFF')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')||(input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')||(input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:415:5: ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' .. '\\u007a' | '\\u00c0' .. '\\u00d6' | '\\u00d8' .. '\\u00f6' | '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' | '\\u3040' .. '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' | '\\u4e00' .. '\\u9fff' | '\\uf900' .. '\\ufaff' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')||(input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')||(input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "JavaIDDigit"
    public final void mJavaIDDigit() throws RecognitionException {
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:432:5: ( '\\u0030' .. '\\u0039' | '\\u0660' .. '\\u0669' | '\\u06f0' .. '\\u06f9' | '\\u0966' .. '\\u096f' | '\\u09e6' .. '\\u09ef' | '\\u0a66' .. '\\u0a6f' | '\\u0ae6' .. '\\u0aef' | '\\u0b66' .. '\\u0b6f' | '\\u0be7' .. '\\u0bef' | '\\u0c66' .. '\\u0c6f' | '\\u0ce6' .. '\\u0cef' | '\\u0d66' .. '\\u0d6f' | '\\u0e50' .. '\\u0e59' | '\\u0ed0' .. '\\u0ed9' | '\\u1040' .. '\\u1049' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= '\u0660' && input.LA(1) <= '\u0669')||(input.LA(1) >= '\u06F0' && input.LA(1) <= '\u06F9')||(input.LA(1) >= '\u0966' && input.LA(1) <= '\u096F')||(input.LA(1) >= '\u09E6' && input.LA(1) <= '\u09EF')||(input.LA(1) >= '\u0A66' && input.LA(1) <= '\u0A6F')||(input.LA(1) >= '\u0AE6' && input.LA(1) <= '\u0AEF')||(input.LA(1) >= '\u0B66' && input.LA(1) <= '\u0B6F')||(input.LA(1) >= '\u0BE7' && input.LA(1) <= '\u0BEF')||(input.LA(1) >= '\u0C66' && input.LA(1) <= '\u0C6F')||(input.LA(1) >= '\u0CE6' && input.LA(1) <= '\u0CEF')||(input.LA(1) >= '\u0D66' && input.LA(1) <= '\u0D6F')||(input.LA(1) >= '\u0E50' && input.LA(1) <= '\u0E59')||(input.LA(1) >= '\u0ED0' && input.LA(1) <= '\u0ED9')||(input.LA(1) >= '\u1040' && input.LA(1) <= '\u1049') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "JavaIDDigit"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:451:8: ( '(' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:451:10: '('
            {
            match('('); 

            implicitLineJoiningLevel++;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:453:8: ( ')' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:453:10: ')'
            {
            match(')'); 

            implicitLineJoiningLevel--;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:455:8: ( '[' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:455:10: '['
            {
            match('['); 

            implicitLineJoiningLevel++;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:457:8: ( ']' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:457:10: ']'
            {
            match(']'); 

            implicitLineJoiningLevel--;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:459:8: ( '{' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:459:10: '{'
            {
            match('{'); 

            implicitLineJoiningLevel++;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:461:8: ( '}' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:461:10: '}'
            {
            match('}'); 

            implicitLineJoiningLevel--;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "CIRCUMFLEX"
    public final void mCIRCUMFLEX() throws RecognitionException {
        try {
            int _type = CIRCUMFLEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:463:12: ( '^' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:463:14: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CIRCUMFLEX"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:465:4: ( '@' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:465:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:467:5: ( '.' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:467:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:469:8: ( ':' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:469:10: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:471:7: ( ',' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:471:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:473:6: ( ';' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:473:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:475:6: ( '+' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:475:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:477:7: ( '-' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:477:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:479:6: ( '*' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:479:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "SLASH"
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:481:7: ( '/' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:481:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "VBAR"
    public final void mVBAR() throws RecognitionException {
        try {
            int _type = VBAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:483:6: ( '|' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:483:8: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VBAR"

    // $ANTLR start "AMPER"
    public final void mAMPER() throws RecognitionException {
        try {
            int _type = AMPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:485:7: ( '&' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:485:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AMPER"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:487:6: ( '<' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:487:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:489:9: ( '>' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:489:11: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "ASSIGN_EQUAL"
    public final void mASSIGN_EQUAL() throws RecognitionException {
        try {
            int _type = ASSIGN_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:491:14: ( '=' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:491:16: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSIGN_EQUAL"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:493:9: ( '%' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:493:11: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:495:10: ( '?' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:495:12: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:497:7: ( '==' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:497:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "NOTEQUAL"
    public final void mNOTEQUAL() throws RecognitionException {
        try {
            int _type = NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:499:10: ( '!=' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:499:12: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "ALT_NOTEQUAL"
    public final void mALT_NOTEQUAL() throws RecognitionException {
        try {
            int _type = ALT_NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:501:13: ( '<>' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:501:15: '<>'
            {
            match("<>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALT_NOTEQUAL"

    // $ANTLR start "LESSEQUAL"
    public final void mLESSEQUAL() throws RecognitionException {
        try {
            int _type = LESSEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:503:11: ( '<=' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:503:13: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESSEQUAL"

    // $ANTLR start "GREATEREQUAL"
    public final void mGREATEREQUAL() throws RecognitionException {
        try {
            int _type = GREATEREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:506:14: ( '>=' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:506:16: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATEREQUAL"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:508:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:508:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:518:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:518:9: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            if (input.LA(1)=='*') _type=DocComment; else _channel=HIDDEN;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:518:77: ( options {greedy=false; } : . )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0=='*') ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1=='/') ) {
                        alt27=2;
                    }
                    else if ( ((LA27_1 >= '\u0000' && LA27_1 <= '.')||(LA27_1 >= '0' && LA27_1 <= '\uFFFF')) ) {
                        alt27=1;
                    }


                }
                else if ( ((LA27_0 >= '\u0000' && LA27_0 <= ')')||(LA27_0 >= '+' && LA27_0 <= '\uFFFF')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:518:105: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            match("*/"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:522:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:522:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:522:12: (~ ( '\\n' | '\\r' ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0 >= '\u0000' && LA28_0 <= '\t')||(LA28_0 >= '\u000B' && LA28_0 <= '\f')||(LA28_0 >= '\u000E' && LA28_0 <= '\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:522:26: ( '\\r' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0=='\r') ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:522:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    public void mTokens() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:8: ( TRIE | CONTAINS | DECLARE | WORDLIST | WORDTABLE | AND | CONTEXTCOUNT | COUNT | TOTALCOUNT | CURRENTCOUNT | INLIST | ISINTAG | LAST | MOFN | NEAR | OR | PARTOF | PARTOFNEQ | POSITION | REGEXP | SCORE | VOTE | IF | FEATURE | PARSE | CREATE | GATHER | FILL | ATTRIBUTE | COLOR | DEL | LOG | MARK | MARKSCORE | MARKONCE | MARKFAST | MARKTABLE | MARKLAST | REPLACE | RETAINMARKUP | RETAINTYPE | FILTERMARKUP | FILTERTYPE | CALL | EXEC | CONFIGURE | ASSIGN | SETFEATURE | GETFEATURE | UNMARK | UNMARKALL | TRANSFER | EXPAND | DYNAMICANCHORING | BEFORE | AFTER | IS | STARTSWITH | ENDSWITH | NOT | ADD | REMOVE | REMOVEDUPLICATE | MERGE | GET | GETLIST | SIZE | MATCHEDTEXT | REMOVESTRING | CLEAR | THEN | BasicAnnotationType | LogLevel | OldColor | PackageString | ScriptString | EngineString | BlockString | AutomataBlockString | TypeString | IntString | DoubleString | StringString | BooleanString | TypeSystemString | SymbolString | CONDITION | ACTION | BOOLEANLIST | INTLIST | DOUBLELIST | STRINGLIST | TYPELIST | EXP | LOGN | SIN | COS | TAN | XOR | TRUE | FALSE | HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | RessourceLiteral | Identifier | LPAREN | RPAREN | LBRACK | RBRACK | LCURLY | RCURLY | CIRCUMFLEX | AT | DOT | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN_EQUAL | PERCENT | QUESTION | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | GREATEREQUAL | WS | COMMENT | LINE_COMMENT )
        int alt30=140;
        alt30 = dfa30.predict(input);
        switch (alt30) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:10: TRIE
                {
                mTRIE(); 


                }
                break;
            case 2 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:15: CONTAINS
                {
                mCONTAINS(); 


                }
                break;
            case 3 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:24: DECLARE
                {
                mDECLARE(); 


                }
                break;
            case 4 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:32: WORDLIST
                {
                mWORDLIST(); 


                }
                break;
            case 5 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:41: WORDTABLE
                {
                mWORDTABLE(); 


                }
                break;
            case 6 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:51: AND
                {
                mAND(); 


                }
                break;
            case 7 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:55: CONTEXTCOUNT
                {
                mCONTEXTCOUNT(); 


                }
                break;
            case 8 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:68: COUNT
                {
                mCOUNT(); 


                }
                break;
            case 9 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:74: TOTALCOUNT
                {
                mTOTALCOUNT(); 


                }
                break;
            case 10 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:85: CURRENTCOUNT
                {
                mCURRENTCOUNT(); 


                }
                break;
            case 11 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:98: INLIST
                {
                mINLIST(); 


                }
                break;
            case 12 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:105: ISINTAG
                {
                mISINTAG(); 


                }
                break;
            case 13 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:113: LAST
                {
                mLAST(); 


                }
                break;
            case 14 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:118: MOFN
                {
                mMOFN(); 


                }
                break;
            case 15 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:123: NEAR
                {
                mNEAR(); 


                }
                break;
            case 16 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:128: OR
                {
                mOR(); 


                }
                break;
            case 17 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:131: PARTOF
                {
                mPARTOF(); 


                }
                break;
            case 18 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:138: PARTOFNEQ
                {
                mPARTOFNEQ(); 


                }
                break;
            case 19 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:148: POSITION
                {
                mPOSITION(); 


                }
                break;
            case 20 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:157: REGEXP
                {
                mREGEXP(); 


                }
                break;
            case 21 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:164: SCORE
                {
                mSCORE(); 


                }
                break;
            case 22 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:170: VOTE
                {
                mVOTE(); 


                }
                break;
            case 23 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:175: IF
                {
                mIF(); 


                }
                break;
            case 24 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:178: FEATURE
                {
                mFEATURE(); 


                }
                break;
            case 25 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:186: PARSE
                {
                mPARSE(); 


                }
                break;
            case 26 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:192: CREATE
                {
                mCREATE(); 


                }
                break;
            case 27 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:199: GATHER
                {
                mGATHER(); 


                }
                break;
            case 28 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:206: FILL
                {
                mFILL(); 


                }
                break;
            case 29 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:211: ATTRIBUTE
                {
                mATTRIBUTE(); 


                }
                break;
            case 30 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:221: COLOR
                {
                mCOLOR(); 


                }
                break;
            case 31 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:227: DEL
                {
                mDEL(); 


                }
                break;
            case 32 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:231: LOG
                {
                mLOG(); 


                }
                break;
            case 33 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:235: MARK
                {
                mMARK(); 


                }
                break;
            case 34 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:240: MARKSCORE
                {
                mMARKSCORE(); 


                }
                break;
            case 35 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:250: MARKONCE
                {
                mMARKONCE(); 


                }
                break;
            case 36 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:259: MARKFAST
                {
                mMARKFAST(); 


                }
                break;
            case 37 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:268: MARKTABLE
                {
                mMARKTABLE(); 


                }
                break;
            case 38 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:278: MARKLAST
                {
                mMARKLAST(); 


                }
                break;
            case 39 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:287: REPLACE
                {
                mREPLACE(); 


                }
                break;
            case 40 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:295: RETAINMARKUP
                {
                mRETAINMARKUP(); 


                }
                break;
            case 41 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:308: RETAINTYPE
                {
                mRETAINTYPE(); 


                }
                break;
            case 42 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:319: FILTERMARKUP
                {
                mFILTERMARKUP(); 


                }
                break;
            case 43 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:332: FILTERTYPE
                {
                mFILTERTYPE(); 


                }
                break;
            case 44 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:343: CALL
                {
                mCALL(); 


                }
                break;
            case 45 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:348: EXEC
                {
                mEXEC(); 


                }
                break;
            case 46 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:353: CONFIGURE
                {
                mCONFIGURE(); 


                }
                break;
            case 47 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:363: ASSIGN
                {
                mASSIGN(); 


                }
                break;
            case 48 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:370: SETFEATURE
                {
                mSETFEATURE(); 


                }
                break;
            case 49 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:381: GETFEATURE
                {
                mGETFEATURE(); 


                }
                break;
            case 50 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:392: UNMARK
                {
                mUNMARK(); 


                }
                break;
            case 51 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:399: UNMARKALL
                {
                mUNMARKALL(); 


                }
                break;
            case 52 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:409: TRANSFER
                {
                mTRANSFER(); 


                }
                break;
            case 53 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:418: EXPAND
                {
                mEXPAND(); 


                }
                break;
            case 54 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:425: DYNAMICANCHORING
                {
                mDYNAMICANCHORING(); 


                }
                break;
            case 55 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:442: BEFORE
                {
                mBEFORE(); 


                }
                break;
            case 56 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:449: AFTER
                {
                mAFTER(); 


                }
                break;
            case 57 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:455: IS
                {
                mIS(); 


                }
                break;
            case 58 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:458: STARTSWITH
                {
                mSTARTSWITH(); 


                }
                break;
            case 59 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:469: ENDSWITH
                {
                mENDSWITH(); 


                }
                break;
            case 60 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:478: NOT
                {
                mNOT(); 


                }
                break;
            case 61 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:482: ADD
                {
                mADD(); 


                }
                break;
            case 62 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:486: REMOVE
                {
                mREMOVE(); 


                }
                break;
            case 63 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:493: REMOVEDUPLICATE
                {
                mREMOVEDUPLICATE(); 


                }
                break;
            case 64 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:509: MERGE
                {
                mMERGE(); 


                }
                break;
            case 65 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:515: GET
                {
                mGET(); 


                }
                break;
            case 66 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:519: GETLIST
                {
                mGETLIST(); 


                }
                break;
            case 67 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:527: SIZE
                {
                mSIZE(); 


                }
                break;
            case 68 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:532: MATCHEDTEXT
                {
                mMATCHEDTEXT(); 


                }
                break;
            case 69 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:544: REMOVESTRING
                {
                mREMOVESTRING(); 


                }
                break;
            case 70 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:557: CLEAR
                {
                mCLEAR(); 


                }
                break;
            case 71 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:563: THEN
                {
                mTHEN(); 


                }
                break;
            case 72 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:568: BasicAnnotationType
                {
                mBasicAnnotationType(); 


                }
                break;
            case 73 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:588: LogLevel
                {
                mLogLevel(); 


                }
                break;
            case 74 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:597: OldColor
                {
                mOldColor(); 


                }
                break;
            case 75 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:606: PackageString
                {
                mPackageString(); 


                }
                break;
            case 76 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:620: ScriptString
                {
                mScriptString(); 


                }
                break;
            case 77 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:633: EngineString
                {
                mEngineString(); 


                }
                break;
            case 78 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:646: BlockString
                {
                mBlockString(); 


                }
                break;
            case 79 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:658: AutomataBlockString
                {
                mAutomataBlockString(); 


                }
                break;
            case 80 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:678: TypeString
                {
                mTypeString(); 


                }
                break;
            case 81 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:689: IntString
                {
                mIntString(); 


                }
                break;
            case 82 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:699: DoubleString
                {
                mDoubleString(); 


                }
                break;
            case 83 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:712: StringString
                {
                mStringString(); 


                }
                break;
            case 84 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:725: BooleanString
                {
                mBooleanString(); 


                }
                break;
            case 85 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:739: TypeSystemString
                {
                mTypeSystemString(); 


                }
                break;
            case 86 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:756: SymbolString
                {
                mSymbolString(); 


                }
                break;
            case 87 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:769: CONDITION
                {
                mCONDITION(); 


                }
                break;
            case 88 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:779: ACTION
                {
                mACTION(); 


                }
                break;
            case 89 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:786: BOOLEANLIST
                {
                mBOOLEANLIST(); 


                }
                break;
            case 90 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:798: INTLIST
                {
                mINTLIST(); 


                }
                break;
            case 91 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:806: DOUBLELIST
                {
                mDOUBLELIST(); 


                }
                break;
            case 92 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:817: STRINGLIST
                {
                mSTRINGLIST(); 


                }
                break;
            case 93 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:828: TYPELIST
                {
                mTYPELIST(); 


                }
                break;
            case 94 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:837: EXP
                {
                mEXP(); 


                }
                break;
            case 95 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:841: LOGN
                {
                mLOGN(); 


                }
                break;
            case 96 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:846: SIN
                {
                mSIN(); 


                }
                break;
            case 97 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:850: COS
                {
                mCOS(); 


                }
                break;
            case 98 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:854: TAN
                {
                mTAN(); 


                }
                break;
            case 99 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:858: XOR
                {
                mXOR(); 


                }
                break;
            case 100 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:862: TRUE
                {
                mTRUE(); 


                }
                break;
            case 101 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:867: FALSE
                {
                mFALSE(); 


                }
                break;
            case 102 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:873: HexLiteral
                {
                mHexLiteral(); 


                }
                break;
            case 103 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:884: DecimalLiteral
                {
                mDecimalLiteral(); 


                }
                break;
            case 104 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:899: OctalLiteral
                {
                mOctalLiteral(); 


                }
                break;
            case 105 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:912: FloatingPointLiteral
                {
                mFloatingPointLiteral(); 


                }
                break;
            case 106 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:933: CharacterLiteral
                {
                mCharacterLiteral(); 


                }
                break;
            case 107 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:950: StringLiteral
                {
                mStringLiteral(); 


                }
                break;
            case 108 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:964: RessourceLiteral
                {
                mRessourceLiteral(); 


                }
                break;
            case 109 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:981: Identifier
                {
                mIdentifier(); 


                }
                break;
            case 110 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:992: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 111 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:999: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 112 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1006: LBRACK
                {
                mLBRACK(); 


                }
                break;
            case 113 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1013: RBRACK
                {
                mRBRACK(); 


                }
                break;
            case 114 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1020: LCURLY
                {
                mLCURLY(); 


                }
                break;
            case 115 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1027: RCURLY
                {
                mRCURLY(); 


                }
                break;
            case 116 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1034: CIRCUMFLEX
                {
                mCIRCUMFLEX(); 


                }
                break;
            case 117 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1045: AT
                {
                mAT(); 


                }
                break;
            case 118 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1048: DOT
                {
                mDOT(); 


                }
                break;
            case 119 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1052: COLON
                {
                mCOLON(); 


                }
                break;
            case 120 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1058: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 121 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1064: SEMI
                {
                mSEMI(); 


                }
                break;
            case 122 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1069: PLUS
                {
                mPLUS(); 


                }
                break;
            case 123 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1074: MINUS
                {
                mMINUS(); 


                }
                break;
            case 124 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1080: STAR
                {
                mSTAR(); 


                }
                break;
            case 125 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1085: SLASH
                {
                mSLASH(); 


                }
                break;
            case 126 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1091: VBAR
                {
                mVBAR(); 


                }
                break;
            case 127 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1096: AMPER
                {
                mAMPER(); 


                }
                break;
            case 128 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1102: LESS
                {
                mLESS(); 


                }
                break;
            case 129 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1107: GREATER
                {
                mGREATER(); 


                }
                break;
            case 130 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1115: ASSIGN_EQUAL
                {
                mASSIGN_EQUAL(); 


                }
                break;
            case 131 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1128: PERCENT
                {
                mPERCENT(); 


                }
                break;
            case 132 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1136: QUESTION
                {
                mQUESTION(); 


                }
                break;
            case 133 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1145: EQUAL
                {
                mEQUAL(); 


                }
                break;
            case 134 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1151: NOTEQUAL
                {
                mNOTEQUAL(); 


                }
                break;
            case 135 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1160: ALT_NOTEQUAL
                {
                mALT_NOTEQUAL(); 


                }
                break;
            case 136 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1173: LESSEQUAL
                {
                mLESSEQUAL(); 


                }
                break;
            case 137 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1183: GREATEREQUAL
                {
                mGREATEREQUAL(); 


                }
                break;
            case 138 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1196: WS
                {
                mWS(); 


                }
                break;
            case 139 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1199: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 140 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerLexer.g:1:1207: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA30_eotS =
        "\1\uffff\3\55\1\123\17\55\1\u0083\1\55\1\123\21\55\2\u009b\1\u009f"+
        "\20\uffff\1\u00a5\2\uffff\1\u00a8\1\u00aa\1\u00ac\4\uffff\11\55"+
        "\1\123\4\55\1\uffff\11\55\1\u00cd\1\u00ce\11\55\1\u00d9\3\55\1\123"+
        "\6\55\1\123\16\55\2\uffff\26\55\2\uffff\1\u0116\1\uffff\1\u009b"+
        "\16\uffff\4\55\1\u0120\4\55\1\u0127\3\55\1\123\2\55\1\u012d\3\55"+
        "\1\u0131\1\123\3\55\1\u0135\2\123\2\55\1\u0139\1\55\2\uffff\1\55"+
        "\1\u013d\5\55\1\u0143\1\123\1\55\1\uffff\21\55\1\u0157\7\55\1\u0162"+
        "\1\55\1\u0165\33\55\1\u0181\3\55\1\u0185\6\uffff\1\u018a\2\55\1"+
        "\u018f\1\uffff\6\55\1\uffff\2\55\1\u019a\2\55\1\uffff\3\55\1\uffff"+
        "\3\55\1\uffff\3\55\1\uffff\1\55\1\u01a8\1\u01a9\1\uffff\1\u01aa"+
        "\1\u01b1\2\55\1\u01b4\1\uffff\1\123\21\55\1\u01c6\1\uffff\3\55\1"+
        "\u01ca\1\55\1\u01cc\4\55\1\uffff\1\u01d1\1\55\1\uffff\11\55\1\u01de"+
        "\3\55\1\u01de\4\55\1\u0181\2\55\1\u0181\1\55\1\u0181\1\55\1\u0181"+
        "\1\u01ea\1\uffff\1\u0181\1\55\1\u0181\6\uffff\4\55\1\uffff\4\55"+
        "\1\u01f6\1\u01f7\2\123\2\55\1\uffff\1\u01fa\7\55\1\u0202\4\55\3"+
        "\uffff\6\55\1\uffff\1\55\1\u020e\1\uffff\1\55\1\u0210\7\55\1\u0218"+
        "\1\u0219\6\55\1\uffff\1\55\1\123\1\55\1\uffff\1\55\1\uffff\4\55"+
        "\1\uffff\6\55\1\123\1\u022d\3\55\1\u01de\1\uffff\1\55\1\u0232\4"+
        "\55\1\u0181\1\55\2\u0181\1\55\1\uffff\1\55\2\uffff\10\55\2\uffff"+
        "\1\55\1\u0244\1\uffff\2\55\1\u0248\3\55\1\u024c\1\uffff\1\u024d"+
        "\1\u024e\7\55\1\123\1\55\1\uffff\1\u0258\1\uffff\2\55\1\123\1\u025b"+
        "\2\55\1\u0261\2\uffff\1\u0262\4\55\1\u0268\1\55\1\u026a\2\55\1\u026e"+
        "\2\55\1\u0271\2\55\1\u0274\1\u0276\1\u0277\1\uffff\2\55\1\u01de"+
        "\1\55\1\uffff\1\u01de\1\55\1\u01de\4\u0181\1\uffff\11\55\1\uffff"+
        "\1\u0286\2\55\1\uffff\3\55\3\uffff\1\u028c\1\u028d\7\55\1\uffff"+
        "\1\u0295\1\55\1\uffff\1\u0297\4\55\2\uffff\5\55\1\uffff\1\123\1"+
        "\uffff\1\u02a1\2\55\1\uffff\1\55\1\u02a5\1\uffff\2\55\1\uffff\1"+
        "\55\2\uffff\1\u02aa\1\55\1\u0181\1\u01de\1\uffff\1\u02ac\2\55\1"+
        "\u02af\1\u02b0\4\55\1\uffff\2\55\1\u02b7\2\55\2\uffff\1\55\1\u02bb"+
        "\1\u02bc\1\55\1\u02be\2\55\1\uffff\1\u02c1\1\uffff\11\55\1\uffff"+
        "\3\55\1\uffff\1\55\1\u02cf\2\55\1\uffff\1\123\1\uffff\2\55\2\uffff"+
        "\1\55\1\u02d5\1\u02d6\3\55\1\uffff\1\u02da\1\u02db\1\u02dc\2\uffff"+
        "\1\u02dd\1\uffff\1\55\1\u02df\1\uffff\5\55\1\123\7\55\1\uffff\1"+
        "\u02ec\1\55\1\u02ee\1\u02ef\1\55\2\uffff\2\55\1\u02f3\4\uffff\1"+
        "\55\1\uffff\1\55\1\u02f6\2\55\1\u02f9\1\55\1\u02fb\1\u02fc\1\55"+
        "\1\u02fe\1\u02ff\1\55\1\uffff\1\55\2\uffff\3\55\1\uffff\1\u0305"+
        "\1\55\1\uffff\2\55\1\uffff\1\123\2\uffff\1\55\2\uffff\1\123\1\u030a"+
        "\1\u030b\1\u030c\1\55\1\uffff\1\u030e\1\55\1\u0310\1\u0311\3\uffff"+
        "\1\55\1\uffff\1\55\2\uffff\3\55\1\u0317\1\u0318\2\uffff";
    static final String DFA30_eofS =
        "\u0319\uffff";
    static final String DFA30_minS =
        "\1\11\2\101\1\105\1\44\1\103\1\106\2\101\1\102\1\122\1\101\1\105"+
        "\1\103\1\117\1\105\1\101\2\116\1\105\1\76\1\125\1\44\1\141\1\157"+
        "\1\156\1\141\1\145\1\154\1\141\1\162\1\154\1\141\1\165\2\145\1\151"+
        "\1\145\1\161\1\117\2\56\1\60\1\0\17\uffff\1\52\2\uffff\3\75\4\uffff"+
        "\1\101\1\124\1\120\1\116\1\114\1\122\1\105\1\114\1\105\1\44\1\103"+
        "\1\116\1\125\1\122\1\uffff\1\104\1\124\1\123\1\124\1\104\1\120\1"+
        "\114\1\124\1\114\2\44\1\123\1\107\1\106\2\122\1\101\1\124\1\115"+
        "\1\123\1\44\1\103\1\123\1\122\1\44\1\107\1\114\1\117\1\115\1\101"+
        "\1\116\1\44\1\101\1\115\1\124\1\101\1\114\2\124\1\103\1\104\1\115"+
        "\1\106\1\105\2\117\2\uffff\1\105\1\156\1\143\1\154\1\156\1\146\1"+
        "\162\1\166\1\154\1\141\1\162\1\141\1\151\1\166\1\162\1\141\1\165"+
        "\1\144\1\155\1\154\1\165\1\122\2\uffff\1\56\1\uffff\1\56\1\uffff"+
        "\1\42\1\0\13\uffff\1\105\1\116\1\101\1\105\1\44\1\104\1\116\1\117"+
        "\1\115\1\44\1\122\1\101\1\114\1\44\1\101\1\114\1\44\1\101\1\102"+
        "\1\104\2\44\1\122\1\111\1\105\3\44\2\111\1\44\1\116\2\uffff\1\124"+
        "\1\44\1\116\1\113\1\103\1\107\1\122\2\44\1\120\1\uffff\1\123\1\113"+
        "\2\111\1\105\1\114\1\101\1\117\1\105\1\122\1\111\1\106\1\111\1\124"+
        "\1\122\1\111\1\105\1\44\2\103\1\102\1\105\1\124\1\114\1\110\1\44"+
        "\1\103\1\44\1\114\1\123\1\111\1\101\1\117\1\101\1\103\1\114\1\123"+
        "\1\145\1\150\1\163\1\146\1\157\1\156\1\145\1\166\1\143\1\145\1\157"+
        "\1\145\1\171\1\166\1\171\1\160\1\154\1\145\1\44\1\145\1\154\1\141"+
        "\1\44\1\uffff\1\0\1\60\2\0\1\uffff\1\44\1\123\1\114\1\44\1\uffff"+
        "\1\101\2\111\1\124\1\116\1\101\1\uffff\1\105\1\124\1\44\1\122\1"+
        "\101\1\uffff\1\115\2\114\1\uffff\1\111\1\107\1\122\1\uffff\1\117"+
        "\1\123\1\111\1\uffff\1\124\2\44\1\uffff\2\44\1\110\1\105\1\44\1"+
        "\uffff\1\44\1\117\1\105\1\101\1\124\1\117\1\130\1\101\1\111\1\126"+
        "\1\123\1\105\1\120\1\105\1\103\1\105\1\124\1\116\1\44\1\uffff\1"+
        "\111\1\105\1\117\1\44\1\125\1\44\3\105\1\111\1\uffff\1\44\1\116"+
        "\1\uffff\1\101\1\127\1\116\2\122\2\113\1\105\1\124\1\44\1\163\1"+
        "\145\1\151\1\44\1\151\1\162\1\145\1\153\1\44\1\157\1\156\1\44\1"+
        "\145\1\44\1\154\2\44\1\uffff\1\44\1\157\1\44\1\uffff\1\60\2\0\2"+
        "\uffff\1\106\1\103\1\131\1\111\1\uffff\1\111\1\130\1\107\1\124\4"+
        "\44\1\116\1\105\1\uffff\1\44\1\122\1\111\1\105\1\111\1\101\1\102"+
        "\1\116\1\44\1\116\1\124\1\123\1\101\3\uffff\1\103\1\116\3\101\1"+
        "\120\1\uffff\1\105\1\44\1\uffff\1\106\1\44\1\107\1\111\1\104\1\120"+
        "\1\103\1\116\1\105\2\44\1\124\1\101\1\117\1\116\1\123\1\107\1\uffff"+
        "\1\101\1\44\1\114\1\uffff\1\122\1\uffff\2\122\1\101\1\123\1\uffff"+
        "\1\104\1\115\1\111\1\105\1\113\1\105\2\44\1\101\1\111\1\164\1\44"+
        "\1\uffff\1\151\1\44\1\147\1\156\1\145\1\162\1\44\1\156\2\44\1\145"+
        "\1\uffff\1\167\1\60\1\0\1\105\1\117\2\123\1\116\1\124\1\125\1\111"+
        "\2\uffff\1\124\1\44\1\uffff\1\105\1\103\1\44\1\123\1\102\1\125\1"+
        "\44\1\uffff\2\44\1\124\1\107\1\117\1\103\1\123\1\102\1\123\1\44"+
        "\1\104\1\uffff\1\44\1\uffff\1\105\1\117\2\44\1\105\1\115\1\44\2"+
        "\uffff\1\44\1\124\1\114\1\103\1\127\1\44\1\114\1\44\1\105\1\115"+
        "\1\44\2\124\1\44\1\101\1\124\3\44\1\uffff\1\116\1\117\1\44\1\141"+
        "\1\uffff\1\44\1\147\5\44\1\60\1\122\1\125\2\124\1\123\1\103\1\122"+
        "\1\117\1\103\1\uffff\1\44\1\101\1\111\1\uffff\1\124\1\114\1\124"+
        "\3\uffff\2\44\1\122\1\105\1\124\1\114\2\124\1\105\1\uffff\1\44\1"+
        "\116\1\uffff\1\44\1\101\1\131\1\125\1\124\2\uffff\1\125\1\117\1"+
        "\105\2\111\1\uffff\1\44\1\uffff\1\44\1\101\1\131\1\uffff\1\125\1"+
        "\44\1\uffff\1\124\1\110\1\uffff\1\114\2\uffff\1\44\1\116\2\44\1"+
        "\0\1\44\1\116\1\105\2\44\1\117\1\105\1\116\1\117\1\uffff\1\116\1"+
        "\123\1\44\2\105\2\uffff\1\105\2\44\1\105\1\44\1\105\1\121\1\uffff"+
        "\1\44\1\uffff\1\122\2\120\2\122\1\116\1\105\1\124\1\123\1\uffff"+
        "\1\122\1\120\1\122\1\uffff\1\111\1\44\1\114\1\111\1\uffff\1\44\1"+
        "\uffff\1\124\1\115\2\uffff\1\125\2\44\1\125\1\103\1\124\1\uffff"+
        "\3\44\2\uffff\1\44\1\uffff\1\130\1\44\1\uffff\1\113\1\105\1\114"+
        "\1\111\1\105\1\44\1\116\1\110\1\124\1\113\2\105\1\117\1\uffff\1"+
        "\44\1\123\2\44\1\116\2\uffff\1\116\1\110\1\44\4\uffff\1\124\1\uffff"+
        "\1\125\1\44\1\111\1\116\1\44\1\104\2\44\1\125\2\44\1\116\1\uffff"+
        "\1\124\2\uffff\2\124\1\117\1\uffff\1\44\1\120\1\uffff\1\103\1\107"+
        "\1\uffff\1\44\2\uffff\1\120\2\uffff\4\44\1\122\1\uffff\1\44\1\101"+
        "\2\44\3\uffff\1\111\1\uffff\1\124\2\uffff\1\116\1\105\1\107\2\44"+
        "\2\uffff";
    static final String DFA30_maxS =
        "\1\ufaff\1\131\1\127\1\131\1\ufaff\1\124\1\123\2\117\1\125\1\122"+
        "\1\117\1\125\1\131\1\117\1\111\1\105\1\130\1\116\1\122\1\76\1\125"+
        "\1\ufaff\1\165\1\157\1\156\1\141\1\151\1\154\1\141\1\162\1\154\1"+
        "\141\1\165\1\162\1\145\1\151\1\145\1\161\1\117\1\170\2\71\1\uffff"+
        "\17\uffff\1\57\2\uffff\1\76\2\75\4\uffff\1\111\1\124\1\120\1\116"+
        "\1\125\1\122\1\105\1\120\1\105\1\ufaff\1\114\1\116\1\125\1\122\1"+
        "\uffff\1\131\1\124\1\123\1\124\1\104\1\120\1\114\2\124\2\ufaff\1"+
        "\123\1\107\1\106\1\124\1\122\1\101\1\124\1\115\1\123\1\ufaff\1\122"+
        "\1\123\1\122\1\ufaff\1\124\1\114\1\122\1\124\1\122\1\132\1\ufaff"+
        "\1\105\1\115\1\124\1\101\1\114\2\124\1\120\1\107\1\115\1\106\1\105"+
        "\2\117\2\uffff\1\105\1\156\1\143\1\154\1\156\1\146\1\162\1\166\1"+
        "\154\1\165\1\162\1\145\1\151\1\166\1\162\1\141\1\165\1\144\1\155"+
        "\1\154\1\165\1\122\2\uffff\1\71\1\uffff\1\71\1\uffff\1\165\1\uffff"+
        "\13\uffff\1\105\1\116\1\101\1\105\1\ufaff\1\124\1\116\1\117\1\115"+
        "\1\ufaff\1\122\1\101\1\114\1\ufaff\1\101\1\114\1\ufaff\1\101\1\102"+
        "\1\104\2\ufaff\1\122\1\111\1\105\3\ufaff\2\111\1\ufaff\1\116\2\uffff"+
        "\1\124\1\ufaff\1\116\1\113\1\103\1\107\1\122\2\ufaff\1\120\1\uffff"+
        "\1\124\1\113\2\111\1\105\1\114\1\101\1\117\1\105\1\122\1\111\1\106"+
        "\1\111\1\124\1\122\1\111\1\105\1\ufaff\2\103\1\102\1\105\2\124\1"+
        "\110\1\ufaff\1\103\1\ufaff\1\114\1\123\1\111\1\101\1\117\1\101\1"+
        "\103\1\114\1\123\1\145\1\150\1\163\1\146\1\157\1\156\1\145\1\166"+
        "\1\143\1\145\1\157\1\145\1\171\1\166\1\171\1\160\1\154\1\145\1\ufaff"+
        "\1\145\1\154\1\141\1\ufaff\1\uffff\1\uffff\1\146\2\uffff\1\uffff"+
        "\1\ufaff\1\123\1\114\1\ufaff\1\uffff\1\105\2\111\1\124\1\122\1\101"+
        "\1\uffff\1\105\1\124\1\ufaff\1\122\1\101\1\uffff\1\115\1\114\1\124"+
        "\1\uffff\1\111\1\107\1\122\1\uffff\1\117\1\123\1\111\1\uffff\1\124"+
        "\2\ufaff\1\uffff\2\ufaff\1\110\1\105\1\ufaff\1\uffff\1\ufaff\1\117"+
        "\1\105\1\101\1\124\1\117\1\130\1\101\1\111\1\126\1\123\1\105\1\120"+
        "\1\105\1\103\1\105\1\124\1\116\1\ufaff\1\uffff\1\111\1\105\1\117"+
        "\1\ufaff\1\125\1\ufaff\3\105\1\111\1\uffff\1\ufaff\1\116\1\uffff"+
        "\1\101\1\127\1\116\2\122\2\113\1\105\1\124\1\ufaff\1\163\1\145\1"+
        "\151\1\ufaff\1\151\1\162\1\145\1\153\1\ufaff\1\157\1\156\1\ufaff"+
        "\1\145\1\ufaff\1\154\2\ufaff\1\uffff\1\ufaff\1\157\1\ufaff\1\uffff"+
        "\1\146\2\uffff\2\uffff\1\106\1\103\1\131\1\111\1\uffff\1\111\1\130"+
        "\1\107\1\124\4\ufaff\1\116\1\105\1\uffff\1\ufaff\1\122\1\111\1\105"+
        "\1\111\1\101\1\102\1\116\1\ufaff\1\116\1\124\1\123\1\101\3\uffff"+
        "\1\103\1\116\3\101\1\120\1\uffff\1\105\1\ufaff\1\uffff\1\106\1\ufaff"+
        "\1\107\1\111\1\104\1\120\1\103\1\116\1\105\2\ufaff\1\124\1\101\1"+
        "\117\1\116\1\123\1\107\1\uffff\1\101\1\ufaff\1\114\1\uffff\1\122"+
        "\1\uffff\2\122\1\101\1\123\1\uffff\1\104\1\115\1\111\1\105\1\113"+
        "\1\105\2\ufaff\1\101\1\111\1\164\1\ufaff\1\uffff\1\151\1\ufaff\1"+
        "\147\1\156\1\145\1\162\1\ufaff\1\156\2\ufaff\1\145\1\uffff\1\167"+
        "\1\146\1\uffff\1\105\1\117\2\123\1\116\1\124\1\125\1\111\2\uffff"+
        "\1\124\1\ufaff\1\uffff\1\105\1\103\1\ufaff\1\123\1\102\1\125\1\ufaff"+
        "\1\uffff\2\ufaff\1\124\1\107\1\117\1\103\1\123\1\102\1\123\1\ufaff"+
        "\1\104\1\uffff\1\ufaff\1\uffff\1\105\1\117\2\ufaff\1\105\1\124\1"+
        "\ufaff\2\uffff\1\ufaff\1\124\1\114\1\103\1\127\1\ufaff\1\114\1\ufaff"+
        "\1\105\1\124\1\ufaff\2\124\1\ufaff\1\101\1\124\3\ufaff\1\uffff\1"+
        "\116\1\117\1\ufaff\1\141\1\uffff\1\ufaff\1\147\5\ufaff\1\146\1\122"+
        "\1\125\2\124\1\123\1\103\1\122\1\117\1\103\1\uffff\1\ufaff\1\101"+
        "\1\111\1\uffff\1\124\1\114\1\124\3\uffff\2\ufaff\1\122\1\105\1\124"+
        "\1\114\2\124\1\105\1\uffff\1\ufaff\1\116\1\uffff\1\ufaff\1\101\1"+
        "\131\1\125\1\124\2\uffff\1\125\1\117\1\105\2\111\1\uffff\1\ufaff"+
        "\1\uffff\1\ufaff\1\101\1\131\1\uffff\1\125\1\ufaff\1\uffff\1\124"+
        "\1\110\1\uffff\1\114\2\uffff\1\ufaff\1\116\2\ufaff\1\uffff\1\ufaff"+
        "\1\116\1\105\2\ufaff\1\117\1\105\1\116\1\117\1\uffff\1\116\1\123"+
        "\1\ufaff\2\105\2\uffff\1\105\2\ufaff\1\105\1\ufaff\1\105\1\121\1"+
        "\uffff\1\ufaff\1\uffff\1\122\2\120\2\122\1\116\1\105\1\124\1\123"+
        "\1\uffff\1\122\1\120\1\122\1\uffff\1\111\1\ufaff\1\114\1\111\1\uffff"+
        "\1\ufaff\1\uffff\1\124\1\115\2\uffff\1\125\2\ufaff\1\125\1\103\1"+
        "\124\1\uffff\3\ufaff\2\uffff\1\ufaff\1\uffff\1\130\1\ufaff\1\uffff"+
        "\1\113\1\105\1\114\1\111\1\105\1\ufaff\1\116\1\110\1\124\1\113\2"+
        "\105\1\117\1\uffff\1\ufaff\1\123\2\ufaff\1\116\2\uffff\1\116\1\110"+
        "\1\ufaff\4\uffff\1\124\1\uffff\1\125\1\ufaff\1\111\1\116\1\ufaff"+
        "\1\104\2\ufaff\1\125\2\ufaff\1\116\1\uffff\1\124\2\uffff\2\124\1"+
        "\117\1\uffff\1\ufaff\1\120\1\uffff\1\103\1\107\1\uffff\1\ufaff\2"+
        "\uffff\1\120\2\uffff\4\ufaff\1\122\1\uffff\1\ufaff\1\101\2\ufaff"+
        "\3\uffff\1\111\1\uffff\1\124\2\uffff\1\116\1\105\1\107\2\ufaff\2"+
        "\uffff";
    static final String DFA30_acceptS =
        "\54\uffff\1\153\1\155\1\156\1\157\1\160\1\161\1\162\1\163\1\164"+
        "\1\165\1\167\1\170\1\171\1\172\1\174\1\uffff\1\176\1\177\3\uffff"+
        "\1\u0083\1\u0084\1\u0086\1\u008a\16\uffff\1\110\56\uffff\1\107\1"+
        "\173\26\uffff\1\146\1\147\1\uffff\1\151\1\uffff\1\166\2\uffff\1"+
        "\154\1\u008b\1\u008c\1\175\1\u0087\1\u0088\1\u0080\1\u0089\1\u0081"+
        "\1\u0085\1\u0082\40\uffff\1\71\1\27\12\uffff\1\20\74\uffff\1\150"+
        "\4\uffff\1\152\4\uffff\1\142\6\uffff\1\141\5\uffff\1\37\3\uffff"+
        "\1\6\3\uffff\1\75\3\uffff\1\121\3\uffff\1\40\5\uffff\1\74\23\uffff"+
        "\1\140\12\uffff\1\101\2\uffff\1\136\33\uffff\1\112\3\uffff\1\143"+
        "\3\uffff\1\152\1\1\4\uffff\1\120\12\uffff\1\54\15\uffff\1\15\1\137"+
        "\1\16\6\uffff\1\41\2\uffff\1\17\21\uffff\1\103\3\uffff\1\26\1\uffff"+
        "\1\34\4\uffff\1\55\14\uffff\1\111\13\uffff\1\144\13\uffff\1\10\1"+
        "\36\2\uffff\1\106\7\uffff\1\70\13\uffff\1\100\1\uffff\1\31\7\uffff"+
        "\1\117\1\25\23\uffff\1\116\4\uffff\1\145\21\uffff\1\32\3\uffff\1"+
        "\122\3\uffff\1\57\1\130\1\13\11\uffff\1\21\2\uffff\1\24\5\uffff"+
        "\1\76\1\114\5\uffff\1\123\1\uffff\1\126\3\uffff\1\33\2\uffff\1\65"+
        "\2\uffff\1\115\1\uffff\1\62\1\67\16\uffff\1\3\5\uffff\1\132\1\14"+
        "\7\uffff\1\113\1\uffff\1\47\11\uffff\1\30\3\uffff\1\102\4\uffff"+
        "\1\124\1\uffff\1\64\2\uffff\1\135\1\2\6\uffff\1\4\3\uffff\1\43\1"+
        "\44\1\uffff\1\46\2\uffff\1\23\15\uffff\1\73\5\uffff\1\56\1\127\3"+
        "\uffff\1\5\1\35\1\42\1\45\1\uffff\1\22\14\uffff\1\63\1\uffff\1\11"+
        "\1\125\3\uffff\1\133\2\uffff\1\51\2\uffff\1\60\1\uffff\1\72\1\134"+
        "\1\uffff\1\53\1\61\5\uffff\1\104\4\uffff\1\131\1\7\1\12\1\uffff"+
        "\1\50\1\uffff\1\105\1\52\5\uffff\1\77\1\66";
    static final String DFA30_specialS =
        "\53\uffff\1\5\165\uffff\1\2\165\uffff\1\10\1\uffff\1\1\1\6\154\uffff"+
        "\1\7\1\4\144\uffff\1\3\u008e\uffff\1\0\u009c\uffff}>";
    static final String[] DFA30_transitionS = {
            "\2\104\1\uffff\2\104\22\uffff\1\104\1\103\1\54\1\uffff\1\55"+
            "\1\101\1\75\1\53\1\56\1\57\1\72\1\71\1\67\1\24\1\52\1\73\1\50"+
            "\11\51\1\66\1\70\1\76\1\100\1\77\1\102\1\65\1\5\1\23\1\2\1\3"+
            "\1\21\1\17\1\20\1\55\1\6\2\55\1\7\1\10\1\11\1\12\1\13\1\25\1"+
            "\14\1\15\1\1\1\22\1\16\1\4\1\47\2\55\1\60\1\uffff\1\61\1\64"+
            "\1\26\1\uffff\1\46\1\34\1\30\2\55\1\27\1\36\1\55\1\31\2\55\1"+
            "\44\1\35\1\40\1\37\1\41\1\55\1\43\1\33\1\42\2\55\1\32\1\55\1"+
            "\45\1\55\1\62\1\74\1\63\102\uffff\27\55\1\uffff\37\55\1\uffff"+
            "\u1f08\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff"+
            "\u092e\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\110\15\uffff\1\106\2\uffff\1\105\6\uffff\1\107",
            "\1\114\12\uffff\1\115\2\uffff\1\111\2\uffff\1\113\2\uffff\1"+
            "\112\1\uffff\1\116",
            "\1\117\11\uffff\1\121\11\uffff\1\120",
            "\1\55\13\uffff\12\55\7\uffff\16\55\1\122\13\55\4\uffff\1\55"+
            "\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55"+
            "\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\133\1\130\1\uffff\1\127\5\uffff\1\132\1\131\1\124\4\uffff"+
            "\1\126\1\125",
            "\1\136\7\uffff\1\134\4\uffff\1\135",
            "\1\137\15\uffff\1\140",
            "\1\142\3\uffff\1\143\11\uffff\1\141",
            "\1\147\2\uffff\1\144\11\uffff\1\145\5\uffff\1\146",
            "\1\150",
            "\1\151\3\uffff\1\153\7\uffff\1\154\1\uffff\1\152",
            "\1\155\17\uffff\1\156",
            "\1\157\1\uffff\1\160\3\uffff\1\162\6\uffff\1\164\3\uffff\1"+
            "\161\2\uffff\1\163\1\uffff\1\165",
            "\1\166",
            "\1\167\3\uffff\1\170",
            "\1\171\3\uffff\1\172",
            "\1\174\11\uffff\1\173",
            "\1\175",
            "\1\176\6\uffff\1\u0080\2\uffff\1\u0081\2\uffff\1\177",
            "\1\u0082",
            "\1\u0084",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0087\7\uffff\1\u0085\13\uffff\1\u0086",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b\3\uffff\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093\14\uffff\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009d\1\uffff\10\u009c\2\u009d\36\uffff\1\u009a\37\uffff"+
            "\1\u009a",
            "\1\u009d\1\uffff\12\u009e",
            "\12\u009d",
            "\47\u00a1\1\u00a2\64\u00a1\1\u00a0\uffa3\u00a1",
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
            "",
            "",
            "\1\u00a3\4\uffff\1\u00a4",
            "",
            "",
            "\1\u00a7\1\u00a6",
            "\1\u00a9",
            "\1\u00ab",
            "",
            "",
            "",
            "",
            "\1\u00ae\7\uffff\1\u00ad",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b4\1\u00b5\1\u00b2\4\uffff\1\u00b6\1\uffff\1\u00b3",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9\3\uffff\1\u00ba",
            "\1\u00bb",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00bc\10\uffff\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "",
            "\1\u00c1\24\uffff\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca\7\uffff\1\u00cb",
            "\1\55\13\uffff\12\55\7\uffff\10\55\1\u00cc\21\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2\1\uffff\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00db\16\uffff\1\u00da",
            "\1\u00dc",
            "\1\u00dd",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00de\5\uffff\1\u00e1\2\uffff\1\u00df\3\uffff\1\u00e0",
            "\1\u00e2",
            "\1\u00e3\2\uffff\1\u00e4",
            "\1\u00e6\1\u00e7\5\uffff\1\u00e5",
            "\1\u00e8\20\uffff\1\u00e9",
            "\1\u00eb\13\uffff\1\u00ea",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00ed\3\uffff\1\u00ec",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f6\1\uffff\1\u00f4\12\uffff\1\u00f5",
            "\1\u00f7\2\uffff\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "",
            "",
            "\1\u00fe",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107\23\uffff\1\u0108",
            "\1\u0109",
            "\1\u010b\3\uffff\1\u010a",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "",
            "",
            "\1\u009d\1\uffff\10\u009c\2\u009d",
            "",
            "\1\u009d\1\uffff\12\u009e",
            "",
            "\1\u0117\4\uffff\1\u0117\10\uffff\4\u0119\4\u011a\44\uffff"+
            "\1\u0117\5\uffff\1\u0117\3\uffff\1\u0117\7\uffff\1\u0117\3\uffff"+
            "\1\u0117\1\uffff\1\u0117\1\u0118",
            "\47\u00a2\1\u011b\uffd8\u00a2",
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
            "\1\u011c",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0123\1\uffff\1\u0122\15\uffff\1\u0121",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u012b",
            "\1\u012c",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0136",
            "\1\u0137",
            "\1\55\13\uffff\12\55\7\uffff\13\55\1\u0138\16\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u013a",
            "",
            "",
            "\1\u013b",
            "\1\55\13\uffff\12\55\7\uffff\15\55\1\u013c\14\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0144",
            "",
            "\1\u0146\1\u0145",
            "\1\u0147",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "\1\u0151",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\u015b",
            "\1\u015c",
            "\1\u015d\7\uffff\1\u015e",
            "\1\u015f",
            "\1\55\13\uffff\12\55\7\uffff\5\55\1\u0160\5\55\1\u0161\16\55"+
            "\4\uffff\1\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1"+
            "\uffff\u1f08\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080"+
            "\uffff\u092e\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0163",
            "\1\55\13\uffff\12\55\7\uffff\1\u0164\31\55\4\uffff\1\55\1\uffff"+
            "\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040"+
            "\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2"+
            "\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0166",
            "\1\u0167",
            "\1\u0168",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "\1\u016c",
            "\1\u016d",
            "\1\u016e",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\u0176",
            "\1\u0177",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "\1\u017b",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0182",
            "\1\u0183",
            "\1\u0184",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\47\u00a2\1\u011b\uffd8\u00a2",
            "\12\u0186\7\uffff\6\u0186\32\uffff\6\u0186",
            "\47\u00a2\1\u011b\10\u00a2\10\u0187\uffc8\u00a2",
            "\47\u00a2\1\u011b\10\u00a2\10\u0188\uffc8\u00a2",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u018b",
            "\1\u018c",
            "\1\55\13\uffff\12\55\7\uffff\13\55\1\u018e\6\55\1\u018d\7\55"+
            "\4\uffff\1\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1"+
            "\uffff\u1f08\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080"+
            "\uffff\u092e\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0190\3\uffff\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "\1\u0194",
            "\1\u0196\3\uffff\1\u0195",
            "\1\u0197",
            "",
            "\1\u0198",
            "\1\u0199",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u019b",
            "\1\u019c",
            "",
            "\1\u019d",
            "\1\u019e",
            "\1\u019f\7\uffff\1\u01a0",
            "",
            "\1\u01a1",
            "\1\u01a2",
            "\1\u01a3",
            "",
            "\1\u01a4",
            "\1\u01a5",
            "\1\u01a6",
            "",
            "\1\u01a7",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\5\55\1\u01ad\5\55\1\u01af\2\55"+
            "\1\u01ac\3\55\1\u01ab\1\u01ae\1\u01b0\5\55\4\uffff\1\55\1\uffff"+
            "\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040"+
            "\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2"+
            "\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u01b2",
            "\1\u01b3",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01b5",
            "\1\u01b6",
            "\1\u01b7",
            "\1\u01b8",
            "\1\u01b9",
            "\1\u01ba",
            "\1\u01bb",
            "\1\u01bc",
            "\1\u01bd",
            "\1\u01be",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\u01c4",
            "\1\u01c5",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u01c7",
            "\1\u01c8",
            "\1\u01c9",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01cb",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01cd",
            "\1\u01ce",
            "\1\u01cf",
            "\1\u01d0",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01d2",
            "",
            "\1\u01d3",
            "\1\u01d4",
            "\1\u01d5",
            "\1\u01d6",
            "\1\u01d7",
            "\1\u01d8",
            "\1\u01d9",
            "\1\u01da",
            "\1\u01db",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\21"+
            "\55\1\u01dd\1\u01dc\7\55\105\uffff\27\55\1\uffff\37\55\1\uffff"+
            "\u1f08\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff"+
            "\u092e\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u01df",
            "\1\u01e0",
            "\1\u01e1",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01e2",
            "\1\u01e3",
            "\1\u01e4",
            "\1\u01e5",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01e6",
            "\1\u01e7",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01e8",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01e9",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01eb",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\12\u01ec\7\uffff\6\u01ec\32\uffff\6\u01ec",
            "\47\u00a2\1\u011b\10\u00a2\10\u01ed\uffc8\u00a2",
            "\47\u00a2\1\u011b\uffd8\u00a2",
            "",
            "",
            "\1\u01ee",
            "\1\u01ef",
            "\1\u01f0",
            "\1\u01f1",
            "",
            "\1\u01f2",
            "\1\u01f3",
            "\1\u01f4",
            "\1\u01f5",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01f8",
            "\1\u01f9",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u01fb",
            "\1\u01fc",
            "\1\u01fd",
            "\1\u01fe",
            "\1\u01ff",
            "\1\u0200",
            "\1\u0201",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0203",
            "\1\u0204",
            "\1\u0205",
            "\1\u0206",
            "",
            "",
            "",
            "\1\u0207",
            "\1\u0208",
            "\1\u0209",
            "\1\u020a",
            "\1\u020b",
            "\1\u020c",
            "",
            "\1\u020d",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u020f",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0211",
            "\1\u0212",
            "\1\u0213",
            "\1\u0214",
            "\1\u0215",
            "\1\u0216",
            "\1\u0217",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u021a",
            "\1\u021b",
            "\1\u021c",
            "\1\u021d",
            "\1\u021e",
            "\1\u021f",
            "",
            "\1\u0220",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0221",
            "",
            "\1\u0222",
            "",
            "\1\u0223",
            "\1\u0224",
            "\1\u0225",
            "\1\u0226",
            "",
            "\1\u0227",
            "\1\u0228",
            "\1\u0229",
            "\1\u022a",
            "\1\u022b",
            "\1\u022c",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u022e",
            "\1\u022f",
            "\1\u0230",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0231",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0233",
            "\1\u0234",
            "\1\u0235",
            "\1\u0236",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0237",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0238",
            "",
            "\1\u0239",
            "\12\u023a\7\uffff\6\u023a\32\uffff\6\u023a",
            "\47\u00a2\1\u011b\uffd8\u00a2",
            "\1\u023b",
            "\1\u023c",
            "\1\u023d",
            "\1\u023e",
            "\1\u023f",
            "\1\u0240",
            "\1\u0241",
            "\1\u0242",
            "",
            "",
            "\1\u0243",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0245",
            "\1\u0246",
            "\1\55\13\uffff\12\55\7\uffff\13\55\1\u0247\16\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0249",
            "\1\u024a",
            "\1\u024b",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u024f",
            "\1\u0250",
            "\1\u0251",
            "\1\u0252",
            "\1\u0253",
            "\1\u0254",
            "\1\u0255",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0256",
            "",
            "\1\55\13\uffff\12\55\7\uffff\15\55\1\u0257\14\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0259",
            "\1\u025a",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u025c",
            "\1\u025d\6\uffff\1\u025e",
            "\1\55\13\uffff\12\55\7\uffff\3\55\1\u025f\16\55\1\u0260\7\55"+
            "\4\uffff\1\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1"+
            "\uffff\u1f08\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080"+
            "\uffff\u092e\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0263",
            "\1\u0264",
            "\1\u0265",
            "\1\u0266",
            "\1\55\13\uffff\12\55\7\uffff\13\55\1\u0267\16\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0269",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u026b",
            "\1\u026c\6\uffff\1\u026d",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u026f",
            "\1\u0270",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0272",
            "\1\u0273",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\1\u0275\31\55\4\uffff\1\55\1\uffff"+
            "\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040"+
            "\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2"+
            "\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0278",
            "\1\u0279",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u027a",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u027b",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\12\u027c\7\uffff\6\u027c\32\uffff\6\u027c",
            "\1\u027d",
            "\1\u027e",
            "\1\u027f",
            "\1\u0280",
            "\1\u0281",
            "\1\u0282",
            "\1\u0283",
            "\1\u0284",
            "\1\u0285",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0287",
            "\1\u0288",
            "",
            "\1\u0289",
            "\1\u028a",
            "\1\u028b",
            "",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u028e",
            "\1\u028f",
            "\1\u0290",
            "\1\u0291",
            "\1\u0292",
            "\1\u0293",
            "\1\u0294",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0296",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0298",
            "\1\u0299",
            "\1\u029a",
            "\1\u029b",
            "",
            "",
            "\1\u029c",
            "\1\u029d",
            "\1\u029e",
            "\1\u029f",
            "\1\u02a0",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02a2",
            "\1\u02a3",
            "",
            "\1\u02a4",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u02a6",
            "\1\u02a7",
            "",
            "\1\u02a8",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\13\55\1\u02a9\16\55\4\uffff\1"+
            "\55\1\uffff\32\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u02ab",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\47\u00a2\1\u011b\uffd8\u00a2",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02ad",
            "\1\u02ae",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02b1",
            "\1\u02b2",
            "\1\u02b3",
            "\1\u02b4",
            "",
            "\1\u02b5",
            "\1\u02b6",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02b8",
            "\1\u02b9",
            "",
            "",
            "\1\u02ba",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02bd",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02bf",
            "\1\u02c0",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u02c2",
            "\1\u02c3",
            "\1\u02c4",
            "\1\u02c5",
            "\1\u02c6",
            "\1\u02c7",
            "\1\u02c8",
            "\1\u02c9",
            "\1\u02ca",
            "",
            "\1\u02cb",
            "\1\u02cc",
            "\1\u02cd",
            "",
            "\1\u02ce",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02d0",
            "\1\u02d1",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u02d2",
            "\1\u02d3",
            "",
            "",
            "\1\u02d4",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02d7",
            "\1\u02d8",
            "\1\u02d9",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u02de",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u02e0",
            "\1\u02e1",
            "\1\u02e2",
            "\1\u02e3",
            "\1\u02e4",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02e5",
            "\1\u02e6",
            "\1\u02e7",
            "\1\u02e8",
            "\1\u02e9",
            "\1\u02ea",
            "\1\u02eb",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02ed",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02f0",
            "",
            "",
            "\1\u02f1",
            "\1\u02f2",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "",
            "",
            "\1\u02f4",
            "",
            "\1\u02f5",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02f7",
            "\1\u02f8",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02fa",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u02fd",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0300",
            "",
            "\1\u0301",
            "",
            "",
            "\1\u0302",
            "\1\u0303",
            "\1\u0304",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0306",
            "",
            "\1\u0307",
            "\1\u0308",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\u0309",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u030d",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u030f",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "",
            "\1\u0312",
            "",
            "\1\u0313",
            "",
            "",
            "\1\u0314",
            "\1\u0315",
            "\1\u0316",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( TRIE | CONTAINS | DECLARE | WORDLIST | WORDTABLE | AND | CONTEXTCOUNT | COUNT | TOTALCOUNT | CURRENTCOUNT | INLIST | ISINTAG | LAST | MOFN | NEAR | OR | PARTOF | PARTOFNEQ | POSITION | REGEXP | SCORE | VOTE | IF | FEATURE | PARSE | CREATE | GATHER | FILL | ATTRIBUTE | COLOR | DEL | LOG | MARK | MARKSCORE | MARKONCE | MARKFAST | MARKTABLE | MARKLAST | REPLACE | RETAINMARKUP | RETAINTYPE | FILTERMARKUP | FILTERTYPE | CALL | EXEC | CONFIGURE | ASSIGN | SETFEATURE | GETFEATURE | UNMARK | UNMARKALL | TRANSFER | EXPAND | DYNAMICANCHORING | BEFORE | AFTER | IS | STARTSWITH | ENDSWITH | NOT | ADD | REMOVE | REMOVEDUPLICATE | MERGE | GET | GETLIST | SIZE | MATCHEDTEXT | REMOVESTRING | CLEAR | THEN | BasicAnnotationType | LogLevel | OldColor | PackageString | ScriptString | EngineString | BlockString | AutomataBlockString | TypeString | IntString | DoubleString | StringString | BooleanString | TypeSystemString | SymbolString | CONDITION | ACTION | BOOLEANLIST | INTLIST | DOUBLELIST | STRINGLIST | TYPELIST | EXP | LOGN | SIN | COS | TAN | XOR | TRUE | FALSE | HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | RessourceLiteral | Identifier | LPAREN | RPAREN | LBRACK | RBRACK | LCURLY | RCURLY | CIRCUMFLEX | AT | DOT | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN_EQUAL | PERCENT | QUESTION | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | GREATEREQUAL | WS | COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_636 = input.LA(1);

                        s = -1;
                        if ( (LA30_636=='\'') ) {s = 283;}

                        else if ( ((LA30_636 >= '\u0000' && LA30_636 <= '&')||(LA30_636 >= '(' && LA30_636 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA30_281 = input.LA(1);

                        s = -1;
                        if ( ((LA30_281 >= '0' && LA30_281 <= '7')) ) {s = 391;}

                        else if ( (LA30_281=='\'') ) {s = 283;}

                        else if ( ((LA30_281 >= '\u0000' && LA30_281 <= '&')||(LA30_281 >= '(' && LA30_281 <= '/')||(LA30_281 >= '8' && LA30_281 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA30_161 = input.LA(1);

                        s = -1;
                        if ( (LA30_161=='\'') ) {s = 283;}

                        else if ( ((LA30_161 >= '\u0000' && LA30_161 <= '&')||(LA30_161 >= '(' && LA30_161 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA30_493 = input.LA(1);

                        s = -1;
                        if ( (LA30_493=='\'') ) {s = 283;}

                        else if ( ((LA30_493 >= '\u0000' && LA30_493 <= '&')||(LA30_493 >= '(' && LA30_493 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA30_392 = input.LA(1);

                        s = -1;
                        if ( (LA30_392=='\'') ) {s = 283;}

                        else if ( ((LA30_392 >= '\u0000' && LA30_392 <= '&')||(LA30_392 >= '(' && LA30_392 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA30_43 = input.LA(1);

                        s = -1;
                        if ( (LA30_43=='\\') ) {s = 160;}

                        else if ( ((LA30_43 >= '\u0000' && LA30_43 <= '&')||(LA30_43 >= '(' && LA30_43 <= '[')||(LA30_43 >= ']' && LA30_43 <= '\uFFFF')) ) {s = 161;}

                        else if ( (LA30_43=='\'') ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA30_282 = input.LA(1);

                        s = -1;
                        if ( ((LA30_282 >= '0' && LA30_282 <= '7')) ) {s = 392;}

                        else if ( (LA30_282=='\'') ) {s = 283;}

                        else if ( ((LA30_282 >= '\u0000' && LA30_282 <= '&')||(LA30_282 >= '(' && LA30_282 <= '/')||(LA30_282 >= '8' && LA30_282 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA30_391 = input.LA(1);

                        s = -1;
                        if ( ((LA30_391 >= '0' && LA30_391 <= '7')) ) {s = 493;}

                        else if ( (LA30_391=='\'') ) {s = 283;}

                        else if ( ((LA30_391 >= '\u0000' && LA30_391 <= '&')||(LA30_391 >= '(' && LA30_391 <= '/')||(LA30_391 >= '8' && LA30_391 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA30_279 = input.LA(1);

                        s = -1;
                        if ( (LA30_279=='\'') ) {s = 283;}

                        else if ( ((LA30_279 >= '\u0000' && LA30_279 <= '&')||(LA30_279 >= '(' && LA30_279 <= '\uFFFF')) ) {s = 162;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}