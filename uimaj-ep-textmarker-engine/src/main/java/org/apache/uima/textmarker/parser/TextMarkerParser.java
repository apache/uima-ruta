// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g 2012-01-10 14:15:15

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ADD", "AFTER", "ALT_NOTEQUAL", "AMPER", "AND", "ASSIGN", "ASSIGN_EQUAL", "AT", "ATTRIBUTE", "Annotation", "AutomataBlockString", "BEFORE", "BOOLEANLIST", "BasicAnnotationType", "BlockString", "BooleanString", "CALL", "CIRCUMFLEX", "CLEAR", "COLON", "COLOR", "COMMA", "COMMENT", "CONDITION", "CONFIGURE", "CONTAINS", "CONTEXTCOUNT", "COS", "COUNT", "CREATE", "CURRENTCOUNT", "CharacterLiteral", "DECLARE", "DEL", "DOT", "DOUBLELIST", "DYNAMICANCHORING", "DecimalLiteral", "DocComment", "DoubleString", "ENDSWITH", "EQUAL", "EXEC", "EXP", "EXPAND", "EngineString", "EscapeSequence", "Exponent", "FALSE", "FEATURE", "FILL", "FILTERMARKUP", "FILTERTYPE", "FLOATLIST", "FloatString", "FloatTypeSuffix", "FloatingPointLiteral", "GATHER", "GET", "GETFEATURE", "GETLIST", "GREATER", "GREATEREQUAL", "HexDigit", "HexLiteral", "IF", "INLIST", "INTLIST", "IS", "ISINTAG", "Identifier", "IntString", "IntegerTypeSuffix", "JavaIDDigit", "LAST", "LBRACK", "LCURLY", "LESS", "LESSEQUAL", "LINE_COMMENT", "LOG", "LOGN", "LPAREN", "Letter", "ListIdentifier", "LogLevel", "MARK", "MARKFAST", "MARKLAST", "MARKONCE", "MARKSCORE", "MARKTABLE", "MATCHEDTEXT", "MERGE", "MINUS", "MOFN", "NEAR", "NOT", "NOTEQUAL", "OR", "OctalEscape", "OctalLiteral", "OldColor", "PARSE", "PARTOF", "PARTOFNEQ", "PERCENT", "PLUS", "POSITION", "PackageString", "QUESTION", "RBRACK", "RCURLY", "REGEXP", "REMOVE", "REMOVEDUPLICATE", "REMOVESTRING", "REPLACE", "RETAINMARKUP", "RETAINTYPE", "RPAREN", "RessourceLiteral", "SCORE", "SEMI", "SETFEATURE", "SIN", "SIZE", "SLASH", "STAR", "STARTSWITH", "STRINGLIST", "ScriptString", "StringLiteral", "StringString", "SymbolString", "TAN", "THEN", "TOTALCOUNT", "TRANSFER", "TRIE", "TRUE", "TYPELIST", "TypeString", "TypeSystemString", "UNMARK", "UNMARKALL", "UnicodeEscape", "VBAR", "VOTE", "WORDLIST", "WORDTABLE", "WS", "XOR"
    };

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
    public static final int FLOATLIST=58;
    public static final int FloatString=59;
    public static final int FloatTypeSuffix=60;
    public static final int FloatingPointLiteral=61;
    public static final int GATHER=62;
    public static final int GET=63;
    public static final int GETFEATURE=64;
    public static final int GETLIST=65;
    public static final int GREATER=66;
    public static final int GREATEREQUAL=67;
    public static final int HexDigit=68;
    public static final int HexLiteral=69;
    public static final int IF=70;
    public static final int INLIST=71;
    public static final int INTLIST=72;
    public static final int IS=73;
    public static final int ISINTAG=74;
    public static final int Identifier=75;
    public static final int IntString=76;
    public static final int IntegerTypeSuffix=77;
    public static final int JavaIDDigit=78;
    public static final int LAST=79;
    public static final int LBRACK=80;
    public static final int LCURLY=81;
    public static final int LESS=82;
    public static final int LESSEQUAL=83;
    public static final int LINE_COMMENT=84;
    public static final int LOG=85;
    public static final int LOGN=86;
    public static final int LPAREN=87;
    public static final int Letter=88;
    public static final int ListIdentifier=89;
    public static final int LogLevel=90;
    public static final int MARK=91;
    public static final int MARKFAST=92;
    public static final int MARKLAST=93;
    public static final int MARKONCE=94;
    public static final int MARKSCORE=95;
    public static final int MARKTABLE=96;
    public static final int MATCHEDTEXT=97;
    public static final int MERGE=98;
    public static final int MINUS=99;
    public static final int MOFN=100;
    public static final int NEAR=101;
    public static final int NOT=102;
    public static final int NOTEQUAL=103;
    public static final int OR=104;
    public static final int OctalEscape=105;
    public static final int OctalLiteral=106;
    public static final int OldColor=107;
    public static final int PARSE=108;
    public static final int PARTOF=109;
    public static final int PARTOFNEQ=110;
    public static final int PERCENT=111;
    public static final int PLUS=112;
    public static final int POSITION=113;
    public static final int PackageString=114;
    public static final int QUESTION=115;
    public static final int RBRACK=116;
    public static final int RCURLY=117;
    public static final int REGEXP=118;
    public static final int REMOVE=119;
    public static final int REMOVEDUPLICATE=120;
    public static final int REMOVESTRING=121;
    public static final int REPLACE=122;
    public static final int RETAINMARKUP=123;
    public static final int RETAINTYPE=124;
    public static final int RPAREN=125;
    public static final int RessourceLiteral=126;
    public static final int SCORE=127;
    public static final int SEMI=128;
    public static final int SETFEATURE=129;
    public static final int SIN=130;
    public static final int SIZE=131;
    public static final int SLASH=132;
    public static final int STAR=133;
    public static final int STARTSWITH=134;
    public static final int STRINGLIST=135;
    public static final int ScriptString=136;
    public static final int StringLiteral=137;
    public static final int StringString=138;
    public static final int SymbolString=139;
    public static final int TAN=140;
    public static final int THEN=141;
    public static final int TOTALCOUNT=142;
    public static final int TRANSFER=143;
    public static final int TRIE=144;
    public static final int TRUE=145;
    public static final int TYPELIST=146;
    public static final int TypeString=147;
    public static final int TypeSystemString=148;
    public static final int UNMARK=149;
    public static final int UNMARKALL=150;
    public static final int UnicodeEscape=151;
    public static final int VBAR=152;
    public static final int VOTE=153;
    public static final int WORDLIST=154;
    public static final int WORDTABLE=155;
    public static final int WS=156;
    public static final int XOR=157;

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
    	
    	public void setValue(TextMarkerBlock parent, List<String> names, Object obj) {
    		for(String name : names) {
    			setValue(parent,name,obj);
    		}
    	}
    	
    	public void setValue(TextMarkerBlock parent, String name, Object obj) {
    		if(obj != null) {
    			Object value = parent.getEnvironment().getLiteralValue(name, obj);
    			parent.getEnvironment().setVariableValue(name, value);
    			parent.getEnvironment().setInitialVariableValue(name, value);
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
    	
    	private TypeSystemDescription localTSD;
    	private String[] resourcePaths;
    	

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
            	rootBlock = factory.createRootScriptBlock(moduleName, p, localTSD);
                    rootBlock.getEnvironment().setResourcePaths(resourcePaths);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:228:1: packageDeclaration returns [String pack = \"\"] : PackageString p= dottedIdentifier SEMI ;
    public final String packageDeclaration() throws RecognitionException {
        String pack =  "";


        String p =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:229:2: ( PackageString p= dottedIdentifier SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:229:4: PackageString p= dottedIdentifier SEMI
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:232:1: statements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (stmt= statement )* ;
    public final List<TextMarkerStatement> statements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        TextMarkerStatement stmt =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:233:2: ( (stmt= statement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:234:2: (stmt= statement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:234:2: (stmt= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ACTION||LA1_0==AutomataBlockString||(LA1_0 >= BOOLEANLIST && LA1_0 <= BooleanString)||LA1_0==CONDITION||LA1_0==DECLARE||LA1_0==DOUBLELIST||LA1_0==DoubleString||(LA1_0 >= FLOATLIST && LA1_0 <= FloatString)||LA1_0==INTLIST||(LA1_0 >= Identifier && LA1_0 <= IntString)||LA1_0==LPAREN||LA1_0==STRINGLIST||(LA1_0 >= StringLiteral && LA1_0 <= StringString)||(LA1_0 >= TYPELIST && LA1_0 <= TypeString)||(LA1_0 >= WORDLIST && LA1_0 <= WORDTABLE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:234:3: stmt= statement
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:237:1: globalStatements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (morestmts= globalStatement )* ;
    public final List<TextMarkerStatement> globalStatements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        List<TextMarkerStatement> morestmts =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:238:2: ( (morestmts= globalStatement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:239:2: (morestmts= globalStatement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:239:2: (morestmts= globalStatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==EngineString||LA2_0==ScriptString||LA2_0==TypeSystemString) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:239:3: morestmts= globalStatement
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:242:1: globalStatement returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : stmtImport= importStatement ;
    public final List<TextMarkerStatement> globalStatement() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        TextMarkerStatement stmtImport =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:243:2: (stmtImport= importStatement )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:244:2: stmtImport= importStatement
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:247:1: statement returns [TextMarkerStatement stmt = null] : (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration ) ;
    public final TextMarkerStatement statement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        TextMarkerStatement stmtDecl =null;

        TextMarkerStatement stmtVariable =null;

        TextMarkerRule stmtRule =null;

        TextMarkerBlock stmtBlock =null;

        TextMarkerBlock stmtAutomata =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:248:2: ( (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:249:2: (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:249:2: (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration )
            int alt3=5;
            switch ( input.LA(1) ) {
            case DECLARE:
                {
                alt3=1;
                }
                break;
            case ACTION:
            case BOOLEANLIST:
            case BooleanString:
            case CONDITION:
            case DOUBLELIST:
            case DoubleString:
            case FLOATLIST:
            case FloatString:
            case INTLIST:
            case IntString:
            case STRINGLIST:
            case StringString:
            case TYPELIST:
            case TypeString:
            case WORDLIST:
            case WORDTABLE:
                {
                alt3=2;
                }
                break;
            case BasicAnnotationType:
            case Identifier:
            case LPAREN:
            case StringLiteral:
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:249:4: stmtDecl= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_statement230);
                    stmtDecl=declaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtDecl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:250:4: stmtVariable= variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement241);
                    stmtVariable=variableDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtVariable;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:251:4: stmtRule= simpleStatement
                    {
                    pushFollow(FOLLOW_simpleStatement_in_statement252);
                    stmtRule=simpleStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtRule;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:252:4: stmtBlock= blockDeclaration
                    {
                    pushFollow(FOLLOW_blockDeclaration_in_statement263);
                    stmtBlock=blockDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtBlock;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:253:4: stmtAutomata= automataDeclaration
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:257:1: variableDeclaration returns [TextMarkerStatement stmt = null] : (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= FloatString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= FLOATLIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration );
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



        List<String> vars = new ArrayList<String>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:261:2: (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= FloatString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= FLOATLIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration )
            int alt24=16;
            switch ( input.LA(1) ) {
            case IntString:
                {
                alt24=1;
                }
                break;
            case DoubleString:
                {
                alt24=2;
                }
                break;
            case FloatString:
                {
                alt24=3;
                }
                break;
            case StringString:
                {
                alt24=4;
                }
                break;
            case BooleanString:
                {
                alt24=5;
                }
                break;
            case TypeString:
                {
                alt24=6;
                }
                break;
            case WORDLIST:
                {
                alt24=7;
                }
                break;
            case WORDTABLE:
                {
                alt24=8;
                }
                break;
            case BOOLEANLIST:
                {
                alt24=9;
                }
                break;
            case STRINGLIST:
                {
                alt24=10;
                }
                break;
            case INTLIST:
                {
                alt24=11;
                }
                break;
            case DOUBLELIST:
                {
                alt24=12;
                }
                break;
            case FLOATLIST:
                {
                alt24=13;
                }
                break;
            case TYPELIST:
                {
                alt24=14;
                }
                break;
            case CONDITION:
                {
                alt24=15;
                }
                break;
            case ACTION:
                {
                alt24=16;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmt;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:262:2: type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI
                    {
                    type=(Token)match(input,IntString,FOLLOW_IntString_in_variableDeclaration304); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration313); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:264:3: ( COMMA {...}?id= Identifier )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==COMMA) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:264:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration320); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration328); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:265:6: ( ASSIGN_EQUAL value1= numberExpression )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==ASSIGN_EQUAL) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:265:7: ASSIGN_EQUAL value1= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration338); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration344);
                            value1=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, vars, value1);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration351); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:267:2: type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI
                    {
                    type=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_variableDeclaration361); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration370); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:269:3: ( COMMA {...}?id= Identifier )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:269:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration377); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration385); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:270:7: ( ASSIGN_EQUAL value2= numberExpression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==ASSIGN_EQUAL) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:270:8: ASSIGN_EQUAL value2= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration396); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration402);
                            value2=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, vars, value2);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration408); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:272:2: type= FloatString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI
                    {
                    type=(Token)match(input,FloatString,FOLLOW_FloatString_in_variableDeclaration418); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration427); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:274:3: ( COMMA {...}?id= Identifier )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:274:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration434); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration442); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:275:7: ( ASSIGN_EQUAL value2= numberExpression )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==ASSIGN_EQUAL) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:275:8: ASSIGN_EQUAL value2= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration453); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration459);
                            value2=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, vars, value2);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration465); if (state.failed) return stmt;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:277:2: type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration475); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration484); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:279:3: ( COMMA {...}?id= Identifier )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:279:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration491); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration499); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:280:7: ( ASSIGN_EQUAL value3= stringExpression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ASSIGN_EQUAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:280:8: ASSIGN_EQUAL value3= stringExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration510); if (state.failed) return stmt;

                            pushFollow(FOLLOW_stringExpression_in_variableDeclaration516);
                            value3=stringExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, vars, value3);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration522); if (state.failed) return stmt;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:282:2: type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration532); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration541); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:284:3: ( COMMA {...}?id= Identifier )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==COMMA) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:284:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration548); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration556); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:285:7: ( ASSIGN_EQUAL value4= booleanExpression )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==ASSIGN_EQUAL) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:285:8: ASSIGN_EQUAL value4= booleanExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration567); if (state.failed) return stmt;

                            pushFollow(FOLLOW_booleanExpression_in_variableDeclaration573);
                            value4=booleanExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, vars, value4);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration579); if (state.failed) return stmt;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:287:2: type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration589); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration598); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:289:3: ( COMMA {...}?id= Identifier )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==COMMA) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:289:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration605); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration613); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {vars.add(id.getText());addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:290:7: ( ASSIGN_EQUAL value5= annotationType )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:290:8: ASSIGN_EQUAL value5= annotationType
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration624); if (state.failed) return stmt;

                            pushFollow(FOLLOW_annotationType_in_variableDeclaration630);
                            value5=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, vars, value5);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration636); if (state.failed) return stmt;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:292:2: type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration647); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration659); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:294:20: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:294:21: ASSIGN_EQUAL list= wordListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662); if (state.failed) return stmt;

                            pushFollow(FOLLOW_wordListExpression_in_variableDeclaration668);
                            list=wordListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration672); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(list != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), list);}}

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:296:2: type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration686); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration698); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:298:20: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:298:21: ASSIGN_EQUAL table= wordTableExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration701); if (state.failed) return stmt;

                            pushFollow(FOLLOW_wordTableExpression_in_variableDeclaration707);
                            table=wordTableExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration711); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(table != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), table);}}

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:300:2: type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration723); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration735); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:302:20: ( ASSIGN_EQUAL bl= booleanListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:302:21: ASSIGN_EQUAL bl= booleanListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration738); if (state.failed) return stmt;

                            pushFollow(FOLLOW_booleanListExpression_in_variableDeclaration744);
                            bl=booleanListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration748); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(bl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), bl);}}

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:304:2: type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration761); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration773); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:306:20: ( ASSIGN_EQUAL sl= stringListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:306:21: ASSIGN_EQUAL sl= stringListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration776); if (state.failed) return stmt;

                            pushFollow(FOLLOW_stringListExpression_in_variableDeclaration782);
                            sl=stringListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration786); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(sl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), sl);}}

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:308:2: type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration799); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration811); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:310:20: ( ASSIGN_EQUAL il= numberListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:310:21: ASSIGN_EQUAL il= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration814); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration820);
                            il=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration824); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(il != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), il);}}

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:312:2: type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration837); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration849); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:314:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==ASSIGN_EQUAL) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:314:21: ASSIGN_EQUAL dl= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration852); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration858);
                            dl=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration862); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(dl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), dl);}}

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:316:2: type= FLOATLIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,FLOATLIST,FOLLOW_FLOATLIST_in_variableDeclaration875); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration887); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:318:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==ASSIGN_EQUAL) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:318:21: ASSIGN_EQUAL dl= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration890); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration896);
                            dl=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration900); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(dl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), dl);}}

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:320:2: type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration913); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration925); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:322:20: ( ASSIGN_EQUAL tl= typeListExpression )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==ASSIGN_EQUAL) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:322:21: ASSIGN_EQUAL tl= typeListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration928); if (state.failed) return stmt;

                            pushFollow(FOLLOW_typeListExpression_in_variableDeclaration934);
                            tl=typeListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration938); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(tl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), tl);}}

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:324:2: stmt1= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration951);
                    stmt1=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmt1;}

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:326:2: stmt2= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration963);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:330:1: conditionDeclaration returns [TextMarkerStatement stmt = null] : type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final TextMarkerStatement conditionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerCondition> cons =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:331:5: (type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:332:5: type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
            {
            type=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_conditionDeclaration992); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionDeclaration998); if (state.failed) return stmt;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1000); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionDeclaration1002); if (state.failed) return stmt;

            pushFollow(FOLLOW_conditions_in_conditionDeclaration1008);
            cons=conditions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionDeclaration1010); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_conditionDeclaration1012); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:339:1: actionDeclaration returns [TextMarkerStatement stmt = null] : type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final TextMarkerStatement actionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:340:5: (type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:341:5: type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
            {
            type=(Token)match(input,ACTION,FOLLOW_ACTION_in_actionDeclaration1048); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionDeclaration1054); if (state.failed) return stmt;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1056); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDeclaration1058); if (state.failed) return stmt;

            pushFollow(FOLLOW_actions_in_actionDeclaration1064);
            a=actions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDeclaration1066); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_actionDeclaration1068); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:347:1: importStatement returns [TextMarkerStatement stmt = null] : ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI );
    public final TextMarkerStatement importStatement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        String ts =null;

        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:348:2: ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI )
            int alt25=3;
            switch ( input.LA(1) ) {
            case TypeSystemString:
                {
                alt25=1;
                }
                break;
            case ScriptString:
                {
                alt25=2;
                }
                break;
            case EngineString:
                {
                alt25=3;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:349:2: TypeSystemString ts= dottedIdentifier2 SEMI
                    {
                    match(input,TypeSystemString,FOLLOW_TypeSystemString_in_importStatement1093); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1099);
                    ts=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportTypeSystem(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ts);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1102); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:350:4: ScriptString ns= dottedIdentifier2 SEMI
                    {
                    match(input,ScriptString,FOLLOW_ScriptString_in_importStatement1107); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1113);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportScript(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1116); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:351:4: EngineString ns= dottedIdentifier2 SEMI
                    {
                    match(input,EngineString,FOLLOW_EngineString_in_importStatement1121); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1127);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportEngine(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1130); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:354:1: declaration returns [TextMarkerStatement stmt = null] : ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
    public final TextMarkerStatement declaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token id=null;
        Token newName=null;
        Token obj2=null;
        Token obj3=null;
        Token obj6=null;
        Token obj4=null;
        Token obj5=null;
        Token fname=null;
        Token lazyParent =null;

        Token type =null;

        Token obj1 =null;



        List featureTypes = new ArrayList();
        List featureNames = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:359:2: ( ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:361:2: DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1154); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:363:13: (lazyParent= annotationType )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==BasicAnnotationType) ) {
                        alt26=1;
                    }
                    else if ( (LA26_0==Identifier) ) {
                        int LA26_2 = input.LA(2);

                        if ( (LA26_2==DOT||LA26_2==Identifier) ) {
                            alt26=1;
                        }
                    }
                    switch (alt26) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:363:13: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1164);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1172); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:365:4: ( COMMA id= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:365:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1179); if (state.failed) return stmt;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1193); if (state.failed) return stmt;

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1202); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:370:2: DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1209); if (state.failed) return stmt;

                    pushFollow(FOLLOW_annotationType_in_declaration1215);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmt;

                    newName=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1221); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:371:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:371:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1227); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:372:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString )
                    int alt28=6;
                    switch ( input.LA(1) ) {
                    case BasicAnnotationType:
                    case Identifier:
                        {
                        alt28=1;
                        }
                        break;
                    case StringString:
                        {
                        alt28=2;
                        }
                        break;
                    case DoubleString:
                        {
                        alt28=3;
                        }
                        break;
                    case FloatString:
                        {
                        alt28=4;
                        }
                        break;
                    case IntString:
                        {
                        alt28=5;
                        }
                        break;
                    case BooleanString:
                        {
                        alt28=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;

                    }

                    switch (alt28) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:373:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1242);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:374:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1255); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:375:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1268); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:376:6: obj6= FloatString
                            {
                            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1280); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj6.getText());}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:377:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1292); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                            }
                            break;
                        case 6 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:378:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1304); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1320); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:381:4: ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:382:4: COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1332); if (state.failed) return stmt;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:383:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString )
                    	    int alt29=6;
                    	    switch ( input.LA(1) ) {
                    	    case BasicAnnotationType:
                    	    case Identifier:
                    	        {
                    	        alt29=1;
                    	        }
                    	        break;
                    	    case StringString:
                    	        {
                    	        alt29=2;
                    	        }
                    	        break;
                    	    case DoubleString:
                    	        {
                    	        alt29=3;
                    	        }
                    	        break;
                    	    case FloatString:
                    	        {
                    	        alt29=4;
                    	        }
                    	        break;
                    	    case IntString:
                    	        {
                    	        alt29=5;
                    	        }
                    	        break;
                    	    case BooleanString:
                    	        {
                    	        alt29=6;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 29, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt29) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:384:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1347);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:385:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1360); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:386:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1373); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:387:6: obj6= FloatString
                    	            {
                    	            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1385); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj6.getText());}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:388:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1397); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                    	            }
                    	            break;
                    	        case 6 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:389:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1409); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                    	            }
                    	            break;

                    	    }


                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1425); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1433); if (state.failed) return stmt;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1436); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:398:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:415:2: (type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:416:2: type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1494); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1498); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1505); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1509); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createScriptBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);}

            if ( state.backtracking==0 ) {((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	container = new RuleElementIsolator();}

            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1522);
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

            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1533); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_blockDeclaration1539);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1541); if (state.failed) return block;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:437:1: automataDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:456:2: (type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:458:2: type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_automataDeclaration1593); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_automataDeclaration1597); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_automataDeclaration1604); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_automataDeclaration1608); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createAutomataBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);}

            if ( state.backtracking==0 ) {((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	container = new RuleElementIsolator();}

            pushFollow(FOLLOW_ruleElementWithCA_in_automataDeclaration1621);
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

            match(input,LCURLY,FOLLOW_LCURLY_in_automataDeclaration1630); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_automataDeclaration1636);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            match(input,RCURLY,FOLLOW_RCURLY_in_automataDeclaration1638); if (state.failed) return block;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:480:1: ruleElementWithCA[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression idRef =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:481:5: (idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:483:5: idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1675);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(idRef,null,null,null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:485:7: (q= quantifierPart )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LBRACK||LA32_0==PLUS||LA32_0==QUESTION||LA32_0==STAR) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:485:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1692);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1704); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:486:18: (c= conditions )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==AFTER||LA33_0==AND||LA33_0==BEFORE||(LA33_0 >= CONTAINS && LA33_0 <= CONTEXTCOUNT)||LA33_0==COUNT||LA33_0==CURRENTCOUNT||LA33_0==ENDSWITH||LA33_0==FEATURE||(LA33_0 >= IF && LA33_0 <= INLIST)||(LA33_0 >= IS && LA33_0 <= Identifier)||LA33_0==LAST||(LA33_0 >= MINUS && LA33_0 <= NOT)||LA33_0==OR||(LA33_0 >= PARSE && LA33_0 <= PARTOFNEQ)||LA33_0==POSITION||LA33_0==REGEXP||LA33_0==SCORE||LA33_0==SIZE||LA33_0==STARTSWITH||LA33_0==TOTALCOUNT||LA33_0==VOTE) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:486:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1710);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:486:32: ( THEN a= actions )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==THEN) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:486:33: THEN a= actions
                    {
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1714); if (state.failed) return re;

                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1720);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1724); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:502:1: simpleStatement returns [TextMarkerRule stmt = null] :elements= ruleElements[stmt.getRoot()] SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;


        List<RuleElement> elements =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:503:2: (elements= ruleElements[stmt.getRoot()] SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:504:2: elements= ruleElements[stmt.getRoot()] SEMI
            {
            if ( state.backtracking==0 ) {stmt = factory.createRule(elements, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            pushFollow(FOLLOW_ruleElements_in_simpleStatement1765);
            elements=ruleElements(stmt.getRoot());

            state._fsp--;
            if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1768); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:509:1: ruleElements[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()] : re= ruleElement[container] (re= ruleElement[container] )* ;
    public final List<RuleElement> ruleElements(RuleElementContainer container) throws RecognitionException {
        List<RuleElement> elements =  new ArrayList<RuleElement>();


        RuleElement re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:510:2: (re= ruleElement[container] (re= ruleElement[container] )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:511:2: re= ruleElement[container] (re= ruleElement[container] )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1795);
            re=ruleElement(container);

            state._fsp--;
            if (state.failed) return elements;

            if ( state.backtracking==0 ) {elements.add(re);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:511:50: (re= ruleElement[container] )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==BasicAnnotationType||LA35_0==Identifier||LA35_0==LPAREN||LA35_0==StringLiteral) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:511:51: re= ruleElement[container]
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1805);
            	    re=ruleElement(container);

            	    state._fsp--;
            	    if (state.failed) return elements;

            	    if ( state.backtracking==0 ) {elements.add(re);}

            	    }
            	    break;

            	default :
            	    break loop35;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:515:1: ruleElement[RuleElementContainer container] returns [RuleElement re = null] : (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] );
    public final RuleElement ruleElement(RuleElementContainer container) throws RecognitionException {
        RuleElement re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        ComposedRuleElement re3 =null;

        TextMarkerRuleElement re4 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:516:2: (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] )
            int alt36=4;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA36_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))))) ) {
                    alt36=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    alt36=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt36=1;
                }
                break;
            case StringLiteral:
                {
                alt36=2;
                }
                break;
            case LPAREN:
                {
                int LA36_4 = input.LA(2);

                if ( (synpred1_TextMarkerParser()) ) {
                    alt36=3;
                }
                else if ( (synpred2_TextMarkerParser()) ) {
                    alt36=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 4, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }

            switch (alt36) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:517:2: re1= ruleElementType[container]
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1834);
                    re1=ruleElementType(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:518:4: re2= ruleElementLiteral[container]
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1846);
                    re2=ruleElementLiteral(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:519:4: ( ruleElementComposed[null] )=>re3= ruleElementComposed[container]
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1863);
                    re3=ruleElementComposed(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:520:4: ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container]
                    {
                    pushFollow(FOLLOW_ruleElementDisjunctive_in_ruleElement1881);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:523:1: ruleElementDisjunctive[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:527:5: ( LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:528:5: LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementDisjunctive1913); if (state.failed) return re;

            pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1929);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {typeExprs.add(type1);}

            match(input,VBAR,FOLLOW_VBAR_in_ruleElementDisjunctive1938); if (state.failed) return re;

            pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1944);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {typeExprs.add(type2);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:5: ( VBAR type3= typeExpression )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==VBAR) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:531:6: VBAR type3= typeExpression
                    {
                    match(input,VBAR,FOLLOW_VBAR_in_ruleElementDisjunctive1953); if (state.failed) return re;

                    pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1959);
                    type3=typeExpression();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {typeExprs.add(type3);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementDisjunctive1968); if (state.failed) return re;

            if ( state.backtracking==0 ) { re = factory.createRuleElement(typeExprs, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:535:8: (q= quantifierPart )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LBRACK||LA38_0==PLUS||LA38_0==QUESTION||LA38_0==STAR) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:535:8: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementDisjunctive1994);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LCURLY) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementDisjunctive2007); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:19: (c= conditions )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==AFTER||LA39_0==AND||LA39_0==BEFORE||(LA39_0 >= CONTAINS && LA39_0 <= CONTEXTCOUNT)||LA39_0==COUNT||LA39_0==CURRENTCOUNT||LA39_0==ENDSWITH||LA39_0==FEATURE||(LA39_0 >= IF && LA39_0 <= INLIST)||(LA39_0 >= IS && LA39_0 <= Identifier)||LA39_0==LAST||(LA39_0 >= MINUS && LA39_0 <= NOT)||LA39_0==OR||(LA39_0 >= PARSE && LA39_0 <= PARTOFNEQ)||LA39_0==POSITION||LA39_0==REGEXP||LA39_0==SCORE||LA39_0==SIZE||LA39_0==STARTSWITH||LA39_0==TOTALCOUNT||LA39_0==VOTE) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementDisjunctive2013);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:33: ( THEN a= actions )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==THEN) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementDisjunctive2017); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementDisjunctive2023);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementDisjunctive2027); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:1: ruleElementComposed[RuleElementContainer container] returns [ComposedRuleElement re = null] : LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final ComposedRuleElement ruleElementComposed(RuleElementContainer container) throws RecognitionException {
        ruleElementComposed_stack.push(new ruleElementComposed_scope());
        ComposedRuleElement re =  null;


        List<RuleElement> res =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;





        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:557:2: ( LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:558:2: LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            if ( state.backtracking==0 ) {re = factory.createComposedRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	// dre = factory.createDisjunctiveRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con = re;}

            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed2068); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:563:2: ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:569:2: ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con]
            {
            pushFollow(FOLLOW_ruleElements_in_ruleElementComposed2096);
            res=ruleElements(((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con);

            state._fsp--;
            if (state.failed) return re;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed2106); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:11: (q= quantifierPart )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LBRACK||LA42_0==PLUS||LA42_0==QUESTION||LA42_0==STAR) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:11: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed2112);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:29: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LCURLY) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:30: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed2116); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:39: (c= conditions )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==AFTER||LA43_0==AND||LA43_0==BEFORE||(LA43_0 >= CONTAINS && LA43_0 <= CONTEXTCOUNT)||LA43_0==COUNT||LA43_0==CURRENTCOUNT||LA43_0==ENDSWITH||LA43_0==FEATURE||(LA43_0 >= IF && LA43_0 <= INLIST)||(LA43_0 >= IS && LA43_0 <= Identifier)||LA43_0==LAST||(LA43_0 >= MINUS && LA43_0 <= NOT)||LA43_0==OR||(LA43_0 >= PARSE && LA43_0 <= PARTOFNEQ)||LA43_0==POSITION||LA43_0==REGEXP||LA43_0==SCORE||LA43_0==SIZE||LA43_0==STARTSWITH||LA43_0==TOTALCOUNT||LA43_0==VOTE) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:39: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed2122);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:53: ( THEN a= actions )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==THEN) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:54: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementComposed2126); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementComposed2132);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementComposed2136); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:588:1: ruleElementType[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression typeExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:5: ( ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:591:5: ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2179);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(typeExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:7: (q= quantifierPart )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LBRACK||LA46_0==PLUS||LA46_0==QUESTION||LA46_0==STAR) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2198);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:594:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LCURLY) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:594:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2211); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:594:19: (c= conditions )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==AFTER||LA47_0==AND||LA47_0==BEFORE||(LA47_0 >= CONTAINS && LA47_0 <= CONTEXTCOUNT)||LA47_0==COUNT||LA47_0==CURRENTCOUNT||LA47_0==ENDSWITH||LA47_0==FEATURE||(LA47_0 >= IF && LA47_0 <= INLIST)||(LA47_0 >= IS && LA47_0 <= Identifier)||LA47_0==LAST||(LA47_0 >= MINUS && LA47_0 <= NOT)||LA47_0==OR||(LA47_0 >= PARSE && LA47_0 <= PARTOFNEQ)||LA47_0==POSITION||LA47_0==REGEXP||LA47_0==SCORE||LA47_0==SIZE||LA47_0==STARTSWITH||LA47_0==TOTALCOUNT||LA47_0==VOTE) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:594:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2217);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:594:33: ( THEN a= actions )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==THEN) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:594:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2221); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementType2227);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2231); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:608:1: ruleElementLiteral[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        StringExpression stringExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:609:5: ( ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:611:5: ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2278);
            stringExpr=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(stringExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:614:7: (q= quantifierPart )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LBRACK||LA50_0==PLUS||LA50_0==QUESTION||LA50_0==STAR) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:614:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2302);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:615:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LCURLY) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:615:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2315); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:615:19: (c= conditions )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==AFTER||LA51_0==AND||LA51_0==BEFORE||(LA51_0 >= CONTAINS && LA51_0 <= CONTEXTCOUNT)||LA51_0==COUNT||LA51_0==CURRENTCOUNT||LA51_0==ENDSWITH||LA51_0==FEATURE||(LA51_0 >= IF && LA51_0 <= INLIST)||(LA51_0 >= IS && LA51_0 <= Identifier)||LA51_0==LAST||(LA51_0 >= MINUS && LA51_0 <= NOT)||LA51_0==OR||(LA51_0 >= PARSE && LA51_0 <= PARTOFNEQ)||LA51_0==POSITION||LA51_0==REGEXP||LA51_0==SCORE||LA51_0==SIZE||LA51_0==STARTSWITH||LA51_0==TOTALCOUNT||LA51_0==VOTE) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:615:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2321);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:615:33: ( THEN a= actions )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==THEN) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:615:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2325); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2331);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2335); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:629:1: conditions returns [List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<AbstractTextMarkerCondition> conditions() throws RecognitionException {
        List<AbstractTextMarkerCondition> conds =  new ArrayList<AbstractTextMarkerCondition>();


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:630:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2373);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;

            if ( state.backtracking==0 ) {conds.add(c);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:35: ( COMMA c= condition )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==COMMA) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2378); if (state.failed) return conds;

            	    pushFollow(FOLLOW_condition_in_conditions2384);
            	    c=condition();

            	    state._fsp--;
            	    if (state.failed) return conds;

            	    if ( state.backtracking==0 ) {conds.add(c);}

            	    }
            	    break;

            	default :
            	    break loop54;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:634:1: actions returns [List<AbstractTextMarkerAction> actions = new ArrayList<AbstractTextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<AbstractTextMarkerAction> actions() throws RecognitionException {
        List<AbstractTextMarkerAction> actions =  new ArrayList<AbstractTextMarkerAction>();


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:635:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2422);
            a=action();

            state._fsp--;
            if (state.failed) return actions;

            if ( state.backtracking==0 ) {actions.add(a);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:34: ( COMMA a= action )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==COMMA) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2427); if (state.failed) return actions;

            	    pushFollow(FOLLOW_action_in_actions2433);
            	    a=action();

            	    state._fsp--;
            	    if (state.failed) return actions;

            	    if ( state.backtracking==0 ) {actions.add(a);}

            	    }
            	    break;

            	default :
            	    break loop55;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:640:1: listExpression returns [ListExpression expr = null] : ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( floatListExpression )=>dl= floatListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression );
    public final ListExpression listExpression() throws RecognitionException {
        ListExpression expr =  null;


        BooleanListExpression bl =null;

        NumberListExpression il =null;

        NumberListExpression dl =null;

        StringListExpression sl =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:641:2: ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( floatListExpression )=>dl= floatListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression )
            int alt56=6;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==LCURLY) ) {
                int LA56_1 = input.LA(2);

                if ( (synpred7_TextMarkerParser()) ) {
                    alt56=1;
                }
                else if ( (synpred8_TextMarkerParser()) ) {
                    alt56=2;
                }
                else if ( (synpred9_TextMarkerParser()) ) {
                    alt56=3;
                }
                else if ( (synpred10_TextMarkerParser()) ) {
                    alt56=4;
                }
                else if ( (synpred11_TextMarkerParser()) ) {
                    alt56=5;
                }
                else if ( (synpred12_TextMarkerParser()) ) {
                    alt56=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA56_0==Identifier) ) {
                int LA56_2 = input.LA(2);

                if ( (((synpred7_TextMarkerParser()&&synpred7_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt56=1;
                }
                else if ( ((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))&&synpred8_TextMarkerParser())) ) {
                    alt56=2;
                }
                else if ( (((synpred9_TextMarkerParser()&&synpred9_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST")))) ) {
                    alt56=3;
                }
                else if ( (((synpred10_TextMarkerParser()&&synpred10_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST")))) ) {
                    alt56=4;
                }
                else if ( (((synpred11_TextMarkerParser()&&synpred11_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt56=5;
                }
                else if ( ((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST")))&&synpred12_TextMarkerParser())) ) {
                    alt56=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }
            switch (alt56) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:642:2: ( booleanListExpression )=>bl= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2469);
                    bl=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:643:4: ( intListExpression )=>il= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2485);
                    il=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = il;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:644:4: ( doubleListExpression )=>dl= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2501);
                    dl=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = dl;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:645:4: ( floatListExpression )=>dl= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_listExpression2517);
                    dl=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = dl;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:646:4: ( stringListExpression )=>sl= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2533);
                    sl=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = sl;}

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:647:4: ( typeListExpression )=>tl= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2549);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:650:1: booleanListExpression returns [BooleanListExpression expr = null] : e= simpleBooleanListExpression ;
    public final BooleanListExpression booleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        BooleanListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:651:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:652:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2571);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:655:1: simpleBooleanListExpression returns [BooleanListExpression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier );
    public final BooleanListExpression simpleBooleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        Token var=null;
        BooleanExpression e =null;



        	List<BooleanExpression> list = new ArrayList<BooleanExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:658:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:659:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2592); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:659:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==FALSE||LA58_0==Identifier||LA58_0==TRUE) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:659:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2599);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:659:53: ( COMMA e= simpleBooleanExpression )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==COMMA) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:659:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2604); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2610);
                            	    e=simpleBooleanExpression();

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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2619); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:662:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEANLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2634); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:667:1: intListExpression returns [NumberListExpression expr = null] : e= simpleIntListExpression ;
    public final NumberListExpression intListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:668:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:669:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2659);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:672:1: simpleIntListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleIntListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:675:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==LCURLY) ) {
                alt62=1;
            }
            else if ( (LA62_0==Identifier) ) {
                alt62=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:676:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2680); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:676:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==DecimalLiteral||LA61_0==FloatingPointLiteral||LA61_0==Identifier||LA61_0==LPAREN||LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:676:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2687);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:676:52: ( COMMA e= simpleNumberExpression )*
                            loop60:
                            do {
                                int alt60=2;
                                int LA60_0 = input.LA(1);

                                if ( (LA60_0==COMMA) ) {
                                    alt60=1;
                                }


                                switch (alt60) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:676:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2692); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2698);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop60;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2707); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:679:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INTLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2722); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:684:1: numberListExpression returns [NumberListExpression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression | (e1= floatListExpression )=>e1= floatListExpression |e2= intListExpression );
    public final NumberListExpression numberListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e1 =null;

        NumberListExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:685:2: ( (e1= doubleListExpression )=>e1= doubleListExpression | (e1= floatListExpression )=>e1= floatListExpression |e2= intListExpression )
            int alt63=3;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==LCURLY) ) {
                int LA63_1 = input.LA(2);

                if ( (synpred13_TextMarkerParser()) ) {
                    alt63=1;
                }
                else if ( (synpred14_TextMarkerParser()) ) {
                    alt63=2;
                }
                else if ( (true) ) {
                    alt63=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA63_0==Identifier) ) {
                int LA63_2 = input.LA(2);

                if ( ((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST")))&&synpred13_TextMarkerParser())) ) {
                    alt63=1;
                }
                else if ( (((synpred14_TextMarkerParser()&&synpred14_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST")))) ) {
                    alt63=2;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                    alt63=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:686:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2756);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:688:2: (e1= floatListExpression )=>e1= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_numberListExpression2777);
                    e1=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:690:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2789);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:693:1: doubleListExpression returns [NumberListExpression expr = null] : e= simpleDoubleListExpression ;
    public final NumberListExpression doubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:695:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2812);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:698:1: simpleDoubleListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleDoubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:701:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2833); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==DecimalLiteral||LA65_0==FloatingPointLiteral||LA65_0==Identifier||LA65_0==LPAREN||LA65_0==MINUS) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2840);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:52: ( COMMA e= simpleNumberExpression )*
                            loop64:
                            do {
                                int alt64=2;
                                int LA64_0 = input.LA(1);

                                if ( (LA64_0==COMMA) ) {
                                    alt64=1;
                                }


                                switch (alt64) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2845); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2851);
                            	    e=simpleNumberExpression();

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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2860); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:705:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2875); if (state.failed) return expr;

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



    // $ANTLR start "floatListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:710:1: floatListExpression returns [NumberListExpression expr = null] : e= simpleFloatListExpression ;
    public final NumberListExpression floatListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:2: (e= simpleFloatListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:712:2: e= simpleFloatListExpression
            {
            pushFollow(FOLLOW_simpleFloatListExpression_in_floatListExpression2901);
            e=simpleFloatListExpression();

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
    // $ANTLR end "floatListExpression"



    // $ANTLR start "simpleFloatListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:715:1: simpleFloatListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleFloatListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:718:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleFloatListExpression2922); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==DecimalLiteral||LA68_0==FloatingPointLiteral||LA68_0==Identifier||LA68_0==LPAREN||LA68_0==MINUS) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2929);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:52: ( COMMA e= simpleNumberExpression )*
                            loop67:
                            do {
                                int alt67=2;
                                int LA67_0 = input.LA(1);

                                if ( (LA67_0==COMMA) ) {
                                    alt67=1;
                                }


                                switch (alt67) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleFloatListExpression2934); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2940);
                            	    e=simpleNumberExpression();

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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleFloatListExpression2949); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:722:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleFloatListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"FLOATLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleFloatListExpression2964); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceFloatListExpression(var);}

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
    // $ANTLR end "simpleFloatListExpression"



    // $ANTLR start "stringListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:726:1: stringListExpression returns [StringListExpression expr = null] : e= simpleStringListExpression ;
    public final StringListExpression stringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        StringListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:727:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:728:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2988);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:731:1: simpleStringListExpression returns [StringListExpression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier );
    public final StringListExpression simpleStringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        Token var=null;
        StringExpression e =null;



        	List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:734:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==LCURLY) ) {
                alt72=1;
            }
            else if ( (LA72_0==Identifier) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;

            }
            switch (alt72) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:735:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression3009); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:735:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==Identifier||LA71_0==StringLiteral) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:735:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression3016);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:735:52: ( COMMA e= simpleStringExpression )*
                            loop70:
                            do {
                                int alt70=2;
                                int LA70_0 = input.LA(1);

                                if ( (LA70_0==COMMA) ) {
                                    alt70=1;
                                }


                                switch (alt70) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:735:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression3021); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression3027);
                            	    e=simpleStringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop70;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression3036); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:738:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRINGLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression3052); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:743:1: typeListExpression returns [TypeListExpression expr = null] : e= simpleTypeListExpression ;
    public final TypeListExpression typeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        TypeListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:744:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:745:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression3077);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:748:1: simpleTypeListExpression returns [TypeListExpression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier );
    public final TypeListExpression simpleTypeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        Token var=null;
        TypeExpression e =null;



        	List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:751:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==LCURLY) ) {
                alt75=1;
            }
            else if ( (LA75_0==Identifier) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;

            }
            switch (alt75) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:752:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression3098); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:752:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==BasicAnnotationType||LA74_0==Identifier) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:752:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3105);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:752:50: ( COMMA e= simpleTypeExpression )*
                            loop73:
                            do {
                                int alt73=2;
                                int LA73_0 = input.LA(1);

                                if ( (LA73_0==COMMA) ) {
                                    alt73=1;
                                }


                                switch (alt73) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:752:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression3110); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3116);
                            	    e=simpleTypeExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop73;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression3125); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTypeListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:755:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression3140); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:760:1: typeExpression returns [TypeExpression type = null] options {backtrack=true; } : (tf= typeFunction |st= simpleTypeExpression );
    public final TypeExpression typeExpression() throws RecognitionException {
        TypeExpression type =  null;


        TypeExpression tf =null;

        TypeExpression st =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:764:2: (tf= typeFunction |st= simpleTypeExpression )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==Identifier) ) {
                int LA76_1 = input.LA(2);

                if ( (synpred15_TextMarkerParser()) ) {
                    alt76=1;
                }
                else if ( (true) ) {
                    alt76=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return type;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 76, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA76_0==BasicAnnotationType) ) {
                alt76=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;

            }
            switch (alt76) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:765:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression3177);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:766:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression3188);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:771:1: typeFunction returns [TypeExpression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final TypeExpression typeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        TypeExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:772:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:773:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction3222);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:777:1: externalTypeFunction returns [TypeExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TypeExpression externalTypeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:778:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:780:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction3247); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction3249); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction3256);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction3258); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:787:1: simpleTypeExpression returns [TypeExpression type = null] : ({...}?var= Identifier |at= annotationType );
    public final TypeExpression simpleTypeExpression() throws RecognitionException {
        TypeExpression type =  null;


        Token var=null;
        Token at =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:788:2: ({...}?var= Identifier |at= annotationType )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==Identifier) ) {
                int LA77_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                    alt77=1;
                }
                else if ( (true) ) {
                    alt77=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return type;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 77, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA77_0==BasicAnnotationType) ) {
                alt77=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;

            }
            switch (alt77) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:789:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "simpleTypeExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPE\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeExpression3283); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = ExpressionFactory.createReferenceTypeExpression(var);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:792:2: at= annotationType
                    {
                    pushFollow(FOLLOW_annotationType_in_simpleTypeExpression3297);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:1: variable returns [Token var = null] :{...}?v= Identifier ;
    public final Token variable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:798:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:799:2: {...}?v= Identifier
            {
            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "isVariable($blockDeclaration::env, input.LT(1).getText())");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3323); if (state.failed) return var;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:802:1: listVariable returns [Token var = null] :{...}?v= Identifier ;
    public final Token listVariable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:803:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:804:2: {...}?v= Identifier
            {
            if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEANLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INTLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLELIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "FLOATLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRINGLIST")
            	||isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPELIST")
            	)) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "listVariable", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEANLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INTLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLELIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"FLOATLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRINGLIST\")\r\n\t||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPELIST\")\r\n\t");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3347); if (state.failed) return var;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:1: quantifierPart returns [RuleElementQuantifier quantifier = null] : ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? );
    public final RuleElementQuantifier quantifierPart() throws RecognitionException {
        RuleElementQuantifier quantifier =  null;


        Token q=null;
        Token comma=null;
        NumberExpression min =null;

        NumberExpression max =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:829:2: ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
            int alt84=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt84=1;
                }
                break;
            case PLUS:
                {
                alt84=2;
                }
                break;
            case QUESTION:
                {
                alt84=3;
                }
                break;
            case LBRACK:
                {
                alt84=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return quantifier;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }

            switch (alt84) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:830:3: STAR (q= QUESTION )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_quantifierPart3381); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:830:10: (q= QUESTION )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==QUESTION) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:830:10: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3387); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createStarReluctantQuantifier();} 
                    	 	else{quantifier = TextMarkerScriptFactory.createStarGreedyQuantifier();}}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:833:4: PLUS (q= QUESTION )?
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3398); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:833:11: (q= QUESTION )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==QUESTION) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:833:11: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3404); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createPlusReluctantQuantifier();}
                    	 else {quantifier = TextMarkerScriptFactory.createPlusGreedyQuantifier();}}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:836:4: QUESTION (q= QUESTION )?
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3414); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:836:15: (q= QUESTION )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==QUESTION) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:836:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3420); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createQuestionReluctantQuantifier();} 
                    	 else {quantifier = TextMarkerScriptFactory.createQuestionGreedyQuantifier();}}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:4: LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3431); if (state.failed) return quantifier;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3437);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:34: (comma= COMMA (max= numberExpression )? )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==COMMA) ) {
                        alt82=1;
                    }
                    switch (alt82) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:35: comma= COMMA (max= numberExpression )?
                            {
                            comma=(Token)match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3444); if (state.failed) return quantifier;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:49: (max= numberExpression )?
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==COS||LA81_0==DecimalLiteral||LA81_0==EXP||LA81_0==FloatingPointLiteral||LA81_0==Identifier||(LA81_0 >= LOGN && LA81_0 <= LPAREN)||LA81_0==MINUS||LA81_0==SIN||LA81_0==TAN) ) {
                                alt81=1;
                            }
                            switch (alt81) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:50: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3451);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return quantifier;

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3457); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:86: (q= QUESTION )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==QUESTION) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:86: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3463); if (state.failed) return quantifier;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:845:1: condition returns [AbstractTextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) ;
    public final AbstractTextMarkerCondition condition() throws RecognitionException {
        AbstractTextMarkerCondition result =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:846:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:847:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:847:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            int alt85=30;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt85=1;
                }
                break;
            case CONTAINS:
                {
                alt85=2;
                }
                break;
            case CONTEXTCOUNT:
                {
                alt85=3;
                }
                break;
            case COUNT:
                {
                alt85=4;
                }
                break;
            case CURRENTCOUNT:
                {
                alt85=5;
                }
                break;
            case INLIST:
                {
                alt85=6;
                }
                break;
            case ISINTAG:
                {
                alt85=7;
                }
                break;
            case LAST:
                {
                alt85=8;
                }
                break;
            case MOFN:
                {
                alt85=9;
                }
                break;
            case NEAR:
                {
                alt85=10;
                }
                break;
            case MINUS:
            case NOT:
                {
                alt85=11;
                }
                break;
            case OR:
                {
                alt85=12;
                }
                break;
            case PARTOF:
                {
                alt85=13;
                }
                break;
            case POSITION:
                {
                alt85=14;
                }
                break;
            case REGEXP:
                {
                alt85=15;
                }
                break;
            case SCORE:
                {
                alt85=16;
                }
                break;
            case TOTALCOUNT:
                {
                alt85=17;
                }
                break;
            case VOTE:
                {
                alt85=18;
                }
                break;
            case IF:
                {
                alt85=19;
                }
                break;
            case FEATURE:
                {
                alt85=20;
                }
                break;
            case PARSE:
                {
                alt85=21;
                }
                break;
            case IS:
                {
                alt85=22;
                }
                break;
            case BEFORE:
                {
                alt85=23;
                }
                break;
            case AFTER:
                {
                alt85=24;
                }
                break;
            case STARTSWITH:
                {
                alt85=25;
                }
                break;
            case ENDSWITH:
                {
                alt85=26;
                }
                break;
            case PARTOFNEQ:
                {
                alt85=27;
                }
                break;
            case SIZE:
                {
                alt85=28;
                }
                break;
            case Identifier:
                {
                int LA85_29 = input.LA(2);

                if ( (LA85_29==LPAREN) && (synpred17_TextMarkerParser())) {
                    alt85=29;
                }
                else if ( (LA85_29==COMMA||LA85_29==RCURLY||LA85_29==RPAREN||LA85_29==THEN) ) {
                    alt85=30;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 85, 29, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;

            }

            switch (alt85) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:848:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3494);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:849:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3503);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:850:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3512);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:851:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3521);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:852:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3530);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:853:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3539);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:854:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3548);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:855:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3557);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:856:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3566);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:857:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3575);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:858:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3584);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:859:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3593);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:860:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3602);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:861:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3611);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:862:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3620);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:863:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3629);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:864:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3638);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:865:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3647);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:866:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3656);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:867:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3665);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:868:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3674);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:869:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3683);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:870:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3692);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:871:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3701);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:872:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3711);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:873:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3720);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:874:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3729);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:875:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3738);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3757);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:877:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3766);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:882:1: variableCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier ;
    public final AbstractTextMarkerCondition variableCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:883:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3796); if (state.failed) return condition;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:891:1: externalCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerCondition externalCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:892:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:894:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3823); if (state.failed) return condition;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3825); if (state.failed) return condition;

            pushFollow(FOLLOW_varArgumentList_in_externalCondition3831);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3833); if (state.failed) return condition;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:900:1: conditionAnd returns [AbstractTextMarkerCondition cond = null] : AND LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionAnd() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:5: ( AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:5: AND LPAREN conds= conditions RPAREN
            {
            match(input,AND,FOLLOW_AND_in_conditionAnd3862); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3864); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionAnd3870);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3872); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:906:1: conditionContains returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContains() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        ListExpression list =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression percent =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:5: ( CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:911:5: CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3924); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3926); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:911:21: (type= typeExpression |list= listExpression COMMA a= argument )
            int alt86=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA86_1 = input.LA(2);

                if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST")))))) ) {
                    alt86=1;
                }
                else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt86=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt86=1;
                }
                break;
            case LCURLY:
                {
                alt86=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;

            }

            switch (alt86) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:911:22: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3933);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:911:46: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3941);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3943); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionContains3949);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COMMA) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3958); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3964);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3966); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3972);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:64: ( COMMA percent= booleanExpression )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COMMA) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:912:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3975); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3981);
                            percent=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3987); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:1: conditionContextCount returns [AbstractTextMarkerCondition cond = null] : CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContextCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:917:5: ( CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:918:5: CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount4020); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount4022); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount4028);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:918:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==COMMA) ) {
                int LA89_1 = input.LA(2);

                if ( (LA89_1==COS||LA89_1==DecimalLiteral||LA89_1==EXP||LA89_1==FloatingPointLiteral||(LA89_1 >= LOGN && LA89_1 <= LPAREN)||LA89_1==MINUS||LA89_1==SIN||LA89_1==TAN) ) {
                    alt89=1;
                }
                else if ( (LA89_1==Identifier) ) {
                    int LA89_4 = input.LA(3);

                    if ( (LA89_4==COMMA||LA89_4==LPAREN||LA89_4==MINUS||(LA89_4 >= PERCENT && LA89_4 <= PLUS)||(LA89_4 >= SLASH && LA89_4 <= STAR)) ) {
                        alt89=1;
                    }
                }
            }
            switch (alt89) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:918:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4031); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount4037);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4039); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount4045);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:919:5: ( COMMA var= numberVariable )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==COMMA) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:919:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4055); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount4061);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount4065); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:922:1: conditionCount returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final AbstractTextMarkerCondition conditionCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression type =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;

        TypeExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:926:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==COUNT) ) {
                int LA95_1 = input.LA(2);

                if ( (synpred18_TextMarkerParser()) ) {
                    alt95=1;
                }
                else if ( (true) ) {
                    alt95=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 95, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;

            }
            switch (alt95) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount4111); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4113); if (state.failed) return cond;

                    pushFollow(FOLLOW_listExpression_in_conditionCount4119);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount4121); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionCount4127);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt91=2;
                    int LA91_0 = input.LA(1);

                    if ( (LA91_0==COMMA) ) {
                        int LA91_1 = input.LA(2);

                        if ( (LA91_1==COS||LA91_1==DecimalLiteral||LA91_1==EXP||LA91_1==FloatingPointLiteral||(LA91_1 >= LOGN && LA91_1 <= LPAREN)||LA91_1==MINUS||LA91_1==SIN||LA91_1==TAN) ) {
                            alt91=1;
                        }
                        else if ( (LA91_1==Identifier) ) {
                            int LA91_4 = input.LA(3);

                            if ( (LA91_4==COMMA||LA91_4==LPAREN||LA91_4==MINUS||(LA91_4 >= PERCENT && LA91_4 <= PLUS)||(LA91_4 >= SLASH && LA91_4 <= STAR)) ) {
                                alt91=1;
                            }
                        }
                    }
                    switch (alt91) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:60: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4130); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4136);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4138); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4144);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:928:5: ( COMMA var= numberVariable )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);

                    if ( (LA92_0==COMMA) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:928:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4154); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4160);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4164); if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionCount(type, a, min, max, var,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:931:5: COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount4182); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4184); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount4190);
                    list=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:931:40: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt93=2;
                    int LA93_0 = input.LA(1);

                    if ( (LA93_0==COMMA) ) {
                        int LA93_1 = input.LA(2);

                        if ( (LA93_1==COS||LA93_1==DecimalLiteral||LA93_1==EXP||LA93_1==FloatingPointLiteral||(LA93_1 >= LOGN && LA93_1 <= LPAREN)||LA93_1==MINUS||LA93_1==SIN||LA93_1==TAN) ) {
                            alt93=1;
                        }
                        else if ( (LA93_1==Identifier) ) {
                            int LA93_4 = input.LA(3);

                            if ( (LA93_4==COMMA||LA93_4==LPAREN||LA93_4==MINUS||(LA93_4 >= PERCENT && LA93_4 <= PLUS)||(LA93_4 >= SLASH && LA93_4 <= STAR)) ) {
                                alt93=1;
                            }
                        }
                    }
                    switch (alt93) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:931:41: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4193); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4199);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4201); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4207);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:932:5: ( COMMA var= numberVariable )?
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==COMMA) ) {
                        alt94=1;
                    }
                    switch (alt94) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:932:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4217); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4223);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4227); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:935:1: conditionTotalCount returns [AbstractTextMarkerCondition cond = null] : TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionTotalCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:936:5: ( TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:5: TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4263); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4265); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4271);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:45: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==COMMA) ) {
                int LA96_1 = input.LA(2);

                if ( (LA96_1==COS||LA96_1==DecimalLiteral||LA96_1==EXP||LA96_1==FloatingPointLiteral||(LA96_1 >= LOGN && LA96_1 <= LPAREN)||LA96_1==MINUS||LA96_1==SIN||LA96_1==TAN) ) {
                    alt96=1;
                }
                else if ( (LA96_1==Identifier) ) {
                    int LA96_4 = input.LA(3);

                    if ( (LA96_4==COMMA||LA96_4==LPAREN||LA96_4==MINUS||(LA96_4 >= PERCENT && LA96_4 <= PLUS)||(LA96_4 >= SLASH && LA96_4 <= STAR)) ) {
                        alt96=1;
                    }
                }
            }
            switch (alt96) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:46: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4274); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4280);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4282); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4288);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:938:5: ( COMMA var= numberVariable )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:938:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4297); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4303);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4307); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:1: conditionCurrentCount returns [AbstractTextMarkerCondition cond = null] : CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionCurrentCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:942:5: ( CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:943:5: CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4340); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4342); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4348);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:943:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==COMMA) ) {
                int LA98_1 = input.LA(2);

                if ( (LA98_1==COS||LA98_1==DecimalLiteral||LA98_1==EXP||LA98_1==FloatingPointLiteral||(LA98_1 >= LOGN && LA98_1 <= LPAREN)||LA98_1==MINUS||LA98_1==SIN||LA98_1==TAN) ) {
                    alt98=1;
                }
                else if ( (LA98_1==Identifier) ) {
                    int LA98_4 = input.LA(3);

                    if ( (LA98_4==COMMA||LA98_4==LPAREN||LA98_4==MINUS||(LA98_4 >= PERCENT && LA98_4 <= PLUS)||(LA98_4 >= SLASH && LA98_4 <= STAR)) ) {
                        alt98=1;
                    }
                }
            }
            switch (alt98) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:943:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4351); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4357);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4359); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4365);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:5: ( COMMA var= numberVariable )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==COMMA) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4375); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4381);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4385); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:947:1: conditionInList returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionInList() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringListExpression list2 =null;

        WordListExpression list1 =null;

        NumberExpression dist =null;

        BooleanExpression rel =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:951:5: ( INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:5: INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            match(input,INLIST,FOLLOW_INLIST_in_conditionInList4428); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4430); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:19: ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression )
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==LCURLY) && (synpred19_TextMarkerParser())) {
                alt100=1;
            }
            else if ( (LA100_0==Identifier) ) {
                int LA100_2 = input.LA(2);

                if ( (((synpred19_TextMarkerParser()&&synpred19_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt100=1;
                }
                else if ( (true) ) {
                    alt100=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 100, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA100_0==RessourceLiteral) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:20: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4445);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:83: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4453);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:111: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==COMMA) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:112: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4457); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4463);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:142: ( COMMA rel= booleanExpression )?
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==COMMA) ) {
                        alt101=1;
                    }
                    switch (alt101) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:143: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4466); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4472);
                            rel=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4478); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:1: conditionIsInTag returns [AbstractTextMarkerCondition cond = null] : ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final AbstractTextMarkerCondition conditionIsInTag() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression id =null;

        StringExpression id1 =null;

        StringExpression id2 =null;



        List<StringExpression> list1 = new ArrayList<StringExpression>();
        List<StringExpression> list2 = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:961:5: ( ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:962:5: ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4513); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4515); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4521);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:962:42: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==COMMA) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:962:43: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4524); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4530);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4532); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4538);
            	    id2=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    if ( state.backtracking==0 ) {list1.add(id1);list2.add(id2);}

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4544); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:966:1: conditionLast returns [AbstractTextMarkerCondition cond = null] : LAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionLast() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:967:5: ( LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:968:5: LAST LPAREN type= typeExpression RPAREN
            {
            match(input,LAST,FOLLOW_LAST_in_conditionLast4583); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4585); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionLast4591);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4593); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:973:1: conditionMofN returns [AbstractTextMarkerCondition cond = null] : MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionMofN() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:974:5: ( MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:5: MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4640); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4642); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4648);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4650); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4656);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4658); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionMofN4664);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4666); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:979:1: conditionNear returns [AbstractTextMarkerCondition cond = null] : NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionNear() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression direction =null;

        BooleanExpression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:980:5: ( NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:981:5: NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            match(input,NEAR,FOLLOW_NEAR_in_conditionNear4701); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4703); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionNear4709);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4711); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4717);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4719); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4725);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:981:97: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==COMMA) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:981:98: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4728); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4734);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:981:134: ( COMMA filtered= booleanExpression )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==COMMA) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:981:135: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4737); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4743);
                            filtered=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4749); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:984:1: conditionNot returns [AbstractTextMarkerCondition cond = null] : ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) ;
    public final AbstractTextMarkerCondition conditionNot() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:985:5: ( ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==MINUS) ) {
                alt106=1;
            }
            else if ( (LA106_0==NOT) ) {
                alt106=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 106, 0, input);

                throw nvae;

            }
            switch (alt106) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:6: ( MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:6: ( MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:7: MINUS c= condition
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_conditionNot4785); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4791);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:31: ( NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:31: ( NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:986:32: NOT LPAREN c= condition RPAREN
                    {
                    match(input,NOT,FOLLOW_NOT_in_conditionNot4798); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4800); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4806);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4808); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:989:1: conditionOr returns [AbstractTextMarkerCondition cond = null] : OR LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionOr() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:990:5: ( OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:991:5: OR LPAREN conds= conditions RPAREN
            {
            match(input,OR,FOLLOW_OR_in_conditionOr4847); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4849); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionOr4855);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4857); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:994:1: conditionPartOf returns [AbstractTextMarkerCondition cond = null] : PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:995:5: ( PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:996:5: PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4887); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4889); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:996:19: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:996:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4896);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:996:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4902);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4905); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:999:1: conditionPartOfNeq returns [AbstractTextMarkerCondition cond = null] : PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1000:5: ( PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:5: PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4935); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4937); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:22: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:23: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq4944);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:46: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq4950);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq4953); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1005:1: conditionPosition returns [AbstractTextMarkerCondition cond = null] : POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionPosition() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1006:5: ( POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1007:5: POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            match(input,POSITION,FOLLOW_POSITION_in_conditionPosition4987); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition4989); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionPosition4995);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition4997); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionPosition5003);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition5005); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1010:1: conditionRegExp returns [AbstractTextMarkerCondition cond = null] : REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final AbstractTextMarkerCondition conditionRegExp() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression pattern =null;

        BooleanExpression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1011:5: ( REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1012:5: REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp5035); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp5037); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp5043);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1012:46: ( COMMA caseSensitive= booleanExpression )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==COMMA) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1012:47: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp5046); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp5052);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp5056); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1016:1: conditionScore returns [AbstractTextMarkerCondition cond = null] : SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionScore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1017:5: ( SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1018:5: SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            match(input,SCORE,FOLLOW_SCORE_in_conditionScore5091); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore5093); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore5099);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1018:41: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==COMMA) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1018:42: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore5102); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore5108);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1019:5: ( COMMA var= numberVariable )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==COMMA) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1019:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore5115); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionScore5121);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore5128); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1024:1: conditionVote returns [AbstractTextMarkerCondition cond = null] : VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionVote() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:5: ( VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1026:5: VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            match(input,VOTE,FOLLOW_VOTE_in_conditionVote5163); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote5165); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5171);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionVote5173); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5179);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote5181); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1030:1: conditionIf returns [AbstractTextMarkerCondition cond = null] : IF LPAREN e= booleanExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionIf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:5: ( IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1032:5: IF LPAREN e= booleanExpression RPAREN
            {
            match(input,IF,FOLLOW_IF_in_conditionIf5219); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf5221); if (state.failed) return cond;

            pushFollow(FOLLOW_booleanExpression_in_conditionIf5227);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf5229); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1036:1: conditionFeature returns [AbstractTextMarkerCondition cond = null] : FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerCondition conditionFeature() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression se =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:5: ( FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:5: FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5263); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5265); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionFeature5271);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5273); if (state.failed) return cond;

            pushFollow(FOLLOW_argument_in_conditionFeature5279);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5281); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1042:1: conditionParse returns [AbstractTextMarkerCondition cond = null] : PARSE LPAREN {...}?id= Identifier RPAREN ;
    public final AbstractTextMarkerCondition conditionParse() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:5: ( PARSE LPAREN {...}?id= Identifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1044:5: PARSE LPAREN {...}?id= Identifier RPAREN
            {
            match(input,PARSE,FOLLOW_PARSE_in_conditionParse5315); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5317); if (state.failed) return cond;

            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return cond;}
                throw new FailedPredicateException(input, "conditionParse", "isVariable($blockDeclaration::env,input.LT(1).getText())");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionParse5325); if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5327); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1048:1: conditionIs returns [AbstractTextMarkerCondition cond = null] : IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionIs() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:5: ( IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1050:5: IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,IS,FOLLOW_IS_in_conditionIs5358); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5360); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1050:15: (type1= typeExpression |type2= typeListExpression )
            int alt112=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA112_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt112=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt112=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 112, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt112=1;
                }
                break;
            case LCURLY:
                {
                alt112=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;

            }

            switch (alt112) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1050:16: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5367);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1050:39: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5373);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5376); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1054:1: conditionBefore returns [AbstractTextMarkerCondition cond = null] : BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionBefore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:5: ( BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1056:5: BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5407); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5409); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1056:19: (type1= typeExpression |type2= typeListExpression )
            int alt113=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA113_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt113=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt113=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 113, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt113=1;
                }
                break;
            case LCURLY:
                {
                alt113=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 113, 0, input);

                throw nvae;

            }

            switch (alt113) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1056:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5416);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1056:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5422);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5425); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1060:1: conditionAfter returns [AbstractTextMarkerCondition cond = null] : AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionAfter() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1061:5: ( AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:5: AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5456); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5458); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:18: (type1= typeExpression |type2= typeListExpression )
            int alt114=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA114_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt114=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt114=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 114, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt114=1;
                }
                break;
            case LCURLY:
                {
                alt114=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 114, 0, input);

                throw nvae;

            }

            switch (alt114) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:19: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5465);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:42: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5471);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5474); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1066:1: conditionStartsWith returns [AbstractTextMarkerCondition cond = null] : STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionStartsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1067:5: ( STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:5: STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5505); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5507); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:23: (type1= typeExpression |type2= typeListExpression )
            int alt115=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA115_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt115=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt115=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 115, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt115=1;
                }
                break;
            case LCURLY:
                {
                alt115=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;

            }

            switch (alt115) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:24: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5514);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:47: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5520);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5523); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1072:1: conditionEndsWith returns [AbstractTextMarkerCondition cond = null] : ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionEndsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1073:5: ( ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:5: ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5554); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5556); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:21: (type1= typeExpression |type2= typeListExpression )
            int alt116=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA116_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt116=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                    alt116=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 116, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt116=1;
                }
                break;
            case LCURLY:
                {
                alt116=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;

            }

            switch (alt116) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:22: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5563);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:45: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5569);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5572); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1078:1: conditionSize returns [AbstractTextMarkerCondition cond = null] : SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionSize() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression list =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1079:5: ( SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:5: SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,SIZE,FOLLOW_SIZE_in_conditionSize5603); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5605); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize5611);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:39: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==COMMA) ) {
                int LA117_1 = input.LA(2);

                if ( (LA117_1==COS||LA117_1==DecimalLiteral||LA117_1==EXP||LA117_1==FloatingPointLiteral||(LA117_1 >= LOGN && LA117_1 <= LPAREN)||LA117_1==MINUS||LA117_1==SIN||LA117_1==TAN) ) {
                    alt117=1;
                }
                else if ( (LA117_1==Identifier) ) {
                    int LA117_4 = input.LA(3);

                    if ( (LA117_4==COMMA||LA117_4==LPAREN||LA117_4==MINUS||(LA117_4 >= PERCENT && LA117_4 <= PLUS)||(LA117_4 >= SLASH && LA117_4 <= STAR)) ) {
                        alt117=1;
                    }
                }
            }
            switch (alt117) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:40: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5614); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5620);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5622); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5628);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:100: ( COMMA var= numberVariable )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==COMMA) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:101: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5633); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionSize5639);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5643); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1084:1: action returns [AbstractTextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) ;
    public final AbstractTextMarkerAction action() throws RecognitionException {
        AbstractTextMarkerAction result =  null;


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1085:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1086:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1086:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            int alt119=39;
            switch ( input.LA(1) ) {
            case COLOR:
                {
                alt119=1;
                }
                break;
            case DEL:
                {
                alt119=2;
                }
                break;
            case LOG:
                {
                alt119=3;
                }
                break;
            case MARK:
                {
                alt119=4;
                }
                break;
            case MARKSCORE:
                {
                alt119=5;
                }
                break;
            case MARKFAST:
                {
                alt119=6;
                }
                break;
            case MARKLAST:
                {
                alt119=7;
                }
                break;
            case REPLACE:
                {
                alt119=8;
                }
                break;
            case FILTERMARKUP:
                {
                alt119=9;
                }
                break;
            case FILTERTYPE:
                {
                alt119=10;
                }
                break;
            case RETAINMARKUP:
                {
                alt119=11;
                }
                break;
            case RETAINTYPE:
                {
                alt119=12;
                }
                break;
            case CREATE:
                {
                alt119=13;
                }
                break;
            case FILL:
                {
                alt119=14;
                }
                break;
            case CALL:
                {
                alt119=15;
                }
                break;
            case ASSIGN:
                {
                alt119=16;
                }
                break;
            case SETFEATURE:
                {
                alt119=17;
                }
                break;
            case GETFEATURE:
                {
                alt119=18;
                }
                break;
            case UNMARK:
                {
                alt119=19;
                }
                break;
            case UNMARKALL:
                {
                alt119=20;
                }
                break;
            case TRANSFER:
                {
                alt119=21;
                }
                break;
            case MARKONCE:
                {
                alt119=22;
                }
                break;
            case TRIE:
                {
                alt119=23;
                }
                break;
            case GATHER:
                {
                alt119=24;
                }
                break;
            case EXEC:
                {
                alt119=25;
                }
                break;
            case MARKTABLE:
                {
                alt119=26;
                }
                break;
            case ADD:
                {
                alt119=27;
                }
                break;
            case REMOVE:
                {
                alt119=28;
                }
                break;
            case REMOVEDUPLICATE:
                {
                alt119=29;
                }
                break;
            case MERGE:
                {
                alt119=30;
                }
                break;
            case GET:
                {
                alt119=31;
                }
                break;
            case GETLIST:
                {
                alt119=32;
                }
                break;
            case MATCHEDTEXT:
                {
                alt119=33;
                }
                break;
            case CLEAR:
                {
                alt119=34;
                }
                break;
            case EXPAND:
                {
                alt119=35;
                }
                break;
            case CONFIGURE:
                {
                alt119=36;
                }
                break;
            case DYNAMICANCHORING:
                {
                alt119=37;
                }
                break;
            case Identifier:
                {
                int LA119_38 = input.LA(2);

                if ( (LA119_38==LPAREN) && (synpred20_TextMarkerParser())) {
                    alt119=38;
                }
                else if ( (LA119_38==COMMA||LA119_38==RCURLY||LA119_38==RPAREN) ) {
                    alt119=39;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 119, 38, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 119, 0, input);

                throw nvae;

            }

            switch (alt119) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1087:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5676);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1088:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5685);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1089:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5694);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1090:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5703);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1091:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5712);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1092:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5721);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1093:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5730);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1094:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5739);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1095:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5748);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1096:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5757);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1097:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5766);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1098:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5775);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1099:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5784);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1100:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action5793);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1101:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action5802);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1102:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action5811);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1103:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action5820);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1104:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action5829);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1105:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action5838);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1106:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action5847);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1107:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action5856);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1108:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action5865);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1109:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action5874);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1110:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action5883);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1111:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action5892);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1112:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action5901);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1113:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action5910);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1114:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action5919);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1115:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action5928);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1116:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action5937);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action5946);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1118:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action5955);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1119:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action5964);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1120:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action5973);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1121:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action5982);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1122:4: a= actionConfigure
                    {
                    pushFollow(FOLLOW_actionConfigure_in_action5991);
                    a=actionConfigure();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1123:4: a= actionDynamicAnchoring
                    {
                    pushFollow(FOLLOW_actionDynamicAnchoring_in_action6000);
                    a=actionDynamicAnchoring();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 38 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1124:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action6019);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 39 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1125:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action6028);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1129:1: variableAction returns [AbstractTextMarkerAction action = null] : id= Identifier ;
    public final AbstractTextMarkerAction variableAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1130:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1132:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction6057); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1139:1: externalAction returns [AbstractTextMarkerAction action = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerAction externalAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1140:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1142:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction6085); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction6087); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction6093);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction6095); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:1: actionCreate returns [AbstractTextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1155:5: (name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1156:5: name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate6131); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate6133); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate6139);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1157:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==COMMA) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1157:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6146); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1158:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==MINUS) && (synpred21_TextMarkerParser())) {
                        alt121=1;
                    }
                    else if ( (LA121_0==DecimalLiteral) && (synpred21_TextMarkerParser())) {
                        alt121=1;
                    }
                    else if ( (LA121_0==FloatingPointLiteral) && (synpred21_TextMarkerParser())) {
                        alt121=1;
                    }
                    else if ( (LA121_0==Identifier) ) {
                        int LA121_4 = input.LA(2);

                        if ( (synpred21_TextMarkerParser()) ) {
                            alt121=1;
                        }
                    }
                    else if ( (LA121_0==LPAREN) && (synpred21_TextMarkerParser())) {
                        alt121=1;
                    }
                    else if ( (LA121_0==COS||LA121_0==EXP||LA121_0==LOGN||LA121_0==SIN||LA121_0==TAN) && (synpred21_TextMarkerParser())) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:5: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate6171);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:80: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop120:
                            do {
                                int alt120=2;
                                int LA120_0 = input.LA(1);

                                if ( (LA120_0==COMMA) ) {
                                    int LA120_1 = input.LA(2);

                                    if ( (synpred22_TextMarkerParser()) ) {
                                        alt120=1;
                                    }


                                }


                                switch (alt120) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:81: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6188); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate6194);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop120;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate6201); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1160:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==Identifier||LA123_0==REMOVESTRING||LA123_0==StringLiteral) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1160:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate6214);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6216); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate6222);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {map.put(fname,obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1161:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop122:
                            do {
                                int alt122=2;
                                int LA122_0 = input.LA(1);

                                if ( (LA122_0==COMMA) ) {
                                    alt122=1;
                                }


                                switch (alt122) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1161:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6232); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate6238);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6240); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate6246);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                            	    }
                            	    break;

                            	default :
                            	    break loop122;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6261); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1167:1: actionMarkTable returns [AbstractTextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1171:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1172:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6302); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6304); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6315);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6317); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6328);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6330); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6340);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1176:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==COMMA) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1176:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6348); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6362);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6364); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6370);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1178:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==COMMA) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1178:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6380); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6386);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6388); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6394);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop125;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6407); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1183:1: actionGather returns [AbstractTextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1188:5: (name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1189:5: name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6448); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6450); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6456);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1190:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==COMMA) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1190:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6463); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt128=2;
                    int LA128_0 = input.LA(1);

                    if ( (LA128_0==MINUS) && (synpred23_TextMarkerParser())) {
                        alt128=1;
                    }
                    else if ( (LA128_0==DecimalLiteral) && (synpred23_TextMarkerParser())) {
                        alt128=1;
                    }
                    else if ( (LA128_0==FloatingPointLiteral) && (synpred23_TextMarkerParser())) {
                        alt128=1;
                    }
                    else if ( (LA128_0==Identifier) ) {
                        int LA128_4 = input.LA(2);

                        if ( (synpred23_TextMarkerParser()) ) {
                            alt128=1;
                        }
                    }
                    else if ( (LA128_0==LPAREN) && (synpred23_TextMarkerParser())) {
                        alt128=1;
                    }
                    else if ( (LA128_0==COS||LA128_0==EXP||LA128_0==LOGN||LA128_0==SIN||LA128_0==TAN) && (synpred23_TextMarkerParser())) {
                        alt128=1;
                    }
                    switch (alt128) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:6: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6483);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:81: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop127:
                            do {
                                int alt127=2;
                                int LA127_0 = input.LA(1);

                                if ( (LA127_0==COMMA) ) {
                                    int LA127_1 = input.LA(2);

                                    if ( (synpred24_TextMarkerParser()) ) {
                                        alt127=1;
                                    }


                                }


                                switch (alt127) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:82: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6499); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6505);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop127;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6512); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==Identifier||LA132_0==REMOVESTRING||LA132_0==StringLiteral) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6525);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6527); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:44: (obj1= numberExpression |obj2= numberListExpression )
                            int alt129=2;
                            switch ( input.LA(1) ) {
                            case COS:
                            case DecimalLiteral:
                            case EXP:
                            case FloatingPointLiteral:
                            case LOGN:
                            case LPAREN:
                            case MINUS:
                            case SIN:
                            case TAN:
                                {
                                alt129=1;
                                }
                                break;
                            case Identifier:
                                {
                                int LA129_2 = input.LA(2);

                                if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                                    alt129=1;
                                }
                                else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                                    alt129=2;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return action;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 129, 2, input);

                                    throw nvae;

                                }
                                }
                                break;
                            case LCURLY:
                                {
                                alt129=2;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return action;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 129, 0, input);

                                throw nvae;

                            }

                            switch (alt129) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6534);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6542);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {map.put(fname,obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop131:
                            do {
                                int alt131=2;
                                int LA131_0 = input.LA(1);

                                if ( (LA131_0==COMMA) ) {
                                    alt131=1;
                                }


                                switch (alt131) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6553); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6559);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6561); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:50: (obj1= numberExpression |obj2= numberListExpression )
                            	    int alt130=2;
                            	    switch ( input.LA(1) ) {
                            	    case COS:
                            	    case DecimalLiteral:
                            	    case EXP:
                            	    case FloatingPointLiteral:
                            	    case LOGN:
                            	    case LPAREN:
                            	    case MINUS:
                            	    case SIN:
                            	    case TAN:
                            	        {
                            	        alt130=1;
                            	        }
                            	        break;
                            	    case Identifier:
                            	        {
                            	        int LA130_2 = input.LA(2);

                            	        if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                            	            alt130=1;
                            	        }
                            	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                            	            alt130=2;
                            	        }
                            	        else {
                            	            if (state.backtracking>0) {state.failed=true; return action;}
                            	            NoViableAltException nvae =
                            	                new NoViableAltException("", 130, 2, input);

                            	            throw nvae;

                            	        }
                            	        }
                            	        break;
                            	    case LCURLY:
                            	        {
                            	        alt130=2;
                            	        }
                            	        break;
                            	    default:
                            	        if (state.backtracking>0) {state.failed=true; return action;}
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 130, 0, input);

                            	        throw nvae;

                            	    }

                            	    switch (alt130) {
                            	        case 1 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6568);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6576);
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
                            	    break loop131;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6592); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1200:1: actionFill returns [AbstractTextMarkerAction action = null] : FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN ;
    public final AbstractTextMarkerAction actionFill() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1204:5: ( FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1205:5: FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN
            {
            match(input,FILL,FOLLOW_FILL_in_actionFill6634); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6636); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill6642);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1205:39: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+
            int cnt134=0;
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==COMMA) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1205:40: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6645); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill6651);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6653); if (state.failed) return action;

            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1206:5: (obj1= argument )
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1207:5: obj1= argument
            	    {
            	    pushFollow(FOLLOW_argument_in_actionFill6670);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt134 >= 1 ) break loop134;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(134, input);
                        throw eee;
                }
                cnt134++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6687); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1213:1: actionColor returns [AbstractTextMarkerAction action = null] : COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionColor() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression bgcolor =null;

        StringExpression fgcolor =null;

        BooleanExpression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1214:5: ( COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1215:5: COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            match(input,COLOR,FOLLOW_COLOR_in_actionColor6725); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6727); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor6733);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionColor6745); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor6756);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1219:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==COMMA) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1219:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6764); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor6774);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1221:5: ( COMMA selected= booleanExpression )?
                    int alt135=2;
                    int LA135_0 = input.LA(1);

                    if ( (LA135_0==COMMA) ) {
                        alt135=1;
                    }
                    switch (alt135) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1221:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor6782); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor6792);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor6802); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1227:1: actionDel returns [AbstractTextMarkerAction action = null] : DEL ;
    public final AbstractTextMarkerAction actionDel() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1228:5: ( DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1229:5: DEL
            {
            match(input,DEL,FOLLOW_DEL_in_actionDel6836); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1233:1: actionLog returns [AbstractTextMarkerAction action = null] : LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final AbstractTextMarkerAction actionLog() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token log=null;
        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1234:5: ( LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1235:5: LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            match(input,LOG,FOLLOW_LOG_in_actionLog6878); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog6880); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog6886);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1235:39: ( COMMA log= LogLevel )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==COMMA) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1235:40: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog6889); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog6895); if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog6899); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1239:1: actionMark returns [AbstractTextMarkerAction action = null] : MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1243:5: ( MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1244:5: MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARK,FOLLOW_MARK_in_actionMark6938); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark6940); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark6951);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1246:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop138:
            do {
                int alt138=2;
                int LA138_0 = input.LA(1);

                if ( (LA138_0==COMMA) ) {
                    alt138=1;
                }


                switch (alt138) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1247:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark6967); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark6983);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop138;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark7007); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1255:1: actionExpand returns [AbstractTextMarkerAction action = null] : EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionExpand() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1259:5: ( EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1260:5: EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand7051); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand7053); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand7064);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1262:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop139:
            do {
                int alt139=2;
                int LA139_0 = input.LA(1);

                if ( (LA139_0==COMMA) ) {
                    alt139=1;
                }


                switch (alt139) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1263:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand7080); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand7096);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop139;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand7120); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1272:1: actionMarkScore returns [AbstractTextMarkerAction action = null] : MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkScore() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1276:5: ( MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1277:5: MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore7165); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore7167); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore7178);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7180); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore7190);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1280:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0==COMMA) ) {
                    alt140=1;
                }


                switch (alt140) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1281:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7206); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore7222);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop140;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore7246); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1289:1: actionMarkOnce returns [AbstractTextMarkerAction action = null] : MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkOnce() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1293:5: ( MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:5: MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7290); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7292); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:21: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==MINUS) && (synpred28_TextMarkerParser())) {
                alt141=1;
            }
            else if ( (LA141_0==DecimalLiteral) && (synpred28_TextMarkerParser())) {
                alt141=1;
            }
            else if ( (LA141_0==FloatingPointLiteral) && (synpred28_TextMarkerParser())) {
                alt141=1;
            }
            else if ( (LA141_0==Identifier) ) {
                int LA141_4 = input.LA(2);

                if ( (synpred28_TextMarkerParser()) ) {
                    alt141=1;
                }
            }
            else if ( (LA141_0==LPAREN) && (synpred28_TextMarkerParser())) {
                alt141=1;
            }
            else if ( (LA141_0==COS||LA141_0==EXP||LA141_0==LOGN||LA141_0==SIN||LA141_0==TAN) && (synpred28_TextMarkerParser())) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:22: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7309);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7311); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7329);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1295:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0==COMMA) ) {
                    alt142=1;
                }


                switch (alt142) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1296:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7345); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7361);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop142;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7380); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1303:1: actionMarkFast returns [AbstractTextMarkerAction action = null] : MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkFast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        WordListExpression list =null;

        BooleanExpression ignore =null;

        NumberExpression ignoreLength =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1304:5: ( MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1305:5: MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN
            {
            match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7419); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7421); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7427);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7429); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7435);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1305:75: ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==COMMA) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1305:76: COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7438); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7444);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1305:109: ( COMMA ignoreLength= numberExpression )?
                    int alt143=2;
                    int LA143_0 = input.LA(1);

                    if ( (LA143_0==COMMA) ) {
                        alt143=1;
                    }
                    switch (alt143) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1305:110: COMMA ignoreLength= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7447); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7453);
                            ignoreLength=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7459); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1309:1: actionMarkLast returns [AbstractTextMarkerAction action = null] : MARKLAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionMarkLast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1310:5: ( MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1311:5: MARKLAST LPAREN type= typeExpression RPAREN
            {
            match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7493); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7495); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7501);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7503); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1315:1: actionReplace returns [AbstractTextMarkerAction action = null] : REPLACE LPAREN lit= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionReplace() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1316:5: ( REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1317:5: REPLACE LPAREN lit= stringExpression RPAREN
            {
            match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7537); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7539); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace7545);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7547); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1321:1: actionRetainMarkup returns [AbstractTextMarkerAction action = null] : RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1325:5: ( RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1326:5: RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7590); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1326:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==LPAREN) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1326:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7593); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7599);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1326:64: ( COMMA id= stringExpression )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==COMMA) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1326:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7604); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7610);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop145;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7616); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1331:1: actionRetainType returns [AbstractTextMarkerAction action = null] : RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1335:5: ( RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1336:5: RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7662); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1336:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==LPAREN) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1336:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7665); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7671);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1336:60: ( COMMA id= typeExpression )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==COMMA) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1336:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7676); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7682);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7688); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1340:1: actionFilterMarkup returns [AbstractTextMarkerAction action = null] : FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:5: ( FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:5: FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup7736); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==LPAREN) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup7739); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7745);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:64: ( COMMA id= stringExpression )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==COMMA) ) {
                            alt149=1;
                        }


                        switch (alt149) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup7750); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7756);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop149;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup7762); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1350:1: actionFilterType returns [AbstractTextMarkerAction action = null] : FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1354:5: ( FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1355:5: FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType7808); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1355:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==LPAREN) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1355:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType7811); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType7817);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1355:60: ( COMMA id= typeExpression )*
                    loop151:
                    do {
                        int alt151=2;
                        int LA151_0 = input.LA(1);

                        if ( (LA151_0==COMMA) ) {
                            alt151=1;
                        }


                        switch (alt151) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1355:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType7822); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType7828);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop151;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType7834); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1359:1: actionCall returns [AbstractTextMarkerAction action = null] : CALL LPAREN ns= dottedIdentifier RPAREN ;
    public final AbstractTextMarkerAction actionCall() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1360:5: ( CALL LPAREN ns= dottedIdentifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1361:5: CALL LPAREN ns= dottedIdentifier RPAREN
            {
            match(input,CALL,FOLLOW_CALL_in_actionCall7874); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCall7876); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionCall7882);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall7884); if (state.failed) return action;

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



    // $ANTLR start "actionConfigure"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1366:1: actionConfigure returns [AbstractTextMarkerAction action = null] : CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN ;
    public final AbstractTextMarkerAction actionConfigure() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1371:5: ( CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1372:5: CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN
            {
            match(input,CONFIGURE,FOLLOW_CONFIGURE_in_actionConfigure7922); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionConfigure7924); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionConfigure7930);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionConfigure7937); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionConfigure7947);
            fname=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure7949); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionConfigure7955);
            obj1=argument();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(fname,obj1);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1375:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
            loop153:
            do {
                int alt153=2;
                int LA153_0 = input.LA(1);

                if ( (LA153_0==COMMA) ) {
                    alt153=1;
                }


                switch (alt153) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1375:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure7965); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionConfigure7971);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure7973); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionConfigure7979);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

            	    }
            	    break;

            	default :
            	    break loop153;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionConfigure7989); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createConfigureAction(ns, map, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

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
    // $ANTLR end "actionConfigure"



    // $ANTLR start "actionExec"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1381:1: actionExec returns [AbstractTextMarkerAction action = null] : EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionExec() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1382:5: ( EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1383:5: EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN
            {
            match(input,EXEC,FOLLOW_EXEC_in_actionExec8021); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExec8023); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionExec8029);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1383:39: ( COMMA tl= typeListExpression )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==COMMA) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1383:40: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec8032); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec8038);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec8042); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1387:1: actionAssign returns [AbstractTextMarkerAction action = null] : name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN ;
    public final AbstractTextMarkerAction actionAssign() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token nv=null;
        TypeExpression e1 =null;

        BooleanExpression e2 =null;

        StringExpression e3 =null;

        NumberExpression e4 =null;

        NumberExpression e6 =null;

        NumberExpression e5 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1388:5: (name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1389:5: name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign8085); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign8087); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1390:5: ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression )
            int alt155=6;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==Identifier) ) {
                int LA155_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                    alt155=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                    alt155=2;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                    alt155=3;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                    alt155=4;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "FLOAT"))) ) {
                    alt155=5;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                    alt155=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return action;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 155, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return action;}
                NoViableAltException nvae =
                    new NoViableAltException("", 155, 0, input);

                throw nvae;

            }
            switch (alt155) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1391:5: {...}?nv= Identifier COMMA e1= typeExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8114); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8116); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionAssign8122);
                    e1=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e1,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1395:5: {...}?nv= Identifier COMMA e2= booleanExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8160); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8162); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionAssign8168);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1399:5: {...}?nv= Identifier COMMA e3= stringExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRING\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8206); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8208); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionAssign8214);
                    e3=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e3,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1403:5: {...}?nv= Identifier COMMA e4= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INT\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8252); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8254); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8260);
                    e4=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e4,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1407:6: {...}?nv= Identifier COMMA e6= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "FLOAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"FLOAT\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8299); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8301); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8307);
                    e6=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e6,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1411:5: {...}?nv= Identifier COMMA e5= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8345); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8347); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8353);
                    e5=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e5,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8372); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1417:1: actionSetFeature returns [AbstractTextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerAction actionSetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1418:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1419:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8404); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8406); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8412);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8414); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature8420);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8422); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1424:1: actionGetFeature returns [AbstractTextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final AbstractTextMarkerAction actionGetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        Token v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1425:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1426:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8461); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8463); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8469);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8471); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature8477);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8479); if (state.failed) return action;

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



    // $ANTLR start "actionDynamicAnchoring"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1431:1: actionDynamicAnchoring returns [AbstractTextMarkerAction action = null] : name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionDynamicAnchoring() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression active =null;

        NumberExpression penalty =null;

        NumberExpression factor =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1432:5: (name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1433:5: name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,DYNAMICANCHORING,FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8515); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDynamicAnchoring8517); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionDynamicAnchoring8523);
            active=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1434:5: ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==COMMA) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1434:6: COMMA penalty= numberExpression ( COMMA factor= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8531); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8537);
                    penalty=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1435:5: ( COMMA factor= numberExpression )?
                    int alt156=2;
                    int LA156_0 = input.LA(1);

                    if ( (LA156_0==COMMA) ) {
                        alt156=1;
                    }
                    switch (alt156) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1435:6: COMMA factor= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8545); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8551);
                            factor=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createDynamicAnchoringAction(active, penalty, factor, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDynamicAnchoring8568); if (state.failed) return action;

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
    // $ANTLR end "actionDynamicAnchoring"



    // $ANTLR start "actionUnmark"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1441:1: actionUnmark returns [AbstractTextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionUnmark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1442:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1443:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8598); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8600); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark8606);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8608); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1448:1: actionUnmarkAll returns [AbstractTextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionUnmarkAll() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;

        TypeListExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1449:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1450:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8644); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8646); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8652);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1451:5: ( COMMA list= typeListExpression )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==COMMA) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1451:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8660); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8666);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8670); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1455:1: actionTransfer returns [AbstractTextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionTransfer() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1456:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1457:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8705); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8707); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer8713);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8715); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1461:1: actionTrie returns [AbstractTextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1465:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1466:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8755); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8757); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8767);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8769); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie8775);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(key,value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1468:5: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop159:
            do {
                int alt159=2;
                int LA159_0 = input.LA(1);

                if ( (LA159_0==COMMA) ) {
                    int LA159_1 = input.LA(2);

                    if ( (LA159_1==Identifier) ) {
                        int LA159_2 = input.LA(3);

                        if ( (LA159_2==ASSIGN_EQUAL||LA159_2==LPAREN||LA159_2==PLUS) ) {
                            alt159=1;
                        }


                    }
                    else if ( (LA159_1==REMOVESTRING||LA159_1==StringLiteral) ) {
                        alt159=1;
                    }


                }


                switch (alt159) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1468:6: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8783); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8789);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8791); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8797);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(key,value);}

            	    }
            	    break;

            	default :
            	    break loop159;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8807); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie8813);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8820); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8826);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8833); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8839);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8846); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8852);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8859); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8865);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8872); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8878);
            ignoreChar=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie8884); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1480:1: actionAdd returns [AbstractTextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionAdd() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1484:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1485:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd8929); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd8931); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd8937);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1485:40: ( COMMA a= argument )+
            int cnt160=0;
            loop160:
            do {
                int alt160=2;
                int LA160_0 = input.LA(1);

                if ( (LA160_0==COMMA) ) {
                    alt160=1;
                }


                switch (alt160) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1485:41: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd8940); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd8946);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt160 >= 1 ) break loop160;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(160, input);
                        throw eee;
                }
                cnt160++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd8952); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1489:1: actionRemove returns [AbstractTextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionRemove() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1494:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove8992); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove8994); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove9000);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1494:43: ( COMMA a= argument )+
            int cnt161=0;
            loop161:
            do {
                int alt161=2;
                int LA161_0 = input.LA(1);

                if ( (LA161_0==COMMA) ) {
                    alt161=1;
                }


                switch (alt161) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1494:44: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove9003); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove9009);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt161 >= 1 ) break loop161;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(161, input);
                        throw eee;
                }
                cnt161++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove9015); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1498:1: actionRemoveDuplicate returns [AbstractTextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1499:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1500:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9051); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate9053); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate9059);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate9061); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:1: actionMerge returns [AbstractTextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final AbstractTextMarkerAction actionMerge() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression join =null;

        Token t =null;

        ListExpression f =null;



        	List<ListExpression> list = new ArrayList<ListExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1508:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1509:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge9106); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge9108); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge9114);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9116); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge9122);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9124); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge9130);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1509:113: ( COMMA f= listExpression )+
            int cnt162=0;
            loop162:
            do {
                int alt162=2;
                int LA162_0 = input.LA(1);

                if ( (LA162_0==COMMA) ) {
                    alt162=1;
                }


                switch (alt162) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1509:114: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge9135); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge9141);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(f);}

            	    }
            	    break;

            	default :
            	    if ( cnt162 >= 1 ) break loop162;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(162, input);
                        throw eee;
                }
                cnt162++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge9147); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1513:1: actionGet returns [AbstractTextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGet() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        ListExpression f =null;

        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1514:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1515:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet9182); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet9184); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet9190);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9192); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet9198);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9200); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet9206);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet9208); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1519:1: actionGetList returns [AbstractTextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGetList() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1520:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1521:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList9243); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList9245); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList9251);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList9253); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList9259);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList9261); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1525:1: actionMatchedText returns [AbstractTextMarkerAction action = null] : MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMatchedText() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token var =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1529:5: ( MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1530:5: MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText9300); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText9302); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText9313);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1532:5: ( COMMA index= numberExpression )*
            loop163:
            do {
                int alt163=2;
                int LA163_0 = input.LA(1);

                if ( (LA163_0==COMMA) ) {
                    alt163=1;
                }


                switch (alt163) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1533:9: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText9329); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText9335);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop163;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText9359); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1541:1: actionClear returns [AbstractTextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionClear() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1542:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1543:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear9399); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear9401); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear9407);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear9409); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1548:1: varArgumentList returns [List args = new ArrayList()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List varArgumentList() throws RecognitionException {
        List args =  new ArrayList();


        TextMarkerExpression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==LPAREN) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList9436); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList9442);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:41: ( COMMA arg= argument )*
                    loop164:
                    do {
                        int alt164=2;
                        int LA164_0 = input.LA(1);

                        if ( (LA164_0==COMMA) ) {
                            alt164=1;
                        }


                        switch (alt164) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:42: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList9446); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList9452);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;

                    	    if ( state.backtracking==0 ) {args.add(arg);}

                    	    }
                    	    break;

                    	default :
                    	    break loop164;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList9458); if (state.failed) return args;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1553:1: argument returns [TextMarkerExpression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final TextMarkerExpression argument() throws RecognitionException {
        TextMarkerExpression expr =  null;


        StringExpression a4 =null;

        BooleanExpression a2 =null;

        NumberExpression a3 =null;

        TypeExpression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1557:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
            int alt166=4;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
            case StringLiteral:
                {
                alt166=1;
                }
                break;
            case Identifier:
                {
                int LA166_2 = input.LA(2);

                if ( (synpred31_TextMarkerParser()) ) {
                    alt166=1;
                }
                else if ( (synpred32_TextMarkerParser()) ) {
                    alt166=2;
                }
                else if ( (synpred33_TextMarkerParser()) ) {
                    alt166=3;
                }
                else if ( (true) ) {
                    alt166=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 166, 2, input);

                    throw nvae;

                }
                }
                break;
            case FALSE:
            case TRUE:
            case XOR:
                {
                alt166=2;
                }
                break;
            case BasicAnnotationType:
                {
                int LA166_6 = input.LA(2);

                if ( (synpred32_TextMarkerParser()) ) {
                    alt166=2;
                }
                else if ( (true) ) {
                    alt166=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 166, 6, input);

                    throw nvae;

                }
                }
                break;
            case LPAREN:
                {
                int LA166_7 = input.LA(2);

                if ( (synpred32_TextMarkerParser()) ) {
                    alt166=2;
                }
                else if ( (synpred33_TextMarkerParser()) ) {
                    alt166=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 166, 7, input);

                    throw nvae;

                }
                }
                break;
            case COS:
            case DecimalLiteral:
            case EXP:
            case FloatingPointLiteral:
            case LOGN:
            case MINUS:
            case SIN:
            case TAN:
                {
                alt166=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 166, 0, input);

                throw nvae;

            }

            switch (alt166) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1558:2: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument9492);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1559:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument9503);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1560:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9514);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1561:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9525);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1570:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1571:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1572:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9559); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1573:2: (dot= DOT idn= Identifier )*
            loop167:
            do {
                int alt167=2;
                int LA167_0 = input.LA(1);

                if ( (LA167_0==DOT) ) {
                    alt167=1;
                }


                switch (alt167) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1574:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9572); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9582); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop167;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1579:1: dottedIdentifier2 returns [String idString = \"\"] : id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* ;
    public final String dottedIdentifier2() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1580:2: (id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1581:2: id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29608); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1582:2: (dot= ( DOT | MINUS ) idn= Identifier )*
            loop168:
            do {
                int alt168=2;
                int LA168_0 = input.LA(1);

                if ( (LA168_0==DOT||LA168_0==MINUS) ) {
                    alt168=1;
                }


                switch (alt168) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1583:3: dot= ( DOT | MINUS ) idn= Identifier
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

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29635); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop168;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1589:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1591:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1592:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9667); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1595:2: (dot= DOT id= Identifier )*
            loop169:
            do {
                int alt169=2;
                int LA169_0 = input.LA(1);

                if ( (LA169_0==DOT) ) {
                    alt169=1;
                }


                switch (alt169) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1596:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9680); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9690); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop169;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1604:1: annotationType returns [Token ref = null] : (token= BasicAnnotationType |did= dottedId ) ;
    public final Token annotationType() throws RecognitionException {
        Token ref =  null;


        Token token=null;
        Token did =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1605:2: ( (token= BasicAnnotationType |did= dottedId ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1606:2: (token= BasicAnnotationType |did= dottedId )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1606:2: (token= BasicAnnotationType |did= dottedId )
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==BasicAnnotationType) ) {
                alt170=1;
            }
            else if ( (LA170_0==Identifier) ) {
                alt170=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 170, 0, input);

                throw nvae;

            }
            switch (alt170) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1608:2: token= BasicAnnotationType
                    {
                    token=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType9725); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1610:2: did= dottedId
                    {
                    pushFollow(FOLLOW_dottedId_in_annotationType9737);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1614:1: wordListExpression returns [WordListExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordListExpression wordListExpression() throws RecognitionException {
        WordListExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1615:2: (id= Identifier |path= RessourceLiteral )
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==Identifier) ) {
                alt171=1;
            }
            else if ( (LA171_0==RessourceLiteral) ) {
                alt171=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 171, 0, input);

                throw nvae;

            }
            switch (alt171) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1616:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression9762); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordListExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1619:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression9775); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1624:1: wordTableExpression returns [WordTableExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordTableExpression wordTableExpression() throws RecognitionException {
        WordTableExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1625:2: (id= Identifier |path= RessourceLiteral )
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( (LA172_0==Identifier) ) {
                alt172=1;
            }
            else if ( (LA172_0==RessourceLiteral) ) {
                alt172=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 172, 0, input);

                throw nvae;

            }
            switch (alt172) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1626:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression9799); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordTableExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1629:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression9812); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1634:1: numberFunction returns [NumberExpression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final NumberExpression numberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression numExprP =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1635:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==COS||LA173_0==EXP||LA173_0==LOGN||LA173_0==SIN||LA173_0==TAN) ) {
                alt173=1;
            }
            else if ( (LA173_0==Identifier) && (synpred34_TextMarkerParser())) {
                alt173=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 173, 0, input);

                throw nvae;

            }
            switch (alt173) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1636:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1636:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1636:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
                    {
                    op=(Token)input.LT(1);

                    if ( input.LA(1)==COS||input.LA(1)==EXP||input.LA(1)==LOGN||input.LA(1)==SIN||input.LA(1)==TAN ) {
                        input.consume();
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction9857);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1639:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction9881);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1644:1: externalNumberFunction returns [NumberExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final NumberExpression externalNumberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1645:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1647:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction9907); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction9909); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction9916);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction9918); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1654:1: numberVariable returns [Token ref = null] : ({...}?token1= Identifier |{...}?token2= Identifier |{...}?token2= Identifier );
    public final Token numberVariable() throws RecognitionException {
        Token ref =  null;


        Token token1=null;
        Token token2=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:2: ({...}?token1= Identifier |{...}?token2= Identifier |{...}?token2= Identifier )
            int alt174=3;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==Identifier) ) {
                int LA174_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                    alt174=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                    alt174=2;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOAT"))) ) {
                    alt174=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ref;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 174, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 174, 0, input);

                throw nvae;

            }
            switch (alt174) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1656:2: {...}?token1= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INT\")");
                    }

                    token1=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9943); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1657:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLE\")");
                    }

                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9956); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1658:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"FLOAT\")");
                    }

                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9969); if (state.failed) return ref;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1662:1: additiveExpression returns [NumberExpression expr = null] : e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* ;
    public final NumberExpression additiveExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1665:2: (e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1666:2: e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9999);
            e=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {exprs.add(e);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1666:46: (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            loop175:
            do {
                int alt175=2;
                int LA175_0 = input.LA(1);

                if ( (LA175_0==MINUS||LA175_0==PLUS) ) {
                    alt175=1;
                }


                switch (alt175) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1666:48: op= ( PLUS | MINUS ) e= multiplicativeExpression
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
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

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10021);
            	    e=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) {exprs.add(e);}

            	    }
            	    break;

            	default :
            	    break loop175;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1670:1: multiplicativeExpression returns [NumberExpression expr = null] : (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) ;
    public final NumberExpression multiplicativeExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;

        NumberExpression e1 =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1673:2: ( (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1674:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1674:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            int alt177=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt177=1;
                }
                break;
            case Identifier:
                {
                int LA177_2 = input.LA(2);

                if ( (LA177_2==LPAREN) ) {
                    alt177=2;
                }
                else if ( (LA177_2==EOF||LA177_2==COMMA||LA177_2==EQUAL||(LA177_2 >= GREATER && LA177_2 <= GREATEREQUAL)||(LA177_2 >= LESS && LA177_2 <= LESSEQUAL)||LA177_2==MINUS||LA177_2==NOTEQUAL||(LA177_2 >= PERCENT && LA177_2 <= PLUS)||LA177_2==RBRACK||LA177_2==RPAREN||LA177_2==SEMI||(LA177_2 >= SLASH && LA177_2 <= STAR)) ) {
                    alt177=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 177, 2, input);

                    throw nvae;

                }
                }
                break;
            case COS:
            case EXP:
            case LOGN:
            case SIN:
            case TAN:
                {
                alt177=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 177, 0, input);

                throw nvae;

            }

            switch (alt177) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1674:3: e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10054);
                    e=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1674:45: (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    loop176:
                    do {
                        int alt176=2;
                        int LA176_0 = input.LA(1);

                        if ( (LA176_0==PERCENT||(LA176_0 >= SLASH && LA176_0 <= STAR)) ) {
                            alt176=1;
                        }


                        switch (alt176) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1674:47: op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression
                    	    {
                    	    op=(Token)input.LT(1);

                    	    if ( input.LA(1)==PERCENT||(input.LA(1) >= SLASH && input.LA(1) <= STAR) ) {
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

                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10082);
                    	    e=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {exprs.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop176;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1676:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression10100);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1679:1: numberExpression returns [NumberExpression expr = null] : e= additiveExpression ;
    public final NumberExpression numberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1680:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1681:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression10123);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1684:1: numberExpressionInPar returns [NumberExpression expr = null] : LPAREN e= additiveExpression RPAREN ;
    public final NumberExpression numberExpressionInPar() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1685:2: ( LPAREN e= additiveExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1686:2: LPAREN e= additiveExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar10141); if (state.failed) return expr;

            pushFollow(FOLLOW_additiveExpression_in_numberExpressionInPar10148);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar10150); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1689:1: simpleNumberExpression returns [NumberExpression expr = null] : ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar );
    public final NumberExpression simpleNumberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token m=null;
        Token lit=null;
        Token var =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1690:2: ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar )
            int alt181=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case DecimalLiteral:
                    {
                    alt181=1;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt181=2;
                    }
                    break;
                case Identifier:
                    {
                    alt181=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 181, 1, input);

                    throw nvae;

                }

                }
                break;
            case DecimalLiteral:
                {
                alt181=1;
                }
                break;
            case FloatingPointLiteral:
                {
                alt181=2;
                }
                break;
            case Identifier:
                {
                alt181=3;
                }
                break;
            case LPAREN:
                {
                alt181=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 181, 0, input);

                throw nvae;

            }

            switch (alt181) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1691:2: (m= MINUS )? lit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1691:4: (m= MINUS )?
                    int alt178=2;
                    int LA178_0 = input.LA(1);

                    if ( (LA178_0==MINUS) ) {
                        alt178=1;
                    }
                    switch (alt178) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1691:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10173); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression10180); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createIntegerExpression(lit,m);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1693:4: (m= MINUS )? lit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1693:6: (m= MINUS )?
                    int alt179=2;
                    int LA179_0 = input.LA(1);

                    if ( (LA179_0==MINUS) ) {
                        alt179=1;
                    }
                    switch (alt179) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1693:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10194); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10201); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDoubleExpression(lit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1694:4: (m= MINUS )? var= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1694:6: (m= MINUS )?
                    int alt180=2;
                    int LA180_0 = input.LA(1);

                    if ( (LA180_0==MINUS) ) {
                        alt180=1;
                    }
                    switch (alt180) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1694:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10212); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression10219);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceNumberExpression(var,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1695:4: e= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression10230);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1698:1: stringExpression returns [StringExpression expr = null] options {backtrack=true; } : (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1705:2: (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction )
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==StringLiteral) ) {
                alt184=1;
            }
            else if ( (LA184_0==Identifier) ) {
                int LA184_2 = input.LA(2);

                if ( (LA184_2==LPAREN) && (synpred36_TextMarkerParser())) {
                    alt184=2;
                }
                else if ( (LA184_2==EOF||LA184_2==ASSIGN_EQUAL||LA184_2==COMMA||LA184_2==PLUS||LA184_2==RPAREN||LA184_2==SEMI) ) {
                    alt184=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 184, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA184_0==REMOVESTRING) && (synpred36_TextMarkerParser())) {
                alt184=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 184, 0, input);

                throw nvae;

            }
            switch (alt184) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1706:2: e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10270);
                    e=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1707:2: ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    loop183:
                    do {
                        int alt183=2;
                        int LA183_0 = input.LA(1);

                        if ( (LA183_0==PLUS) ) {
                            alt183=1;
                        }


                        switch (alt183) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1707:3: PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression10277); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1707:8: (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    int alt182=5;
                    	    switch ( input.LA(1) ) {
                    	    case StringLiteral:
                    	        {
                    	        alt182=1;
                    	        }
                    	        break;
                    	    case Identifier:
                    	        {
                    	        int LA182_2 = input.LA(2);

                    	        if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    	            alt182=1;
                    	        }
                    	        else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt182=3;
                    	        }
                    	        else if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))))) ) {
                    	            alt182=4;
                    	        }
                    	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    	            alt182=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 182, 2, input);

                    	            throw nvae;

                    	        }
                    	        }
                    	        break;
                    	    case LPAREN:
                    	        {
                    	        alt182=2;
                    	        }
                    	        break;
                    	    case FALSE:
                    	    case TRUE:
                    	        {
                    	        alt182=3;
                    	        }
                    	        break;
                    	    case BasicAnnotationType:
                    	        {
                    	        alt182=4;
                    	        }
                    	        break;
                    	    case LCURLY:
                    	        {
                    	        alt182=5;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 182, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt182) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1707:9: e1= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10284);
                    	            e1=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1708:5: e2= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression10297);
                    	            e2=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1709:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression10309);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1710:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression10321);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(te);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1711:5: le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression10333);
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
                    	    break loop183;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedStringExpression(exprs);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1714:3: (e= stringFunction )=>e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression10361);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1718:1: stringFunction returns [StringExpression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final StringExpression stringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token name=null;
        Token var =null;

        StringExpression t =null;

        StringExpression e =null;


        List<StringExpression> list = new ArrayList<StringExpression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1720:2: (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==REMOVESTRING) ) {
                alt186=1;
            }
            else if ( (LA186_0==Identifier) && (synpred37_TextMarkerParser())) {
                alt186=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 186, 0, input);

                throw nvae;

            }
            switch (alt186) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1721:2: name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction10388); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction10390); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction10396);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1721:44: ( COMMA t= stringExpression )+
                    int cnt185=0;
                    loop185:
                    do {
                        int alt185=2;
                        int LA185_0 = input.LA(1);

                        if ( (LA185_0==COMMA) ) {
                            alt185=1;
                        }


                        switch (alt185) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1721:45: COMMA t= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction10399); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction10405);
                    	    t=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {list.add(t);}

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt185 >= 1 ) break loop185;
                    	    if (state.backtracking>0) {state.failed=true; return expr;}
                                EarlyExitException eee =
                                    new EarlyExitException(185, input);
                                throw eee;
                        }
                        cnt185++;
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction10411); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = StringFunctionFactory.createRemoveFunction(var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1724:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction10433);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1728:1: externalStringFunction returns [StringExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final StringExpression externalStringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1729:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1731:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction10458); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction10460); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction10467);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction10469); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1738:1: simpleStringExpression returns [StringExpression expr = null] : (lit= StringLiteral |{...}?id= Identifier );
    public final StringExpression simpleStringExpression() throws RecognitionException {
        StringExpression expr =  null;


        Token lit=null;
        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1739:2: (lit= StringLiteral |{...}?id= Identifier )
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==StringLiteral) ) {
                alt187=1;
            }
            else if ( (LA187_0==Identifier) ) {
                alt187=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 187, 0, input);

                throw nvae;

            }
            switch (alt187) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1740:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression10493); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleStringExpression(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1741:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRING\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression10507); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1745:1: booleanExpression returns [BooleanExpression expr = null] : ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final BooleanExpression booleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e =null;

        BooleanExpression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1746:2: ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression )
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==TRUE) ) {
                int LA188_1 = input.LA(2);

                if ( (LA188_1==EQUAL||LA188_1==NOTEQUAL) && (synpred38_TextMarkerParser())) {
                    alt188=1;
                }
                else if ( (LA188_1==EOF||LA188_1==COMMA||LA188_1==RPAREN||LA188_1==SEMI) ) {
                    alt188=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 188, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA188_0==FALSE) ) {
                int LA188_2 = input.LA(2);

                if ( (LA188_2==EQUAL||LA188_2==NOTEQUAL) && (synpred38_TextMarkerParser())) {
                    alt188=1;
                }
                else if ( (LA188_2==EOF||LA188_2==COMMA||LA188_2==RPAREN||LA188_2==SEMI) ) {
                    alt188=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 188, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA188_0==Identifier) ) {
                int LA188_3 = input.LA(2);

                if ( (LA188_3==LPAREN) && (synpred38_TextMarkerParser())) {
                    alt188=1;
                }
                else if ( (LA188_3==EQUAL||LA188_3==NOTEQUAL) && (synpred38_TextMarkerParser())) {
                    alt188=1;
                }
                else if ( (LA188_3==DOT) && (synpred38_TextMarkerParser())) {
                    alt188=1;
                }
                else if ( (LA188_3==EOF||LA188_3==COMMA||LA188_3==RPAREN||LA188_3==SEMI) ) {
                    alt188=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 188, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA188_0==BasicAnnotationType) && (synpred38_TextMarkerParser())) {
                alt188=1;
            }
            else if ( (LA188_0==LPAREN) && (synpred38_TextMarkerParser())) {
                alt188=1;
            }
            else if ( (LA188_0==XOR) && (synpred38_TextMarkerParser())) {
                alt188=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 188, 0, input);

                throw nvae;

            }
            switch (alt188) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1747:2: (e= composedBooleanExpression )=>e= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10540);
                    e=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1748:3: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10551);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1751:1: simpleBooleanExpression returns [BooleanExpression expr = null] : (e= literalBooleanExpression |{...}?id= Identifier );
    public final BooleanExpression simpleBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1752:2: (e= literalBooleanExpression |{...}?id= Identifier )
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==FALSE||LA189_0==TRUE) ) {
                alt189=1;
            }
            else if ( (LA189_0==Identifier) ) {
                alt189=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 189, 0, input);

                throw nvae;

            }
            switch (alt189) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1753:3: e= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10574);
                    e=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1754:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression10589); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1759:1: composedBooleanExpression returns [BooleanExpression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final BooleanExpression composedBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e2 =null;

        BooleanExpression bte =null;

        BooleanExpression bne =null;

        BooleanExpression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1761:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
            int alt190=4;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==TRUE) && (synpred39_TextMarkerParser())) {
                alt190=1;
            }
            else if ( (LA190_0==FALSE) && (synpred39_TextMarkerParser())) {
                alt190=1;
            }
            else if ( (LA190_0==Identifier) ) {
                int LA190_3 = input.LA(2);

                if ( (((synpred39_TextMarkerParser()&&synpred39_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt190=1;
                }
                else if ( (synpred40_TextMarkerParser()) ) {
                    alt190=2;
                }
                else if ( (true) ) {
                    alt190=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 190, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA190_0==BasicAnnotationType) && (synpred40_TextMarkerParser())) {
                alt190=2;
            }
            else if ( (LA190_0==LPAREN) && (synpred41_TextMarkerParser())) {
                alt190=3;
            }
            else if ( (LA190_0==XOR) ) {
                alt190=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 190, 0, input);

                throw nvae;

            }
            switch (alt190) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1762:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression10623);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1763:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression10643);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1764:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression10662);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1765:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression10672);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1769:1: booleanFunction returns [BooleanExpression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final BooleanExpression booleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;

        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1771:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==XOR) ) {
                alt191=1;
            }
            else if ( (LA191_0==Identifier) && (synpred42_TextMarkerParser())) {
                alt191=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 191, 0, input);

                throw nvae;

            }
            switch (alt191) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1772:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1772:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1772:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction10697); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction10699); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10705);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction10707); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10713);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction10715); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1774:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction10737);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1778:1: externalBooleanFunction returns [BooleanExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final BooleanExpression externalBooleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1779:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1781:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction10762); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction10764); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction10771);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction10773); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1789:1: booleanCompare returns [BooleanExpression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final BooleanExpression booleanCompare() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1790:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1791:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1791:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1791:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare10798);
            e1=simpleBooleanExpression();

            state._fsp--;
            if (state.failed) return expr;

            op=(Token)input.LT(1);

            if ( input.LA(1)==EQUAL||input.LA(1)==NOTEQUAL ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare10816);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1796:1: literalBooleanExpression returns [BooleanExpression expr = null] : (v= TRUE |v= FALSE );
    public final BooleanExpression literalBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1797:2: (v= TRUE |v= FALSE )
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==TRUE) ) {
                alt192=1;
            }
            else if ( (LA192_0==FALSE) ) {
                alt192=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 192, 0, input);

                throw nvae;

            }
            switch (alt192) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1798:2: v= TRUE
                    {
                    v=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression10842); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleBooleanExpression(v);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1799:4: v= FALSE
                    {
                    v=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression10854); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1804:1: booleanTypeExpression returns [BooleanExpression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final BooleanExpression booleanTypeExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        TypeExpression e1 =null;

        TypeExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1805:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1806:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10880);
            e1=typeExpression();

            state._fsp--;
            if (state.failed) return expr;

            op=(Token)input.LT(1);

            if ( input.LA(1)==EQUAL||input.LA(1)==NOTEQUAL ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10900);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1812:1: booleanNumberExpression returns [BooleanExpression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final BooleanExpression booleanNumberExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        NumberExpression e1 =null;

        NumberExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1813:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1814:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression10922); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10929);
            e1=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            op=(Token)input.LT(1);

            if ( input.LA(1)==EQUAL||(input.LA(1) >= GREATER && input.LA(1) <= GREATEREQUAL)||(input.LA(1) >= LESS && input.LA(1) <= LESSEQUAL)||input.LA(1)==NOTEQUAL ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10965);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression10968); if (state.failed) return expr;

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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:519:4: ( ruleElementComposed[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:519:5: ruleElementComposed[null]
        {
        pushFollow(FOLLOW_ruleElementComposed_in_synpred1_TextMarkerParser1855);
        ruleElementComposed(null);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_TextMarkerParser

    // $ANTLR start synpred2_TextMarkerParser
    public final void synpred2_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:520:4: ( ruleElementDisjunctive[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:520:5: ruleElementDisjunctive[null]
        {
        pushFollow(FOLLOW_ruleElementDisjunctive_in_synpred2_TextMarkerParser1872);
        ruleElementDisjunctive(null);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:642:2: ( booleanListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:642:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred7_TextMarkerParser2461);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:643:4: ( intListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:643:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred8_TextMarkerParser2477);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred9_TextMarkerParser
    public final void synpred9_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:644:4: ( doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:644:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred9_TextMarkerParser2493);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_TextMarkerParser

    // $ANTLR start synpred10_TextMarkerParser
    public final void synpred10_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:645:4: ( floatListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:645:5: floatListExpression
        {
        pushFollow(FOLLOW_floatListExpression_in_synpred10_TextMarkerParser2509);
        floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_TextMarkerParser

    // $ANTLR start synpred11_TextMarkerParser
    public final void synpred11_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:646:4: ( stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:646:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred11_TextMarkerParser2525);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:647:4: ( typeListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:647:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred12_TextMarkerParser2541);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred13_TextMarkerParser
    public final void synpred13_TextMarkerParser_fragment() throws RecognitionException {
        NumberListExpression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:686:2: (e1= doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:686:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred13_TextMarkerParser2748);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {
        NumberListExpression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:688:2: (e1= floatListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:688:3: e1= floatListExpression
        {
        pushFollow(FOLLOW_floatListExpression_in_synpred14_TextMarkerParser2769);
        e1=floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression tf =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:765:2: (tf= typeFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:765:2: tf= typeFunction
        {
        pushFollow(FOLLOW_typeFunction_in_synpred15_TextMarkerParser3177);
        tf=typeFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {
        AbstractTextMarkerCondition c =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred17_TextMarkerParser3749);
        c=externalCondition();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_TextMarkerParser

    // $ANTLR start synpred18_TextMarkerParser
    public final void synpred18_TextMarkerParser_fragment() throws RecognitionException {
        ListExpression type =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        match(input,COUNT,FOLLOW_COUNT_in_synpred18_TextMarkerParser4111); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred18_TextMarkerParser4113); if (state.failed) return ;

        pushFollow(FOLLOW_listExpression_in_synpred18_TextMarkerParser4119);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4121); if (state.failed) return ;

        pushFollow(FOLLOW_argument_in_synpred18_TextMarkerParser4127);
        a=argument();

        state._fsp--;
        if (state.failed) return ;

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
        int alt193=2;
        int LA193_0 = input.LA(1);

        if ( (LA193_0==COMMA) ) {
            int LA193_1 = input.LA(2);

            if ( (LA193_1==COS||LA193_1==DecimalLiteral||LA193_1==EXP||LA193_1==FloatingPointLiteral||(LA193_1 >= LOGN && LA193_1 <= LPAREN)||LA193_1==MINUS||LA193_1==SIN||LA193_1==TAN) ) {
                alt193=1;
            }
            else if ( (LA193_1==Identifier) ) {
                int LA193_4 = input.LA(3);

                if ( (LA193_4==COMMA||LA193_4==LPAREN||LA193_4==MINUS||(LA193_4 >= PERCENT && LA193_4 <= PLUS)||(LA193_4 >= SLASH && LA193_4 <= STAR)) ) {
                    alt193=1;
                }
            }
        }
        switch (alt193) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:60: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4130); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser4136);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4138); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser4144);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:928:5: ( COMMA var= numberVariable )?
        int alt194=2;
        int LA194_0 = input.LA(1);

        if ( (LA194_0==COMMA) ) {
            alt194=1;
        }
        switch (alt194) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:928:6: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4154); if (state.failed) return ;

                pushFollow(FOLLOW_numberVariable_in_synpred18_TextMarkerParser4160);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        match(input,RPAREN,FOLLOW_RPAREN_in_synpred18_TextMarkerParser4164); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {
        StringListExpression list2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:20: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:21: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred19_TextMarkerParser4438);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred20_TextMarkerParser
    public final void synpred20_TextMarkerParser_fragment() throws RecognitionException {
        AbstractTextMarkerAction a =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1124:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1124:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred20_TextMarkerParser6011);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_TextMarkerParser

    // $ANTLR start synpred21_TextMarkerParser
    public final void synpred21_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:5: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:6: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred21_TextMarkerParser6164);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:81: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1159:82: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred22_TextMarkerParser6177); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred22_TextMarkerParser6183);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred23_TextMarkerParser
    public final void synpred23_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:6: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:7: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred23_TextMarkerParser6476);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred23_TextMarkerParser

    // $ANTLR start synpred24_TextMarkerParser
    public final void synpred24_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:82: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:83: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred24_TextMarkerParser6489); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred24_TextMarkerParser6495);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred24_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:22: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:23: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred28_TextMarkerParser7300);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:85: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:86: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred29_TextMarkerParser7320);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1558:2: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1558:2: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred31_TextMarkerParser9492);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1559:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1559:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred32_TextMarkerParser9503);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred33_TextMarkerParser
    public final void synpred33_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1560:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1560:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred33_TextMarkerParser9514);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred33_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1639:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1639:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred34_TextMarkerParser9873);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred36_TextMarkerParser
    public final void synpred36_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1714:3: (e= stringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1714:4: e= stringFunction
        {
        pushFollow(FOLLOW_stringFunction_in_synpred36_TextMarkerParser10353);
        e=stringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred36_TextMarkerParser

    // $ANTLR start synpred37_TextMarkerParser
    public final void synpred37_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1724:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1724:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred37_TextMarkerParser10425);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred37_TextMarkerParser

    // $ANTLR start synpred38_TextMarkerParser
    public final void synpred38_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1747:2: (e= composedBooleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1747:3: e= composedBooleanExpression
        {
        pushFollow(FOLLOW_composedBooleanExpression_in_synpred38_TextMarkerParser10532);
        e=composedBooleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred38_TextMarkerParser

    // $ANTLR start synpred39_TextMarkerParser
    public final void synpred39_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1762:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1762:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred39_TextMarkerParser10615);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred39_TextMarkerParser

    // $ANTLR start synpred40_TextMarkerParser
    public final void synpred40_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1763:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1763:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred40_TextMarkerParser10635);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred40_TextMarkerParser

    // $ANTLR start synpred41_TextMarkerParser
    public final void synpred41_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1764:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1764:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred41_TextMarkerParser10654);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred41_TextMarkerParser

    // $ANTLR start synpred42_TextMarkerParser
    public final void synpred42_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1774:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1774:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred42_TextMarkerParser10729);
        e=externalBooleanFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred42_TextMarkerParser

    // Delegated rules

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
    public final boolean synpred23_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_TextMarkerParser_fragment(); // can never throw exception
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
    public final boolean synpred33_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred33_TextMarkerParser_fragment(); // can never throw exception
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
    public final boolean synpred41_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_TextMarkerParser_fragment(); // can never throw exception
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
    public final boolean synpred14_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_TextMarkerParser_fragment(); // can never throw exception
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
    public final boolean synpred24_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_TextMarkerParser_fragment(); // can never throw exception
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
    public final boolean synpred28_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred42_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred42_TextMarkerParser_fragment(); // can never throw exception
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


    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA31_eotS =
        "\11\uffff";
    static final String DFA31_eofS =
        "\11\uffff";
    static final String DFA31_minS =
        "\1\45\1\22\1\113\2\32\1\113\2\uffff\1\47";
    static final String DFA31_maxS =
        "\1\45\2\113\2\u0080\1\113\2\uffff\1\113";
    static final String DFA31_acceptS =
        "\6\uffff\1\1\1\2\1\uffff";
    static final String DFA31_specialS =
        "\11\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\1",
            "\1\2\70\uffff\1\3",
            "\1\4",
            "\1\6\14\uffff\1\5\43\uffff\1\4\64\uffff\1\6",
            "\1\6\74\uffff\1\7\50\uffff\1\6",
            "\1\10",
            "",
            "",
            "\1\5\43\uffff\1\4"
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "360:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
        }
    }
 

    public static final BitSet FOLLOW_packageDeclaration_in_file_input76 = new BitSet(new long[]{0x0C041120101E8010L,0x0000000000801900L,0x000000000C1C0780L});
    public static final BitSet FOLLOW_globalStatements_in_file_input92 = new BitSet(new long[]{0x0C001120101E8010L,0x0000000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_file_input101 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file_input109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PackageString_in_packageDeclaration124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_packageDeclaration130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements155 = new BitSet(new long[]{0x0C001120101E8012L,0x0000000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_globalStatement_in_globalStatements180 = new BitSet(new long[]{0x0004000000000002L,0x0000000000000000L,0x0000000000100100L});
    public static final BitSet FOLLOW_importStatement_in_globalStatement204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_statement230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStatement_in_statement252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockDeclaration_in_statement263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_automataDeclaration_in_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntString_in_variableDeclaration304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration313 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration328 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration338 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration344 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration370 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration385 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration396 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatString_in_variableDeclaration418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration427 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration442 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration453 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration475 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration484 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration491 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration499 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration510 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration516 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration541 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration548 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration556 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration567 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration598 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration613 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration624 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration630 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration647 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration659 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration698 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration701 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration735 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration738 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration761 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration773 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration776 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration782 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration811 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration837 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration849 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATLIST_in_variableDeclaration875 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration887 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration925 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration928 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONDITION_in_conditionDeclaration992 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_conditionDeclaration998 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1000 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionDeclaration1002 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionDeclaration1008 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionDeclaration1010 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_conditionDeclaration1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_actionDeclaration1048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_actionDeclaration1054 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDeclaration1058 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_actionDeclaration1064 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDeclaration1066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_actionDeclaration1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement1093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement1107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement1121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1154 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_declaration1164 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1172 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_declaration1179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1193 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_declaration1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1209 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_declaration1215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1227 = new BitSet(new long[]{0x0800100000140000L,0x0000000000001800L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotationType_in_declaration1242 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_StringString_in_declaration1255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1268 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_FloatString_in_declaration1280 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_IntString_in_declaration1292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1320 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1332 = new BitSet(new long[]{0x0800100000140000L,0x0000000000001800L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotationType_in_declaration1347 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_StringString_in_declaration1360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1373 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_FloatString_in_declaration1385 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_IntString_in_declaration1397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1425 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1433 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_declaration1436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1494 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1505 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1509 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1533 = new BitSet(new long[]{0x0C001120101E8010L,0x0020000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1539 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AutomataBlockString_in_automataDeclaration1593 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_automataDeclaration1597 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_automataDeclaration1604 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_automataDeclaration1608 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_automataDeclaration1621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_automataDeclaration1630 = new BitSet(new long[]{0x0C001120101E8010L,0x0020000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_automataDeclaration1636 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_automataDeclaration1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1675 = new BitSet(new long[]{0x0000000000000000L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1704 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1710 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1714 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1720 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1795 = new BitSet(new long[]{0x0000000000040002L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1805 = new BitSet(new long[]{0x0000000000040002L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_ruleElement1881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementDisjunctive1913 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1938 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1944 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1953 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1959 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementDisjunctive1968 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementDisjunctive1994 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementDisjunctive2007 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementDisjunctive2013 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementDisjunctive2017 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementDisjunctive2023 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementDisjunctive2027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed2068 = new BitSet(new long[]{0x0000000000040000L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed2096 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed2106 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed2112 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed2116 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed2122 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2126 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2132 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2179 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2198 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2211 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2217 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2221 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementType2227 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2278 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2302 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2315 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2321 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2325 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2331 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2373 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_conditions2378 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditions2384 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_action_in_actions2422 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_actions2427 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_action_in_actions2433 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_listExpression2517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2592 = new BitSet(new long[]{0x0020000000000000L,0x0020000000000800L,0x0000000000020000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2599 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2604 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000800L,0x0000000000020000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2610 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2680 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2687 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2692 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2698 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_numberListExpression2777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2833 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2840 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2845 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2851 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleFloatListExpression_in_floatListExpression2901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleFloatListExpression2922 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2929 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleFloatListExpression2934 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2940 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleFloatListExpression2949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleFloatListExpression2964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression3009 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression3016 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression3021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression3027 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression3036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression3052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression3098 = new BitSet(new long[]{0x0000000000040000L,0x0020000000000800L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3105 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression3110 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3116 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression3125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression3140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression3177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression3188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction3222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction3247 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3249 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3256 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeExpression3283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3381 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3398 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3414 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3431 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3437 = new BitSet(new long[]{0x0000000004000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3444 = new BitSet(new long[]{0x2001040100000000L,0x0010000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3451 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3457 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3825 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3831 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3862 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3864 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3870 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3926 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3933 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3941 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3943 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionContains3949 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3958 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3964 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3966 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3972 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3975 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3981 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount4020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount4022 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount4028 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4031 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount4037 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4039 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount4045 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4055 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount4061 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount4065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount4119 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4121 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionCount4127 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4130 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4136 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4138 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4144 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4160 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4182 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4184 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount4190 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4193 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4199 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4201 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4207 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4223 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4263 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4265 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4271 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4274 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4280 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4282 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4288 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4303 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4340 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4342 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4348 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4351 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4357 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4359 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4365 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4381 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4430 = new BitSet(new long[]{0x0000000000000000L,0x4000000000020800L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4445 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4453 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4457 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4463 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4466 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4472 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4515 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4521 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4524 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4530 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4532 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4538 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4583 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4585 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4591 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4642 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4648 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4650 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4656 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4658 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4664 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4703 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4709 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4711 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4717 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4719 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4725 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4728 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4734 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4737 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4743 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4785 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditionNot4791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4800 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditionNot4806 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4849 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4855 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4887 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4889 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4896 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4902 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4935 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4937 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq4944 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq4950 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq4953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition4987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition4989 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition4995 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition4997 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition5003 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition5005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp5035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp5037 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp5043 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp5046 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp5052 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp5056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore5091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore5093 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5099 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5102 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5108 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore5121 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore5128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote5163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote5165 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5171 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote5173 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5179 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote5181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf5219 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf5221 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf5227 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf5229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5263 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5265 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5271 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5273 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5279 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5317 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_conditionParse5325 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5358 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5360 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5367 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5373 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5409 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5416 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5422 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5458 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5465 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5471 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5505 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5507 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5514 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5520 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5554 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5556 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5563 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5569 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5611 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5614 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5620 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5622 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5628 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5639 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action5748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action5766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action5793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action5802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action5811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action5820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action5829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action5838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action5847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action5856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action5865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action5874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action5883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action5892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action5901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action5910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action5919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action5928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action5937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action5946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action5955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action5964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action5973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action5982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionConfigure_in_action5991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDynamicAnchoring_in_action6000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action6019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action6028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction6057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction6085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction6087 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction6093 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction6095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate6131 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate6133 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate6139 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6146 = new BitSet(new long[]{0x2001040100000000L,0x2200000800C00800L,0x0000000000001204L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6171 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6188 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6194 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6201 = new BitSet(new long[]{0x0000000000000000L,0x2200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6214 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6216 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionCreate6222 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6232 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6238 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6240 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionCreate6246 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6304 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6315 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6317 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6328 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6330 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6340 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6348 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6362 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6364 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6370 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6380 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6386 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6388 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6394 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6450 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6456 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6463 = new BitSet(new long[]{0x2001040100000000L,0x2200000800C00800L,0x0000000000001204L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6483 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6499 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6505 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6512 = new BitSet(new long[]{0x0000000000000000L,0x2200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6525 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6527 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C20800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6534 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6542 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6553 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6559 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6561 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C20800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6568 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6576 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6636 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6642 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6645 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6651 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6653 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionFill6670 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6727 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6733 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6745 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6756 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6764 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6774 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6782 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor6792 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor6802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel6836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog6878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog6880 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog6886 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionLog6889 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog6895 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog6899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark6938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark6940 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark6951 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMark6967 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark6983 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark7007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand7051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand7053 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand7064 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand7080 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand7096 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand7120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore7165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore7167 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7178 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7180 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore7190 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7206 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7222 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore7246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7290 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7292 = new BitSet(new long[]{0x2001040100040000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7309 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7311 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7329 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7345 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7361 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7419 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7421 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7427 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7429 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7435 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7438 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7444 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7447 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7453 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7495 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7501 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7537 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7539 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7545 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7590 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7593 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7599 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7604 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7610 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7662 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7665 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7671 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7676 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7682 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup7736 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup7739 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7745 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup7750 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7756 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup7762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType7808 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType7811 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7817 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType7822 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7828 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType7834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall7874 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall7876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionCall7882 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall7884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONFIGURE_in_actionConfigure7922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionConfigure7924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionConfigure7930 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure7937 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure7947 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure7949 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionConfigure7955 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure7965 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure7971 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure7973 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionConfigure7979 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionConfigure7989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec8021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec8023 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionExec8029 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExec8032 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec8038 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec8042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign8085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign8087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8114 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8116 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionAssign8122 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8160 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8162 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionAssign8168 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8206 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8208 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionAssign8214 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8252 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8254 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8260 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8299 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8301 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8307 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8345 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8347 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8353 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8406 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8412 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8414 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8420 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8461 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8463 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8469 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8477 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDynamicAnchoring8517 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionDynamicAnchoring8523 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8531 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8537 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8545 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8551 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDynamicAnchoring8568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8600 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8606 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8646 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8652 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8666 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8705 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8707 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8713 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8757 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8767 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8769 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8775 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8783 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8789 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8791 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8797 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8807 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8813 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8820 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8826 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8833 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8839 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8846 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8852 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8859 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8865 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8872 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8878 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie8884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd8929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd8931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd8937 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd8940 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionAdd8946 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd8952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove8992 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove8994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove9000 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove9003 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionRemove9009 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove9015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate9053 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate9059 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate9061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge9106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge9108 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge9114 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge9122 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9130 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9135 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9141 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge9147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet9182 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet9184 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionGet9190 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionGet9198 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9200 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet9206 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet9208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList9243 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList9245 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList9251 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList9253 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList9259 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList9261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText9300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText9302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText9313 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText9329 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText9335 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText9359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear9399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear9401 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionClear9407 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear9409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList9436 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9442 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList9446 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9452 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList9458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument9492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument9503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9559 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9582 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29608 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_dottedIdentifier29621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29635 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9667 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedId9680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9690 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType9725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationType9737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression9762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression9775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression9799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression9812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction9835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction9857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction9881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction9907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction9909 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction9916 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction9918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9999 = new BitSet(new long[]{0x0000000000000002L,0x0001000800000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression10008 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10021 = new BitSet(new long[]{0x0000000000000002L,0x0001000800000000L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10054 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression10063 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10082 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression10100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression10123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar10141 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpressionInPar10148 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar10150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10173 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression10180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10194 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression10219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression10230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10270 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression10277 = new BitSet(new long[]{0x0020000000040000L,0x0000000000820800L,0x0000000000020200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10284 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression10297 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression10309 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression10321 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression10333 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression10361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction10388 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction10390 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_stringFunction10396 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction10399 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction10405 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction10411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction10433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction10458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction10460 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction10467 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction10469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression10493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression10507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression10589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression10623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression10643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression10662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression10672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction10697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction10699 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10705 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction10707 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10713 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction10715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction10737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction10762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction10764 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction10771 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction10773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare10798 = new BitSet(new long[]{0x0000400000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_set_in_booleanCompare10804 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare10816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression10842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression10854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10880 = new BitSet(new long[]{0x0000400000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression10887 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression10922 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10929 = new BitSet(new long[]{0x0000400000000000L,0x00000080000C000CL});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression10936 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10965 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression10968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_synpred1_TextMarkerParser1855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_synpred2_TextMarkerParser1872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred7_TextMarkerParser2461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred8_TextMarkerParser2477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred9_TextMarkerParser2493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred10_TextMarkerParser2509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred11_TextMarkerParser2525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred12_TextMarkerParser2541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred13_TextMarkerParser2748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred14_TextMarkerParser2769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_synpred15_TextMarkerParser3177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred17_TextMarkerParser3749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred18_TextMarkerParser4111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred18_TextMarkerParser4113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_synpred18_TextMarkerParser4119 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4121 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_synpred18_TextMarkerParser4127 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4130 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser4136 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4138 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser4144 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_synpred18_TextMarkerParser4160 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred18_TextMarkerParser4164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred19_TextMarkerParser4438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred20_TextMarkerParser6011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred21_TextMarkerParser6164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred22_TextMarkerParser6177 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred22_TextMarkerParser6183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred23_TextMarkerParser6476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred24_TextMarkerParser6489 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred24_TextMarkerParser6495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred28_TextMarkerParser7300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred29_TextMarkerParser7320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred31_TextMarkerParser9492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred32_TextMarkerParser9503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred33_TextMarkerParser9514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred34_TextMarkerParser9873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_synpred36_TextMarkerParser10353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred37_TextMarkerParser10425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_synpred38_TextMarkerParser10532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred39_TextMarkerParser10615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred40_TextMarkerParser10635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred41_TextMarkerParser10654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred42_TextMarkerParser10729 = new BitSet(new long[]{0x0000000000000002L});

}