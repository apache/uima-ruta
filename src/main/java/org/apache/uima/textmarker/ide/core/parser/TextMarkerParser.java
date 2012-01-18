// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g 2012-01-18 13:50:26

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

package org.apache.uima.textmarker.ide.core.parser;
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
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;

import org.apache.uima.textmarker.ide.core.extensions.TextMarkerExternalFactory;
import org.apache.uima.textmarker.ide.core.builder.DescriptorManager;
import org.apache.uima.textmarker.ide.parser.ast.ActionFactory;
import org.apache.uima.textmarker.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.ComponentReference;
import org.apache.uima.textmarker.ide.parser.ast.ComposedRuleElement;
import org.apache.uima.textmarker.ide.parser.ast.ConditionFactory;
import org.apache.uima.textmarker.ide.parser.ast.ExpressionFactory;
import org.apache.uima.textmarker.ide.parser.ast.ScriptFactory;
import org.apache.uima.textmarker.ide.parser.ast.StatementFactory;
import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBlock;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRule;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRuleElement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerScriptBlock;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerFeatureDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerPackageDeclaration;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TextMarkerParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ADD", "AFTER", "ALT_NOTEQUAL", "AMPER", "AND", "ASSIGN", "ASSIGN_EQUAL", "AT", "ATTRIBUTE", "Annotation", "AutomataBlockString", "BEFORE", "BOOLEANLIST", "BasicAnnotationType", "BlockString", "BooleanString", "CALL", "CIRCUMFLEX", "CLEAR", "COLON", "COLOR", "COMMA", "COMMENT", "CONDITION", "CONFIGURE", "CONTAINS", "CONTEXTCOUNT", "COS", "COUNT", "CREATE", "CURRENTCOUNT", "CharacterLiteral", "DECLARE", "DEL", "DOT", "DOUBLELIST", "DYNAMICANCHORING", "DecimalLiteral", "DocComment", "DoubleString", "ENDSWITH", "EQUAL", "EXEC", "EXP", "EXPAND", "EngineString", "EscapeSequence", "Exponent", "FALSE", "FEATURE", "FILL", "FILTERTYPE", "FLOATLIST", "FloatString", "FloatTypeSuffix", "FloatingPointLiteral", "GATHER", "GET", "GETFEATURE", "GETLIST", "GREATER", "GREATEREQUAL", "HexDigit", "HexLiteral", "IF", "INLIST", "INTLIST", "IS", "Identifier", "IntString", "IntegerTypeSuffix", "JavaIDDigit", "LAST", "LBRACK", "LCURLY", "LESS", "LESSEQUAL", "LINE_COMMENT", "LOG", "LOGN", "LPAREN", "Letter", "ListIdentifier", "LogLevel", "MARK", "MARKFAST", "MARKLAST", "MARKONCE", "MARKSCORE", "MARKTABLE", "MATCHEDTEXT", "MERGE", "MINUS", "MOFN", "NEAR", "NOT", "NOTEQUAL", "OR", "OctalEscape", "OctalLiteral", "OldColor", "PARSE", "PARTOF", "PARTOFNEQ", "PERCENT", "PLUS", "POSITION", "PackageString", "QUESTION", "RBRACK", "RCURLY", "REGEXP", "REMOVE", "REMOVEDUPLICATE", "REMOVESTRING", "REPLACE", "RETAINTYPE", "RPAREN", "RessourceLiteral", "SCORE", "SEMI", "SETFEATURE", "SIN", "SIZE", "SLASH", "STAR", "STARTSWITH", "STRINGLIST", "ScriptString", "StringLiteral", "StringString", "SymbolString", "TAN", "THEN", "TOTALCOUNT", "TRANSFER", "TRIE", "TRUE", "TYPELIST", "TypeString", "TypeSystemString", "UNMARK", "UNMARKALL", "UnicodeEscape", "VBAR", "VOTE", "WORDLIST", "WORDTABLE", "WS", "XOR"
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
    public static final int FILTERTYPE=56;
    public static final int FLOATLIST=57;
    public static final int FloatString=58;
    public static final int FloatTypeSuffix=59;
    public static final int FloatingPointLiteral=60;
    public static final int GATHER=61;
    public static final int GET=62;
    public static final int GETFEATURE=63;
    public static final int GETLIST=64;
    public static final int GREATER=65;
    public static final int GREATEREQUAL=66;
    public static final int HexDigit=67;
    public static final int HexLiteral=68;
    public static final int IF=69;
    public static final int INLIST=70;
    public static final int INTLIST=71;
    public static final int IS=72;
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
    public static final int RETAINTYPE=121;
    public static final int RPAREN=122;
    public static final int RessourceLiteral=123;
    public static final int SCORE=124;
    public static final int SEMI=125;
    public static final int SETFEATURE=126;
    public static final int SIN=127;
    public static final int SIZE=128;
    public static final int SLASH=129;
    public static final int STAR=130;
    public static final int STARTSWITH=131;
    public static final int STRINGLIST=132;
    public static final int ScriptString=133;
    public static final int StringLiteral=134;
    public static final int StringString=135;
    public static final int SymbolString=136;
    public static final int TAN=137;
    public static final int THEN=138;
    public static final int TOTALCOUNT=139;
    public static final int TRANSFER=140;
    public static final int TRIE=141;
    public static final int TRUE=142;
    public static final int TYPELIST=143;
    public static final int TypeString=144;
    public static final int TypeSystemString=145;
    public static final int UNMARK=146;
    public static final int UNMARKALL=147;
    public static final int UnicodeEscape=148;
    public static final int VBAR=149;
    public static final int VOTE=150;
    public static final int WORDLIST=151;
    public static final int WORDTABLE=152;
    public static final int WS=153;
    public static final int XOR=154;

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
    public String getGrammarFileName() { return "D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g"; }


    	public DLTKTextMarkerErrorReporter reporter;
    	public ModuleDeclaration md;
    	private List<String> vars = new ArrayList<String>();	
    	private Map<String, String> varTypeMap = new HashMap<String, String>();
    	private Map<String, String> lists = new HashMap<String, String>();
    	private Map<String, String> tables = new HashMap<String, String>();
    	public int length;
    	public DLTKTokenConverter converter;
    	public DescriptorManager descriptor;
    	private int level = 0;
    	private TextMarkerExternalFactory external = new TextMarkerExternalFactory();
    	
    	private String module;
    	private String packageString;
    	
    	
    	public List<String> getVariables() {
    		return vars;
    	}
    	
    	public Map<String, String> getVariableTypes() {
    		return varTypeMap;
    	}
    	
    	DLTKToken toDLTK(Token token) {
    		return converter.convert(token);
    	}
    	public void emitErrorMessage(String msg) {
    		reporter.reportMessage(msg);
    	}
    	public void reportError(RecognitionException e) {
    		if( reporter != null ) {
    			reporter.reportError(e);
    		}
    	}
    	
    	public void addType(TextMarkerBlock parent, String type, String parentType) {
    		vars.add(type);
    		descriptor.addType(parent.getNamespace()+"."+type.trim(), "Type defined in "+packageString+"."+module, parentType);
    	}
    	
    	public void addPredefinedType(String type) {
    		vars.add(type);
    		varTypeMap.put(type, "TYPE");
    		
    	}
    	
    	public void addType(TextMarkerBlock parent, String name, String parentType, List featuresTypes,
              List<Token> featuresNames) {
    	   	 name = parent.getNamespace() + "." + name.trim();
    	   	 descriptor.addType(name, "Type defined in " + packageString + "." + module, parentType);
    	    	for (int i = 0; i < featuresTypes.size(); i++) {
    	    	  Object object = featuresTypes.get(i);
    	    	  String ftype = "";
    	    	  if (object instanceof ASTNode) {
    	    		    ftype = ((ASTNode) object).toString();
    		      } else if (object instanceof Token) {
    	     	   ftype = ((Token) object).getText();
    	     	 }
    		      String fname = featuresNames.get(i).getText();
    	     	 descriptor.addFeature(name, fname, fname, ftype);
    	   	 }
     	 }
    	
    	public void addWordList(TextMarkerBlock parent, String name, String list) {
    		lists.put(name, list);
    	}
    	
    	public void addCSVTable(TextMarkerBlock parent, String name, String table) {
    		tables.put(name, table);
    	}
    	
    	public boolean isType(TextMarkerBlock parent, String type) {
    		return vars.contains(type);
    	}
    		
    	public void addVariable(String var, String type, IntStream input) throws NoViableAltException {
    		if(!vars.contains(var)) {
    			vars.add(var);
    			varTypeMap.put(var, type);
    		} 
    	}
    	
    	public void addVariable(String var, String type) {
    		if(!vars.contains(var)) {
    			vars.add(var);
    			varTypeMap.put(var, type);
    		}
    	}
    	
    	public boolean isVariable(String var) {
    		return vars.contains(var);
    	}
    	
    	public boolean isVariableOfType(String var, String type) {
    		return vars.contains(var) && type.equals(varTypeMap.get(var));
    	}
    	
    	public void checkVariable(String var, IntStream input) throws NoViableAltException {
    		if(!vars.contains(var)) {
    			throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
    		}
    	}
    	
    	  public void addImportTypeSystem(ComponentDeclaration module) {
    	    descriptor.addTypeSystem(module.getName());
    	  }
    	
    	  public void addImportScript(ComponentDeclaration module) {
    		    descriptor.addScript(module.getName());
    		  }
    	
    	  public void addImportEngine(ComponentDeclaration module) {
    		    descriptor.addEngine(module.getName());
    		  }
    	
    	protected static final int[] getBounds(Token t) {
    		if (t instanceof CommonToken) {
    			CommonToken ct = (CommonToken) t;
    			int[] bounds = {ct.getStartIndex(), ct.getStopIndex()}; 
    			return bounds;
    		}
    		return null;
    	}
    	
    //	public String getTypeOf(String varName) {
    //		String vn = varTypeMap.get(varName);
    //		return vn != null? vn : "";
    //	}
    	



    // $ANTLR start "file_input"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:229:1: file_input[String module] : p= packageDeclaration gs= globalStatements s= statements EOF ;
    public final void file_input(String module) throws RecognitionException {
        TextMarkerPackageDeclaration p =null;

        List<Statement> gs =null;

        List<Statement> s =null;



        TextMarkerScriptBlock rootBlock = null;
        List<Statement> stmts = new ArrayList<Statement>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:234:2: (p= packageDeclaration gs= globalStatements s= statements EOF )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:235:2: p= packageDeclaration gs= globalStatements s= statements EOF
            {
            pushFollow(FOLLOW_packageDeclaration_in_file_input73);
            p=packageDeclaration();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	String packageName = "org.apache.uima.tm";
            	if(p != null) {packageName = p.getName();}
            	rootBlock = ScriptFactory.createScriptBlock(0,0,0,0,module, null, null, packageName);
            	stmts.add(p);
            	this.module = module;
            	if(p != null) {
            		this.packageString = p.getName();
            	}
            	}

            if ( state.backtracking==0 ) {blockDeclaration_stack.push(new blockDeclaration_scope());((blockDeclaration_scope)blockDeclaration_stack.peek()).env = rootBlock;}

            pushFollow(FOLLOW_globalStatements_in_file_input87);
            gs=globalStatements();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_statements_in_file_input94);
            s=statements();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	  stmts.addAll(gs);
            	  stmts.addAll(s);
              	  for (Statement stmt : stmts){
            		  if (stmt != null) {
            		    md.addStatement(stmt);
            		  }
            	  };
            	}

            match(input,EOF,FOLLOW_EOF_in_file_input100); if (state.failed) return ;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "file_input"



    // $ANTLR start "packageDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:263:1: packageDeclaration returns [TextMarkerPackageDeclaration pack] : pString= PackageString p= dottedId SEMI ;
    public final TextMarkerPackageDeclaration packageDeclaration() throws RecognitionException {
        TextMarkerPackageDeclaration pack = null;


        Token pString=null;
        Token p =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:264:2: (pString= PackageString p= dottedId SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:264:4: pString= PackageString p= dottedId SEMI
            {
            pString=(Token)match(input,PackageString,FOLLOW_PackageString_in_packageDeclaration121); if (state.failed) return pack;

            if ( state.backtracking==0 ) {
            	pack = StatementFactory.createPkgDeclaration(p, pString);
            	}

            pushFollow(FOLLOW_dottedId_in_packageDeclaration132);
            p=dottedId();

            state._fsp--;
            if (state.failed) return pack;

            if ( state.backtracking==0 ) {
            	pack = StatementFactory.createPkgDeclaration(p, pString);
            	}

            match(input,SEMI,FOLLOW_SEMI_in_packageDeclaration139); if (state.failed) return pack;

            if ( state.backtracking==0 ) {
            	pack = StatementFactory.createPkgDeclaration(p, pString);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return pack;
    }
    // $ANTLR end "packageDeclaration"



    // $ANTLR start "statements"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:278:1: statements returns [List<Statement> stmts = new ArrayList<Statement>()] : (morestmts= statement )* ;
    public final List<Statement> statements() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        List<Statement> morestmts =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:279:2: ( (morestmts= statement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:280:2: (morestmts= statement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:280:2: (morestmts= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==AutomataBlockString||(LA1_0 >= BOOLEANLIST && LA1_0 <= BooleanString)||LA1_0==DECLARE||LA1_0==DOUBLELIST||LA1_0==DoubleString||(LA1_0 >= FLOATLIST && LA1_0 <= FloatString)||LA1_0==INTLIST||(LA1_0 >= Identifier && LA1_0 <= IntString)||LA1_0==LPAREN||LA1_0==STRINGLIST||(LA1_0 >= StringLiteral && LA1_0 <= StringString)||(LA1_0 >= TYPELIST && LA1_0 <= TypeString)||(LA1_0 >= WORDLIST && LA1_0 <= WORDTABLE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:280:3: morestmts= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements163);
            	    morestmts=statement();

            	    state._fsp--;
            	    if (state.failed) return stmts;

            	    if ( state.backtracking==0 ) {if(morestmts != null) {stmts.addAll(morestmts);}}

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "statements"



    // $ANTLR start "globalStatements"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:283:1: globalStatements returns [List<Statement> stmts = new ArrayList<Statement>()] : (morestmts= globalStatement )* ;
    public final List<Statement> globalStatements() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        List<Statement> morestmts =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:284:2: ( (morestmts= globalStatement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:285:2: (morestmts= globalStatement )*
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:285:2: (morestmts= globalStatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==EngineString||LA2_0==ScriptString||LA2_0==TypeSystemString) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:285:3: morestmts= globalStatement
            	    {
            	    pushFollow(FOLLOW_globalStatement_in_globalStatements189);
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
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "globalStatements"



    // $ANTLR start "globalStatement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:288:1: globalStatement returns [List<Statement> stmts = new ArrayList<Statement>()] : stmtImport= importStatement ;
    public final List<Statement> globalStatement() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        Statement stmtImport =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:289:2: (stmtImport= importStatement )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:290:2: stmtImport= importStatement
            {
            pushFollow(FOLLOW_importStatement_in_globalStatement213);
            stmtImport=importStatement();

            state._fsp--;
            if (state.failed) return stmts;

            if ( state.backtracking==0 ) {stmts.add(stmtImport);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "globalStatement"



    // $ANTLR start "statement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:293:1: statement returns [List<Statement> stmts = new ArrayList<Statement>()] : (stmts1= declaration |stmtVariable= variableDeclaration |stmt3= blockDeclaration |stmt2= simpleStatement ) ;
    public final List<Statement> statement() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        List<Statement> stmts1 =null;

        List<Statement> stmtVariable =null;

        TextMarkerBlock stmt3 =null;

        TextMarkerRule stmt2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:294:2: ( (stmts1= declaration |stmtVariable= variableDeclaration |stmt3= blockDeclaration |stmt2= simpleStatement ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:295:2: (stmts1= declaration |stmtVariable= variableDeclaration |stmt3= blockDeclaration |stmt2= simpleStatement )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:295:2: (stmts1= declaration |stmtVariable= variableDeclaration |stmt3= blockDeclaration |stmt2= simpleStatement )
            int alt3=4;
            switch ( input.LA(1) ) {
            case DECLARE:
                {
                alt3=1;
                }
                break;
            case BOOLEANLIST:
            case BooleanString:
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
            case AutomataBlockString:
            case BlockString:
                {
                alt3=3;
                }
                break;
            case BasicAnnotationType:
            case Identifier:
            case LPAREN:
            case StringLiteral:
                {
                alt3=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmts;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:295:4: stmts1= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_statement239);
                    stmts1=declaration();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.addAll(stmts1);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:296:4: stmtVariable= variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement250);
                    stmtVariable=variableDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.addAll(stmtVariable);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:297:4: stmt3= blockDeclaration
                    {
                    pushFollow(FOLLOW_blockDeclaration_in_statement261);
                    stmt3=blockDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.add(stmt3);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:299:4: stmt2= simpleStatement
                    {
                    pushFollow(FOLLOW_simpleStatement_in_statement274);
                    stmt2=simpleStatement();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.add(stmt2);}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "statement"



    // $ANTLR start "importStatement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:305:1: importStatement returns [Statement stmt = null] : (im= TypeSystemString name= dottedComponentDeclaration SEMI |im= ScriptString name= dottedComponentDeclaration SEMI |im= EngineString name= dottedComponentDeclaration SEMI );
    public final Statement importStatement() throws RecognitionException {
        Statement stmt =  null;


        Token im=null;
        ComponentDeclaration name =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:306:2: (im= TypeSystemString name= dottedComponentDeclaration SEMI |im= ScriptString name= dottedComponentDeclaration SEMI |im= EngineString name= dottedComponentDeclaration SEMI )
            int alt4=3;
            switch ( input.LA(1) ) {
            case TypeSystemString:
                {
                alt4=1;
                }
                break;
            case ScriptString:
                {
                alt4=2;
                }
                break;
            case EngineString:
                {
                alt4=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmt;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:307:2: im= TypeSystemString name= dottedComponentDeclaration SEMI
                    {
                    im=(Token)match(input,TypeSystemString,FOLLOW_TypeSystemString_in_importStatement303); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = StatementFactory.createImportTypeSystem(StatementFactory.createEmptyComponentDeclaration(im),im);}

                    pushFollow(FOLLOW_dottedComponentDeclaration_in_importStatement315);
                    name=dottedComponentDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {if(name != null) {stmt = StatementFactory.createImportTypeSystem(name,im);addImportTypeSystem(name);}}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement323); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:312:4: im= ScriptString name= dottedComponentDeclaration SEMI
                    {
                    im=(Token)match(input,ScriptString,FOLLOW_ScriptString_in_importStatement333); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = StatementFactory.createImportScript(StatementFactory.createEmptyComponentDeclaration(im),im);}

                    pushFollow(FOLLOW_dottedComponentDeclaration_in_importStatement345);
                    name=dottedComponentDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {if(name != null) {stmt = StatementFactory.createImportScript(name,im);addImportScript(name);}}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement353); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:317:4: im= EngineString name= dottedComponentDeclaration SEMI
                    {
                    im=(Token)match(input,EngineString,FOLLOW_EngineString_in_importStatement363); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {stmt = StatementFactory.createImportEngine(StatementFactory.createEmptyComponentDeclaration(im),im);}

                    pushFollow(FOLLOW_dottedComponentDeclaration_in_importStatement375);
                    name=dottedComponentDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {if(name != null) {stmt = StatementFactory.createImportEngine(name,im);addImportEngine(name);}}

                    match(input,SEMI,FOLLOW_SEMI_in_importStatement383); if (state.failed) return stmt;

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "importStatement"



    // $ANTLR start "variableDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:324:1: variableDeclaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= FloatString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI |type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI |type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI |type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI |type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= FLOATLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI |type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI );
    public final List<Statement> variableDeclaration() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        Token type=null;
        Token id=null;
        Expression init =null;

        Expression list =null;

        Expression table =null;



        	List decls = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:328:2: (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= FloatString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI |type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI |type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI |type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI |type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= FLOATLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI |type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI )
            int alt25=14;
            switch ( input.LA(1) ) {
            case IntString:
                {
                alt25=1;
                }
                break;
            case DoubleString:
                {
                alt25=2;
                }
                break;
            case FloatString:
                {
                alt25=3;
                }
                break;
            case StringString:
                {
                alt25=4;
                }
                break;
            case BooleanString:
                {
                alt25=5;
                }
                break;
            case TypeString:
                {
                alt25=6;
                }
                break;
            case WORDLIST:
                {
                alt25=7;
                }
                break;
            case WORDTABLE:
                {
                alt25=8;
                }
                break;
            case BOOLEANLIST:
                {
                alt25=9;
                }
                break;
            case INTLIST:
                {
                alt25=10;
                }
                break;
            case DOUBLELIST:
                {
                alt25=11;
                }
                break;
            case FLOATLIST:
                {
                alt25=12;
                }
                break;
            case STRINGLIST:
                {
                alt25=13;
                }
                break;
            case TYPELIST:
                {
                alt25=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmts;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }

            switch (alt25) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:329:2: type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI
                    {
                    type=(Token)match(input,IntString,FOLLOW_IntString_in_variableDeclaration410); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration416); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText()); decls.add(StatementFactory.createIntVariable(id, type));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:330:3: ( COMMA id= Identifier )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==COMMA) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:330:4: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration423); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration428); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText()); decls.add(StatementFactory.createIntVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:331:6: ( ASSIGN_EQUAL init= numberExpression )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==ASSIGN_EQUAL) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:331:7: ASSIGN_EQUAL init= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration438); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration444);
                            init=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration449); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
                    		 }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:336:2: type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI
                    {
                    type=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_variableDeclaration463); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration469); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createDoubleVariable(id, type));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:337:4: ( COMMA id= Identifier )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==COMMA) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:337:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration477); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration483); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createDoubleVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:338:7: ( ASSIGN_EQUAL init= numberExpression )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==ASSIGN_EQUAL) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:338:8: ASSIGN_EQUAL init= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration494); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration500);
                            init=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration505); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
                    		 }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:343:2: type= FloatString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI
                    {
                    type=(Token)match(input,FloatString,FOLLOW_FloatString_in_variableDeclaration519); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration525); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createFloatVariable(id, type));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:344:4: ( COMMA id= Identifier )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==COMMA) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:344:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration533); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration539); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createFloatVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:345:7: ( ASSIGN_EQUAL init= numberExpression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==ASSIGN_EQUAL) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:345:8: ASSIGN_EQUAL init= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration550); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration556);
                            init=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration561); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
                    		 }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:350:2: type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration575); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration581); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:351:4: ( COMMA id= Identifier )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==COMMA) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:351:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration589); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration595); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:352:7: ( ASSIGN_EQUAL init= stringExpression )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==ASSIGN_EQUAL) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:352:8: ASSIGN_EQUAL init= stringExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration606); if (state.failed) return stmts;

                            pushFollow(FOLLOW_stringExpression_in_variableDeclaration612);
                            init=stringExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration617); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
                    		 }

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:357:2: type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration631); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration637); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:358:4: ( COMMA id= Identifier )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMA) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:358:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration645); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration651); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:359:7: ( ASSIGN_EQUAL init= booleanExpression )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==ASSIGN_EQUAL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:359:8: ASSIGN_EQUAL init= booleanExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662); if (state.failed) return stmts;

                            pushFollow(FOLLOW_booleanExpression_in_variableDeclaration668);
                            init=booleanExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration673); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
                    		 }

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:364:2: type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration687); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration693); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:365:4: ( COMMA id= Identifier )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:365:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration701); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration707); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:366:7: ( ASSIGN_EQUAL init= annotationType )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:366:8: ASSIGN_EQUAL init= annotationType
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration718); if (state.failed) return stmts;

                            pushFollow(FOLLOW_annotationType_in_variableDeclaration724);
                            init=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration729); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
                    		 }

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:371:9: type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration757); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration763); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:371:41: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:371:42: ASSIGN_EQUAL list= wordListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration766); if (state.failed) return stmts;

                            pushFollow(FOLLOW_wordListExpression_in_variableDeclaration772);
                            list=wordListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration776); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createListVariable(id,type,list));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:378:9: type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration810); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration816); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:378:42: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:378:43: ASSIGN_EQUAL table= wordTableExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration819); if (state.failed) return stmts;

                            pushFollow(FOLLOW_wordTableExpression_in_variableDeclaration825);
                            table=wordTableExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration830); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createTableVariable(id,type,table));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, table));
                            }

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:385:9: type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration864); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration870); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:385:44: ( ASSIGN_EQUAL list= booleanListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:385:45: ASSIGN_EQUAL list= booleanListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration873); if (state.failed) return stmts;

                            pushFollow(FOLLOW_booleanListExpression_in_variableDeclaration879);
                            list=booleanListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration884); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_BL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:392:9: type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration918); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration924); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:392:40: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:392:41: ASSIGN_EQUAL list= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration927); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration933);
                            list=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration938); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_NL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:399:9: type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration973); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration979); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:399:43: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==ASSIGN_EQUAL) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:399:44: ASSIGN_EQUAL list= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration982); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration988);
                            list=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration993); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_NL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:406:9: type= FLOATLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,FLOATLIST,FOLLOW_FLOATLIST_in_variableDeclaration1029); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration1035); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:406:42: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==ASSIGN_EQUAL) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:406:43: ASSIGN_EQUAL list= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1038); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration1044);
                            list=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration1049); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_NL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:413:9: type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration1091); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration1097); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:413:43: ( ASSIGN_EQUAL list= stringListExpression )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==ASSIGN_EQUAL) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:413:44: ASSIGN_EQUAL list= stringListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1100); if (state.failed) return stmts;

                            pushFollow(FOLLOW_stringListExpression_in_variableDeclaration1106);
                            list=stringListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration1111); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_SL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:420:9: type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration1153); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration1159); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:420:41: ( ASSIGN_EQUAL list= typeListExpression )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==ASSIGN_EQUAL) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:420:42: ASSIGN_EQUAL list= typeListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1162); if (state.failed) return stmts;

                            pushFollow(FOLLOW_typeListExpression_in_variableDeclaration1168);
                            list=typeListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration1173); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_TL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "variableDeclaration"



    // $ANTLR start "declaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:449:1: declaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
    public final List<Statement> declaration() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        Token declareToken=null;
        Token id=null;
        Token end=null;
        Token obj2=null;
        Token obj3=null;
        Token obj6=null;
        Token obj4=null;
        Token obj5=null;
        Token fname=null;
        Expression lazyParent =null;

        Expression type =null;

        Expression obj1 =null;



        	Statement stmt = null;
        	List<Object> featureTypes = new ArrayList<Object>();
        	List<Token> featureNames = new ArrayList<Token>();
        	List<Declaration> declarations = new ArrayList<Declaration>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:456:2: ( (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:458:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:458:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:458:3: declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1241); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:458:35: (lazyParent= annotationType )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==Identifier) ) {
                        int LA26_1 = input.LA(2);

                        if ( (LA26_1==DOT||LA26_1==Identifier) ) {
                            alt26=1;
                        }
                    }
                    else if ( (LA26_0==BasicAnnotationType) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:458:35: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1247);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1257); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    if ( state.backtracking==0 ) {addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), lazyParent == null ? null : lazyParent.toString());
                    			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:462:3: ( COMMA id= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:462:4: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1269); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1279); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    	    if ( state.backtracking==0 ) {addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(),  lazyParent == null ? null : lazyParent.toString()); 
                    	    			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    end=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration1298); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, lazyParent);
                    		 stmts.add(stmt);
                    		 }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:472:2: declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1311); if (state.failed) return stmts;

                    pushFollow(FOLLOW_annotationType_in_declaration1315);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1322); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:474:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:474:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1329); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:475:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString )
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
                        if (state.backtracking>0) {state.failed=true; return stmts;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;

                    }

                    switch (alt28) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:476:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1344);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1);}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:477:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1357); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2);}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:478:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1370); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3);}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:479:6: obj6= FloatString
                            {
                            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1382); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj6);}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:480:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1396); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4);}

                            }
                            break;
                        case 6 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:481:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1408); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5);}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1428); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {featureNames.add(fname);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:485:4: ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:486:4: COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1440); if (state.failed) return stmts;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:487:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString )
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
                    	        if (state.backtracking>0) {state.failed=true; return stmts;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 29, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt29) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:488:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1455);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:489:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1468); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:490:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1481); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:491:6: obj6= FloatString
                    	            {
                    	            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1493); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj6);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:492:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1507); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4);}

                    	            }
                    	            break;
                    	        case 6 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:493:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1519); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj5);}

                    	            }
                    	            break;

                    	    }


                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1538); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {featureNames.add(fname);}

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1546); if (state.failed) return stmts;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1549); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		List<TextMarkerFeatureDeclaration> features = new ArrayList<TextMarkerFeatureDeclaration>();
                    		int i = 0;
                    		for (Object eachTO : featureTypes) {
                    		   Token eachName = featureNames.get(i); 
                    		   features.add(StatementFactory.createFeatureDeclaration(eachTO, eachName));  
                    		   i++;
                    		}
                    		addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.toString(), featureTypes, featureNames);
                    		declarations.add( StatementFactory.createAnnotationType(id,declareToken, type, features));
                    		stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, type);
                    		stmts.add(stmt);
                    		}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmts;
    }
    // $ANTLR end "declaration"


    protected static class blockDeclaration_scope {
        TextMarkerBlock env;
    }
    protected Stack blockDeclaration_stack = new Stack();



    // $ANTLR start "blockDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:518:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : (declareToken= BlockString |declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY ;
    public final TextMarkerBlock blockDeclaration() throws RecognitionException {
        blockDeclaration_stack.push(new blockDeclaration_scope());
        TextMarkerBlock block =  null;


        Token declareToken=null;
        Token id=null;
        Token rc=null;
        TextMarkerRuleElement re1 =null;

        List<Statement> body =null;



        TextMarkerRuleElement re = null;
        level++;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:532:2: ( (declareToken= BlockString |declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:534:2: (declareToken= BlockString |declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:534:2: (declareToken= BlockString |declareToken= AutomataBlockString )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==BlockString) ) {
                alt32=1;
            }
            else if ( (LA32_0==AutomataBlockString) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return block;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:534:3: declareToken= BlockString
                    {
                    declareToken=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1610); if (state.failed) return block;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:534:32: declareToken= AutomataBlockString
                    {
                    declareToken=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_blockDeclaration1618); if (state.failed) return block;

                    }
                    break;

            }


            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1622); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1629); if (state.failed) return block;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            if ( state.backtracking==0 ) {
            		block = ScriptFactory.createScriptBlock(id, declareToken, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);
            		((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	}

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1637); if (state.failed) return block;

            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1644);
            re1=ruleElementWithCA();

            state._fsp--;
            if (state.failed) return block;

            if ( state.backtracking==0 ) {re = re1;}

            if ( state.backtracking==0 ) {ScriptFactory.finalizeScriptBlock(block, rc, re, body);}

            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1652); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_blockDeclaration1658);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1664); if (state.failed) return block;

            if ( state.backtracking==0 ) {ScriptFactory.finalizeScriptBlock(block, rc, re, body);}

            }

            if ( state.backtracking==0 ) {
            level--;
            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
            blockDeclaration_stack.pop();
        }
        return block;
    }
    // $ANTLR end "blockDeclaration"



    // $ANTLR start "ruleElementWithCA"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:549:1: ruleElementWithCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Token end=null;
        Expression idRef =null;

        List<Expression> quantifier =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:550:5: (idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:551:5: idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1694);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:551:37: (quantifier= quantifierPart )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LBRACK||LA33_0==PLUS||LA33_0==QUESTION||LA33_0==STAR) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:551:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1700);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);}

            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1713); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:552:18: (c= conditions )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==AFTER||LA34_0==AND||LA34_0==BEFORE||(LA34_0 >= CONTAINS && LA34_0 <= CONTEXTCOUNT)||LA34_0==COUNT||LA34_0==CURRENTCOUNT||LA34_0==ENDSWITH||LA34_0==FEATURE||(LA34_0 >= IF && LA34_0 <= INLIST)||(LA34_0 >= IS && LA34_0 <= Identifier)||LA34_0==LAST||(LA34_0 >= MINUS && LA34_0 <= NOT)||LA34_0==OR||(LA34_0 >= PARSE && LA34_0 <= PARTOFNEQ)||LA34_0==POSITION||LA34_0==REGEXP||LA34_0==SCORE||LA34_0==SIZE||LA34_0==STARTSWITH||LA34_0==TOTALCOUNT||LA34_0==VOTE) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:552:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1719);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:552:32: ( THEN a= actions )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==THEN) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:552:33: THEN a= actions
                    {
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1723); if (state.failed) return re;

                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1729);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1737); if (state.failed) return re;

            if ( state.backtracking==0 ) {re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementWithCA"



    // $ANTLR start "ruleElementWithoutCA"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:557:1: ruleElementWithoutCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? ;
    public final TextMarkerRuleElement ruleElementWithoutCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Expression idRef =null;

        List<Expression> quantifier =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:558:5: (idRef= typeExpression (quantifier= quantifierPart )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:559:5: idRef= typeExpression (quantifier= quantifierPart )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithoutCA1777);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:559:37: (quantifier= quantifierPart )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==LBRACK||LA36_0==PLUS||LA36_0==QUESTION||LA36_0==STAR) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:559:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithoutCA1783);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {re = ScriptFactory.createRuleElement(idRef,quantifier,null,null, null);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementWithoutCA"



    // $ANTLR start "simpleStatement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:564:1: simpleStatement returns [TextMarkerRule stmt = null] : elements= ruleElements SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;


        List<Expression> elements =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:565:2: (elements= ruleElements SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:566:2: elements= ruleElements SEMI
            {
            pushFollow(FOLLOW_ruleElements_in_simpleStatement1825);
            elements=ruleElements();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {stmt = ScriptFactory.createRule(elements);}

            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1834); if (state.failed) return stmt;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "simpleStatement"



    // $ANTLR start "ruleElements"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:571:1: ruleElements returns [List<Expression> elements = new ArrayList<Expression>()] : re= ruleElement (re= ruleElement )* ;
    public final List<Expression> ruleElements() throws RecognitionException {
        List<Expression> elements =  new ArrayList<Expression>();


        Expression re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:572:2: (re= ruleElement (re= ruleElement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:573:2: re= ruleElement (re= ruleElement )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1855);
            re=ruleElement();

            state._fsp--;
            if (state.failed) return elements;

            if ( state.backtracking==0 ) {if(re!=null) elements.add(re);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:573:52: (re= ruleElement )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==BasicAnnotationType||LA37_0==Identifier||LA37_0==LPAREN||LA37_0==StringLiteral) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:573:53: re= ruleElement
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1864);
            	    re=ruleElement();

            	    state._fsp--;
            	    if (state.failed) return elements;

            	    if ( state.backtracking==0 ) {if(re!=null) elements.add(re);}

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return elements;
    }
    // $ANTLR end "ruleElements"



    // $ANTLR start "blockRuleElement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:576:1: blockRuleElement returns [TextMarkerRuleElement rElement = null] : re= ruleElementType ;
    public final TextMarkerRuleElement blockRuleElement() throws RecognitionException {
        TextMarkerRuleElement rElement =  null;


        TextMarkerRuleElement re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:577:2: (re= ruleElementType )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:578:2: re= ruleElementType
            {
            pushFollow(FOLLOW_ruleElementType_in_blockRuleElement1891);
            re=ruleElementType();

            state._fsp--;
            if (state.failed) return rElement;

            if ( state.backtracking==0 ) {rElement = re;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return rElement;
    }
    // $ANTLR end "blockRuleElement"



    // $ANTLR start "ruleElement"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:581:1: ruleElement returns [Expression re = null] : (re1= ruleElementType |re2= ruleElementLiteral |re3= ruleElementComposed );
    public final Expression ruleElement() throws RecognitionException {
        Expression re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        ComposedRuleElement re3 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:582:2: (re1= ruleElementType |re2= ruleElementLiteral |re3= ruleElementComposed )
            int alt38=3;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA38_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "STRING"))))) ) {
                    alt38=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                    alt38=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt38=1;
                }
                break;
            case StringLiteral:
                {
                alt38=2;
                }
                break;
            case LPAREN:
                {
                alt38=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }

            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:583:2: re1= ruleElementType
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1915);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:4: re2= ruleElementLiteral
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1926);
                    re2=ruleElementLiteral();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:585:4: re3= ruleElementComposed
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1937);
                    re3=ruleElementComposed();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re3;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElement"



    // $ANTLR start "ruleElementComposed"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:588:1: ruleElementComposed returns [ComposedRuleElement re = null] : LPAREN ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final ComposedRuleElement ruleElementComposed() throws RecognitionException {
        ComposedRuleElement re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        TextMarkerRuleElement re3 =null;

        List<Expression> res =null;

        List<Expression> q =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;



        	boolean disjunctive = false;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:592:2: ( LPAREN ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:593:2: LPAREN ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed1961); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:595:2: ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==Identifier) ) {
                int LA40_1 = input.LA(2);

                if ( (synpred1_TextMarkerParser()) ) {
                    alt40=1;
                }
                else if ( (synpred2_TextMarkerParser()) ) {
                    alt40=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA40_0==BasicAnnotationType) ) {
                int LA40_2 = input.LA(2);

                if ( (synpred1_TextMarkerParser()) ) {
                    alt40=1;
                }
                else if ( (synpred2_TextMarkerParser()) ) {
                    alt40=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA40_0==StringLiteral) && (synpred2_TextMarkerParser())) {
                alt40=2;
            }
            else if ( (LA40_0==LPAREN) && (synpred2_TextMarkerParser())) {
                alt40=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }
            switch (alt40) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:595:3: ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )*
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed1979);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {disjunctive = true; res = new ArrayList<Expression>(); res.add(re1);}

                    match(input,VBAR,FOLLOW_VBAR_in_ruleElementComposed1985); if (state.failed) return re;

                    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed1991);
                    re2=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {res.add(re2);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:597:2: ( VBAR re3= ruleElementType )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==VBAR) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:597:3: VBAR re3= ruleElementType
                    	    {
                    	    match(input,VBAR,FOLLOW_VBAR_in_ruleElementComposed1997); if (state.failed) return re;

                    	    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed2003);
                    	    re3=ruleElementType();

                    	    state._fsp--;
                    	    if (state.failed) return re;

                    	    if ( state.backtracking==0 ) {res.add(re3);}

                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:598:4: ( ruleElements )=>res= ruleElements
                    {
                    pushFollow(FOLLOW_ruleElements_in_ruleElementComposed2020);
                    res=ruleElements();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed2026); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:11: (q= quantifierPart )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LBRACK||LA41_0==PLUS||LA41_0==QUESTION||LA41_0==STAR) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:11: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed2032);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:29: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==LCURLY) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:30: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed2036); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:39: (c= conditions )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==AFTER||LA42_0==AND||LA42_0==BEFORE||(LA42_0 >= CONTAINS && LA42_0 <= CONTEXTCOUNT)||LA42_0==COUNT||LA42_0==CURRENTCOUNT||LA42_0==ENDSWITH||LA42_0==FEATURE||(LA42_0 >= IF && LA42_0 <= INLIST)||(LA42_0 >= IS && LA42_0 <= Identifier)||LA42_0==LAST||(LA42_0 >= MINUS && LA42_0 <= NOT)||LA42_0==OR||(LA42_0 >= PARSE && LA42_0 <= PARTOFNEQ)||LA42_0==POSITION||LA42_0==REGEXP||LA42_0==SCORE||LA42_0==SIZE||LA42_0==STARTSWITH||LA42_0==TOTALCOUNT||LA42_0==VOTE) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:39: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed2042);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:53: ( THEN a= actions )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==THEN) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:54: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementComposed2046); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementComposed2052);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementComposed2056); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {re = ScriptFactory.createComposedRuleElement(res, q, c, a, disjunctive,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementComposed"



    // $ANTLR start "ruleElementType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:604:1: ruleElementType returns [TextMarkerRuleElement re = null] : ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Token end=null;
        Expression idRef =null;

        List<Expression> quantifier =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:605:5: ( ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:606:5: ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2090);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:606:55: (quantifier= quantifierPart )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LBRACK||LA45_0==PLUS||LA45_0==QUESTION||LA45_0==STAR) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:606:55: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2096);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:607:9: ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LCURLY) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:607:10: LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2109); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:607:19: (c= conditions )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==AFTER||LA46_0==AND||LA46_0==BEFORE||(LA46_0 >= CONTAINS && LA46_0 <= CONTEXTCOUNT)||LA46_0==COUNT||LA46_0==CURRENTCOUNT||LA46_0==ENDSWITH||LA46_0==FEATURE||(LA46_0 >= IF && LA46_0 <= INLIST)||(LA46_0 >= IS && LA46_0 <= Identifier)||LA46_0==LAST||(LA46_0 >= MINUS && LA46_0 <= NOT)||LA46_0==OR||(LA46_0 >= PARSE && LA46_0 <= PARTOFNEQ)||LA46_0==POSITION||LA46_0==REGEXP||LA46_0==SCORE||LA46_0==SIZE||LA46_0==STARTSWITH||LA46_0==TOTALCOUNT||LA46_0==VOTE) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:607:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2115);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:607:33: ( THEN a= actions )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==THEN) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:607:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2119); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementType2125);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2133); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
                    // TODO handle quantifierPart.
                    re = ScriptFactory.createRuleElement(idRef,quantifier,c,a,end);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementType"



    // $ANTLR start "ruleElementLiteral"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:614:1: ruleElementLiteral returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Token end=null;
        Expression idRef =null;

        List<Expression> quantifier =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:615:5: ( ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:616:5: ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2186);
            idRef=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:616:71: (quantifier= quantifierPart )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LBRACK||LA49_0==PLUS||LA49_0==QUESTION||LA49_0==STAR) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:616:71: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2192);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:617:9: ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LCURLY) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:617:10: LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2205); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:617:19: (c= conditions )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==AFTER||LA50_0==AND||LA50_0==BEFORE||(LA50_0 >= CONTAINS && LA50_0 <= CONTEXTCOUNT)||LA50_0==COUNT||LA50_0==CURRENTCOUNT||LA50_0==ENDSWITH||LA50_0==FEATURE||(LA50_0 >= IF && LA50_0 <= INLIST)||(LA50_0 >= IS && LA50_0 <= Identifier)||LA50_0==LAST||(LA50_0 >= MINUS && LA50_0 <= NOT)||LA50_0==OR||(LA50_0 >= PARSE && LA50_0 <= PARTOFNEQ)||LA50_0==POSITION||LA50_0==REGEXP||LA50_0==SCORE||LA50_0==SIZE||LA50_0==STARTSWITH||LA50_0==TOTALCOUNT||LA50_0==VOTE) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:617:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2211);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:617:33: ( THEN a= actions )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==THEN) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:617:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2215); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2221);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2229); if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
                    // TODO handle quantifierPart.
                    re = ScriptFactory.createRuleElement(idRef,quantifier,c,a,end);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return re;
    }
    // $ANTLR end "ruleElementLiteral"



    // $ANTLR start "conditions"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:624:1: conditions returns [List<TextMarkerCondition> conds = new ArrayList<TextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<TextMarkerCondition> conditions() throws RecognitionException {
        List<TextMarkerCondition> conds =  new ArrayList<TextMarkerCondition>();


        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:625:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:626:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2283);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;

            if ( state.backtracking==0 ) {conds.add(c);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:626:35: ( COMMA c= condition )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==COMMA) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:626:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2288); if (state.failed) return conds;

            	    pushFollow(FOLLOW_condition_in_conditions2294);
            	    c=condition();

            	    state._fsp--;
            	    if (state.failed) return conds;

            	    if ( state.backtracking==0 ) {conds.add(c);}

            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return conds;
    }
    // $ANTLR end "conditions"



    // $ANTLR start "actions"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:630:1: actions returns [List<TextMarkerAction> actions = new ArrayList<TextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<TextMarkerAction> actions() throws RecognitionException {
        List<TextMarkerAction> actions =  new ArrayList<TextMarkerAction>();


        TextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:631:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:632:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2331);
            a=action();

            state._fsp--;
            if (state.failed) return actions;

            if ( state.backtracking==0 ) {actions.add(a);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:632:34: ( COMMA a= action )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==COMMA) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:632:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2336); if (state.failed) return actions;

            	    pushFollow(FOLLOW_action_in_actions2342);
            	    a=action();

            	    state._fsp--;
            	    if (state.failed) return actions;

            	    if ( state.backtracking==0 ) {actions.add(a);}

            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return actions;
    }
    // $ANTLR end "actions"



    // $ANTLR start "listExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:636:1: listExpression returns [Expression expr = null] : ( ( booleanListExpression )=>e= booleanListExpression | ( intListExpression )=>e= intListExpression | ( doubleListExpression )=>e= doubleListExpression | ( floatListExpression )=>e= floatListExpression | ( stringListExpression )=>e= stringListExpression | ( typeListExpression )=>e= typeListExpression );
    public final Expression listExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:637:2: ( ( booleanListExpression )=>e= booleanListExpression | ( intListExpression )=>e= intListExpression | ( doubleListExpression )=>e= doubleListExpression | ( floatListExpression )=>e= floatListExpression | ( stringListExpression )=>e= stringListExpression | ( typeListExpression )=>e= typeListExpression )
            int alt55=6;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==LCURLY) ) {
                int LA55_1 = input.LA(2);

                if ( (synpred5_TextMarkerParser()) ) {
                    alt55=1;
                }
                else if ( (synpred6_TextMarkerParser()) ) {
                    alt55=2;
                }
                else if ( (synpred7_TextMarkerParser()) ) {
                    alt55=3;
                }
                else if ( (synpred8_TextMarkerParser()) ) {
                    alt55=4;
                }
                else if ( (synpred9_TextMarkerParser()) ) {
                    alt55=5;
                }
                else if ( (synpred10_TextMarkerParser()) ) {
                    alt55=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA55_0==Identifier) ) {
                int LA55_2 = input.LA(2);

                if ( (((synpred5_TextMarkerParser()&&synpred5_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt55=1;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "INTLIST"))&&(isVariableOfType(input.LT(1).getText(), "INTLIST")))&&synpred6_TextMarkerParser())) ) {
                    alt55=2;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))&&synpred7_TextMarkerParser())) ) {
                    alt55=3;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "FLOATLIST"))&&(isVariableOfType(input.LT(1).getText(), "FLOATLIST")))&&synpred8_TextMarkerParser())) ) {
                    alt55=4;
                }
                else if ( (((synpred9_TextMarkerParser()&&synpred9_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt55=5;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "TYPELIST"))&&(isVariableOfType(input.LT(1).getText(), "TYPELIST")))&&synpred10_TextMarkerParser())) ) {
                    alt55=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }
            switch (alt55) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:638:2: ( booleanListExpression )=>e= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2380);
                    e=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:4: ( intListExpression )=>e= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2396);
                    e=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:640:4: ( doubleListExpression )=>e= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2412);
                    e=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:641:4: ( floatListExpression )=>e= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_listExpression2428);
                    e=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:642:4: ( stringListExpression )=>e= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2444);
                    e=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:643:4: ( typeListExpression )=>e= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2460);
                    e=typeListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "listExpression"



    // $ANTLR start "booleanListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:646:1: booleanListExpression returns [Expression expr = null] : e= simpleBooleanListExpression ;
    public final Expression booleanListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:648:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:649:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2484);
            e=simpleBooleanListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanListExpression"



    // $ANTLR start "simpleBooleanListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:652:1: simpleBooleanListExpression returns [Expression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleBooleanListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:655:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==LCURLY) ) {
                alt58=1;
            }
            else if ( (LA58_0==Identifier) ) {
                alt58=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;

            }
            switch (alt58) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:656:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2505); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:656:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==FALSE||LA57_0==Identifier||LA57_0==TRUE) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:656:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2512);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:656:53: ( COMMA e= simpleBooleanExpression )*
                            loop56:
                            do {
                                int alt56=2;
                                int LA56_0 = input.LA(1);

                                if ( (LA56_0==COMMA) ) {
                                    alt56=1;
                                }


                                switch (alt56) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:656:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2517); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2523);
                            	    e=simpleBooleanExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop56;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2532); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_BL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:659:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEANLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2549); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_BL);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanListExpression"



    // $ANTLR start "intListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:665:1: intListExpression returns [Expression expr = null] : e= simpleIntListExpression ;
    public final Expression intListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:666:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:667:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2574);
            e=simpleIntListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "intListExpression"



    // $ANTLR start "simpleIntListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:670:1: simpleIntListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleIntListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:673:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==LCURLY) ) {
                alt61=1;
            }
            else if ( (LA61_0==Identifier) ) {
                alt61=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }
            switch (alt61) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:674:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2595); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:674:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==DecimalLiteral||LA60_0==FloatingPointLiteral||LA60_0==Identifier||LA60_0==LPAREN||LA60_0==MINUS) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:674:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2602);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:674:52: ( COMMA e= simpleNumberExpression )*
                            loop59:
                            do {
                                int alt59=2;
                                int LA59_0 = input.LA(1);

                                if ( (LA59_0==COMMA) ) {
                                    alt59=1;
                                }


                                switch (alt59) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:674:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2607); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2613);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop59;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2622); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:677:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType(input.LT(1).getText(), \"INTLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2639); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleIntListExpression"



    // $ANTLR start "numberListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:683:1: numberListExpression returns [Expression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression | (e1= floatListExpression )=>e1= floatListExpression |e2= intListExpression );
    public final Expression numberListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:684:2: ( (e1= doubleListExpression )=>e1= doubleListExpression | (e1= floatListExpression )=>e1= floatListExpression |e2= intListExpression )
            int alt62=3;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==LCURLY) ) {
                int LA62_1 = input.LA(2);

                if ( (synpred11_TextMarkerParser()) ) {
                    alt62=1;
                }
                else if ( (synpred12_TextMarkerParser()) ) {
                    alt62=2;
                }
                else if ( (true) ) {
                    alt62=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA62_0==Identifier) ) {
                int LA62_2 = input.LA(2);

                if ( ((((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))&&synpred11_TextMarkerParser())) ) {
                    alt62=1;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "FLOATLIST"))&&(isVariableOfType(input.LT(1).getText(), "FLOATLIST")))&&synpred12_TextMarkerParser())) ) {
                    alt62=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "INTLIST"))) ) {
                    alt62=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:685:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2673);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:687:2: (e1= floatListExpression )=>e1= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_numberListExpression2694);
                    e1=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:689:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2706);
                    e2=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberListExpression"



    // $ANTLR start "doubleListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:692:1: doubleListExpression returns [Expression expr = null] : e= simpleDoubleListExpression ;
    public final Expression doubleListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:693:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:694:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2729);
            e=simpleDoubleListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "doubleListExpression"



    // $ANTLR start "simpleDoubleListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:697:1: simpleDoubleListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleDoubleListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:700:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==LCURLY) ) {
                alt65=1;
            }
            else if ( (LA65_0==Identifier) ) {
                alt65=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;

            }
            switch (alt65) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:701:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2750); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:701:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==DecimalLiteral||LA64_0==FloatingPointLiteral||LA64_0==Identifier||LA64_0==LPAREN||LA64_0==MINUS) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:701:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2757);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:701:52: ( COMMA e= simpleNumberExpression )*
                            loop63:
                            do {
                                int alt63=2;
                                int LA63_0 = input.LA(1);

                                if ( (LA63_0==COMMA) ) {
                                    alt63=1;
                                }


                                switch (alt63) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:701:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2762); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2768);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop63;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2777); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:704:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType(input.LT(1).getText(), \"DOUBLELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2794); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleDoubleListExpression"



    // $ANTLR start "floatListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:709:1: floatListExpression returns [Expression expr = null] : e= simpleFloatListExpression ;
    public final Expression floatListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:710:2: (e= simpleFloatListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:711:2: e= simpleFloatListExpression
            {
            pushFollow(FOLLOW_simpleFloatListExpression_in_floatListExpression2818);
            e=simpleFloatListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "floatListExpression"



    // $ANTLR start "simpleFloatListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:714:1: simpleFloatListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleFloatListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:717:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==LCURLY) ) {
                alt68=1;
            }
            else if ( (LA68_0==Identifier) ) {
                alt68=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;

            }
            switch (alt68) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleFloatListExpression2839); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==DecimalLiteral||LA67_0==FloatingPointLiteral||LA67_0==Identifier||LA67_0==LPAREN||LA67_0==MINUS) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2846);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:52: ( COMMA e= simpleNumberExpression )*
                            loop66:
                            do {
                                int alt66=2;
                                int LA66_0 = input.LA(1);

                                if ( (LA66_0==COMMA) ) {
                                    alt66=1;
                                }


                                switch (alt66) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleFloatListExpression2851); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2857);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop66;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleFloatListExpression2866); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:721:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "FLOATLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleFloatListExpression", "isVariableOfType(input.LT(1).getText(), \"FLOATLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleFloatListExpression2883); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleFloatListExpression"



    // $ANTLR start "stringListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:727:1: stringListExpression returns [Expression expr = null] : e= simpleStringListExpression ;
    public final Expression stringListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:728:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:729:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2908);
            e=simpleStringListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "stringListExpression"



    // $ANTLR start "simpleStringListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:732:1: simpleStringListExpression returns [Expression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleStringListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:735:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==LCURLY) ) {
                alt71=1;
            }
            else if ( (LA71_0==Identifier) ) {
                alt71=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;

            }
            switch (alt71) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:736:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression2929); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:736:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt70=2;
                    int LA70_0 = input.LA(1);

                    if ( (LA70_0==Identifier||LA70_0==StringLiteral) ) {
                        alt70=1;
                    }
                    switch (alt70) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:736:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2936);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:736:52: ( COMMA e= simpleStringExpression )*
                            loop69:
                            do {
                                int alt69=2;
                                int LA69_0 = input.LA(1);

                                if ( (LA69_0==COMMA) ) {
                                    alt69=1;
                                }


                                switch (alt69) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:736:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression2941); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2947);
                            	    e=simpleStringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop69;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression2956); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_SL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:739:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType(input.LT(1).getText(), \"STRINGLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression2973); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_SL);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleStringListExpression"



    // $ANTLR start "typeListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:745:1: typeListExpression returns [Expression expr = null] : e= simpleTypeListExpression ;
    public final Expression typeListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:746:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:747:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression2998);
            e=simpleTypeListExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "typeListExpression"



    // $ANTLR start "simpleTypeListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:750:1: simpleTypeListExpression returns [Expression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleTypeListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:753:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==LCURLY) ) {
                alt74=1;
            }
            else if ( (LA74_0==Identifier) ) {
                alt74=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;

            }
            switch (alt74) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:754:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression3019); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:754:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==BasicAnnotationType||LA73_0==Identifier) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:754:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3026);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:754:50: ( COMMA e= simpleTypeExpression )*
                            loop72:
                            do {
                                int alt72=2;
                                int LA72_0 = input.LA(1);

                                if ( (LA72_0==COMMA) ) {
                                    alt72=1;
                                }


                                switch (alt72) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:754:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression3031); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3037);
                            	    e=simpleTypeExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop72;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression3046); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_TL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:757:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType(input.LT(1).getText(), \"TYPELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression3063); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_TL);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleTypeListExpression"



    // $ANTLR start "typeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:762:1: typeExpression returns [Expression expr = null] : (tf= typeFunction |st= simpleTypeExpression );
    public final Expression typeExpression() throws RecognitionException {
        Expression expr =  null;


        Expression tf =null;

        Expression st =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:763:2: (tf= typeFunction |st= simpleTypeExpression )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==Identifier) ) {
                int LA75_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION"))) ) {
                    alt75=1;
                }
                else if ( (true) ) {
                    alt75=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 75, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA75_0==BasicAnnotationType) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:764:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression3089);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:765:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression3100);
                    st=simpleTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTypeExpression(st);
                    	 }

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "typeExpression"



    // $ANTLR start "typeFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:771:1: typeFunction returns [Expression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final Expression typeFunction() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:772:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:773:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction3134);
            e=externalTypeFunction();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = e;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "typeFunction"



    // $ANTLR start "externalTypeFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:777:1: externalTypeFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalTypeFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:778:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:779:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalTypeFunction", "isVariableOfType(input.LT(1).getText(), \"TYPEFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction3159); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction3163); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction3170);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction3174); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalTypeFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalTypeFunction"



    // $ANTLR start "simpleTypeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:788:1: simpleTypeExpression returns [Expression type = null] : at= annotationType ;
    public final Expression simpleTypeExpression() throws RecognitionException {
        Expression type =  null;


        Expression at =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:789:2: (at= annotationType )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:790:2: at= annotationType
            {
            pushFollow(FOLLOW_annotationType_in_simpleTypeExpression3197);
            at=annotationType();

            state._fsp--;
            if (state.failed) return type;

            if ( state.backtracking==0 ) {type = at;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return type;
    }
    // $ANTLR end "simpleTypeExpression"



    // $ANTLR start "variable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:793:1: variable returns [Expression var = null] :{...}?v= Identifier ;
    public final Expression variable() throws RecognitionException {
        Expression var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:794:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:795:2: {...}?v= Identifier
            {
            if ( !((vars.contains(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "vars.contains(input.LT(1).getText())");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3221); if (state.failed) return var;

            if ( state.backtracking==0 ) {var=ExpressionFactory.createGenericVariableReference(v);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return var;
    }
    // $ANTLR end "variable"



    // $ANTLR start "listVariable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:799:1: listVariable returns [Expression var = null] :{...}?v= Identifier ;
    public final Expression listVariable() throws RecognitionException {
        Expression var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:800:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:801:2: {...}?v= Identifier
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")
            	||isVariableOfType(input.LT(1).getText(), "INTLIST")
            	||isVariableOfType(input.LT(1).getText(), "FLOATLIST")
            	||isVariableOfType(input.LT(1).getText(), "DOUBLELIST")
            	||isVariableOfType(input.LT(1).getText(), "STRINGLIST")
            	||isVariableOfType(input.LT(1).getText(), "TYPELIST")
            	)) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "listVariable", "isVariableOfType(input.LT(1).getText(), \"BOOLEANLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"INTLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"FLOATLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"DOUBLELIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"STRINGLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"TYPELIST\")\r\n\t");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3248); if (state.failed) return var;

            if ( state.backtracking==0 ) {var=ExpressionFactory.createGenericVariableReference(v);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return var;
    }
    // $ANTLR end "listVariable"



    // $ANTLR start "quantifierPart"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:812:1: quantifierPart returns [List<Expression> exprs = new ArrayList<Expression>()] : (s= STAR (q= QUESTION )? |p= PLUS (q= QUESTION )? |q1= QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? ) );
    public final List<Expression> quantifierPart() throws RecognitionException {
        List<Expression> exprs =  new ArrayList<Expression>();


        Token s=null;
        Token q=null;
        Token p=null;
        Token q1=null;
        Expression min =null;

        Expression max =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:813:2: (s= STAR (q= QUESTION )? |p= PLUS (q= QUESTION )? |q1= QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? ) )
            int alt82=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt82=1;
                }
                break;
            case PLUS:
                {
                alt82=2;
                }
                break;
            case QUESTION:
                {
                alt82=3;
                }
                break;
            case LBRACK:
                {
                alt82=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return exprs;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;

            }

            switch (alt82) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:814:3: s= STAR (q= QUESTION )?
                    {
                    s=(Token)match(input,STAR,FOLLOW_STAR_in_quantifierPart3275); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:814:14: (q= QUESTION )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==QUESTION) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:814:14: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3281); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(s,q));}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:815:4: p= PLUS (q= QUESTION )?
                    {
                    p=(Token)match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3293); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:815:15: (q= QUESTION )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==QUESTION) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:815:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3299); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(p,q));}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:816:4: q1= QUESTION (q= QUESTION )?
                    {
                    q1=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3311); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:816:20: (q= QUESTION )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==QUESTION) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:816:20: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3317); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q1,q));}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:4: ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:4: ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:5: LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3326); if (state.failed) return exprs;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3332);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:35: ( COMMA (max= numberExpression )? )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==COMMA) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:36: COMMA (max= numberExpression )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3335); if (state.failed) return exprs;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:42: (max= numberExpression )?
                            int alt79=2;
                            int LA79_0 = input.LA(1);

                            if ( (LA79_0==COS||LA79_0==DecimalLiteral||LA79_0==EXP||LA79_0==FloatingPointLiteral||LA79_0==Identifier||(LA79_0 >= LOGN && LA79_0 <= LPAREN)||LA79_0==MINUS||LA79_0==SIN||LA79_0==TAN) ) {
                                alt79=1;
                            }
                            switch (alt79) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:43: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3342);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return exprs;

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3348); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:79: (q= QUESTION )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==QUESTION) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:79: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3354); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {if(min!=null) {exprs.add(min);}
                    		  if(max!=null) {exprs.add(max);}
                    		 }

                    }


                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return exprs;
    }
    // $ANTLR end "quantifierPart"



    // $ANTLR start "condition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:825:1: condition returns [TextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition ) ;
    public final TextMarkerCondition condition() throws RecognitionException {
        TextMarkerCondition result =  null;


        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:826:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:827:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:827:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition )
            int alt83=28;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==AND) ) {
                alt83=1;
            }
            else if ( (LA83_0==CONTAINS) ) {
                alt83=2;
            }
            else if ( (LA83_0==CONTEXTCOUNT) ) {
                alt83=3;
            }
            else if ( (LA83_0==COUNT) ) {
                alt83=4;
            }
            else if ( (LA83_0==CURRENTCOUNT) ) {
                alt83=5;
            }
            else if ( (LA83_0==INLIST) ) {
                alt83=6;
            }
            else if ( (LA83_0==LAST) ) {
                alt83=7;
            }
            else if ( (LA83_0==MOFN) ) {
                alt83=8;
            }
            else if ( (LA83_0==NEAR) ) {
                alt83=9;
            }
            else if ( (LA83_0==MINUS||LA83_0==NOT) ) {
                alt83=10;
            }
            else if ( (LA83_0==OR) ) {
                alt83=11;
            }
            else if ( (LA83_0==PARTOF) ) {
                alt83=12;
            }
            else if ( (LA83_0==POSITION) ) {
                alt83=13;
            }
            else if ( (LA83_0==REGEXP) ) {
                alt83=14;
            }
            else if ( (LA83_0==SCORE) ) {
                alt83=15;
            }
            else if ( (LA83_0==TOTALCOUNT) ) {
                alt83=16;
            }
            else if ( (LA83_0==VOTE) ) {
                alt83=17;
            }
            else if ( (LA83_0==IF) ) {
                alt83=18;
            }
            else if ( (LA83_0==FEATURE) ) {
                alt83=19;
            }
            else if ( (LA83_0==PARSE) ) {
                alt83=20;
            }
            else if ( (LA83_0==IS) ) {
                alt83=21;
            }
            else if ( (LA83_0==BEFORE) ) {
                alt83=22;
            }
            else if ( (LA83_0==AFTER) ) {
                alt83=23;
            }
            else if ( (LA83_0==STARTSWITH) ) {
                alt83=24;
            }
            else if ( (LA83_0==ENDSWITH) ) {
                alt83=25;
            }
            else if ( (LA83_0==PARTOFNEQ) ) {
                alt83=26;
            }
            else if ( (LA83_0==SIZE) ) {
                alt83=27;
            }
            else if ( (LA83_0==Identifier) && (synpred14_TextMarkerParser())) {
                alt83=28;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;

            }
            switch (alt83) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:828:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3392);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:829:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3401);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:830:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3410);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:831:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3419);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:832:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3428);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:833:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3437);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:834:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3446);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:835:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3455);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:836:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3464);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:837:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3473);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:838:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3482);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:839:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3491);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:840:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3500);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:841:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3509);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:842:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3518);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:843:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3527);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:844:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3536);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:845:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3545);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:846:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3554);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:847:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3563);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:848:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3572);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:849:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3581);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:850:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3590);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:851:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3599);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:852:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3608);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:853:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3617);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:854:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3626);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:855:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3644);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {result = c;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "condition"



    // $ANTLR start "externalCondition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:872:1: externalCondition returns [TextMarkerCondition condition = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerCondition externalCondition() throws RecognitionException {
        TextMarkerCondition condition =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:873:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:874:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "CONDITION"))) ) {
                if (state.backtracking>0) {state.failed=true; return condition;}
                throw new FailedPredicateException(input, "externalCondition", "isVariableOfType(input.LT(1).getText(), \"CONDITION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3688); if (state.failed) return condition;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3691); if (state.failed) return condition;

            if ( state.backtracking==0 ) {condition = external.createExternalCondition(id, args);}

            pushFollow(FOLLOW_varArgumentList_in_externalCondition3701);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;

            if ( state.backtracking==0 ) {condition = external.createExternalCondition(id, args);}

            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3708); if (state.failed) return condition;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return condition;
    }
    // $ANTLR end "externalCondition"



    // $ANTLR start "conditionAnd"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:881:1: conditionAnd returns [TextMarkerCondition cond = null] : name= AND LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionAnd() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:882:5: (name= AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:883:5: name= AND LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,AND,FOLLOW_AND_in_conditionAnd3736); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3738); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionAnd3744);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, conds);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3758); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionAnd"



    // $ANTLR start "conditionContains"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:888:1: conditionContains returns [TextMarkerCondition cond = null] options {backtrack=true; } : name= CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionContains() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression list =null;

        Expression a =null;

        Expression min =null;

        Expression max =null;

        Expression percent =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:892:5: (name= CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:5: name= CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3804); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3806); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:29: (type= typeExpression |list= listExpression COMMA a= argument )
            int alt84=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA84_1 = input.LA(2);

                if ( (!((((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))))) ) {
                    alt84=1;
                }
                else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt84=1;
                }
                break;
            case LCURLY:
                {
                alt84=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }

            switch (alt84) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3813);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:54: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3821);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3823); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionContains3829);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:894:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==COMMA) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:894:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3838); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3844);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3846); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3852);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:894:64: ( COMMA percent= booleanExpression )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==COMMA) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:894:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3855); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3861);
                            percent=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {if(type != null) {cond = ConditionFactory.createCondition(name,type, min, max, percent);}
                else {cond = ConditionFactory.createCondition(name,list,a, min, max, percent);};}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3878); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionContains"



    // $ANTLR start "conditionContextCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:900:1: conditionContextCount returns [TextMarkerCondition cond = null] : name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionContextCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression typeExpr =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:901:5: (name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:902:5: name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount3914); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount3916); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount3922);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:904:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:904:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3936); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3942);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3944); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3950);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:906:5: ( COMMA var= numberVariable )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COMMA) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:906:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3965); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount3971);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount3986); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionContextCount"



    // $ANTLR start "conditionCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:911:1: conditionCount returns [TextMarkerCondition cond = null] options {backtrack=true; } : (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN |name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final TextMarkerCondition conditionCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression a =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:915:6: (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN |name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==COUNT) ) {
                int LA93_1 = input.LA(2);

                if ( (synpred15_TextMarkerParser()) ) {
                    alt93=1;
                }
                else if ( (true) ) {
                    alt93=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 93, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;

            }
            switch (alt93) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:916:6: name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount4037); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4039); if (state.failed) return cond;

                    pushFollow(FOLLOW_listExpression_in_conditionCount4045);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount4060); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionCount4066);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:920:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:920:7: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4082); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4088);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4090); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4096);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:922:6: ( COMMA var= numberVariable )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==COMMA) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:922:7: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4114); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4120);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4136); if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:926:5: name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount4152); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4154); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount4160);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:928:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:928:6: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4174); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4180);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4182); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4188);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:930:5: ( COMMA var= numberVariable )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);

                    if ( (LA92_0==COMMA) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:930:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4203); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4209);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4226); if (state.failed) return cond;

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionCount"



    // $ANTLR start "conditionCurrentCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:935:1: conditionCurrentCount returns [TextMarkerCondition cond = null] : name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionCurrentCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:936:5: (name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:937:5: name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4266); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4268); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4274);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:939:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==COMMA) ) {
                int LA94_1 = input.LA(2);

                if ( (LA94_1==COS||LA94_1==DecimalLiteral||LA94_1==EXP||LA94_1==FloatingPointLiteral||(LA94_1 >= LOGN && LA94_1 <= LPAREN)||LA94_1==MINUS||LA94_1==SIN||LA94_1==TAN) ) {
                    alt94=1;
                }
                else if ( (LA94_1==Identifier) ) {
                    int LA94_4 = input.LA(3);

                    if ( (LA94_4==COMMA||LA94_4==LPAREN||LA94_4==MINUS||(LA94_4 >= PERCENT && LA94_4 <= PLUS)||(LA94_4 >= SLASH && LA94_4 <= STAR)) ) {
                        alt94=1;
                    }
                }
            }
            switch (alt94) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:939:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4288); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4294);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4296); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4302);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:941:5: ( COMMA var= numberVariable )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==COMMA) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:941:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4318); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4324);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4339); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionCurrentCount"



    // $ANTLR start "conditionTotalCount"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:946:1: conditionTotalCount returns [TextMarkerCondition cond = null] : name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionTotalCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:947:5: (name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:948:5: name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4378); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4380); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4386);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:950:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:950:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4400); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4406);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4408); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4414);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:952:5: ( COMMA var= numberVariable )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:952:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4429); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4435);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4450); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionTotalCount"



    // $ANTLR start "conditionInList"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:956:1: conditionInList returns [TextMarkerCondition cond = null] options {backtrack=true; } : name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionInList() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression list2 =null;

        Expression list1 =null;

        Expression dist =null;

        Expression rel =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:960:5: (name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:5: name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,INLIST,FOLLOW_INLIST_in_conditionInList4491); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4493); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:26: ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==LCURLY) && (synpred16_TextMarkerParser())) {
                alt98=1;
            }
            else if ( (LA98_0==Identifier) ) {
                int LA98_2 = input.LA(2);

                if ( (((synpred16_TextMarkerParser()&&synpred16_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt98=1;
                }
                else if ( (true) ) {
                    alt98=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 98, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA98_0==RessourceLiteral) ) {
                alt98=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;

            }
            switch (alt98) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:27: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4508);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:90: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4516);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:962:5: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==COMMA) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:962:6: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4525); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4531);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:962:36: ( COMMA rel= booleanExpression )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==COMMA) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:962:37: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4534); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4540);
                            rel=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {if(list1 != null) {cond = ConditionFactory.createCondition(name, list1, dist, rel);}
                else {cond = ConditionFactory.createCondition(name, list2, dist, rel);};}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4558); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionInList"



    // $ANTLR start "conditionLast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:968:1: conditionLast returns [TextMarkerCondition cond = null] : name= LAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerCondition conditionLast() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:969:5: (name= LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:970:5: name= LAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,LAST,FOLLOW_LAST_in_conditionLast4602); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4604); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionLast4610);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4623); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionLast"



    // $ANTLR start "conditionMofN"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:975:1: conditionMofN returns [TextMarkerCondition cond = null] : name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final TextMarkerCondition conditionMofN() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression min =null;

        Expression max =null;

        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:976:5: (name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:977:5: name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            name=(Token)match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4659); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4661); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4667);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4669); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4675);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4677); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionMofN4683);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {List exprs = new ArrayList();
                exprs.add(min);
                exprs.add(max);
                exprs.addAll(conds);
                cond = ConditionFactory.createCondition(name, exprs);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4698); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionMofN"



    // $ANTLR start "conditionNear"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:986:1: conditionNear returns [TextMarkerCondition cond = null] : name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionNear() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression direction =null;

        Expression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:987:5: (name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:988:5: name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,NEAR,FOLLOW_NEAR_in_conditionNear4730); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4732); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionNear4738);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4740); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4746);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4748); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4754);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:989:5: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==COMMA) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:989:6: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4762); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4768);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:989:42: ( COMMA filtered= booleanExpression )?
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==COMMA) ) {
                        alt101=1;
                    }
                    switch (alt101) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:989:43: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4771); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4777);
                            filtered=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, direction, filtered);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4797); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionNear"



    // $ANTLR start "conditionNot"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:993:1: conditionNot returns [TextMarkerCondition cond = null] : ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) ;
    public final TextMarkerCondition conditionNot() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:994:5: ( ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==MINUS) ) {
                alt103=1;
            }
            else if ( (LA103_0==NOT) ) {
                alt103=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;

            }
            switch (alt103) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:6: (name= MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:6: (name= MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:7: name= MINUS c= condition
                    {
                    name=(Token)match(input,MINUS,FOLLOW_MINUS_in_conditionNot4830); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4836);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:38: (name= NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:38: (name= NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:995:39: name= NOT LPAREN c= condition RPAREN
                    {
                    name=(Token)match(input,NOT,FOLLOW_NOT_in_conditionNot4847); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4849); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4855);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4857); if (state.failed) return cond;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, c);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionNot"



    // $ANTLR start "conditionOr"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:998:1: conditionOr returns [TextMarkerCondition cond = null] : name= OR LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionOr() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:999:5: (name= OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1000:5: name= OR LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,OR,FOLLOW_OR_in_conditionOr4897); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4899); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionOr4905);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, conds);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4918); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionOr"



    // $ANTLR start "conditionPartOf"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1004:1: conditionPartOf returns [TextMarkerCondition cond = null] : name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOf() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1005:5: (name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:5: name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4946); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4948); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:26: (type= typeExpression |type= typeListExpression )
            int alt104=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA104_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt104=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt104=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt104=1;
                }
                break;
            case LCURLY:
                {
                alt104=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;

            }

            switch (alt104) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4955);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4961);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4978); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionPartOf"



    // $ANTLR start "conditionPartOfNeq"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:1: conditionPartOfNeq returns [TextMarkerCondition cond = null] : name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1012:5: (name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:5: name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq5011); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq5013); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:29: (type= typeExpression |type= typeListExpression )
            int alt105=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA105_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt105=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt105=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt105=1;
                }
                break;
            case LCURLY:
                {
                alt105=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;

            }

            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq5020);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:52: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq5026);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq5043); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionPartOfNeq"



    // $ANTLR start "conditionPosition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1019:1: conditionPosition returns [TextMarkerCondition cond = null] : name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final TextMarkerCondition conditionPosition() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1020:5: (name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1021:5: name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            name=(Token)match(input,POSITION,FOLLOW_POSITION_in_conditionPosition5080); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition5082); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionPosition5088);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition5090); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionPosition5096);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, pos);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition5109); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionPosition"



    // $ANTLR start "conditionRegExp"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1025:1: conditionRegExp returns [TextMarkerCondition cond = null] : name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final TextMarkerCondition conditionRegExp() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression pattern =null;

        Expression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1026:5: (name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1027:5: name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            name=(Token)match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp5137); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp5139); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp5145);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1027:53: ( COMMA caseSensitive= booleanExpression )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==COMMA) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1027:54: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp5148); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp5154);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, pattern, caseSensitive);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp5172); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionRegExp"



    // $ANTLR start "conditionScore"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1032:1: conditionScore returns [TextMarkerCondition cond = null] : name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final TextMarkerCondition conditionScore() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1033:5: (name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1034:5: name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            name=(Token)match(input,SCORE,FOLLOW_SCORE_in_conditionScore5206); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore5208); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore5214);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1034:48: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==COMMA) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1034:49: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore5217); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore5223);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1035:5: ( COMMA var= numberVariable )?
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==COMMA) ) {
                        alt107=1;
                    }
                    switch (alt107) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1035:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore5232); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionScore5238);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore5255); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionScore"



    // $ANTLR start "conditionVote"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1040:1: conditionVote returns [TextMarkerCondition cond = null] : name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final TextMarkerCondition conditionVote() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type1 =null;

        Expression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1041:5: (name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1042:5: name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            name=(Token)match(input,VOTE,FOLLOW_VOTE_in_conditionVote5287); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote5289); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5295);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionVote5297); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5303);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type1, type2);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote5316); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionVote"



    // $ANTLR start "conditionIf"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1046:1: conditionIf returns [TextMarkerCondition cond = null] : name= IF LPAREN e= booleanExpression RPAREN ;
    public final TextMarkerCondition conditionIf() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1047:5: (name= IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1048:5: name= IF LPAREN e= booleanExpression RPAREN
            {
            name=(Token)match(input,IF,FOLLOW_IF_in_conditionIf5350); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf5352); if (state.failed) return cond;

            pushFollow(FOLLOW_booleanExpression_in_conditionIf5358);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, e);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf5371); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionIf"



    // $ANTLR start "conditionFeature"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1053:1: conditionFeature returns [TextMarkerCondition cond = null] : name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerCondition conditionFeature() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression se =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1054:5: (name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1055:5: name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5410); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5412); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionFeature5418);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5420); if (state.failed) return cond;

            pushFollow(FOLLOW_argument_in_conditionFeature5426);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, se, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5439); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionFeature"



    // $ANTLR start "conditionParse"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1059:1: conditionParse returns [TextMarkerCondition cond = null] : name= PARSE LPAREN var= genericVariableReference RPAREN ;
    public final TextMarkerCondition conditionParse() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1060:5: (name= PARSE LPAREN var= genericVariableReference RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1061:5: name= PARSE LPAREN var= genericVariableReference RPAREN
            {
            name=(Token)match(input,PARSE,FOLLOW_PARSE_in_conditionParse5470); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5472); if (state.failed) return cond;

            pushFollow(FOLLOW_genericVariableReference_in_conditionParse5481);
            var=genericVariableReference();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5494); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionParse"



    // $ANTLR start "conditionIs"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1068:1: conditionIs returns [TextMarkerCondition cond = null] : name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionIs() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1069:5: (name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:5: name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,IS,FOLLOW_IS_in_conditionIs5524); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5526); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:22: (type= typeExpression |type= typeListExpression )
            int alt109=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA109_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt109=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:23: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5533);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:45: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5539);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5553); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionIs"



    // $ANTLR start "conditionBefore"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1075:1: conditionBefore returns [TextMarkerCondition cond = null] : name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionBefore() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1076:5: (name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:5: name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5582); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5584); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:26: (type= typeExpression |type= typeListExpression )
            int alt110=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA110_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt110=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5591);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5597);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5611); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionBefore"



    // $ANTLR start "conditionAfter"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1082:1: conditionAfter returns [TextMarkerCondition cond = null] : name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionAfter() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1083:5: (name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:5: name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5640); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5642); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:25: (type= typeExpression |type= typeListExpression )
            int alt111=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA111_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt111=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt111=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 111, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt111=1;
                }
                break;
            case LCURLY:
                {
                alt111=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 111, 0, input);

                throw nvae;

            }

            switch (alt111) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:26: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5649);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:48: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5655);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5669); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionAfter"



    // $ANTLR start "conditionStartsWith"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1089:1: conditionStartsWith returns [TextMarkerCondition cond = null] : name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionStartsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1090:5: (name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1091:5: name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5702); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5704); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1091:30: (type= typeExpression |type= typeListExpression )
            int alt112=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA112_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt112=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1091:31: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5711);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1091:53: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5717);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5731); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionStartsWith"



    // $ANTLR start "conditionEndsWith"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1096:1: conditionEndsWith returns [TextMarkerCondition cond = null] : name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionEndsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1097:5: (name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1098:5: name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5764); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5766); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1098:28: (type= typeExpression |type= typeListExpression )
            int alt113=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA113_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt113=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1098:29: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5773);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1098:51: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5779);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5793); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionEndsWith"



    // $ANTLR start "conditionSize"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1103:1: conditionSize returns [TextMarkerCondition cond = null] : name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionSize() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression list =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1104:5: (name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:5: name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,SIZE,FOLLOW_SIZE_in_conditionSize5826); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5828); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize5834);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:46: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==COMMA) ) {
                int LA114_1 = input.LA(2);

                if ( (LA114_1==COS||LA114_1==DecimalLiteral||LA114_1==EXP||LA114_1==FloatingPointLiteral||(LA114_1 >= LOGN && LA114_1 <= LPAREN)||LA114_1==MINUS||LA114_1==SIN||LA114_1==TAN) ) {
                    alt114=1;
                }
                else if ( (LA114_1==Identifier) ) {
                    int LA114_4 = input.LA(3);

                    if ( (LA114_4==COMMA||LA114_4==LPAREN||LA114_4==MINUS||(LA114_4 >= PERCENT && LA114_4 <= PLUS)||(LA114_4 >= SLASH && LA114_4 <= STAR)) ) {
                        alt114=1;
                    }
                }
            }
            switch (alt114) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:47: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5837); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5843);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5845); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5851);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:107: ( COMMA var= numberVariable )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==COMMA) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:108: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5856); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionSize5862);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, list, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5877); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return cond;
    }
    // $ANTLR end "conditionSize"



    // $ANTLR start "action"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1111:1: action returns [TextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainType |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction ) ;
    public final TextMarkerAction action() throws RecognitionException {
        TextMarkerAction result =  null;


        TextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1112:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainType |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1113:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainType |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1113:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainType |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction )
            int alt116=36;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==COLOR) ) {
                alt116=1;
            }
            else if ( (LA116_0==DEL) ) {
                alt116=2;
            }
            else if ( (LA116_0==LOG) ) {
                alt116=3;
            }
            else if ( (LA116_0==MARK) ) {
                alt116=4;
            }
            else if ( (LA116_0==MARKSCORE) ) {
                alt116=5;
            }
            else if ( (LA116_0==MARKFAST) ) {
                alt116=6;
            }
            else if ( (LA116_0==MARKLAST) ) {
                alt116=7;
            }
            else if ( (LA116_0==REPLACE) ) {
                alt116=8;
            }
            else if ( (LA116_0==RETAINTYPE) ) {
                alt116=9;
            }
            else if ( (LA116_0==FILTERTYPE) ) {
                alt116=10;
            }
            else if ( (LA116_0==CREATE) ) {
                alt116=11;
            }
            else if ( (LA116_0==FILL) ) {
                alt116=12;
            }
            else if ( (LA116_0==CALL) ) {
                alt116=13;
            }
            else if ( (LA116_0==ASSIGN) ) {
                alt116=14;
            }
            else if ( (LA116_0==SETFEATURE) ) {
                alt116=15;
            }
            else if ( (LA116_0==GETFEATURE) ) {
                alt116=16;
            }
            else if ( (LA116_0==UNMARK) ) {
                alt116=17;
            }
            else if ( (LA116_0==UNMARKALL) ) {
                alt116=18;
            }
            else if ( (LA116_0==TRANSFER) ) {
                alt116=19;
            }
            else if ( (LA116_0==MARKONCE) ) {
                alt116=20;
            }
            else if ( (LA116_0==TRIE) ) {
                alt116=21;
            }
            else if ( (LA116_0==GATHER) ) {
                alt116=22;
            }
            else if ( (LA116_0==EXEC) ) {
                alt116=23;
            }
            else if ( (LA116_0==MARKTABLE) ) {
                alt116=24;
            }
            else if ( (LA116_0==ADD) ) {
                alt116=25;
            }
            else if ( (LA116_0==REMOVE) ) {
                alt116=26;
            }
            else if ( (LA116_0==REMOVEDUPLICATE) ) {
                alt116=27;
            }
            else if ( (LA116_0==MERGE) ) {
                alt116=28;
            }
            else if ( (LA116_0==GET) ) {
                alt116=29;
            }
            else if ( (LA116_0==GETLIST) ) {
                alt116=30;
            }
            else if ( (LA116_0==MATCHEDTEXT) ) {
                alt116=31;
            }
            else if ( (LA116_0==CLEAR) ) {
                alt116=32;
            }
            else if ( (LA116_0==EXPAND) ) {
                alt116=33;
            }
            else if ( (LA116_0==CONFIGURE) ) {
                alt116=34;
            }
            else if ( (LA116_0==DYNAMICANCHORING) ) {
                alt116=35;
            }
            else if ( (LA116_0==Identifier) && (synpred17_TextMarkerParser())) {
                alt116=36;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;

            }
            switch (alt116) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1114:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5905);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1115:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5914);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1116:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5923);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1117:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5932);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1118:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5941);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1119:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5950);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1120:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5959);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5968);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1122:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5977);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1123:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5986);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1124:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5995);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1125:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action6004);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1126:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action6013);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1127:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action6022);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1128:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action6031);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1129:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action6040);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1130:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action6049);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1131:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action6058);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1132:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action6067);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1133:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action6076);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1134:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action6085);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1135:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action6094);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1136:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action6104);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1137:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action6113);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1138:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action6122);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1139:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action6131);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1140:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action6140);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1141:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action6149);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1142:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action6158);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1143:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action6168);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1144:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action6177);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1145:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action6186);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1146:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action6195);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1147:4: a= actionConfigure
                    {
                    pushFollow(FOLLOW_actionConfigure_in_action6204);
                    a=actionConfigure();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1148:4: a= actionDynamicAnchoring
                    {
                    pushFollow(FOLLOW_actionDynamicAnchoring_in_action6213);
                    a=actionDynamicAnchoring();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1149:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action6231);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {result = a;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "action"



    // $ANTLR start "externalAction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:1: externalAction returns [TextMarkerAction action = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerAction externalAction() throws RecognitionException {
        TextMarkerAction action =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1166:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1167:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "ACTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return action;}
                throw new FailedPredicateException(input, "externalAction", "isVariableOfType(input.LT(1).getText(), \"ACTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction6272); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction6276); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction6285);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction6290); if (state.failed) return action;

            if ( state.backtracking==0 ) {
            		action = external.createExternalAction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "externalAction"



    // $ANTLR start "actionCreate"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1177:1: actionCreate returns [TextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
    public final TextMarkerAction actionCreate() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression structure =null;

        Expression index =null;

        Expression fname =null;

        Expression obj1 =null;



            List left = new ArrayList();
            List right = new ArrayList();
            List indexes = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1183:5: (name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1184:5: name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate6326); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate6328); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate6334);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1185:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==COMMA) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1185:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6341); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==COS||LA118_0==DecimalLiteral||LA118_0==EXP||LA118_0==FloatingPointLiteral||(LA118_0 >= LOGN && LA118_0 <= LPAREN)||LA118_0==MINUS||LA118_0==SIN||LA118_0==TAN) ) {
                        alt118=1;
                    }
                    else if ( (LA118_0==Identifier) ) {
                        int LA118_2 = input.LA(2);

                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                            alt118=1;
                        }
                    }
                    switch (alt118) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate6362);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop117:
                            do {
                                int alt117=2;
                                int LA117_0 = input.LA(1);

                                if ( (LA117_0==COMMA) ) {
                                    int LA117_1 = input.LA(2);

                                    if ( (synpred18_TextMarkerParser()) ) {
                                        alt117=1;
                                    }


                                }


                                switch (alt117) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:89: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:90: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6379); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate6385);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop117;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate6391); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1189:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==Identifier||LA120_0==REMOVESTRING||LA120_0==StringLiteral) ) {
                        alt120=1;
                    }
                    switch (alt120) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1189:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate6409);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6411); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate6417);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1190:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop119:
                            do {
                                int alt119=2;
                                int LA119_0 = input.LA(1);

                                if ( (LA119_0==COMMA) ) {
                                    alt119=1;
                                }


                                switch (alt119) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1190:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6427); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate6433);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6435); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate6441);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                            	    }
                            	    break;

                            	default :
                            	    break loop119;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6472); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionCreate"



    // $ANTLR start "actionMarkTable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1198:1: actionMarkTable returns [TextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
    public final TextMarkerAction actionMarkTable() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression structure =null;

        Expression index =null;

        Expression table =null;

        Expression fname =null;

        Expression obj1 =null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1203:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6507); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6509); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6520);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6522); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6533);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6535); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6545);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1208:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==COMMA) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1208:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6553); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6564);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6566); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6572);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1210:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop122:
                    do {
                        int alt122=2;
                        int LA122_0 = input.LA(1);

                        if ( (LA122_0==COMMA) ) {
                            alt122=1;
                        }


                        switch (alt122) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1210:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6582); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6588);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6590); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6596);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop122;
                        }
                    } while (true);


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, index, table, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6620); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkTable"



    // $ANTLR start "actionGather"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1216:1: actionGather returns [TextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
    public final TextMarkerAction actionGather() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression structure =null;

        Expression index =null;

        Expression fname =null;

        Expression obj1 =null;

        Expression obj2 =null;



            List left = new ArrayList();
            List right = new ArrayList();
            List indexes = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1222:5: (name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1223:5: name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6654); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6656); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6662);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1225:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==COMMA) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1225:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6676); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==COS||LA125_0==DecimalLiteral||LA125_0==EXP||LA125_0==FloatingPointLiteral||(LA125_0 >= LOGN && LA125_0 <= LPAREN)||LA125_0==MINUS||LA125_0==SIN||LA125_0==TAN) ) {
                        alt125=1;
                    }
                    else if ( (LA125_0==Identifier) ) {
                        int LA125_2 = input.LA(2);

                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                            alt125=1;
                        }
                    }
                    switch (alt125) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6688);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop124:
                            do {
                                int alt124=2;
                                int LA124_0 = input.LA(1);

                                if ( (LA124_0==COMMA) ) {
                                    int LA124_1 = input.LA(2);

                                    if ( (synpred19_TextMarkerParser()) ) {
                                        alt124=1;
                                    }


                                }


                                switch (alt124) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:88: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:89: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6704); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6710);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop124;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6717); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==Identifier||LA129_0==REMOVESTRING||LA129_0==StringLiteral) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6730);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6732); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:44: (obj1= numberExpression |obj2= numberListExpression )
                            int alt126=2;
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
                                alt126=1;
                                }
                                break;
                            case Identifier:
                                {
                                int LA126_2 = input.LA(2);

                                if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                                    alt126=1;
                                }
                                else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST")))) ) {
                                    alt126=2;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return action;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 126, 2, input);

                                    throw nvae;

                                }
                                }
                                break;
                            case LCURLY:
                                {
                                alt126=2;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return action;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 126, 0, input);

                                throw nvae;

                            }

                            switch (alt126) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6739);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6747);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {left.add(fname); right.add(obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1228:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop128:
                            do {
                                int alt128=2;
                                int LA128_0 = input.LA(1);

                                if ( (LA128_0==COMMA) ) {
                                    alt128=1;
                                }


                                switch (alt128) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1228:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6758); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6764);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6766); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1228:50: (obj1= numberExpression |obj2= numberListExpression )
                            	    int alt127=2;
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
                            	        alt127=1;
                            	        }
                            	        break;
                            	    case Identifier:
                            	        {
                            	        int LA127_2 = input.LA(2);

                            	        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                            	            alt127=1;
                            	        }
                            	        else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST")))) ) {
                            	            alt127=2;
                            	        }
                            	        else {
                            	            if (state.backtracking>0) {state.failed=true; return action;}
                            	            NoViableAltException nvae =
                            	                new NoViableAltException("", 127, 2, input);

                            	            throw nvae;

                            	        }
                            	        }
                            	        break;
                            	    case LCURLY:
                            	        {
                            	        alt127=2;
                            	        }
                            	        break;
                            	    default:
                            	        if (state.backtracking>0) {state.failed=true; return action;}
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 127, 0, input);

                            	        throw nvae;

                            	    }

                            	    switch (alt127) {
                            	        case 1 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1228:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6773);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1228:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6781);
                            	            obj2=numberListExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;

                            	    }


                            	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1 != null? obj1 : obj2);}

                            	    }
                            	    break;

                            	default :
                            	    break loop128;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6813); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGather"



    // $ANTLR start "actionFill"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1236:1: actionFill returns [TextMarkerAction action = null] : name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN ;
    public final TextMarkerAction actionFill() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression structure =null;

        Expression fname =null;

        Expression obj1 =null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1241:5: (name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1242:5: name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN
            {
            name=(Token)match(input,FILL,FOLLOW_FILL_in_actionFill6848); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6850); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill6856);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, null, left, right);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1244:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+
            int cnt131=0;
            loop131:
            do {
                int alt131=2;
                int LA131_0 = input.LA(1);

                if ( (LA131_0==COMMA) ) {
                    alt131=1;
                }


                switch (alt131) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1245:5: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6874); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill6880);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6882); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionFill6892);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

            	    }
            	    break;

            	default :
            	    if ( cnt131 >= 1 ) break loop131;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(131, input);
                        throw eee;
                }
                cnt131++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, null, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6914); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionFill"



    // $ANTLR start "actionColor"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1253:1: actionColor returns [TextMarkerAction action = null] : name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final TextMarkerAction actionColor() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression bgcolor =null;

        Expression fgcolor =null;

        Expression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1254:5: (name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1255:5: name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,COLOR,FOLLOW_COLOR_in_actionColor6951); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6953); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor6959);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            match(input,COMMA,FOLLOW_COMMA_in_actionColor6973); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor6984);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1260:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==COMMA) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1260:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6998); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor7008);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1263:5: ( COMMA selected= booleanExpression )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==COMMA) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1263:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor7022); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor7032);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor7048); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionColor"



    // $ANTLR start "actionDel"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1269:1: actionDel returns [TextMarkerAction action = null] : name= DEL ;
    public final TextMarkerAction actionDel() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1270:5: (name= DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1271:5: name= DEL
            {
            name=(Token)match(input,DEL,FOLLOW_DEL_in_actionDel7080); if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, new ArrayList());}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionDel"



    // $ANTLR start "actionLog"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1275:1: actionLog returns [TextMarkerAction action = null] : name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final TextMarkerAction actionLog() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token log=null;
        Expression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1276:5: (name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1277:5: name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            name=(Token)match(input,LOG,FOLLOW_LOG_in_actionLog7126); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog7128); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog7134);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1277:46: ( COMMA log= LogLevel )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==COMMA) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1277:47: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog7137); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog7143); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createLogAction(name, lit, log);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog7159); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionLog"



    // $ANTLR start "actionMark"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1282:1: actionMark returns [TextMarkerAction action = null] : name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMark() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1286:5: (name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1287:5: name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARK,FOLLOW_MARK_in_actionMark7197); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark7199); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark7210);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1290:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop135:
            do {
                int alt135=2;
                int LA135_0 = input.LA(1);

                if ( (LA135_0==COMMA) ) {
                    alt135=1;
                }


                switch (alt135) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1291:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark7228); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark7244);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop135;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark7266); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMark"



    // $ANTLR start "actionExpand"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1297:1: actionExpand returns [TextMarkerAction action = null] : name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionExpand() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1301:5: (name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1302:5: name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand7303); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand7305); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand7316);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1305:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==COMMA) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1306:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand7334); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand7350);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand7372); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionExpand"



    // $ANTLR start "actionMarkScore"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1312:1: actionMarkScore returns [TextMarkerAction action = null] : name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkScore() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression score =null;

        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1316:5: (name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1317:5: name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore7409); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore7411); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore7417);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7419); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore7425);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(score); list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1319:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==COMMA) ) {
                    alt137=1;
                }


                switch (alt137) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1320:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7443); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore7459);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop137;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore7481); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkScore"



    // $ANTLR start "actionMarkOnce"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1326:1: actionMarkOnce returns [TextMarkerAction action = null] : name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkOnce() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression score =null;

        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1330:5: (name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:5: name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7518); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7520); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:28: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==MINUS) && (synpred23_TextMarkerParser())) {
                alt138=1;
            }
            else if ( (LA138_0==Identifier) ) {
                int LA138_2 = input.LA(2);

                if ( (((((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))&&((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT"))))&&synpred23_TextMarkerParser())) ) {
                    alt138=1;
                }
            }
            else if ( (LA138_0==DecimalLiteral) && (synpred23_TextMarkerParser())) {
                alt138=1;
            }
            else if ( (LA138_0==FloatingPointLiteral) && (synpred23_TextMarkerParser())) {
                alt138=1;
            }
            else if ( (LA138_0==LPAREN) && (synpred23_TextMarkerParser())) {
                alt138=1;
            }
            else if ( (LA138_0==COS||LA138_0==EXP||LA138_0==LOGN||LA138_0==SIN||LA138_0==TAN) && (synpred23_TextMarkerParser())) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:29: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7537);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7539); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7557);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(score); list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1333:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop139:
            do {
                int alt139=2;
                int LA139_0 = input.LA(1);

                if ( (LA139_0==COMMA) ) {
                    alt139=1;
                }


                switch (alt139) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1334:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7575); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7591);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7613); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkOnce"



    // $ANTLR start "actionMarkFast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1340:1: actionMarkFast returns [TextMarkerAction action = null] : name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN ;
    public final TextMarkerAction actionMarkFast() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression list =null;

        Expression ignore =null;

        Expression numExpr =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1341:5: (name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1342:5: name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7645); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7647); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7653);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7666); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7672);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1346:5: ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==COMMA) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1346:6: COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7686); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7692);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1346:39: ( COMMA numExpr= numberExpression )?
                    int alt140=2;
                    int LA140_0 = input.LA(1);

                    if ( (LA140_0==COMMA) ) {
                        alt140=1;
                    }
                    switch (alt140) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1346:40: COMMA numExpr= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7695); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7701);
                            numExpr=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7719); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkFast"



    // $ANTLR start "actionMarkLast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1351:1: actionMarkLast returns [TextMarkerAction action = null] : name= MARKLAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerAction actionMarkLast() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1352:5: (name= MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1353:5: name= MARKLAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7751); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7753); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7759);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7772); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMarkLast"



    // $ANTLR start "actionReplace"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1359:1: actionReplace returns [TextMarkerAction action = null] : name= REPLACE LPAREN lit= stringExpression RPAREN ;
    public final TextMarkerAction actionReplace() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1360:5: (name= REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1361:5: name= REPLACE LPAREN lit= stringExpression RPAREN
            {
            name=(Token)match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7805); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7807); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace7813);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, lit);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7826); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionReplace"



    // $ANTLR start "actionRetainType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1367:1: actionRetainType returns [TextMarkerAction action = null] : name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainType() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1371:5: (name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1372:5: name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7872); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1372:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==LPAREN) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1372:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7875); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7881);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1374:5: ( COMMA id= typeExpression )*
                    loop142:
                    do {
                        int alt142=2;
                        int LA142_0 = input.LA(1);

                        if ( (LA142_0==COMMA) ) {
                            alt142=1;
                        }


                        switch (alt142) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1374:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7897); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7903);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop142;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7920); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRetainType"



    // $ANTLR start "actionFilterType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1381:1: actionFilterType returns [TextMarkerAction action = null] : name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterType() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1385:5: (name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1386:5: name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType7970); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1386:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==LPAREN) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1386:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType7973); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType7979);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:5: ( COMMA id= typeExpression )*
                    loop144:
                    do {
                        int alt144=2;
                        int LA144_0 = input.LA(1);

                        if ( (LA144_0==COMMA) ) {
                            alt144=1;
                        }


                        switch (alt144) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType7995); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType8001);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop144;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType8018); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionFilterType"



    // $ANTLR start "actionCall"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1394:1: actionCall returns [TextMarkerAction action = null] : name= CALL lp= LPAREN ns= dottedComponentReference RPAREN ;
    public final TextMarkerAction actionCall() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;



        String string = "";


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1399:5: (name= CALL lp= LPAREN ns= dottedComponentReference RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1400:5: name= CALL lp= LPAREN ns= dottedComponentReference RPAREN
            {
            name=(Token)match(input,CALL,FOLLOW_CALL_in_actionCall8067); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionCall8073); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}

            pushFollow(FOLLOW_dottedComponentReference_in_actionCall8095);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns);}}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall8109); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionCall"



    // $ANTLR start "actionConfigure"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1409:1: actionConfigure returns [TextMarkerAction action = null] : name= CONFIGURE lp= LPAREN ns= dottedComponentReference ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? RPAREN ;
    public final TextMarkerAction actionConfigure() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;

        Expression fname =null;

        Expression obj1 =null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1414:5: (name= CONFIGURE lp= LPAREN ns= dottedComponentReference ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1415:5: name= CONFIGURE lp= LPAREN ns= dottedComponentReference ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? RPAREN
            {
            name=(Token)match(input,CONFIGURE,FOLLOW_CONFIGURE_in_actionConfigure8144); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionConfigure8150); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createConfigureAction(name, StatementFactory.createEmtpyComponentReference(lp), null , null);}

            pushFollow(FOLLOW_dottedComponentReference_in_actionConfigure8172);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createConfigureAction(name, ns, null , null);}}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1421:6: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==COMMA) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1421:7: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure8193); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionConfigure8199);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure8201); if (state.failed) return action;

                    pushFollow(FOLLOW_argument_in_actionConfigure8207);
                    obj1=argument();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1422:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                    loop146:
                    do {
                        int alt146=2;
                        int LA146_0 = input.LA(1);

                        if ( (LA146_0==COMMA) ) {
                            alt146=1;
                        }


                        switch (alt146) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1422:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure8217); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionConfigure8223);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure8225); if (state.failed) return action;

                    	    pushFollow(FOLLOW_argument_in_actionConfigure8231);
                    	    obj1=argument();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop146;
                        }
                    } while (true);


                    }
                    break;

            }


            if ( state.backtracking==0 ) {   action = ActionFactory.createConfigureAction(name, ns, left , right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionConfigure8255); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionConfigure"



    // $ANTLR start "actionExec"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1429:1: actionExec returns [TextMarkerAction action = null] : name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionExec() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;

        Expression tl =null;



        String string = "";

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1433:5: (name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1434:5: name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN
            {
            name=(Token)match(input,EXEC,FOLLOW_EXEC_in_actionExec8290); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionExec8296); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}

            pushFollow(FOLLOW_dottedComponentReference_in_actionExec8314);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, null);}}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1438:6: ( COMMA tl= typeListExpression )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==COMMA) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1438:7: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec8330); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec8336);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, tl);}}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec8352); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionExec"



    // $ANTLR start "actionAssign"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1444:1: actionAssign returns [TextMarkerAction action = null] : name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN ;
    public final TextMarkerAction actionAssign() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token id=null;
        Expression e =null;



            VariableReference ref = null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1448:5: (name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1449:5: name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign8394); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign8396); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1450:5: (id= Identifier COMMA e= argument )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1450:6: id= Identifier COMMA e= argument
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8407); if (state.failed) return action;

            if ( state.backtracking==0 ) {
                ref = ExpressionFactory.createGenericVariableReference(id);
                action = ActionFactory.createAction(name, ref, e);}

            match(input,COMMA,FOLLOW_COMMA_in_actionAssign8425); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionAssign8431);
            e=argument();

            state._fsp--;
            if (state.failed) return action;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8439); if (state.failed) return action;

            if ( state.backtracking==0 ) {
                ref = ExpressionFactory.createGenericVariableReference(id);
                action = ActionFactory.createAction(name, ref, e);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionAssign"



    // $ANTLR start "actionSetFeature"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1463:1: actionSetFeature returns [TextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerAction actionSetFeature() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1464:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1465:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8476); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8478); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8484);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8498); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature8504);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8517); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionSetFeature"



    // $ANTLR start "actionGetFeature"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1472:1: actionGetFeature returns [TextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final TextMarkerAction actionGetFeature() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1473:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1474:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8546); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8548); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8554);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8567); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature8573);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8586); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGetFeature"



    // $ANTLR start "actionDynamicAnchoring"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1482:1: actionDynamicAnchoring returns [TextMarkerAction action = null] : name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN ;
    public final TextMarkerAction actionDynamicAnchoring() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression active =null;

        Expression penalty =null;

        Expression factor =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1483:5: (name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1484:5: name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,DYNAMICANCHORING,FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8616); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDynamicAnchoring8618); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionDynamicAnchoring8624);
            active=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, active);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1486:5: ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==COMMA) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1486:6: COMMA penalty= numberExpression ( COMMA factor= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8639); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8645);
                    penalty=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, active, penalty);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1488:5: ( COMMA factor= numberExpression )?
                    int alt149=2;
                    int LA149_0 = input.LA(1);

                    if ( (LA149_0==COMMA) ) {
                        alt149=1;
                    }
                    switch (alt149) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1488:6: COMMA factor= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring8659); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring8665);
                            factor=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, active, penalty, factor);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDynamicAnchoring8682); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionDynamicAnchoring"



    // $ANTLR start "actionUnmark"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1494:1: actionUnmark returns [TextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionUnmark() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1495:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1496:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8712); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8714); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark8720);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8733); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionUnmark"



    // $ANTLR start "actionUnmarkAll"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1501:1: actionUnmarkAll returns [TextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionUnmarkAll() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1502:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1503:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8762); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8764); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8770);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1505:5: ( COMMA list= typeListExpression )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==COMMA) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1505:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8784); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8790);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8805); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionUnmarkAll"



    // $ANTLR start "actionTransfer"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1513:1: actionTransfer returns [TextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionTransfer() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1514:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1515:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8837); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8839); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer8845);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8858); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionTransfer"



    // $ANTLR start "actionTrie"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1520:1: actionTrie returns [TextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
    public final TextMarkerAction actionTrie() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression key =null;

        Expression value =null;

        Expression list =null;

        Expression ignoreCase =null;

        Expression ignoreLength =null;

        Expression edit =null;

        Expression distance =null;

        Expression ignoreChar =null;



        Map<Expression, Expression> map = new HashMap<Expression, Expression>();
        List<Expression> left = new ArrayList<Expression>();
        List<Expression> right = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1526:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1527:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8896); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8898); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8912);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {left.add(key);}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8915); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie8930);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {right.add(value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1530:9: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop152:
            do {
                int alt152=2;
                int LA152_0 = input.LA(1);

                if ( (LA152_0==COMMA) ) {
                    int LA152_1 = input.LA(2);

                    if ( (LA152_1==Identifier) ) {
                        int LA152_2 = input.LA(3);

                        if ( (LA152_2==ASSIGN_EQUAL||LA152_2==LPAREN||LA152_2==PLUS) ) {
                            alt152=1;
                        }


                    }
                    else if ( (LA152_1==REMOVESTRING||LA152_1==StringLiteral) ) {
                        alt152=1;
                    }


                }


                switch (alt152) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1530:10: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8943); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8949);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {left.add(key);}

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8953); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8968);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {right.add(value);}

            	    }
            	    break;

            	default :
            	    break loop152;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8982); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie8988);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9004); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie9010);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9017); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie9023);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9030); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie9036);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9043); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie9049);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9056); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie9062);
            ignoreChar=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {
                List args = new ArrayList();
                	args.add(ignoreCase);
            	args.add(ignoreLength);
            	args.add(edit);
            	args.add(distance);
            	args.add(ignoreChar);
                
                action = ActionFactory.createStructureAction(name, list, args, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie9085); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionTrie"



    // $ANTLR start "actionAdd"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1554:1: actionAdd returns [TextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionAdd() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression a =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1558:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1559:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd9123); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd9125); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd9131);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1561:5: ( COMMA a= argument )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1561:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd9145); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd9151);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd9168); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionAdd"



    // $ANTLR start "actionRemove"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1566:1: actionRemove returns [TextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionRemove() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression a =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1570:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1571:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove9202); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove9204); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove9210);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1573:5: ( COMMA a= argument )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1573:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove9224); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove9230);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove9247); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRemove"



    // $ANTLR start "actionRemoveDuplicate"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1579:1: actionRemoveDuplicate returns [TextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final TextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1580:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1581:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9277); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate9279); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate9285);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate9298); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionRemoveDuplicate"



    // $ANTLR start "actionMerge"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1586:1: actionMerge returns [TextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final TextMarkerAction actionMerge() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression join =null;

        Expression t =null;

        Expression f =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1590:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1591:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge9335); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge9337); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge9343);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9357); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge9363);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9377); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge9383);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1596:5: ( COMMA f= listExpression )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1596:6: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge9393); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge9399);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(f);}

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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge9416); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMerge"



    // $ANTLR start "actionGet"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1601:1: actionGet returns [TextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGet() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression var =null;

        Expression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1602:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1603:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet9445); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet9447); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet9453);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9466); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet9472);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9485); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet9491);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet9504); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGet"



    // $ANTLR start "actionGetList"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1613:1: actionGetList returns [TextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGetList() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;

        Expression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1614:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1615:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList9534); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList9536); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList9542);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList9555); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList9561);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, op);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList9574); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionGetList"



    // $ANTLR start "actionMatchedText"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1622:1: actionMatchedText returns [TextMarkerAction action = null] : name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMatchedText() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1626:5: (name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1627:5: name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText9611); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText9613); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText9624);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1629:5: ( COMMA index= numberExpression )*
            loop156:
            do {
                int alt156=2;
                int LA156_0 = input.LA(1);

                if ( (LA156_0==COMMA) ) {
                    alt156=1;
                }


                switch (alt156) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1630:5: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText9636); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText9642);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop156;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText9664); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionMatchedText"



    // $ANTLR start "actionClear"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1636:1: actionClear returns [TextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final TextMarkerAction actionClear() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1637:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1638:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear9697); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear9699); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear9705);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear9718); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return action;
    }
    // $ANTLR end "actionClear"



    // $ANTLR start "varArgumentList"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1645:1: varArgumentList returns [List<Expression> args = new ArrayList<Expression>()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List<Expression> varArgumentList() throws RecognitionException {
        List<Expression> args =  new ArrayList<Expression>();


        Expression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1646:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1647:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1647:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==LPAREN) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1647:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList9740); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList9746);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1647:42: ( COMMA arg= argument )*
                    loop157:
                    do {
                        int alt157=2;
                        int LA157_0 = input.LA(1);

                        if ( (LA157_0==COMMA) ) {
                            alt157=1;
                        }


                        switch (alt157) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1647:43: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList9751); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList9757);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;

                    	    if ( state.backtracking==0 ) {args.add(arg);}

                    	    }
                    	    break;

                    	default :
                    	    break loop157;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList9763); if (state.failed) return args;

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return args;
    }
    // $ANTLR end "varArgumentList"



    // $ANTLR start "argument"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1651:1: argument returns [Expression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final Expression argument() throws RecognitionException {
        Expression expr =  null;


        Expression a4 =null;

        Expression a2 =null;

        Expression a3 =null;

        Expression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1655:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
            int alt159=4;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
            case StringLiteral:
                {
                alt159=1;
                }
                break;
            case Identifier:
                {
                int LA159_2 = input.LA(2);

                if ( (((synpred26_TextMarkerParser()&&synpred26_TextMarkerParser())&&((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "STRING"))))) ) {
                    alt159=1;
                }
                else if ( (synpred27_TextMarkerParser()) ) {
                    alt159=2;
                }
                else if ( (((((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))&&((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT"))))&&synpred28_TextMarkerParser())) ) {
                    alt159=3;
                }
                else if ( (true) ) {
                    alt159=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 159, 2, input);

                    throw nvae;

                }
                }
                break;
            case FALSE:
            case TRUE:
            case XOR:
                {
                alt159=2;
                }
                break;
            case BasicAnnotationType:
                {
                int LA159_6 = input.LA(2);

                if ( (synpred27_TextMarkerParser()) ) {
                    alt159=2;
                }
                else if ( (true) ) {
                    alt159=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 159, 6, input);

                    throw nvae;

                }
                }
                break;
            case LPAREN:
                {
                int LA159_7 = input.LA(2);

                if ( (synpred27_TextMarkerParser()) ) {
                    alt159=2;
                }
                else if ( (synpred28_TextMarkerParser()) ) {
                    alt159=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 159, 7, input);

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
                alt159=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 159, 0, input);

                throw nvae;

            }

            switch (alt159) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1656:3: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument9800);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1657:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument9811);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1658:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9822);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1659:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9833);
                    a1=typeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a1;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "argument"



    // $ANTLR start "dottedIdentifier"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1670:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1671:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1672:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9870); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1673:2: (dot= DOT idn= Identifier )*
            loop160:
            do {
                int alt160=2;
                int LA160_0 = input.LA(1);

                if ( (LA160_0==DOT) ) {
                    alt160=1;
                }


                switch (alt160) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1674:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9883); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9893); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop160;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return idString;
    }
    // $ANTLR end "dottedIdentifier"



    // $ANTLR start "dottedId"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1680:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1682:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1683:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9925); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1686:2: (dot= DOT id= Identifier )*
            loop161:
            do {
                int alt161=2;
                int LA161_0 = input.LA(1);

                if ( (LA161_0==DOT) ) {
                    alt161=1;
                }


                switch (alt161) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1687:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9938); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9948); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop161;
                }
            } while (true);


            if ( state.backtracking==0 ) {token = ct;
            	 return token;}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return token;
    }
    // $ANTLR end "dottedId"



    // $ANTLR start "dottedComponentReference"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1696:1: dottedComponentReference returns [ComponentReference ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentReference dottedComponentReference() throws RecognitionException {
        ComponentReference ref =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1698:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1699:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference9983); if (state.failed) return ref;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		//if (ct.getText().equals("<missing Identifier>")) {
            	        //    CommonTokenStream cts = (CommonTokenStream) input;
            	        //    Token lt = cts.LT(1);
            	        //    ct = new CommonToken(lt);
            	        //  }
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1707:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop162:
            do {
                int alt162=2;
                int LA162_0 = input.LA(1);

                if ( (LA162_0==DOT||LA162_0==MINUS) ) {
                    alt162=1;
                }


                switch (alt162) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1708:3: dot= ( DOT | MINUS ) id= Identifier
            	    {
            	    dot=(Token)input.LT(1);

            	    if ( input.LA(1)==DOT||input.LA(1)==MINUS ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ref;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference10012); if (state.failed) return ref;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop162;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            	 if (!ct.getText().equals("<missing Identifier>")) ref = StatementFactory.createComponentReference(ct);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return ref;
    }
    // $ANTLR end "dottedComponentReference"



    // $ANTLR start "dottedComponentDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1716:1: dottedComponentDeclaration returns [ComponentDeclaration ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentDeclaration dottedComponentDeclaration() throws RecognitionException {
        ComponentDeclaration ref =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1718:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1719:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration10046); if (state.failed) return ref;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1722:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop163:
            do {
                int alt163=2;
                int LA163_0 = input.LA(1);

                if ( (LA163_0==DOT||LA163_0==MINUS) ) {
                    alt163=1;
                }


                switch (alt163) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1723:3: dot= ( DOT | MINUS ) id= Identifier
            	    {
            	    dot=(Token)input.LT(1);

            	    if ( input.LA(1)==DOT||input.LA(1)==MINUS ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ref;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration10075); if (state.failed) return ref;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop163;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            	 if (!ct.getText().equals("<missing Identifier>")) ref = StatementFactory.createComponentDeclaration(ct);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return ref;
    }
    // $ANTLR end "dottedComponentDeclaration"



    // $ANTLR start "annotationType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1732:1: annotationType returns [Expression at = null] : (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType ) ;
    public final Expression annotationType() throws RecognitionException {
        Expression at =  null;


        Token basicAT=null;
        Expression atRef =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1733:2: ( (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1734:2: (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1734:2: (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType )
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==Identifier) ) {
                alt164=1;
            }
            else if ( (LA164_0==BasicAnnotationType) ) {
                alt164=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return at;}
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;

            }
            switch (alt164) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1735:2: atRef= annotationTypeVariableReference
                    {
                    pushFollow(FOLLOW_annotationTypeVariableReference_in_annotationType10109);
                    atRef=annotationTypeVariableReference();

                    state._fsp--;
                    if (state.failed) return at;

                    if ( state.backtracking==0 ) {at = atRef;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1736:4: basicAT= BasicAnnotationType
                    {
                    basicAT=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType10120); if (state.failed) return at;

                    if ( state.backtracking==0 ) {at = ExpressionFactory.createAnnotationTypeConstantReference(basicAT);}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return at;
    }
    // $ANTLR end "annotationType"



    // $ANTLR start "annotationTypeVariableReference"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1740:1: annotationTypeVariableReference returns [Expression typeVar = null] : atRef= dottedId ;
    public final Expression annotationTypeVariableReference() throws RecognitionException {
        Expression typeVar =  null;


        Token atRef =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1741:3: (atRef= dottedId )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1742:3: atRef= dottedId
            {
            pushFollow(FOLLOW_dottedId_in_annotationTypeVariableReference10149);
            atRef=dottedId();

            state._fsp--;
            if (state.failed) return typeVar;

            if ( state.backtracking==0 ) {typeVar = ExpressionFactory.createAnnotationTypeVariableReference(atRef);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return typeVar;
    }
    // $ANTLR end "annotationTypeVariableReference"



    // $ANTLR start "wordListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1746:1: wordListExpression returns [Expression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final Expression wordListExpression() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1747:2: (id= Identifier |path= RessourceLiteral )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1748:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression10173); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListVariableReference(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1751:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression10186); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createRessourceReference(path);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "wordListExpression"



    // $ANTLR start "wordTableExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1756:1: wordTableExpression returns [Expression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final Expression wordTableExpression() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1757:2: (id= Identifier |path= RessourceLiteral )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1758:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression10210); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTableVariableReference(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1761:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression10223); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createRessourceReference(path);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "wordTableExpression"



    // $ANTLR start "numberExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1766:1: numberExpression returns [Expression expr = null] : e= additiveExpression ;
    public final Expression numberExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1767:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1768:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression10247);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {if(e!=null) expr = ExpressionFactory.createNumberExpression(e);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberExpression"



    // $ANTLR start "additiveExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1773:1: additiveExpression returns [Expression root = null] : expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* ;
    public final Expression additiveExpression() throws RecognitionException {
        Expression root =  null;


        Token op=null;
        Expression expr1 =null;

        Expression expr2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1774:5: (expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1774:9: expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10273);
            expr1=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return root;

            if ( state.backtracking==0 ) {root=expr1;}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1775:2: (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            loop167:
            do {
                int alt167=2;
                int LA167_0 = input.LA(1);

                if ( (LA167_0==MINUS||LA167_0==PLUS) ) {
                    alt167=1;
                }


                switch (alt167) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1775:4: op= ( PLUS | MINUS ) expr2= multiplicativeExpression
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return root;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10292);
            	    expr2=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return root;

            	    if ( state.backtracking==0 ) {root=ExpressionFactory.createBinaryArithmeticExpr(root,expr2,op);}

            	    }
            	    break;

            	default :
            	    break loop167;
                }
            } while (true);


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return root;
    }
    // $ANTLR end "additiveExpression"



    // $ANTLR start "multiplicativeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1779:1: multiplicativeExpression returns [Expression root = null] : (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction ) ;
    public final Expression multiplicativeExpression() throws RecognitionException {
        Expression root =  null;


        Token op=null;
        Expression expr1 =null;

        Expression sNE =null;

        Expression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1780:5: ( (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1781:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1781:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction )
            int alt169=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt169=1;
                }
                break;
            case Identifier:
                {
                int LA169_2 = input.LA(2);

                if ( (LA169_2==LPAREN) ) {
                    alt169=2;
                }
                else if ( (LA169_2==EOF||LA169_2==COMMA||LA169_2==EQUAL||(LA169_2 >= GREATER && LA169_2 <= GREATEREQUAL)||(LA169_2 >= LESS && LA169_2 <= LESSEQUAL)||LA169_2==MINUS||LA169_2==NOTEQUAL||(LA169_2 >= PERCENT && LA169_2 <= PLUS)||LA169_2==RBRACK||LA169_2==RPAREN||LA169_2==SEMI||(LA169_2 >= SLASH && LA169_2 <= STAR)) ) {
                    alt169=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return root;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 169, 2, input);

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
                alt169=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return root;}
                NoViableAltException nvae =
                    new NoViableAltException("", 169, 0, input);

                throw nvae;

            }

            switch (alt169) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1781:3: expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10321);
                    expr1=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return root;

                    if ( state.backtracking==0 ) {root=expr1;}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1782:2: (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    loop168:
                    do {
                        int alt168=2;
                        int LA168_0 = input.LA(1);

                        if ( (LA168_0==PERCENT||(LA168_0 >= SLASH && LA168_0 <= STAR)) ) {
                            alt168=1;
                        }


                        switch (alt168) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1782:4: op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression
                    	    {
                    	    op=(Token)input.LT(1);

                    	    if ( input.LA(1)==PERCENT||(input.LA(1) >= SLASH && input.LA(1) <= STAR) ) {
                    	        input.consume();
                    	        state.errorRecovery=false;
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return root;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }


                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10348);
                    	    sNE=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return root;

                    	    if ( state.backtracking==0 ) {root=ExpressionFactory.createBinaryArithmeticExpr(root,sNE,op);}

                    	    }
                    	    break;

                    	default :
                    	    break loop168;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1783:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression10364);
                    e1=numberFunction();

                    state._fsp--;
                    if (state.failed) return root;

                    if ( state.backtracking==0 ) {root = e1;}

                    }
                    break;

            }


            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return root;
    }
    // $ANTLR end "multiplicativeExpression"



    // $ANTLR start "numberExpressionInPar"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1787:1: numberExpressionInPar returns [TextMarkerExpression expr = null] : lp= LPAREN numE= numberExpression rp= RPAREN ;
    public final TextMarkerExpression numberExpressionInPar() throws RecognitionException {
        TextMarkerExpression expr =  null;


        Token lp=null;
        Token rp=null;
        Expression numE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1788:2: (lp= LPAREN numE= numberExpression rp= RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1789:2: lp= LPAREN numE= numberExpression rp= RPAREN
            {
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar10388); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_numberExpressionInPar10394);
            numE=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            rp=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar10400); if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = ExpressionFactory.createNumberExpression((TextMarkerExpression)numE); 
            	  expr.setInParantheses(true);
                      expr.setStart(((CommonToken) lp).getStartIndex());
                      expr.setEnd(((CommonToken) rp).getStopIndex()+1);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberExpressionInPar"



    // $ANTLR start "simpleNumberExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1797:1: simpleNumberExpression returns [Expression expr = null] : ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral |numExprPar= numberExpressionInPar );
    public final Expression simpleNumberExpression() throws RecognitionException {
        Expression expr =  null;


        Token m=null;
        Token decLit=null;
        Token fpLit=null;
        Expression numVarRef =null;

        TextMarkerExpression numExprPar =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1798:2: ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral |numExprPar= numberExpressionInPar )
            int alt173=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case Identifier:
                    {
                    alt173=1;
                    }
                    break;
                case DecimalLiteral:
                    {
                    alt173=2;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt173=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 173, 1, input);

                    throw nvae;

                }

                }
                break;
            case Identifier:
                {
                alt173=1;
                }
                break;
            case DecimalLiteral:
                {
                alt173=2;
                }
                break;
            case FloatingPointLiteral:
                {
                alt173=3;
                }
                break;
            case LPAREN:
                {
                alt173=4;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1799:2: (m= MINUS )? numVarRef= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1799:4: (m= MINUS )?
                    int alt170=2;
                    int LA170_0 = input.LA(1);

                    if ( (LA170_0==MINUS) ) {
                        alt170=1;
                    }
                    switch (alt170) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1799:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10425); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression10432);
                    numVarRef=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {if(m == null) {expr = numVarRef;} else {expr = ExpressionFactory.createNegatedNumberExpression(m, numVarRef);}}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1801:4: (m= MINUS )? decLit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1801:4: (m= MINUS )?
                    int alt171=2;
                    int LA171_0 = input.LA(1);

                    if ( (LA171_0==MINUS) ) {
                        alt171=1;
                    }
                    switch (alt171) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1801:5: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10447); if (state.failed) return expr;

                            }
                            break;

                    }


                    decLit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression10455); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDecimalLiteral(decLit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1803:4: (m= MINUS )? fpLit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1803:6: (m= MINUS )?
                    int alt172=2;
                    int LA172_0 = input.LA(1);

                    if ( (LA172_0==MINUS) ) {
                        alt172=1;
                    }
                    switch (alt172) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1803:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10469); if (state.failed) return expr;

                            }
                            break;

                    }


                    fpLit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10476); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createFloatingPointLiteral(fpLit,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1806:4: numExprPar= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression10492);
                    numExprPar=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = numExprPar;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleNumberExpression"



    // $ANTLR start "numberFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1811:1: numberFunction returns [Expression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final Expression numberFunction() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        TextMarkerExpression numExprP =null;

        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1812:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==COS||LA174_0==EXP||LA174_0==LOGN||LA174_0==SIN||LA174_0==TAN) ) {
                alt174=1;
            }
            else if ( (LA174_0==Identifier) && (synpred29_TextMarkerParser())) {
                alt174=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 174, 0, input);

                throw nvae;

            }
            switch (alt174) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1813:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1813:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1813:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
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


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction10539);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createUnaryArithmeticExpr(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction10563);
                    e=externalNumberFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberFunction"



    // $ANTLR start "externalNumberFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1820:1: externalNumberFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalNumberFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1821:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1822:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalNumberFunction", "isVariableOfType(input.LT(1).getText(), \"NUMBERFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction10588); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction10592); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction10599);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction10602); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalNumberFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalNumberFunction"



    // $ANTLR start "numberVariable"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1832:1: numberVariable returns [Expression expr = null] : ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier ) ;
    public final Expression numberVariable() throws RecognitionException {
        Expression expr =  null;


        Token numVarRef=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1833:2: ( ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1834:5: ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1834:5: ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier )
            int alt175=3;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==Identifier) ) {
                int LA175_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                    alt175=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                    alt175=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "FLOAT"))) ) {
                    alt175=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 175, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 175, 0, input);

                throw nvae;

            }
            switch (alt175) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1834:7: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"INT\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10633); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1835:5: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"DOUBLE\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10646); if (state.failed) return expr;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1836:6: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "FLOAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"FLOAT\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10659); if (state.failed) return expr;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {	 expr = ExpressionFactory.createNumberVariableReference(numVarRef);}

            }

        }
        catch (Exception e) {
            expr = ExpressionFactory.createNumberVariableReference(input.LT(1));
        }

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "numberVariable"



    // $ANTLR start "stringExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1842:1: stringExpression returns [Expression expr = null] : (e= stringFunction |strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )* );
    public final Expression stringExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;

        Expression strExpr1 =null;

        Expression nextstrExpr =null;

        TextMarkerExpression ne =null;

        Expression be =null;

        Expression le =null;

        Expression te =null;


        List<Expression> exprList = new ArrayList<Expression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1844:2: (e= stringFunction |strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )* )
            int alt178=2;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
                {
                alt178=1;
                }
                break;
            case Identifier:
                {
                int LA178_2 = input.LA(2);

                if ( (LA178_2==LPAREN) ) {
                    alt178=1;
                }
                else if ( (LA178_2==EOF||LA178_2==ASSIGN_EQUAL||LA178_2==COMMA||LA178_2==PLUS||LA178_2==RPAREN||LA178_2==SEMI) ) {
                    alt178=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 178, 2, input);

                    throw nvae;

                }
                }
                break;
            case StringLiteral:
                {
                alt178=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 178, 0, input);

                throw nvae;

            }

            switch (alt178) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1845:2: e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression10697);
                    e=stringFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1847:2: strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10710);
                    strExpr1=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {if (strExpr1!=null) exprList.add(strExpr1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1848:2: ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )*
                    loop177:
                    do {
                        int alt177=2;
                        int LA177_0 = input.LA(1);

                        if ( (LA177_0==PLUS) ) {
                            alt177=1;
                        }


                        switch (alt177) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1848:3: PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression10716); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1848:8: (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression )
                    	    int alt176=5;
                    	    int LA176_0 = input.LA(1);

                    	    if ( (LA176_0==StringLiteral) ) {
                    	        alt176=1;
                    	    }
                    	    else if ( (LA176_0==Identifier) ) {
                    	        int LA176_2 = input.LA(2);

                    	        if ( ((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                    	            alt176=1;
                    	        }
                    	        else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt176=3;
                    	        }
                    	        else if ( (((((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))&&((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST"))))&&synpred30_TextMarkerParser())) ) {
                    	            alt176=4;
                    	        }
                    	        else if ( (true) ) {
                    	            alt176=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 176, 2, input);

                    	            throw nvae;

                    	        }
                    	    }
                    	    else if ( (LA176_0==LPAREN) ) {
                    	        alt176=2;
                    	    }
                    	    else if ( (LA176_0==FALSE||LA176_0==TRUE) ) {
                    	        alt176=3;
                    	    }
                    	    else if ( (LA176_0==LCURLY) && (synpred30_TextMarkerParser())) {
                    	        alt176=4;
                    	    }
                    	    else if ( (LA176_0==BasicAnnotationType) ) {
                    	        alt176=5;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 176, 0, input);

                    	        throw nvae;

                    	    }
                    	    switch (alt176) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1848:9: nextstrExpr= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10723);
                    	            nextstrExpr=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (nextstrExpr!=null) exprList.add(nextstrExpr);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1849:5: ne= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression10735);
                    	            ne=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (ne!=null) exprList.add(ne);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1850:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression10747);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (be!=null) exprList.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1851:5: ( listExpression )=>le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression10764);
                    	            le=listExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (le!=null) exprList.add(le);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1852:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression10776);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (te!=null) exprList.add(te);}

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop177;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringExpression(exprList);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "stringExpression"



    // $ANTLR start "stringFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1859:1: stringFunction returns [Expression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final Expression stringFunction() throws RecognitionException {
        Expression expr =  null;


        Token name=null;
        Expression var =null;

        Expression s =null;

        Expression e =null;


        List<Expression> list = new ArrayList<Expression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1861:2: (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==REMOVESTRING) ) {
                alt180=1;
            }
            else if ( (LA180_0==Identifier) && (synpred31_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1862:2: name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction10813); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction10815); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction10821);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1862:44: ( COMMA s= stringExpression )+
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
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1862:45: COMMA s= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction10824); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction10830);
                    	    s=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {list.add(s);}

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


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction10835); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringFunction(name,var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1865:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction10857);
                    e=externalStringFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "stringFunction"



    // $ANTLR start "externalStringFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1869:1: externalStringFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalStringFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1870:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1871:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalStringFunction", "isVariableOfType(input.LT(1).getText(), \"STRINGFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction10882); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction10886); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction10893);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction10896); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalStringFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalStringFunction"



    // $ANTLR start "simpleStringExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1881:1: simpleStringExpression returns [Expression expr = null] : (lit= StringLiteral |{...}?variableId= Identifier );
    public final Expression simpleStringExpression() throws RecognitionException {
        Expression expr =  null;


        Token lit=null;
        Token variableId=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1882:2: (lit= StringLiteral |{...}?variableId= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1883:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression10921); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleString(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1884:5: {...}?variableId= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType(input.LT(1).getText(), \"STRING\")");
                    }

                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression10936); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringVariableReference(variableId);}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleStringExpression"



    // $ANTLR start "booleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1895:1: booleanExpression returns [Expression expr = null] : (bcE= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final Expression booleanExpression() throws RecognitionException {
        Expression expr =  null;


        Expression bcE =null;

        Expression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1896:2: (bcE= composedBooleanExpression |sbE= simpleBooleanExpression )
            int alt182=2;
            switch ( input.LA(1) ) {
            case TRUE:
                {
                int LA182_1 = input.LA(2);

                if ( (LA182_1==EQUAL||LA182_1==NOTEQUAL) ) {
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
                break;
            case FALSE:
                {
                int LA182_2 = input.LA(2);

                if ( (LA182_2==EQUAL||LA182_2==NOTEQUAL) ) {
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
                break;
            case Identifier:
                {
                int LA182_3 = input.LA(2);

                if ( (LA182_3==DOT||LA182_3==EQUAL||LA182_3==LPAREN||LA182_3==NOTEQUAL) ) {
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
                break;
            case BasicAnnotationType:
            case LPAREN:
            case XOR:
                {
                alt182=1;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1897:2: bcE= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10969);
                    bcE=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bcE;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1898:4: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10980);
                    sbE=simpleBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = sbE;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanExpression"



    // $ANTLR start "simpleBooleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1901:1: simpleBooleanExpression returns [Expression expr = null] : (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) ) ;
    public final Expression simpleBooleanExpression() throws RecognitionException {
        Expression expr =  null;


        Token variableId=null;
        BooleanLiteral lbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1902:2: ( (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1903:3: (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1903:3: (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1903:4: lbE= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression11005);
                    lbE=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = lbE;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1904:4: {...}? (variableId= Identifier )
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1904:57: (variableId= Identifier )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1904:58: variableId= Identifier
                    {
                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression11018); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanVariableReference(variableId);}

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanExpression(expr);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanExpression"



    // $ANTLR start "composedBooleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1911:1: composedBooleanExpression returns [Expression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final Expression composedBooleanExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e2 =null;

        Expression bte =null;

        Expression bne =null;

        Expression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1912:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
            int alt184=4;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==TRUE) && (synpred32_TextMarkerParser())) {
                alt184=1;
            }
            else if ( (LA184_0==FALSE) && (synpred32_TextMarkerParser())) {
                alt184=1;
            }
            else if ( (LA184_0==Identifier) ) {
                int LA184_3 = input.LA(2);

                if ( (((synpred32_TextMarkerParser()&&synpred32_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt184=1;
                }
                else if ( (synpred33_TextMarkerParser()) ) {
                    alt184=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                    alt184=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 184, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA184_0==BasicAnnotationType) && (synpred33_TextMarkerParser())) {
                alt184=2;
            }
            else if ( (LA184_0==LPAREN) && (synpred34_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1913:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression11064);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1914:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression11084);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1915:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression11103);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1916:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression11113);
                    e1=booleanFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "composedBooleanExpression"



    // $ANTLR start "booleanFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1920:1: booleanFunction returns [Expression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final Expression booleanFunction() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;

        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1922:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==XOR) ) {
                alt185=1;
            }
            else if ( (LA185_0==Identifier) && (synpred35_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1923:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1923:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1923:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction11138); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction11140); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11146);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction11148); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11154);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction11156); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1925:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction11178);
                    e=externalBooleanFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;

            }
        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanFunction"



    // $ANTLR start "externalBooleanFunction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1929:1: externalBooleanFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalBooleanFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1930:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1931:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalBooleanFunction", "isVariableOfType(input.LT(1).getText(), \"BOOLEANFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction11204); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction11207); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction11214);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction11218); if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            		expr = external.createExternalBooleanFunction(id, args);
            	}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "externalBooleanFunction"



    // $ANTLR start "booleanCompare"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1941:1: booleanCompare returns [Expression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final Expression booleanCompare() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1942:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1943:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1943:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1943:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare11243);
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


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare11261);
            e2=booleanExpression();

            state._fsp--;
            if (state.failed) return expr;

            }


            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanCompare"



    // $ANTLR start "literalBooleanExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1948:1: literalBooleanExpression returns [BooleanLiteral expr = null] : (value= TRUE |value= FALSE ) ;
    public final BooleanLiteral literalBooleanExpression() throws RecognitionException {
        BooleanLiteral expr =  null;


        Token value=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1949:2: ( (value= TRUE |value= FALSE ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1950:2: (value= TRUE |value= FALSE )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1950:2: (value= TRUE |value= FALSE )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1950:3: value= TRUE
                    {
                    value=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression11288); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1951:4: value= FALSE
                    {
                    value=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression11298); if (state.failed) return expr;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleBooleanExpression(value);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "literalBooleanExpression"



    // $ANTLR start "booleanTypeExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1956:1: booleanTypeExpression returns [Expression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final Expression booleanTypeExpression() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1957:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1958:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11325);
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


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11345);
            e2=typeExpression();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanTypeExpression"



    // $ANTLR start "booleanNumberExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1966:1: booleanNumberExpression returns [Expression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final Expression booleanNumberExpression() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1967:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1968:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression11368); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11375);
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


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11411);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression11414); if (state.failed) return expr;

            if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return expr;
    }
    // $ANTLR end "booleanNumberExpression"



    // $ANTLR start "genericVariableReference"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1976:1: genericVariableReference returns [Expression varRef] : id= Identifier ;
    public final Expression genericVariableReference() throws RecognitionException {
        Expression varRef = null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1977:3: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1978:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericVariableReference11434); if (state.failed) return varRef;

            if ( state.backtracking==0 ) {return ExpressionFactory.createGenericVariableReference(id);}

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}

        finally {
        	// do for sure before leaving
        }
        return varRef;
    }
    // $ANTLR end "genericVariableReference"

    // $ANTLR start synpred1_TextMarkerParser
    public final void synpred1_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:595:3: ( ruleElementType VBAR )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:595:4: ruleElementType VBAR
        {
        pushFollow(FOLLOW_ruleElementType_in_synpred1_TextMarkerParser1969);
        ruleElementType();

        state._fsp--;
        if (state.failed) return ;

        match(input,VBAR,FOLLOW_VBAR_in_synpred1_TextMarkerParser1971); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_TextMarkerParser

    // $ANTLR start synpred2_TextMarkerParser
    public final void synpred2_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:598:4: ( ruleElements )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:598:5: ruleElements
        {
        pushFollow(FOLLOW_ruleElements_in_synpred2_TextMarkerParser2013);
        ruleElements();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_TextMarkerParser

    // $ANTLR start synpred5_TextMarkerParser
    public final void synpred5_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:638:2: ( booleanListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:638:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred5_TextMarkerParser2372);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_TextMarkerParser

    // $ANTLR start synpred6_TextMarkerParser
    public final void synpred6_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:4: ( intListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred6_TextMarkerParser2388);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred6_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:640:4: ( doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:640:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred7_TextMarkerParser2404);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:641:4: ( floatListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:641:5: floatListExpression
        {
        pushFollow(FOLLOW_floatListExpression_in_synpred8_TextMarkerParser2420);
        floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred9_TextMarkerParser
    public final void synpred9_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:642:4: ( stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:642:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred9_TextMarkerParser2436);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_TextMarkerParser

    // $ANTLR start synpred10_TextMarkerParser
    public final void synpred10_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:643:4: ( typeListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:643:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred10_TextMarkerParser2452);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_TextMarkerParser

    // $ANTLR start synpred11_TextMarkerParser
    public final void synpred11_TextMarkerParser_fragment() throws RecognitionException {
        Expression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:685:2: (e1= doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:685:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred11_TextMarkerParser2665);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {
        Expression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:687:2: (e1= floatListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:687:3: e1= floatListExpression
        {
        pushFollow(FOLLOW_floatListExpression_in_synpred12_TextMarkerParser2686);
        e1=floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {
        TextMarkerCondition c =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:855:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:855:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred14_TextMarkerParser3636);
        c=externalCondition();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {
        Token name=null;
        Expression type =null;

        Expression a =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:916:6: (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:916:6: name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        name=(Token)match(input,COUNT,FOLLOW_COUNT_in_synpred15_TextMarkerParser4037); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred15_TextMarkerParser4039); if (state.failed) return ;

        pushFollow(FOLLOW_listExpression_in_synpred15_TextMarkerParser4045);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4060); if (state.failed) return ;

        pushFollow(FOLLOW_argument_in_synpred15_TextMarkerParser4066);
        a=argument();

        state._fsp--;
        if (state.failed) return ;

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:920:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:920:7: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4082); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred15_TextMarkerParser4088);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4090); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred15_TextMarkerParser4096);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:922:6: ( COMMA var= numberVariable )?
        int alt188=2;
        int LA188_0 = input.LA(1);

        if ( (LA188_0==COMMA) ) {
            alt188=1;
        }
        switch (alt188) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:922:7: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4114); if (state.failed) return ;

                pushFollow(FOLLOW_numberVariable_in_synpred15_TextMarkerParser4120);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        match(input,RPAREN,FOLLOW_RPAREN_in_synpred15_TextMarkerParser4136); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred16_TextMarkerParser
    public final void synpred16_TextMarkerParser_fragment() throws RecognitionException {
        Expression list2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:27: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:28: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred16_TextMarkerParser4501);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {
        TextMarkerAction a =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1149:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1149:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred17_TextMarkerParser6223);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_TextMarkerParser

    // $ANTLR start synpred18_TextMarkerParser
    public final void synpred18_TextMarkerParser_fragment() throws RecognitionException {
        Expression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1187:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser6368); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser6374);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {
        Expression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1226:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred19_TextMarkerParser6694); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred19_TextMarkerParser6700);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred23_TextMarkerParser
    public final void synpred23_TextMarkerParser_fragment() throws RecognitionException {
        Expression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:29: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:30: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred23_TextMarkerParser7528);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred23_TextMarkerParser

    // $ANTLR start synpred24_TextMarkerParser
    public final void synpred24_TextMarkerParser_fragment() throws RecognitionException {
        Expression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:92: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:93: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred24_TextMarkerParser7548);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred24_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {
        Expression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1656:3: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1656:3: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred26_TextMarkerParser9800);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {
        Expression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1657:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1657:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred27_TextMarkerParser9811);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {
        Expression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1658:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1658:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred28_TextMarkerParser9822);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred29_TextMarkerParser10555);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1851:5: ( listExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1851:6: listExpression
        {
        pushFollow(FOLLOW_listExpression_in_synpred30_TextMarkerParser10756);
        listExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1865:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1865:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred31_TextMarkerParser10849);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        Expression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1913:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1913:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred32_TextMarkerParser11056);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred33_TextMarkerParser
    public final void synpred33_TextMarkerParser_fragment() throws RecognitionException {
        Expression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1914:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1914:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred33_TextMarkerParser11076);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred33_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {
        Expression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1915:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1915:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred34_TextMarkerParser11095);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred35_TextMarkerParser
    public final void synpred35_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1925:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1925:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred35_TextMarkerParser11170);
        e=externalBooleanFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred35_TextMarkerParser

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
    public final boolean synpred6_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_TextMarkerParser_fragment(); // can never throw exception
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
    public final boolean synpred5_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_TextMarkerParser_fragment(); // can never throw exception
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
        "\1\45\1\22\1\32\2\111\1\32\1\uffff\1\47\1\uffff";
    static final String DFA31_maxS =
        "\1\45\1\111\1\175\2\111\1\175\1\uffff\1\111\1\uffff";
    static final String DFA31_acceptS =
        "\6\uffff\1\1\1\uffff\1\2";
    static final String DFA31_specialS =
        "\11\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\1",
            "\1\3\66\uffff\1\2",
            "\1\6\14\uffff\1\4\41\uffff\1\5\63\uffff\1\6",
            "\1\5",
            "\1\7",
            "\1\6\72\uffff\1\10\47\uffff\1\6",
            "",
            "\1\4\41\uffff\1\5",
            ""
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
            return "458:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
        }
    }
 

    public static final BitSet FOLLOW_packageDeclaration_in_file_input73 = new BitSet(new long[]{0x06041120001E8000L,0x0000000000200680L,0x00000000018380F0L});
    public static final BitSet FOLLOW_globalStatements_in_file_input87 = new BitSet(new long[]{0x06001120001E8000L,0x0000000000200680L,0x00000000018180D0L});
    public static final BitSet FOLLOW_statements_in_file_input94 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file_input100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PackageString_in_packageDeclaration121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedId_in_packageDeclaration132 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements163 = new BitSet(new long[]{0x06001120001E8002L,0x0000000000200680L,0x00000000018180D0L});
    public static final BitSet FOLLOW_globalStatement_in_globalStatements189 = new BitSet(new long[]{0x0004000000000002L,0x0000000000000000L,0x0000000000020020L});
    public static final BitSet FOLLOW_importStatement_in_globalStatement213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_statement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockDeclaration_in_statement261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStatement_in_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement303 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement315 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_importStatement323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement345 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_importStatement353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement375 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_importStatement383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntString_in_variableDeclaration410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration416 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration428 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration438 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration444 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration463 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration469 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration483 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration494 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration500 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatString_in_variableDeclaration519 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration525 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration539 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration550 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration556 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration581 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration595 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration606 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration612 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration631 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration637 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration651 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration668 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration687 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration693 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration707 = new BitSet(new long[]{0x0000000004000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration718 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration724 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration763 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration766 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000200L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration772 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration816 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration819 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000200L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration825 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration870 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration873 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration879 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration918 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration924 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration933 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration979 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration982 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration988 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATLIST_in_variableDeclaration1029 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1035 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration1044 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration1091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1097 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1100 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration1106 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration1153 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1159 = new BitSet(new long[]{0x0000000000000800L,0x2000000000000000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration1168 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1241 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotationType_in_declaration1247 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1257 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1279 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1311 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotationType_in_declaration1315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1329 = new BitSet(new long[]{0x0400100000140000L,0x0000000000000600L,0x0000000000000080L});
    public static final BitSet FOLLOW_annotationType_in_declaration1344 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_StringString_in_declaration1357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_FloatString_in_declaration1382 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_IntString_in_declaration1396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1428 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1440 = new BitSet(new long[]{0x0400100000140000L,0x0000000000000600L,0x0000000000000080L});
    public static final BitSet FOLLOW_annotationType_in_declaration1455 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_StringString_in_declaration1468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1481 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_FloatString_in_declaration1493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_IntString_in_declaration1507 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1519 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_declaration1538 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1546 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_AutomataBlockString_in_blockDeclaration1618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1622 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1629 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1637 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1652 = new BitSet(new long[]{0x06001120001E8000L,0x0008000000200680L,0x00000000018180D0L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1658 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1694 = new BitSet(new long[]{0x0000000000000000L,0x000240000000C000L,0x0000000000000004L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1713 = new BitSet(new long[]{0x0040200AC0010240L,0x10189C5E00002360L,0x0000000000400C09L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1719 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1723 = new BitSet(new long[]{0xE182824422A00420L,0x43600001FE080201L,0x00000000000C3000L});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1729 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithoutCA1777 = new BitSet(new long[]{0x0000000000000002L,0x0002400000004000L,0x0000000000000004L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithoutCA1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1825 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1855 = new BitSet(new long[]{0x0000000000040002L,0x0000000000200200L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1864 = new BitSet(new long[]{0x0000000000040002L,0x0000000000200200L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleElementType_in_blockRuleElement1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed1961 = new BitSet(new long[]{0x0000000000040000L,0x0000000000200200L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed1979 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementComposed1985 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed1991 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementComposed1997 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed2003 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed2020 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed2026 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000004L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed2032 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed2036 = new BitSet(new long[]{0x0040200AC0010240L,0x10189C5E00002360L,0x0000000000400C09L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed2042 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2046 = new BitSet(new long[]{0xE182824422A00420L,0x43600001FE080201L,0x00000000000C3000L});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2052 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2090 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000004L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2096 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2109 = new BitSet(new long[]{0x0040200AC0010240L,0x10189C5E00002360L,0x0000000000400C09L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2115 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2119 = new BitSet(new long[]{0xE182824422A00420L,0x43600001FE080201L,0x00000000000C3000L});
    public static final BitSet FOLLOW_actions_in_ruleElementType2125 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2186 = new BitSet(new long[]{0x0000000000000002L,0x000240000000C000L,0x0000000000000004L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2192 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2205 = new BitSet(new long[]{0x0040200AC0010240L,0x10189C5E00002360L,0x0000000000400C09L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2211 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2215 = new BitSet(new long[]{0xE182824422A00420L,0x43600001FE080201L,0x00000000000C3000L});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2221 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2283 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_conditions2288 = new BitSet(new long[]{0x0040200AC0010240L,0x10109C5E00002360L,0x0000000000400809L});
    public static final BitSet FOLLOW_condition_in_conditions2294 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_action_in_actions2331 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_actions2336 = new BitSet(new long[]{0xE182824422A00420L,0x43600001FE080201L,0x00000000000C3000L});
    public static final BitSet FOLLOW_action_in_actions2342 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_listExpression2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2505 = new BitSet(new long[]{0x0020000000000000L,0x0008000000000200L,0x0000000000004000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2512 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2517 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000200L,0x0000000000004000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2523 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2595 = new BitSet(new long[]{0x1000040000000000L,0x0008000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2602 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2607 = new BitSet(new long[]{0x1000040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2613 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_numberListExpression2694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2750 = new BitSet(new long[]{0x1000040000000000L,0x0008000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2757 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2762 = new BitSet(new long[]{0x1000040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2768 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleFloatListExpression_in_floatListExpression2818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleFloatListExpression2839 = new BitSet(new long[]{0x1000040000000000L,0x0008000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2846 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleFloatListExpression2851 = new BitSet(new long[]{0x1000040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2857 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleFloatListExpression2866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleFloatListExpression2883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression2929 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2936 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression2941 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2947 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression2956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression2998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression3019 = new BitSet(new long[]{0x0000000000040000L,0x0008000000000200L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3026 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression3031 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3037 = new BitSet(new long[]{0x0000000004000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression3046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression3063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression3089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression3100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction3134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction3159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3163 = new BitSet(new long[]{0x0000000000000000L,0x0400000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3170 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3275 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3293 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3311 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3326 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3332 = new BitSet(new long[]{0x0000000004000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3335 = new BitSet(new long[]{0x1001040100000000L,0x8004000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3342 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3348 = new BitSet(new long[]{0x0000000000000002L,0x0002000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3691 = new BitSet(new long[]{0x0000000000000000L,0x0400000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3701 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3738 = new BitSet(new long[]{0x0040200AC0010240L,0x10109C5E00002360L,0x0000000000400809L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3744 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3806 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3813 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3821 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3823 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_conditionContains3829 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3838 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3844 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3846 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3852 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3855 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3861 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount3914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount3916 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount3922 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3936 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3942 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3944 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3950 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount3971 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount3986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4037 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount4045 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4060 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_conditionCount4066 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4082 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4088 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4090 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4096 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4120 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4152 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4154 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount4160 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4174 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4180 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4182 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4188 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4203 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4209 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4266 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4268 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4274 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4288 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4294 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4296 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4302 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4318 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4324 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4380 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4386 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4400 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4406 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4408 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4414 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4429 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4435 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4491 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4493 = new BitSet(new long[]{0x0000000000000000L,0x0800000000008200L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4508 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4516 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4525 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4531 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4534 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4540 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4602 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4604 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4610 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4659 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4661 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4667 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4669 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4675 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4677 = new BitSet(new long[]{0x0040200AC0010240L,0x10109C5E00002360L,0x0000000000400809L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4683 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4730 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4732 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4738 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4740 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4746 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4748 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4754 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4762 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4768 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4771 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4777 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4830 = new BitSet(new long[]{0x0040200AC0010240L,0x10109C5E00002360L,0x0000000000400809L});
    public static final BitSet FOLLOW_condition_in_conditionNot4836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4849 = new BitSet(new long[]{0x0040200AC0010240L,0x10109C5E00002360L,0x0000000000400809L});
    public static final BitSet FOLLOW_condition_in_conditionNot4855 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4899 = new BitSet(new long[]{0x0040200AC0010240L,0x10109C5E00002360L,0x0000000000400809L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4905 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4948 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4955 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4961 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq5011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq5013 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq5020 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq5026 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq5043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition5080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition5082 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition5088 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition5090 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition5096 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition5109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp5137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp5139 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp5145 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp5148 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp5154 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp5172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore5206 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore5208 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5214 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5217 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5223 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5232 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore5238 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore5255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote5287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote5289 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5295 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote5297 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5303 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote5316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf5350 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf5352 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf5358 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf5371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5412 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5418 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5420 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5426 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_genericVariableReference_in_conditionParse5481 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5524 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5526 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5533 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5539 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5582 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5584 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5591 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5597 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5642 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5649 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5655 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5704 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5711 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5717 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5766 = new BitSet(new long[]{0x0000000000040000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5773 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5779 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5834 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5837 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5843 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5845 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5851 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5856 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5862 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action6004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action6013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action6022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action6031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action6040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action6049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action6058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action6067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action6076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action6085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action6094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action6104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action6113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action6122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action6131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action6140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action6149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action6158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action6168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action6177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action6186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action6195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionConfigure_in_action6204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDynamicAnchoring_in_action6213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action6231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction6272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction6276 = new BitSet(new long[]{0x0000000000000000L,0x0400000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction6285 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction6290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate6326 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate6328 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate6334 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6341 = new BitSet(new long[]{0x1001040100000000L,0x8480000200300200L,0x0000000000000240L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6362 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6379 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6385 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6391 = new BitSet(new long[]{0x0000000000000000L,0x0480000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6409 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6411 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionCreate6417 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6427 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6433 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6435 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionCreate6441 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6507 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6509 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6520 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6522 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6533 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6535 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000200L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6545 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6553 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6564 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6566 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6572 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6582 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6588 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6590 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6596 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6654 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6656 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6662 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6676 = new BitSet(new long[]{0x1001040100000000L,0x8480000200300200L,0x0000000000000240L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6688 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6704 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6710 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6717 = new BitSet(new long[]{0x0000000000000000L,0x0480000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6730 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6732 = new BitSet(new long[]{0x1001040100000000L,0x8000000200308200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6739 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6747 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6758 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6764 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6766 = new BitSet(new long[]{0x1001040100000000L,0x8000000200308200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6773 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6781 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6850 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6856 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6874 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6880 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6882 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionFill6892 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6951 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6953 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6959 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6973 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6984 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6998 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor7008 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor7022 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor7032 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor7048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel7080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog7126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog7128 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog7134 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionLog7137 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog7143 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog7159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark7197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark7199 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark7210 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMark7228 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark7244 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark7266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand7303 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand7305 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand7316 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand7334 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand7350 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand7372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore7409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore7411 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7417 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7419 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore7425 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7443 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7459 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore7481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7518 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7520 = new BitSet(new long[]{0x1001040100040000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7537 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7539 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7557 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7575 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7591 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7647 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7653 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7666 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000200L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7672 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7686 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7692 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7695 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7701 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7751 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7753 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7759 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7807 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7813 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7872 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7875 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7881 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7897 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7903 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType7970 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType7973 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7979 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType7995 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8001 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType8018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall8067 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall8073 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionCall8095 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall8109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONFIGURE_in_actionConfigure8144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionConfigure8150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionConfigure8172 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure8193 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure8199 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure8201 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionConfigure8207 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure8217 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure8223 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure8225 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionConfigure8231 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionConfigure8255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec8290 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec8296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionExec8314 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExec8330 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec8336 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec8352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign8394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign8396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8407 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8425 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionAssign8431 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8478 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8484 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8498 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8504 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8548 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8554 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8567 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8573 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring8616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDynamicAnchoring8618 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionDynamicAnchoring8624 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8639 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8645 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring8659 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring8665 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDynamicAnchoring8682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8712 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8714 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8720 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8764 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8770 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8784 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8790 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8837 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8839 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8845 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8898 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8912 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8915 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8930 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8943 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8949 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8953 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8968 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8982 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000200L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8988 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9004 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie9010 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9017 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie9023 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9030 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie9036 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9043 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie9049 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9056 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie9062 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie9085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd9123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd9125 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd9131 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd9145 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionAdd9151 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd9168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove9202 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove9204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove9210 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove9224 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_actionRemove9230 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove9247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9277 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate9279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate9285 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate9298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge9335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge9337 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge9343 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge9363 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9383 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9399 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge9416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet9445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet9447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_actionGet9453 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9466 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_actionGet9472 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9485 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet9491 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet9504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList9534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList9536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList9542 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList9555 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList9561 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList9574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText9611 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText9613 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText9624 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText9636 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText9642 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText9664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear9697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear9699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_listVariable_in_actionClear9705 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear9718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList9740 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9746 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList9751 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9757 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList9763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument9800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument9811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9870 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9893 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9925 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedId9938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9948 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference9983 = new BitSet(new long[]{0x0000008000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_set_in_dottedComponentReference9996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference10012 = new BitSet(new long[]{0x0000008000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration10046 = new BitSet(new long[]{0x0000008000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_set_in_dottedComponentDeclaration10059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration10075 = new BitSet(new long[]{0x0000008000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_annotationTypeVariableReference_in_annotationType10109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType10120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationTypeVariableReference10149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression10173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression10186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression10210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression10223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression10247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10273 = new BitSet(new long[]{0x0000000000000002L,0x0000400200000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression10282 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10292 = new BitSet(new long[]{0x0000000000000002L,0x0000400200000000L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10321 = new BitSet(new long[]{0x0000000000000002L,0x0000200000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression10330 = new BitSet(new long[]{0x1000040000000000L,0x0000000200200200L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10348 = new BitSet(new long[]{0x0000000000000002L,0x0000200000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression10364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar10388 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_numberExpressionInPar10394 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar10400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression10432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10447 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression10455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10469 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression10492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction10517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction10539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction10563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction10588 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction10592 = new BitSet(new long[]{0x0000000000000000L,0x0400000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction10599 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction10602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression10697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10710 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression10716 = new BitSet(new long[]{0x0020000000040000L,0x0000000000208200L,0x0000000000004040L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10723 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression10735 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression10747 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression10764 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression10776 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction10813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction10815 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_in_stringFunction10821 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction10824 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000200L,0x0000000000000040L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction10830 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction10835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction10857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction10882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction10886 = new BitSet(new long[]{0x0000000000000000L,0x0400000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction10893 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction10896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression10921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression10936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression11005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression11018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression11064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression11084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression11103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression11113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction11138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction11140 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11146 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction11148 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11154 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction11156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction11178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction11204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction11207 = new BitSet(new long[]{0x0000000000000000L,0x0400000000200000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction11214 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction11218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare11243 = new BitSet(new long[]{0x0000400000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_set_in_booleanCompare11249 = new BitSet(new long[]{0x0020000000040000L,0x0000000000200200L,0x0000000004004000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare11261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression11288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression11298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11325 = new BitSet(new long[]{0x0000400000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression11332 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression11368 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11375 = new BitSet(new long[]{0x0000400000000000L,0x0000002000030006L});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression11382 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11411 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression11414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_genericVariableReference11434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_synpred1_TextMarkerParser1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_VBAR_in_synpred1_TextMarkerParser1971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_synpred2_TextMarkerParser2013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred5_TextMarkerParser2372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred6_TextMarkerParser2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred7_TextMarkerParser2404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred8_TextMarkerParser2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred9_TextMarkerParser2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred10_TextMarkerParser2452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred11_TextMarkerParser2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred12_TextMarkerParser2686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred14_TextMarkerParser3636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred15_TextMarkerParser4037 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred15_TextMarkerParser4039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008200L});
    public static final BitSet FOLLOW_listExpression_in_synpred15_TextMarkerParser4045 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4060 = new BitSet(new long[]{0x1021040100040000L,0x8080000200300200L,0x0000000004004240L});
    public static final BitSet FOLLOW_argument_in_synpred15_TextMarkerParser4066 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4082 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_synpred15_TextMarkerParser4088 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4090 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_synpred15_TextMarkerParser4096 = new BitSet(new long[]{0x0000000004000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberVariable_in_synpred15_TextMarkerParser4120 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred15_TextMarkerParser4136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred16_TextMarkerParser4501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred17_TextMarkerParser6223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser6368 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser6374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred19_TextMarkerParser6694 = new BitSet(new long[]{0x1001040100000000L,0x8000000200300200L,0x0000000000000200L});
    public static final BitSet FOLLOW_numberExpression_in_synpred19_TextMarkerParser6700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred23_TextMarkerParser7528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred24_TextMarkerParser7548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred26_TextMarkerParser9800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred27_TextMarkerParser9811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred28_TextMarkerParser9822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred29_TextMarkerParser10555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_listExpression_in_synpred30_TextMarkerParser10756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred31_TextMarkerParser10849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred32_TextMarkerParser11056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred33_TextMarkerParser11076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred34_TextMarkerParser11095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred35_TextMarkerParser11170 = new BitSet(new long[]{0x0000000000000002L});

}