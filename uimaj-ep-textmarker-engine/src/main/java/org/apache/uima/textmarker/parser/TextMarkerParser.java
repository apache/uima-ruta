// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g 2011-10-21 15:26:08

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ADD", "AFTER", "ALT_NOTEQUAL", "AMPER", "AND", "ASSIGN", "ASSIGN_EQUAL", "AT", "ATTRIBUTE", "Annotation", "AutomataBlockString", "BEFORE", "BOOLEANLIST", "BasicAnnotationType", "BlockString", "BooleanString", "CALL", "CIRCUMFLEX", "CLEAR", "COLON", "COLOR", "COMMA", "COMMENT", "CONDITION", "CONFIGURE", "CONTAINS", "CONTEXTCOUNT", "COS", "COUNT", "CREATE", "CURRENTCOUNT", "CharacterLiteral", "DECLARE", "DEL", "DOT", "DOUBLELIST", "DYNAMICANCHORING", "DecimalLiteral", "DocComment", "DoubleString", "ENDSWITH", "EQUAL", "EXEC", "EXP", "EXPAND", "EngineString", "EscapeSequence", "Exponent", "FALSE", "FEATURE", "FILL", "FILTERMARKUP", "FILTERTYPE", "FloatTypeSuffix", "FloatingPointLiteral", "GATHER", "GET", "GETFEATURE", "GETLIST", "GREATER", "GREATEREQUAL", "HexDigit", "HexLiteral", "IF", "INLIST", "INTLIST", "IS", "ISINTAG", "Identifier", "IntString", "IntegerTypeSuffix", "JavaIDDigit", "LAST", "LBRACK", "LCURLY", "LESS", "LESSEQUAL", "LINE_COMMENT", "LOG", "LOGN", "LPAREN", "Letter", "ListIdentifier", "LogLevel", "MARK", "MARKFAST", "MARKLAST", "MARKONCE", "MARKSCORE", "MARKTABLE", "MATCHEDTEXT", "MERGE", "MINUS", "MOFN", "NEAR", "NOT", "NOTEQUAL", "OR", "OctalEscape", "OctalLiteral", "OldColor", "PARSE", "PARTOF", "PARTOFNEQ", "PERCENT", "PLUS", "POSITION", "PackageString", "QUESTION", "RBRACK", "RCURLY", "REGEXP", "REMOVE", "REMOVEDUPLICATE", "REMOVESTRING", "REPLACE", "RETAINMARKUP", "RETAINTYPE", "RPAREN", "RessourceLiteral", "SCORE", "SEMI", "SETFEATURE", "SIN", "SIZE", "SLASH", "STAR", "STARTSWITH", "STRINGLIST", "ScriptString", "StringLiteral", "StringString", "SymbolString", "TAN", "THEN", "TOTALCOUNT", "TRANSFER", "TRIE", "TRUE", "TYPELIST", "TypeString", "TypeSystemString", "UNMARK", "UNMARKALL", "UnicodeEscape", "VBAR", "VOTE", "WORDLIST", "WORDTABLE", "WS", "XOR"
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
    	
    	private TypeSystemDescription localTSD;
    	private String[] resourcePaths;
    	

    	public void setResourcePaths(String[] resourcePaths) {
    		this.resourcePaths = resourcePaths;
    	}
    	
    	 public void setLocalTSD(TypeSystemDescription localTSD) {
          		this.localTSD = localTSD;
       	 }



    // $ANTLR start "file_input"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:198:1: file_input[String moduleName] returns [TextMarkerModule module] : p= packageDeclaration gs= globalStatements s= statements EOF ;
    public final TextMarkerModule file_input(String moduleName) throws RecognitionException {
        TextMarkerModule module = null;


        String p =null;

        List<TextMarkerStatement> gs =null;

        List<TextMarkerStatement> s =null;



        TextMarkerScriptBlock rootBlock = null;
        List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:203:2: (p= packageDeclaration gs= globalStatements s= statements EOF )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:204:2: p= packageDeclaration gs= globalStatements s= statements EOF
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:220:1: packageDeclaration returns [String pack = \"\"] : PackageString p= dottedIdentifier SEMI ;
    public final String packageDeclaration() throws RecognitionException {
        String pack =  "";


        String p =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:221:2: ( PackageString p= dottedIdentifier SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:221:4: PackageString p= dottedIdentifier SEMI
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:224:1: statements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (stmt= statement )* ;
    public final List<TextMarkerStatement> statements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        TextMarkerStatement stmt =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:225:2: ( (stmt= statement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:226:2: (stmt= statement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:226:2: (stmt= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ACTION||LA1_0==AutomataBlockString||(LA1_0 >= BOOLEANLIST && LA1_0 <= BooleanString)||LA1_0==CONDITION||LA1_0==DECLARE||LA1_0==DOUBLELIST||LA1_0==DoubleString||LA1_0==INTLIST||(LA1_0 >= Identifier && LA1_0 <= IntString)||LA1_0==LPAREN||LA1_0==STRINGLIST||(LA1_0 >= StringLiteral && LA1_0 <= StringString)||(LA1_0 >= TYPELIST && LA1_0 <= TypeString)||(LA1_0 >= WORDLIST && LA1_0 <= WORDTABLE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:226:3: stmt= statement
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:229:1: globalStatements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (morestmts= globalStatement )* ;
    public final List<TextMarkerStatement> globalStatements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        List<TextMarkerStatement> morestmts =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:230:2: ( (morestmts= globalStatement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:231:2: (morestmts= globalStatement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:231:2: (morestmts= globalStatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==EngineString||LA2_0==ScriptString||LA2_0==TypeSystemString) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:231:3: morestmts= globalStatement
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:234:1: globalStatement returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : stmtImport= importStatement ;
    public final List<TextMarkerStatement> globalStatement() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();


        TextMarkerStatement stmtImport =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:235:2: (stmtImport= importStatement )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:236:2: stmtImport= importStatement
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:239:1: statement returns [TextMarkerStatement stmt = null] : (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration ) ;
    public final TextMarkerStatement statement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        TextMarkerStatement stmtDecl =null;

        TextMarkerStatement stmtVariable =null;

        TextMarkerRule stmtRule =null;

        TextMarkerBlock stmtBlock =null;

        TextMarkerBlock stmtAutomata =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:240:2: ( (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:241:2: (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:241:2: (stmtDecl= declaration |stmtVariable= variableDeclaration |stmtRule= simpleStatement |stmtBlock= blockDeclaration |stmtAutomata= automataDeclaration )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:241:4: stmtDecl= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_statement230);
                    stmtDecl=declaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtDecl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:242:4: stmtVariable= variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement241);
                    stmtVariable=variableDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtVariable;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:243:4: stmtRule= simpleStatement
                    {
                    pushFollow(FOLLOW_simpleStatement_in_statement252);
                    stmtRule=simpleStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtRule;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:244:4: stmtBlock= blockDeclaration
                    {
                    pushFollow(FOLLOW_blockDeclaration_in_statement263);
                    stmtBlock=blockDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmtBlock;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:245:4: stmtAutomata= automataDeclaration
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:249:1: variableDeclaration returns [TextMarkerStatement stmt = null] : (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:250:2: (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:251:2: type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI
                    {
                    type=(Token)match(input,IntString,FOLLOW_IntString_in_variableDeclaration299); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration308); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:253:3: ( COMMA {...}?id= Identifier )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==COMMA) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:253:4: COMMA {...}?id= Identifier
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:254:6: ( ASSIGN_EQUAL value1= numberExpression )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==ASSIGN_EQUAL) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:254:7: ASSIGN_EQUAL value1= numberExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:256:2: type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI
                    {
                    type=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_variableDeclaration356); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration365); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:258:3: ( COMMA {...}?id= Identifier )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:258:4: COMMA {...}?id= Identifier
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:259:7: ( ASSIGN_EQUAL value2= numberExpression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==ASSIGN_EQUAL) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:259:8: ASSIGN_EQUAL value2= numberExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:261:2: type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration413); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration422); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:263:3: ( COMMA {...}?id= Identifier )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:263:4: COMMA {...}?id= Identifier
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:264:7: ( ASSIGN_EQUAL value3= stringExpression )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==ASSIGN_EQUAL) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:264:8: ASSIGN_EQUAL value3= stringExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:266:2: type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration470); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration479); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:268:3: ( COMMA {...}?id= Identifier )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:268:4: COMMA {...}?id= Identifier
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:269:7: ( ASSIGN_EQUAL value4= booleanExpression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ASSIGN_EQUAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:269:8: ASSIGN_EQUAL value4= booleanExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:271:2: type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration527); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration536); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:273:3: ( COMMA {...}?id= Identifier )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==COMMA) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:273:4: COMMA {...}?id= Identifier
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:274:7: ( ASSIGN_EQUAL value5= annotationType )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==ASSIGN_EQUAL) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:274:8: ASSIGN_EQUAL value5= annotationType
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:276:2: type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration585); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration597); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:278:20: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==ASSIGN_EQUAL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:278:21: ASSIGN_EQUAL list= wordListExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:280:2: type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration624); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration636); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:282:20: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:282:21: ASSIGN_EQUAL table= wordTableExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:284:2: type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration661); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration673); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:286:20: ( ASSIGN_EQUAL bl= booleanListExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:286:21: ASSIGN_EQUAL bl= booleanListExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:288:2: type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration699); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration711); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:290:20: ( ASSIGN_EQUAL sl= stringListExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:290:21: ASSIGN_EQUAL sl= stringListExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:292:2: type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration737); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration749); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:294:20: ( ASSIGN_EQUAL il= numberListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:294:21: ASSIGN_EQUAL il= numberListExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:296:2: type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration775); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration787); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:298:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:298:21: ASSIGN_EQUAL dl= numberListExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:300:2: type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration813); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration825); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:302:20: ( ASSIGN_EQUAL tl= typeListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:302:21: ASSIGN_EQUAL tl= typeListExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:304:2: stmt1= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration851);
                    stmt1=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmt1;}

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:306:2: stmt2= actionDeclaration
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:310:1: conditionDeclaration returns [TextMarkerStatement stmt = null] : type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final TextMarkerStatement conditionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerCondition> cons =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:311:5: (type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:312:5: type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:319:1: actionDeclaration returns [TextMarkerStatement stmt = null] : type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final TextMarkerStatement actionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:320:5: (type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:321:5: type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:327:1: importStatement returns [TextMarkerStatement stmt = null] : ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI );
    public final TextMarkerStatement importStatement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        String ts =null;

        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:328:2: ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:329:2: TypeSystemString ts= dottedIdentifier2 SEMI
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:330:4: ScriptString ns= dottedIdentifier2 SEMI
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:331:4: EngineString ns= dottedIdentifier2 SEMI
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:334:1: declaration returns [TextMarkerStatement stmt = null] : ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:339:2: ( ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:340:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:340:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:341:2: DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1054); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:343:13: (lazyParent= annotationType )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==BasicAnnotationType) ) {
                        alt23=1;
                    }
                    else if ( (LA23_0==Identifier) ) {
                        int LA23_2 = input.LA(2);

                        if ( (LA23_2==DOT||LA23_2==Identifier) ) {
                            alt23=1;
                        }
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:343:13: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1064);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1072); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:345:4: ( COMMA id= Identifier )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:345:5: COMMA id= Identifier
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:350:2: DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1109); if (state.failed) return stmt;

                    pushFollow(FOLLOW_annotationType_in_declaration1115);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmt;

                    newName=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1121); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:351:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:351:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1127); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:352:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString )
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:353:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1142);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:354:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1155); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:355:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1168); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:356:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1180); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:357:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1192); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1208); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:4: ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:361:4: COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1220); if (state.failed) return stmt;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:362:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString )
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
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:363:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1235);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:364:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1248); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:365:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1261); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:366:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1273); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:367:6: obj5= BooleanString
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:376:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:393:2: (type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:394:2: type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1370); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1374); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1381); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1385); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createScriptBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);}

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:415:1: automataDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:434:2: (type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:436:2: type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_automataDeclaration1469); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_automataDeclaration1473); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_automataDeclaration1480); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_automataDeclaration1484); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createAutomataBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);}

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:458:1: ruleElementWithCA[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression idRef =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:459:5: (idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:461:5: idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1551);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(idRef,null,null,null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:463:7: (q= quantifierPart )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LBRACK||LA29_0==PLUS||LA29_0==QUESTION||LA29_0==STAR) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:463:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1568);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1580); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:464:18: (c= conditions )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==AFTER||LA30_0==AND||LA30_0==BEFORE||(LA30_0 >= CONTAINS && LA30_0 <= CONTEXTCOUNT)||LA30_0==COUNT||LA30_0==CURRENTCOUNT||LA30_0==ENDSWITH||LA30_0==FEATURE||(LA30_0 >= IF && LA30_0 <= INLIST)||(LA30_0 >= IS && LA30_0 <= Identifier)||LA30_0==LAST||(LA30_0 >= MINUS && LA30_0 <= NOT)||LA30_0==OR||(LA30_0 >= PARSE && LA30_0 <= PARTOFNEQ)||LA30_0==POSITION||LA30_0==REGEXP||LA30_0==SCORE||LA30_0==SIZE||LA30_0==STARTSWITH||LA30_0==TOTALCOUNT||LA30_0==VOTE) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:464:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1586);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:464:32: ( THEN a= actions )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==THEN) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:464:33: THEN a= actions
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:480:1: simpleStatement returns [TextMarkerRule stmt = null] :elements= ruleElements[stmt.getRoot()] SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;


        List<RuleElement> elements =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:481:2: (elements= ruleElements[stmt.getRoot()] SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:482:2: elements= ruleElements[stmt.getRoot()] SEMI
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:487:1: ruleElements[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()] : re= ruleElement[container] (re= ruleElement[container] )* ;
    public final List<RuleElement> ruleElements(RuleElementContainer container) throws RecognitionException {
        List<RuleElement> elements =  new ArrayList<RuleElement>();


        RuleElement re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:488:2: (re= ruleElement[container] (re= ruleElement[container] )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:489:2: re= ruleElement[container] (re= ruleElement[container] )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1671);
            re=ruleElement(container);

            state._fsp--;
            if (state.failed) return elements;

            if ( state.backtracking==0 ) {elements.add(re);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:489:50: (re= ruleElement[container] )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==BasicAnnotationType||LA32_0==Identifier||LA32_0==LPAREN||LA32_0==StringLiteral) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:489:51: re= ruleElement[container]
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:493:1: ruleElement[RuleElementContainer container] returns [RuleElement re = null] : (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] );
    public final RuleElement ruleElement(RuleElementContainer container) throws RecognitionException {
        RuleElement re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        ComposedRuleElement re3 =null;

        TextMarkerRuleElement re4 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:494:2: (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:495:2: re1= ruleElementType[container]
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1710);
                    re1=ruleElementType(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:496:4: re2= ruleElementLiteral[container]
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1722);
                    re2=ruleElementLiteral(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:497:4: ( ruleElementComposed[null] )=>re3= ruleElementComposed[container]
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1739);
                    re3=ruleElementComposed(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:498:4: ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container]
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:501:1: ruleElementDisjunctive[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:505:5: ( LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:506:5: LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
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

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:509:5: ( VBAR type3= typeExpression )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==VBAR) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:509:6: VBAR type3= typeExpression
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

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:513:8: (q= quantifierPart )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LBRACK||LA35_0==PLUS||LA35_0==QUESTION||LA35_0==STAR) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:513:8: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementDisjunctive1870);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LCURLY) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementDisjunctive1883); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:19: (c= conditions )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==AFTER||LA36_0==AND||LA36_0==BEFORE||(LA36_0 >= CONTAINS && LA36_0 <= CONTEXTCOUNT)||LA36_0==COUNT||LA36_0==CURRENTCOUNT||LA36_0==ENDSWITH||LA36_0==FEATURE||(LA36_0 >= IF && LA36_0 <= INLIST)||(LA36_0 >= IS && LA36_0 <= Identifier)||LA36_0==LAST||(LA36_0 >= MINUS && LA36_0 <= NOT)||LA36_0==OR||(LA36_0 >= PARSE && LA36_0 <= PARTOFNEQ)||LA36_0==POSITION||LA36_0==REGEXP||LA36_0==SCORE||LA36_0==SIZE||LA36_0==STARTSWITH||LA36_0==TOTALCOUNT||LA36_0==VOTE) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementDisjunctive1889);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:33: ( THEN a= actions )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==THEN) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:514:34: THEN a= actions
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:528:1: ruleElementComposed[RuleElementContainer container] returns [ComposedRuleElement re = null] : LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final ComposedRuleElement ruleElementComposed(RuleElementContainer container) throws RecognitionException {
        ruleElementComposed_stack.push(new ruleElementComposed_scope());
        ComposedRuleElement re =  null;


        List<RuleElement> res =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;





        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:535:2: ( LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:536:2: LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            if ( state.backtracking==0 ) {re = factory.createComposedRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	// dre = factory.createDisjunctiveRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con = re;}

            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed1944); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:541:2: ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:547:2: ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con]
            {
            pushFollow(FOLLOW_ruleElements_in_ruleElementComposed1972);
            res=ruleElements(((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con);

            state._fsp--;
            if (state.failed) return re;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed1982); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:11: (q= quantifierPart )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==LBRACK||LA39_0==PLUS||LA39_0==QUESTION||LA39_0==STAR) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:11: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed1988);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:29: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LCURLY) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:30: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed1992); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:39: (c= conditions )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==AFTER||LA40_0==AND||LA40_0==BEFORE||(LA40_0 >= CONTAINS && LA40_0 <= CONTEXTCOUNT)||LA40_0==COUNT||LA40_0==CURRENTCOUNT||LA40_0==ENDSWITH||LA40_0==FEATURE||(LA40_0 >= IF && LA40_0 <= INLIST)||(LA40_0 >= IS && LA40_0 <= Identifier)||LA40_0==LAST||(LA40_0 >= MINUS && LA40_0 <= NOT)||LA40_0==OR||(LA40_0 >= PARSE && LA40_0 <= PARTOFNEQ)||LA40_0==POSITION||LA40_0==REGEXP||LA40_0==SCORE||LA40_0==SIZE||LA40_0==STARTSWITH||LA40_0==TOTALCOUNT||LA40_0==VOTE) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:39: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed1998);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:53: ( THEN a= actions )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==THEN) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:550:54: THEN a= actions
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:566:1: ruleElementType[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression typeExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:567:5: ( ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:569:5: ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2055);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(typeExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:571:7: (q= quantifierPart )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==LBRACK||LA43_0==PLUS||LA43_0==QUESTION||LA43_0==STAR) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:571:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2074);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LCURLY) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2087); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:19: (c= conditions )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==AFTER||LA44_0==AND||LA44_0==BEFORE||(LA44_0 >= CONTAINS && LA44_0 <= CONTEXTCOUNT)||LA44_0==COUNT||LA44_0==CURRENTCOUNT||LA44_0==ENDSWITH||LA44_0==FEATURE||(LA44_0 >= IF && LA44_0 <= INLIST)||(LA44_0 >= IS && LA44_0 <= Identifier)||LA44_0==LAST||(LA44_0 >= MINUS && LA44_0 <= NOT)||LA44_0==OR||(LA44_0 >= PARSE && LA44_0 <= PARTOFNEQ)||LA44_0==POSITION||LA44_0==REGEXP||LA44_0==SCORE||LA44_0==SIZE||LA44_0==STARTSWITH||LA44_0==TOTALCOUNT||LA44_0==VOTE) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2093);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:33: ( THEN a= actions )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==THEN) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:572:34: THEN a= actions
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:586:1: ruleElementLiteral[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        StringExpression stringExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:587:5: ( ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:589:5: ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2154);
            stringExpr=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(stringExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:592:7: (q= quantifierPart )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==LBRACK||LA47_0==PLUS||LA47_0==QUESTION||LA47_0==STAR) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:592:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2178);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LCURLY) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2191); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:19: (c= conditions )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==AFTER||LA48_0==AND||LA48_0==BEFORE||(LA48_0 >= CONTAINS && LA48_0 <= CONTEXTCOUNT)||LA48_0==COUNT||LA48_0==CURRENTCOUNT||LA48_0==ENDSWITH||LA48_0==FEATURE||(LA48_0 >= IF && LA48_0 <= INLIST)||(LA48_0 >= IS && LA48_0 <= Identifier)||LA48_0==LAST||(LA48_0 >= MINUS && LA48_0 <= NOT)||LA48_0==OR||(LA48_0 >= PARSE && LA48_0 <= PARTOFNEQ)||LA48_0==POSITION||LA48_0==REGEXP||LA48_0==SCORE||LA48_0==SIZE||LA48_0==STARTSWITH||LA48_0==TOTALCOUNT||LA48_0==VOTE) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2197);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:33: ( THEN a= actions )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==THEN) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:593:34: THEN a= actions
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:607:1: conditions returns [List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<AbstractTextMarkerCondition> conditions() throws RecognitionException {
        List<AbstractTextMarkerCondition> conds =  new ArrayList<AbstractTextMarkerCondition>();


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:608:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:609:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2249);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;

            if ( state.backtracking==0 ) {conds.add(c);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:609:35: ( COMMA c= condition )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:609:36: COMMA c= condition
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:612:1: actions returns [List<AbstractTextMarkerAction> actions = new ArrayList<AbstractTextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<AbstractTextMarkerAction> actions() throws RecognitionException {
        List<AbstractTextMarkerAction> actions =  new ArrayList<AbstractTextMarkerAction>();


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:613:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:614:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2298);
            a=action();

            state._fsp--;
            if (state.failed) return actions;

            if ( state.backtracking==0 ) {actions.add(a);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:614:34: ( COMMA a= action )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==COMMA) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:614:35: COMMA a= action
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:618:1: listExpression returns [ListExpression expr = null] : ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression );
    public final ListExpression listExpression() throws RecognitionException {
        ListExpression expr =  null;


        BooleanListExpression bl =null;

        NumberListExpression il =null;

        NumberListExpression dl =null;

        StringListExpression sl =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:619:2: ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:620:2: ( booleanListExpression )=>bl= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2345);
                    bl=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:621:4: ( intListExpression )=>il= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2361);
                    il=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = il;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:622:4: ( doubleListExpression )=>dl= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2377);
                    dl=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = dl;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:623:4: ( stringListExpression )=>sl= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2393);
                    sl=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = sl;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:624:4: ( typeListExpression )=>tl= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:627:1: booleanListExpression returns [BooleanListExpression expr = null] : e= simpleBooleanListExpression ;
    public final BooleanListExpression booleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        BooleanListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:628:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:629:2: e= simpleBooleanListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:632:1: simpleBooleanListExpression returns [BooleanListExpression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier );
    public final BooleanListExpression simpleBooleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        Token var=null;
        BooleanExpression e =null;



        	List<BooleanExpression> list = new ArrayList<BooleanExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:635:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2452); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==FALSE||LA55_0==Identifier||LA55_0==TRUE) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2459);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:53: ( COMMA e= simpleBooleanExpression )*
                            loop54:
                            do {
                                int alt54=2;
                                int LA54_0 = input.LA(1);

                                if ( (LA54_0==COMMA) ) {
                                    alt54=1;
                                }


                                switch (alt54) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:54: COMMA e= simpleBooleanExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:639:2: {...}?var= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:644:1: intListExpression returns [NumberListExpression expr = null] : e= simpleIntListExpression ;
    public final NumberListExpression intListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:645:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:646:2: e= simpleIntListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:649:1: simpleIntListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleIntListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:652:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2540); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==DecimalLiteral||LA58_0==FloatingPointLiteral||LA58_0==Identifier||LA58_0==LPAREN||LA58_0==MINUS) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2547);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:52: ( COMMA e= simpleNumberExpression )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==COMMA) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:653:53: COMMA e= simpleNumberExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:656:2: {...}?var= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:661:1: numberListExpression returns [NumberListExpression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression |e2= intListExpression );
    public final NumberListExpression numberListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e1 =null;

        NumberListExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:662:2: ( (e1= doubleListExpression )=>e1= doubleListExpression |e2= intListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:663:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2616);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:665:2: e2= intListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:668:1: doubleListExpression returns [NumberListExpression expr = null] : e= simpleDoubleListExpression ;
    public final NumberListExpression doubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:669:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:670:2: e= simpleDoubleListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:673:1: simpleDoubleListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleDoubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:676:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2672); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==DecimalLiteral||LA62_0==FloatingPointLiteral||LA62_0==Identifier||LA62_0==LPAREN||LA62_0==MINUS) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2679);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:52: ( COMMA e= simpleNumberExpression )*
                            loop61:
                            do {
                                int alt61=2;
                                int LA61_0 = input.LA(1);

                                if ( (LA61_0==COMMA) ) {
                                    alt61=1;
                                }


                                switch (alt61) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:53: COMMA e= simpleNumberExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:680:2: {...}?var= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:685:1: stringListExpression returns [StringListExpression expr = null] : e= simpleStringListExpression ;
    public final StringListExpression stringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        StringListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:686:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:687:2: e= simpleStringListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:690:1: simpleStringListExpression returns [StringListExpression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier );
    public final StringListExpression simpleStringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        Token var=null;
        StringExpression e =null;



        	List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:693:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression2760); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==Identifier||LA65_0==StringLiteral) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2767);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:52: ( COMMA e= simpleStringExpression )*
                            loop64:
                            do {
                                int alt64=2;
                                int LA64_0 = input.LA(1);

                                if ( (LA64_0==COMMA) ) {
                                    alt64=1;
                                }


                                switch (alt64) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:53: COMMA e= simpleStringExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:697:2: {...}?var= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:702:1: typeListExpression returns [TypeListExpression expr = null] : e= simpleTypeListExpression ;
    public final TypeListExpression typeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        TypeListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:703:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:704:2: e= simpleTypeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:707:1: simpleTypeListExpression returns [TypeListExpression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier );
    public final TypeListExpression simpleTypeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        Token var=null;
        TypeExpression e =null;



        	List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:710:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression2849); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==BasicAnnotationType||LA68_0==Identifier) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2856);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:50: ( COMMA e= simpleTypeExpression )*
                            loop67:
                            do {
                                int alt67=2;
                                int LA67_0 = input.LA(1);

                                if ( (LA67_0==COMMA) ) {
                                    alt67=1;
                                }


                                switch (alt67) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:51: COMMA e= simpleTypeExpression
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:714:2: {...}?var= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:719:1: typeExpression returns [TypeExpression type = null] options {backtrack=true; } : (tf= typeFunction |st= simpleTypeExpression );
    public final TypeExpression typeExpression() throws RecognitionException {
        TypeExpression type =  null;


        TypeExpression tf =null;

        TypeExpression st =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:723:2: (tf= typeFunction |st= simpleTypeExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression2928);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:725:4: st= simpleTypeExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:730:1: typeFunction returns [TypeExpression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final TypeExpression typeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        TypeExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:731:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:732:2: (e= externalTypeFunction )=>e= externalTypeFunction
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:736:1: externalTypeFunction returns [TypeExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TypeExpression externalTypeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:737:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:739:2: id= Identifier LPAREN args= varArgumentList RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:746:1: simpleTypeExpression returns [TypeExpression type = null] : ({...}?var= Identifier |at= annotationType );
    public final TypeExpression simpleTypeExpression() throws RecognitionException {
        TypeExpression type =  null;


        Token var=null;
        Token at =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:747:2: ({...}?var= Identifier |at= annotationType )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:748:2: {...}?var= Identifier
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:751:2: at= annotationType
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:756:1: variable returns [Token var = null] :{...}?v= Identifier ;
    public final Token variable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:757:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:758:2: {...}?v= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:761:1: listVariable returns [Token var = null] :{...}?v= Identifier ;
    public final Token listVariable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:762:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:763:2: {...}?v= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:786:1: quantifierPart returns [RuleElementQuantifier quantifier = null] : ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? );
    public final RuleElementQuantifier quantifierPart() throws RecognitionException {
        RuleElementQuantifier quantifier =  null;


        Token q=null;
        Token comma=null;
        NumberExpression min =null;

        NumberExpression max =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:787:2: ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:788:3: STAR (q= QUESTION )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_quantifierPart3132); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:788:10: (q= QUESTION )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==QUESTION) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:788:10: q= QUESTION
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:791:4: PLUS (q= QUESTION )?
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3149); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:791:11: (q= QUESTION )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==QUESTION) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:791:11: q= QUESTION
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:794:4: QUESTION (q= QUESTION )?
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3165); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:794:15: (q= QUESTION )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==QUESTION) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:794:15: q= QUESTION
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:4: LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3182); if (state.failed) return quantifier;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3188);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:34: (comma= COMMA (max= numberExpression )? )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COMMA) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:35: comma= COMMA (max= numberExpression )?
                            {
                            comma=(Token)match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3195); if (state.failed) return quantifier;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:49: (max= numberExpression )?
                            int alt75=2;
                            int LA75_0 = input.LA(1);

                            if ( (LA75_0==COS||LA75_0==DecimalLiteral||LA75_0==EXP||LA75_0==FloatingPointLiteral||LA75_0==Identifier||(LA75_0 >= LOGN && LA75_0 <= LPAREN)||LA75_0==MINUS||LA75_0==SIN||LA75_0==TAN) ) {
                                alt75=1;
                            }
                            switch (alt75) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:50: max= numberExpression
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

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:86: (q= QUESTION )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==QUESTION) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:797:86: q= QUESTION
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:803:1: condition returns [AbstractTextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) ;
    public final AbstractTextMarkerCondition condition() throws RecognitionException {
        AbstractTextMarkerCondition result =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:804:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:805:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:805:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
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
            case MINUS:
            case NOT:
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
                else if ( (LA79_29==COMMA||LA79_29==RCURLY||LA79_29==RPAREN||LA79_29==THEN) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:806:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3245);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:807:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3254);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:808:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3263);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:809:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3272);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:810:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3281);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:811:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3290);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:812:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3299);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:813:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3308);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:814:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3317);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:815:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3326);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:816:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3335);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:817:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3344);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:818:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3353);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:819:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3362);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:820:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3371);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:821:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3380);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:822:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3389);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:823:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3398);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:824:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3407);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:825:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3416);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:826:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3425);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:827:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3434);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3443);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:829:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3452);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:830:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3462);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:831:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3471);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:832:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3480);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:833:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3489);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:834:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3508);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:835:4: c= variableCondition
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:840:1: variableCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier ;
    public final AbstractTextMarkerCondition variableCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:841:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:843:2: id= Identifier
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:849:1: externalCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerCondition externalCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:850:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:852:2: id= Identifier LPAREN args= varArgumentList RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:858:1: conditionAnd returns [AbstractTextMarkerCondition cond = null] : AND LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionAnd() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:859:5: ( AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:860:5: AND LPAREN conds= conditions RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:864:1: conditionContains returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContains() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        ListExpression list =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression percent =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:868:5: ( CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:869:5: CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3675); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3677); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:869:21: (type= typeExpression |list= listExpression COMMA a= argument )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:869:22: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3684);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:869:46: list= listExpression COMMA a= argument
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


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:870:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:870:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
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

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:870:64: ( COMMA percent= booleanExpression )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==COMMA) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:870:65: COMMA percent= booleanExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:874:1: conditionContextCount returns [AbstractTextMarkerCondition cond = null] : CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContextCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:875:5: ( CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:5: CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount3771); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount3773); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount3779);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==COMMA) ) {
                int LA83_1 = input.LA(2);

                if ( (LA83_1==COS||LA83_1==DecimalLiteral||LA83_1==EXP||LA83_1==FloatingPointLiteral||(LA83_1 >= LOGN && LA83_1 <= LPAREN)||LA83_1==MINUS||LA83_1==SIN||LA83_1==TAN) ) {
                    alt83=1;
                }
                else if ( (LA83_1==Identifier) ) {
                    int LA83_4 = input.LA(3);

                    if ( (LA83_4==COMMA||LA83_4==LPAREN||LA83_4==MINUS||(LA83_4 >= PERCENT && LA83_4 <= PLUS)||(LA83_4 >= SLASH && LA83_4 <= STAR)) ) {
                        alt83=1;
                    }
                }
            }
            switch (alt83) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:876:48: COMMA min= numberExpression COMMA max= numberExpression
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


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:877:5: ( COMMA var= numberVariable )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==COMMA) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:877:6: COMMA var= numberVariable
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:880:1: conditionCount returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final AbstractTextMarkerCondition conditionCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression type =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;

        TypeExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:884:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
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

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==COMMA) ) {
                        int LA85_1 = input.LA(2);

                        if ( (LA85_1==COS||LA85_1==DecimalLiteral||LA85_1==EXP||LA85_1==FloatingPointLiteral||(LA85_1 >= LOGN && LA85_1 <= LPAREN)||LA85_1==MINUS||LA85_1==SIN||LA85_1==TAN) ) {
                            alt85=1;
                        }
                        else if ( (LA85_1==Identifier) ) {
                            int LA85_4 = input.LA(3);

                            if ( (LA85_4==COMMA||LA85_4==LPAREN||LA85_4==MINUS||(LA85_4 >= PERCENT && LA85_4 <= PLUS)||(LA85_4 >= SLASH && LA85_4 <= STAR)) ) {
                                alt85=1;
                            }
                        }
                    }
                    switch (alt85) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:60: COMMA min= numberExpression COMMA max= numberExpression
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:5: ( COMMA var= numberVariable )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==COMMA) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:6: COMMA var= numberVariable
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:889:5: COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount3933); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3935); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount3941);
                    list=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:889:40: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COMMA) ) {
                        int LA87_1 = input.LA(2);

                        if ( (LA87_1==COS||LA87_1==DecimalLiteral||LA87_1==EXP||LA87_1==FloatingPointLiteral||(LA87_1 >= LOGN && LA87_1 <= LPAREN)||LA87_1==MINUS||LA87_1==SIN||LA87_1==TAN) ) {
                            alt87=1;
                        }
                        else if ( (LA87_1==Identifier) ) {
                            int LA87_4 = input.LA(3);

                            if ( (LA87_4==COMMA||LA87_4==LPAREN||LA87_4==MINUS||(LA87_4 >= PERCENT && LA87_4 <= PLUS)||(LA87_4 >= SLASH && LA87_4 <= STAR)) ) {
                                alt87=1;
                            }
                        }
                    }
                    switch (alt87) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:889:41: COMMA min= numberExpression COMMA max= numberExpression
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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:890:5: ( COMMA var= numberVariable )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==COMMA) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:890:6: COMMA var= numberVariable
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:893:1: conditionTotalCount returns [AbstractTextMarkerCondition cond = null] : TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionTotalCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:894:5: ( TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:895:5: TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4014); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4016); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4022);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:895:45: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==COMMA) ) {
                int LA90_1 = input.LA(2);

                if ( (LA90_1==COS||LA90_1==DecimalLiteral||LA90_1==EXP||LA90_1==FloatingPointLiteral||(LA90_1 >= LOGN && LA90_1 <= LPAREN)||LA90_1==MINUS||LA90_1==SIN||LA90_1==TAN) ) {
                    alt90=1;
                }
                else if ( (LA90_1==Identifier) ) {
                    int LA90_4 = input.LA(3);

                    if ( (LA90_4==COMMA||LA90_4==LPAREN||LA90_4==MINUS||(LA90_4 >= PERCENT && LA90_4 <= PLUS)||(LA90_4 >= SLASH && LA90_4 <= STAR)) ) {
                        alt90=1;
                    }
                }
            }
            switch (alt90) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:895:46: COMMA min= numberExpression COMMA max= numberExpression
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


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:896:5: ( COMMA var= numberVariable )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:896:6: COMMA var= numberVariable
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:899:1: conditionCurrentCount returns [AbstractTextMarkerCondition cond = null] : CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionCurrentCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:900:5: ( CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:5: CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4091); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4093); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4099);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==COMMA) ) {
                int LA92_1 = input.LA(2);

                if ( (LA92_1==COS||LA92_1==DecimalLiteral||LA92_1==EXP||LA92_1==FloatingPointLiteral||(LA92_1 >= LOGN && LA92_1 <= LPAREN)||LA92_1==MINUS||LA92_1==SIN||LA92_1==TAN) ) {
                    alt92=1;
                }
                else if ( (LA92_1==Identifier) ) {
                    int LA92_4 = input.LA(3);

                    if ( (LA92_4==COMMA||LA92_4==LPAREN||LA92_4==MINUS||(LA92_4 >= PERCENT && LA92_4 <= PLUS)||(LA92_4 >= SLASH && LA92_4 <= STAR)) ) {
                        alt92=1;
                    }
                }
            }
            switch (alt92) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:48: COMMA min= numberExpression COMMA max= numberExpression
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


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:5: ( COMMA var= numberVariable )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==COMMA) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:902:6: COMMA var= numberVariable
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:905:1: conditionInList returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionInList() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringListExpression list2 =null;

        WordListExpression list1 =null;

        NumberExpression dist =null;

        BooleanExpression rel =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:909:5: ( INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:5: INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            match(input,INLIST,FOLLOW_INLIST_in_conditionInList4179); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4181); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:19: ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:20: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4196);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:83: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4204);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:111: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==COMMA) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:112: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4208); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4214);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:142: ( COMMA rel= booleanExpression )?
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( (LA95_0==COMMA) ) {
                        alt95=1;
                    }
                    switch (alt95) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:143: COMMA rel= booleanExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:914:1: conditionIsInTag returns [AbstractTextMarkerCondition cond = null] : ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final AbstractTextMarkerCondition conditionIsInTag() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression id =null;

        StringExpression id1 =null;

        StringExpression id2 =null;



        List<StringExpression> list1 = new ArrayList<StringExpression>();
        List<StringExpression> list2 = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:919:5: ( ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:920:5: ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4264); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4266); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4272);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:920:42: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==COMMA) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:920:43: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:924:1: conditionLast returns [AbstractTextMarkerCondition cond = null] : LAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionLast() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:925:5: ( LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:926:5: LAST LPAREN type= typeExpression RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:931:1: conditionMofN returns [AbstractTextMarkerCondition cond = null] : MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionMofN() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:932:5: ( MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:933:5: MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:937:1: conditionNear returns [AbstractTextMarkerCondition cond = null] : NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionNear() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression direction =null;

        BooleanExpression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:938:5: ( NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:939:5: NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
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

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:939:97: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==COMMA) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:939:98: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4479); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4485);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:939:134: ( COMMA filtered= booleanExpression )?
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==COMMA) ) {
                        alt98=1;
                    }
                    switch (alt98) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:939:135: COMMA filtered= booleanExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:942:1: conditionNot returns [AbstractTextMarkerCondition cond = null] : ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) ;
    public final AbstractTextMarkerCondition conditionNot() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:943:5: ( ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:6: ( MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:6: ( MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:7: MINUS c= condition
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:31: ( NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:31: ( NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:944:32: NOT LPAREN c= condition RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:947:1: conditionOr returns [AbstractTextMarkerCondition cond = null] : OR LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionOr() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:948:5: ( OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:949:5: OR LPAREN conds= conditions RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:952:1: conditionPartOf returns [AbstractTextMarkerCondition cond = null] : PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:953:5: ( PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:954:5: PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4638); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4640); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:954:19: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:954:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4647);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:954:43: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:957:1: conditionPartOfNeq returns [AbstractTextMarkerCondition cond = null] : PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:958:5: ( PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:959:5: PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4686); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4688); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:959:22: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:959:23: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq4695);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:959:46: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:963:1: conditionPosition returns [AbstractTextMarkerCondition cond = null] : POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionPosition() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:964:5: ( POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:965:5: POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:968:1: conditionRegExp returns [AbstractTextMarkerCondition cond = null] : REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final AbstractTextMarkerCondition conditionRegExp() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression pattern =null;

        BooleanExpression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:969:5: ( REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:5: REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp4786); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp4788); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp4794);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:46: ( COMMA caseSensitive= booleanExpression )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==COMMA) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:47: COMMA caseSensitive= booleanExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:974:1: conditionScore returns [AbstractTextMarkerCondition cond = null] : SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionScore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:5: ( SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:5: SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            match(input,SCORE,FOLLOW_SCORE_in_conditionScore4842); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore4844); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore4850);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:41: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==COMMA) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:976:42: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore4853); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore4859);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:977:5: ( COMMA var= numberVariable )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==COMMA) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:977:6: COMMA var= numberVariable
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:982:1: conditionVote returns [AbstractTextMarkerCondition cond = null] : VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionVote() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:983:5: ( VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:984:5: VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:988:1: conditionIf returns [AbstractTextMarkerCondition cond = null] : IF LPAREN e= booleanExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionIf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:989:5: ( IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:990:5: IF LPAREN e= booleanExpression RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:994:1: conditionFeature returns [AbstractTextMarkerCondition cond = null] : FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerCondition conditionFeature() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression se =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:995:5: ( FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:996:5: FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1000:1: conditionParse returns [AbstractTextMarkerCondition cond = null] : PARSE LPAREN {...}?id= Identifier RPAREN ;
    public final AbstractTextMarkerCondition conditionParse() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:5: ( PARSE LPAREN {...}?id= Identifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1002:5: PARSE LPAREN {...}?id= Identifier RPAREN
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1006:1: conditionIs returns [AbstractTextMarkerCondition cond = null] : IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionIs() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1007:5: ( IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1008:5: IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,IS,FOLLOW_IS_in_conditionIs5109); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5111); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1008:15: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1008:16: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5118);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1008:39: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1012:1: conditionBefore returns [AbstractTextMarkerCondition cond = null] : BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionBefore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1013:5: ( BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1014:5: BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5158); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5160); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1014:19: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1014:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5167);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1014:43: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1018:1: conditionAfter returns [AbstractTextMarkerCondition cond = null] : AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionAfter() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1019:5: ( AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1020:5: AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5207); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5209); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1020:18: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1020:19: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5216);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1020:42: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1024:1: conditionStartsWith returns [AbstractTextMarkerCondition cond = null] : STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionStartsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:5: ( STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1026:5: STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5256); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5258); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1026:23: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1026:24: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5265);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1026:47: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1030:1: conditionEndsWith returns [AbstractTextMarkerCondition cond = null] : ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionEndsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:5: ( ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1032:5: ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5305); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5307); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1032:21: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1032:22: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5314);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1032:45: type2= typeListExpression
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1036:1: conditionSize returns [AbstractTextMarkerCondition cond = null] : SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionSize() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression list =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:5: ( SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:5: SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,SIZE,FOLLOW_SIZE_in_conditionSize5354); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5356); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize5362);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:39: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==COMMA) ) {
                int LA111_1 = input.LA(2);

                if ( (LA111_1==COS||LA111_1==DecimalLiteral||LA111_1==EXP||LA111_1==FloatingPointLiteral||(LA111_1 >= LOGN && LA111_1 <= LPAREN)||LA111_1==MINUS||LA111_1==SIN||LA111_1==TAN) ) {
                    alt111=1;
                }
                else if ( (LA111_1==Identifier) ) {
                    int LA111_4 = input.LA(3);

                    if ( (LA111_4==COMMA||LA111_4==LPAREN||LA111_4==MINUS||(LA111_4 >= PERCENT && LA111_4 <= PLUS)||(LA111_4 >= SLASH && LA111_4 <= STAR)) ) {
                        alt111=1;
                    }
                }
            }
            switch (alt111) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:40: COMMA min= numberExpression COMMA max= numberExpression
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


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:100: ( COMMA var= numberVariable )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==COMMA) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:101: COMMA var= numberVariable
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1042:1: action returns [AbstractTextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) ;
    public final AbstractTextMarkerAction action() throws RecognitionException {
        AbstractTextMarkerAction result =  null;


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1044:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1044:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            int alt113=39;
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
            case CONFIGURE:
                {
                alt113=36;
                }
                break;
            case DYNAMICANCHORING:
                {
                alt113=37;
                }
                break;
            case Identifier:
                {
                int LA113_38 = input.LA(2);

                if ( (LA113_38==LPAREN) && (synpred18_TextMarkerParser())) {
                    alt113=38;
                }
                else if ( (LA113_38==COMMA||LA113_38==RCURLY||LA113_38==RPAREN) ) {
                    alt113=39;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 113, 38, input);

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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1045:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5427);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1046:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5436);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1047:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5445);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1048:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5454);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5463);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1050:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5472);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1051:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5481);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1052:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5490);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1053:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5499);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1054:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5508);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5517);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1056:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5526);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1057:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5535);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1058:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action5544);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1059:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action5553);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1060:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action5562);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1061:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action5571);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action5580);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1063:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action5589);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1064:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action5598);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1065:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action5607);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1066:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action5616);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1067:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action5625);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action5634);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action5643);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1070:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action5652);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1071:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action5661);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1072:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action5670);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1073:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action5679);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action5688);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1075:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action5697);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1076:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action5706);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1077:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action5715);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1078:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action5724);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1079:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action5733);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:4: a= actionConfigure
                    {
                    pushFollow(FOLLOW_actionConfigure_in_action5742);
                    a=actionConfigure();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1081:4: a= actionDynamicAnchoring
                    {
                    pushFollow(FOLLOW_actionDynamicAnchoring_in_action5751);
                    a=actionDynamicAnchoring();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 38 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1082:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action5770);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 39 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1083:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action5779);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1087:1: variableAction returns [AbstractTextMarkerAction action = null] : id= Identifier ;
    public final AbstractTextMarkerAction variableAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1088:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1090:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction5808); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1097:1: externalAction returns [AbstractTextMarkerAction action = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerAction externalAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1098:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1100:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction5836); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction5838); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction5844);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction5846); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1108:1: actionCreate returns [AbstractTextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1113:5: (name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1114:5: name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate5882); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate5884); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate5890);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1115:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==COMMA) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1115:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5897); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1116:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
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
                    else if ( (LA115_0==COS||LA115_0==EXP||LA115_0==LOGN||LA115_0==SIN||LA115_0==TAN) && (synpred19_TextMarkerParser())) {
                        alt115=1;
                    }
                    switch (alt115) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:5: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate5922);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:80: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
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
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:81: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5939); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate5945);
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


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate5952); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1118:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( (LA117_0==Identifier||LA117_0==REMOVESTRING||LA117_0==StringLiteral) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1118:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate5965);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate5967); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate5973);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {map.put(fname,obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1119:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop116:
                            do {
                                int alt116=2;
                                int LA116_0 = input.LA(1);

                                if ( (LA116_0==COMMA) ) {
                                    alt116=1;
                                }


                                switch (alt116) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1119:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5983); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate5989);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate5991); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate5997);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6012); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1125:1: actionMarkTable returns [AbstractTextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1129:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1130:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6053); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6055); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6066);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6068); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6079);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6081); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6091);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1134:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==COMMA) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1134:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6099); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6113);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6115); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6121);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1136:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==COMMA) ) {
                            alt119=1;
                        }


                        switch (alt119) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1136:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6131); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6137);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6139); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6145);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6158); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1141:1: actionGather returns [AbstractTextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1146:5: (name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1147:5: name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6199); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6201); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6207);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==COMMA) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6214); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
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
                    else if ( (LA122_0==COS||LA122_0==EXP||LA122_0==LOGN||LA122_0==SIN||LA122_0==TAN) && (synpred21_TextMarkerParser())) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:6: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6234);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:81: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
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
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:82: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6250); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6256);
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


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6263); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==Identifier||LA126_0==REMOVESTRING||LA126_0==StringLiteral) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6276);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6278); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:44: (obj1= numberExpression |obj2= numberListExpression )
                            int alt123=2;
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
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6285);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6293);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {map.put(fname,obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop125:
                            do {
                                int alt125=2;
                                int LA125_0 = input.LA(1);

                                if ( (LA125_0==COMMA) ) {
                                    alt125=1;
                                }


                                switch (alt125) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6304); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6310);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6312); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:50: (obj1= numberExpression |obj2= numberListExpression )
                            	    int alt124=2;
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
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6319);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1151:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6327);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6343); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1158:1: actionFill returns [AbstractTextMarkerAction action = null] : FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN ;
    public final AbstractTextMarkerAction actionFill() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1162:5: ( FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1163:5: FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN
            {
            match(input,FILL,FOLLOW_FILL_in_actionFill6385); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6387); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill6393);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1163:39: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1163:40: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6396); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill6402);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6404); if (state.failed) return action;

            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1164:5: (obj1= argument )
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:5: obj1= argument
            	    {
            	    pushFollow(FOLLOW_argument_in_actionFill6421);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6438); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1171:1: actionColor returns [AbstractTextMarkerAction action = null] : COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionColor() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression bgcolor =null;

        StringExpression fgcolor =null;

        BooleanExpression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1172:5: ( COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1173:5: COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            match(input,COLOR,FOLLOW_COLOR_in_actionColor6476); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6478); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor6484);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionColor6496); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor6507);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1177:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==COMMA) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1177:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6515); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor6525);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1179:5: ( COMMA selected= booleanExpression )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==COMMA) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1179:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor6533); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor6543);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor6553); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1185:1: actionDel returns [AbstractTextMarkerAction action = null] : DEL ;
    public final AbstractTextMarkerAction actionDel() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1186:5: ( DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1187:5: DEL
            {
            match(input,DEL,FOLLOW_DEL_in_actionDel6587); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1191:1: actionLog returns [AbstractTextMarkerAction action = null] : LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final AbstractTextMarkerAction actionLog() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token log=null;
        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1192:5: ( LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:5: LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            match(input,LOG,FOLLOW_LOG_in_actionLog6629); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog6631); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog6637);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:39: ( COMMA log= LogLevel )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==COMMA) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:40: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog6640); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog6646); if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog6650); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1197:1: actionMark returns [AbstractTextMarkerAction action = null] : MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1201:5: ( MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1202:5: MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARK,FOLLOW_MARK_in_actionMark6689); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark6691); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark6702);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1204:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==COMMA) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1205:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark6718); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark6734);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark6758); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1213:1: actionExpand returns [AbstractTextMarkerAction action = null] : EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionExpand() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1217:5: ( EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1218:5: EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand6802); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand6804); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand6815);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1220:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop133:
            do {
                int alt133=2;
                int LA133_0 = input.LA(1);

                if ( (LA133_0==COMMA) ) {
                    alt133=1;
                }


                switch (alt133) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1221:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand6831); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand6847);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand6871); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1230:1: actionMarkScore returns [AbstractTextMarkerAction action = null] : MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkScore() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1234:5: ( MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1235:5: MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore6916); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore6918); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore6929);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore6931); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore6941);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1238:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==COMMA) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1239:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore6957); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore6973);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore6997); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1247:1: actionMarkOnce returns [AbstractTextMarkerAction action = null] : MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkOnce() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1251:5: ( MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:5: MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7041); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7043); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:21: ( (score= numberExpression )=>score= numberExpression COMMA )?
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
            else if ( (LA135_0==COS||LA135_0==EXP||LA135_0==LOGN||LA135_0==SIN||LA135_0==TAN) && (synpred26_TextMarkerParser())) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:22: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7060);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7062); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7080);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1253:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==COMMA) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1254:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7096); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7112);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7131); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1261:1: actionMarkFast returns [AbstractTextMarkerAction action = null] : MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkFast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        WordListExpression list =null;

        BooleanExpression ignore =null;

        NumberExpression ignoreLength =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1262:5: ( MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1263:5: MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN
            {
            match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7170); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7172); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7178);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7180); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7186);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1263:75: ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==COMMA) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1263:76: COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7189); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7195);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1263:109: ( COMMA ignoreLength= numberExpression )?
                    int alt137=2;
                    int LA137_0 = input.LA(1);

                    if ( (LA137_0==COMMA) ) {
                        alt137=1;
                    }
                    switch (alt137) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1263:110: COMMA ignoreLength= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7198); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7204);
                            ignoreLength=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7210); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1267:1: actionMarkLast returns [AbstractTextMarkerAction action = null] : MARKLAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionMarkLast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1268:5: ( MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1269:5: MARKLAST LPAREN type= typeExpression RPAREN
            {
            match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7244); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7246); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7252);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7254); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1273:1: actionReplace returns [AbstractTextMarkerAction action = null] : REPLACE LPAREN lit= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionReplace() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1274:5: ( REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1275:5: REPLACE LPAREN lit= stringExpression RPAREN
            {
            match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7288); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7290); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace7296);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7298); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1279:1: actionRetainMarkup returns [AbstractTextMarkerAction action = null] : RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:5: ( RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:5: RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7341); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==LPAREN) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7344); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7350);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:64: ( COMMA id= stringExpression )*
                    loop139:
                    do {
                        int alt139=2;
                        int LA139_0 = input.LA(1);

                        if ( (LA139_0==COMMA) ) {
                            alt139=1;
                        }


                        switch (alt139) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7355); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7361);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7367); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1289:1: actionRetainType returns [AbstractTextMarkerAction action = null] : RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1293:5: ( RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:5: RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7413); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==LPAREN) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7416); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7422);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:60: ( COMMA id= typeExpression )*
                    loop141:
                    do {
                        int alt141=2;
                        int LA141_0 = input.LA(1);

                        if ( (LA141_0==COMMA) ) {
                            alt141=1;
                        }


                        switch (alt141) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7427); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7433);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7439); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1298:1: actionFilterMarkup returns [AbstractTextMarkerAction action = null] : FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1302:5: ( FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1303:5: FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup7487); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1303:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==LPAREN) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1303:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup7490); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7496);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1303:64: ( COMMA id= stringExpression )*
                    loop143:
                    do {
                        int alt143=2;
                        int LA143_0 = input.LA(1);

                        if ( (LA143_0==COMMA) ) {
                            alt143=1;
                        }


                        switch (alt143) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1303:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup7501); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7507);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup7513); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1308:1: actionFilterType returns [AbstractTextMarkerAction action = null] : FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1312:5: ( FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1313:5: FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType7559); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1313:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==LPAREN) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1313:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType7562); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType7568);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1313:60: ( COMMA id= typeExpression )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==COMMA) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1313:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType7573); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType7579);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType7585); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1317:1: actionCall returns [AbstractTextMarkerAction action = null] : CALL LPAREN ns= dottedIdentifier RPAREN ;
    public final AbstractTextMarkerAction actionCall() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1318:5: ( CALL LPAREN ns= dottedIdentifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1319:5: CALL LPAREN ns= dottedIdentifier RPAREN
            {
            match(input,CALL,FOLLOW_CALL_in_actionCall7625); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCall7627); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionCall7633);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall7635); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1324:1: actionConfigure returns [AbstractTextMarkerAction action = null] : CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN ;
    public final AbstractTextMarkerAction actionConfigure() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1329:5: ( CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1330:5: CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN
            {
            match(input,CONFIGURE,FOLLOW_CONFIGURE_in_actionConfigure7673); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionConfigure7675); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionConfigure7681);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionConfigure7688); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionConfigure7698);
            fname=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure7700); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionConfigure7706);
            obj1=argument();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(fname,obj1);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1333:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
            loop147:
            do {
                int alt147=2;
                int LA147_0 = input.LA(1);

                if ( (LA147_0==COMMA) ) {
                    alt147=1;
                }


                switch (alt147) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1333:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure7716); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionConfigure7722);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure7724); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionConfigure7730);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(fname,obj1);}

            	    }
            	    break;

            	default :
            	    break loop147;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionConfigure7740); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1339:1: actionExec returns [AbstractTextMarkerAction action = null] : EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionExec() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1340:5: ( EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1341:5: EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN
            {
            match(input,EXEC,FOLLOW_EXEC_in_actionExec7772); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExec7774); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionExec7780);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1341:39: ( COMMA tl= typeListExpression )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==COMMA) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1341:40: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec7783); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec7789);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec7793); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1345:1: actionAssign returns [AbstractTextMarkerAction action = null] : name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1346:5: (name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1347:5: name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign7836); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign7838); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1348:5: ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression )
            int alt149=5;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==Identifier) ) {
                int LA149_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                    alt149=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                    alt149=2;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                    alt149=3;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                    alt149=4;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                    alt149=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return action;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 149, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return action;}
                NoViableAltException nvae =
                    new NoViableAltException("", 149, 0, input);

                throw nvae;

            }
            switch (alt149) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1349:5: {...}?nv= Identifier COMMA e1= typeExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7865); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7867); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionAssign7873);
                    e1=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e1,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1353:5: {...}?nv= Identifier COMMA e2= booleanExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7911); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7913); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionAssign7919);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1357:5: {...}?nv= Identifier COMMA e3= stringExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRING\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7957); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7959); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionAssign7965);
                    e3=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e3,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1361:5: {...}?nv= Identifier COMMA e4= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INT\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8003); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8005); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8011);
                    e4=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e4,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1365:5: {...}?nv= Identifier COMMA e5= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8049); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8051); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8057);
                    e5=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e5,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8076); if (state.failed) return action;

            }

        }

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1371:1: actionSetFeature returns [AbstractTextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerAction actionSetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1372:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1373:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8108); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8110); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8116);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8118); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature8124);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8126); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1378:1: actionGetFeature returns [AbstractTextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final AbstractTextMarkerAction actionGetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        Token v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1379:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1380:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8165); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8167); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8173);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8175); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature8181);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8183); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1385:1: actionDynamicAnchoring returns [AbstractTextMarkerAction action = null] : name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionDynamicAnchoring() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression active =null;

        NumberExpression penalty =null;

        NumberExpression factor =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1386:5: (name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1387:5: name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,DYNAMICANCHORING,FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8219); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDynamicAnchoring8221); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionDynamicAnchoring8227);
            active=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1388:5: ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==COMMA) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1388:6: COMMA penalty= numberExpression ( COMMA factor= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8235); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8241);
                    penalty=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1389:5: ( COMMA factor= numberExpression )?
                    int alt150=2;
                    int LA150_0 = input.LA(1);

                    if ( (LA150_0==COMMA) ) {
                        alt150=1;
                    }
                    switch (alt150) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1389:6: COMMA factor= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8249); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8255);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDynamicAnchoring8272); if (state.failed) return action;

            }

        }

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1395:1: actionUnmark returns [AbstractTextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionUnmark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1396:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1397:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8302); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8304); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark8310);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8312); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1402:1: actionUnmarkAll returns [AbstractTextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionUnmarkAll() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;

        TypeListExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1403:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1404:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8348); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8350); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8356);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1405:5: ( COMMA list= typeListExpression )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==COMMA) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1405:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8364); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8370);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8374); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1409:1: actionTransfer returns [AbstractTextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionTransfer() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1410:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1411:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8409); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8411); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer8417);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8419); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1415:1: actionTrie returns [AbstractTextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1419:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1420:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8459); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8461); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8471);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8473); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie8479);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(key,value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1422:5: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop153:
            do {
                int alt153=2;
                int LA153_0 = input.LA(1);

                if ( (LA153_0==COMMA) ) {
                    int LA153_1 = input.LA(2);

                    if ( (LA153_1==Identifier) ) {
                        int LA153_2 = input.LA(3);

                        if ( (LA153_2==ASSIGN_EQUAL||LA153_2==LPAREN||LA153_2==PLUS) ) {
                            alt153=1;
                        }


                    }
                    else if ( (LA153_1==REMOVESTRING||LA153_1==StringLiteral) ) {
                        alt153=1;
                    }


                }


                switch (alt153) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1422:6: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8487); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8493);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8495); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8501);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {map.put(key,value);}

            	    }
            	    break;

            	default :
            	    break loop153;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8511); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie8517);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8524); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8530);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8537); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8543);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8550); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8556);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8563); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8569);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8576); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8582);
            ignoreChar=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie8588); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1434:1: actionAdd returns [AbstractTextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionAdd() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1438:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1439:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd8633); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd8635); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd8641);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1439:40: ( COMMA a= argument )+
            int cnt154=0;
            loop154:
            do {
                int alt154=2;
                int LA154_0 = input.LA(1);

                if ( (LA154_0==COMMA) ) {
                    alt154=1;
                }


                switch (alt154) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1439:41: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd8644); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd8650);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt154 >= 1 ) break loop154;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(154, input);
                        throw eee;
                }
                cnt154++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd8656); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1443:1: actionRemove returns [AbstractTextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionRemove() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1447:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1448:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove8696); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove8698); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove8704);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1448:43: ( COMMA a= argument )+
            int cnt155=0;
            loop155:
            do {
                int alt155=2;
                int LA155_0 = input.LA(1);

                if ( (LA155_0==COMMA) ) {
                    alt155=1;
                }


                switch (alt155) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1448:44: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove8707); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove8713);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt155 >= 1 ) break loop155;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(155, input);
                        throw eee;
                }
                cnt155++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove8719); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1452:1: actionRemoveDuplicate returns [AbstractTextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1453:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1454:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate8755); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate8757); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate8763);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate8765); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1458:1: actionMerge returns [AbstractTextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final AbstractTextMarkerAction actionMerge() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression join =null;

        Token t =null;

        ListExpression f =null;



        	List<ListExpression> list = new ArrayList<ListExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1462:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1463:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge8810); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge8812); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge8818);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge8820); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge8826);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge8828); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge8834);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1463:113: ( COMMA f= listExpression )+
            int cnt156=0;
            loop156:
            do {
                int alt156=2;
                int LA156_0 = input.LA(1);

                if ( (LA156_0==COMMA) ) {
                    alt156=1;
                }


                switch (alt156) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1463:114: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge8839); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge8845);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(f);}

            	    }
            	    break;

            	default :
            	    if ( cnt156 >= 1 ) break loop156;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(156, input);
                        throw eee;
                }
                cnt156++;
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge8851); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1467:1: actionGet returns [AbstractTextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGet() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        ListExpression f =null;

        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1468:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1469:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet8886); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet8888); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet8894);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet8896); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet8902);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet8904); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet8910);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet8912); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1473:1: actionGetList returns [AbstractTextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGetList() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1474:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1475:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList8947); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList8949); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList8955);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList8957); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList8963);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList8965); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1479:1: actionMatchedText returns [AbstractTextMarkerAction action = null] : MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMatchedText() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token var =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1483:5: ( MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1484:5: MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText9004); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText9006); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText9017);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1486:5: ( COMMA index= numberExpression )*
            loop157:
            do {
                int alt157=2;
                int LA157_0 = input.LA(1);

                if ( (LA157_0==COMMA) ) {
                    alt157=1;
                }


                switch (alt157) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1487:9: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText9033); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText9039);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop157;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText9063); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1495:1: actionClear returns [AbstractTextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionClear() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1496:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1497:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear9103); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear9105); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear9111);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear9113); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1502:1: varArgumentList returns [List args = new ArrayList()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List varArgumentList() throws RecognitionException {
        List args =  new ArrayList();


        TextMarkerExpression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1503:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==LPAREN) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList9140); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList9146);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:41: ( COMMA arg= argument )*
                    loop158:
                    do {
                        int alt158=2;
                        int LA158_0 = input.LA(1);

                        if ( (LA158_0==COMMA) ) {
                            alt158=1;
                        }


                        switch (alt158) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:42: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList9150); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList9156);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;

                    	    if ( state.backtracking==0 ) {args.add(arg);}

                    	    }
                    	    break;

                    	default :
                    	    break loop158;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList9162); if (state.failed) return args;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1507:1: argument returns [TextMarkerExpression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final TextMarkerExpression argument() throws RecognitionException {
        TextMarkerExpression expr =  null;


        StringExpression a4 =null;

        BooleanExpression a2 =null;

        NumberExpression a3 =null;

        TypeExpression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1511:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
            int alt160=4;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
            case StringLiteral:
                {
                alt160=1;
                }
                break;
            case Identifier:
                {
                int LA160_2 = input.LA(2);

                if ( (synpred29_TextMarkerParser()) ) {
                    alt160=1;
                }
                else if ( (synpred30_TextMarkerParser()) ) {
                    alt160=2;
                }
                else if ( (synpred31_TextMarkerParser()) ) {
                    alt160=3;
                }
                else if ( (true) ) {
                    alt160=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 160, 2, input);

                    throw nvae;

                }
                }
                break;
            case FALSE:
            case TRUE:
            case XOR:
                {
                alt160=2;
                }
                break;
            case BasicAnnotationType:
                {
                int LA160_6 = input.LA(2);

                if ( (synpred30_TextMarkerParser()) ) {
                    alt160=2;
                }
                else if ( (true) ) {
                    alt160=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 160, 6, input);

                    throw nvae;

                }
                }
                break;
            case LPAREN:
                {
                int LA160_7 = input.LA(2);

                if ( (synpred30_TextMarkerParser()) ) {
                    alt160=2;
                }
                else if ( (synpred31_TextMarkerParser()) ) {
                    alt160=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 160, 7, input);

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
                alt160=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 160, 0, input);

                throw nvae;

            }

            switch (alt160) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1512:2: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument9196);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1513:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument9207);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1514:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9218);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1515:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9229);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1524:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1525:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1526:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9263); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1527:2: (dot= DOT idn= Identifier )*
            loop161:
            do {
                int alt161=2;
                int LA161_0 = input.LA(1);

                if ( (LA161_0==DOT) ) {
                    alt161=1;
                }


                switch (alt161) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1528:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9276); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9286); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop161;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1533:1: dottedIdentifier2 returns [String idString = \"\"] : id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* ;
    public final String dottedIdentifier2() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1534:2: (id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1535:2: id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29312); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1536:2: (dot= ( DOT | MINUS ) idn= Identifier )*
            loop162:
            do {
                int alt162=2;
                int LA162_0 = input.LA(1);

                if ( (LA162_0==DOT||LA162_0==MINUS) ) {
                    alt162=1;
                }


                switch (alt162) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1537:3: dot= ( DOT | MINUS ) idn= Identifier
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

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29339); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop162;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1543:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1545:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1546:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9371); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:2: (dot= DOT id= Identifier )*
            loop163:
            do {
                int alt163=2;
                int LA163_0 = input.LA(1);

                if ( (LA163_0==DOT) ) {
                    alt163=1;
                }


                switch (alt163) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9384); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9394); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop163;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1558:1: annotationType returns [Token ref = null] : (token= BasicAnnotationType |did= dottedId ) ;
    public final Token annotationType() throws RecognitionException {
        Token ref =  null;


        Token token=null;
        Token did =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1559:2: ( (token= BasicAnnotationType |did= dottedId ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1560:2: (token= BasicAnnotationType |did= dottedId )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1560:2: (token= BasicAnnotationType |did= dottedId )
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==BasicAnnotationType) ) {
                alt164=1;
            }
            else if ( (LA164_0==Identifier) ) {
                alt164=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;

            }
            switch (alt164) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1562:2: token= BasicAnnotationType
                    {
                    token=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType9429); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1564:2: did= dottedId
                    {
                    pushFollow(FOLLOW_dottedId_in_annotationType9441);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1568:1: wordListExpression returns [WordListExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordListExpression wordListExpression() throws RecognitionException {
        WordListExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1569:2: (id= Identifier |path= RessourceLiteral )
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==Identifier) ) {
                alt165=1;
            }
            else if ( (LA165_0==RessourceLiteral) ) {
                alt165=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 165, 0, input);

                throw nvae;

            }
            switch (alt165) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1570:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression9466); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordListExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1573:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression9479); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1578:1: wordTableExpression returns [WordTableExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordTableExpression wordTableExpression() throws RecognitionException {
        WordTableExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1579:2: (id= Identifier |path= RessourceLiteral )
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==Identifier) ) {
                alt166=1;
            }
            else if ( (LA166_0==RessourceLiteral) ) {
                alt166=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 166, 0, input);

                throw nvae;

            }
            switch (alt166) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1580:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression9503); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordTableExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1583:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression9516); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1588:1: numberFunction returns [NumberExpression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final NumberExpression numberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression numExprP =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1589:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==COS||LA167_0==EXP||LA167_0==LOGN||LA167_0==SIN||LA167_0==TAN) ) {
                alt167=1;
            }
            else if ( (LA167_0==Identifier) && (synpred32_TextMarkerParser())) {
                alt167=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 167, 0, input);

                throw nvae;

            }
            switch (alt167) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1590:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1590:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1590:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
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


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction9561);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1593:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction9585);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1598:1: externalNumberFunction returns [NumberExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final NumberExpression externalNumberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1599:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1601:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction9611); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction9613); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction9620);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction9622); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1608:1: numberVariable returns [Token ref = null] : ({...}?token1= Identifier |{...}?token2= Identifier );
    public final Token numberVariable() throws RecognitionException {
        Token ref =  null;


        Token token1=null;
        Token token2=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1609:2: ({...}?token1= Identifier |{...}?token2= Identifier )
            int alt168=2;
            int LA168_0 = input.LA(1);

            if ( (LA168_0==Identifier) ) {
                int LA168_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                    alt168=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                    alt168=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ref;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 168, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 168, 0, input);

                throw nvae;

            }
            switch (alt168) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1610:2: {...}?token1= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INT\")");
                    }

                    token1=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9647); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1611:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLE\")");
                    }

                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9660); if (state.failed) return ref;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1615:1: additiveExpression returns [NumberExpression expr = null] : e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* ;
    public final NumberExpression additiveExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1618:2: (e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1619:2: e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9690);
            e=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {exprs.add(e);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1619:46: (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            loop169:
            do {
                int alt169=2;
                int LA169_0 = input.LA(1);

                if ( (LA169_0==MINUS||LA169_0==PLUS) ) {
                    alt169=1;
                }


                switch (alt169) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1619:48: op= ( PLUS | MINUS ) e= multiplicativeExpression
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

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9712);
            	    e=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) {exprs.add(e);}

            	    }
            	    break;

            	default :
            	    break loop169;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1623:1: multiplicativeExpression returns [NumberExpression expr = null] : (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) ;
    public final NumberExpression multiplicativeExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;

        NumberExpression e1 =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1626:2: ( (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1627:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1627:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            int alt171=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt171=1;
                }
                break;
            case Identifier:
                {
                int LA171_2 = input.LA(2);

                if ( (LA171_2==LPAREN) ) {
                    alt171=2;
                }
                else if ( (LA171_2==EOF||LA171_2==COMMA||LA171_2==EQUAL||(LA171_2 >= GREATER && LA171_2 <= GREATEREQUAL)||(LA171_2 >= LESS && LA171_2 <= LESSEQUAL)||LA171_2==MINUS||LA171_2==NOTEQUAL||(LA171_2 >= PERCENT && LA171_2 <= PLUS)||LA171_2==RBRACK||LA171_2==RPAREN||LA171_2==SEMI||(LA171_2 >= SLASH && LA171_2 <= STAR)) ) {
                    alt171=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 171, 2, input);

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
                alt171=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 171, 0, input);

                throw nvae;

            }

            switch (alt171) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1627:3: e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression9745);
                    e=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1627:45: (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    loop170:
                    do {
                        int alt170=2;
                        int LA170_0 = input.LA(1);

                        if ( (LA170_0==PERCENT||(LA170_0 >= SLASH && LA170_0 <= STAR)) ) {
                            alt170=1;
                        }


                        switch (alt170) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1627:47: op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression
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

                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression9773);
                    	    e=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {exprs.add(e);}

                    	    }
                    	    break;

                    	default :
                    	    break loop170;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1629:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression9791);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1632:1: numberExpression returns [NumberExpression expr = null] : e= additiveExpression ;
    public final NumberExpression numberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1633:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1634:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression9814);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1637:1: numberExpressionInPar returns [NumberExpression expr = null] : LPAREN e= additiveExpression RPAREN ;
    public final NumberExpression numberExpressionInPar() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1638:2: ( LPAREN e= additiveExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1639:2: LPAREN e= additiveExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar9832); if (state.failed) return expr;

            pushFollow(FOLLOW_additiveExpression_in_numberExpressionInPar9839);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar9841); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1642:1: simpleNumberExpression returns [NumberExpression expr = null] : ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar );
    public final NumberExpression simpleNumberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token m=null;
        Token lit=null;
        Token var =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1643:2: ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar )
            int alt175=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case DecimalLiteral:
                    {
                    alt175=1;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt175=2;
                    }
                    break;
                case Identifier:
                    {
                    alt175=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 175, 1, input);

                    throw nvae;

                }

                }
                break;
            case DecimalLiteral:
                {
                alt175=1;
                }
                break;
            case FloatingPointLiteral:
                {
                alt175=2;
                }
                break;
            case Identifier:
                {
                alt175=3;
                }
                break;
            case LPAREN:
                {
                alt175=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 175, 0, input);

                throw nvae;

            }

            switch (alt175) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1644:2: (m= MINUS )? lit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1644:4: (m= MINUS )?
                    int alt172=2;
                    int LA172_0 = input.LA(1);

                    if ( (LA172_0==MINUS) ) {
                        alt172=1;
                    }
                    switch (alt172) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1644:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9864); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression9871); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createIntegerExpression(lit,m);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1645:4: (m= MINUS )? lit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1645:6: (m= MINUS )?
                    int alt173=2;
                    int LA173_0 = input.LA(1);

                    if ( (LA173_0==MINUS) ) {
                        alt173=1;
                    }
                    switch (alt173) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1645:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9883); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression9890); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDoubleExpression(lit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1646:4: (m= MINUS )? var= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1646:6: (m= MINUS )?
                    int alt174=2;
                    int LA174_0 = input.LA(1);

                    if ( (LA174_0==MINUS) ) {
                        alt174=1;
                    }
                    switch (alt174) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1646:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9901); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression9908);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceNumberExpression(var,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1647:4: e= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression9919);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1650:1: stringExpression returns [StringExpression expr = null] options {backtrack=true; } : (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1657:2: (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction )
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==StringLiteral) ) {
                alt178=1;
            }
            else if ( (LA178_0==Identifier) ) {
                int LA178_2 = input.LA(2);

                if ( (LA178_2==LPAREN) && (synpred34_TextMarkerParser())) {
                    alt178=2;
                }
                else if ( (LA178_2==EOF||LA178_2==ASSIGN_EQUAL||LA178_2==COMMA||LA178_2==PLUS||LA178_2==RPAREN||LA178_2==SEMI) ) {
                    alt178=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 178, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA178_0==REMOVESTRING) && (synpred34_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1658:2: e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression9959);
                    e=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1659:2: ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    loop177:
                    do {
                        int alt177=2;
                        int LA177_0 = input.LA(1);

                        if ( (LA177_0==PLUS) ) {
                            alt177=1;
                        }


                        switch (alt177) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1659:3: PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression9966); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1659:8: (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    int alt176=5;
                    	    switch ( input.LA(1) ) {
                    	    case StringLiteral:
                    	        {
                    	        alt176=1;
                    	        }
                    	        break;
                    	    case Identifier:
                    	        {
                    	        int LA176_2 = input.LA(2);

                    	        if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    	            alt176=1;
                    	        }
                    	        else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt176=3;
                    	        }
                    	        else if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))))) ) {
                    	            alt176=4;
                    	        }
                    	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                    	            alt176=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 176, 2, input);

                    	            throw nvae;

                    	        }
                    	        }
                    	        break;
                    	    case LPAREN:
                    	        {
                    	        alt176=2;
                    	        }
                    	        break;
                    	    case FALSE:
                    	    case TRUE:
                    	        {
                    	        alt176=3;
                    	        }
                    	        break;
                    	    case BasicAnnotationType:
                    	        {
                    	        alt176=4;
                    	        }
                    	        break;
                    	    case LCURLY:
                    	        {
                    	        alt176=5;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 176, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt176) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1659:9: e1= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression9973);
                    	            e1=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1660:5: e2= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression9986);
                    	            e2=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1661:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression9998);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1662:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression10010);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(te);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1663:5: le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression10022);
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
                    	    break loop177;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedStringExpression(exprs);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1666:3: (e= stringFunction )=>e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression10050);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1670:1: stringFunction returns [StringExpression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final StringExpression stringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token name=null;
        Token var =null;

        StringExpression t =null;

        StringExpression e =null;


        List<StringExpression> list = new ArrayList<StringExpression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1672:2: (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==REMOVESTRING) ) {
                alt180=1;
            }
            else if ( (LA180_0==Identifier) && (synpred35_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1673:2: name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction10077); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction10079); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction10085);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1673:44: ( COMMA t= stringExpression )+
                    int cnt179=0;
                    loop179:
                    do {
                        int alt179=2;
                        int LA179_0 = input.LA(1);

                        if ( (LA179_0==COMMA) ) {
                            alt179=1;
                        }


                        switch (alt179) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1673:45: COMMA t= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction10088); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction10094);
                    	    t=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {list.add(t);}

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt179 >= 1 ) break loop179;
                    	    if (state.backtracking>0) {state.failed=true; return expr;}
                                EarlyExitException eee =
                                    new EarlyExitException(179, input);
                                throw eee;
                        }
                        cnt179++;
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction10100); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = StringFunctionFactory.createRemoveFunction(var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1676:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction10122);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1680:1: externalStringFunction returns [StringExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final StringExpression externalStringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1681:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1683:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction10147); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction10149); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction10156);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction10158); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1690:1: simpleStringExpression returns [StringExpression expr = null] : (lit= StringLiteral |{...}?id= Identifier );
    public final StringExpression simpleStringExpression() throws RecognitionException {
        StringExpression expr =  null;


        Token lit=null;
        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1691:2: (lit= StringLiteral |{...}?id= Identifier )
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==StringLiteral) ) {
                alt181=1;
            }
            else if ( (LA181_0==Identifier) ) {
                alt181=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 181, 0, input);

                throw nvae;

            }
            switch (alt181) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1692:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression10182); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleStringExpression(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1693:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRING\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression10196); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1697:1: booleanExpression returns [BooleanExpression expr = null] : ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final BooleanExpression booleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e =null;

        BooleanExpression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1698:2: ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression )
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==TRUE) ) {
                int LA182_1 = input.LA(2);

                if ( (LA182_1==EQUAL||LA182_1==NOTEQUAL) && (synpred36_TextMarkerParser())) {
                    alt182=1;
                }
                else if ( (LA182_1==EOF||LA182_1==COMMA||LA182_1==RPAREN||LA182_1==SEMI) ) {
                    alt182=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 182, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA182_0==FALSE) ) {
                int LA182_2 = input.LA(2);

                if ( (LA182_2==EQUAL||LA182_2==NOTEQUAL) && (synpred36_TextMarkerParser())) {
                    alt182=1;
                }
                else if ( (LA182_2==EOF||LA182_2==COMMA||LA182_2==RPAREN||LA182_2==SEMI) ) {
                    alt182=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 182, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA182_0==Identifier) ) {
                int LA182_3 = input.LA(2);

                if ( (LA182_3==LPAREN) && (synpred36_TextMarkerParser())) {
                    alt182=1;
                }
                else if ( (LA182_3==EQUAL||LA182_3==NOTEQUAL) && (synpred36_TextMarkerParser())) {
                    alt182=1;
                }
                else if ( (LA182_3==DOT) && (synpred36_TextMarkerParser())) {
                    alt182=1;
                }
                else if ( (LA182_3==EOF||LA182_3==COMMA||LA182_3==RPAREN||LA182_3==SEMI) ) {
                    alt182=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 182, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA182_0==BasicAnnotationType) && (synpred36_TextMarkerParser())) {
                alt182=1;
            }
            else if ( (LA182_0==LPAREN) && (synpred36_TextMarkerParser())) {
                alt182=1;
            }
            else if ( (LA182_0==XOR) && (synpred36_TextMarkerParser())) {
                alt182=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 182, 0, input);

                throw nvae;

            }
            switch (alt182) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1699:2: (e= composedBooleanExpression )=>e= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10229);
                    e=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1700:3: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10240);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:1: simpleBooleanExpression returns [BooleanExpression expr = null] : (e= literalBooleanExpression |{...}?id= Identifier );
    public final BooleanExpression simpleBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1704:2: (e= literalBooleanExpression |{...}?id= Identifier )
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==FALSE||LA183_0==TRUE) ) {
                alt183=1;
            }
            else if ( (LA183_0==Identifier) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1705:3: e= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10263);
                    e=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1706:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression10278); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1711:1: composedBooleanExpression returns [BooleanExpression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final BooleanExpression composedBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e2 =null;

        BooleanExpression bte =null;

        BooleanExpression bne =null;

        BooleanExpression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
            int alt184=4;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==TRUE) && (synpred37_TextMarkerParser())) {
                alt184=1;
            }
            else if ( (LA184_0==FALSE) && (synpred37_TextMarkerParser())) {
                alt184=1;
            }
            else if ( (LA184_0==Identifier) ) {
                int LA184_3 = input.LA(2);

                if ( (((synpred37_TextMarkerParser()&&synpred37_TextMarkerParser())&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt184=1;
                }
                else if ( (synpred38_TextMarkerParser()) ) {
                    alt184=2;
                }
                else if ( (true) ) {
                    alt184=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 184, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA184_0==BasicAnnotationType) && (synpred38_TextMarkerParser())) {
                alt184=2;
            }
            else if ( (LA184_0==LPAREN) && (synpred39_TextMarkerParser())) {
                alt184=3;
            }
            else if ( (LA184_0==XOR) ) {
                alt184=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 184, 0, input);

                throw nvae;

            }
            switch (alt184) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1714:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression10312);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1715:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression10332);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1716:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression10351);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1717:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression10361);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1721:1: booleanFunction returns [BooleanExpression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final BooleanExpression booleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;

        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1723:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==XOR) ) {
                alt185=1;
            }
            else if ( (LA185_0==Identifier) && (synpred40_TextMarkerParser())) {
                alt185=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 185, 0, input);

                throw nvae;

            }
            switch (alt185) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1724:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1724:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1724:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction10386); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction10388); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10394);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction10396); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10402);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction10404); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1726:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction10426);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1730:1: externalBooleanFunction returns [BooleanExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final BooleanExpression externalBooleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1731:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1733:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction10451); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction10453); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction10460);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction10462); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1741:1: booleanCompare returns [BooleanExpression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final BooleanExpression booleanCompare() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1742:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1743:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1743:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1743:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare10487);
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


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare10505);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1748:1: literalBooleanExpression returns [BooleanExpression expr = null] : (v= TRUE |v= FALSE );
    public final BooleanExpression literalBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1749:2: (v= TRUE |v= FALSE )
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==TRUE) ) {
                alt186=1;
            }
            else if ( (LA186_0==FALSE) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1750:2: v= TRUE
                    {
                    v=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression10531); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleBooleanExpression(v);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1751:4: v= FALSE
                    {
                    v=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression10543); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1756:1: booleanTypeExpression returns [BooleanExpression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final BooleanExpression booleanTypeExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        TypeExpression e1 =null;

        TypeExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1757:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1758:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10569);
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


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10589);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1764:1: booleanNumberExpression returns [BooleanExpression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final BooleanExpression booleanNumberExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        NumberExpression e1 =null;

        NumberExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1765:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1766:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression10611); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10618);
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


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10654);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression10657); if (state.failed) return expr;

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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:497:4: ( ruleElementComposed[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:497:5: ruleElementComposed[null]
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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:498:4: ( ruleElementDisjunctive[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:498:5: ruleElementDisjunctive[null]
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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:620:2: ( booleanListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:620:3: booleanListExpression
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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:621:4: ( intListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:621:5: intListExpression
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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:622:4: ( doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:622:5: doubleListExpression
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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:623:4: ( stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:623:5: stringListExpression
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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:624:4: ( typeListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:624:5: typeListExpression
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:663:2: (e1= doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:663:3: e1= doubleListExpression
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:2: (tf= typeFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:2: tf= typeFunction
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:834:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:834:5: c= externalCondition
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
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

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
        int alt187=2;
        int LA187_0 = input.LA(1);

        if ( (LA187_0==COMMA) ) {
            int LA187_1 = input.LA(2);

            if ( (LA187_1==COS||LA187_1==DecimalLiteral||LA187_1==EXP||LA187_1==FloatingPointLiteral||(LA187_1 >= LOGN && LA187_1 <= LPAREN)||LA187_1==MINUS||LA187_1==SIN||LA187_1==TAN) ) {
                alt187=1;
            }
            else if ( (LA187_1==Identifier) ) {
                int LA187_4 = input.LA(3);

                if ( (LA187_4==COMMA||LA187_4==LPAREN||LA187_4==MINUS||(LA187_4 >= PERCENT && LA187_4 <= PLUS)||(LA187_4 >= SLASH && LA187_4 <= STAR)) ) {
                    alt187=1;
                }
            }
        }
        switch (alt187) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:885:60: COMMA min= numberExpression COMMA max= numberExpression
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:5: ( COMMA var= numberVariable )?
        int alt188=2;
        int LA188_0 = input.LA(1);

        if ( (LA188_0==COMMA) ) {
            alt188=1;
        }
        switch (alt188) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:886:6: COMMA var= numberVariable
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:20: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:910:21: list2= stringListExpression
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1082:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1082:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred18_TextMarkerParser5762);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:5: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:6: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred19_TextMarkerParser5915);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred20_TextMarkerParser
    public final void synpred20_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:81: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1117:82: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred20_TextMarkerParser5928); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred20_TextMarkerParser5934);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_TextMarkerParser

    // $ANTLR start synpred21_TextMarkerParser
    public final void synpred21_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:6: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:7: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred21_TextMarkerParser6227);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:82: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:83: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred22_TextMarkerParser6240); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred22_TextMarkerParser6246);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:22: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:23: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred26_TextMarkerParser7051);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:85: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:86: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred27_TextMarkerParser7071);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1512:2: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1512:2: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred29_TextMarkerParser9196);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1513:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1513:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred30_TextMarkerParser9207);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1514:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1514:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred31_TextMarkerParser9218);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1593:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1593:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred32_TextMarkerParser9577);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1666:3: (e= stringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1666:4: e= stringFunction
        {
        pushFollow(FOLLOW_stringFunction_in_synpred34_TextMarkerParser10042);
        e=stringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred35_TextMarkerParser
    public final void synpred35_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1676:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1676:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred35_TextMarkerParser10114);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred35_TextMarkerParser

    // $ANTLR start synpred36_TextMarkerParser
    public final void synpred36_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1699:2: (e= composedBooleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1699:3: e= composedBooleanExpression
        {
        pushFollow(FOLLOW_composedBooleanExpression_in_synpred36_TextMarkerParser10221);
        e=composedBooleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred36_TextMarkerParser

    // $ANTLR start synpred37_TextMarkerParser
    public final void synpred37_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1714:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1714:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred37_TextMarkerParser10304);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred37_TextMarkerParser

    // $ANTLR start synpred38_TextMarkerParser
    public final void synpred38_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1715:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1715:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred38_TextMarkerParser10324);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred38_TextMarkerParser

    // $ANTLR start synpred39_TextMarkerParser
    public final void synpred39_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1716:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1716:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred39_TextMarkerParser10343);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred39_TextMarkerParser

    // $ANTLR start synpred40_TextMarkerParser
    public final void synpred40_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1726:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1726:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred40_TextMarkerParser10418);
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
        "\1\45\1\22\1\111\2\32\1\111\2\uffff\1\47";
    static final String DFA28_maxS =
        "\1\45\2\111\2\176\1\111\2\uffff\1\111";
    static final String DFA28_acceptS =
        "\6\uffff\1\1\1\2\1\uffff";
    static final String DFA28_specialS =
        "\11\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\1",
            "\1\2\66\uffff\1\3",
            "\1\4",
            "\1\6\14\uffff\1\5\41\uffff\1\4\64\uffff\1\6",
            "\1\6\72\uffff\1\7\50\uffff\1\6",
            "\1\10",
            "",
            "",
            "\1\5\41\uffff\1\4"
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
            return "340:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
        }
    }
 

    public static final BitSet FOLLOW_packageDeclaration_in_file_input76 = new BitSet(new long[]{0x00041120101E8010L,0x0000000000200640L,0x00000000030701E0L});
    public static final BitSet FOLLOW_globalStatements_in_file_input92 = new BitSet(new long[]{0x00001120101E8010L,0x0000000000200640L,0x00000000030301A0L});
    public static final BitSet FOLLOW_statements_in_file_input101 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file_input109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PackageString_in_packageDeclaration124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier_in_packageDeclaration130 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements155 = new BitSet(new long[]{0x00001120101E8012L,0x0000000000200640L,0x00000000030301A0L});
    public static final BitSet FOLLOW_globalStatement_in_globalStatements180 = new BitSet(new long[]{0x0004000000000002L,0x0000000000000000L,0x0000000000040040L});
    public static final BitSet FOLLOW_importStatement_in_globalStatement204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_statement230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStatement_in_statement252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockDeclaration_in_statement263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_automataDeclaration_in_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntString_in_variableDeclaration299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration308 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration323 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration333 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration339 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration365 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration380 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration391 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration397 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration422 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration429 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration437 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration448 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration454 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration479 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration494 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration505 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration511 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration536 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration551 = new BitSet(new long[]{0x0000000004000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration562 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration568 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration597 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration600 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000200L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration606 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration636 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration639 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000200L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration645 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration661 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration673 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration682 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration711 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration714 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration720 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration737 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration749 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration758 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration787 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration790 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration796 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration825 = new BitSet(new long[]{0x0000000000000800L,0x4000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration834 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONDITION_in_conditionDeclaration892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_conditionDeclaration898 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration900 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionDeclaration902 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_conditions_in_conditionDeclaration908 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionDeclaration910 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_conditionDeclaration912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_actionDeclaration948 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_actionDeclaration954 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDeclaration958 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_actions_in_actionDeclaration964 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDeclaration966 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_actionDeclaration968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement999 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement1007 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1013 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement1021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1027 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1054 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotationType_in_declaration1064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1072 = new BitSet(new long[]{0x0000000004000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1093 = new BitSet(new long[]{0x0000000004000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1109 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotationType_in_declaration1115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1127 = new BitSet(new long[]{0x0000100000140000L,0x0000000000000600L,0x0000000000000100L});
    public static final BitSet FOLLOW_annotationType_in_declaration1142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_StringString_in_declaration1155 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_IntString_in_declaration1180 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1208 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1220 = new BitSet(new long[]{0x0000100000140000L,0x0000000000000600L,0x0000000000000100L});
    public static final BitSet FOLLOW_annotationType_in_declaration1235 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_StringString_in_declaration1248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_IntString_in_declaration1273 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1301 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1309 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1381 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1385 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1398 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1409 = new BitSet(new long[]{0x00001120101E8010L,0x0008000000200640L,0x00000000030301A0L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1415 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AutomataBlockString_in_automataDeclaration1469 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_automataDeclaration1473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_automataDeclaration1480 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_automataDeclaration1484 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_automataDeclaration1497 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_automataDeclaration1506 = new BitSet(new long[]{0x00001120101E8010L,0x0008000000200640L,0x00000000030301A0L});
    public static final BitSet FOLLOW_statements_in_automataDeclaration1512 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_automataDeclaration1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1551 = new BitSet(new long[]{0x0000000000000000L,0x000240000000C000L,0x0000000000000008L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1580 = new BitSet(new long[]{0x0040200AC0010240L,0x20189C5E000023B0L,0x0000000000801812L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1586 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1590 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1596 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1641 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1671 = new BitSet(new long[]{0x0000000000040002L,0x0000000000200200L,0x0000000000000080L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1681 = new BitSet(new long[]{0x0000000000040002L,0x0000000000200200L,0x0000000000000080L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_ruleElement1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementDisjunctive1789 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1814 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1820 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1829 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1835 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementDisjunctive1844 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000008L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementDisjunctive1870 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementDisjunctive1883 = new BitSet(new long[]{0x0040200AC0010240L,0x20189C5E000023B0L,0x0000000000801812L});
    public static final BitSet FOLLOW_conditions_in_ruleElementDisjunctive1889 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_ruleElementDisjunctive1893 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_actions_in_ruleElementDisjunctive1899 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementDisjunctive1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed1944 = new BitSet(new long[]{0x0000000000040000L,0x0000000000200200L,0x0000000000000080L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed1972 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed1982 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000008L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed1988 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed1992 = new BitSet(new long[]{0x0040200AC0010240L,0x20189C5E000023B0L,0x0000000000801812L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed1998 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2002 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2008 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2055 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000008L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2074 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2087 = new BitSet(new long[]{0x0040200AC0010240L,0x20189C5E000023B0L,0x0000000000801812L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2093 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2097 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_actions_in_ruleElementType2103 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2154 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000008L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2178 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2191 = new BitSet(new long[]{0x0040200AC0010240L,0x20189C5E000023B0L,0x0000000000801812L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2197 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2201 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2207 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2249 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_conditions2254 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_condition_in_conditions2260 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_action_in_actions2298 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_actions2303 = new BitSet(new long[]{0xF382824422A00420L,0x87600001FE080200L,0x0000000000186000L});
    public static final BitSet FOLLOW_action_in_actions2309 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2452 = new BitSet(new long[]{0x0020000000000000L,0x0008000000000200L,0x0000000000008000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2459 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2464 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000200L,0x0000000000008000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2470 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2540 = new BitSet(new long[]{0x0800040000000000L,0x0008000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2547 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2552 = new BitSet(new long[]{0x0800040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2558 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2672 = new BitSet(new long[]{0x0800040000000000L,0x0008000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2679 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2684 = new BitSet(new long[]{0x0800040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2690 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression2760 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2767 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression2772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2778 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression2787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression2803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression2828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression2849 = new BitSet(new long[]{0x0000000000040000L,0x0008000000000200L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2856 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression2861 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2867 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression2876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression2939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction2998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3000 = new BitSet(new long[]{0x0000000000000000L,0x0800000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3007 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeExpression3034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3132 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3149 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3165 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3182 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3188 = new BitSet(new long[]{0x0000000004000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3195 = new BitSet(new long[]{0x0801040100000000L,0x0004000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3202 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3208 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
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
    public static final BitSet FOLLOW_Identifier_in_externalCondition3574 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3576 = new BitSet(new long[]{0x0000000000000000L,0x0800000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3582 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3613 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3615 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3621 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3677 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3684 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3692 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3694 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_conditionContains3700 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3709 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3715 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3717 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3723 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3726 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3732 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount3771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount3773 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount3779 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3782 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3788 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3790 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3796 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3806 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount3812 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3862 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount3870 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3872 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_conditionCount3878 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3881 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3887 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3889 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3895 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3911 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3935 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount3941 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3944 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3950 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3952 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3958 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3974 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4014 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4016 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4022 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4025 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4031 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4033 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4039 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4054 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4093 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4099 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4102 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4108 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4110 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4116 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4132 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4181 = new BitSet(new long[]{0x0000000000000000L,0x1000000000008200L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4196 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4204 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4208 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4214 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4217 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4223 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4264 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4266 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4272 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4275 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4281 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4283 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4289 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4336 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4342 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4391 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4393 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4399 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4401 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4407 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4409 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4415 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4454 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4460 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4462 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4468 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4470 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4476 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4479 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4485 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4488 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4494 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4536 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_condition_in_conditionNot4542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4549 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4551 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_condition_in_conditionNot4557 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4600 = new BitSet(new long[]{0x0040200AC0010240L,0x20109C5E000023B0L,0x0000000000801012L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4606 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4638 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4640 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4647 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4653 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4688 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq4695 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq4701 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq4704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition4738 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition4740 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition4746 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition4748 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition4754 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition4756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp4786 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp4788 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp4794 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp4797 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp4803 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp4807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore4842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore4844 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore4850 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore4853 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore4859 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore4866 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore4872 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore4879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote4914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote4916 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote4922 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote4924 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote4930 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote4932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf4970 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf4972 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf4978 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf4980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5014 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5016 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5022 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5024 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5030 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5068 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_conditionParse5076 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5111 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5118 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5124 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5158 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5160 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5167 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5173 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5209 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5216 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5222 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5258 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5265 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5271 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5305 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5307 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5314 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5320 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5354 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5362 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5365 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5371 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5373 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5379 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5390 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
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
    public static final BitSet FOLLOW_actionConfigure_in_action5742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDynamicAnchoring_in_action5751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action5770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action5779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction5808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction5836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction5838 = new BitSet(new long[]{0x0000000000000000L,0x0800000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction5844 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction5846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate5882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate5884 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate5890 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5897 = new BitSet(new long[]{0x0801040100000000L,0x0880000200300200L,0x0000000000000481L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate5922 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5939 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate5945 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5952 = new BitSet(new long[]{0x0000000000000000L,0x0880000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate5965 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate5967 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionCreate5973 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5983 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate5989 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate5991 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionCreate5997 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6053 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6055 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6066 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6068 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6079 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6081 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000200L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6091 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6099 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6113 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6115 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6121 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6131 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6137 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6139 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6145 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6199 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6201 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6207 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6214 = new BitSet(new long[]{0x0801040100000000L,0x0880000200300200L,0x0000000000000481L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6234 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6250 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6256 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6263 = new BitSet(new long[]{0x0000000000000000L,0x0880000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6276 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6278 = new BitSet(new long[]{0x0801040100000000L,0x0000000200308200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6285 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6293 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6304 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6310 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6312 = new BitSet(new long[]{0x0801040100000000L,0x0000000200308200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6319 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6327 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6385 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6387 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6393 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6396 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6402 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6404 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionFill6421 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6478 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6484 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6496 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6507 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6515 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6525 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6533 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor6543 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor6553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel6587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog6629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog6631 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog6637 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionLog6640 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog6646 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog6650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark6689 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark6691 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark6702 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMark6718 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark6734 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark6758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand6802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand6804 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand6815 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand6831 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand6847 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand6871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore6916 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore6918 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore6929 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore6931 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore6941 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore6957 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore6973 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore6997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7043 = new BitSet(new long[]{0x0801040100040000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7060 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7062 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7080 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7096 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7112 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7170 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7172 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7178 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7180 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000200L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7186 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7189 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7195 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7198 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7204 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7244 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7246 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7252 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7290 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7296 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7341 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7344 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7350 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7355 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7361 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7413 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7416 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7422 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7427 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7433 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup7487 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup7490 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7496 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup7501 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7507 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup7513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType7559 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType7562 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7568 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType7573 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7579 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType7585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall7625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall7627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionCall7633 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall7635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONFIGURE_in_actionConfigure7673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionConfigure7675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionConfigure7681 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure7688 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure7698 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure7700 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionConfigure7706 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure7716 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure7722 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure7724 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionConfigure7730 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionConfigure7740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec7772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec7774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionExec7780 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExec7783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec7789 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec7793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign7836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign7838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7865 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7867 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionAssign7873 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7911 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7913 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionAssign7919 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7957 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7959 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionAssign7965 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8003 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8005 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8011 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8049 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8051 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8057 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8110 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8116 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8118 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8124 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8167 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8173 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8175 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8181 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8219 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDynamicAnchoring8221 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionDynamicAnchoring8227 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8235 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8241 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8249 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8255 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDynamicAnchoring8272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8304 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8310 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8348 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8350 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8356 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8364 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8370 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8411 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8417 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8461 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8471 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8473 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8479 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8487 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8493 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8495 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8501 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8511 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000200L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8517 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8524 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8530 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8537 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8543 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8550 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8556 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8563 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8569 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8576 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8582 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie8588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd8633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd8635 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd8641 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd8644 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionAdd8650 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd8656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove8696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove8698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove8704 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove8707 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_actionRemove8713 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove8719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate8755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate8757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate8763 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate8765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge8810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge8812 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge8818 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge8826 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge8834 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge8845 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge8851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet8886 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet8888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_actionGet8894 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet8896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_actionGet8902 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet8904 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet8910 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet8912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList8947 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList8949 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList8955 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList8957 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList8963 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList8965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText9004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText9006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText9017 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText9033 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText9039 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText9063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear9103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear9105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionClear9111 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear9113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList9140 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9146 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList9150 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9156 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList9162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument9196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument9207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9263 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9276 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9286 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29312 = new BitSet(new long[]{0x0000008000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_set_in_dottedIdentifier29325 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29339 = new BitSet(new long[]{0x0000008000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9371 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedId9384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9394 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType9429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationType9441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression9466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression9479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression9503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression9516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction9539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction9561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction9585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction9611 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction9613 = new BitSet(new long[]{0x0000000000000000L,0x0800000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction9620 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction9622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9690 = new BitSet(new long[]{0x0000000000000002L,0x0000400200000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression9699 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9712 = new BitSet(new long[]{0x0000000000000002L,0x0000400200000000L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression9745 = new BitSet(new long[]{0x0000000000000002L,0x0000200000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression9754 = new BitSet(new long[]{0x0800040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression9773 = new BitSet(new long[]{0x0000000000000002L,0x0000200000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression9791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression9814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar9832 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpressionInPar9839 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar9841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9864 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression9871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9883 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression9890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression9908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression9919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression9959 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression9966 = new BitSet(new long[]{0x0020000000040000L,0x0000000000208200L,0x0000000000008080L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression9973 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression9986 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression9998 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression10010 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression10022 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression10050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction10077 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction10079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_stringFunction10085 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction10088 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction10094 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction10100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction10122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction10147 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction10149 = new BitSet(new long[]{0x0000000000000000L,0x0800000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction10156 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction10158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression10182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression10196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression10278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression10312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression10332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression10351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression10361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction10386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction10388 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10394 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction10396 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10402 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction10404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction10426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction10451 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction10453 = new BitSet(new long[]{0x0000000000000000L,0x0800000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction10460 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction10462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare10487 = new BitSet(new long[]{0x0000400000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_set_in_booleanCompare10493 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000008008000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare10505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression10531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression10543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10569 = new BitSet(new long[]{0x0000400000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression10576 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression10611 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10618 = new BitSet(new long[]{0x0000400000000000L,0x0000002000030003L});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression10625 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10654 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression10657 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_COUNT_in_synpred16_TextMarkerParser3862 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred16_TextMarkerParser3864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_synpred16_TextMarkerParser3870 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3872 = new BitSet(new long[]{0x0821040100040000L,0x0080000200300200L,0x0000000008008481L});
    public static final BitSet FOLLOW_argument_in_synpred16_TextMarkerParser3878 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3881 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_synpred16_TextMarkerParser3887 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3889 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_synpred16_TextMarkerParser3895 = new BitSet(new long[]{0x0000000004000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser3905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_synpred16_TextMarkerParser3911 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred16_TextMarkerParser3915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred17_TextMarkerParser4189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred18_TextMarkerParser5762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred19_TextMarkerParser5915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred20_TextMarkerParser5928 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_synpred20_TextMarkerParser5934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred21_TextMarkerParser6227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred22_TextMarkerParser6240 = new BitSet(new long[]{0x0801040100000000L,0x0000000200300200L,0x0000000000000401L});
    public static final BitSet FOLLOW_numberExpression_in_synpred22_TextMarkerParser6246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred26_TextMarkerParser7051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred27_TextMarkerParser7071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred29_TextMarkerParser9196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred30_TextMarkerParser9207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred31_TextMarkerParser9218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred32_TextMarkerParser9577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_synpred34_TextMarkerParser10042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred35_TextMarkerParser10114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_synpred36_TextMarkerParser10221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred37_TextMarkerParser10304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred38_TextMarkerParser10324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred39_TextMarkerParser10343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred40_TextMarkerParser10418 = new BitSet(new long[]{0x0000000000000002L});

}