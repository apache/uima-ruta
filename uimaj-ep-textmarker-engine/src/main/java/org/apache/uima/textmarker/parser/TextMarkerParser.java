// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g 2011-09-19 18:00:27

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.metadata.TypeSystemDescription;

import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.action.ActionFactory;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.condition.ConditionFactory;
import org.apache.uima.textmarker.TextMarkerAutomataBlock;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerModule;
import org.apache.uima.textmarker.TextMarkerScriptBlock;
import org.apache.uima.textmarker.TextMarkerScriptFactory;
import org.apache.uima.textmarker.TextMarkerAutomataFactory;
import org.apache.uima.textmarker.TextMarkerStatement;
import org.apache.uima.textmarker.expression.ExpressionFactory;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.BooleanListExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.string.StringFunctionFactory;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.extensions.TextMarkerExternalFactory;
import org.apache.uima.textmarker.rule.ComposedRuleElement;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleElementContainer;
import org.apache.uima.textmarker.rule.RuleElementIsolator;
import org.apache.uima.textmarker.rule.TextMarkerRule;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TextMarkerParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DocComment", "Annotation", "ListIdentifier", "TRIE", "CONTAINS", "DECLARE", "WORDLIST", "WORDTABLE", "AND", "CONTEXTCOUNT", "COUNT", "TOTALCOUNT", "CURRENTCOUNT", "INLIST", "ISINTAG", "LAST", "MOFN", "NEAR", "OR", "PARTOF", "PARTOFNEQ", "POSITION", "REGEXP", "SCORE", "VOTE", "IF", "FEATURE", "PARSE", "CREATE", "GATHER", "FILL", "ATTRIBUTE", "COLOR", "DEL", "LOG", "MARK", "MARKSCORE", "MARKONCE", "MARKFAST", "MARKTABLE", "MARKLAST", "REPLACE", "RETAINMARKUP", "RETAINTYPE", "FILTERMARKUP", "FILTERTYPE", "CALL", "EXEC", "ASSIGN", "SETFEATURE", "GETFEATURE", "UNMARK", "UNMARKALL", "TRANSFER", "EXPAND", "BEFORE", "AFTER", "IS", "STARTSWITH", "ENDSWITH", "NOT", "ADD", "REMOVE", "REMOVEDUPLICATE", "MERGE", "GET", "GETLIST", "SIZE", "MATCHEDTEXT", "REMOVESTRING", "CLEAR", "THEN", "BasicAnnotationType", "LogLevel", "OldColor", "PackageString", "ScriptString", "EngineString", "BlockString", "AutomataBlockString", "TypeString", "IntString", "DoubleString", "StringString", "BooleanString", "TypeSystemString", "SymbolString", "CONDITION", "ACTION", "BOOLEANLIST", "INTLIST", "DOUBLELIST", "STRINGLIST", "TYPELIST", "EXP", "LOGN", "SIN", "COS", "TAN", "XOR", "TRUE", "FALSE", "HexDigit", "IntegerTypeSuffix", "HexLiteral", "DecimalLiteral", "OctalLiteral", "Exponent", "FloatTypeSuffix", "FloatingPointLiteral", "EscapeSequence", "CharacterLiteral", "StringLiteral", "RessourceLiteral", "UnicodeEscape", "OctalEscape", "Letter", "JavaIDDigit", "Identifier", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "CIRCUMFLEX", "AT", "DOT", "COLON", "COMMA", "SEMI", "PLUS", "MINUS", "STAR", "SLASH", "VBAR", "AMPER", "LESS", "GREATER", "ASSIGN_EQUAL", "PERCENT", "QUESTION", "EQUAL", "NOTEQUAL", "ALT_NOTEQUAL", "LESSEQUAL", "GREATEREQUAL", "WS", "COMMENT", "LINE_COMMENT"
    };

    public static final int EOF=-1;
    public static final int STAR=137;
    public static final int FloatTypeSuffix=112;
    public static final int OctalLiteral=110;
    public static final int FILTERMARKUP=48;
    public static final int LOG=38;
    public static final int CONTAINS=8;
    public static final int REMOVE=66;
    public static final int GREATEREQUAL=150;
    public static final int MARKFAST=42;
    public static final int MATCHEDTEXT=72;
    public static final int CONDITION=91;
    public static final int COUNT=14;
    public static final int LOGN=99;
    public static final int NOT=64;
    public static final int Identifier=122;
    public static final int ACTION=92;
    public static final int NOTEQUAL=147;
    public static final int CLEAR=74;
    public static final int VBAR=139;
    public static final int DOUBLELIST=95;
    public static final int ENDSWITH=63;
    public static final int RPAREN=124;
    public static final int CREATE=32;
    public static final int GREATER=142;
    public static final int SIN=100;
    public static final int EXP=98;
    public static final int CURRENTCOUNT=16;
    public static final int COS=101;
    public static final int TAN=102;
    public static final int TYPELIST=97;
    public static final int LESS=141;
    public static final int REGEXP=26;
    public static final int GET=69;
    public static final int PARTOF=23;
    public static final int UNMARK=55;
    public static final int LAST=19;
    public static final int COMMENT=152;
    public static final int REMOVEDUPLICATE=67;
    public static final int UNMARKALL=56;
    public static final int RBRACK=126;
    public static final int NEAR=21;
    public static final int LINE_COMMENT=153;
    public static final int IntegerTypeSuffix=107;
    public static final int MARKSCORE=40;
    public static final int REMOVESTRING=73;
    public static final int TRANSFER=57;
    public static final int LCURLY=127;
    public static final int TRIE=7;
    public static final int FILTERTYPE=49;
    public static final int RETAINMARKUP=46;
    public static final int STRINGLIST=96;
    public static final int MARKONCE=41;
    public static final int ScriptString=80;
    public static final int EngineString=81;
    public static final int WS=151;
    public static final int WORDTABLE=11;
    public static final int WORDLIST=10;
    public static final int AutomataBlockString=83;
    public static final int FloatingPointLiteral=113;
    public static final int INTLIST=94;
    public static final int OR=22;
    public static final int JavaIDDigit=121;
    public static final int CALL=50;
    public static final int Annotation=5;
    public static final int FALSE=105;
    public static final int LESSEQUAL=149;
    public static final int RessourceLiteral=117;
    public static final int VOTE=28;
    public static final int Letter=120;
    public static final int EscapeSequence=114;
    public static final int SIZE=71;
    public static final int BasicAnnotationType=76;
    public static final int LBRACK=125;
    public static final int CharacterLiteral=115;
    public static final int DEL=37;
    public static final int ATTRIBUTE=35;
    public static final int TypeString=84;
    public static final int Exponent=111;
    public static final int ASSIGN_EQUAL=143;
    public static final int RETAINTYPE=47;
    public static final int AND=12;
    public static final int TypeSystemString=89;
    public static final int EXPAND=58;
    public static final int BlockString=82;
    public static final int IntString=85;
    public static final int HexDigit=106;
    public static final int COLOR=36;
    public static final int LPAREN=123;
    public static final int POSITION=25;
    public static final int IF=29;
    public static final int AT=130;
    public static final int LogLevel=77;
    public static final int SLASH=138;
    public static final int THEN=75;
    public static final int FILL=34;
    public static final int COMMA=133;
    public static final int IS=61;
    public static final int AMPER=140;
    public static final int REPLACE=45;
    public static final int GETLIST=70;
    public static final int EQUAL=146;
    public static final int GATHER=33;
    public static final int INLIST=17;
    public static final int PLUS=135;
    public static final int BooleanString=88;
    public static final int GETFEATURE=54;
    public static final int DOT=131;
    public static final int ListIdentifier=6;
    public static final int PARTOFNEQ=24;
    public static final int BOOLEANLIST=93;
    public static final int ADD=65;
    public static final int MARKTABLE=43;
    public static final int HexLiteral=108;
    public static final int XOR=103;
    public static final int PERCENT=144;
    public static final int MARK=39;
    public static final int PARSE=31;
    public static final int PackageString=79;
    public static final int OldColor=78;
    public static final int MARKLAST=44;
    public static final int MERGE=68;
    public static final int CONTEXTCOUNT=13;
    public static final int BEFORE=59;
    public static final int EXEC=51;
    public static final int MINUS=136;
    public static final int AFTER=60;
    public static final int DecimalLiteral=109;
    public static final int SEMI=134;
    public static final int TRUE=104;
    public static final int FEATURE=30;
    public static final int SymbolString=90;
    public static final int COLON=132;
    public static final int StringLiteral=116;
    public static final int StringString=87;
    public static final int SCORE=27;
    public static final int QUESTION=145;
    public static final int UnicodeEscape=118;
    public static final int RCURLY=128;
    public static final int STARTSWITH=62;
    public static final int ASSIGN=52;
    public static final int TOTALCOUNT=15;
    public static final int DECLARE=9;
    public static final int DocComment=4;
    public static final int ISINTAG=18;
    public static final int MOFN=20;
    public static final int OctalEscape=119;
    public static final int SETFEATURE=53;
    public static final int DoubleString=86;
    public static final int CIRCUMFLEX=129;
    public static final int ALT_NOTEQUAL=148;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public TextMarkerParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public TextMarkerParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return TextMarkerParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g"; }


    private List vars = new ArrayList();	
    private int level = 0;
    private TextMarkerScriptFactory factory = new TextMarkerScriptFactory();
    private TextMarkerScriptFactory automataFactory = new TextMarkerAutomataFactory();
    private TextMarkerExternalFactory external;


    public void setExternalFactory(TextMarkerExternalFactory factory) {
    	external = factory;
    }

    public void emitErrorMessage(String msg) {
    		System.out.println(msg);
    	}
    	public void reportError(RecognitionException e) {
    		System.out.println(e);
    	}

    	//public void addVariable(String var, IntStream input) throws NoViableAltException {
    	//	if(!vars.contains(var)) {
    	//		vars.add(var);
    	//	} else {
    	//		throw new NoViableAltException("already declared \"" + var + "\"", 3, 0, input);
    	//	}
    	//}
    	public void addVariable(TextMarkerBlock parent, String name, String type) {
    		parent.getEnvironment().addVariable(name, type);
    	}
    	
    	public boolean ownsVariable(TextMarkerBlock parent, String name) {
    		return parent.getEnvironment().ownsVariable(name);
    	}
    	public boolean isVariable(TextMarkerBlock parent, String name) {
    		return parent.getEnvironment().isVariable(name);
    	}
    	
    	public void setValue(TextMarkerBlock parent, String name, Object value) {
    		if(value != null) {
    			parent.getEnvironment().setVariableValue(name, value);
    		}
    	}
    	
    	public boolean ownsVariableOfType(TextMarkerBlock parent, String name, String type) {
    		return parent.getEnvironment().ownsVariableOfType(name,type);
    	}
    	
    	public boolean isVariableOfType(TextMarkerBlock parent, String name, String type) {
    		return parent.getEnvironment().isVariableOfType(name,type);
    	}
    	
    	public void addType(TextMarkerBlock parent, String type) {
    	}
    	
    	public void addType(TextMarkerBlock parent, String name, String parentType, List featuresTypes, List featuresNames) {
        	}
    	
    	public boolean isType(TextMarkerBlock parent, String type) {
    		return parent.getEnvironment().getType(type) != null || type.equals("Document");
    	}
    	
    	public void checkVariable(String var, IntStream input) throws NoViableAltException {
    		if(!vars.contains(var)) {
    			throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
    		}
    	}
    	
    	public void addImportTypeSystem(TextMarkerBlock parent, String descriptor) {
    		//parent.getEnvironment().addTypeSystem(descriptor);
    	}
    	public void addImportScript(TextMarkerBlock parent, String namespace) {
    		parent.getScript().addScript(namespace, null);
    	}
    	public void addImportEngine(TextMarkerBlock parent, String namespace) {
    		parent.getScript().addEngine(namespace, null);
    	}
    	
    	
    	protected static final int[] getBounds(Token t) {
        		if (t instanceof CommonToken) {
        			CommonToken ct = (CommonToken) t;
        			int[] bounds = {ct.getStartIndex(), ct.getStopIndex()}; 
        			return bounds;
        		}
        		return null;
        	}
    	
    	private CAS cas;
    	private TypeSystemDescription localTSD;
    	private String[] resourcePaths;
    	
    	public void setCAS(CAS cas) {
    		this.cas = cas;
    	}
    	
    	public void setResourcePaths(String[] resourcePaths) {
    		this.resourcePaths = resourcePaths;
    	}
    	
    	 public void setLocalTSD(TypeSystemDescription localTSD) {
          		this.localTSD = localTSD;
       	 }



    // $ANTLR start "file_input"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:206:1: file_input[String moduleName] returns [TextMarkerModule module] : p= packageDeclaration gs= globalStatements s= statements EOF ;
    public final TextMarkerModule file_input(String moduleName) throws RecognitionException {
        TextMarkerModule module = null;


        String p =null;

        List<TextMarkerStatement> gs =null;

        List<TextMarkerStatement> s =null;



        TextMarkerScriptBlock rootBlock = null;
        List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:211:2: (p= packageDeclaration gs= globalStatements s= statements EOF )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:212:2: p= packageDeclaration gs= globalStatements s= statements EOF
            {
            pushFollow(FOLLOW_packageDeclaration_in_file_input76);
            p=packageDeclaration();

            state._fsp--;
            if (state.failed) return module;

            if ( state.backtracking==0 ) {
            	rootBlock = factory.createRootScriptBlock(moduleName, p, cas, localTSD);
                   	
                    try {
                    	Type documentType = cas.getJCas().getCasType(DocumentAnnotation.type);
                    	rootBlock.getEnvironment().addType("Document", documentType);
                    	Type annotationType = cas.getJCas().getCasType(org.apache.uima.jcas.tcas.Annotation.type);
                    	rootBlock.getEnvironment().addType("Annotation", annotationType);
                    	rootBlock.getEnvironment().setResourcePaths(resourcePaths);
                    	} catch (CASException e) {
                    		e.printStackTrace();
                   	}
            	rootBlock.setElements(stmts);
            	module = new TextMarkerModule(rootBlock);
            	rootBlock.setScript(module);
            	}

            if ( state.backtracking==0 ) {blockDeclaration_stack.push(new blockDeclaration_scope());((blockDeclaration_scope)blockDeclaration_stack.peek()).env = rootBlock;}

            pushFollow(FOLLOW_globalStatements_in_file_input92);
            gs=globalStatements();

            state._fsp--;
            if (state.failed) return module;

            if ( state.backtracking==0 ) {stmts.addAll(gs);}

            pushFollow(FOLLOW_statements_in_file_input101);
            s=statements();

            state._fsp--;
            if (state.failed) return module;

            if ( state.backtracking==0 ) {stmts.addAll(s);}

            match(input,EOF,FOLLOW_EOF_in_file_input109); if (state.failed) return module;

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return module;
    }
    // $ANTLR end "file_input"



    // $ANTLR start "packageDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:237:1: packageDeclaration returns [String pack = \"\"] : PackageString p= dottedIdentifier SEMI ;
    public final String packageDeclaration() throws RecognitionException {
        String pack =  "";


        String p =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:238:2: ( PackageString p= dottedIdentifier SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:238:4: PackageString p= dottedIdentifier SEMI
            {
            match(input,PackageString,FOLLOW_PackageString_in_packageDeclaration124); if (state.failed) return pack;

            pushFollow(FOLLOW_dottedIdentifier_in_packageDeclaration130);
            p=dottedIdentifier();

            state._fsp--;
            if (state.failed) return pack;

            match(input,SEMI,FOLLOW_SEMI_in_packageDeclaration132); if (state.failed) return pack;

            if ( state.backtracking==0 ) {pack = p;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return pack;
    }
    // $ANTLR end "packageDeclaration"



    // $ANTLR start "statements"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:241:1: statements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (stmt= statement )* ;
    public final List<TextMarkerStatement> statements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        TextMarkerStatement stmt =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:242:2: ( (stmt= statement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:243:2: (stmt= statement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:243:2: (stmt= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= DECLARE && LA1_0 <= WORDTABLE)||LA1_0==BasicAnnotationType||(LA1_0 >= BlockString && LA1_0 <= BooleanString)||(LA1_0 >= CONDITION && LA1_0 <= TYPELIST)||LA1_0==StringLiteral||(LA1_0 >= Identifier && LA1_0 <= LPAREN)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:243:3: stmt= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements155);
            	    stmt=statement();

            	    state._fsp--;
            	    if (state.failed) return stmts;

            	    if(stmt != null) {stmts.add(stmt);}

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "statements"



    // $ANTLR start "globalStatements"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:246:1: globalStatements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (morestmts= globalStatement )* ;
    public final List<TextMarkerStatement> globalStatements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        List<TextMarkerStatement> morestmts =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:247:2: ( (morestmts= globalStatement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:248:2: (morestmts= globalStatement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:248:2: (morestmts= globalStatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ScriptString && LA2_0 <= EngineString)||LA2_0==TypeSystemString) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:248:3: morestmts= globalStatement
            	    {
            	    pushFollow(FOLLOW_globalStatement_in_globalStatements180);
            	    morestmts=globalStatement();

            	    state._fsp--;
            	    if (state.failed) return stmts;

            	    if ( state.backtracking==0 ) {if(morestmts != null) {stmts.addAll(morestmts);}}

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "globalStatements"



    // $ANTLR start "globalStatement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:251:1: globalStatement returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : stmtImport= importStatement ;
    public final List<TextMarkerStatement> globalStatement() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        TextMarkerStatement stmtImport =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:252:2: (stmtImport= importStatement )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:253:2: stmtImport= importStatement
            {
            pushFollow(FOLLOW_importStatement_in_globalStatement204);
            stmtImport=importStatement();

            state._fsp--;
            if (state.failed) return stmts;

            if ( state.backtracking==0 ) {stmts.add(stmtImport);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "globalStatement"



    // $ANTLR start "statement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:256:1: statement returns [TextMarkerStatement stmt = null] : (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration ) ;
    public final TextMarkerStatement statement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        TextMarkerStatement stmtDecl =null;

        TextMarkerStatement stmtVariable =null;

        TextMarkerRule stmtRule =null;

        TextMarkerBlock stmtBlock =null;

        TextMarkerBlock stmtAutomata =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:257:2: ( (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:258:2: (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:258:2: (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration )
            int alt3=5;
            switch ( input.LA(1) ) {
            case DECLARE:
                {
                alt3=1;
                }
                break;
            case WORDLIST:
            case WORDTABLE:
            case TypeString:
            case IntString:
            case DoubleString:
            case StringString:
            case BooleanString:
            case CONDITION:
            case ACTION:
            case BOOLEANLIST:
            case INTLIST:
            case DOUBLELIST:
            case STRINGLIST:
            case TYPELIST:
                {
                alt3=2;
                }
                break;
            case BasicAnnotationType:
            case StringLiteral:
            case Identifier:
            case LPAREN:
                {
                alt3=3;
                }
                break;
            case BlockString:
                {
                alt3=4;
                }
                break;
            case AutomataBlockString:
                {
                alt3=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmt;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:258:4: stmtDecl= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_statement230);
                    stmtDecl=declaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtDecl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:259:4: stmtVariable= variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement241);
                    stmtVariable=variableDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtVariable;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:260:4: stmtRule= simpleStatement
                    {
                    pushFollow(FOLLOW_simpleStatement_in_statement252);
                    stmtRule=simpleStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtRule;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:261:4: stmtBlock= blockDeclaration
                    {
                    pushFollow(FOLLOW_blockDeclaration_in_statement263);
                    stmtBlock=blockDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtBlock;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:262:4: stmtAutomata= automataDeclaration
                    {
                    pushFollow(FOLLOW_automataDeclaration_in_statement274);
                    stmtAutomata=automataDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtBlock;}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "statement"



    // $ANTLR start "variableDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:266:1: variableDeclaration returns [TextMarkerStatement stmt = null] : (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration );
    public final TextMarkerStatement variableDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        Token name=null;
        NumberExpression value1 =null;

        NumberExpression value2 =null;

        StringExpression value3 =null;

        BooleanExpression value4 =null;

        Token value5 =null;

        WordListExpression list =null;

        WordTableExpression table =null;

        BooleanListExpression bl =null;

        StringListExpression sl =null;

        NumberListExpression il =null;

        NumberListExpression dl =null;

        TypeListExpression tl =null;

        TextMarkerStatement stmt1 =null;

        TextMarkerStatement stmt2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:267:2: (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration )
            int alt21=14;
            switch ( input.LA(1) ) {
            case IntString:
                {
                alt21=1;
                }
                break;
            case DoubleString:
                {
                alt21=2;
                }
                break;
            case StringString:
                {
                alt21=3;
                }
                break;
            case BooleanString:
                {
                alt21=4;
                }
                break;
            case TypeString:
                {
                alt21=5;
                }
                break;
            case WORDLIST:
                {
                alt21=6;
                }
                break;
            case WORDTABLE:
                {
                alt21=7;
                }
                break;
            case BOOLEANLIST:
                {
                alt21=8;
                }
                break;
            case STRINGLIST:
                {
                alt21=9;
                }
                break;
            case INTLIST:
                {
                alt21=10;
                }
                break;
            case DOUBLELIST:
                {
                alt21=11;
                }
                break;
            case TYPELIST:
                {
                alt21=12;
                }
                break;
            case CONDITION:
                {
                alt21=13;
                }
                break;
            case ACTION:
                {
                alt21=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmt;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:268:2: type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI
                    {
                    type=(Token)match(input,IntString,FOLLOW_IntString_in_variableDeclaration299); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration308); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:270:3: ( COMMA {...}?id= Identifier )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==COMMA) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:270:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration315); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration323); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:271:6: ( ASSIGN_EQUAL value1= numberExpression )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==ASSIGN_EQUAL) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:271:7: ASSIGN_EQUAL value1= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration333); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration339);
                            value1=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value1);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration346); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:273:2: type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI
                    {
                    type=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_variableDeclaration356); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration365); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:275:3: ( COMMA {...}?id= Identifier )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:275:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration372); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration380); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:276:7: ( ASSIGN_EQUAL value2= numberExpression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==ASSIGN_EQUAL) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:276:8: ASSIGN_EQUAL value2= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration391); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration397);
                            value2=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value2);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration403); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:278:2: type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration413); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration422); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:280:3: ( COMMA {...}?id= Identifier )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:280:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration429); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration437); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:281:7: ( ASSIGN_EQUAL value3= stringExpression )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==ASSIGN_EQUAL) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:281:8: ASSIGN_EQUAL value3= stringExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration448); if (state.failed) return stmt;

                            pushFollow(FOLLOW_stringExpression_in_variableDeclaration454);
                            value3=stringExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value3);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration460); if (state.failed) return stmt;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:283:2: type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration470); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration479); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:285:3: ( COMMA {...}?id= Identifier )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:285:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration486); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration494); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:286:7: ( ASSIGN_EQUAL value4= booleanExpression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ASSIGN_EQUAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:286:8: ASSIGN_EQUAL value4= booleanExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration505); if (state.failed) return stmt;

                            pushFollow(FOLLOW_booleanExpression_in_variableDeclaration511);
                            value4=booleanExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value4);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration517); if (state.failed) return stmt;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:288:2: type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration527); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration536); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:290:3: ( COMMA {...}?id= Identifier )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==COMMA) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:290:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration543); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration551); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:291:7: ( ASSIGN_EQUAL value5= annotationType )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==ASSIGN_EQUAL) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:291:8: ASSIGN_EQUAL value5= annotationType
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration562); if (state.failed) return stmt;

                            pushFollow(FOLLOW_annotationType_in_variableDeclaration568);
                            value5=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value5);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration574); if (state.failed) return stmt;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:293:2: type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration585); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration597); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:295:20: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==ASSIGN_EQUAL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:295:21: ASSIGN_EQUAL list= wordListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration600); if (state.failed) return stmt;

                            pushFollow(FOLLOW_wordListExpression_in_variableDeclaration606);
                            list=wordListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration610); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(list != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), list);}}

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:297:2: type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration624); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration636); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:299:20: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:299:21: ASSIGN_EQUAL table= wordTableExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration639); if (state.failed) return stmt;

                            pushFollow(FOLLOW_wordTableExpression_in_variableDeclaration645);
                            table=wordTableExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration649); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(table != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), table);}}

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:301:2: type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration661); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration673); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:303:20: ( ASSIGN_EQUAL bl= booleanListExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:303:21: ASSIGN_EQUAL bl= booleanListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration676); if (state.failed) return stmt;

                            pushFollow(FOLLOW_booleanListExpression_in_variableDeclaration682);
                            bl=booleanListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration686); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(bl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), bl);}}

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:305:2: type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration699); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration711); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:307:20: ( ASSIGN_EQUAL sl= stringListExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:307:21: ASSIGN_EQUAL sl= stringListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration714); if (state.failed) return stmt;

                            pushFollow(FOLLOW_stringListExpression_in_variableDeclaration720);
                            sl=stringListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration724); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(sl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), sl);}}

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:309:2: type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration737); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration749); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:311:20: ( ASSIGN_EQUAL il= numberListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:311:21: ASSIGN_EQUAL il= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration752); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration758);
                            il=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration762); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(il != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), il);}}

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:313:2: type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration775); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration787); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:315:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:315:21: ASSIGN_EQUAL dl= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration790); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration796);
                            dl=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration800); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(dl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), dl);}}

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:317:2: type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration813); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration825); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:319:20: ( ASSIGN_EQUAL tl= typeListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:319:21: ASSIGN_EQUAL tl= typeListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration828); if (state.failed) return stmt;

                            pushFollow(FOLLOW_typeListExpression_in_variableDeclaration834);
                            tl=typeListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration838); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(tl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), tl);}}

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:321:2: stmt1= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration851);
                    stmt1=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmt1;}

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:323:2: stmt2= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration863);
                    stmt2=actionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmt2;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "variableDeclaration"



    // $ANTLR start "conditionDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:327:1: conditionDeclaration returns [TextMarkerStatement stmt = null] : type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final TextMarkerStatement conditionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerCondition> cons =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:328:5: (type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:329:5: type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
            {
            type=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_conditionDeclaration892); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionDeclaration898); if (state.failed) return stmt;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration900); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionDeclaration902); if (state.failed) return stmt;

            pushFollow(FOLLOW_conditions_in_conditionDeclaration908);
            cons=conditions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionDeclaration910); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_conditionDeclaration912); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                AbstractTextMarkerCondition condition = ConditionFactory.createConditionAnd(cons,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), condition);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "conditionDeclaration"



    // $ANTLR start "actionDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:336:1: actionDeclaration returns [TextMarkerStatement stmt = null] : type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final TextMarkerStatement actionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:337:5: (type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:338:5: type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
            {
            type=(Token)match(input,ACTION,FOLLOW_ACTION_in_actionDeclaration948); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionDeclaration954); if (state.failed) return stmt;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionDeclaration956); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDeclaration958); if (state.failed) return stmt;

            pushFollow(FOLLOW_actions_in_actionDeclaration964);
            a=actions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDeclaration966); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_actionDeclaration968); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                AbstractTextMarkerAction action = ActionFactory.createComposedAction(a,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), action);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "actionDeclaration"



    // $ANTLR start "importStatement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:344:1: importStatement returns [TextMarkerStatement stmt = null] : ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI );
    public final TextMarkerStatement importStatement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        String ts =null;

        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:345:2: ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI )
            int alt22=3;
            switch ( input.LA(1) ) {
            case TypeSystemString:
                {
                alt22=1;
                }
                break;
            case ScriptString:
                {
                alt22=2;
                }
                break;
            case EngineString:
                {
                alt22=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmt;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:346:2: TypeSystemString ts= dottedIdentifier2 SEMI
                    {
                    match(input,TypeSystemString,FOLLOW_TypeSystemString_in_importStatement993); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement999);
                    ts=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportTypeSystem(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ts);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1002); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:347:4: ScriptString ns= dottedIdentifier2 SEMI
                    {
                    match(input,ScriptString,FOLLOW_ScriptString_in_importStatement1007); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1013);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportScript(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1016); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:348:4: EngineString ns= dottedIdentifier2 SEMI
                    {
                    match(input,EngineString,FOLLOW_EngineString_in_importStatement1021); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1027);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportEngine(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1030); if (state.failed) return stmt;

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "importStatement"



    // $ANTLR start "declaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:351:1: declaration returns [TextMarkerStatement stmt = null] : ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
    public final TextMarkerStatement declaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token id=null;
        Token newName=null;
        Token obj2=null;
        Token obj3=null;
        Token obj4=null;
        Token obj5=null;
        Token fname=null;
        Token lazyParent =null;

        Token type =null;

        Token obj1 =null;



        List featureTypes = new ArrayList();
        List featureNames = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:356:2: ( ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:357:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:357:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:358:2: DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1054); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:13: (lazyParent= annotationType )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==BasicAnnotationType) ) {
                        alt23=1;
                    }
                    else if ( (LA23_0==Identifier) ) {
                        int LA23_2 = input.LA(2);

                        if ( (LA23_2==Identifier||LA23_2==DOT) ) {
                            alt23=1;
                        }
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:13: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1064);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1072); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:362:4: ( COMMA id= Identifier )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:362:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1079); if (state.failed) return stmt;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1093); if (state.failed) return stmt;

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1102); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:367:2: DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1109); if (state.failed) return stmt;

                    pushFollow(FOLLOW_annotationType_in_declaration1115);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmt;

                    newName=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1121); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:368:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:368:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1127); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:369:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString )
                    int alt25=5;
                    switch ( input.LA(1) ) {
                    case BasicAnnotationType:
                    case Identifier:
                        {
                        alt25=1;
                        }
                        break;
                    case StringString:
                        {
                        alt25=2;
                        }
                        break;
                    case DoubleString:
                        {
                        alt25=3;
                        }
                        break;
                    case IntString:
                        {
                        alt25=4;
                        }
                        break;
                    case BooleanString:
                        {
                        alt25=5;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 0, input);

                        throw nvae;

                    }

                    switch (alt25) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:370:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1142);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:371:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1155); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:372:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1168); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:373:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1180); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:374:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1192); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1208); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:377:4: ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:378:4: COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1220); if (state.failed) return stmt;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:379:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString )
                    	    int alt26=5;
                    	    switch ( input.LA(1) ) {
                    	    case BasicAnnotationType:
                    	    case Identifier:
                    	        {
                    	        alt26=1;
                    	        }
                    	        break;
                    	    case StringString:
                    	        {
                    	        alt26=2;
                    	        }
                    	        break;
                    	    case DoubleString:
                    	        {
                    	        alt26=3;
                    	        }
                    	        break;
                    	    case IntString:
                    	        {
                    	        alt26=4;
                    	        }
                    	        break;
                    	    case BooleanString:
                    	        {
                    	        alt26=5;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 26, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt26) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:380:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1235);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:381:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1248); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:382:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1261); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:383:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1273); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:384:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1285); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                    	            }
                    	            break;

                    	    }


                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1301); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1309); if (state.failed) return stmt;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1312); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, newName.getText(), type.getText(), featureTypes, featureNames);}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "declaration"


    protected static class blockDeclaration_scope {
        TextMarkerBlock env;
    }
    protected Stack blockDeclaration_stack = new Stack();



    // $ANTLR start "blockDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:393:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
    public final TextMarkerBlock blockDeclaration() throws RecognitionException {
        blockDeclaration_stack.push(new blockDeclaration_scope());
        TextMarkerBlock block =  null;


        Token type=null;
        Token id=null;
        TextMarkerRuleElement re1 =null;

        List<TextMarkerStatement> body =null;



        TextMarkerRuleElement re = null;
        RuleElementIsolator container = null;
        level++;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:410:2: (type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:411:2: type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1370); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1374); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1381); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1385); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createScriptBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env, cas);}

            if ( state.backtracking==0 ) {((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	container = new RuleElementIsolator();}

            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1398);
            re1=ruleElementWithCA(container);

            state._fsp--;
            if (state.failed) return block;

            if ( state.backtracking==0 ) {re = re1;	 }

            if ( state.backtracking==0 ) {TextMarkerRule rule = factory.createRule(re, block);
            	if(block instanceof TextMarkerScriptBlock) {
            	((TextMarkerScriptBlock)block).setRule(rule);
            	}
            	container.setContainer(rule);
            	}

            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1409); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_blockDeclaration1415);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1417); if (state.failed) return block;

            if ( state.backtracking==0 ) {block.setElements(body);
            	((blockDeclaration_scope)blockDeclaration_stack.peek()).env.getScript().addBlock(id.getText(),block);
            	}

            }

            if ( state.backtracking==0 ) {
            level--;
            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
            blockDeclaration_stack.pop();
        }
        return block;
    }
    // $ANTLR end "blockDeclaration"


    protected static class automataDeclaration_scope {
        TextMarkerBlock env;
    }
    protected Stack automataDeclaration_stack = new Stack();



    // $ANTLR start "automataDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:432:1: automataDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
    public final TextMarkerBlock automataDeclaration() throws RecognitionException {
        automataDeclaration_stack.push(new automataDeclaration_scope());
        TextMarkerBlock block =  null;


        Token type=null;
        Token id=null;
        TextMarkerRuleElement re1 =null;

        List<TextMarkerStatement> body =null;



        TextMarkerRuleElement re = null;
        RuleElementIsolator container = null;
        TextMarkerScriptFactory oldFactory = factory;
        factory = automataFactory; 
        level++;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:451:2: (type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:453:2: type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_automataDeclaration1469); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_automataDeclaration1473); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_automataDeclaration1480); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_automataDeclaration1484); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createAutomataBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env, cas);}

            if ( state.backtracking==0 ) {((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	container = new RuleElementIsolator();}

            pushFollow(FOLLOW_ruleElementWithCA_in_automataDeclaration1497);
            re1=ruleElementWithCA(container);

            state._fsp--;
            if (state.failed) return block;

            if ( state.backtracking==0 ) {re = re1;}

            if ( state.backtracking==0 ) {TextMarkerRule rule = factory.createRule(re, block);
            	if(block instanceof TextMarkerAutomataBlock) {
            	((TextMarkerAutomataBlock)block).setMatchRule(rule);
            	}
            	container.setContainer(rule);
            	}

            match(input,LCURLY,FOLLOW_LCURLY_in_automataDeclaration1506); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_automataDeclaration1512);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            match(input,RCURLY,FOLLOW_RCURLY_in_automataDeclaration1514); if (state.failed) return block;

            if ( state.backtracking==0 ) {block.setElements(body);
            	((blockDeclaration_scope)blockDeclaration_stack.peek()).env.getScript().addBlock(id.getText(),block);

            	}

            }

            if ( state.backtracking==0 ) {
            factory = oldFactory;
            level--;
            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
            automataDeclaration_stack.pop();
        }
        return block;
    }
    // $ANTLR end "automataDeclaration"



    // $ANTLR start "ruleElementWithCA"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:475:1: ruleElementWithCA[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression idRef =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:476:5: (idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:478:5: idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1551);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(idRef,null,null,null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:480:7: (q= quantifierPart )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LBRACK||LA29_0==PLUS||LA29_0==STAR||LA29_0==QUESTION) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:480:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1568);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1580); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:481:18: (c= conditions )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==CONTAINS||(LA30_0 >= AND && LA30_0 <= PARSE)||(LA30_0 >= BEFORE && LA30_0 <= NOT)||LA30_0==SIZE||LA30_0==Identifier||LA30_0==MINUS) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:481:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1586);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:481:32: ( THEN a= actions )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==THEN) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:481:33: THEN a= actions
                    {
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1590); if (state.failed) return re;

                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1596);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1600); if (state.failed) return re;

            if ( state.backtracking==0 ) {
            	if(q != null) {
            		re.setQuantifier(q);
            	}
            	if(c!= null) {
            		re.setConditions(c);
            	}
            	if(a != null) {
            		re.setActions(a);
            	}
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementWithCA"



    // $ANTLR start "simpleStatement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:497:1: simpleStatement returns [TextMarkerRule stmt = null] :elements= ruleElements[stmt.getRoot()] SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;


        List<RuleElement> elements =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:498:2: (elements= ruleElements[stmt.getRoot()] SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:499:2: elements= ruleElements[stmt.getRoot()] SEMI
            {
            if ( state.backtracking==0 ) {stmt = factory.createRule(elements, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            pushFollow(FOLLOW_ruleElements_in_simpleStatement1641);
            elements=ruleElements(stmt.getRoot());

            state._fsp--;
            if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1644); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {stmt.setRuleElements(elements);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "simpleStatement"



    // $ANTLR start "ruleElements"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:504:1: ruleElements[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()] : re= ruleElement[container] (re= ruleElement[container] )* ;
    public final List<RuleElement> ruleElements(RuleElementContainer container) throws RecognitionException {
        List<RuleElement> elements =  new ArrayList<RuleElement>();


        RuleElement re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:505:2: (re= ruleElement[container] (re= ruleElement[container] )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:506:2: re= ruleElement[container] (re= ruleElement[container] )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1671);
            re=ruleElement(container);

            state._fsp--;
            if (state.failed) return elements;

            if ( state.backtracking==0 ) {elements.add(re);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:506:50: (re= ruleElement[container] )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==BasicAnnotationType||LA32_0==StringLiteral||(LA32_0 >= Identifier && LA32_0 <= LPAREN)) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:506:51: re= ruleElement[container]
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1681);
            	    re=ruleElement(container);

            	    state._fsp--;
            	    if (state.failed) return elements;

            	    if ( state.backtracking==0 ) {elements.add(re);}

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return elements;
    }
    // $ANTLR end "ruleElements"



    // $ANTLR start "ruleElement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:510:1: ruleElement[RuleElementContainer container] returns [RuleElement re = null] : (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] );
    public final RuleElement ruleElement(RuleElementContainer container) throws RecognitionException {
        RuleElement re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        ComposedRuleElement re3 =null;

        TextMarkerRuleElement re4 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:511:2: (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] )
            int alt33=4;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA33_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))))) ) {
                    alt33=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    alt33=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt33=1;
                }
                break;
            case StringLiteral:
                {
                alt33=2;
                }
                break;
            case LPAREN:
                {
                int LA33_4 = input.LA(2);

                if ( (synpred1_TextMarkerParser()) ) {
                    alt33=3;
                }
                else if ( (synpred2_TextMarkerParser()) ) {
                    alt33=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 4, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }

            switch (alt33) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:512:2: re1= ruleElementType[container]
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1710);
                    re1=ruleElementType(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:513:4: re2= ruleElementLiteral[container]
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1722);
                    re2=ruleElementLiteral(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:4: ( ruleElementComposed[null] )=>re3= ruleElementComposed[container]
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1739);
                    re3=ruleElementComposed(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:515:4: ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container]
                    {
                    pushFollow(FOLLOW_ruleElementDisjunctive_in_ruleElement1757);
                    re4=ruleElementDisjunctive(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re4;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElement"



    // $ANTLR start "ruleElementDisjunctive"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:518:1: ruleElementDisjunctive[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementDisjunctive(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression type1 =null;

        TypeExpression type2 =null;

        TypeExpression type3 =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;



        	List<TypeExpression> typeExprs = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:522:5: ( LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:523:5: LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementDisjunctive1789); if (state.failed) return re;

            pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1805);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {typeExprs.add(type1);}

            match(input,VBAR,FOLLOW_VBAR_in_ruleElementDisjunctive1814); if (state.failed) return re;

            pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1820);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {typeExprs.add(type2);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:526:5: ( VBAR type3= typeExpression )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==VBAR) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:526:6: VBAR type3= typeExpression
                    {
                    match(input,VBAR,FOLLOW_VBAR_in_ruleElementDisjunctive1829); if (state.failed) return re;

                    pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1835);
                    type3=typeExpression();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {typeExprs.add(type3);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementDisjunctive1844); if (state.failed) return re;

            if ( state.backtracking==0 ) { re = factory.createRuleElement(typeExprs, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:530:8: (q= quantifierPart )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LBRACK||LA35_0==PLUS||LA35_0==STAR||LA35_0==QUESTION) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:530:8: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementDisjunctive1870);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LCURLY) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementDisjunctive1883); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:19: (c= conditions )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==CONTAINS||(LA36_0 >= AND && LA36_0 <= PARSE)||(LA36_0 >= BEFORE && LA36_0 <= NOT)||LA36_0==SIZE||LA36_0==Identifier||LA36_0==MINUS) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementDisjunctive1889);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:33: ( THEN a= actions )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==THEN) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementDisjunctive1893); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementDisjunctive1899);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementDisjunctive1903); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            	if(q != null) {
            		re.setQuantifier(q);
            	}
            	if(c!= null) {
            		re.setConditions(c);
            	}
            	if(a != null) {
            		re.setActions(a);
            	}
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementDisjunctive"


    protected static class ruleElementComposed_scope {
        RuleElementContainer con;
    }
    protected Stack ruleElementComposed_stack = new Stack();



    // $ANTLR start "ruleElementComposed"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:545:1: ruleElementComposed[RuleElementContainer container] returns [ComposedRuleElement re = null] : LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final ComposedRuleElement ruleElementComposed(RuleElementContainer container) throws RecognitionException {
        ruleElementComposed_stack.push(new ruleElementComposed_scope());
        ComposedRuleElement re =  null;


        List<RuleElement> res =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;





        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:552:2: ( LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:553:2: LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            if ( state.backtracking==0 ) {re = factory.createComposedRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	// dre = factory.createDisjunctiveRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con = re;}

            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed1944); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:558:2: ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:564:2: ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con]
            {
            pushFollow(FOLLOW_ruleElements_in_ruleElementComposed1972);
            res=ruleElements(((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con);

            state._fsp--;
            if (state.failed) return re;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed1982); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:11: (q= quantifierPart )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==LBRACK||LA39_0==PLUS||LA39_0==STAR||LA39_0==QUESTION) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:11: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed1988);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:29: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LCURLY) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:30: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed1992); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:39: (c= conditions )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==CONTAINS||(LA40_0 >= AND && LA40_0 <= PARSE)||(LA40_0 >= BEFORE && LA40_0 <= NOT)||LA40_0==SIZE||LA40_0==Identifier||LA40_0==MINUS) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:39: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed1998);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:53: ( THEN a= actions )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==THEN) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:54: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementComposed2002); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementComposed2008);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementComposed2012); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            	re.setRuleElements(res);
            	if(q != null) {
            		re.setQuantifier(q);
            	}
            	if(c!= null) {
            		re.setConditions(c);
            	}
            	if(a != null) {
            		re.setActions(a);
            	}
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
            ruleElementComposed_stack.pop();
        }
        return re;
    }
    // $ANTLR end "ruleElementComposed"



    // $ANTLR start "ruleElementType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:1: ruleElementType[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression typeExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:584:5: ( ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:586:5: ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2055);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(typeExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:588:7: (q= quantifierPart )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==LBRACK||LA43_0==PLUS||LA43_0==STAR||LA43_0==QUESTION) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:588:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2074);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LCURLY) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2087); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:19: (c= conditions )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==CONTAINS||(LA44_0 >= AND && LA44_0 <= PARSE)||(LA44_0 >= BEFORE && LA44_0 <= NOT)||LA44_0==SIZE||LA44_0==Identifier||LA44_0==MINUS) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2093);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:33: ( THEN a= actions )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==THEN) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2097); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementType2103);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2107); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            	if(q != null) {
            		re.setQuantifier(q);
            	}
            	if(c!= null) {
            		re.setConditions(c);
            	}
            	if(a != null) {
            		re.setActions(a);
            	}
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementType"



    // $ANTLR start "ruleElementLiteral"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:603:1: ruleElementLiteral[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        StringExpression stringExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:5: ( ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:606:5: ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2154);
            stringExpr=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(stringExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:609:7: (q= quantifierPart )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==LBRACK||LA47_0==PLUS||LA47_0==STAR||LA47_0==QUESTION) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:609:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2178);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:610:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LCURLY) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:610:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2191); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:610:19: (c= conditions )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==CONTAINS||(LA48_0 >= AND && LA48_0 <= PARSE)||(LA48_0 >= BEFORE && LA48_0 <= NOT)||LA48_0==SIZE||LA48_0==Identifier||LA48_0==MINUS) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:610:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2197);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:610:33: ( THEN a= actions )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==THEN) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:610:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2201); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2207);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2211); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            	if(q != null) {
            		re.setQuantifier(q);
            	}
            	if(c!= null) {
            		re.setConditions(c);
            	}
            	if(a != null) {
            		re.setActions(a);
            	}
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementLiteral"



    // $ANTLR start "conditions"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:624:1: conditions returns [List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<AbstractTextMarkerCondition> conditions() throws RecognitionException {
        List<AbstractTextMarkerCondition> conds =  new ArrayList<AbstractTextMarkerCondition>();


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:625:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:626:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2249);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;

            if ( state.backtracking==0 ) {conds.add(c);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:626:35: ( COMMA c= condition )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:626:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2254); if (state.failed) return conds;

            	    pushFollow(FOLLOW_condition_in_conditions2260);
            	    c=condition();

            	    state._fsp--;
            	    if (state.failed) return conds;

            	    if ( state.backtracking==0 ) {conds.add(c);}

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return conds;
    }
    // $ANTLR end "conditions"



    // $ANTLR start "actions"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:629:1: actions returns [List<AbstractTextMarkerAction> actions = new ArrayList<AbstractTextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<AbstractTextMarkerAction> actions() throws RecognitionException {
        List<AbstractTextMarkerAction> actions =  new ArrayList<AbstractTextMarkerAction>();


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:630:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2298);
            a=action();

            state._fsp--;
            if (state.failed) return actions;

            if ( state.backtracking==0 ) {actions.add(a);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:34: ( COMMA a= action )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==COMMA) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2303); if (state.failed) return actions;

            	    pushFollow(FOLLOW_action_in_actions2309);
            	    a=action();

            	    state._fsp--;
            	    if (state.failed) return actions;

            	    if ( state.backtracking==0 ) {actions.add(a);}

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return actions;
    }
    // $ANTLR end "actions"



    // $ANTLR start "listExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:635:1: listExpression returns [ListExpression expr = null] : ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression );
    public final ListExpression listExpression() throws RecognitionException {
        ListExpression expr =  null;


        BooleanListExpression bl =null;

        NumberListExpression il =null;

        NumberListExpression dl =null;

        StringListExpression sl =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:2: ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression )
            int alt53=5;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LCURLY) ) {
                int LA53_1 = input.LA(2);

                if ( (synpred7_TextMarkerParser()) ) {
                    alt53=1;
                }
                else if ( (synpred8_TextMarkerParser()) ) {
                    alt53=2;
                }
                else if ( (synpred9_TextMarkerParser()) ) {
                    alt53=3;
                }
                else if ( (synpred10_TextMarkerParser()) ) {
                    alt53=4;
                }
                else if ( (synpred11_TextMarkerParser()) ) {
                    alt53=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA53_0==Identifier) ) {
                int LA53_2 = input.LA(2);

                if ( (((synpred7_TextMarkerParser()&&synpred7_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt53=1;
                }
                else if ( ((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))&&synpred8_TextMarkerParser())) ) {
                    alt53=2;
                }
                else if ( (((synpred9_TextMarkerParser()&&synpred9_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST")))) ) {
                    alt53=3;
                }
                else if ( (((synpred10_TextMarkerParser()&&synpred10_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt53=4;
                }
                else if ( ((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST")))&&synpred11_TextMarkerParser())) ) {
                    alt53=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }
            switch (alt53) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:637:2: ( booleanListExpression )=>bl= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2345);
                    bl=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:638:4: ( intListExpression )=>il= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2361);
                    il=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = il;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:639:4: ( doubleListExpression )=>dl= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2377);
                    dl=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = dl;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:640:4: ( stringListExpression )=>sl= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2393);
                    sl=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = sl;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:641:4: ( typeListExpression )=>tl= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2409);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = tl;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "listExpression"



    // $ANTLR start "booleanListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:644:1: booleanListExpression returns [BooleanListExpression expr = null] : e= simpleBooleanListExpression ;
    public final BooleanListExpression booleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        BooleanListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:645:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:646:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2431);
            e=simpleBooleanListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanListExpression"



    // $ANTLR start "simpleBooleanListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:649:1: simpleBooleanListExpression returns [BooleanListExpression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier );
    public final BooleanListExpression simpleBooleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        Token var=null;
        BooleanExpression e =null;



        	List<BooleanExpression> list = new ArrayList<BooleanExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:652:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==LCURLY) ) {
                alt56=1;
            }
            else if ( (LA56_0==Identifier) ) {
                alt56=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }
            switch (alt56) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2452); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( ((LA55_0 >= TRUE && LA55_0 <= FALSE)||LA55_0==Identifier) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2459);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:53: ( COMMA e= simpleBooleanExpression )*
                            loop54:
                            do {
                                int alt54=2;
                                int LA54_0 = input.LA(1);

                                if ( (LA54_0==COMMA) ) {
                                    alt54=1;
                                }


                                switch (alt54) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2464); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2470);
                            	    e=simpleBooleanExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop54;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2479); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:656:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEANLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2494); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceBooleanListExpression(var);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanListExpression"



    // $ANTLR start "intListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:661:1: intListExpression returns [NumberListExpression expr = null] : e= simpleIntListExpression ;
    public final NumberListExpression intListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:662:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:663:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2519);
            e=simpleIntListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "intListExpression"



    // $ANTLR start "simpleIntListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:666:1: simpleIntListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleIntListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:669:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==LCURLY) ) {
                alt59=1;
            }
            else if ( (LA59_0==Identifier) ) {
                alt59=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }
            switch (alt59) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:670:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2540); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:670:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==DecimalLiteral||LA58_0==FloatingPointLiteral||(LA58_0 >= Identifier && LA58_0 <= LPAREN)||LA58_0==MINUS) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:670:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2547);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:670:52: ( COMMA e= simpleNumberExpression )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==COMMA) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:670:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2552); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2558);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop57;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2567); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:673:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INTLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2582); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceIntListExpression(var);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleIntListExpression"



    // $ANTLR start "numberListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:678:1: numberListExpression returns [NumberListExpression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression |e2= intListExpression );
    public final NumberListExpression numberListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e1 =null;

        NumberListExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:679:2: ( (e1= doubleListExpression )=>e1= doubleListExpression |e2= intListExpression )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==LCURLY) ) {
                int LA60_1 = input.LA(2);

                if ( (synpred12_TextMarkerParser()) ) {
                    alt60=1;
                }
                else if ( (true) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA60_0==Identifier) ) {
                int LA60_2 = input.LA(2);

                if ( ((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST")))&&synpred12_TextMarkerParser())) ) {
                    alt60=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                    alt60=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 60, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;

            }
            switch (alt60) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:680:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2616);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:682:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2628);
                    e2=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberListExpression"



    // $ANTLR start "doubleListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:685:1: doubleListExpression returns [NumberListExpression expr = null] : e= simpleDoubleListExpression ;
    public final NumberListExpression doubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:686:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:687:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2651);
            e=simpleDoubleListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "doubleListExpression"



    // $ANTLR start "simpleDoubleListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:690:1: simpleDoubleListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleDoubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:693:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==LCURLY) ) {
                alt63=1;
            }
            else if ( (LA63_0==Identifier) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2672); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==DecimalLiteral||LA62_0==FloatingPointLiteral||(LA62_0 >= Identifier && LA62_0 <= LPAREN)||LA62_0==MINUS) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2679);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:52: ( COMMA e= simpleNumberExpression )*
                            loop61:
                            do {
                                int alt61=2;
                                int LA61_0 = input.LA(1);

                                if ( (LA61_0==COMMA) ) {
                                    alt61=1;
                                }


                                switch (alt61) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2684); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2690);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop61;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2699); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:697:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2714); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceDoubleListExpression(var);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleDoubleListExpression"



    // $ANTLR start "stringListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:1: stringListExpression returns [StringListExpression expr = null] : e= simpleStringListExpression ;
    public final StringListExpression stringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        StringListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:703:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:704:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2739);
            e=simpleStringListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "stringListExpression"



    // $ANTLR start "simpleStringListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:707:1: simpleStringListExpression returns [StringListExpression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier );
    public final StringListExpression simpleStringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        Token var=null;
        StringExpression e =null;



        	List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:710:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==LCURLY) ) {
                alt66=1;
            }
            else if ( (LA66_0==Identifier) ) {
                alt66=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression2760); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==StringLiteral||LA65_0==Identifier) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2767);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:52: ( COMMA e= simpleStringExpression )*
                            loop64:
                            do {
                                int alt64=2;
                                int LA64_0 = input.LA(1);

                                if ( (LA64_0==COMMA) ) {
                                    alt64=1;
                                }


                                switch (alt64) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression2772); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2778);
                            	    e=simpleStringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop64;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression2787); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:714:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRINGLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression2803); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceStringListExpression(var);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleStringListExpression"



    // $ANTLR start "typeListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:1: typeListExpression returns [TypeListExpression expr = null] : e= simpleTypeListExpression ;
    public final TypeListExpression typeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        TypeListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:720:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:721:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression2828);
            e=simpleTypeListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "typeListExpression"



    // $ANTLR start "simpleTypeListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:1: simpleTypeListExpression returns [TypeListExpression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier );
    public final TypeListExpression simpleTypeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        Token var=null;
        TypeExpression e =null;



        	List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:727:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==LCURLY) ) {
                alt69=1;
            }
            else if ( (LA69_0==Identifier) ) {
                alt69=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;

            }
            switch (alt69) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:728:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression2849); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:728:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==BasicAnnotationType||LA68_0==Identifier) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:728:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2856);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:728:50: ( COMMA e= simpleTypeExpression )*
                            loop67:
                            do {
                                int alt67=2;
                                int LA67_0 = input.LA(1);

                                if ( (LA67_0==COMMA) ) {
                                    alt67=1;
                                }


                                switch (alt67) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:728:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression2861); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2867);
                            	    e=simpleTypeExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop67;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression2876); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTypeListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:731:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression2891); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceTypeListExpression(var);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleTypeListExpression"



    // $ANTLR start "typeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:736:1: typeExpression returns [TypeExpression type = null] options {backtrack=true; } : (tf= typeFunction |st= simpleTypeExpression );
    public final TypeExpression typeExpression() throws RecognitionException {
        TypeExpression type =  null;


        TypeExpression tf =null;

        TypeExpression st =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:740:2: (tf= typeFunction |st= simpleTypeExpression )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==Identifier) ) {
                int LA70_1 = input.LA(2);

                if ( (synpred13_TextMarkerParser()) ) {
                    alt70=1;
                }
                else if ( (true) ) {
                    alt70=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return type;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA70_0==BasicAnnotationType) ) {
                alt70=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;

            }
            switch (alt70) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression2928);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:742:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression2939);
                    st=simpleTypeExpression();

                    state._fsp--;
                    if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = st;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return type;
    }
    // $ANTLR end "typeExpression"



    // $ANTLR start "typeFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:747:1: typeFunction returns [TypeExpression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final TypeExpression typeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        TypeExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:748:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:749:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction2973);
            e=externalTypeFunction();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "typeFunction"



    // $ANTLR start "externalTypeFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:753:1: externalTypeFunction returns [TypeExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TypeExpression externalTypeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:754:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:756:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction2998); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction3000); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction3007);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction3009); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalTypeFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalTypeFunction"



    // $ANTLR start "simpleTypeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:763:1: simpleTypeExpression returns [TypeExpression type = null] : ({...}?var= Identifier |at= annotationType );
    public final TypeExpression simpleTypeExpression() throws RecognitionException {
        TypeExpression type =  null;


        Token var=null;
        Token at =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:764:2: ({...}?var= Identifier |at= annotationType )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==Identifier) ) {
                int LA71_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                    alt71=1;
                }
                else if ( (true) ) {
                    alt71=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return type;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 71, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA71_0==BasicAnnotationType) ) {
                alt71=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;

            }
            switch (alt71) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:765:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "simpleTypeExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPE\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeExpression3034); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = ExpressionFactory.createReferenceTypeExpression(var);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:768:2: at= annotationType
                    {
                    pushFollow(FOLLOW_annotationType_in_simpleTypeExpression3048);
                    at=annotationType();

                    state._fsp--;
                    if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = ExpressionFactory.createSimpleTypeExpression(at, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return type;
    }
    // $ANTLR end "simpleTypeExpression"



    // $ANTLR start "variable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:773:1: variable returns [Token var = null] :{...}?v= Identifier ;
    public final Token variable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:774:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:775:2: {...}?v= Identifier
            {
            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "isVariable($blockDeclaration::env, input.LT(1).getText())");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3074); if (state.failed) return var;

            if ( state.backtracking==0 ) {var = v;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return var;
    }
    // $ANTLR end "variable"



    // $ANTLR start "listVariable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:778:1: listVariable returns [Token var = null] :{...}?v= Identifier ;
    public final Token listVariable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:779:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:780:2: {...}?v= Identifier
            {
            if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEANLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INTLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLELIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRINGLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPELIST")
            	)) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "listVariable", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEANLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INTLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLELIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRINGLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPELIST\")\r\n\t");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3098); if (state.failed) return var;

            if ( state.backtracking==0 ) {var = v;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return var;
    }
    // $ANTLR end "listVariable"



    // $ANTLR start "quantifierPart"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:803:1: quantifierPart returns [RuleElementQuantifier quantifier = null] : ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? );
    public final RuleElementQuantifier quantifierPart() throws RecognitionException {
        RuleElementQuantifier quantifier =  null;


        Token q=null;
        Token comma=null;
        NumberExpression min =null;

        NumberExpression max =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:804:2: ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
            int alt78=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt78=1;
                }
                break;
            case PLUS:
                {
                alt78=2;
                }
                break;
            case QUESTION:
                {
                alt78=3;
                }
                break;
            case LBRACK:
                {
                alt78=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return quantifier;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;

            }

            switch (alt78) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:805:3: STAR (q= QUESTION )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_quantifierPart3132); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:805:10: (q= QUESTION )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==QUESTION) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:805:10: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3138); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createStarReluctantQuantifier();} 
                    	 	else{quantifier = TextMarkerScriptFactory.createStarGreedyQuantifier();}}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:808:4: PLUS (q= QUESTION )?
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3149); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:808:11: (q= QUESTION )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==QUESTION) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:808:11: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3155); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createPlusReluctantQuantifier();}
                    	 else {quantifier = TextMarkerScriptFactory.createPlusGreedyQuantifier();}}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:811:4: QUESTION (q= QUESTION )?
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3165); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:811:15: (q= QUESTION )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==QUESTION) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:811:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3171); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createQuestionReluctantQuantifier();} 
                    	 else {quantifier = TextMarkerScriptFactory.createQuestionGreedyQuantifier();}}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:4: LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3182); if (state.failed) return quantifier;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3188);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:34: (comma= COMMA (max= numberExpression )? )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COMMA) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:35: comma= COMMA (max= numberExpression )?
                            {
                            comma=(Token)match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3195); if (state.failed) return quantifier;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:49: (max= numberExpression )?
                            int alt75=2;
                            int LA75_0 = input.LA(1);

                            if ( ((LA75_0 >= EXP && LA75_0 <= TAN)||LA75_0==DecimalLiteral||LA75_0==FloatingPointLiteral||(LA75_0 >= Identifier && LA75_0 <= LPAREN)||LA75_0==MINUS) ) {
                                alt75=1;
                            }
                            switch (alt75) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:50: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3202);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return quantifier;

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3208); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:86: (q= QUESTION )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==QUESTION) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:86: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3214); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createMinMaxReluctantQuantifier(min,max,comma);} 
                    	 else {quantifier = TextMarkerScriptFactory.createMinMaxGreedyQuantifier(min,max,comma);}}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return quantifier;
    }
    // $ANTLR end "quantifierPart"



    // $ANTLR start "condition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:820:1: condition returns [AbstractTextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) ;
    public final AbstractTextMarkerCondition condition() throws RecognitionException {
        AbstractTextMarkerCondition result =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:821:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:822:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:822:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            int alt79=30;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt79=1;
                }
                break;
            case CONTAINS:
                {
                alt79=2;
                }
                break;
            case CONTEXTCOUNT:
                {
                alt79=3;
                }
                break;
            case COUNT:
                {
                alt79=4;
                }
                break;
            case CURRENTCOUNT:
                {
                alt79=5;
                }
                break;
            case INLIST:
                {
                alt79=6;
                }
                break;
            case ISINTAG:
                {
                alt79=7;
                }
                break;
            case LAST:
                {
                alt79=8;
                }
                break;
            case MOFN:
                {
                alt79=9;
                }
                break;
            case NEAR:
                {
                alt79=10;
                }
                break;
            case NOT:
            case MINUS:
                {
                alt79=11;
                }
                break;
            case OR:
                {
                alt79=12;
                }
                break;
            case PARTOF:
                {
                alt79=13;
                }
                break;
            case POSITION:
                {
                alt79=14;
                }
                break;
            case REGEXP:
                {
                alt79=15;
                }
                break;
            case SCORE:
                {
                alt79=16;
                }
                break;
            case TOTALCOUNT:
                {
                alt79=17;
                }
                break;
            case VOTE:
                {
                alt79=18;
                }
                break;
            case IF:
                {
                alt79=19;
                }
                break;
            case FEATURE:
                {
                alt79=20;
                }
                break;
            case PARSE:
                {
                alt79=21;
                }
                break;
            case IS:
                {
                alt79=22;
                }
                break;
            case BEFORE:
                {
                alt79=23;
                }
                break;
            case AFTER:
                {
                alt79=24;
                }
                break;
            case STARTSWITH:
                {
                alt79=25;
                }
                break;
            case ENDSWITH:
                {
                alt79=26;
                }
                break;
            case PARTOFNEQ:
                {
                alt79=27;
                }
                break;
            case SIZE:
                {
                alt79=28;
                }
                break;
            case Identifier:
                {
                int LA79_29 = input.LA(2);

                if ( (LA79_29==LPAREN) && (synpred15_TextMarkerParser())) {
                    alt79=29;
                }
                else if ( (LA79_29==THEN||LA79_29==RPAREN||LA79_29==RCURLY||LA79_29==COMMA) ) {
                    alt79=30;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 79, 29, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;

            }

            switch (alt79) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:823:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3245);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:824:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3254);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:825:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3263);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:826:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3272);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:827:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3281);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3290);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:829:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3299);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:830:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3308);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:831:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3317);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:832:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3326);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:833:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3335);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:834:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3344);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:835:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3353);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:836:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3362);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:837:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3371);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:838:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3380);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3389);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:840:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3398);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:841:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3407);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:842:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3416);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:843:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3425);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:844:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3434);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:845:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3443);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:846:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3452);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:847:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3462);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:848:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3471);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:849:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3480);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:850:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3489);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:851:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3508);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:852:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3517);
                    c=variableCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {result = c;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "condition"



    // $ANTLR start "variableCondition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:857:1: variableCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier ;
    public final AbstractTextMarkerCondition variableCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:858:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:860:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3547); if (state.failed) return condition;

            if ( state.backtracking==0 ) {
            		condition = ConditionFactory.createConditionVariable(id);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return condition;
    }
    // $ANTLR end "variableCondition"



    // $ANTLR start "externalCondition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:866:1: externalCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerCondition externalCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:867:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:869:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3574); if (state.failed) return condition;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3576); if (state.failed) return condition;

            pushFollow(FOLLOW_varArgumentList_in_externalCondition3582);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3584); if (state.failed) return condition;

            if ( state.backtracking==0 ) {
            		condition = external.createExternalCondition(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return condition;
    }
    // $ANTLR end "externalCondition"



    // $ANTLR start "conditionAnd"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:875:1: conditionAnd returns [AbstractTextMarkerCondition cond = null] : AND LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionAnd() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:5: ( AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:877:5: AND LPAREN conds= conditions RPAREN
            {
            match(input,AND,FOLLOW_AND_in_conditionAnd3613); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3615); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionAnd3621);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3623); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionAnd(conds, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionAnd"



    // $ANTLR start "conditionContains"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:881:1: conditionContains returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContains() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        ListExpression list =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression percent =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:5: ( CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:5: CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3675); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3677); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:21: (type= typeExpression |list= listExpression COMMA a= argument )
            int alt80=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA80_1 = input.LA(2);

                if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                    alt80=1;
                }
                else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                    alt80=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 80, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt80=1;
                }
                break;
            case LCURLY:
                {
                alt80=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;

            }

            switch (alt80) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:22: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3684);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:46: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3692);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3694); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionContains3700);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:887:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:887:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3709); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3715);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3717); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3723);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:887:64: ( COMMA percent= booleanExpression )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==COMMA) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:887:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3726); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3732);
                            percent=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3738); if (state.failed) return cond;

            if ( state.backtracking==0 ) {if(type != null) {cond = ConditionFactory.createConditionContains(type, min, max, percent,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}
                else {cond = ConditionFactory.createConditionContains(list,a, min, max, percent, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);};}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionContains"



    // $ANTLR start "conditionContextCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:891:1: conditionContextCount returns [AbstractTextMarkerCondition cond = null] : CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContextCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:892:5: ( CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:893:5: CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount3771); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount3773); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount3779);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:893:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==COMMA) ) {
                int LA83_1 = input.LA(2);

                if ( ((LA83_1 >= EXP && LA83_1 <= TAN)||LA83_1==DecimalLiteral||LA83_1==FloatingPointLiteral||LA83_1==LPAREN||LA83_1==MINUS) ) {
                    alt83=1;
                }
                else if ( (LA83_1==Identifier) ) {
                    int LA83_4 = input.LA(3);

                    if ( (LA83_4==LPAREN||LA83_4==COMMA||(LA83_4 >= PLUS && LA83_4 <= SLASH)||LA83_4==PERCENT) ) {
                        alt83=1;
                    }
                }
            }
            switch (alt83) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:893:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3782); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3788);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3790); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3796);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:894:5: ( COMMA var= numberVariable )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==COMMA) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:894:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3806); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount3812);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount3816); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionContextCount(type, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionContextCount"



    // $ANTLR start "conditionCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:897:1: conditionCount returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final AbstractTextMarkerCondition conditionCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression type =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;

        TypeExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==COUNT) ) {
                int LA89_1 = input.LA(2);

                if ( (synpred16_TextMarkerParser()) ) {
                    alt89=1;
                }
                else if ( (true) ) {
                    alt89=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 89, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;

            }
            switch (alt89) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount3862); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3864); if (state.failed) return cond;

                    pushFollow(FOLLOW_listExpression_in_conditionCount3870);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount3872); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionCount3878);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==COMMA) ) {
                        int LA85_1 = input.LA(2);

                        if ( ((LA85_1 >= EXP && LA85_1 <= TAN)||LA85_1==DecimalLiteral||LA85_1==FloatingPointLiteral||LA85_1==LPAREN||LA85_1==MINUS) ) {
                            alt85=1;
                        }
                        else if ( (LA85_1==Identifier) ) {
                            int LA85_4 = input.LA(3);

                            if ( (LA85_4==LPAREN||LA85_4==COMMA||(LA85_4 >= PLUS && LA85_4 <= SLASH)||LA85_4==PERCENT) ) {
                                alt85=1;
                            }
                        }
                    }
                    switch (alt85) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:60: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3881); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount3887);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3889); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount3895);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:903:5: ( COMMA var= numberVariable )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==COMMA) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:903:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3905); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount3911);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount3915); if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionCount(type, a, min, max, var,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:906:5: COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount3933); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3935); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount3941);
                    list=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:906:40: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COMMA) ) {
                        int LA87_1 = input.LA(2);

                        if ( ((LA87_1 >= EXP && LA87_1 <= TAN)||LA87_1==DecimalLiteral||LA87_1==FloatingPointLiteral||LA87_1==LPAREN||LA87_1==MINUS) ) {
                            alt87=1;
                        }
                        else if ( (LA87_1==Identifier) ) {
                            int LA87_4 = input.LA(3);

                            if ( (LA87_4==LPAREN||LA87_4==COMMA||(LA87_4 >= PLUS && LA87_4 <= SLASH)||LA87_4==PERCENT) ) {
                                alt87=1;
                            }
                        }
                    }
                    switch (alt87) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:906:41: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3944); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount3950);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3952); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount3958);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:907:5: ( COMMA var= numberVariable )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==COMMA) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:907:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3968); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount3974);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount3978); if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionCount(list, min, max, var,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionCount"



    // $ANTLR start "conditionTotalCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:1: conditionTotalCount returns [AbstractTextMarkerCondition cond = null] : TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionTotalCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:911:5: ( TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:5: TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4014); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4016); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4022);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:45: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==COMMA) ) {
                int LA90_1 = input.LA(2);

                if ( ((LA90_1 >= EXP && LA90_1 <= TAN)||LA90_1==DecimalLiteral||LA90_1==FloatingPointLiteral||LA90_1==LPAREN||LA90_1==MINUS) ) {
                    alt90=1;
                }
                else if ( (LA90_1==Identifier) ) {
                    int LA90_4 = input.LA(3);

                    if ( (LA90_4==LPAREN||LA90_4==COMMA||(LA90_4 >= PLUS && LA90_4 <= SLASH)||LA90_4==PERCENT) ) {
                        alt90=1;
                    }
                }
            }
            switch (alt90) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:46: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4025); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4031);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4033); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4039);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:913:5: ( COMMA var= numberVariable )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:913:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4048); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4054);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4058); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionTotalCount(type, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionTotalCount"



    // $ANTLR start "conditionCurrentCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:1: conditionCurrentCount returns [AbstractTextMarkerCondition cond = null] : CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionCurrentCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:917:5: ( CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:918:5: CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4091); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4093); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4099);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:918:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==COMMA) ) {
                int LA92_1 = input.LA(2);

                if ( ((LA92_1 >= EXP && LA92_1 <= TAN)||LA92_1==DecimalLiteral||LA92_1==FloatingPointLiteral||LA92_1==LPAREN||LA92_1==MINUS) ) {
                    alt92=1;
                }
                else if ( (LA92_1==Identifier) ) {
                    int LA92_4 = input.LA(3);

                    if ( (LA92_4==LPAREN||LA92_4==COMMA||(LA92_4 >= PLUS && LA92_4 <= SLASH)||LA92_4==PERCENT) ) {
                        alt92=1;
                    }
                }
            }
            switch (alt92) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:918:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4102); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4108);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4110); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4116);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:919:5: ( COMMA var= numberVariable )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==COMMA) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:919:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4126); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4132);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4136); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionCurrentCount(type, min, max, var,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionCurrentCount"



    // $ANTLR start "conditionInList"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:922:1: conditionInList returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionInList() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringListExpression list2 =null;

        WordListExpression list1 =null;

        NumberExpression dist =null;

        BooleanExpression rel =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:926:5: ( INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:5: INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            match(input,INLIST,FOLLOW_INLIST_in_conditionInList4179); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4181); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:19: ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==LCURLY) && (synpred17_TextMarkerParser())) {
                alt94=1;
            }
            else if ( (LA94_0==Identifier) ) {
                int LA94_2 = input.LA(2);

                if ( (((synpred17_TextMarkerParser()&&synpred17_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt94=1;
                }
                else if ( (true) ) {
                    alt94=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 94, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA94_0==RessourceLiteral) ) {
                alt94=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;

            }
            switch (alt94) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:20: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4196);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:83: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4204);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:111: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==COMMA) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:112: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4208); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4214);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:142: ( COMMA rel= booleanExpression )?
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( (LA95_0==COMMA) ) {
                        alt95=1;
                    }
                    switch (alt95) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:143: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4217); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4223);
                            rel=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4229); if (state.failed) return cond;

            if ( state.backtracking==0 ) {if(list1 != null) {cond = ConditionFactory.createConditionInList(list1, dist, rel,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}
                else {cond = ConditionFactory.createConditionInList(list2, dist, rel,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);};}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionInList"



    // $ANTLR start "conditionIsInTag"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:931:1: conditionIsInTag returns [AbstractTextMarkerCondition cond = null] : ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final AbstractTextMarkerCondition conditionIsInTag() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression id =null;

        StringExpression id1 =null;

        StringExpression id2 =null;



        List<StringExpression> list1 = new ArrayList<StringExpression>();
        List<StringExpression> list2 = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:936:5: ( ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:5: ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4264); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4266); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4272);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:42: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==COMMA) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:43: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4275); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4281);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4283); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4289);
            	    id2=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    if ( state.backtracking==0 ) {list1.add(id1);list2.add(id2);}

            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4295); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionIsInTag(id, list1, list2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionIsInTag"



    // $ANTLR start "conditionLast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:1: conditionLast returns [AbstractTextMarkerCondition cond = null] : LAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionLast() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:942:5: ( LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:943:5: LAST LPAREN type= typeExpression RPAREN
            {
            match(input,LAST,FOLLOW_LAST_in_conditionLast4334); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4336); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionLast4342);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4344); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionLast(type, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionLast"



    // $ANTLR start "conditionMofN"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:948:1: conditionMofN returns [AbstractTextMarkerCondition cond = null] : MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionMofN() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:949:5: ( MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:950:5: MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4391); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4393); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4399);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4401); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4407);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4409); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionMofN4415);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4417); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionMOfN(conds, min, max, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionMofN"



    // $ANTLR start "conditionNear"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:954:1: conditionNear returns [AbstractTextMarkerCondition cond = null] : NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionNear() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression direction =null;

        BooleanExpression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:955:5: ( NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:5: NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            match(input,NEAR,FOLLOW_NEAR_in_conditionNear4452); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4454); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionNear4460);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4462); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4468);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4470); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4476);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:97: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==COMMA) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:98: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4479); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4485);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:134: ( COMMA filtered= booleanExpression )?
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==COMMA) ) {
                        alt98=1;
                    }
                    switch (alt98) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:135: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4488); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4494);
                            filtered=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4500); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionNear(type, min, max, direction, filtered, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionNear"



    // $ANTLR start "conditionNot"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:959:1: conditionNot returns [AbstractTextMarkerCondition cond = null] : ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) ;
    public final AbstractTextMarkerCondition conditionNot() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:960:5: ( ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==MINUS) ) {
                alt100=1;
            }
            else if ( (LA100_0==NOT) ) {
                alt100=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;

            }
            switch (alt100) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:6: ( MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:6: ( MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:7: MINUS c= condition
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_conditionNot4536); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4542);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:31: ( NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:31: ( NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:32: NOT LPAREN c= condition RPAREN
                    {
                    match(input,NOT,FOLLOW_NOT_in_conditionNot4549); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4551); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4557);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4559); if (state.failed) return cond;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionNot(c, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionNot"



    // $ANTLR start "conditionOr"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:964:1: conditionOr returns [AbstractTextMarkerCondition cond = null] : OR LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionOr() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:965:5: ( OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:966:5: OR LPAREN conds= conditions RPAREN
            {
            match(input,OR,FOLLOW_OR_in_conditionOr4598); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4600); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionOr4606);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4608); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionOr(conds, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionOr"



    // $ANTLR start "conditionPartOf"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:969:1: conditionPartOf returns [AbstractTextMarkerCondition cond = null] : PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:5: ( PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:971:5: PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4638); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4640); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:971:19: (type1= typeExpression |type2= typeListExpression )
            int alt101=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA101_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt101=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt101=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt101=1;
                }
                break;
            case LCURLY:
                {
                alt101=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;

            }

            switch (alt101) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:971:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4647);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:971:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4653);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4656); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionPartOf(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionPartOf"



    // $ANTLR start "conditionPartOfNeq"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:974:1: conditionPartOfNeq returns [AbstractTextMarkerCondition cond = null] : PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:5: ( PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:5: PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4686); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4688); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:22: (type1= typeExpression |type2= typeListExpression )
            int alt102=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA102_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt102=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt102=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 102, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt102=1;
                }
                break;
            case LCURLY:
                {
                alt102=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;

            }

            switch (alt102) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:23: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq4695);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:46: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq4701);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq4704); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionPartOfNeq(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionPartOfNeq"



    // $ANTLR start "conditionPosition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:980:1: conditionPosition returns [AbstractTextMarkerCondition cond = null] : POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionPosition() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:981:5: ( POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:982:5: POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            match(input,POSITION,FOLLOW_POSITION_in_conditionPosition4738); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition4740); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionPosition4746);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition4748); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionPosition4754);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition4756); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionPosition(type, pos, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionPosition"



    // $ANTLR start "conditionRegExp"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:985:1: conditionRegExp returns [AbstractTextMarkerCondition cond = null] : REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final AbstractTextMarkerCondition conditionRegExp() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression pattern =null;

        BooleanExpression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:5: ( REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:987:5: REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp4786); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp4788); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp4794);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:987:46: ( COMMA caseSensitive= booleanExpression )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==COMMA) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:987:47: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp4797); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp4803);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp4807); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionRegExp(pattern, caseSensitive, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionRegExp"



    // $ANTLR start "conditionScore"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:991:1: conditionScore returns [AbstractTextMarkerCondition cond = null] : SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionScore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:992:5: ( SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:993:5: SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            match(input,SCORE,FOLLOW_SCORE_in_conditionScore4842); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore4844); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore4850);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:993:41: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==COMMA) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:993:42: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore4853); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore4859);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:994:5: ( COMMA var= numberVariable )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==COMMA) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:994:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore4866); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionScore4872);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore4879); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionScore(min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionScore"



    // $ANTLR start "conditionVote"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:999:1: conditionVote returns [AbstractTextMarkerCondition cond = null] : VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionVote() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1000:5: ( VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:5: VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            match(input,VOTE,FOLLOW_VOTE_in_conditionVote4914); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote4916); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote4922);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionVote4924); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote4930);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote4932); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionVote(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionVote"



    // $ANTLR start "conditionIf"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1005:1: conditionIf returns [AbstractTextMarkerCondition cond = null] : IF LPAREN e= booleanExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionIf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1006:5: ( IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1007:5: IF LPAREN e= booleanExpression RPAREN
            {
            match(input,IF,FOLLOW_IF_in_conditionIf4970); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf4972); if (state.failed) return cond;

            pushFollow(FOLLOW_booleanExpression_in_conditionIf4978);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf4980); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionIf(e, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionIf"



    // $ANTLR start "conditionFeature"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1011:1: conditionFeature returns [AbstractTextMarkerCondition cond = null] : FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerCondition conditionFeature() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression se =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1012:5: ( FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1013:5: FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5014); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5016); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionFeature5022);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5024); if (state.failed) return cond;

            pushFollow(FOLLOW_argument_in_conditionFeature5030);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5032); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionFeature(se, v, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionFeature"



    // $ANTLR start "conditionParse"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1017:1: conditionParse returns [AbstractTextMarkerCondition cond = null] : PARSE LPAREN {...}?id= Identifier RPAREN ;
    public final AbstractTextMarkerCondition conditionParse() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1018:5: ( PARSE LPAREN {...}?id= Identifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1019:5: PARSE LPAREN {...}?id= Identifier RPAREN
            {
            match(input,PARSE,FOLLOW_PARSE_in_conditionParse5066); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5068); if (state.failed) return cond;

            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return cond;}
                throw new FailedPredicateException(input, "conditionParse", "isVariable($blockDeclaration::env,input.LT(1).getText())");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionParse5076); if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5078); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionParse(id, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionParse"



    // $ANTLR start "conditionIs"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1023:1: conditionIs returns [AbstractTextMarkerCondition cond = null] : IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionIs() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1024:5: ( IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:5: IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,IS,FOLLOW_IS_in_conditionIs5109); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5111); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:15: (type1= typeExpression |type2= typeListExpression )
            int alt106=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA106_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt106=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt106=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 106, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt106=1;
                }
                break;
            case LCURLY:
                {
                alt106=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 106, 0, input);

                throw nvae;

            }

            switch (alt106) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:16: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5118);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:39: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5124);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5127); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionIs(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionIs"



    // $ANTLR start "conditionBefore"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1029:1: conditionBefore returns [AbstractTextMarkerCondition cond = null] : BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionBefore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1030:5: ( BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:5: BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5158); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5160); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:19: (type1= typeExpression |type2= typeListExpression )
            int alt107=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA107_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt107=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt107=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 107, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt107=1;
                }
                break;
            case LCURLY:
                {
                alt107=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;

            }

            switch (alt107) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5167);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5173);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5176); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionBefore(type1,type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionBefore"



    // $ANTLR start "conditionAfter"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1035:1: conditionAfter returns [AbstractTextMarkerCondition cond = null] : AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionAfter() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1036:5: ( AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:5: AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5207); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5209); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:18: (type1= typeExpression |type2= typeListExpression )
            int alt108=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA108_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt108=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt108=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 108, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt108=1;
                }
                break;
            case LCURLY:
                {
                alt108=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 0, input);

                throw nvae;

            }

            switch (alt108) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:19: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5216);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:42: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5222);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5225); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionAfter(type1,type2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionAfter"



    // $ANTLR start "conditionStartsWith"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1041:1: conditionStartsWith returns [AbstractTextMarkerCondition cond = null] : STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionStartsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1042:5: ( STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:5: STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5256); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5258); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:23: (type1= typeExpression |type2= typeListExpression )
            int alt109=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA109_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt109=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt109=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 109, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt109=1;
                }
                break;
            case LCURLY:
                {
                alt109=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                throw nvae;

            }

            switch (alt109) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:24: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5265);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:47: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5271);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5274); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionStartsWith(type1,type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionStartsWith"



    // $ANTLR start "conditionEndsWith"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1047:1: conditionEndsWith returns [AbstractTextMarkerCondition cond = null] : ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionEndsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1048:5: ( ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:5: ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5305); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5307); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:21: (type1= typeExpression |type2= typeListExpression )
            int alt110=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA110_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt110=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt110=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 110, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt110=1;
                }
                break;
            case LCURLY:
                {
                alt110=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;

            }

            switch (alt110) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:22: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5314);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:45: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5320);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5323); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionEndsWith(type1,type2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionEndsWith"



    // $ANTLR start "conditionSize"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1053:1: conditionSize returns [AbstractTextMarkerCondition cond = null] : SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionSize() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression list =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1054:5: ( SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:5: SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,SIZE,FOLLOW_SIZE_in_conditionSize5354); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5356); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize5362);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:39: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==COMMA) ) {
                int LA111_1 = input.LA(2);

                if ( ((LA111_1 >= EXP && LA111_1 <= TAN)||LA111_1==DecimalLiteral||LA111_1==FloatingPointLiteral||LA111_1==LPAREN||LA111_1==MINUS) ) {
                    alt111=1;
                }
                else if ( (LA111_1==Identifier) ) {
                    int LA111_4 = input.LA(3);

                    if ( (LA111_4==LPAREN||LA111_4==COMMA||(LA111_4 >= PLUS && LA111_4 <= SLASH)||LA111_4==PERCENT) ) {
                        alt111=1;
                    }
                }
            }
            switch (alt111) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:40: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5365); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5371);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5373); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5379);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:100: ( COMMA var= numberVariable )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==COMMA) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:101: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5384); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionSize5390);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5394); if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionSize(list, min, max, var,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionSize"



    // $ANTLR start "action"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1059:1: action returns [AbstractTextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction ) ;
    public final AbstractTextMarkerAction action() throws RecognitionException {
        AbstractTextMarkerAction result =  null;


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1060:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1061:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1061:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction )
            int alt113=37;
            switch ( input.LA(1) ) {
            case COLOR:
                {
                alt113=1;
                }
                break;
            case DEL:
                {
                alt113=2;
                }
                break;
            case LOG:
                {
                alt113=3;
                }
                break;
            case MARK:
                {
                alt113=4;
                }
                break;
            case MARKSCORE:
                {
                alt113=5;
                }
                break;
            case MARKFAST:
                {
                alt113=6;
                }
                break;
            case MARKLAST:
                {
                alt113=7;
                }
                break;
            case REPLACE:
                {
                alt113=8;
                }
                break;
            case FILTERMARKUP:
                {
                alt113=9;
                }
                break;
            case FILTERTYPE:
                {
                alt113=10;
                }
                break;
            case RETAINMARKUP:
                {
                alt113=11;
                }
                break;
            case RETAINTYPE:
                {
                alt113=12;
                }
                break;
            case CREATE:
                {
                alt113=13;
                }
                break;
            case FILL:
                {
                alt113=14;
                }
                break;
            case CALL:
                {
                alt113=15;
                }
                break;
            case ASSIGN:
                {
                alt113=16;
                }
                break;
            case SETFEATURE:
                {
                alt113=17;
                }
                break;
            case GETFEATURE:
                {
                alt113=18;
                }
                break;
            case UNMARK:
                {
                alt113=19;
                }
                break;
            case UNMARKALL:
                {
                alt113=20;
                }
                break;
            case TRANSFER:
                {
                alt113=21;
                }
                break;
            case MARKONCE:
                {
                alt113=22;
                }
                break;
            case TRIE:
                {
                alt113=23;
                }
                break;
            case GATHER:
                {
                alt113=24;
                }
                break;
            case EXEC:
                {
                alt113=25;
                }
                break;
            case MARKTABLE:
                {
                alt113=26;
                }
                break;
            case ADD:
                {
                alt113=27;
                }
                break;
            case REMOVE:
                {
                alt113=28;
                }
                break;
            case REMOVEDUPLICATE:
                {
                alt113=29;
                }
                break;
            case MERGE:
                {
                alt113=30;
                }
                break;
            case GET:
                {
                alt113=31;
                }
                break;
            case GETLIST:
                {
                alt113=32;
                }
                break;
            case MATCHEDTEXT:
                {
                alt113=33;
                }
                break;
            case CLEAR:
                {
                alt113=34;
                }
                break;
            case EXPAND:
                {
                alt113=35;
                }
                break;
            case Identifier:
                {
                int LA113_36 = input.LA(2);

                if ( (LA113_36==LPAREN) && (synpred18_TextMarkerParser())) {
                    alt113=36;
                }
                else if ( (LA113_36==RPAREN||LA113_36==RCURLY||LA113_36==COMMA) ) {
                    alt113=37;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 113, 36, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 113, 0, input);

                throw nvae;

            }

            switch (alt113) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5427);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1063:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5436);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1064:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5445);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1065:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5454);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1066:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5463);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1067:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5472);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5481);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5490);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1070:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5499);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1071:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5508);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1072:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5517);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1073:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5526);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5535);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1075:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action5544);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1076:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action5553);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1077:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action5562);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1078:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action5571);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1079:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action5580);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action5589);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1081:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action5598);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1082:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action5607);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1083:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action5616);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1084:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action5625);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1085:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action5634);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1086:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action5643);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1087:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action5652);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1088:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action5661);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1089:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action5670);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1090:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action5679);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1091:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action5688);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1092:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action5697);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1093:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action5706);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1094:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action5715);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1095:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action5724);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1096:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action5733);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1097:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action5751);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1098:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action5760);
                    a=variableAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {result = a;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "action"



    // $ANTLR start "variableAction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1102:1: variableAction returns [AbstractTextMarkerAction action = null] : id= Identifier ;
    public final AbstractTextMarkerAction variableAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1103:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1105:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction5789); if (state.failed) return action;

            if ( state.backtracking==0 ) {
            		action = ActionFactory.createActionVariable(id);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "variableAction"



    // $ANTLR start "externalAction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1112:1: externalAction returns [AbstractTextMarkerAction action = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerAction externalAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1113:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1115:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction5817); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction5819); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction5825);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction5827); if (state.failed) return action;

            if ( state.backtracking==0 ) {
            		action = external.createExternalAction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "externalAction"



    // $ANTLR start "actionCreate"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1123:1: actionCreate returns [AbstractTextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
    public final AbstractTextMarkerAction actionCreate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression structure =null;

        NumberExpression index =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
            	List<NumberExpression> indexes = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1128:5: (name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1129:5: name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate5863); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate5865); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate5871);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1130:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==COMMA) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1130:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5878); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1131:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==MINUS) && (synpred19_TextMarkerParser())) {
                        alt115=1;
                    }
                    else if ( (LA115_0==DecimalLiteral) && (synpred19_TextMarkerParser())) {
                        alt115=1;
                    }
                    else if ( (LA115_0==FloatingPointLiteral) && (synpred19_TextMarkerParser())) {
                        alt115=1;
                    }
                    else if ( (LA115_0==Identifier) ) {
                        int LA115_4 = input.LA(2);

                        if ( (synpred19_TextMarkerParser()) ) {
                            alt115=1;
                        }
                    }
                    else if ( (LA115_0==LPAREN) && (synpred19_TextMarkerParser())) {
                        alt115=1;
                    }
                    else if ( ((LA115_0 >= EXP && LA115_0 <= TAN)) && (synpred19_TextMarkerParser())) {
                        alt115=1;
                    }
                    switch (alt115) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:5: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate5903);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:80: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop114:
                            do {
                                int alt114=2;
                                int LA114_0 = input.LA(1);

                                if ( (LA114_0==COMMA) ) {
                                    int LA114_1 = input.LA(2);

                                    if ( (synpred20_TextMarkerParser()) ) {
                                        alt114=1;
                                    }


                                }


                                switch (alt114) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:81: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5920); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate5926);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop114;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate5933); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1133:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( (LA117_0==REMOVESTRING||LA117_0==StringLiteral||LA117_0==Identifier) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1133:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate5946);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate5948); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate5954);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {map.put(fname,obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1134:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop116:
                            do {
                                int alt116=2;
                                int LA116_0 = input.LA(1);

                                if ( (LA116_0==COMMA) ) {
                                    alt116=1;
                                }


                                switch (alt116) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1134:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5964); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate5970);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate5972); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate5978);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                            	    }
                            	    break;

                            	default :
                            	    break loop116;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate5993); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createCreateAction(structure, map, indexes, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionCreate"



    // $ANTLR start "actionMarkTable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1140:1: actionMarkTable returns [AbstractTextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkTable() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression structure =null;

        NumberExpression index =null;

        WordTableExpression table =null;

        StringExpression fname =null;

        NumberExpression obj1 =null;



        	Map<StringExpression, NumberExpression> map = new HashMap<StringExpression, NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1144:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1145:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6034); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6036); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6047);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6049); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6060);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6062); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6072);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==COMMA) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6080); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6094);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6096); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6102);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==COMMA) ) {
                            alt119=1;
                        }


                        switch (alt119) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6112); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6118);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6120); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6126);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop119;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6139); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMarkTableAction(structure, index, table, map,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkTable"



    // $ANTLR start "actionGather"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1156:1: actionGather returns [AbstractTextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
    public final AbstractTextMarkerAction actionGather() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression structure =null;

        NumberExpression index =null;

        StringExpression fname =null;

        NumberExpression obj1 =null;

        NumberListExpression obj2 =null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
            	List<NumberExpression> indexes = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1161:5: (name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1162:5: name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6180); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6182); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6188);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1163:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==COMMA) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1163:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6195); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==MINUS) && (synpred21_TextMarkerParser())) {
                        alt122=1;
                    }
                    else if ( (LA122_0==DecimalLiteral) && (synpred21_TextMarkerParser())) {
                        alt122=1;
                    }
                    else if ( (LA122_0==FloatingPointLiteral) && (synpred21_TextMarkerParser())) {
                        alt122=1;
                    }
                    else if ( (LA122_0==Identifier) ) {
                        int LA122_4 = input.LA(2);

                        if ( (synpred21_TextMarkerParser()) ) {
                            alt122=1;
                        }
                    }
                    else if ( (LA122_0==LPAREN) && (synpred21_TextMarkerParser())) {
                        alt122=1;
                    }
                    else if ( ((LA122_0 >= EXP && LA122_0 <= TAN)) && (synpred21_TextMarkerParser())) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:6: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6215);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:81: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop121:
                            do {
                                int alt121=2;
                                int LA121_0 = input.LA(1);

                                if ( (LA121_0==COMMA) ) {
                                    int LA121_1 = input.LA(2);

                                    if ( (synpred22_TextMarkerParser()) ) {
                                        alt121=1;
                                    }


                                }


                                switch (alt121) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:82: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6231); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6237);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop121;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6244); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==REMOVESTRING||LA126_0==StringLiteral||LA126_0==Identifier) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6257);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6259); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:44: (obj1= numberExpression |obj2= numberListExpression )
                            int alt123=2;
                            switch ( input.LA(1) ) {
                            case EXP:
                            case LOGN:
                            case SIN:
                            case COS:
                            case TAN:
                            case DecimalLiteral:
                            case FloatingPointLiteral:
                            case LPAREN:
                            case MINUS:
                                {
                                alt123=1;
                                }
                                break;
                            case Identifier:
                                {
                                int LA123_2 = input.LA(2);

                                if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                                    alt123=1;
                                }
                                else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                                    alt123=2;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return action;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 123, 2, input);

                                    throw nvae;

                                }
                                }
                                break;
                            case LCURLY:
                                {
                                alt123=2;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return action;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 123, 0, input);

                                throw nvae;

                            }

                            switch (alt123) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6266);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6274);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {map.put(fname,obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1166:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop125:
                            do {
                                int alt125=2;
                                int LA125_0 = input.LA(1);

                                if ( (LA125_0==COMMA) ) {
                                    alt125=1;
                                }


                                switch (alt125) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1166:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6285); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6291);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6293); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1166:50: (obj1= numberExpression |obj2= numberListExpression )
                            	    int alt124=2;
                            	    switch ( input.LA(1) ) {
                            	    case EXP:
                            	    case LOGN:
                            	    case SIN:
                            	    case COS:
                            	    case TAN:
                            	    case DecimalLiteral:
                            	    case FloatingPointLiteral:
                            	    case LPAREN:
                            	    case MINUS:
                            	        {
                            	        alt124=1;
                            	        }
                            	        break;
                            	    case Identifier:
                            	        {
                            	        int LA124_2 = input.LA(2);

                            	        if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                            	            alt124=1;
                            	        }
                            	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                            	            alt124=2;
                            	        }
                            	        else {
                            	            if (state.backtracking>0) {state.failed=true; return action;}
                            	            NoViableAltException nvae =
                            	                new NoViableAltException("", 124, 2, input);

                            	            throw nvae;

                            	        }
                            	        }
                            	        break;
                            	    case LCURLY:
                            	        {
                            	        alt124=2;
                            	        }
                            	        break;
                            	    default:
                            	        if (state.backtracking>0) {state.failed=true; return action;}
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 124, 0, input);

                            	        throw nvae;

                            	    }

                            	    switch (alt124) {
                            	        case 1 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1166:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6300);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1166:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6308);
                            	            obj2=numberListExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;

                            	    }


                            	    if ( state.backtracking==0 ) {map.put(fname,obj1 != null? obj1 : obj2);}

                            	    }
                            	    break;

                            	default :
                            	    break loop125;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6324); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createGatherAction(structure, map, indexes, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGather"



    // $ANTLR start "actionFill"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1173:1: actionFill returns [AbstractTextMarkerAction action = null] : FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN ;
    public final AbstractTextMarkerAction actionFill() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1177:5: ( FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1178:5: FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN
            {
            match(input,FILL,FOLLOW_FILL_in_actionFill6366); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6368); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill6374);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1178:39: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+
            int cnt128=0;
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==COMMA) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1178:40: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6377); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill6383);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6385); if (state.failed) return action;

            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1179:5: (obj1= argument )
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:5: obj1= argument
            	    {
            	    pushFollow(FOLLOW_argument_in_actionFill6402);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt128 >= 1 ) break loop128;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(128, input);
                        throw eee;
                }
                cnt128++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6419); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createFillAction(type, map, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionFill"



    // $ANTLR start "actionColor"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1186:1: actionColor returns [AbstractTextMarkerAction action = null] : COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionColor() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression bgcolor =null;

        StringExpression fgcolor =null;

        BooleanExpression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1187:5: ( COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1188:5: COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            match(input,COLOR,FOLLOW_COLOR_in_actionColor6457); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6459); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor6465);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionColor6477); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor6488);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==COMMA) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6496); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor6506);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1194:5: ( COMMA selected= booleanExpression )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==COMMA) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1194:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor6514); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor6524);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor6534); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createColorAction(type, bgcolor, fgcolor, selected, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionColor"



    // $ANTLR start "actionDel"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1200:1: actionDel returns [AbstractTextMarkerAction action = null] : DEL ;
    public final AbstractTextMarkerAction actionDel() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1201:5: ( DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1202:5: DEL
            {
            match(input,DEL,FOLLOW_DEL_in_actionDel6568); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createDelAction(((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionDel"



    // $ANTLR start "actionLog"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1206:1: actionLog returns [AbstractTextMarkerAction action = null] : LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final AbstractTextMarkerAction actionLog() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token log=null;
        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1207:5: ( LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1208:5: LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            match(input,LOG,FOLLOW_LOG_in_actionLog6610); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog6612); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog6618);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1208:39: ( COMMA log= LogLevel )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==COMMA) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1208:40: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog6621); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog6627); if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog6631); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createLogAction(lit, log, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionLog"



    // $ANTLR start "actionMark"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1212:1: actionMark returns [AbstractTextMarkerAction action = null] : MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1216:5: ( MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1217:5: MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARK,FOLLOW_MARK_in_actionMark6670); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark6672); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark6683);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1219:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==COMMA) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1220:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark6699); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark6715);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop132;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark6739); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMarkAction(null, type, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMark"



    // $ANTLR start "actionExpand"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1228:1: actionExpand returns [AbstractTextMarkerAction action = null] : EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionExpand() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1232:5: ( EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1233:5: EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand6783); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand6785); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand6796);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1235:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop133:
            do {
                int alt133=2;
                int LA133_0 = input.LA(1);

                if ( (LA133_0==COMMA) ) {
                    alt133=1;
                }


                switch (alt133) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1236:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand6812); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand6828);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop133;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand6852); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createExpandAction(type, list,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionExpand"



    // $ANTLR start "actionMarkScore"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1245:1: actionMarkScore returns [AbstractTextMarkerAction action = null] : MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkScore() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1249:5: ( MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1250:5: MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore6897); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore6899); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore6910);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore6912); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore6922);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1253:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==COMMA) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1254:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore6938); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore6954);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore6978); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMarkAction(score, type, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkScore"



    // $ANTLR start "actionMarkOnce"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1262:1: actionMarkOnce returns [AbstractTextMarkerAction action = null] : MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkOnce() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1266:5: ( MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:5: MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7022); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7024); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:21: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==MINUS) && (synpred26_TextMarkerParser())) {
                alt135=1;
            }
            else if ( (LA135_0==DecimalLiteral) && (synpred26_TextMarkerParser())) {
                alt135=1;
            }
            else if ( (LA135_0==FloatingPointLiteral) && (synpred26_TextMarkerParser())) {
                alt135=1;
            }
            else if ( (LA135_0==Identifier) ) {
                int LA135_4 = input.LA(2);

                if ( (synpred26_TextMarkerParser()) ) {
                    alt135=1;
                }
            }
            else if ( (LA135_0==LPAREN) && (synpred26_TextMarkerParser())) {
                alt135=1;
            }
            else if ( ((LA135_0 >= EXP && LA135_0 <= TAN)) && (synpred26_TextMarkerParser())) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:22: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7041);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7043); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7061);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1268:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==COMMA) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1269:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7077); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7093);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop136;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7112); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMarkOnceAction(score, type, list,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkOnce"



    // $ANTLR start "actionMarkFast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1276:1: actionMarkFast returns [AbstractTextMarkerAction action = null] : MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkFast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        WordListExpression list =null;

        BooleanExpression ignore =null;

        NumberExpression ignoreLength =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1277:5: ( MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1278:5: MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN
            {
            match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7151); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7153); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7159);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7161); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7167);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1278:75: ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==COMMA) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1278:76: COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7170); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7176);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1278:109: ( COMMA ignoreLength= numberExpression )?
                    int alt137=2;
                    int LA137_0 = input.LA(1);

                    if ( (LA137_0==COMMA) ) {
                        alt137=1;
                    }
                    switch (alt137) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1278:110: COMMA ignoreLength= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7179); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7185);
                            ignoreLength=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7191); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMarkFastAction(type, list, ignore, ignoreLength, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkFast"



    // $ANTLR start "actionMarkLast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1282:1: actionMarkLast returns [AbstractTextMarkerAction action = null] : MARKLAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionMarkLast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:5: ( MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:5: MARKLAST LPAREN type= typeExpression RPAREN
            {
            match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7225); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7227); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7233);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7235); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMarkLastAction(type, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkLast"



    // $ANTLR start "actionReplace"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1288:1: actionReplace returns [AbstractTextMarkerAction action = null] : REPLACE LPAREN lit= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionReplace() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1289:5: ( REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1290:5: REPLACE LPAREN lit= stringExpression RPAREN
            {
            match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7269); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7271); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace7277);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7279); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createReplaceAction(lit, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionReplace"



    // $ANTLR start "actionRetainMarkup"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:1: actionRetainMarkup returns [AbstractTextMarkerAction action = null] : RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1298:5: ( RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1299:5: RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7322); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1299:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==LPAREN) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1299:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7325); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7331);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1299:64: ( COMMA id= stringExpression )*
                    loop139:
                    do {
                        int alt139=2;
                        int LA139_0 = input.LA(1);

                        if ( (LA139_0==COMMA) ) {
                            alt139=1;
                        }


                        switch (alt139) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1299:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7336); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7342);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop139;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7348); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createRetainMarkupAction(list,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRetainMarkup"



    // $ANTLR start "actionRetainType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1304:1: actionRetainType returns [AbstractTextMarkerAction action = null] : RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1308:5: ( RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1309:5: RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7394); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1309:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==LPAREN) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1309:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7397); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7403);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1309:60: ( COMMA id= typeExpression )*
                    loop141:
                    do {
                        int alt141=2;
                        int LA141_0 = input.LA(1);

                        if ( (LA141_0==COMMA) ) {
                            alt141=1;
                        }


                        switch (alt141) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1309:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7408); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7414);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop141;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7420); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createRetainTypeAction(list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRetainType"



    // $ANTLR start "actionFilterMarkup"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1313:1: actionFilterMarkup returns [AbstractTextMarkerAction action = null] : FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1317:5: ( FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1318:5: FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup7468); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1318:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==LPAREN) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1318:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup7471); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7477);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1318:64: ( COMMA id= stringExpression )*
                    loop143:
                    do {
                        int alt143=2;
                        int LA143_0 = input.LA(1);

                        if ( (LA143_0==COMMA) ) {
                            alt143=1;
                        }


                        switch (alt143) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1318:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup7482); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7488);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop143;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup7494); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createFilterMarkupAction(list,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionFilterMarkup"



    // $ANTLR start "actionFilterType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1323:1: actionFilterType returns [AbstractTextMarkerAction action = null] : FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1327:5: ( FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1328:5: FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType7540); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1328:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==LPAREN) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1328:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType7543); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType7549);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1328:60: ( COMMA id= typeExpression )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==COMMA) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1328:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType7554); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType7560);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop145;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType7566); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createFilterTypeAction(list,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionFilterType"



    // $ANTLR start "actionCall"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1332:1: actionCall returns [AbstractTextMarkerAction action = null] : CALL LPAREN ns= dottedIdentifier RPAREN ;
    public final AbstractTextMarkerAction actionCall() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1333:5: ( CALL LPAREN ns= dottedIdentifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1334:5: CALL LPAREN ns= dottedIdentifier RPAREN
            {
            match(input,CALL,FOLLOW_CALL_in_actionCall7606); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCall7608); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionCall7614);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall7616); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createCallAction(ns, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionCall"



    // $ANTLR start "actionExec"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1338:1: actionExec returns [AbstractTextMarkerAction action = null] : EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionExec() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1339:5: ( EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1340:5: EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN
            {
            match(input,EXEC,FOLLOW_EXEC_in_actionExec7647); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExec7649); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionExec7655);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1340:39: ( COMMA tl= typeListExpression )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==COMMA) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1340:40: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec7658); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec7664);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec7668); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createExecAction(ns, tl, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionExec"



    // $ANTLR start "actionAssign"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:1: actionAssign returns [AbstractTextMarkerAction action = null] : name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN ;
    public final AbstractTextMarkerAction actionAssign() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token nv=null;
        TypeExpression e1 =null;

        BooleanExpression e2 =null;

        StringExpression e3 =null;

        NumberExpression e4 =null;

        NumberExpression e5 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:5: (name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1346:5: name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign7711); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign7713); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1347:5: ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression )
            int alt148=5;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==Identifier) ) {
                int LA148_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                    alt148=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                    alt148=2;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                    alt148=3;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                    alt148=4;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                    alt148=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return action;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 148, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return action;}
                NoViableAltException nvae =
                    new NoViableAltException("", 148, 0, input);

                throw nvae;

            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1348:5: {...}?nv= Identifier COMMA e1= typeExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7740); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7742); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionAssign7748);
                    e1=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e1,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1352:5: {...}?nv= Identifier COMMA e2= booleanExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7786); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7788); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionAssign7794);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1356:5: {...}?nv= Identifier COMMA e3= stringExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRING\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7832); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7834); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionAssign7840);
                    e3=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e3,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1360:5: {...}?nv= Identifier COMMA e4= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INT\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7878); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7880); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign7886);
                    e4=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e4,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1364:5: {...}?nv= Identifier COMMA e5= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7924); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7926); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign7932);
                    e5=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e5,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign7951); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionAssign"



    // $ANTLR start "actionSetFeature"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1370:1: actionSetFeature returns [AbstractTextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerAction actionSetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1371:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1372:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature7983); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature7985); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature7991);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature7993); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature7999);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8001); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createSetFeatureAction(f, v, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionSetFeature"



    // $ANTLR start "actionGetFeature"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1377:1: actionGetFeature returns [AbstractTextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final AbstractTextMarkerAction actionGetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        Token v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1378:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1379:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8040); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8042); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8048);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8050); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature8056);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8058); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createGetFeatureAction(f, v, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGetFeature"



    // $ANTLR start "actionUnmark"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1384:1: actionUnmark returns [AbstractTextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionUnmark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1385:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1386:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8094); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8096); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark8102);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8104); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createUnmarkAction(f,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionUnmark"



    // $ANTLR start "actionUnmarkAll"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1391:1: actionUnmarkAll returns [AbstractTextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionUnmarkAll() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;

        TypeListExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1392:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1393:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8140); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8142); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8148);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1394:5: ( COMMA list= typeListExpression )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==COMMA) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1394:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8156); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8162);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8166); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createUnmarkAllAction(f, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionUnmarkAll"



    // $ANTLR start "actionTransfer"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1398:1: actionTransfer returns [AbstractTextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionTransfer() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1399:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1400:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8201); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8203); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer8209);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8211); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createTransferAction(f, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionTransfer"



    // $ANTLR start "actionTrie"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1404:1: actionTrie returns [AbstractTextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionTrie() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression key =null;

        TypeExpression value =null;

        WordListExpression list =null;

        BooleanExpression ignoreCase =null;

        NumberExpression ignoreLength =null;

        BooleanExpression edit =null;

        NumberExpression distance =null;

        StringExpression ignoreChar =null;



        Map<StringExpression, TypeExpression> map = new HashMap<StringExpression, TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1408:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1409:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8251); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8253); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8263);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8265); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie8271);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(key,value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1411:5: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop150:
            do {
                int alt150=2;
                int LA150_0 = input.LA(1);

                if ( (LA150_0==COMMA) ) {
                    int LA150_1 = input.LA(2);

                    if ( (LA150_1==Identifier) ) {
                        int LA150_2 = input.LA(3);

                        if ( (LA150_2==LPAREN||LA150_2==PLUS||LA150_2==ASSIGN_EQUAL) ) {
                            alt150=1;
                        }


                    }
                    else if ( (LA150_1==REMOVESTRING||LA150_1==StringLiteral) ) {
                        alt150=1;
                    }


                }


                switch (alt150) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1411:6: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8279); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8285);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8287); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8293);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(key,value);}

            	    }
            	    break;

            	default :
            	    break loop150;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8303); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie8309);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8316); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8322);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8329); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8335);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8342); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8348);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8355); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8361);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8368); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8374);
            ignoreChar=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie8380); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createTrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionTrie"



    // $ANTLR start "actionAdd"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1423:1: actionAdd returns [AbstractTextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionAdd() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1427:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1428:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd8425); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd8427); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd8433);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1428:40: ( COMMA a= argument )+
            int cnt151=0;
            loop151:
            do {
                int alt151=2;
                int LA151_0 = input.LA(1);

                if ( (LA151_0==COMMA) ) {
                    alt151=1;
                }


                switch (alt151) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1428:41: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd8436); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd8442);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt151 >= 1 ) break loop151;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(151, input);
                        throw eee;
                }
                cnt151++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd8448); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAddAction(f, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionAdd"



    // $ANTLR start "actionRemove"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1432:1: actionRemove returns [AbstractTextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionRemove() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1436:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1437:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove8488); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove8490); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove8496);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1437:43: ( COMMA a= argument )+
            int cnt152=0;
            loop152:
            do {
                int alt152=2;
                int LA152_0 = input.LA(1);

                if ( (LA152_0==COMMA) ) {
                    alt152=1;
                }


                switch (alt152) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1437:44: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove8499); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove8505);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt152 >= 1 ) break loop152;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(152, input);
                        throw eee;
                }
                cnt152++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove8511); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createRemoveAction(f, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRemove"



    // $ANTLR start "actionRemoveDuplicate"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1441:1: actionRemoveDuplicate returns [AbstractTextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1442:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1443:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate8547); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate8549); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate8555);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate8557); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createRemoveDuplicateAction(f,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRemoveDuplicate"



    // $ANTLR start "actionMerge"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1447:1: actionMerge returns [AbstractTextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final AbstractTextMarkerAction actionMerge() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression join =null;

        Token t =null;

        ListExpression f =null;



        	List<ListExpression> list = new ArrayList<ListExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1451:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1452:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge8602); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge8604); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge8610);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge8612); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge8618);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge8620); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge8626);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1452:113: ( COMMA f= listExpression )+
            int cnt153=0;
            loop153:
            do {
                int alt153=2;
                int LA153_0 = input.LA(1);

                if ( (LA153_0==COMMA) ) {
                    alt153=1;
                }


                switch (alt153) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1452:114: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge8631); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge8637);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(f);}

            	    }
            	    break;

            	default :
            	    if ( cnt153 >= 1 ) break loop153;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(153, input);
                        throw eee;
                }
                cnt153++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge8643); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMergeAction(join, t, list,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMerge"



    // $ANTLR start "actionGet"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1456:1: actionGet returns [AbstractTextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGet() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        ListExpression f =null;

        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1457:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1458:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet8678); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet8680); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet8686);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet8688); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet8694);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet8696); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet8702);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet8704); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createGetAction(f, var, op,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGet"



    // $ANTLR start "actionGetList"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1462:1: actionGetList returns [AbstractTextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGetList() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1463:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1464:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList8739); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList8741); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList8747);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList8749); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList8755);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList8757); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createGetListAction(var, op,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGetList"



    // $ANTLR start "actionMatchedText"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1468:1: actionMatchedText returns [AbstractTextMarkerAction action = null] : MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMatchedText() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token var =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1472:5: ( MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1473:5: MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText8796); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText8798); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText8809);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1475:5: ( COMMA index= numberExpression )*
            loop154:
            do {
                int alt154=2;
                int LA154_0 = input.LA(1);

                if ( (LA154_0==COMMA) ) {
                    alt154=1;
                }


                switch (alt154) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1476:9: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText8825); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText8831);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop154;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText8855); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createMatchedTextAction(var, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMatchedText"



    // $ANTLR start "actionClear"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1484:1: actionClear returns [AbstractTextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionClear() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1485:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1486:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear8895); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear8897); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear8903);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear8905); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createClearAction(var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionClear"



    // $ANTLR start "varArgumentList"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1491:1: varArgumentList returns [List args = new ArrayList()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List varArgumentList() throws RecognitionException {
        List args =  new ArrayList();


        TextMarkerExpression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1492:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==LPAREN) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList8932); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList8938);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:41: ( COMMA arg= argument )*
                    loop155:
                    do {
                        int alt155=2;
                        int LA155_0 = input.LA(1);

                        if ( (LA155_0==COMMA) ) {
                            alt155=1;
                        }


                        switch (alt155) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:42: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList8942); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList8948);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;

                    	    if ( state.backtracking==0 ) {args.add(arg);}

                    	    }
                    	    break;

                    	default :
                    	    break loop155;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList8954); if (state.failed) return args;

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return args;
    }
    // $ANTLR end "varArgumentList"



    // $ANTLR start "argument"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1496:1: argument returns [TextMarkerExpression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final TextMarkerExpression argument() throws RecognitionException {
        TextMarkerExpression expr =  null;


        StringExpression a4 =null;

        BooleanExpression a2 =null;

        NumberExpression a3 =null;

        TypeExpression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1500:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
            int alt157=4;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
            case StringLiteral:
                {
                alt157=1;
                }
                break;
            case Identifier:
                {
                int LA157_2 = input.LA(2);

                if ( (synpred29_TextMarkerParser()) ) {
                    alt157=1;
                }
                else if ( (synpred30_TextMarkerParser()) ) {
                    alt157=2;
                }
                else if ( (synpred31_TextMarkerParser()) ) {
                    alt157=3;
                }
                else if ( (true) ) {
                    alt157=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 157, 2, input);

                    throw nvae;

                }
                }
                break;
            case XOR:
            case TRUE:
            case FALSE:
                {
                alt157=2;
                }
                break;
            case BasicAnnotationType:
                {
                int LA157_6 = input.LA(2);

                if ( (synpred30_TextMarkerParser()) ) {
                    alt157=2;
                }
                else if ( (true) ) {
                    alt157=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 157, 6, input);

                    throw nvae;

                }
                }
                break;
            case LPAREN:
                {
                int LA157_7 = input.LA(2);

                if ( (synpred30_TextMarkerParser()) ) {
                    alt157=2;
                }
                else if ( (synpred31_TextMarkerParser()) ) {
                    alt157=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 157, 7, input);

                    throw nvae;

                }
                }
                break;
            case EXP:
            case LOGN:
            case SIN:
            case COS:
            case TAN:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case MINUS:
                {
                alt157=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 157, 0, input);

                throw nvae;

            }

            switch (alt157) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1501:2: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument8988);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1502:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument8999);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1503:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9010);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9021);
                    a1=typeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a1;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "argument"



    // $ANTLR start "dottedIdentifier"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1513:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1514:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1515:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9055); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1516:2: (dot= DOT idn= Identifier )*
            loop158:
            do {
                int alt158=2;
                int LA158_0 = input.LA(1);

                if ( (LA158_0==DOT) ) {
                    alt158=1;
                }


                switch (alt158) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1517:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9068); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9078); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop158;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return idString;
    }
    // $ANTLR end "dottedIdentifier"



    // $ANTLR start "dottedIdentifier2"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1522:1: dottedIdentifier2 returns [String idString = \"\"] : id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* ;
    public final String dottedIdentifier2() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1523:2: (id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1524:2: id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29104); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1525:2: (dot= ( DOT | MINUS ) idn= Identifier )*
            loop159:
            do {
                int alt159=2;
                int LA159_0 = input.LA(1);

                if ( (LA159_0==DOT||LA159_0==MINUS) ) {
                    alt159=1;
                }


                switch (alt159) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1526:3: dot= ( DOT | MINUS ) idn= Identifier
            	    {
            	    dot=(Token)input.LT(1);

            	    if ( input.LA(1)==DOT||input.LA(1)==MINUS ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return idString;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29131); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop159;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return idString;
    }
    // $ANTLR end "dottedIdentifier2"



    // $ANTLR start "dottedId"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1532:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1534:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1535:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9163); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1538:2: (dot= DOT id= Identifier )*
            loop160:
            do {
                int alt160=2;
                int LA160_0 = input.LA(1);

                if ( (LA160_0==DOT) ) {
                    alt160=1;
                }


                switch (alt160) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1539:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9176); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9186); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop160;
                }
            } while (true);


            if ( state.backtracking==0 ) {token = ct;
            	 return token;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return token;
    }
    // $ANTLR end "dottedId"



    // $ANTLR start "annotationType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1547:1: annotationType returns [Token ref = null] : (token= BasicAnnotationType |did= dottedId ) ;
    public final Token annotationType() throws RecognitionException {
        Token ref =  null;


        Token token=null;
        Token did =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1548:2: ( (token= BasicAnnotationType |did= dottedId ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:2: (token= BasicAnnotationType |did= dottedId )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:2: (token= BasicAnnotationType |did= dottedId )
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==BasicAnnotationType) ) {
                alt161=1;
            }
            else if ( (LA161_0==Identifier) ) {
                alt161=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 161, 0, input);

                throw nvae;

            }
            switch (alt161) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1551:2: token= BasicAnnotationType
                    {
                    token=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType9221); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1553:2: did= dottedId
                    {
                    pushFollow(FOLLOW_dottedId_in_annotationType9233);
                    did=dottedId();

                    state._fsp--;
                    if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = did;}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return ref;
    }
    // $ANTLR end "annotationType"



    // $ANTLR start "wordListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1557:1: wordListExpression returns [WordListExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordListExpression wordListExpression() throws RecognitionException {
        WordListExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1558:2: (id= Identifier |path= RessourceLiteral )
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==Identifier) ) {
                alt162=1;
            }
            else if ( (LA162_0==RessourceLiteral) ) {
                alt162=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 162, 0, input);

                throw nvae;

            }
            switch (alt162) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1559:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression9258); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordListExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1562:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression9271); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createLiteralWordListExpression(path);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "wordListExpression"



    // $ANTLR start "wordTableExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1567:1: wordTableExpression returns [WordTableExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordTableExpression wordTableExpression() throws RecognitionException {
        WordTableExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1568:2: (id= Identifier |path= RessourceLiteral )
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==Identifier) ) {
                alt163=1;
            }
            else if ( (LA163_0==RessourceLiteral) ) {
                alt163=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 163, 0, input);

                throw nvae;

            }
            switch (alt163) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1569:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression9295); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordTableExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1572:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression9308); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createLiteralWordTableExpression(path);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "wordTableExpression"



    // $ANTLR start "numberFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1577:1: numberFunction returns [NumberExpression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final NumberExpression numberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression numExprP =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1578:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( ((LA164_0 >= EXP && LA164_0 <= TAN)) ) {
                alt164=1;
            }
            else if ( (LA164_0==Identifier) && (synpred32_TextMarkerParser())) {
                alt164=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;

            }
            switch (alt164) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1579:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1579:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1579:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
                    {
                    op=(Token)input.LT(1);

                    if ( (input.LA(1) >= EXP && input.LA(1) <= TAN) ) {
                        input.consume();
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction9353);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1582:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction9377);
                    e=externalNumberFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberFunction"



    // $ANTLR start "externalNumberFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1587:1: externalNumberFunction returns [NumberExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final NumberExpression externalNumberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1588:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1590:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction9403); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction9405); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction9412);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction9414); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalNumberFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalNumberFunction"



    // $ANTLR start "numberVariable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1597:1: numberVariable returns [Token ref = null] : ({...}?token1= Identifier |{...}?token2= Identifier );
    public final Token numberVariable() throws RecognitionException {
        Token ref =  null;


        Token token1=null;
        Token token2=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1598:2: ({...}?token1= Identifier |{...}?token2= Identifier )
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==Identifier) ) {
                int LA165_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                    alt165=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                    alt165=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ref;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 165, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 165, 0, input);

                throw nvae;

            }
            switch (alt165) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1599:2: {...}?token1= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INT\")");
                    }

                    token1=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9439); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1600:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLE\")");
                    }

                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9452); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token2;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return ref;
    }
    // $ANTLR end "numberVariable"



    // $ANTLR start "additiveExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1604:1: additiveExpression returns [NumberExpression expr = null] : e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* ;
    public final NumberExpression additiveExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1607:2: (e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1608:2: e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9482);
            e=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {exprs.add(e);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1608:46: (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            loop166:
            do {
                int alt166=2;
                int LA166_0 = input.LA(1);

                if ( ((LA166_0 >= PLUS && LA166_0 <= MINUS)) ) {
                    alt166=1;
                }


                switch (alt166) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1608:48: op= ( PLUS | MINUS ) e= multiplicativeExpression
            	    {
            	    op=(Token)input.LT(1);

            	    if ( (input.LA(1) >= PLUS && input.LA(1) <= MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return expr;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    if ( state.backtracking==0 ) {ops.add(op);}

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9504);
            	    e=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) {exprs.add(e);}

            	    }
            	    break;

            	default :
            	    break loop166;
                }
            } while (true);


            if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "additiveExpression"



    // $ANTLR start "multiplicativeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1612:1: multiplicativeExpression returns [NumberExpression expr = null] : (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) ;
    public final NumberExpression multiplicativeExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;

        NumberExpression e1 =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1615:2: ( (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1616:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1616:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            int alt168=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt168=1;
                }
                break;
            case Identifier:
                {
                int LA168_2 = input.LA(2);

                if ( (LA168_2==LPAREN) ) {
                    alt168=2;
                }
                else if ( (LA168_2==EOF||LA168_2==RPAREN||LA168_2==RBRACK||(LA168_2 >= COMMA && LA168_2 <= SLASH)||(LA168_2 >= LESS && LA168_2 <= GREATER)||LA168_2==PERCENT||(LA168_2 >= EQUAL && LA168_2 <= NOTEQUAL)||(LA168_2 >= LESSEQUAL && LA168_2 <= GREATEREQUAL)) ) {
                    alt168=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 168, 2, input);

                    throw nvae;

                }
                }
                break;
            case EXP:
            case LOGN:
            case SIN:
            case COS:
            case TAN:
                {
                alt168=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 168, 0, input);

                throw nvae;

            }

            switch (alt168) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1616:3: e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression9537);
                    e=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1616:45: (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    loop167:
                    do {
                        int alt167=2;
                        int LA167_0 = input.LA(1);

                        if ( ((LA167_0 >= STAR && LA167_0 <= SLASH)||LA167_0==PERCENT) ) {
                            alt167=1;
                        }


                        switch (alt167) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1616:47: op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression
                    	    {
                    	    op=(Token)input.LT(1);

                    	    if ( (input.LA(1) >= STAR && input.LA(1) <= SLASH)||input.LA(1)==PERCENT ) {
                    	        input.consume();
                    	        state.errorRecovery=false;
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }


                    	    if ( state.backtracking==0 ) {ops.add(op);}

                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression9565);
                    	    e=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {exprs.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop167;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1618:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression9583);
                    e1=numberFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "multiplicativeExpression"



    // $ANTLR start "numberExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1621:1: numberExpression returns [NumberExpression expr = null] : e= additiveExpression ;
    public final NumberExpression numberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1622:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1623:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression9606);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberExpression"



    // $ANTLR start "numberExpressionInPar"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1626:1: numberExpressionInPar returns [NumberExpression expr = null] : LPAREN e= additiveExpression RPAREN ;
    public final NumberExpression numberExpressionInPar() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1627:2: ( LPAREN e= additiveExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1628:2: LPAREN e= additiveExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar9624); if (state.failed) return expr;

            pushFollow(FOLLOW_additiveExpression_in_numberExpressionInPar9631);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar9633); if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberExpressionInPar"



    // $ANTLR start "simpleNumberExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1631:1: simpleNumberExpression returns [NumberExpression expr = null] : ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar );
    public final NumberExpression simpleNumberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token m=null;
        Token lit=null;
        Token var =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1632:2: ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar )
            int alt172=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case DecimalLiteral:
                    {
                    alt172=1;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt172=2;
                    }
                    break;
                case Identifier:
                    {
                    alt172=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 172, 1, input);

                    throw nvae;

                }

                }
                break;
            case DecimalLiteral:
                {
                alt172=1;
                }
                break;
            case FloatingPointLiteral:
                {
                alt172=2;
                }
                break;
            case Identifier:
                {
                alt172=3;
                }
                break;
            case LPAREN:
                {
                alt172=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 172, 0, input);

                throw nvae;

            }

            switch (alt172) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1633:2: (m= MINUS )? lit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1633:4: (m= MINUS )?
                    int alt169=2;
                    int LA169_0 = input.LA(1);

                    if ( (LA169_0==MINUS) ) {
                        alt169=1;
                    }
                    switch (alt169) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1633:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9656); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression9663); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createIntegerExpression(lit,m);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1634:4: (m= MINUS )? lit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1634:6: (m= MINUS )?
                    int alt170=2;
                    int LA170_0 = input.LA(1);

                    if ( (LA170_0==MINUS) ) {
                        alt170=1;
                    }
                    switch (alt170) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1634:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9675); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression9682); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDoubleExpression(lit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1635:4: (m= MINUS )? var= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1635:6: (m= MINUS )?
                    int alt171=2;
                    int LA171_0 = input.LA(1);

                    if ( (LA171_0==MINUS) ) {
                        alt171=1;
                    }
                    switch (alt171) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1635:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9693); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression9700);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceNumberExpression(var,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1636:4: e= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression9711);
                    e=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleNumberExpression"



    // $ANTLR start "stringExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1639:1: stringExpression returns [StringExpression expr = null] options {backtrack=true; } : (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction );
    public final StringExpression stringExpression() throws RecognitionException {
        StringExpression expr =  null;


        StringExpression e =null;

        StringExpression e1 =null;

        NumberExpression e2 =null;

        BooleanExpression be =null;

        TypeExpression te =null;

        ListExpression le =null;



        List<StringExpression> exprs = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1646:2: (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction )
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==StringLiteral) ) {
                alt175=1;
            }
            else if ( (LA175_0==Identifier) ) {
                int LA175_2 = input.LA(2);

                if ( (LA175_2==LPAREN) && (synpred34_TextMarkerParser())) {
                    alt175=2;
                }
                else if ( (LA175_2==EOF||LA175_2==RPAREN||(LA175_2 >= COMMA && LA175_2 <= PLUS)||LA175_2==ASSIGN_EQUAL) ) {
                    alt175=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 175, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA175_0==REMOVESTRING) && (synpred34_TextMarkerParser())) {
                alt175=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 175, 0, input);

                throw nvae;

            }
            switch (alt175) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1647:2: e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression9751);
                    e=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1648:2: ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    loop174:
                    do {
                        int alt174=2;
                        int LA174_0 = input.LA(1);

                        if ( (LA174_0==PLUS) ) {
                            alt174=1;
                        }


                        switch (alt174) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1648:3: PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression9758); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1648:8: (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    int alt173=5;
                    	    switch ( input.LA(1) ) {
                    	    case StringLiteral:
                    	        {
                    	        alt173=1;
                    	        }
                    	        break;
                    	    case Identifier:
                    	        {
                    	        int LA173_2 = input.LA(2);

                    	        if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    	            alt173=1;
                    	        }
                    	        else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt173=3;
                    	        }
                    	        else if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))))) ) {
                    	            alt173=4;
                    	        }
                    	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                    	            alt173=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 173, 2, input);

                    	            throw nvae;

                    	        }
                    	        }
                    	        break;
                    	    case LPAREN:
                    	        {
                    	        alt173=2;
                    	        }
                    	        break;
                    	    case TRUE:
                    	    case FALSE:
                    	        {
                    	        alt173=3;
                    	        }
                    	        break;
                    	    case BasicAnnotationType:
                    	        {
                    	        alt173=4;
                    	        }
                    	        break;
                    	    case LCURLY:
                    	        {
                    	        alt173=5;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 173, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt173) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1648:9: e1= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression9765);
                    	            e1=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1649:5: e2= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression9778);
                    	            e2=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1650:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression9790);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1651:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression9802);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(te);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1652:5: le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression9814);
                    	            le=listExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(le);}

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop174;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedStringExpression(exprs);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:3: (e= stringFunction )=>e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression9842);
                    e=stringFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "stringExpression"



    // $ANTLR start "stringFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1659:1: stringFunction returns [StringExpression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final StringExpression stringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token name=null;
        Token var =null;

        StringExpression t =null;

        StringExpression e =null;


        List<StringExpression> list = new ArrayList<StringExpression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1661:2: (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==REMOVESTRING) ) {
                alt177=1;
            }
            else if ( (LA177_0==Identifier) && (synpred35_TextMarkerParser())) {
                alt177=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 177, 0, input);

                throw nvae;

            }
            switch (alt177) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1662:2: name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction9869); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction9871); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction9877);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1662:44: ( COMMA t= stringExpression )+
                    int cnt176=0;
                    loop176:
                    do {
                        int alt176=2;
                        int LA176_0 = input.LA(1);

                        if ( (LA176_0==COMMA) ) {
                            alt176=1;
                        }


                        switch (alt176) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1662:45: COMMA t= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction9880); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction9886);
                    	    t=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {list.add(t);}

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt176 >= 1 ) break loop176;
                    	    if (state.backtracking>0) {state.failed=true; return expr;}
                                EarlyExitException eee =
                                    new EarlyExitException(176, input);
                                throw eee;
                        }
                        cnt176++;
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction9892); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = StringFunctionFactory.createRemoveFunction(var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1665:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction9914);
                    e=externalStringFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "stringFunction"



    // $ANTLR start "externalStringFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1669:1: externalStringFunction returns [StringExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final StringExpression externalStringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1670:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1672:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction9939); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction9941); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction9948);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction9950); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalStringFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalStringFunction"



    // $ANTLR start "simpleStringExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1679:1: simpleStringExpression returns [StringExpression expr = null] : (lit= StringLiteral |{...}?id= Identifier );
    public final StringExpression simpleStringExpression() throws RecognitionException {
        StringExpression expr =  null;


        Token lit=null;
        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1680:2: (lit= StringLiteral |{...}?id= Identifier )
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==StringLiteral) ) {
                alt178=1;
            }
            else if ( (LA178_0==Identifier) ) {
                alt178=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 178, 0, input);

                throw nvae;

            }
            switch (alt178) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1681:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression9974); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleStringExpression(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1682:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRING\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression9988); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceStringExpression(id);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleStringExpression"



    // $ANTLR start "booleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1686:1: booleanExpression returns [BooleanExpression expr = null] : ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final BooleanExpression booleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e =null;

        BooleanExpression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1687:2: ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression )
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==TRUE) ) {
                int LA179_1 = input.LA(2);

                if ( ((LA179_1 >= EQUAL && LA179_1 <= NOTEQUAL)) && (synpred36_TextMarkerParser())) {
                    alt179=1;
                }
                else if ( (LA179_1==EOF||LA179_1==RPAREN||(LA179_1 >= COMMA && LA179_1 <= SEMI)) ) {
                    alt179=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 179, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA179_0==FALSE) ) {
                int LA179_2 = input.LA(2);

                if ( ((LA179_2 >= EQUAL && LA179_2 <= NOTEQUAL)) && (synpred36_TextMarkerParser())) {
                    alt179=1;
                }
                else if ( (LA179_2==EOF||LA179_2==RPAREN||(LA179_2 >= COMMA && LA179_2 <= SEMI)) ) {
                    alt179=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 179, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA179_0==Identifier) ) {
                int LA179_3 = input.LA(2);

                if ( (LA179_3==LPAREN) && (synpred36_TextMarkerParser())) {
                    alt179=1;
                }
                else if ( ((LA179_3 >= EQUAL && LA179_3 <= NOTEQUAL)) && (synpred36_TextMarkerParser())) {
                    alt179=1;
                }
                else if ( (LA179_3==DOT) && (synpred36_TextMarkerParser())) {
                    alt179=1;
                }
                else if ( (LA179_3==EOF||LA179_3==RPAREN||(LA179_3 >= COMMA && LA179_3 <= SEMI)) ) {
                    alt179=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 179, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA179_0==BasicAnnotationType) && (synpred36_TextMarkerParser())) {
                alt179=1;
            }
            else if ( (LA179_0==LPAREN) && (synpred36_TextMarkerParser())) {
                alt179=1;
            }
            else if ( (LA179_0==XOR) && (synpred36_TextMarkerParser())) {
                alt179=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 179, 0, input);

                throw nvae;

            }
            switch (alt179) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1688:2: (e= composedBooleanExpression )=>e= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10021);
                    e=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1689:3: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10032);
                    sbE=simpleBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = sbE;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanExpression"



    // $ANTLR start "simpleBooleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1692:1: simpleBooleanExpression returns [BooleanExpression expr = null] : (e= literalBooleanExpression |{...}?id= Identifier );
    public final BooleanExpression simpleBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1693:2: (e= literalBooleanExpression |{...}?id= Identifier )
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( ((LA180_0 >= TRUE && LA180_0 <= FALSE)) ) {
                alt180=1;
            }
            else if ( (LA180_0==Identifier) ) {
                alt180=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 180, 0, input);

                throw nvae;

            }
            switch (alt180) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1694:3: e= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10055);
                    e=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1695:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression10070); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceBooleanExpression(id);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanExpression"



    // $ANTLR start "composedBooleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1700:1: composedBooleanExpression returns [BooleanExpression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final BooleanExpression composedBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e2 =null;

        BooleanExpression bte =null;

        BooleanExpression bne =null;

        BooleanExpression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1702:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
            int alt181=4;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==TRUE) && (synpred37_TextMarkerParser())) {
                alt181=1;
            }
            else if ( (LA181_0==FALSE) && (synpred37_TextMarkerParser())) {
                alt181=1;
            }
            else if ( (LA181_0==Identifier) ) {
                int LA181_3 = input.LA(2);

                if ( (((synpred37_TextMarkerParser()&&synpred37_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt181=1;
                }
                else if ( (synpred38_TextMarkerParser()) ) {
                    alt181=2;
                }
                else if ( (true) ) {
                    alt181=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 181, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA181_0==BasicAnnotationType) && (synpred38_TextMarkerParser())) {
                alt181=2;
            }
            else if ( (LA181_0==LPAREN) && (synpred39_TextMarkerParser())) {
                alt181=3;
            }
            else if ( (LA181_0==XOR) ) {
                alt181=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 181, 0, input);

                throw nvae;

            }
            switch (alt181) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression10104);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1704:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression10124);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1705:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression10143);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1706:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression10153);
                    e1=booleanFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "composedBooleanExpression"



    // $ANTLR start "booleanFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1710:1: booleanFunction returns [BooleanExpression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final BooleanExpression booleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;

        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1712:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==XOR) ) {
                alt182=1;
            }
            else if ( (LA182_0==Identifier) && (synpred40_TextMarkerParser())) {
                alt182=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 182, 0, input);

                throw nvae;

            }
            switch (alt182) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction10178); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction10180); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10186);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction10188); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10194);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction10196); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1715:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction10218);
                    e=externalBooleanFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanFunction"



    // $ANTLR start "externalBooleanFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1719:1: externalBooleanFunction returns [BooleanExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final BooleanExpression externalBooleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1720:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1722:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction10243); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction10245); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction10252);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction10254); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalBooleanFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalBooleanFunction"



    // $ANTLR start "booleanCompare"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1730:1: booleanCompare returns [BooleanExpression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final BooleanExpression booleanCompare() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1731:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1732:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1732:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1732:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare10279);
            e1=simpleBooleanExpression();

            state._fsp--;
            if (state.failed) return expr;

            op=(Token)input.LT(1);

            if ( (input.LA(1) >= EQUAL && input.LA(1) <= NOTEQUAL) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare10297);
            e2=booleanExpression();

            state._fsp--;
            if (state.failed) return expr;

            }


            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanCompare"



    // $ANTLR start "literalBooleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1737:1: literalBooleanExpression returns [BooleanExpression expr = null] : (v= TRUE |v= FALSE );
    public final BooleanExpression literalBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1738:2: (v= TRUE |v= FALSE )
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==TRUE) ) {
                alt183=1;
            }
            else if ( (LA183_0==FALSE) ) {
                alt183=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 183, 0, input);

                throw nvae;

            }
            switch (alt183) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1739:2: v= TRUE
                    {
                    v=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression10323); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleBooleanExpression(v);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1740:4: v= FALSE
                    {
                    v=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression10335); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleBooleanExpression(v);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "literalBooleanExpression"



    // $ANTLR start "booleanTypeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1745:1: booleanTypeExpression returns [BooleanExpression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final BooleanExpression booleanTypeExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        TypeExpression e1 =null;

        TypeExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1746:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1747:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10361);
            e1=typeExpression();

            state._fsp--;
            if (state.failed) return expr;

            op=(Token)input.LT(1);

            if ( (input.LA(1) >= EQUAL && input.LA(1) <= NOTEQUAL) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10381);
            e2=typeExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanTypeExpression"



    // $ANTLR start "booleanNumberExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1753:1: booleanNumberExpression returns [BooleanExpression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final BooleanExpression booleanNumberExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        NumberExpression e1 =null;

        NumberExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1754:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1755:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression10403); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10410);
            e1=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            op=(Token)input.LT(1);

            if ( (input.LA(1) >= LESS && input.LA(1) <= GREATER)||(input.LA(1) >= EQUAL && input.LA(1) <= NOTEQUAL)||(input.LA(1) >= LESSEQUAL && input.LA(1) <= GREATEREQUAL) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10446);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression10449); if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanNumberExpression"

    // $ANTLR start synpred1_TextMarkerParser
    public final void synpred1_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:4: ( ruleElementComposed[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:5: ruleElementComposed[null]
        {
        pushFollow(FOLLOW_ruleElementComposed_in_synpred1_TextMarkerParser1731);
        ruleElementComposed(null);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_TextMarkerParser

    // $ANTLR start synpred2_TextMarkerParser
    public final void synpred2_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:515:4: ( ruleElementDisjunctive[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:515:5: ruleElementDisjunctive[null]
        {
        pushFollow(FOLLOW_ruleElementDisjunctive_in_synpred2_TextMarkerParser1748);
        ruleElementDisjunctive(null);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:637:2: ( booleanListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:637:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred7_TextMarkerParser2337);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:638:4: ( intListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:638:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred8_TextMarkerParser2353);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred9_TextMarkerParser
    public final void synpred9_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:639:4: ( doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:639:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred9_TextMarkerParser2369);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_TextMarkerParser

    // $ANTLR start synpred10_TextMarkerParser
    public final void synpred10_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:640:4: ( stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:640:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred10_TextMarkerParser2385);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_TextMarkerParser

    // $ANTLR start synpred11_TextMarkerParser
    public final void synpred11_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:641:4: ( typeListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:641:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred11_TextMarkerParser2401);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {
        NumberListExpression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:680:2: (e1= doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:680:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred12_TextMarkerParser2608);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred13_TextMarkerParser
    public final void synpred13_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression tf =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:2: (tf= typeFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:2: tf= typeFunction
        {
        pushFollow(FOLLOW_typeFunction_in_synpred13_TextMarkerParser2928);
        tf=typeFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {
        AbstractTextMarkerCondition c =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:851:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:851:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred15_TextMarkerParser3500);
        c=externalCondition();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred16_TextMarkerParser
    public final void synpred16_TextMarkerParser_fragment() throws RecognitionException {
        ListExpression type =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        match(input,COUNT,FOLLOW_COUNT_in_synpred16_TextMarkerParser3862); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred16_TextMarkerParser3864); if (state.failed) return ;

        pushFollow(FOLLOW_listExpression_in_synpred16_TextMarkerParser3870);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,COMMA,FOLLOW_COMMA_in_synpred16_TextMarkerParser3872); if (state.failed) return ;

        pushFollow(FOLLOW_argument_in_synpred16_TextMarkerParser3878);
        a=argument();

        state._fsp--;
        if (state.failed) return ;

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
        int alt184=2;
        int LA184_0 = input.LA(1);

        if ( (LA184_0==COMMA) ) {
            int LA184_1 = input.LA(2);

            if ( ((LA184_1 >= EXP && LA184_1 <= TAN)||LA184_1==DecimalLiteral||LA184_1==FloatingPointLiteral||LA184_1==LPAREN||LA184_1==MINUS) ) {
                alt184=1;
            }
            else if ( (LA184_1==Identifier) ) {
                int LA184_4 = input.LA(3);

                if ( (LA184_4==LPAREN||LA184_4==COMMA||(LA184_4 >= PLUS && LA184_4 <= SLASH)||LA184_4==PERCENT) ) {
                    alt184=1;
                }
            }
        }
        switch (alt184) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:60: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred16_TextMarkerParser3881); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred16_TextMarkerParser3887);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                match(input,COMMA,FOLLOW_COMMA_in_synpred16_TextMarkerParser3889); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred16_TextMarkerParser3895);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:903:5: ( COMMA var= numberVariable )?
        int alt185=2;
        int LA185_0 = input.LA(1);

        if ( (LA185_0==COMMA) ) {
            alt185=1;
        }
        switch (alt185) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:903:6: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred16_TextMarkerParser3905); if (state.failed) return ;

                pushFollow(FOLLOW_numberVariable_in_synpred16_TextMarkerParser3911);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        match(input,RPAREN,FOLLOW_RPAREN_in_synpred16_TextMarkerParser3915); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {
        StringListExpression list2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:20: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:21: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred17_TextMarkerParser4189);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_TextMarkerParser

    // $ANTLR start synpred18_TextMarkerParser
    public final void synpred18_TextMarkerParser_fragment() throws RecognitionException {
        AbstractTextMarkerAction a =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1097:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1097:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred18_TextMarkerParser5743);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:5: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:6: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred19_TextMarkerParser5896);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred20_TextMarkerParser
    public final void synpred20_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:81: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:82: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred20_TextMarkerParser5909); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred20_TextMarkerParser5915);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_TextMarkerParser

    // $ANTLR start synpred21_TextMarkerParser
    public final void synpred21_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:6: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:7: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred21_TextMarkerParser6208);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:82: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:83: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred22_TextMarkerParser6221); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred22_TextMarkerParser6227);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:22: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:23: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred26_TextMarkerParser7032);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:85: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:86: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred27_TextMarkerParser7052);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1501:2: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1501:2: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred29_TextMarkerParser8988);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1502:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1502:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred30_TextMarkerParser8999);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1503:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1503:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred31_TextMarkerParser9010);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1582:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1582:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred32_TextMarkerParser9369);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:3: (e= stringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:4: e= stringFunction
        {
        pushFollow(FOLLOW_stringFunction_in_synpred34_TextMarkerParser9834);
        e=stringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred35_TextMarkerParser
    public final void synpred35_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1665:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1665:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred35_TextMarkerParser9906);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred35_TextMarkerParser

    // $ANTLR start synpred36_TextMarkerParser
    public final void synpred36_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1688:2: (e= composedBooleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1688:3: e= composedBooleanExpression
        {
        pushFollow(FOLLOW_composedBooleanExpression_in_synpred36_TextMarkerParser10013);
        e=composedBooleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred36_TextMarkerParser

    // $ANTLR start synpred37_TextMarkerParser
    public final void synpred37_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred37_TextMarkerParser10096);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred37_TextMarkerParser

    // $ANTLR start synpred38_TextMarkerParser
    public final void synpred38_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1704:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1704:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred38_TextMarkerParser10116);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred38_TextMarkerParser

    // $ANTLR start synpred39_TextMarkerParser
    public final void synpred39_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1705:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1705:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred39_TextMarkerParser10135);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred39_TextMarkerParser

    // $ANTLR start synpred40_TextMarkerParser
    public final void synpred40_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1715:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1715:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred40_TextMarkerParser10210);
        e=externalBooleanFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred40_TextMarkerParser

    // Delegated rules

    public final boolean synpred16_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred38_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred37_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred40_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred29_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred26_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred22_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA28 dfa28 = new DFA28(this);
    static final String DFA28_eotS =
        "\11\uffff";
    static final String DFA28_eofS =
        "\11\uffff";
    static final String DFA28_minS =
        "\1\11\1\114\2\172\1\173\1\172\2\uffff\1\172";
    static final String DFA28_maxS =
        "\1\11\2\172\2\u0086\1\172\2\uffff\1\u0083";
    static final String DFA28_acceptS =
        "\6\uffff\1\1\1\2\1\uffff";
    static final String DFA28_specialS =
        "\11\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\1",
            "\1\2\55\uffff\1\3",
            "\1\4",
            "\1\4\10\uffff\1\5\1\uffff\2\6",
            "\1\7\11\uffff\2\6",
            "\1\10",
            "",
            "",
            "\1\4\10\uffff\1\5"
    };

    static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
    static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
    static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
    static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
    static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
    static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
    static final short[][] DFA28_transition;

    static {
        int numStates = DFA28_transitionS.length;
        DFA28_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
        }
    }

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = DFA28_eot;
            this.eof = DFA28_eof;
            this.min = DFA28_min;
            this.max = DFA28_max;
            this.accept = DFA28_accept;
            this.special = DFA28_special;
            this.transition = DFA28_transition;
        }
        public String getDescription() {
            return "357:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
        }
    }
 

    public static final BitSet FOLLOW_packageDeclaration_in_file_input76 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003FBFF1000L});
    public static final BitSet FOLLOW_globalStatements_in_file_input92 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_statements_in_file_input101 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file_input109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PackageString_in_packageDeclaration124 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier_in_packageDeclaration130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements155 = new BitSet(new long[]{0x0000000000000E02L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_globalStatement_in_globalStatements180 = new BitSet(new long[]{0x0000000000000002L,0x0000000002030000L});
    public static final BitSet FOLLOW_importStatement_in_globalStatement204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_statement230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStatement_in_statement252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockDeclaration_in_statement263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_automataDeclaration_in_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntString_in_variableDeclaration299 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration308 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration315 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration323 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration333 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration356 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration365 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration372 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration391 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration413 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration429 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration437 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration448 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration470 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration479 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration486 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration494 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration505 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration527 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration543 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration562 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration585 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration597 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration600 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration606 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration624 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration639 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration661 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration676 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration699 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration711 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration714 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration737 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration752 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration758 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration775 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration787 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration790 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration813 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration828 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration834 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONDITION_in_conditionDeclaration892 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_conditionDeclaration898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration900 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionDeclaration902 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionDeclaration908 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionDeclaration910 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_conditionDeclaration912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_actionDeclaration948 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionDeclaration954 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration956 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDeclaration958 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_actionDeclaration964 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDeclaration966 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_actionDeclaration968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement993 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement999 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement1007 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement1021 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1054 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1064 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1072 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_COMMA_in_declaration1079 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_SEMI_in_declaration1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1109 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1115 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1121 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1127 = new BitSet(new long[]{0x0000000000000000L,0x0400000001E01000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1142 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_StringString_in_declaration1155 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1168 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_IntString_in_declaration1180 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1192 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1208 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_declaration1220 = new BitSet(new long[]{0x0000000000000000L,0x0400000001E01000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1235 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_StringString_in_declaration1248 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1261 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_IntString_in_declaration1273 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1285 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1301 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_declaration1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1370 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1374 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1381 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1385 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1398 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1409 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L,0x0000000000000001L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AutomataBlockString_in_automataDeclaration1469 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_automataDeclaration1473 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_automataDeclaration1480 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_automataDeclaration1484 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_automataDeclaration1497 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_automataDeclaration1506 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L,0x0000000000000001L});
    public static final BitSet FOLLOW_statements_in_automataDeclaration1512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_automataDeclaration1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1551 = new BitSet(new long[]{0x0000000000000000L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1568 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1580 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1590 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1641 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1671 = new BitSet(new long[]{0x0000000000000002L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1681 = new BitSet(new long[]{0x0000000000000002L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_ruleElement1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementDisjunctive1789 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1814 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1820 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1829 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1835 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementDisjunctive1844 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementDisjunctive1870 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementDisjunctive1883 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementDisjunctive1889 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementDisjunctive1893 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementDisjunctive1899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementDisjunctive1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed1944 = new BitSet(new long[]{0x0000000000000000L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed1972 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed1982 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed1988 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed1992 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed1998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2002 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2055 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2074 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2087 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2097 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementType2103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2154 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2178 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2191 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2201 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2249 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditions2254 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditions2260 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_action_in_actions2298 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actions2303 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_action_in_actions2309 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2452 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2464 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2540 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2547 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2552 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2558 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2672 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2679 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2684 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression2760 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression2772 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2778 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression2787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression2803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression2828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression2849 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2856 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression2861 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression2876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression2939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction2998 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3000 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3007 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeExpression3034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3132 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3149 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3165 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3182 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3188 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3195 = new BitSet(new long[]{0x0000000000000000L,0x4C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3202 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3208 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3574 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3576 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3582 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3613 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3615 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3621 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3675 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3677 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3684 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3694 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionContains3700 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3709 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3717 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3723 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3726 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3732 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount3771 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount3773 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount3779 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3782 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3788 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3790 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3796 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3806 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount3812 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3862 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3864 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount3870 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3872 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionCount3878 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3881 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3887 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3889 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3895 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3905 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3911 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3933 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3935 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount3941 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3944 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3952 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3958 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3968 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3974 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4014 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4016 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4022 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4025 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4031 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4033 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4039 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4048 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4054 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4091 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4093 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4099 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4102 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4110 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4116 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4126 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4132 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4179 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4181 = new BitSet(new long[]{0x0000000000000000L,0x8420000000000000L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4196 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4204 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4208 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4214 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4217 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4223 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4264 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4266 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4272 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4275 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4281 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4283 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4289 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4334 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4336 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4342 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4391 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4393 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4401 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4409 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4415 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4452 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4454 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4460 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4462 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4470 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4476 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4479 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4485 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4488 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4494 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4536 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4549 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4551 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4557 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4598 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4600 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4606 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4638 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4640 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4647 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4653 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4686 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4688 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq4695 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq4701 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq4704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition4738 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition4740 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition4746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition4748 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition4754 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition4756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp4786 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp4788 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp4794 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp4797 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp4803 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp4807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore4842 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore4844 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore4850 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore4853 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore4859 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore4866 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore4872 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore4879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote4914 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote4916 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote4922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote4924 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote4930 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote4932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf4970 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf4972 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf4978 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf4980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5014 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5016 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5024 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5030 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5066 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5068 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_conditionParse5076 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5109 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5111 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5118 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5124 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5158 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5160 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5167 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5173 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5207 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5209 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5216 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5222 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5256 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5258 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5265 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5271 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5305 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5307 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5314 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5320 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5354 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5356 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5362 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5365 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5373 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5379 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5384 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5390 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action5499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action5517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action5544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action5553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action5562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action5571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action5580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action5589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action5598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action5607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action5616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action5625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action5634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action5643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action5661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action5670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action5679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action5688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action5697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action5706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action5715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action5724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action5733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action5751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action5760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction5789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction5817 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction5819 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction5825 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction5827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate5863 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate5865 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate5871 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5878 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate5903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5920 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate5926 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5933 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate5946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate5948 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate5954 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5964 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate5970 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate5972 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate5978 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate5993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6034 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6036 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6047 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6049 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6060 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6062 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6072 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6080 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6094 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6096 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6102 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6112 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6120 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6126 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6180 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6182 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6188 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6195 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6231 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6244 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6257 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6259 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6266 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6274 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6285 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6293 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6300 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6308 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6366 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6368 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6377 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6383 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6385 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionFill6402 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6457 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6459 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6477 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6488 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6496 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6506 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6514 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor6524 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor6534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel6568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog6610 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog6612 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog6618 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionLog6621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog6627 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog6631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark6670 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark6672 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark6683 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMark6699 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark6715 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark6739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand6783 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand6785 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand6796 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand6812 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand6828 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand6852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore6897 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore6899 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore6910 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore6912 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore6922 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore6938 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore6954 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore6978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7022 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7024 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00001000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7043 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7061 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7077 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7093 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7151 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7153 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7161 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7167 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7170 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7176 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7179 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7185 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7225 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7227 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7233 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7269 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7271 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7277 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7322 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7325 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7331 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7336 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7342 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7394 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7397 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7403 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7408 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7414 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup7468 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup7471 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7477 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup7482 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7488 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup7494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType7540 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType7543 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7549 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType7554 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7560 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType7566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall7606 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall7608 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionCall7614 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall7616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec7647 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec7649 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionExec7655 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExec7658 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec7664 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec7668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign7711 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign7713 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7742 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionAssign7748 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7786 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7788 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionAssign7794 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7834 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionAssign7840 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7880 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign7886 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7926 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign7932 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign7951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature7983 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature7985 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature7991 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature7993 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature7999 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8040 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8042 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8050 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8056 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8094 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8096 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8102 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8140 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8142 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8148 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8156 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8162 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8201 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8203 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8209 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8251 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8253 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8263 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8265 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8271 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8279 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8287 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8303 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8316 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8329 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8342 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8348 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8355 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8368 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8374 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie8380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd8425 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd8427 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd8433 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd8436 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionAdd8442 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd8448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove8488 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove8490 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove8496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove8499 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionRemove8505 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove8511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate8547 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate8549 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate8555 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate8557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge8602 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge8604 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge8610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8612 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge8618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8620 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge8626 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8631 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge8637 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge8643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet8678 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet8680 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_actionGet8686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet8688 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGet8694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet8696 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet8702 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet8704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList8739 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList8741 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList8747 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList8749 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList8755 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList8757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText8796 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText8798 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText8809 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText8825 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText8831 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText8855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear8895 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear8897 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionClear8903 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear8905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList8932 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList8938 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList8942 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList8948 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList8954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument8988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument8999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9055 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9068 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9078 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29104 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_set_in_dottedIdentifier29117 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29131 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9163 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedId9176 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9186 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType9221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationType9233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression9258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression9271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression9295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression9308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction9331 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction9353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction9377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction9403 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction9405 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction9412 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction9414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9482 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_set_in_additiveExpression9491 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9504 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression9537 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression9546 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression9565 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression9583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression9606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar9624 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpressionInPar9631 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar9633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9656 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression9663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9675 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression9682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9693 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression9700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression9711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression9751 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression9758 = new BitSet(new long[]{0x0000000000000000L,0x8C10030000001000L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression9765 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression9778 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression9790 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression9802 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression9814 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression9842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction9869 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction9871 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_stringFunction9877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction9880 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction9886 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction9892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction9914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction9939 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction9941 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction9948 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction9950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression9974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression9988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression10070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression10104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression10124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression10143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression10153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction10178 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction10180 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction10188 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10194 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction10196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction10218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction10243 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction10245 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction10252 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction10254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare10279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanCompare10285 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare10297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression10323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression10335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression10368 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression10403 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000006C6000L});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression10417 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10446 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression10449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_synpred1_TextMarkerParser1731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_synpred2_TextMarkerParser1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred7_TextMarkerParser2337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred8_TextMarkerParser2353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred9_TextMarkerParser2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred10_TextMarkerParser2385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred11_TextMarkerParser2401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred12_TextMarkerParser2608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_synpred13_TextMarkerParser2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred15_TextMarkerParser3500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred16_TextMarkerParser3862 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred16_TextMarkerParser3864 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_synpred16_TextMarkerParser3870 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3872 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_synpred16_TextMarkerParser3878 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3881 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred16_TextMarkerParser3887 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3889 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred16_TextMarkerParser3895 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3905 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_synpred16_TextMarkerParser3911 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred16_TextMarkerParser3915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred17_TextMarkerParser4189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred18_TextMarkerParser5743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred19_TextMarkerParser5896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred20_TextMarkerParser5909 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred20_TextMarkerParser5915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred21_TextMarkerParser6208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred22_TextMarkerParser6221 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred22_TextMarkerParser6227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred26_TextMarkerParser7032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred27_TextMarkerParser7052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred29_TextMarkerParser8988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred30_TextMarkerParser8999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred31_TextMarkerParser9010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred32_TextMarkerParser9369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_synpred34_TextMarkerParser9834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred35_TextMarkerParser9906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_synpred36_TextMarkerParser10013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred37_TextMarkerParser10096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred38_TextMarkerParser10116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred39_TextMarkerParser10135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred40_TextMarkerParser10210 = new BitSet(new long[]{0x0000000000000002L});

}