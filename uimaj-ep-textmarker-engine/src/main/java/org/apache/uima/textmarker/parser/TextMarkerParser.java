// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g 2012-01-09 11:03:58

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

                if ( (LA1_0==ACTION||LA1_0==AutomataBlockString||(LA1_0 >= BOOLEANLIST && LA1_0 <= BooleanString)||LA1_0==CONDITION||LA1_0==DECLARE||LA1_0==DOUBLELIST||LA1_0==DoubleString||(LA1_0 >= FLOATLIST && LA1_0 <= FloatString)||LA1_0==INTLIST||(LA1_0 >= Identifier && LA1_0 <= IntString)||LA1_0==LPAREN||LA1_0==STRINGLIST||(LA1_0 >= StringLiteral && LA1_0 <= StringString)||(LA1_0 >= TYPELIST && LA1_0 <= TypeString)||(LA1_0 >= WORDLIST && LA1_0 <= WORDTABLE)) ) {
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:249:1: variableDeclaration returns [TextMarkerStatement stmt = null] : (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= FloatString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= FLOATLIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:250:2: (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI |type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= FloatString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI |type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI |type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI |type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI |type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI |type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI |type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= FLOATLIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI |type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI |stmt1= conditionDeclaration |stmt2= actionDeclaration )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:261:2: type= FloatString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI
                    {
                    type=(Token)match(input,FloatString,FOLLOW_FloatString_in_variableDeclaration413); if (state.failed) return stmt;

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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:264:7: ( ASSIGN_EQUAL value2= numberExpression )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==ASSIGN_EQUAL) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:264:8: ASSIGN_EQUAL value2= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration448); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration454);
                            value2=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value2);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration460); if (state.failed) return stmt;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:266:2: type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration470); if (state.failed) return stmt;

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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:269:7: ( ASSIGN_EQUAL value3= stringExpression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ASSIGN_EQUAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:269:8: ASSIGN_EQUAL value3= stringExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration505); if (state.failed) return stmt;

                            pushFollow(FOLLOW_stringExpression_in_variableDeclaration511);
                            value3=stringExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value3);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration517); if (state.failed) return stmt;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:271:2: type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration527); if (state.failed) return stmt;

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


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:274:7: ( ASSIGN_EQUAL value4= booleanExpression )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==ASSIGN_EQUAL) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:274:8: ASSIGN_EQUAL value4= booleanExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration562); if (state.failed) return stmt;

                            pushFollow(FOLLOW_booleanExpression_in_variableDeclaration568);
                            value4=booleanExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value4);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration574); if (state.failed) return stmt;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:276:2: type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration584); if (state.failed) return stmt;

                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration593); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:278:3: ( COMMA {...}?id= Identifier )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==COMMA) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:278:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration600); if (state.failed) return stmt;

                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration608); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:279:7: ( ASSIGN_EQUAL value5= annotationType )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:279:8: ASSIGN_EQUAL value5= annotationType
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration619); if (state.failed) return stmt;

                            pushFollow(FOLLOW_annotationType_in_variableDeclaration625);
                            value5=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value5);}

                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration631); if (state.failed) return stmt;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:281:2: type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration642); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration654); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:283:20: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:283:21: ASSIGN_EQUAL list= wordListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration657); if (state.failed) return stmt;

                            pushFollow(FOLLOW_wordListExpression_in_variableDeclaration663);
                            list=wordListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration667); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(list != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), list);}}

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:285:2: type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration681); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration693); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:287:20: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:287:21: ASSIGN_EQUAL table= wordTableExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration696); if (state.failed) return stmt;

                            pushFollow(FOLLOW_wordTableExpression_in_variableDeclaration702);
                            table=wordTableExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration706); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(table != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), table);}}

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:289:2: type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration718); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration730); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:291:20: ( ASSIGN_EQUAL bl= booleanListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:291:21: ASSIGN_EQUAL bl= booleanListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration733); if (state.failed) return stmt;

                            pushFollow(FOLLOW_booleanListExpression_in_variableDeclaration739);
                            bl=booleanListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration743); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(bl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), bl);}}

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:293:2: type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration756); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration768); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:295:20: ( ASSIGN_EQUAL sl= stringListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:295:21: ASSIGN_EQUAL sl= stringListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration771); if (state.failed) return stmt;

                            pushFollow(FOLLOW_stringListExpression_in_variableDeclaration777);
                            sl=stringListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration781); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(sl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), sl);}}

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:297:2: type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration794); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration806); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:299:20: ( ASSIGN_EQUAL il= numberListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:299:21: ASSIGN_EQUAL il= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration809); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration815);
                            il=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration819); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(il != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), il);}}

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:301:2: type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration832); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration844); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:303:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==ASSIGN_EQUAL) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:303:21: ASSIGN_EQUAL dl= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration847); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration853);
                            dl=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration857); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(dl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), dl);}}

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:305:2: type= FLOATLIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,FLOATLIST,FOLLOW_FLOATLIST_in_variableDeclaration870); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration882); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:307:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==ASSIGN_EQUAL) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:307:21: ASSIGN_EQUAL dl= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration885); if (state.failed) return stmt;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration891);
                            dl=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration895); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(dl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), dl);}}

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:309:2: type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration908); if (state.failed) return stmt;

                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }

                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration920); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:311:20: ( ASSIGN_EQUAL tl= typeListExpression )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==ASSIGN_EQUAL) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:311:21: ASSIGN_EQUAL tl= typeListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration923); if (state.failed) return stmt;

                            pushFollow(FOLLOW_typeListExpression_in_variableDeclaration929);
                            tl=typeListExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration933); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(tl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), tl);}}

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:313:2: stmt1= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration946);
                    stmt1=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = stmt1;}

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:315:2: stmt2= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration958);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:319:1: conditionDeclaration returns [TextMarkerStatement stmt = null] : type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final TextMarkerStatement conditionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerCondition> cons =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:320:5: (type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:321:5: type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
            {
            type=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_conditionDeclaration987); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionDeclaration993); if (state.failed) return stmt;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration995); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionDeclaration997); if (state.failed) return stmt;

            pushFollow(FOLLOW_conditions_in_conditionDeclaration1003);
            cons=conditions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionDeclaration1005); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_conditionDeclaration1007); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:328:1: actionDeclaration returns [TextMarkerStatement stmt = null] : type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final TextMarkerStatement actionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        Token type=null;
        Token id=null;
        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:329:5: (type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:330:5: type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
            {
            type=(Token)match(input,ACTION,FOLLOW_ACTION_in_actionDeclaration1043); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionDeclaration1049); if (state.failed) return stmt;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1051); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDeclaration1053); if (state.failed) return stmt;

            pushFollow(FOLLOW_actions_in_actionDeclaration1059);
            a=actions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDeclaration1061); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_actionDeclaration1063); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:336:1: importStatement returns [TextMarkerStatement stmt = null] : ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI );
    public final TextMarkerStatement importStatement() throws RecognitionException {
        TextMarkerStatement stmt =  null;


        String ts =null;

        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:337:2: ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:338:2: TypeSystemString ts= dottedIdentifier2 SEMI
                    {
                    match(input,TypeSystemString,FOLLOW_TypeSystemString_in_importStatement1088); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1094);
                    ts=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportTypeSystem(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ts);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1097); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:339:4: ScriptString ns= dottedIdentifier2 SEMI
                    {
                    match(input,ScriptString,FOLLOW_ScriptString_in_importStatement1102); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1108);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportScript(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1111); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:340:4: EngineString ns= dottedIdentifier2 SEMI
                    {
                    match(input,EngineString,FOLLOW_EngineString_in_importStatement1116); if (state.failed) return stmt;

                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1122);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {addImportEngine(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1125); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:343:1: declaration returns [TextMarkerStatement stmt = null] : ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:348:2: ( ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:349:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:349:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:350:2: DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1149); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:352:13: (lazyParent= annotationType )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:352:13: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1159);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1167); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:354:4: ( COMMA id= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:354:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1174); if (state.failed) return stmt;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1188); if (state.failed) return stmt;

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1197); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:359:2: DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1204); if (state.failed) return stmt;

                    pushFollow(FOLLOW_annotationType_in_declaration1210);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmt;

                    newName=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1216); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:360:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1222); if (state.failed) return stmt;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:361:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString )
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:362:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1237);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:363:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1250); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:364:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1263); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:365:6: obj6= FloatString
                            {
                            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1275); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj6.getText());}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:366:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1287); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                            }
                            break;
                        case 6 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:367:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1299); if (state.failed) return stmt;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1315); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:370:4: ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:371:4: COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1327); if (state.failed) return stmt;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:372:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString )
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
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:373:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1342);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1.getText());}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:374:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1355); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2.getText());}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:375:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1368); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3.getText());}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:376:6: obj6= FloatString
                    	            {
                    	            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1380); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj6.getText());}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:377:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1392); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4.getText());}

                    	            }
                    	            break;
                    	        case 6 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:378:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1404); if (state.failed) return stmt;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj5.getText());}

                    	            }
                    	            break;

                    	    }


                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1420); if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {featureNames.add(fname.getText());}

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1428); if (state.failed) return stmt;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1431); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:387:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:404:2: (type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:405:2: type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1489); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1493); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1500); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1504); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createScriptBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);}

            if ( state.backtracking==0 ) {((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	container = new RuleElementIsolator();}

            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1517);
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

            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1528); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_blockDeclaration1534);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1536); if (state.failed) return block;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:426:1: automataDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:445:2: (type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:447:2: type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA[container] LCURLY body= statements RCURLY
            {
            type=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_automataDeclaration1588); if (state.failed) return block;

            match(input,LPAREN,FOLLOW_LPAREN_in_automataDeclaration1592); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_automataDeclaration1599); if (state.failed) return block;

            match(input,RPAREN,FOLLOW_RPAREN_in_automataDeclaration1603); if (state.failed) return block;

            if ( state.backtracking==0 ) {block = factory.createAutomataBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);}

            if ( state.backtracking==0 ) {((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	container = new RuleElementIsolator();}

            pushFollow(FOLLOW_ruleElementWithCA_in_automataDeclaration1616);
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

            match(input,LCURLY,FOLLOW_LCURLY_in_automataDeclaration1625); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_automataDeclaration1631);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            match(input,RCURLY,FOLLOW_RCURLY_in_automataDeclaration1633); if (state.failed) return block;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:469:1: ruleElementWithCA[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression idRef =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:470:5: (idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:472:5: idRef= typeExpression (q= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1670);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(idRef,null,null,null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:474:7: (q= quantifierPart )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LBRACK||LA32_0==PLUS||LA32_0==QUESTION||LA32_0==STAR) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:474:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1687);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1699); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:475:18: (c= conditions )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==AFTER||LA33_0==AND||LA33_0==BEFORE||(LA33_0 >= CONTAINS && LA33_0 <= CONTEXTCOUNT)||LA33_0==COUNT||LA33_0==CURRENTCOUNT||LA33_0==ENDSWITH||LA33_0==FEATURE||(LA33_0 >= IF && LA33_0 <= INLIST)||(LA33_0 >= IS && LA33_0 <= Identifier)||LA33_0==LAST||(LA33_0 >= MINUS && LA33_0 <= NOT)||LA33_0==OR||(LA33_0 >= PARSE && LA33_0 <= PARTOFNEQ)||LA33_0==POSITION||LA33_0==REGEXP||LA33_0==SCORE||LA33_0==SIZE||LA33_0==STARTSWITH||LA33_0==TOTALCOUNT||LA33_0==VOTE) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:475:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1705);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:475:32: ( THEN a= actions )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==THEN) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:475:33: THEN a= actions
                    {
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1709); if (state.failed) return re;

                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1715);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1719); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:491:1: simpleStatement returns [TextMarkerRule stmt = null] :elements= ruleElements[stmt.getRoot()] SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;


        List<RuleElement> elements =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:492:2: (elements= ruleElements[stmt.getRoot()] SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:493:2: elements= ruleElements[stmt.getRoot()] SEMI
            {
            if ( state.backtracking==0 ) {stmt = factory.createRule(elements, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            pushFollow(FOLLOW_ruleElements_in_simpleStatement1760);
            elements=ruleElements(stmt.getRoot());

            state._fsp--;
            if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1763); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:498:1: ruleElements[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()] : re= ruleElement[container] (re= ruleElement[container] )* ;
    public final List<RuleElement> ruleElements(RuleElementContainer container) throws RecognitionException {
        List<RuleElement> elements =  new ArrayList<RuleElement>();


        RuleElement re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:499:2: (re= ruleElement[container] (re= ruleElement[container] )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:500:2: re= ruleElement[container] (re= ruleElement[container] )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1790);
            re=ruleElement(container);

            state._fsp--;
            if (state.failed) return elements;

            if ( state.backtracking==0 ) {elements.add(re);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:500:50: (re= ruleElement[container] )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==BasicAnnotationType||LA35_0==Identifier||LA35_0==LPAREN||LA35_0==StringLiteral) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:500:51: re= ruleElement[container]
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1800);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:504:1: ruleElement[RuleElementContainer container] returns [RuleElement re = null] : (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] );
    public final RuleElement ruleElement(RuleElementContainer container) throws RecognitionException {
        RuleElement re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        ComposedRuleElement re3 =null;

        TextMarkerRuleElement re4 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:505:2: (re1= ruleElementType[container] |re2= ruleElementLiteral[container] | ( ruleElementComposed[null] )=>re3= ruleElementComposed[container] | ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container] )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:506:2: re1= ruleElementType[container]
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1829);
                    re1=ruleElementType(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:507:4: re2= ruleElementLiteral[container]
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1841);
                    re2=ruleElementLiteral(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:508:4: ( ruleElementComposed[null] )=>re3= ruleElementComposed[container]
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1858);
                    re3=ruleElementComposed(container);

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:509:4: ( ruleElementDisjunctive[null] )=>re4= ruleElementDisjunctive[container]
                    {
                    pushFollow(FOLLOW_ruleElementDisjunctive_in_ruleElement1876);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:512:1: ruleElementDisjunctive[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:516:5: ( LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:517:5: LPAREN ( typeExpression VBAR )=>type1= typeExpression VBAR type2= typeExpression ( VBAR type3= typeExpression )? RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementDisjunctive1908); if (state.failed) return re;

            pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1924);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {typeExprs.add(type1);}

            match(input,VBAR,FOLLOW_VBAR_in_ruleElementDisjunctive1933); if (state.failed) return re;

            pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1939);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {typeExprs.add(type2);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:520:5: ( VBAR type3= typeExpression )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==VBAR) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:520:6: VBAR type3= typeExpression
                    {
                    match(input,VBAR,FOLLOW_VBAR_in_ruleElementDisjunctive1948); if (state.failed) return re;

                    pushFollow(FOLLOW_typeExpression_in_ruleElementDisjunctive1954);
                    type3=typeExpression();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {typeExprs.add(type3);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementDisjunctive1963); if (state.failed) return re;

            if ( state.backtracking==0 ) { re = factory.createRuleElement(typeExprs, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:524:8: (q= quantifierPart )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LBRACK||LA38_0==PLUS||LA38_0==QUESTION||LA38_0==STAR) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:524:8: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementDisjunctive1989);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:525:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LCURLY) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:525:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementDisjunctive2002); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:525:19: (c= conditions )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==AFTER||LA39_0==AND||LA39_0==BEFORE||(LA39_0 >= CONTAINS && LA39_0 <= CONTEXTCOUNT)||LA39_0==COUNT||LA39_0==CURRENTCOUNT||LA39_0==ENDSWITH||LA39_0==FEATURE||(LA39_0 >= IF && LA39_0 <= INLIST)||(LA39_0 >= IS && LA39_0 <= Identifier)||LA39_0==LAST||(LA39_0 >= MINUS && LA39_0 <= NOT)||LA39_0==OR||(LA39_0 >= PARSE && LA39_0 <= PARTOFNEQ)||LA39_0==POSITION||LA39_0==REGEXP||LA39_0==SCORE||LA39_0==SIZE||LA39_0==STARTSWITH||LA39_0==TOTALCOUNT||LA39_0==VOTE) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:525:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementDisjunctive2008);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:525:33: ( THEN a= actions )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==THEN) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:525:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementDisjunctive2012); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementDisjunctive2018);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementDisjunctive2022); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:539:1: ruleElementComposed[RuleElementContainer container] returns [ComposedRuleElement re = null] : LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final ComposedRuleElement ruleElementComposed(RuleElementContainer container) throws RecognitionException {
        ruleElementComposed_stack.push(new ruleElementComposed_scope());
        ComposedRuleElement re =  null;


        List<RuleElement> res =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;





        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:546:2: ( LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:547:2: LPAREN ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            if ( state.backtracking==0 ) {re = factory.createComposedRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	// dre = factory.createDisjunctiveRuleElement(container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
            	((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con = re;}

            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed2063); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:552:2: ( ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con] )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:558:2: ( ruleElements[$ruleElementComposed::con] )=>res= ruleElements[$ruleElementComposed::con]
            {
            pushFollow(FOLLOW_ruleElements_in_ruleElementComposed2091);
            res=ruleElements(((ruleElementComposed_scope)ruleElementComposed_stack.peek()).con);

            state._fsp--;
            if (state.failed) return re;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed2101); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:11: (q= quantifierPart )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LBRACK||LA42_0==PLUS||LA42_0==QUESTION||LA42_0==STAR) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:11: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed2107);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:29: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LCURLY) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:30: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed2111); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:39: (c= conditions )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==AFTER||LA43_0==AND||LA43_0==BEFORE||(LA43_0 >= CONTAINS && LA43_0 <= CONTEXTCOUNT)||LA43_0==COUNT||LA43_0==CURRENTCOUNT||LA43_0==ENDSWITH||LA43_0==FEATURE||(LA43_0 >= IF && LA43_0 <= INLIST)||(LA43_0 >= IS && LA43_0 <= Identifier)||LA43_0==LAST||(LA43_0 >= MINUS && LA43_0 <= NOT)||LA43_0==OR||(LA43_0 >= PARSE && LA43_0 <= PARTOFNEQ)||LA43_0==POSITION||LA43_0==REGEXP||LA43_0==SCORE||LA43_0==SIZE||LA43_0==STARTSWITH||LA43_0==TOTALCOUNT||LA43_0==VOTE) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:39: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed2117);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:53: ( THEN a= actions )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==THEN) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:561:54: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementComposed2121); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementComposed2127);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementComposed2131); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:577:1: ruleElementType[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        TypeExpression typeExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:578:5: ( ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:580:5: ( typeExpression )=>typeExpr= typeExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2174);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(typeExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:582:7: (q= quantifierPart )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LBRACK||LA46_0==PLUS||LA46_0==QUESTION||LA46_0==STAR) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:582:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2193);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LCURLY) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2206); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:19: (c= conditions )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==AFTER||LA47_0==AND||LA47_0==BEFORE||(LA47_0 >= CONTAINS && LA47_0 <= CONTEXTCOUNT)||LA47_0==COUNT||LA47_0==CURRENTCOUNT||LA47_0==ENDSWITH||LA47_0==FEATURE||(LA47_0 >= IF && LA47_0 <= INLIST)||(LA47_0 >= IS && LA47_0 <= Identifier)||LA47_0==LAST||(LA47_0 >= MINUS && LA47_0 <= NOT)||LA47_0==OR||(LA47_0 >= PARSE && LA47_0 <= PARTOFNEQ)||LA47_0==POSITION||LA47_0==REGEXP||LA47_0==SCORE||LA47_0==SIZE||LA47_0==STARTSWITH||LA47_0==TOTALCOUNT||LA47_0==VOTE) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2212);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:33: ( THEN a= actions )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==THEN) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:583:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2216); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementType2222);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2226); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:597:1: ruleElementLiteral[RuleElementContainer container] returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral(RuleElementContainer container) throws RecognitionException {
        TextMarkerRuleElement re =  null;


        StringExpression stringExpr =null;

        RuleElementQuantifier q =null;

        List<AbstractTextMarkerCondition> c =null;

        List<AbstractTextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:598:5: ( ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:600:5: ( simpleStringExpression )=>stringExpr= simpleStringExpression (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2273);
            stringExpr=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;

            if ( state.backtracking==0 ) {re = factory.createRuleElement(stringExpr, null, null, null, container, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:603:7: (q= quantifierPart )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LBRACK||LA50_0==PLUS||LA50_0==QUESTION||LA50_0==STAR) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:603:7: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2297);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LCURLY) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2310); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:19: (c= conditions )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==AFTER||LA51_0==AND||LA51_0==BEFORE||(LA51_0 >= CONTAINS && LA51_0 <= CONTEXTCOUNT)||LA51_0==COUNT||LA51_0==CURRENTCOUNT||LA51_0==ENDSWITH||LA51_0==FEATURE||(LA51_0 >= IF && LA51_0 <= INLIST)||(LA51_0 >= IS && LA51_0 <= Identifier)||LA51_0==LAST||(LA51_0 >= MINUS && LA51_0 <= NOT)||LA51_0==OR||(LA51_0 >= PARSE && LA51_0 <= PARTOFNEQ)||LA51_0==POSITION||LA51_0==REGEXP||LA51_0==SCORE||LA51_0==SIZE||LA51_0==STARTSWITH||LA51_0==TOTALCOUNT||LA51_0==VOTE) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2316);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:33: ( THEN a= actions )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==THEN) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:604:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2320); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2326);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2330); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:618:1: conditions returns [List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<AbstractTextMarkerCondition> conditions() throws RecognitionException {
        List<AbstractTextMarkerCondition> conds =  new ArrayList<AbstractTextMarkerCondition>();


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:619:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:620:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2368);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;

            if ( state.backtracking==0 ) {conds.add(c);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:620:35: ( COMMA c= condition )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==COMMA) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:620:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2373); if (state.failed) return conds;

            	    pushFollow(FOLLOW_condition_in_conditions2379);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:623:1: actions returns [List<AbstractTextMarkerAction> actions = new ArrayList<AbstractTextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<AbstractTextMarkerAction> actions() throws RecognitionException {
        List<AbstractTextMarkerAction> actions =  new ArrayList<AbstractTextMarkerAction>();


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:624:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:625:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2417);
            a=action();

            state._fsp--;
            if (state.failed) return actions;

            if ( state.backtracking==0 ) {actions.add(a);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:625:34: ( COMMA a= action )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==COMMA) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:625:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2422); if (state.failed) return actions;

            	    pushFollow(FOLLOW_action_in_actions2428);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:629:1: listExpression returns [ListExpression expr = null] : ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( floatListExpression )=>dl= floatListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression );
    public final ListExpression listExpression() throws RecognitionException {
        ListExpression expr =  null;


        BooleanListExpression bl =null;

        NumberListExpression il =null;

        NumberListExpression dl =null;

        StringListExpression sl =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:630:2: ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( floatListExpression )=>dl= floatListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:2: ( booleanListExpression )=>bl= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2464);
                    bl=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bl;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:632:4: ( intListExpression )=>il= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2480);
                    il=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = il;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:633:4: ( doubleListExpression )=>dl= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2496);
                    dl=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = dl;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:634:4: ( floatListExpression )=>dl= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_listExpression2512);
                    dl=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = dl;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:635:4: ( stringListExpression )=>sl= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2528);
                    sl=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = sl;}

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:4: ( typeListExpression )=>tl= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2544);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:639:1: booleanListExpression returns [BooleanListExpression expr = null] : e= simpleBooleanListExpression ;
    public final BooleanListExpression booleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        BooleanListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:640:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:641:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2566);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:644:1: simpleBooleanListExpression returns [BooleanListExpression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier );
    public final BooleanListExpression simpleBooleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;


        Token var=null;
        BooleanExpression e =null;



        	List<BooleanExpression> list = new ArrayList<BooleanExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:647:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:648:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2587); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:648:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==FALSE||LA58_0==Identifier||LA58_0==TRUE) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:648:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2594);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:648:53: ( COMMA e= simpleBooleanExpression )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==COMMA) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:648:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2599); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2605);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2614); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:651:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEANLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2629); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:656:1: intListExpression returns [NumberListExpression expr = null] : e= simpleIntListExpression ;
    public final NumberListExpression intListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:657:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:658:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2654);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:661:1: simpleIntListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleIntListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:664:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:665:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2675); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:665:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==DecimalLiteral||LA61_0==FloatingPointLiteral||LA61_0==Identifier||LA61_0==LPAREN||LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:665:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2682);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:665:52: ( COMMA e= simpleNumberExpression )*
                            loop60:
                            do {
                                int alt60=2;
                                int LA60_0 = input.LA(1);

                                if ( (LA60_0==COMMA) ) {
                                    alt60=1;
                                }


                                switch (alt60) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:665:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2687); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2693);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2702); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:668:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INTLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2717); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:673:1: numberListExpression returns [NumberListExpression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression | (e1= floatListExpression )=>e1= floatListExpression |e2= intListExpression );
    public final NumberListExpression numberListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e1 =null;

        NumberListExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:674:2: ( (e1= doubleListExpression )=>e1= doubleListExpression | (e1= floatListExpression )=>e1= floatListExpression |e2= intListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:675:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2751);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:2: (e1= floatListExpression )=>e1= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_numberListExpression2772);
                    e1=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:679:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2784);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:682:1: doubleListExpression returns [NumberListExpression expr = null] : e= simpleDoubleListExpression ;
    public final NumberListExpression doubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:683:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:684:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2807);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:687:1: simpleDoubleListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleDoubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:690:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:691:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2828); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:691:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==DecimalLiteral||LA65_0==FloatingPointLiteral||LA65_0==Identifier||LA65_0==LPAREN||LA65_0==MINUS) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:691:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2835);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:691:52: ( COMMA e= simpleNumberExpression )*
                            loop64:
                            do {
                                int alt64=2;
                                int LA64_0 = input.LA(1);

                                if ( (LA64_0==COMMA) ) {
                                    alt64=1;
                                }


                                switch (alt64) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:691:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2840); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2846);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2855); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:694:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2870); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:699:1: floatListExpression returns [NumberListExpression expr = null] : e= simpleFloatListExpression ;
    public final NumberListExpression floatListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        NumberListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:700:2: (e= simpleFloatListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:701:2: e= simpleFloatListExpression
            {
            pushFollow(FOLLOW_simpleFloatListExpression_in_floatListExpression2896);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:704:1: simpleFloatListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final NumberListExpression simpleFloatListExpression() throws RecognitionException {
        NumberListExpression expr =  null;


        Token var=null;
        NumberExpression e =null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:707:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:708:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleFloatListExpression2917); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:708:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==DecimalLiteral||LA68_0==FloatingPointLiteral||LA68_0==Identifier||LA68_0==LPAREN||LA68_0==MINUS) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:708:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2924);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:708:52: ( COMMA e= simpleNumberExpression )*
                            loop67:
                            do {
                                int alt67=2;
                                int LA67_0 = input.LA(1);

                                if ( (LA67_0==COMMA) ) {
                                    alt67=1;
                                }


                                switch (alt67) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:708:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleFloatListExpression2929); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2935);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleFloatListExpression2944); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:711:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOATLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleFloatListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"FLOATLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleFloatListExpression2959); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:715:1: stringListExpression returns [StringListExpression expr = null] : e= simpleStringListExpression ;
    public final StringListExpression stringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        StringListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:716:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:717:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2983);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:720:1: simpleStringListExpression returns [StringListExpression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier );
    public final StringListExpression simpleStringListExpression() throws RecognitionException {
        StringListExpression expr =  null;


        Token var=null;
        StringExpression e =null;



        	List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:723:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression3004); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==Identifier||LA71_0==StringLiteral) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression3011);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:52: ( COMMA e= simpleStringExpression )*
                            loop70:
                            do {
                                int alt70=2;
                                int LA70_0 = input.LA(1);

                                if ( (LA70_0==COMMA) ) {
                                    alt70=1;
                                }


                                switch (alt70) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:724:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression3016); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression3022);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression3031); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:727:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRINGLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression3047); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:732:1: typeListExpression returns [TypeListExpression expr = null] : e= simpleTypeListExpression ;
    public final TypeListExpression typeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        TypeListExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:733:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:734:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression3072);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:737:1: simpleTypeListExpression returns [TypeListExpression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier );
    public final TypeListExpression simpleTypeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;


        Token var=null;
        TypeExpression e =null;



        	List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:740:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression3093); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==BasicAnnotationType||LA74_0==Identifier) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3100);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:50: ( COMMA e= simpleTypeExpression )*
                            loop73:
                            do {
                                int alt73=2;
                                int LA73_0 = input.LA(1);

                                if ( (LA73_0==COMMA) ) {
                                    alt73=1;
                                }


                                switch (alt73) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:741:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression3105); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3111);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression3120); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTypeListExpression(list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:744:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression3135); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:749:1: typeExpression returns [TypeExpression type = null] options {backtrack=true; } : (tf= typeFunction |st= simpleTypeExpression );
    public final TypeExpression typeExpression() throws RecognitionException {
        TypeExpression type =  null;


        TypeExpression tf =null;

        TypeExpression st =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:753:2: (tf= typeFunction |st= simpleTypeExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:754:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression3172);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:755:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression3183);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:760:1: typeFunction returns [TypeExpression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final TypeExpression typeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        TypeExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:761:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:762:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction3217);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:766:1: externalTypeFunction returns [TypeExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TypeExpression externalTypeFunction() throws RecognitionException {
        TypeExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:767:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:769:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction3242); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction3244); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction3251);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction3253); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:776:1: simpleTypeExpression returns [TypeExpression type = null] : ({...}?var= Identifier |at= annotationType );
    public final TypeExpression simpleTypeExpression() throws RecognitionException {
        TypeExpression type =  null;


        Token var=null;
        Token at =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:777:2: ({...}?var= Identifier |at= annotationType )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:778:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "simpleTypeExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPE\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeExpression3278); if (state.failed) return type;

                    if ( state.backtracking==0 ) {type = ExpressionFactory.createReferenceTypeExpression(var);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:781:2: at= annotationType
                    {
                    pushFollow(FOLLOW_annotationType_in_simpleTypeExpression3292);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:786:1: variable returns [Token var = null] :{...}?v= Identifier ;
    public final Token variable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:787:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:788:2: {...}?v= Identifier
            {
            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "isVariable($blockDeclaration::env, input.LT(1).getText())");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3318); if (state.failed) return var;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:791:1: listVariable returns [Token var = null] :{...}?v= Identifier ;
    public final Token listVariable() throws RecognitionException {
        Token var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:792:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:793:2: {...}?v= Identifier
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

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3342); if (state.failed) return var;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:817:1: quantifierPart returns [RuleElementQuantifier quantifier = null] : ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? );
    public final RuleElementQuantifier quantifierPart() throws RecognitionException {
        RuleElementQuantifier quantifier =  null;


        Token q=null;
        Token comma=null;
        NumberExpression min =null;

        NumberExpression max =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:818:2: ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:819:3: STAR (q= QUESTION )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_quantifierPart3376); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:819:10: (q= QUESTION )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==QUESTION) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:819:10: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3382); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createStarReluctantQuantifier();} 
                    	 	else{quantifier = TextMarkerScriptFactory.createStarGreedyQuantifier();}}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:822:4: PLUS (q= QUESTION )?
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3393); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:822:11: (q= QUESTION )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==QUESTION) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:822:11: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3399); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createPlusReluctantQuantifier();}
                    	 else {quantifier = TextMarkerScriptFactory.createPlusGreedyQuantifier();}}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:825:4: QUESTION (q= QUESTION )?
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3409); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:825:15: (q= QUESTION )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==QUESTION) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:825:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3415); if (state.failed) return quantifier;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(q != null) {quantifier = TextMarkerScriptFactory.createQuestionReluctantQuantifier();} 
                    	 else {quantifier = TextMarkerScriptFactory.createQuestionGreedyQuantifier();}}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:4: LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3426); if (state.failed) return quantifier;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3432);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:34: (comma= COMMA (max= numberExpression )? )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==COMMA) ) {
                        alt82=1;
                    }
                    switch (alt82) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:35: comma= COMMA (max= numberExpression )?
                            {
                            comma=(Token)match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3439); if (state.failed) return quantifier;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:49: (max= numberExpression )?
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==COS||LA81_0==DecimalLiteral||LA81_0==EXP||LA81_0==FloatingPointLiteral||LA81_0==Identifier||(LA81_0 >= LOGN && LA81_0 <= LPAREN)||LA81_0==MINUS||LA81_0==SIN||LA81_0==TAN) ) {
                                alt81=1;
                            }
                            switch (alt81) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:50: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3446);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return quantifier;

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3452); if (state.failed) return quantifier;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:86: (q= QUESTION )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==QUESTION) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:828:86: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3458); if (state.failed) return quantifier;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:834:1: condition returns [AbstractTextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) ;
    public final AbstractTextMarkerCondition condition() throws RecognitionException {
        AbstractTextMarkerCondition result =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:835:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:836:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:836:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:837:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3489);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:838:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3498);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:839:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3507);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:840:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3516);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:841:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3525);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:842:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3534);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:843:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3543);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:844:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3552);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:845:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3561);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:846:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3570);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:847:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3579);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:848:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3588);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:849:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3597);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:850:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3606);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:851:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3615);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:852:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3624);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:853:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3633);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:854:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3642);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:855:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3651);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:856:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3660);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:857:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3669);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:858:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3678);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:859:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3687);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:860:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3696);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:861:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3706);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:862:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3715);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:863:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3724);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:864:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3733);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:865:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3752);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:866:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3761);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:871:1: variableCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier ;
    public final AbstractTextMarkerCondition variableCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:872:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:874:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3791); if (state.failed) return condition;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:880:1: externalCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerCondition externalCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:881:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:883:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3818); if (state.failed) return condition;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3820); if (state.failed) return condition;

            pushFollow(FOLLOW_varArgumentList_in_externalCondition3826);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3828); if (state.failed) return condition;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:889:1: conditionAnd returns [AbstractTextMarkerCondition cond = null] : AND LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionAnd() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:890:5: ( AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:891:5: AND LPAREN conds= conditions RPAREN
            {
            match(input,AND,FOLLOW_AND_in_conditionAnd3857); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3859); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionAnd3865);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3867); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:895:1: conditionContains returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContains() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        ListExpression list =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression percent =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:899:5: ( CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:900:5: CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3919); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3921); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:900:21: (type= typeExpression |list= listExpression COMMA a= argument )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:900:22: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3928);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:900:46: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3936);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3938); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionContains3944);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COMMA) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3953); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3959);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3961); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3967);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:64: ( COMMA percent= booleanExpression )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COMMA) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:901:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3970); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3976);
                            percent=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3982); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:905:1: conditionContextCount returns [AbstractTextMarkerCondition cond = null] : CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContextCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:906:5: ( CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:907:5: CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount4015); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount4017); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount4023);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:907:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:907:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4026); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount4032);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4034); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount4040);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:908:5: ( COMMA var= numberVariable )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==COMMA) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:908:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4050); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount4056);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount4060); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:911:1: conditionCount returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final AbstractTextMarkerCondition conditionCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression type =null;

        TextMarkerExpression a =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;

        TypeExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:915:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount4106); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4108); if (state.failed) return cond;

                    pushFollow(FOLLOW_listExpression_in_conditionCount4114);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount4116); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionCount4122);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:60: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4125); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4131);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4133); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4139);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:917:5: ( COMMA var= numberVariable )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);

                    if ( (LA92_0==COMMA) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:917:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4149); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4155);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4159); if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createConditionCount(type, a, min, max, var,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:920:5: COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount4177); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4179); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount4185);
                    list=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:920:40: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:920:41: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4188); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4194);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4196); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4202);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:921:5: ( COMMA var= numberVariable )?
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==COMMA) ) {
                        alt94=1;
                    }
                    switch (alt94) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:921:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4212); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4218);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4222); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:924:1: conditionTotalCount returns [AbstractTextMarkerCondition cond = null] : TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionTotalCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:925:5: ( TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:926:5: TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4258); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4260); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4266);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:926:45: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:926:46: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4269); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4275);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4277); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4283);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:5: ( COMMA var= numberVariable )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:927:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4292); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4298);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4302); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:930:1: conditionCurrentCount returns [AbstractTextMarkerCondition cond = null] : CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionCurrentCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:931:5: ( CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:932:5: CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4335); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4337); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4343);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:932:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:932:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4346); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4352);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4354); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4360);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:933:5: ( COMMA var= numberVariable )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==COMMA) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:933:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4370); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4376);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4380); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:936:1: conditionInList returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionInList() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringListExpression list2 =null;

        WordListExpression list1 =null;

        NumberExpression dist =null;

        BooleanExpression rel =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:940:5: ( INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:5: INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            match(input,INLIST,FOLLOW_INLIST_in_conditionInList4423); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4425); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:19: ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:20: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4440);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:83: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4448);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:111: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==COMMA) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:112: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4452); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4458);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:142: ( COMMA rel= booleanExpression )?
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==COMMA) ) {
                        alt101=1;
                    }
                    switch (alt101) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:143: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4461); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4467);
                            rel=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4473); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:945:1: conditionIsInTag returns [AbstractTextMarkerCondition cond = null] : ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final AbstractTextMarkerCondition conditionIsInTag() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression id =null;

        StringExpression id1 =null;

        StringExpression id2 =null;



        List<StringExpression> list1 = new ArrayList<StringExpression>();
        List<StringExpression> list2 = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:950:5: ( ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:951:5: ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4508); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4510); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4516);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:951:42: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==COMMA) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:951:43: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4519); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4525);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4527); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4533);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4539); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:955:1: conditionLast returns [AbstractTextMarkerCondition cond = null] : LAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionLast() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:956:5: ( LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:957:5: LAST LPAREN type= typeExpression RPAREN
            {
            match(input,LAST,FOLLOW_LAST_in_conditionLast4578); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4580); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionLast4586);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4588); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:962:1: conditionMofN returns [AbstractTextMarkerCondition cond = null] : MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionMofN() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:963:5: ( MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:964:5: MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4635); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4637); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4643);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4645); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4651);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4653); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionMofN4659);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4661); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:968:1: conditionNear returns [AbstractTextMarkerCondition cond = null] : NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionNear() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression min =null;

        NumberExpression max =null;

        BooleanExpression direction =null;

        BooleanExpression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:969:5: ( NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:5: NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            match(input,NEAR,FOLLOW_NEAR_in_conditionNear4696); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4698); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionNear4704);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4706); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4712);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4714); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4720);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:97: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==COMMA) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:98: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4723); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4729);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:134: ( COMMA filtered= booleanExpression )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==COMMA) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:970:135: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4732); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4738);
                            filtered=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4744); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:973:1: conditionNot returns [AbstractTextMarkerCondition cond = null] : ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) ;
    public final AbstractTextMarkerCondition conditionNot() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        AbstractTextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:974:5: ( ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:6: ( MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:6: ( MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:7: MINUS c= condition
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_conditionNot4780); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4786);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:31: ( NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:31: ( NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:975:32: NOT LPAREN c= condition RPAREN
                    {
                    match(input,NOT,FOLLOW_NOT_in_conditionNot4793); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4795); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4801);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4803); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:978:1: conditionOr returns [AbstractTextMarkerCondition cond = null] : OR LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionOr() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        List<AbstractTextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:979:5: ( OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:980:5: OR LPAREN conds= conditions RPAREN
            {
            match(input,OR,FOLLOW_OR_in_conditionOr4842); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4844); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionOr4850);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4852); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:983:1: conditionPartOf returns [AbstractTextMarkerCondition cond = null] : PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:984:5: ( PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:985:5: PARTOF LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4882); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4884); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:985:19: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:985:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4891);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:985:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4897);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4900); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:988:1: conditionPartOfNeq returns [AbstractTextMarkerCondition cond = null] : PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:989:5: ( PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:990:5: PARTOFNEQ LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4930); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4932); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:990:22: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:990:23: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq4939);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:990:46: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq4945);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq4948); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:994:1: conditionPosition returns [AbstractTextMarkerCondition cond = null] : POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionPosition() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type =null;

        NumberExpression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:995:5: ( POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:996:5: POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            match(input,POSITION,FOLLOW_POSITION_in_conditionPosition4982); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition4984); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionPosition4990);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition4992); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionPosition4998);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition5000); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:999:1: conditionRegExp returns [AbstractTextMarkerCondition cond = null] : REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final AbstractTextMarkerCondition conditionRegExp() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression pattern =null;

        BooleanExpression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1000:5: ( REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:5: REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp5030); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp5032); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp5038);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:46: ( COMMA caseSensitive= booleanExpression )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==COMMA) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1001:47: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp5041); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp5047);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp5051); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1005:1: conditionScore returns [AbstractTextMarkerCondition cond = null] : SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionScore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1006:5: ( SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1007:5: SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            match(input,SCORE,FOLLOW_SCORE_in_conditionScore5086); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore5088); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore5094);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1007:41: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==COMMA) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1007:42: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore5097); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore5103);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1008:5: ( COMMA var= numberVariable )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==COMMA) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1008:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore5110); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionScore5116);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore5123); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1013:1: conditionVote returns [AbstractTextMarkerCondition cond = null] : VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionVote() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1014:5: ( VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1015:5: VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            match(input,VOTE,FOLLOW_VOTE_in_conditionVote5158); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote5160); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5166);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionVote5168); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5174);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote5176); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1019:1: conditionIf returns [AbstractTextMarkerCondition cond = null] : IF LPAREN e= booleanExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionIf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1020:5: ( IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1021:5: IF LPAREN e= booleanExpression RPAREN
            {
            match(input,IF,FOLLOW_IF_in_conditionIf5214); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf5216); if (state.failed) return cond;

            pushFollow(FOLLOW_booleanExpression_in_conditionIf5222);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf5224); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1025:1: conditionFeature returns [AbstractTextMarkerCondition cond = null] : FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerCondition conditionFeature() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        StringExpression se =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1026:5: ( FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1027:5: FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5258); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5260); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionFeature5266);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5268); if (state.failed) return cond;

            pushFollow(FOLLOW_argument_in_conditionFeature5274);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5276); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1031:1: conditionParse returns [AbstractTextMarkerCondition cond = null] : PARSE LPAREN {...}?id= Identifier RPAREN ;
    public final AbstractTextMarkerCondition conditionParse() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1032:5: ( PARSE LPAREN {...}?id= Identifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1033:5: PARSE LPAREN {...}?id= Identifier RPAREN
            {
            match(input,PARSE,FOLLOW_PARSE_in_conditionParse5310); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5312); if (state.failed) return cond;

            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return cond;}
                throw new FailedPredicateException(input, "conditionParse", "isVariable($blockDeclaration::env,input.LT(1).getText())");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionParse5320); if (state.failed) return cond;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5322); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1037:1: conditionIs returns [AbstractTextMarkerCondition cond = null] : IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionIs() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1038:5: ( IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1039:5: IS LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,IS,FOLLOW_IS_in_conditionIs5353); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5355); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1039:15: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1039:16: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5362);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1039:39: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5368);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5371); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1043:1: conditionBefore returns [AbstractTextMarkerCondition cond = null] : BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionBefore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1044:5: ( BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1045:5: BEFORE LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5402); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5404); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1045:19: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1045:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5411);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1045:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5417);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5420); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1049:1: conditionAfter returns [AbstractTextMarkerCondition cond = null] : AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionAfter() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1050:5: ( AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1051:5: AFTER LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5451); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5453); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1051:18: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1051:19: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5460);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1051:42: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5466);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5469); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1055:1: conditionStartsWith returns [AbstractTextMarkerCondition cond = null] : STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionStartsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1056:5: ( STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1057:5: STARTSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5500); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5502); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1057:23: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1057:24: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5509);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1057:47: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5515);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5518); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1061:1: conditionEndsWith returns [AbstractTextMarkerCondition cond = null] : ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionEndsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        TypeExpression type1 =null;

        TypeListExpression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1062:5: ( ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1063:5: ENDSWITH LPAREN (type1= typeExpression |type2= typeListExpression ) RPAREN
            {
            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5549); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5551); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1063:21: (type1= typeExpression |type2= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1063:22: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5558);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1063:45: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5564);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5567); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1067:1: conditionSize returns [AbstractTextMarkerCondition cond = null] : SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionSize() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;


        ListExpression list =null;

        NumberExpression min =null;

        NumberExpression max =null;

        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1068:5: ( SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:5: SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,SIZE,FOLLOW_SIZE_in_conditionSize5598); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5600); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize5606);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:39: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:40: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5609); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5615);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5617); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5623);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:100: ( COMMA var= numberVariable )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==COMMA) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1069:101: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5628); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionSize5634);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5638); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1073:1: action returns [AbstractTextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) ;
    public final AbstractTextMarkerAction action() throws RecognitionException {
        AbstractTextMarkerAction result =  null;


        AbstractTextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1074:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1075:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1075:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionFilterMarkup |a= actionFilterType |a= actionRetainMarkup |a= actionRetainType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1076:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5671);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1077:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5680);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1078:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5689);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1079:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5698);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1080:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5707);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1081:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5716);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1082:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5725);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1083:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5734);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1084:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5743);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1085:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5752);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1086:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5761);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1087:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5770);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1088:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5779);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1089:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action5788);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1090:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action5797);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1091:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action5806);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1092:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action5815);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1093:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action5824);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1094:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action5833);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1095:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action5842);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1096:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action5851);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1097:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action5860);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1098:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action5869);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1099:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action5878);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1100:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action5887);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1101:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action5896);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1102:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action5905);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1103:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action5914);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1104:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action5923);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1105:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action5932);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1106:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action5941);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1107:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action5950);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1108:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action5959);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1109:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action5968);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1110:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action5977);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1111:4: a= actionConfigure
                    {
                    pushFollow(FOLLOW_actionConfigure_in_action5986);
                    a=actionConfigure();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1112:4: a= actionDynamicAnchoring
                    {
                    pushFollow(FOLLOW_actionDynamicAnchoring_in_action5995);
                    a=actionDynamicAnchoring();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 38 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1113:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action6014);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 39 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1114:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action6023);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1118:1: variableAction returns [AbstractTextMarkerAction action = null] : id= Identifier ;
    public final AbstractTextMarkerAction variableAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1119:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1121:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction6052); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1128:1: externalAction returns [AbstractTextMarkerAction action = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerAction externalAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1129:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1131:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction6080); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction6082); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction6088);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction6090); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1139:1: actionCreate returns [AbstractTextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1144:5: (name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1145:5: name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate6126); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate6128); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate6134);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1146:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==COMMA) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1146:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6141); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1147:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:5: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate6166);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:80: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
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
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:81: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6183); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate6189);
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


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate6196); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==Identifier||LA123_0==REMOVESTRING||LA123_0==StringLiteral) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1149:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate6209);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6211); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate6217);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {map.put(fname,obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop122:
                            do {
                                int alt122=2;
                                int LA122_0 = input.LA(1);

                                if ( (LA122_0==COMMA) ) {
                                    alt122=1;
                                }


                                switch (alt122) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1150:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6227); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate6233);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6235); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate6241);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6256); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1156:1: actionMarkTable returns [AbstractTextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1160:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1161:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6297); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6299); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6310);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6312); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6323);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6325); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6335);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==COMMA) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1165:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6343); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6357);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6359); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6365);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {map.put(fname,obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1167:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==COMMA) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1167:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6375); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6381);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6383); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6389);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6402); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1172:1: actionGather returns [AbstractTextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1177:5: (name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1178:5: name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6443); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6445); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6451);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1179:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==COMMA) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1179:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6458); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:6: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6478);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:81: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
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
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:82: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6494); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6500);
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


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6507); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1181:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==Identifier||LA132_0==REMOVESTRING||LA132_0==StringLiteral) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1181:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6520);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6522); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1181:44: (obj1= numberExpression |obj2= numberListExpression )
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
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1181:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6529);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1181:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6537);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {map.put(fname,obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1182:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop131:
                            do {
                                int alt131=2;
                                int LA131_0 = input.LA(1);

                                if ( (LA131_0==COMMA) ) {
                                    alt131=1;
                                }


                                switch (alt131) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1182:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6548); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6554);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6556); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1182:50: (obj1= numberExpression |obj2= numberListExpression )
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
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1182:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6563);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1182:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6571);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6587); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1189:1: actionFill returns [AbstractTextMarkerAction action = null] : FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN ;
    public final AbstractTextMarkerAction actionFill() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1193:5: ( FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1194:5: FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN
            {
            match(input,FILL,FOLLOW_FILL_in_actionFill6629); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6631); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill6637);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1194:39: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1194:40: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6640); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill6646);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6648); if (state.failed) return action;

            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1195:5: (obj1= argument )
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1196:5: obj1= argument
            	    {
            	    pushFollow(FOLLOW_argument_in_actionFill6665);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6682); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1202:1: actionColor returns [AbstractTextMarkerAction action = null] : COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionColor() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        StringExpression bgcolor =null;

        StringExpression fgcolor =null;

        BooleanExpression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1203:5: ( COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1204:5: COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            match(input,COLOR,FOLLOW_COLOR_in_actionColor6720); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6722); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor6728);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionColor6740); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor6751);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1208:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==COMMA) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1208:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6759); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor6769);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1210:5: ( COMMA selected= booleanExpression )?
                    int alt135=2;
                    int LA135_0 = input.LA(1);

                    if ( (LA135_0==COMMA) ) {
                        alt135=1;
                    }
                    switch (alt135) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1210:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor6777); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor6787);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor6797); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1216:1: actionDel returns [AbstractTextMarkerAction action = null] : DEL ;
    public final AbstractTextMarkerAction actionDel() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1217:5: ( DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1218:5: DEL
            {
            match(input,DEL,FOLLOW_DEL_in_actionDel6831); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1222:1: actionLog returns [AbstractTextMarkerAction action = null] : LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final AbstractTextMarkerAction actionLog() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token log=null;
        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1223:5: ( LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1224:5: LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            match(input,LOG,FOLLOW_LOG_in_actionLog6873); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog6875); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog6881);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1224:39: ( COMMA log= LogLevel )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==COMMA) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1224:40: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog6884); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog6890); if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog6894); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1228:1: actionMark returns [AbstractTextMarkerAction action = null] : MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1232:5: ( MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1233:5: MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARK,FOLLOW_MARK_in_actionMark6933); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark6935); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark6946);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1235:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop138:
            do {
                int alt138=2;
                int LA138_0 = input.LA(1);

                if ( (LA138_0==COMMA) ) {
                    alt138=1;
                }


                switch (alt138) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1236:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark6962); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark6978);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark7002); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1244:1: actionExpand returns [AbstractTextMarkerAction action = null] : EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionExpand() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1248:5: ( EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1249:5: EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand7046); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand7048); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand7059);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1251:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop139:
            do {
                int alt139=2;
                int LA139_0 = input.LA(1);

                if ( (LA139_0==COMMA) ) {
                    alt139=1;
                }


                switch (alt139) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1252:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand7075); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand7091);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand7115); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1261:1: actionMarkScore returns [AbstractTextMarkerAction action = null] : MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkScore() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1265:5: ( MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1266:5: MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore7160); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore7162); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore7173);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7175); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore7185);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1269:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0==COMMA) ) {
                    alt140=1;
                }


                switch (alt140) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1270:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7201); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore7217);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore7241); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1278:1: actionMarkOnce returns [AbstractTextMarkerAction action = null] : MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkOnce() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        NumberExpression score =null;

        TypeExpression type =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1282:5: ( MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:5: MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7285); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7287); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:21: ( (score= numberExpression )=>score= numberExpression COMMA )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:22: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7304);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7306); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7324);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1284:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0==COMMA) ) {
                    alt142=1;
                }


                switch (alt142) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1285:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7340); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7356);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7375); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1292:1: actionMarkFast returns [AbstractTextMarkerAction action = null] : MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkFast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;

        WordListExpression list =null;

        BooleanExpression ignore =null;

        NumberExpression ignoreLength =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1293:5: ( MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:5: MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN
            {
            match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7414); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7416); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7422);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7424); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7430);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:75: ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==COMMA) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:76: COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7433); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7439);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:109: ( COMMA ignoreLength= numberExpression )?
                    int alt143=2;
                    int LA143_0 = input.LA(1);

                    if ( (LA143_0==COMMA) ) {
                        alt143=1;
                    }
                    switch (alt143) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1294:110: COMMA ignoreLength= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7442); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7448);
                            ignoreLength=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7454); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1298:1: actionMarkLast returns [AbstractTextMarkerAction action = null] : MARKLAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionMarkLast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1299:5: ( MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1300:5: MARKLAST LPAREN type= typeExpression RPAREN
            {
            match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7488); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7490); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7496);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7498); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1304:1: actionReplace returns [AbstractTextMarkerAction action = null] : REPLACE LPAREN lit= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionReplace() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1305:5: ( REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1306:5: REPLACE LPAREN lit= stringExpression RPAREN
            {
            match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7532); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7534); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace7540);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7542); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1310:1: actionRetainMarkup returns [AbstractTextMarkerAction action = null] : RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1314:5: ( RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1315:5: RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7585); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1315:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==LPAREN) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1315:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7588); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7594);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1315:64: ( COMMA id= stringExpression )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==COMMA) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1315:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7599); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7605);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7611); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1320:1: actionRetainType returns [AbstractTextMarkerAction action = null] : RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1324:5: ( RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1325:5: RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7657); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1325:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==LPAREN) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1325:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7660); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7666);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1325:60: ( COMMA id= typeExpression )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==COMMA) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1325:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7671); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7677);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7683); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1329:1: actionFilterMarkup returns [AbstractTextMarkerAction action = null] : FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        StringExpression id =null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1333:5: ( FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1334:5: FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup7731); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1334:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==LPAREN) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1334:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup7734); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7740);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1334:64: ( COMMA id= stringExpression )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==COMMA) ) {
                            alt149=1;
                        }


                        switch (alt149) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1334:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup7745); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7751);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup7757); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1339:1: actionFilterType returns [AbstractTextMarkerAction action = null] : FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        TypeExpression id =null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1343:5: ( FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:5: FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType7803); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==LPAREN) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType7806); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType7812);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:60: ( COMMA id= typeExpression )*
                    loop151:
                    do {
                        int alt151=2;
                        int LA151_0 = input.LA(1);

                        if ( (LA151_0==COMMA) ) {
                            alt151=1;
                        }


                        switch (alt151) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1344:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType7817); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType7823);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType7829); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1348:1: actionCall returns [AbstractTextMarkerAction action = null] : CALL LPAREN ns= dottedIdentifier RPAREN ;
    public final AbstractTextMarkerAction actionCall() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1349:5: ( CALL LPAREN ns= dottedIdentifier RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1350:5: CALL LPAREN ns= dottedIdentifier RPAREN
            {
            match(input,CALL,FOLLOW_CALL_in_actionCall7869); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCall7871); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionCall7877);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall7879); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1355:1: actionConfigure returns [AbstractTextMarkerAction action = null] : CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN ;
    public final AbstractTextMarkerAction actionConfigure() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        StringExpression fname =null;

        TextMarkerExpression obj1 =null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1360:5: ( CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1361:5: CONFIGURE LPAREN ns= dottedIdentifier COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* RPAREN
            {
            match(input,CONFIGURE,FOLLOW_CONFIGURE_in_actionConfigure7917); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionConfigure7919); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionConfigure7925);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionConfigure7932); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionConfigure7942);
            fname=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure7944); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionConfigure7950);
            obj1=argument();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(fname,obj1);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1364:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
            loop153:
            do {
                int alt153=2;
                int LA153_0 = input.LA(1);

                if ( (LA153_0==COMMA) ) {
                    alt153=1;
                }


                switch (alt153) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1364:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure7960); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionConfigure7966);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure7968); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionConfigure7974);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionConfigure7984); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1370:1: actionExec returns [AbstractTextMarkerAction action = null] : EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionExec() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        String ns =null;

        TypeListExpression tl =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1371:5: ( EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1372:5: EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN
            {
            match(input,EXEC,FOLLOW_EXEC_in_actionExec8016); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExec8018); if (state.failed) return action;

            pushFollow(FOLLOW_dottedIdentifier_in_actionExec8024);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1372:39: ( COMMA tl= typeListExpression )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==COMMA) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1372:40: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec8027); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec8033);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec8037); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1376:1: actionAssign returns [AbstractTextMarkerAction action = null] : name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1377:5: (name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1378:5: name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign8080); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign8082); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1379:5: ({...}?nv= Identifier COMMA e1= typeExpression |{...}?nv= Identifier COMMA e2= booleanExpression |{...}?nv= Identifier COMMA e3= stringExpression |{...}?nv= Identifier COMMA e4= numberExpression |{...}?nv= Identifier COMMA e6= numberExpression |{...}?nv= Identifier COMMA e5= numberExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1380:5: {...}?nv= Identifier COMMA e1= typeExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8109); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8111); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionAssign8117);
                    e1=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e1,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1384:5: {...}?nv= Identifier COMMA e2= booleanExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8155); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8157); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionAssign8163);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e2,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1388:5: {...}?nv= Identifier COMMA e3= stringExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRING\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8201); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8203); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionAssign8209);
                    e3=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e3,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1392:5: {...}?nv= Identifier COMMA e4= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INT\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8247); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8249); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8255);
                    e4=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e4,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1396:6: {...}?nv= Identifier COMMA e6= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "FLOAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"FLOAT\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8294); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8296); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8302);
                    e6=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e6,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1400:5: {...}?nv= Identifier COMMA e5= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLE\")");
                    }

                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8340); if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign8342); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionAssign8348);
                    e5=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAssignAction(nv, e5,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8367); if (state.failed) return action;

            }

        }

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1406:1: actionSetFeature returns [AbstractTextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerAction actionSetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        TextMarkerExpression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1407:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1408:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8399); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8401); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8407);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8409); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature8415);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8417); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1413:1: actionGetFeature returns [AbstractTextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final AbstractTextMarkerAction actionGetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        StringExpression f =null;

        Token v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1414:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1415:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8456); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8458); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8464);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8466); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature8472);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8474); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1420:1: actionDynamicAnchoring returns [AbstractTextMarkerAction action = null] : name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionDynamicAnchoring() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression active =null;

        NumberExpression penalty =null;

        NumberExpression factor =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1421:5: (name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1422:5: name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,DYNAMICANCHORING,FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8510); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDynamicAnchoring8512); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionDynamicAnchoring8518);
            active=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1423:5: ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==COMMA) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1423:6: COMMA penalty= numberExpression ( COMMA factor= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8526); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8532);
                    penalty=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1424:5: ( COMMA factor= numberExpression )?
                    int alt156=2;
                    int LA156_0 = input.LA(1);

                    if ( (LA156_0==COMMA) ) {
                        alt156=1;
                    }
                    switch (alt156) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1424:6: COMMA factor= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8540); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8546);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDynamicAnchoring8563); if (state.failed) return action;

            }

        }

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1430:1: actionUnmark returns [AbstractTextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionUnmark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1431:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1432:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8593); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8595); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark8601);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8603); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1437:1: actionUnmarkAll returns [AbstractTextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionUnmarkAll() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;

        TypeListExpression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1438:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1439:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8639); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8641); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8647);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1440:5: ( COMMA list= typeListExpression )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==COMMA) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1440:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8655); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8661);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8665); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1444:1: actionTransfer returns [AbstractTextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionTransfer() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        TypeExpression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1445:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1446:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8700); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8702); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer8708);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8710); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1450:1: actionTrie returns [AbstractTextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1454:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1455:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8750); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8752); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8762);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8764); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie8770);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {map.put(key,value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1457:5: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1457:6: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8778); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8784);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8786); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8792);
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


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8802); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie8808);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8815); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8821);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8828); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8834);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8841); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8847);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8854); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8860);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8867); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8873);
            ignoreChar=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie8879); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1469:1: actionAdd returns [AbstractTextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionAdd() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1473:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1474:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd8924); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd8926); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd8932);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1474:40: ( COMMA a= argument )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1474:41: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd8935); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd8941);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd8947); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1478:1: actionRemove returns [AbstractTextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionRemove() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;

        TextMarkerExpression a =null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1482:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1483:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove8987); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove8989); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove8995);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1483:43: ( COMMA a= argument )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1483:44: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove8998); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove9004);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove9010); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1487:1: actionRemoveDuplicate returns [AbstractTextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1488:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1489:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9046); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate9048); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate9054);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate9056); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1493:1: actionMerge returns [AbstractTextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final AbstractTextMarkerAction actionMerge() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        BooleanExpression join =null;

        Token t =null;

        ListExpression f =null;



        	List<ListExpression> list = new ArrayList<ListExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1497:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1498:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge9101); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge9103); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge9109);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9111); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge9117);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9119); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge9125);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1498:113: ( COMMA f= listExpression )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1498:114: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge9130); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge9136);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge9142); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1502:1: actionGet returns [AbstractTextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGet() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        ListExpression f =null;

        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1503:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1504:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet9177); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet9179); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet9185);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9187); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet9193);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9195); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet9201);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet9203); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1508:1: actionGetList returns [AbstractTextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGetList() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;

        StringExpression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1509:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1510:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList9238); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList9240); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList9246);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList9248); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList9254);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList9256); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1514:1: actionMatchedText returns [AbstractTextMarkerAction action = null] : MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMatchedText() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token var =null;

        NumberExpression index =null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1518:5: ( MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1519:5: MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText9295); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText9297); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText9308);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1521:5: ( COMMA index= numberExpression )*
            loop163:
            do {
                int alt163=2;
                int LA163_0 = input.LA(1);

                if ( (LA163_0==COMMA) ) {
                    alt163=1;
                }


                switch (alt163) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1522:9: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText9324); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText9330);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText9354); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1530:1: actionClear returns [AbstractTextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionClear() throws RecognitionException {
        AbstractTextMarkerAction action =  null;


        Token name=null;
        Token var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1531:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1532:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear9394); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear9396); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear9402);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear9404); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1537:1: varArgumentList returns [List args = new ArrayList()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List varArgumentList() throws RecognitionException {
        List args =  new ArrayList();


        TextMarkerExpression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1538:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1539:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1539:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==LPAREN) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1539:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList9431); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList9437);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1539:41: ( COMMA arg= argument )*
                    loop164:
                    do {
                        int alt164=2;
                        int LA164_0 = input.LA(1);

                        if ( (LA164_0==COMMA) ) {
                            alt164=1;
                        }


                        switch (alt164) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1539:42: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList9441); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList9447);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList9453); if (state.failed) return args;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1542:1: argument returns [TextMarkerExpression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final TextMarkerExpression argument() throws RecognitionException {
        TextMarkerExpression expr =  null;


        StringExpression a4 =null;

        BooleanExpression a2 =null;

        NumberExpression a3 =null;

        TypeExpression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1546:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1547:2: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument9487);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1548:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument9498);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9509);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1550:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9520);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1559:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1560:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1561:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9554); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1562:2: (dot= DOT idn= Identifier )*
            loop167:
            do {
                int alt167=2;
                int LA167_0 = input.LA(1);

                if ( (LA167_0==DOT) ) {
                    alt167=1;
                }


                switch (alt167) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1563:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9567); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9577); if (state.failed) return idString;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1568:1: dottedIdentifier2 returns [String idString = \"\"] : id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* ;
    public final String dottedIdentifier2() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1569:2: (id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1570:2: id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29603); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1571:2: (dot= ( DOT | MINUS ) idn= Identifier )*
            loop168:
            do {
                int alt168=2;
                int LA168_0 = input.LA(1);

                if ( (LA168_0==DOT||LA168_0==MINUS) ) {
                    alt168=1;
                }


                switch (alt168) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1572:3: dot= ( DOT | MINUS ) idn= Identifier
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

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier29630); if (state.failed) return idString;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1578:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1580:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1581:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9662); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1584:2: (dot= DOT id= Identifier )*
            loop169:
            do {
                int alt169=2;
                int LA169_0 = input.LA(1);

                if ( (LA169_0==DOT) ) {
                    alt169=1;
                }


                switch (alt169) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1585:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9675); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9685); if (state.failed) return token;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1593:1: annotationType returns [Token ref = null] : (token= BasicAnnotationType |did= dottedId ) ;
    public final Token annotationType() throws RecognitionException {
        Token ref =  null;


        Token token=null;
        Token did =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1594:2: ( (token= BasicAnnotationType |did= dottedId ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1595:2: (token= BasicAnnotationType |did= dottedId )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1595:2: (token= BasicAnnotationType |did= dottedId )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1597:2: token= BasicAnnotationType
                    {
                    token=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType9720); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1599:2: did= dottedId
                    {
                    pushFollow(FOLLOW_dottedId_in_annotationType9732);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1603:1: wordListExpression returns [WordListExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordListExpression wordListExpression() throws RecognitionException {
        WordListExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1604:2: (id= Identifier |path= RessourceLiteral )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1605:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression9757); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordListExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1608:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression9770); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1613:1: wordTableExpression returns [WordTableExpression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final WordTableExpression wordTableExpression() throws RecognitionException {
        WordTableExpression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1614:2: (id= Identifier |path= RessourceLiteral )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1615:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression9794); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceWordTableExpression(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1618:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression9807); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1623:1: numberFunction returns [NumberExpression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final NumberExpression numberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression numExprP =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1624:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1625:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1625:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1625:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
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


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction9852);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1628:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction9876);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1633:1: externalNumberFunction returns [NumberExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final NumberExpression externalNumberFunction() throws RecognitionException {
        NumberExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1634:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1636:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction9902); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction9904); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction9911);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction9913); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1643:1: numberVariable returns [Token ref = null] : ({...}?token1= Identifier |{...}?token2= Identifier |{...}?token2= Identifier );
    public final Token numberVariable() throws RecognitionException {
        Token ref =  null;


        Token token1=null;
        Token token2=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1644:2: ({...}?token1= Identifier |{...}?token2= Identifier |{...}?token2= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1645:2: {...}?token1= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INT\")");
                    }

                    token1=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9938); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1646:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLE\")");
                    }

                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9951); if (state.failed) return ref;

                    if ( state.backtracking==0 ) {ref = token2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1647:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "FLOAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"FLOAT\")");
                    }

                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9964); if (state.failed) return ref;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1651:1: additiveExpression returns [NumberExpression expr = null] : e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* ;
    public final NumberExpression additiveExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1654:2: (e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:2: e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9994);
            e=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {exprs.add(e);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:46: (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            loop175:
            do {
                int alt175=2;
                int LA175_0 = input.LA(1);

                if ( (LA175_0==MINUS||LA175_0==PLUS) ) {
                    alt175=1;
                }


                switch (alt175) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1655:48: op= ( PLUS | MINUS ) e= multiplicativeExpression
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

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10016);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1659:1: multiplicativeExpression returns [NumberExpression expr = null] : (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) ;
    public final NumberExpression multiplicativeExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token op=null;
        NumberExpression e =null;

        NumberExpression e1 =null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1662:2: ( (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1663:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1663:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* |e1= numberFunction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1663:3: e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10049);
                    e=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1663:45: (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    loop176:
                    do {
                        int alt176=2;
                        int LA176_0 = input.LA(1);

                        if ( (LA176_0==PERCENT||(LA176_0 >= SLASH && LA176_0 <= STAR)) ) {
                            alt176=1;
                        }


                        switch (alt176) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1663:47: op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression
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

                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10077);
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1665:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression10095);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1668:1: numberExpression returns [NumberExpression expr = null] : e= additiveExpression ;
    public final NumberExpression numberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1669:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1670:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression10118);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1673:1: numberExpressionInPar returns [NumberExpression expr = null] : LPAREN e= additiveExpression RPAREN ;
    public final NumberExpression numberExpressionInPar() throws RecognitionException {
        NumberExpression expr =  null;


        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1674:2: ( LPAREN e= additiveExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1675:2: LPAREN e= additiveExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar10136); if (state.failed) return expr;

            pushFollow(FOLLOW_additiveExpression_in_numberExpressionInPar10143);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar10145); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1678:1: simpleNumberExpression returns [NumberExpression expr = null] : ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar );
    public final NumberExpression simpleNumberExpression() throws RecognitionException {
        NumberExpression expr =  null;


        Token m=null;
        Token lit=null;
        Token var =null;

        NumberExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1679:2: ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable |e= numberExpressionInPar )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1680:2: (m= MINUS )? lit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1680:4: (m= MINUS )?
                    int alt178=2;
                    int LA178_0 = input.LA(1);

                    if ( (LA178_0==MINUS) ) {
                        alt178=1;
                    }
                    switch (alt178) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1680:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10168); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression10175); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createIntegerExpression(lit,m);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1682:4: (m= MINUS )? lit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1682:6: (m= MINUS )?
                    int alt179=2;
                    int LA179_0 = input.LA(1);

                    if ( (LA179_0==MINUS) ) {
                        alt179=1;
                    }
                    switch (alt179) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1682:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10189); if (state.failed) return expr;

                            }
                            break;

                    }


                    lit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10196); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDoubleExpression(lit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1683:4: (m= MINUS )? var= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1683:6: (m= MINUS )?
                    int alt180=2;
                    int LA180_0 = input.LA(1);

                    if ( (LA180_0==MINUS) ) {
                        alt180=1;
                    }
                    switch (alt180) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1683:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10207); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression10214);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createReferenceNumberExpression(var,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1684:4: e= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression10225);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1687:1: stringExpression returns [StringExpression expr = null] options {backtrack=true; } : (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1694:2: (e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )* | (e= stringFunction )=>e= stringFunction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1695:2: e= simpleStringExpression ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10265);
                    e=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {exprs.add(e);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1696:2: ( PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression ) )*
                    loop183:
                    do {
                        int alt183=2;
                        int LA183_0 = input.LA(1);

                        if ( (LA183_0==PLUS) ) {
                            alt183=1;
                        }


                        switch (alt183) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1696:3: PLUS (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression10272); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1696:8: (e1= simpleStringExpression |e2= numberExpressionInPar |be= simpleBooleanExpression |te= typeExpression |le= listExpression )
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
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1696:9: e1= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10279);
                    	            e1=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1697:5: e2= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression10292);
                    	            e2=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(e2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1698:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression10304);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1699:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression10316);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {exprs.add(te);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1700:5: le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression10328);
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:3: (e= stringFunction )=>e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression10356);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1707:1: stringFunction returns [StringExpression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final StringExpression stringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token name=null;
        Token var =null;

        StringExpression t =null;

        StringExpression e =null;


        List<StringExpression> list = new ArrayList<StringExpression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1709:2: (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1710:2: name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction10383); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction10385); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction10391);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1710:44: ( COMMA t= stringExpression )+
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
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1710:45: COMMA t= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction10394); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction10400);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction10406); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = StringFunctionFactory.createRemoveFunction(var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction10428);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1717:1: externalStringFunction returns [StringExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final StringExpression externalStringFunction() throws RecognitionException {
        StringExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1718:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1720:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction10453); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction10455); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction10462);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction10464); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1727:1: simpleStringExpression returns [StringExpression expr = null] : (lit= StringLiteral |{...}?id= Identifier );
    public final StringExpression simpleStringExpression() throws RecognitionException {
        StringExpression expr =  null;


        Token lit=null;
        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1728:2: (lit= StringLiteral |{...}?id= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1729:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression10488); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleStringExpression(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1730:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRING\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression10502); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1734:1: booleanExpression returns [BooleanExpression expr = null] : ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final BooleanExpression booleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e =null;

        BooleanExpression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1735:2: ( (e= composedBooleanExpression )=>e= composedBooleanExpression |sbE= simpleBooleanExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1736:2: (e= composedBooleanExpression )=>e= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10535);
                    e=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1737:3: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10546);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1740:1: simpleBooleanExpression returns [BooleanExpression expr = null] : (e= literalBooleanExpression |{...}?id= Identifier );
    public final BooleanExpression simpleBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1741:2: (e= literalBooleanExpression |{...}?id= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1742:3: e= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10569);
                    e=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1743:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression10584); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1748:1: composedBooleanExpression returns [BooleanExpression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final BooleanExpression composedBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        BooleanExpression e2 =null;

        BooleanExpression bte =null;

        BooleanExpression bne =null;

        BooleanExpression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1750:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1751:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression10618);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1752:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression10638);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1753:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression10657);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1754:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression10667);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1758:1: booleanFunction returns [BooleanExpression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final BooleanExpression booleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;

        BooleanExpression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1760:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1761:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1761:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1761:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction10692); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction10694); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10700);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction10702); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10708);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction10710); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1763:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction10732);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1767:1: externalBooleanFunction returns [BooleanExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final BooleanExpression externalBooleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;


        Token id=null;
        List args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1768:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1770:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction10757); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction10759); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction10766);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction10768); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1778:1: booleanCompare returns [BooleanExpression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final BooleanExpression booleanCompare() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        BooleanExpression e1 =null;

        BooleanExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1779:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1780:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1780:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1780:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare10793);
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


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare10811);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1785:1: literalBooleanExpression returns [BooleanExpression expr = null] : (v= TRUE |v= FALSE );
    public final BooleanExpression literalBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1786:2: (v= TRUE |v= FALSE )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1787:2: v= TRUE
                    {
                    v=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression10837); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleBooleanExpression(v);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1788:4: v= FALSE
                    {
                    v=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression10849); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1793:1: booleanTypeExpression returns [BooleanExpression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final BooleanExpression booleanTypeExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        TypeExpression e1 =null;

        TypeExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1794:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1795:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10875);
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


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10895);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1801:1: booleanNumberExpression returns [BooleanExpression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final BooleanExpression booleanNumberExpression() throws RecognitionException {
        BooleanExpression expr =  null;


        Token op=null;
        NumberExpression e1 =null;

        NumberExpression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1802:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1803:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression10917); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10924);
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


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10960);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression10963); if (state.failed) return expr;

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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:508:4: ( ruleElementComposed[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:508:5: ruleElementComposed[null]
        {
        pushFollow(FOLLOW_ruleElementComposed_in_synpred1_TextMarkerParser1850);
        ruleElementComposed(null);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_TextMarkerParser

    // $ANTLR start synpred2_TextMarkerParser
    public final void synpred2_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:509:4: ( ruleElementDisjunctive[null] )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:509:5: ruleElementDisjunctive[null]
        {
        pushFollow(FOLLOW_ruleElementDisjunctive_in_synpred2_TextMarkerParser1867);
        ruleElementDisjunctive(null);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:2: ( booleanListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:631:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred7_TextMarkerParser2456);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:632:4: ( intListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:632:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred8_TextMarkerParser2472);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred9_TextMarkerParser
    public final void synpred9_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:633:4: ( doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:633:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred9_TextMarkerParser2488);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_TextMarkerParser

    // $ANTLR start synpred10_TextMarkerParser
    public final void synpred10_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:634:4: ( floatListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:634:5: floatListExpression
        {
        pushFollow(FOLLOW_floatListExpression_in_synpred10_TextMarkerParser2504);
        floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_TextMarkerParser

    // $ANTLR start synpred11_TextMarkerParser
    public final void synpred11_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:635:4: ( stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:635:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred11_TextMarkerParser2520);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:4: ( typeListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:636:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred12_TextMarkerParser2536);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred13_TextMarkerParser
    public final void synpred13_TextMarkerParser_fragment() throws RecognitionException {
        NumberListExpression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:675:2: (e1= doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:675:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred13_TextMarkerParser2743);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {
        NumberListExpression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:2: (e1= floatListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:677:3: e1= floatListExpression
        {
        pushFollow(FOLLOW_floatListExpression_in_synpred14_TextMarkerParser2764);
        e1=floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression tf =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:754:2: (tf= typeFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:754:2: tf= typeFunction
        {
        pushFollow(FOLLOW_typeFunction_in_synpred15_TextMarkerParser3172);
        tf=typeFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {
        AbstractTextMarkerCondition c =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:865:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:865:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred17_TextMarkerParser3744);
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


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        match(input,COUNT,FOLLOW_COUNT_in_synpred18_TextMarkerParser4106); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred18_TextMarkerParser4108); if (state.failed) return ;

        pushFollow(FOLLOW_listExpression_in_synpred18_TextMarkerParser4114);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4116); if (state.failed) return ;

        pushFollow(FOLLOW_argument_in_synpred18_TextMarkerParser4122);
        a=argument();

        state._fsp--;
        if (state.failed) return ;

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:916:60: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4125); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser4131);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4133); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser4139);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:917:5: ( COMMA var= numberVariable )?
        int alt194=2;
        int LA194_0 = input.LA(1);

        if ( (LA194_0==COMMA) ) {
            alt194=1;
        }
        switch (alt194) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:917:6: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser4149); if (state.failed) return ;

                pushFollow(FOLLOW_numberVariable_in_synpred18_TextMarkerParser4155);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        match(input,RPAREN,FOLLOW_RPAREN_in_synpred18_TextMarkerParser4159); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {
        StringListExpression list2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:20: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:941:21: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred19_TextMarkerParser4433);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred20_TextMarkerParser
    public final void synpred20_TextMarkerParser_fragment() throws RecognitionException {
        AbstractTextMarkerAction a =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1113:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1113:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred20_TextMarkerParser6006);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_TextMarkerParser

    // $ANTLR start synpred21_TextMarkerParser
    public final void synpred21_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:5: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:6: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred21_TextMarkerParser6159);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:81: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1148:82: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred22_TextMarkerParser6172); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred22_TextMarkerParser6178);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred23_TextMarkerParser
    public final void synpred23_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:6: (index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:7: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred23_TextMarkerParser6471);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred23_TextMarkerParser

    // $ANTLR start synpred24_TextMarkerParser
    public final void synpred24_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:82: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1180:83: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred24_TextMarkerParser6484); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred24_TextMarkerParser6490);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred24_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:22: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:23: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred28_TextMarkerParser7295);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        TypeExpression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:85: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1283:86: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred29_TextMarkerParser7315);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1547:2: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1547:2: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred31_TextMarkerParser9487);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1548:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1548:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred32_TextMarkerParser9498);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred33_TextMarkerParser
    public final void synpred33_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1549:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred33_TextMarkerParser9509);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred33_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {
        NumberExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1628:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1628:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred34_TextMarkerParser9868);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred36_TextMarkerParser
    public final void synpred36_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:3: (e= stringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1703:4: e= stringFunction
        {
        pushFollow(FOLLOW_stringFunction_in_synpred36_TextMarkerParser10348);
        e=stringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred36_TextMarkerParser

    // $ANTLR start synpred37_TextMarkerParser
    public final void synpred37_TextMarkerParser_fragment() throws RecognitionException {
        StringExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1713:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred37_TextMarkerParser10420);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred37_TextMarkerParser

    // $ANTLR start synpred38_TextMarkerParser
    public final void synpred38_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1736:2: (e= composedBooleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1736:3: e= composedBooleanExpression
        {
        pushFollow(FOLLOW_composedBooleanExpression_in_synpred38_TextMarkerParser10527);
        e=composedBooleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred38_TextMarkerParser

    // $ANTLR start synpred39_TextMarkerParser
    public final void synpred39_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1751:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1751:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred39_TextMarkerParser10610);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred39_TextMarkerParser

    // $ANTLR start synpred40_TextMarkerParser
    public final void synpred40_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1752:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1752:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred40_TextMarkerParser10630);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred40_TextMarkerParser

    // $ANTLR start synpred41_TextMarkerParser
    public final void synpred41_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1753:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1753:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred41_TextMarkerParser10649);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred41_TextMarkerParser

    // $ANTLR start synpred42_TextMarkerParser
    public final void synpred42_TextMarkerParser_fragment() throws RecognitionException {
        BooleanExpression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1763:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-engine\\src\\main\\java\\org\\apache\\uima\\textmarker\\parser\\TextMarkerParser.g:1763:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred42_TextMarkerParser10724);
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
            return "349:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
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
    public static final BitSet FOLLOW_IntString_in_variableDeclaration299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration308 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration323 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration333 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration365 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration380 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration391 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatString_in_variableDeclaration413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration422 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration429 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration437 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration448 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration479 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration494 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration505 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration536 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration551 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration562 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration584 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration593 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration608 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration619 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration654 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration657 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration663 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration681 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration693 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration696 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration730 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration768 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration794 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration806 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration815 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration844 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration853 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATLIST_in_variableDeclaration870 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration882 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration920 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONDITION_in_conditionDeclaration987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_conditionDeclaration993 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionDeclaration997 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionDeclaration1003 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionDeclaration1005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_conditionDeclaration1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_actionDeclaration1043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_actionDeclaration1049 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDeclaration1053 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_actionDeclaration1059 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDeclaration1061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_actionDeclaration1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement1088 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1094 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement1102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement1116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier2_in_importStatement1122 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1149 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_declaration1159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1167 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_declaration1174 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1188 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_declaration1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1204 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_declaration1210 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1216 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1222 = new BitSet(new long[]{0x0800100000140000L,0x0000000000001800L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotationType_in_declaration1237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_StringString_in_declaration1250 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1263 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_FloatString_in_declaration1275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_IntString_in_declaration1287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1315 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1327 = new BitSet(new long[]{0x0800100000140000L,0x0000000000001800L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotationType_in_declaration1342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_StringString_in_declaration1355 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_FloatString_in_declaration1380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_IntString_in_declaration1392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1420 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_declaration1431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1489 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1500 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1504 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1528 = new BitSet(new long[]{0x0C001120101E8010L,0x0020000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1534 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AutomataBlockString_in_automataDeclaration1588 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_automataDeclaration1592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_automataDeclaration1599 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_automataDeclaration1603 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_automataDeclaration1616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_automataDeclaration1625 = new BitSet(new long[]{0x0C001120101E8010L,0x0020000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_automataDeclaration1631 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_automataDeclaration1633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1670 = new BitSet(new long[]{0x0000000000000000L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1687 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1699 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1705 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1709 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1715 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1760 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1790 = new BitSet(new long[]{0x0000000000040002L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1800 = new BitSet(new long[]{0x0000000000040002L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_ruleElement1876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementDisjunctive1908 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1933 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1939 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementDisjunctive1948 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementDisjunctive1954 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementDisjunctive1963 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementDisjunctive1989 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementDisjunctive2002 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementDisjunctive2008 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementDisjunctive2012 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementDisjunctive2018 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementDisjunctive2022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed2063 = new BitSet(new long[]{0x0000000000040000L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed2091 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed2101 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed2107 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed2111 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed2117 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2121 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2127 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2174 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2193 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2206 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2212 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2216 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementType2222 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2273 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2297 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2310 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2316 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2320 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2326 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2368 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_conditions2373 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditions2379 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_action_in_actions2417 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_actions2422 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_action_in_actions2428 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_listExpression2512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2587 = new BitSet(new long[]{0x0020000000000000L,0x0020000000000800L,0x0000000000020000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2594 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2599 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000800L,0x0000000000020000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2605 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2675 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2682 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2687 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2693 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_numberListExpression2772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2828 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2835 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2840 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2846 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleFloatListExpression_in_floatListExpression2896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleFloatListExpression2917 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2924 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleFloatListExpression2929 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2935 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleFloatListExpression2944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleFloatListExpression2959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression3004 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression3011 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression3016 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression3022 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression3031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression3047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression3072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression3093 = new BitSet(new long[]{0x0000000000040000L,0x0020000000000800L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3100 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression3105 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3111 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression3120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression3135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression3172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression3183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction3217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction3242 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3244 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3251 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeExpression3278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3376 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3393 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3409 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3426 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3432 = new BitSet(new long[]{0x0000000004000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3439 = new BitSet(new long[]{0x2001040100000000L,0x0010000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3446 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3452 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3818 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3820 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3826 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3857 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3859 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3865 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3921 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3928 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3936 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3938 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionContains3944 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3953 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3959 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3961 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3967 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3970 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3976 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount4015 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount4017 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount4023 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4026 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount4032 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4034 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount4040 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4050 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount4056 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount4060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount4114 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4116 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionCount4122 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4125 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4131 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4133 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4139 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4149 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4155 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4177 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4179 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount4185 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4188 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4194 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4196 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4202 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4218 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4260 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4266 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4269 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4275 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4277 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4283 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4298 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4337 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4343 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4346 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4352 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4354 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4360 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4376 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4425 = new BitSet(new long[]{0x0000000000000000L,0x4000000000020800L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4440 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4448 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4452 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4458 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4461 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4467 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4510 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4516 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4519 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4525 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4527 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4533 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4578 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4580 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4586 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4635 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4637 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4643 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4645 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4651 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4653 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4659 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4698 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4704 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4706 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4712 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4714 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4720 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4723 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4729 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4732 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4738 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4780 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditionNot4786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4793 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4795 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditionNot4801 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4844 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4850 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4884 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4891 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4897 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4930 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4932 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq4939 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq4945 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq4948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition4982 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition4984 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition4990 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition4992 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition4998 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition5000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp5030 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp5032 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp5038 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp5041 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp5047 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp5051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore5086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore5088 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5094 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5097 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5103 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore5116 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore5123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote5158 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote5160 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5166 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote5168 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5174 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote5176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf5214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf5216 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf5222 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf5224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5260 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5266 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5268 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5274 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_conditionParse5320 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5355 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5362 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5368 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5404 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5411 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5417 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5451 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5453 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5460 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5466 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5502 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5509 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5515 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5549 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5551 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5558 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5564 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5606 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5609 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5615 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5617 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5623 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5628 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5634 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action5743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action5761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action5788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action5797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action5806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action5815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action5824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action5833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action5842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action5851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action5860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action5869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action5878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action5887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action5896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action5905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action5914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action5923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action5932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action5941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action5950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action5959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action5968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action5977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionConfigure_in_action5986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDynamicAnchoring_in_action5995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action6014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action6023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction6052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction6080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction6082 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction6088 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction6090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate6126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate6128 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate6134 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6141 = new BitSet(new long[]{0x2001040100000000L,0x2200000800C00800L,0x0000000000001204L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6166 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6183 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6189 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6196 = new BitSet(new long[]{0x0000000000000000L,0x2200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6209 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6211 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionCreate6217 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6227 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6233 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6235 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionCreate6241 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6299 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6310 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6312 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6323 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6325 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6335 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6343 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6357 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6359 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6365 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6375 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6381 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6383 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6389 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6443 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6445 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6451 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6458 = new BitSet(new long[]{0x2001040100000000L,0x2200000800C00800L,0x0000000000001204L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6478 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6494 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6500 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6507 = new BitSet(new long[]{0x0000000000000000L,0x2200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6520 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6522 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C20800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6529 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6537 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6548 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6554 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6556 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C20800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6563 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6571 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6631 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6637 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6640 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6646 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6648 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionFill6665 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6722 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6728 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6740 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6751 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6759 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6769 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6777 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor6787 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor6797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel6831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog6873 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog6875 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog6881 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionLog6884 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog6890 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog6894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark6933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark6935 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark6946 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMark6962 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark6978 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark7002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand7046 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand7048 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand7059 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand7075 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand7091 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand7115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore7160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore7162 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7173 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7175 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore7185 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7201 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7217 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore7241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7287 = new BitSet(new long[]{0x2001040100040000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7304 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7306 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7324 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7340 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7356 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7414 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7416 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7422 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7424 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7430 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7433 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7439 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7442 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7448 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7490 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7496 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7534 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7540 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7585 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7588 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7594 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7599 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7605 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7657 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7660 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7666 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7671 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7677 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup7731 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup7734 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7740 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup7745 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7751 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup7757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType7803 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType7806 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7812 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType7817 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7823 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType7829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall7869 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall7871 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionCall7877 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall7879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONFIGURE_in_actionConfigure7917 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionConfigure7919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionConfigure7925 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure7932 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure7942 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure7944 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionConfigure7950 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure7960 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure7966 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure7968 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionConfigure7974 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionConfigure7984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec8016 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec8018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionExec8024 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExec8027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec8033 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec8037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign8080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign8082 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8109 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8111 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionAssign8117 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8155 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8157 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionAssign8163 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8201 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8203 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionAssign8209 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8247 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8249 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8255 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8294 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8296 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8302 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8340 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8342 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign8348 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8401 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8407 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8409 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8415 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8458 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8464 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8466 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8472 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDynamicAnchoring8512 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionDynamicAnchoring8518 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8526 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8532 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8540 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8546 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDynamicAnchoring8563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8593 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8595 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8601 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8641 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8647 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8655 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8661 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8702 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8708 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8750 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8752 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8762 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8764 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8770 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8778 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8784 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8786 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8792 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8802 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8808 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8815 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8821 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8828 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8834 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8841 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8847 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8854 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8860 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8867 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8873 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie8879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd8924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd8926 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd8932 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd8935 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionAdd8941 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd8947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove8987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove8989 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove8995 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove8998 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionRemove9004 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove9010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9046 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate9048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate9054 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate9056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge9101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge9103 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge9109 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge9117 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9125 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9136 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge9142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet9177 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet9179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionGet9185 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9187 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionGet9193 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9195 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet9201 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet9203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList9238 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList9240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList9246 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList9248 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList9254 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList9256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText9295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText9297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText9308 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText9324 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText9330 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText9354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear9394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear9396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionClear9402 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear9404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList9431 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9437 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList9441 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9447 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList9453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument9487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument9498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9554 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9567 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9577 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29603 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_dottedIdentifier29616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier29630 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9662 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedId9675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9685 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType9720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationType9732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression9757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression9770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression9794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression9807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction9830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction9852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction9876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction9902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction9904 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction9911 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction9913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9994 = new BitSet(new long[]{0x0000000000000002L,0x0001000800000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression10003 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10016 = new BitSet(new long[]{0x0000000000000002L,0x0001000800000000L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10049 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression10058 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10077 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression10095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression10118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar10136 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpressionInPar10143 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar10145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10168 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression10175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10189 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression10214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression10225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10265 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression10272 = new BitSet(new long[]{0x0020000000040000L,0x0000000000820800L,0x0000000000020200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10279 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression10292 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression10304 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression10316 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression10328 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression10356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction10383 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction10385 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_stringFunction10391 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction10394 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction10400 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction10406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction10428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction10453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction10455 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction10462 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction10464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression10488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression10502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression10584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression10618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression10638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression10657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression10667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction10692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction10694 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10700 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction10702 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10708 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction10710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction10732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction10757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction10759 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction10766 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction10768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare10793 = new BitSet(new long[]{0x0000400000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_set_in_booleanCompare10799 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare10811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression10837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression10849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10875 = new BitSet(new long[]{0x0000400000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression10882 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression10917 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10924 = new BitSet(new long[]{0x0000400000000000L,0x00000080000C000CL});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression10931 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10960 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression10963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_synpred1_TextMarkerParser1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementDisjunctive_in_synpred2_TextMarkerParser1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred7_TextMarkerParser2456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred8_TextMarkerParser2472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred9_TextMarkerParser2488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred10_TextMarkerParser2504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred11_TextMarkerParser2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred12_TextMarkerParser2536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred13_TextMarkerParser2743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred14_TextMarkerParser2764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_synpred15_TextMarkerParser3172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred17_TextMarkerParser3744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred18_TextMarkerParser4106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred18_TextMarkerParser4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_synpred18_TextMarkerParser4114 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4116 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_synpred18_TextMarkerParser4122 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4125 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser4131 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4133 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser4139 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser4149 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_synpred18_TextMarkerParser4155 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred18_TextMarkerParser4159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred19_TextMarkerParser4433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred20_TextMarkerParser6006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred21_TextMarkerParser6159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred22_TextMarkerParser6172 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred22_TextMarkerParser6178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred23_TextMarkerParser6471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred24_TextMarkerParser6484 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred24_TextMarkerParser6490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred28_TextMarkerParser7295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred29_TextMarkerParser7315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred31_TextMarkerParser9487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred32_TextMarkerParser9498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred33_TextMarkerParser9509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred34_TextMarkerParser9868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_synpred36_TextMarkerParser10348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred37_TextMarkerParser10420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_synpred38_TextMarkerParser10527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred39_TextMarkerParser10610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred40_TextMarkerParser10630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred41_TextMarkerParser10649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred42_TextMarkerParser10724 = new BitSet(new long[]{0x0000000000000002L});

}