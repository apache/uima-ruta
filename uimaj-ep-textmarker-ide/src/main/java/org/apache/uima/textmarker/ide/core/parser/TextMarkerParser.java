// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g 2011-09-26 13:38:53

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

                if ( ((LA1_0 >= DECLARE && LA1_0 <= WORDTABLE)||LA1_0==BasicAnnotationType||(LA1_0 >= BlockString && LA1_0 <= BooleanString)||(LA1_0 >= CONDITION && LA1_0 <= TYPELIST)||LA1_0==StringLiteral||(LA1_0 >= Identifier && LA1_0 <= LPAREN)) ) {
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

                if ( ((LA2_0 >= ScriptString && LA2_0 <= EngineString)||LA2_0==TypeSystemString) ) {
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
            case BlockString:
            case AutomataBlockString:
                {
                alt3=3;
                }
                break;
            case BasicAnnotationType:
            case StringLiteral:
            case Identifier:
            case LPAREN:
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:324:1: variableDeclaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI |type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI |type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI |type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI |type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI |type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI |stmt= conditionDeclaration |stmt= actionDeclaration );
    public final List<Statement> variableDeclaration() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        Token type=null;
        Token id=null;
        Expression init =null;

        Expression list =null;

        Expression table =null;

        Statement stmt =null;



        	List decls = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:328:2: (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI |type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI |type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI |type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI |type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI |type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI |stmt= conditionDeclaration |stmt= actionDeclaration )
            int alt22=14;
            switch ( input.LA(1) ) {
            case IntString:
                {
                alt22=1;
                }
                break;
            case DoubleString:
                {
                alt22=2;
                }
                break;
            case StringString:
                {
                alt22=3;
                }
                break;
            case BooleanString:
                {
                alt22=4;
                }
                break;
            case TypeString:
                {
                alt22=5;
                }
                break;
            case WORDLIST:
                {
                alt22=6;
                }
                break;
            case WORDTABLE:
                {
                alt22=7;
                }
                break;
            case BOOLEANLIST:
                {
                alt22=8;
                }
                break;
            case INTLIST:
                {
                alt22=9;
                }
                break;
            case DOUBLELIST:
                {
                alt22=10;
                }
                break;
            case STRINGLIST:
                {
                alt22=11;
                }
                break;
            case TYPELIST:
                {
                alt22=12;
                }
                break;
            case CONDITION:
                {
                alt22=13;
                }
                break;
            case ACTION:
                {
                alt22=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmts;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:343:2: type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration519); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration525); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));}

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

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:345:7: ( ASSIGN_EQUAL init= stringExpression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==ASSIGN_EQUAL) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:345:8: ASSIGN_EQUAL init= stringExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration550); if (state.failed) return stmts;

                            pushFollow(FOLLOW_stringExpression_in_variableDeclaration556);
                            init=stringExpression();

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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:350:2: type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration575); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration581); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));}

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

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:352:7: ( ASSIGN_EQUAL init= booleanExpression )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==ASSIGN_EQUAL) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:352:8: ASSIGN_EQUAL init= booleanExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration606); if (state.failed) return stmts;

                            pushFollow(FOLLOW_booleanExpression_in_variableDeclaration612);
                            init=booleanExpression();

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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:357:2: type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration631); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration637); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}

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

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:359:7: ( ASSIGN_EQUAL init= annotationType )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==ASSIGN_EQUAL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:359:8: ASSIGN_EQUAL init= annotationType
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662); if (state.failed) return stmts;

                            pushFollow(FOLLOW_annotationType_in_variableDeclaration668);
                            init=annotationType();

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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:364:9: type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration701); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration707); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:364:41: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:364:42: ASSIGN_EQUAL list= wordListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration710); if (state.failed) return stmts;

                            pushFollow(FOLLOW_wordListExpression_in_variableDeclaration716);
                            list=wordListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration720); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createListVariable(id,type,list));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:371:9: type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration754); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration760); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:371:42: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:371:43: ASSIGN_EQUAL table= wordTableExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration763); if (state.failed) return stmts;

                            pushFollow(FOLLOW_wordTableExpression_in_variableDeclaration769);
                            table=wordTableExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration774); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createTableVariable(id,type,table));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, table));
                            }

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:378:9: type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration808); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration814); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:378:44: ( ASSIGN_EQUAL list= booleanListExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:378:45: ASSIGN_EQUAL list= booleanListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration817); if (state.failed) return stmts;

                            pushFollow(FOLLOW_booleanListExpression_in_variableDeclaration823);
                            list=booleanListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration828); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_BL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:385:9: type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration862); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration868); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:385:40: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:385:41: ASSIGN_EQUAL list= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration871); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration877);
                            list=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration882); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_NL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:392:9: type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration917); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration923); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:392:43: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:392:44: ASSIGN_EQUAL list= numberListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration926); if (state.failed) return stmts;

                            pushFollow(FOLLOW_numberListExpression_in_variableDeclaration932);
                            list=numberListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration937); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_NL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:399:9: type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration979); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration985); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:399:43: ( ASSIGN_EQUAL list= stringListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:399:44: ASSIGN_EQUAL list= stringListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration988); if (state.failed) return stmts;

                            pushFollow(FOLLOW_stringListExpression_in_variableDeclaration994);
                            list=stringListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration999); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_SL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:406:9: type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration1041); if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration1047); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:406:41: ( ASSIGN_EQUAL list= typeListExpression )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==ASSIGN_EQUAL) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:406:42: ASSIGN_EQUAL list= typeListExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1050); if (state.failed) return stmts;

                            pushFollow(FOLLOW_typeListExpression_in_variableDeclaration1056);
                            list=typeListExpression();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration1061); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                            addVariable(id.getText(), type.getText());
                            decls.add(StatementFactory.createVarListVariable(id,type,list, TMTypeConstants.TM_TYPE_TL));
                            stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
                            }

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:413:2: stmt= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration1088);
                    stmt=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.add(stmt);}

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:415:2: stmt= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration1100);
                    stmt=actionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.add(stmt);}

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



    // $ANTLR start "conditionDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:418:1: conditionDeclaration returns [Statement stmt = null] : declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final Statement conditionDeclaration() throws RecognitionException {
        Statement stmt =  null;


        Token declareToken=null;
        Token id=null;
        List<TextMarkerCondition> cons =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:419:5: (declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:420:5: declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
            {
            declareToken=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_conditionDeclaration1128); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionDeclaration1134); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1142); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionDeclaration1149); if (state.failed) return stmt;

            pushFollow(FOLLOW_conditions_in_conditionDeclaration1155);
            cons=conditions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionDeclaration1157); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_conditionDeclaration1159); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {stmt = StatementFactory.createComposedVariableConditionDeclaration(id, cons);}

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
    // $ANTLR end "conditionDeclaration"



    // $ANTLR start "actionDeclaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:427:1: actionDeclaration returns [Statement stmt = null] : declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final Statement actionDeclaration() throws RecognitionException {
        Statement stmt =  null;


        Token declareToken=null;
        Token id=null;
        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:428:5: (declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:429:5: declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
            {
            declareToken=(Token)match(input,ACTION,FOLLOW_ACTION_in_actionDeclaration1195); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionDeclaration1201); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1209); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDeclaration1215); if (state.failed) return stmt;

            pushFollow(FOLLOW_actions_in_actionDeclaration1221);
            a=actions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDeclaration1223); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_actionDeclaration1225); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {stmt = StatementFactory.createComposedVariableActionDeclaration(id, a);}

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
    // $ANTLR end "actionDeclaration"



    // $ANTLR start "declaration"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:435:1: declaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
    public final List<Statement> declaration() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();


        Token declareToken=null;
        Token id=null;
        Token end=null;
        Token obj2=null;
        Token obj3=null;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:442:2: ( (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:444:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:444:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:444:3: declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1260); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:444:35: (lazyParent= annotationType )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==Identifier) ) {
                        int LA23_1 = input.LA(2);

                        if ( (LA23_1==Identifier||LA23_1==DOT) ) {
                            alt23=1;
                        }
                    }
                    else if ( (LA23_0==BasicAnnotationType) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:444:35: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1266);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1276); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    if ( state.backtracking==0 ) {addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), lazyParent == null ? null : lazyParent.toString());
                    			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:448:3: ( COMMA id= Identifier )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:448:4: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1288); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1298); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    	    if ( state.backtracking==0 ) {addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(),  lazyParent == null ? null : lazyParent.toString()); 
                    	    			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    end=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration1317); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, lazyParent);
                    		 stmts.add(stmt);
                    		 }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:458:2: declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1330); if (state.failed) return stmts;

                    pushFollow(FOLLOW_annotationType_in_declaration1334);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1341); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:460:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:460:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1348); if (state.failed) return stmts;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:461:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString )
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
                        if (state.backtracking>0) {state.failed=true; return stmts;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 0, input);

                        throw nvae;

                    }

                    switch (alt25) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:462:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1363);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1);}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:463:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1376); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2);}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:464:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1389); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3);}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:465:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1402); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4);}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:466:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1414); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5);}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1434); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {featureNames.add(fname);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:470:4: ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:471:4: COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1446); if (state.failed) return stmts;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:472:4: (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString )
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
                    	        if (state.backtracking>0) {state.failed=true; return stmts;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 26, 0, input);

                    	        throw nvae;

                    	    }

                    	    switch (alt26) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:473:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1461);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:474:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1474); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:475:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1487); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:476:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1499); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:477:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1511); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj5);}

                    	            }
                    	            break;

                    	    }


                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1530); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {featureNames.add(fname);}

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1538); if (state.failed) return stmts;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1541); if (state.failed) return stmts;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:502:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : (declareToken= BlockString |declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:516:2: ( (declareToken= BlockString |declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:518:2: (declareToken= BlockString |declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:518:2: (declareToken= BlockString |declareToken= AutomataBlockString )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==BlockString) ) {
                alt29=1;
            }
            else if ( (LA29_0==AutomataBlockString) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return block;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:518:3: declareToken= BlockString
                    {
                    declareToken=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1602); if (state.failed) return block;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:518:32: declareToken= AutomataBlockString
                    {
                    declareToken=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_blockDeclaration1610); if (state.failed) return block;

                    }
                    break;

            }


            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1614); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1621); if (state.failed) return block;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            if ( state.backtracking==0 ) {
            		block = ScriptFactory.createScriptBlock(id, declareToken, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);
            		((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	}

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1629); if (state.failed) return block;

            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1636);
            re1=ruleElementWithCA();

            state._fsp--;
            if (state.failed) return block;

            if ( state.backtracking==0 ) {re = re1;}

            if ( state.backtracking==0 ) {ScriptFactory.finalizeScriptBlock(block, rc, re, body);}

            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1644); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_blockDeclaration1650);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1656); if (state.failed) return block;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:533:1: ruleElementWithCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Token end=null;
        Expression idRef =null;

        List<Expression> quantifier =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:534:5: (idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:535:5: idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1686);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:535:37: (quantifier= quantifierPart )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==LBRACK||LA30_0==PLUS||LA30_0==STAR||LA30_0==QUESTION) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:535:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1692);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);}

            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1705); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:536:18: (c= conditions )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==CONTAINS||(LA31_0 >= AND && LA31_0 <= PARSE)||(LA31_0 >= BEFORE && LA31_0 <= NOT)||LA31_0==SIZE||LA31_0==Identifier||LA31_0==MINUS) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:536:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1711);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:536:32: ( THEN a= actions )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==THEN) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:536:33: THEN a= actions
                    {
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1715); if (state.failed) return re;

                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1721);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1729); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:541:1: ruleElementWithoutCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? ;
    public final TextMarkerRuleElement ruleElementWithoutCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Expression idRef =null;

        List<Expression> quantifier =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:542:5: (idRef= typeExpression (quantifier= quantifierPart )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:543:5: idRef= typeExpression (quantifier= quantifierPart )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithoutCA1769);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:543:37: (quantifier= quantifierPart )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LBRACK||LA33_0==PLUS||LA33_0==STAR||LA33_0==QUESTION) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:543:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithoutCA1775);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:548:1: simpleStatement returns [TextMarkerRule stmt = null] : elements= ruleElements SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;


        List<Expression> elements =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:549:2: (elements= ruleElements SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:550:2: elements= ruleElements SEMI
            {
            pushFollow(FOLLOW_ruleElements_in_simpleStatement1817);
            elements=ruleElements();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {stmt = ScriptFactory.createRule(elements);}

            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1826); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:555:1: ruleElements returns [List<Expression> elements = new ArrayList<Expression>()] : re= ruleElement (re= ruleElement )* ;
    public final List<Expression> ruleElements() throws RecognitionException {
        List<Expression> elements =  new ArrayList<Expression>();


        Expression re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:556:2: (re= ruleElement (re= ruleElement )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:557:2: re= ruleElement (re= ruleElement )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1847);
            re=ruleElement();

            state._fsp--;
            if (state.failed) return elements;

            if ( state.backtracking==0 ) {if(re!=null) elements.add(re);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:557:52: (re= ruleElement )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==BasicAnnotationType||LA34_0==StringLiteral||(LA34_0 >= Identifier && LA34_0 <= LPAREN)) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:557:53: re= ruleElement
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1856);
            	    re=ruleElement();

            	    state._fsp--;
            	    if (state.failed) return elements;

            	    if ( state.backtracking==0 ) {if(re!=null) elements.add(re);}

            	    }
            	    break;

            	default :
            	    break loop34;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:560:1: blockRuleElement returns [TextMarkerRuleElement rElement = null] : re= ruleElementType ;
    public final TextMarkerRuleElement blockRuleElement() throws RecognitionException {
        TextMarkerRuleElement rElement =  null;


        TextMarkerRuleElement re =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:561:2: (re= ruleElementType )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:562:2: re= ruleElementType
            {
            pushFollow(FOLLOW_ruleElementType_in_blockRuleElement1883);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:565:1: ruleElement returns [Expression re = null] : (re1= ruleElementType |re2= ruleElementLiteral |re3= ruleElementComposed );
    public final Expression ruleElement() throws RecognitionException {
        Expression re =  null;


        TextMarkerRuleElement re1 =null;

        TextMarkerRuleElement re2 =null;

        ComposedRuleElement re3 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:566:2: (re1= ruleElementType |re2= ruleElementLiteral |re3= ruleElementComposed )
            int alt35=3;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA35_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "STRING"))))) ) {
                    alt35=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                    alt35=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt35=1;
                }
                break;
            case StringLiteral:
                {
                alt35=2;
                }
                break;
            case LPAREN:
                {
                alt35=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }

            switch (alt35) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:567:2: re1= ruleElementType
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1907);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:568:4: re2= ruleElementLiteral
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1918);
                    re2=ruleElementLiteral();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:569:4: re3= ruleElementComposed
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1929);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:572:1: ruleElementComposed returns [ComposedRuleElement re = null] : LPAREN ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:576:2: ( LPAREN ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:577:2: LPAREN ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements ) RPAREN (q= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed1953); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:579:2: ( ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )* | ( ruleElements )=>res= ruleElements )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==Identifier) ) {
                int LA37_1 = input.LA(2);

                if ( (synpred1_TextMarkerParser()) ) {
                    alt37=1;
                }
                else if ( (synpred2_TextMarkerParser()) ) {
                    alt37=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA37_0==BasicAnnotationType) ) {
                int LA37_2 = input.LA(2);

                if ( (synpred1_TextMarkerParser()) ) {
                    alt37=1;
                }
                else if ( (synpred2_TextMarkerParser()) ) {
                    alt37=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA37_0==StringLiteral) && (synpred2_TextMarkerParser())) {
                alt37=2;
            }
            else if ( (LA37_0==LPAREN) && (synpred2_TextMarkerParser())) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }
            switch (alt37) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:579:3: ( ruleElementType VBAR )=>re1= ruleElementType VBAR re2= ruleElementType ( VBAR re3= ruleElementType )*
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed1971);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {disjunctive = true; res = new ArrayList<Expression>(); res.add(re1);}

                    match(input,VBAR,FOLLOW_VBAR_in_ruleElementComposed1977); if (state.failed) return re;

                    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed1983);
                    re2=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {res.add(re2);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:581:2: ( VBAR re3= ruleElementType )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==VBAR) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:581:3: VBAR re3= ruleElementType
                    	    {
                    	    match(input,VBAR,FOLLOW_VBAR_in_ruleElementComposed1989); if (state.failed) return re;

                    	    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed1995);
                    	    re3=ruleElementType();

                    	    state._fsp--;
                    	    if (state.failed) return re;

                    	    if ( state.backtracking==0 ) {res.add(re3);}

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:582:4: ( ruleElements )=>res= ruleElements
                    {
                    pushFollow(FOLLOW_ruleElements_in_ruleElementComposed2012);
                    res=ruleElements();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed2018); if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:11: (q= quantifierPart )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LBRACK||LA38_0==PLUS||LA38_0==STAR||LA38_0==QUESTION) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:11: q= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed2024);
                    q=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:29: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LCURLY) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:30: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed2028); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:39: (c= conditions )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==CONTAINS||(LA39_0 >= AND && LA39_0 <= PARSE)||(LA39_0 >= BEFORE && LA39_0 <= NOT)||LA39_0==SIZE||LA39_0==Identifier||LA39_0==MINUS) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:39: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed2034);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:53: ( THEN a= actions )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==THEN) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:54: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementComposed2038); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementComposed2044);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementComposed2048); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:588:1: ruleElementType returns [TextMarkerRuleElement re = null] : ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Token end=null;
        Expression idRef =null;

        List<Expression> quantifier =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:589:5: ( ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:590:5: ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2082);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:590:55: (quantifier= quantifierPart )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LBRACK||LA42_0==PLUS||LA42_0==STAR||LA42_0==QUESTION) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:590:55: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2088);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:591:9: ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LCURLY) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:591:10: LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2101); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:591:19: (c= conditions )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==CONTAINS||(LA43_0 >= AND && LA43_0 <= PARSE)||(LA43_0 >= BEFORE && LA43_0 <= NOT)||LA43_0==SIZE||LA43_0==Identifier||LA43_0==MINUS) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:591:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2107);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:591:33: ( THEN a= actions )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==THEN) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:591:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2111); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementType2117);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2125); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:598:1: ruleElementLiteral returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral() throws RecognitionException {
        TextMarkerRuleElement re =  null;


        Token end=null;
        Expression idRef =null;

        List<Expression> quantifier =null;

        List<TextMarkerCondition> c =null;

        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:599:5: ( ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:5: ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2178);
            idRef=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:71: (quantifier= quantifierPart )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LBRACK||LA46_0==PLUS||LA46_0==STAR||LA46_0==QUESTION) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:600:71: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2184);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:601:9: ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LCURLY) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:601:10: LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2197); if (state.failed) return re;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:601:19: (c= conditions )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==CONTAINS||(LA47_0 >= AND && LA47_0 <= PARSE)||(LA47_0 >= BEFORE && LA47_0 <= NOT)||LA47_0==SIZE||LA47_0==Identifier||LA47_0==MINUS) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:601:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2203);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:601:33: ( THEN a= actions )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==THEN) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:601:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2207); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2213);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2221); if (state.failed) return re;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:608:1: conditions returns [List<TextMarkerCondition> conds = new ArrayList<TextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<TextMarkerCondition> conditions() throws RecognitionException {
        List<TextMarkerCondition> conds =  new ArrayList<TextMarkerCondition>();


        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:609:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:610:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2275);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;

            if ( state.backtracking==0 ) {conds.add(c);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:610:35: ( COMMA c= condition )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==COMMA) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:610:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2280); if (state.failed) return conds;

            	    pushFollow(FOLLOW_condition_in_conditions2286);
            	    c=condition();

            	    state._fsp--;
            	    if (state.failed) return conds;

            	    if ( state.backtracking==0 ) {conds.add(c);}

            	    }
            	    break;

            	default :
            	    break loop50;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:614:1: actions returns [List<TextMarkerAction> actions = new ArrayList<TextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<TextMarkerAction> actions() throws RecognitionException {
        List<TextMarkerAction> actions =  new ArrayList<TextMarkerAction>();


        TextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:615:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:616:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2323);
            a=action();

            state._fsp--;
            if (state.failed) return actions;

            if ( state.backtracking==0 ) {actions.add(a);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:616:34: ( COMMA a= action )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:616:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2328); if (state.failed) return actions;

            	    pushFollow(FOLLOW_action_in_actions2334);
            	    a=action();

            	    state._fsp--;
            	    if (state.failed) return actions;

            	    if ( state.backtracking==0 ) {actions.add(a);}

            	    }
            	    break;

            	default :
            	    break loop51;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:620:1: listExpression returns [Expression expr = null] : ( ( booleanListExpression )=>e= booleanListExpression | ( intListExpression )=>e= intListExpression | ( doubleListExpression )=>e= doubleListExpression | ( stringListExpression )=>e= stringListExpression | ( typeListExpression )=>e= typeListExpression );
    public final Expression listExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:621:2: ( ( booleanListExpression )=>e= booleanListExpression | ( intListExpression )=>e= intListExpression | ( doubleListExpression )=>e= doubleListExpression | ( stringListExpression )=>e= stringListExpression | ( typeListExpression )=>e= typeListExpression )
            int alt52=5;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LCURLY) ) {
                int LA52_1 = input.LA(2);

                if ( (synpred5_TextMarkerParser()) ) {
                    alt52=1;
                }
                else if ( (synpred6_TextMarkerParser()) ) {
                    alt52=2;
                }
                else if ( (synpred7_TextMarkerParser()) ) {
                    alt52=3;
                }
                else if ( (synpred8_TextMarkerParser()) ) {
                    alt52=4;
                }
                else if ( (synpred9_TextMarkerParser()) ) {
                    alt52=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA52_0==Identifier) ) {
                int LA52_2 = input.LA(2);

                if ( (((synpred5_TextMarkerParser()&&synpred5_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt52=1;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "INTLIST"))&&(isVariableOfType(input.LT(1).getText(), "INTLIST")))&&synpred6_TextMarkerParser())) ) {
                    alt52=2;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))&&synpred7_TextMarkerParser())) ) {
                    alt52=3;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "STRINGLIST"))&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))&&synpred8_TextMarkerParser())) ) {
                    alt52=4;
                }
                else if ( ((((isVariableOfType(input.LT(1).getText(), "TYPELIST"))&&(isVariableOfType(input.LT(1).getText(), "TYPELIST")))&&synpred9_TextMarkerParser())) ) {
                    alt52=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }
            switch (alt52) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:622:2: ( booleanListExpression )=>e= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2372);
                    e=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:623:4: ( intListExpression )=>e= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2388);
                    e=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:624:4: ( doubleListExpression )=>e= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2404);
                    e=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:625:4: ( stringListExpression )=>e= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2420);
                    e=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:626:4: ( typeListExpression )=>e= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2436);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:629:1: booleanListExpression returns [Expression expr = null] : e= simpleBooleanListExpression ;
    public final Expression booleanListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:631:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:632:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2460);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:635:1: simpleBooleanListExpression returns [Expression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleBooleanListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:638:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY |{...}?var= Identifier )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==LCURLY) ) {
                alt55=1;
            }
            else if ( (LA55_0==Identifier) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }
            switch (alt55) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2481); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( ((LA54_0 >= TRUE && LA54_0 <= FALSE)||LA54_0==Identifier) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2488);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:53: ( COMMA e= simpleBooleanExpression )*
                            loop53:
                            do {
                                int alt53=2;
                                int LA53_0 = input.LA(1);

                                if ( (LA53_0==COMMA) ) {
                                    alt53=1;
                                }


                                switch (alt53) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2493); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2499);
                            	    e=simpleBooleanExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;

                            	    if ( state.backtracking==0 ) {list.add(e);}

                            	    }
                            	    break;

                            	default :
                            	    break loop53;
                                }
                            } while (true);


                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2508); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_BL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:642:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEANLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2525); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:648:1: intListExpression returns [Expression expr = null] : e= simpleIntListExpression ;
    public final Expression intListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:649:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:650:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2550);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:653:1: simpleIntListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleIntListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:656:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:657:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2571); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:657:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==DecimalLiteral||LA57_0==FloatingPointLiteral||(LA57_0 >= Identifier && LA57_0 <= LPAREN)||LA57_0==MINUS) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:657:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2578);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:657:52: ( COMMA e= simpleNumberExpression )*
                            loop56:
                            do {
                                int alt56=2;
                                int LA56_0 = input.LA(1);

                                if ( (LA56_0==COMMA) ) {
                                    alt56=1;
                                }


                                switch (alt56) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:657:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2583); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2589);
                            	    e=simpleNumberExpression();

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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2598); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:660:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType(input.LT(1).getText(), \"INTLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2615); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:666:1: numberListExpression returns [Expression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression |e2= intListExpression );
    public final Expression numberListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:667:2: ( (e1= doubleListExpression )=>e1= doubleListExpression |e2= intListExpression )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==LCURLY) ) {
                int LA59_1 = input.LA(2);

                if ( (synpred10_TextMarkerParser()) ) {
                    alt59=1;
                }
                else if ( (true) ) {
                    alt59=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 59, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA59_0==Identifier) ) {
                int LA59_2 = input.LA(2);

                if ( ((((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))&&synpred10_TextMarkerParser())) ) {
                    alt59=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "INTLIST"))) ) {
                    alt59=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 59, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }
            switch (alt59) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:668:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2649);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:670:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2661);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:673:1: doubleListExpression returns [Expression expr = null] : e= simpleDoubleListExpression ;
    public final Expression doubleListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:674:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:675:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2684);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:678:1: simpleDoubleListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleDoubleListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:681:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:682:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2705); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:682:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==DecimalLiteral||LA61_0==FloatingPointLiteral||(LA61_0 >= Identifier && LA61_0 <= LPAREN)||LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:682:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2712);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:682:52: ( COMMA e= simpleNumberExpression )*
                            loop60:
                            do {
                                int alt60=2;
                                int LA60_0 = input.LA(1);

                                if ( (LA60_0==COMMA) ) {
                                    alt60=1;
                                }


                                switch (alt60) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:682:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2717); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2723);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2732); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:685:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType(input.LT(1).getText(), \"DOUBLELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2749); if (state.failed) return expr;

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



    // $ANTLR start "stringListExpression"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:691:1: stringListExpression returns [Expression expr = null] : e= simpleStringListExpression ;
    public final Expression stringListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:692:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:693:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2774);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:696:1: simpleStringListExpression returns [Expression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleStringListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:699:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:700:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression2795); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:700:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==StringLiteral||LA64_0==Identifier) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:700:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2802);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:700:52: ( COMMA e= simpleStringExpression )*
                            loop63:
                            do {
                                int alt63=2;
                                int LA63_0 = input.LA(1);

                                if ( (LA63_0==COMMA) ) {
                                    alt63=1;
                                }


                                switch (alt63) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:700:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression2807); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2813);
                            	    e=simpleStringExpression();

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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression2822); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_SL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:703:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType(input.LT(1).getText(), \"STRINGLIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression2839); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:709:1: typeListExpression returns [Expression expr = null] : e= simpleTypeListExpression ;
    public final Expression typeListExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:710:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:711:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression2864);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:714:1: simpleTypeListExpression returns [Expression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier );
    public final Expression simpleTypeListExpression() throws RecognitionException {
        Expression expr =  null;


        Token var=null;
        Expression e =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:717:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY |{...}?var= Identifier )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression2885); if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==BasicAnnotationType||LA67_0==Identifier) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2892);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;

                            if ( state.backtracking==0 ) {list.add(e);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:50: ( COMMA e= simpleTypeExpression )*
                            loop66:
                            do {
                                int alt66=2;
                                int LA66_0 = input.LA(1);

                                if ( (LA66_0==COMMA) ) {
                                    alt66=1;
                                }


                                switch (alt66) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:718:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression2897); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2903);
                            	    e=simpleTypeExpression();

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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression2912); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_TL);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:721:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType(input.LT(1).getText(), \"TYPELIST\")");
                    }

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression2929); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:726:1: typeExpression returns [Expression expr = null] : (tf= typeFunction |st= simpleTypeExpression );
    public final Expression typeExpression() throws RecognitionException {
        Expression expr =  null;


        Expression tf =null;

        Expression st =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:727:2: (tf= typeFunction |st= simpleTypeExpression )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==Identifier) ) {
                int LA69_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION"))) ) {
                    alt69=1;
                }
                else if ( (true) ) {
                    alt69=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 69, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA69_0==BasicAnnotationType) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:728:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression2955);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:729:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression2966);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:735:1: typeFunction returns [Expression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final Expression typeFunction() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:736:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:737:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction3000);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:741:1: externalTypeFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalTypeFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:742:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:743:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalTypeFunction", "isVariableOfType(input.LT(1).getText(), \"TYPEFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction3025); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction3029); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction3036);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction3040); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:752:1: simpleTypeExpression returns [Expression type = null] : at= annotationType ;
    public final Expression simpleTypeExpression() throws RecognitionException {
        Expression type =  null;


        Expression at =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:753:2: (at= annotationType )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:754:2: at= annotationType
            {
            pushFollow(FOLLOW_annotationType_in_simpleTypeExpression3063);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:757:1: variable returns [Expression var = null] :{...}?v= Identifier ;
    public final Expression variable() throws RecognitionException {
        Expression var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:758:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:759:2: {...}?v= Identifier
            {
            if ( !((vars.contains(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "vars.contains(input.LT(1).getText())");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3087); if (state.failed) return var;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:763:1: listVariable returns [Expression var = null] :{...}?v= Identifier ;
    public final Expression listVariable() throws RecognitionException {
        Expression var =  null;


        Token v=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:764:2: ({...}?v= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:765:2: {...}?v= Identifier
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")
            	||isVariableOfType(input.LT(1).getText(), "INTLIST")
            	||isVariableOfType(input.LT(1).getText(), "DOUBLELIST")
            	||isVariableOfType(input.LT(1).getText(), "STRINGLIST")
            	||isVariableOfType(input.LT(1).getText(), "TYPELIST")
            	)) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "listVariable", "isVariableOfType(input.LT(1).getText(), \"BOOLEANLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"INTLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"DOUBLELIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"STRINGLIST\")\r\n\t||isVariableOfType(input.LT(1).getText(), \"TYPELIST\")\r\n\t");
            }

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3114); if (state.failed) return var;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:775:1: quantifierPart returns [List<Expression> exprs = new ArrayList<Expression>()] : (s= STAR (q= QUESTION )? |p= PLUS (q= QUESTION )? |q1= QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? ) );
    public final List<Expression> quantifierPart() throws RecognitionException {
        List<Expression> exprs =  new ArrayList<Expression>();


        Token s=null;
        Token q=null;
        Token p=null;
        Token q1=null;
        Expression min =null;

        Expression max =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:776:2: (s= STAR (q= QUESTION )? |p= PLUS (q= QUESTION )? |q1= QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? ) )
            int alt76=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt76=1;
                }
                break;
            case PLUS:
                {
                alt76=2;
                }
                break;
            case QUESTION:
                {
                alt76=3;
                }
                break;
            case LBRACK:
                {
                alt76=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return exprs;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;

            }

            switch (alt76) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:777:3: s= STAR (q= QUESTION )?
                    {
                    s=(Token)match(input,STAR,FOLLOW_STAR_in_quantifierPart3141); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:777:14: (q= QUESTION )?
                    int alt70=2;
                    int LA70_0 = input.LA(1);

                    if ( (LA70_0==QUESTION) ) {
                        alt70=1;
                    }
                    switch (alt70) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:777:14: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3147); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(s,q));}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:778:4: p= PLUS (q= QUESTION )?
                    {
                    p=(Token)match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3159); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:778:15: (q= QUESTION )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==QUESTION) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:778:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3165); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(p,q));}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:779:4: q1= QUESTION (q= QUESTION )?
                    {
                    q1=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3177); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:779:20: (q= QUESTION )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==QUESTION) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:779:20: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3183); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q1,q));}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:4: ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:4: ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:5: LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3192); if (state.failed) return exprs;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3198);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:35: ( COMMA (max= numberExpression )? )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==COMMA) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:36: COMMA (max= numberExpression )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3201); if (state.failed) return exprs;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:42: (max= numberExpression )?
                            int alt73=2;
                            int LA73_0 = input.LA(1);

                            if ( ((LA73_0 >= EXP && LA73_0 <= TAN)||LA73_0==DecimalLiteral||LA73_0==FloatingPointLiteral||(LA73_0 >= Identifier && LA73_0 <= LPAREN)||LA73_0==MINUS) ) {
                                alt73=1;
                            }
                            switch (alt73) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:43: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3208);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return exprs;

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3214); if (state.failed) return exprs;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:79: (q= QUESTION )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==QUESTION) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:780:79: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3220); if (state.failed) return exprs;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:788:1: condition returns [TextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) ;
    public final TextMarkerCondition condition() throws RecognitionException {
        TextMarkerCondition result =  null;


        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:789:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:790:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:790:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            int alt77=30;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt77=1;
                }
                break;
            case CONTAINS:
                {
                alt77=2;
                }
                break;
            case CONTEXTCOUNT:
                {
                alt77=3;
                }
                break;
            case COUNT:
                {
                alt77=4;
                }
                break;
            case CURRENTCOUNT:
                {
                alt77=5;
                }
                break;
            case INLIST:
                {
                alt77=6;
                }
                break;
            case ISINTAG:
                {
                alt77=7;
                }
                break;
            case LAST:
                {
                alt77=8;
                }
                break;
            case MOFN:
                {
                alt77=9;
                }
                break;
            case NEAR:
                {
                alt77=10;
                }
                break;
            case NOT:
            case MINUS:
                {
                alt77=11;
                }
                break;
            case OR:
                {
                alt77=12;
                }
                break;
            case PARTOF:
                {
                alt77=13;
                }
                break;
            case POSITION:
                {
                alt77=14;
                }
                break;
            case REGEXP:
                {
                alt77=15;
                }
                break;
            case SCORE:
                {
                alt77=16;
                }
                break;
            case TOTALCOUNT:
                {
                alt77=17;
                }
                break;
            case VOTE:
                {
                alt77=18;
                }
                break;
            case IF:
                {
                alt77=19;
                }
                break;
            case FEATURE:
                {
                alt77=20;
                }
                break;
            case PARSE:
                {
                alt77=21;
                }
                break;
            case IS:
                {
                alt77=22;
                }
                break;
            case BEFORE:
                {
                alt77=23;
                }
                break;
            case AFTER:
                {
                alt77=24;
                }
                break;
            case STARTSWITH:
                {
                alt77=25;
                }
                break;
            case ENDSWITH:
                {
                alt77=26;
                }
                break;
            case PARTOFNEQ:
                {
                alt77=27;
                }
                break;
            case SIZE:
                {
                alt77=28;
                }
                break;
            case Identifier:
                {
                int LA77_29 = input.LA(2);

                if ( (LA77_29==LPAREN) && (synpred12_TextMarkerParser())) {
                    alt77=29;
                }
                else if ( (LA77_29==THEN||LA77_29==RPAREN||LA77_29==RCURLY||LA77_29==COMMA) ) {
                    alt77=30;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 77, 29, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;

            }

            switch (alt77) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:791:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3258);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:792:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3267);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:793:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3276);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:794:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3285);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:795:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3294);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:796:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3303);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:797:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3312);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:798:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3321);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:799:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3330);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:800:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3339);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:801:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3348);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:802:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3357);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:803:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3366);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:804:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3375);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:805:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3384);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:806:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3393);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:807:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3402);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:808:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3411);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:809:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3420);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:810:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3429);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:811:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3438);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:812:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3447);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:813:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3456);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:814:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3465);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:815:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3474);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:816:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3483);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:817:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3492);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:818:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3501);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:819:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3519);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:820:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3528);
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



    // $ANTLR start "variableCondition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:825:1: variableCondition returns [TextMarkerCondition condition = null] : id= Identifier ;
    public final TextMarkerCondition variableCondition() throws RecognitionException {
        TextMarkerCondition condition =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:826:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:829:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3561); if (state.failed) return condition;

            if ( state.backtracking==0 ) {
            		condition = ConditionFactory.createCondition(id);
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
        return condition;
    }
    // $ANTLR end "variableCondition"



    // $ANTLR start "externalCondition"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:835:1: externalCondition returns [TextMarkerCondition condition = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerCondition externalCondition() throws RecognitionException {
        TextMarkerCondition condition =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:836:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:837:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "CONDITION"))) ) {
                if (state.backtracking>0) {state.failed=true; return condition;}
                throw new FailedPredicateException(input, "externalCondition", "isVariableOfType(input.LT(1).getText(), \"CONDITION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3588); if (state.failed) return condition;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3591); if (state.failed) return condition;

            if ( state.backtracking==0 ) {condition = external.createExternalCondition(id, args);}

            pushFollow(FOLLOW_varArgumentList_in_externalCondition3601);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;

            if ( state.backtracking==0 ) {condition = external.createExternalCondition(id, args);}

            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3608); if (state.failed) return condition;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:844:1: conditionAnd returns [TextMarkerCondition cond = null] : name= AND LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionAnd() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:845:5: (name= AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:846:5: name= AND LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,AND,FOLLOW_AND_in_conditionAnd3636); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3638); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionAnd3644);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, conds);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3658); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:851:1: conditionContains returns [TextMarkerCondition cond = null] options {backtrack=true; } : name= CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:855:5: (name= CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:5: name= CONTAINS LPAREN (type= typeExpression |list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3704); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3706); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:29: (type= typeExpression |list= listExpression COMMA a= argument )
            int alt78=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA78_1 = input.LA(2);

                if ( (!((((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))))) ) {
                    alt78=1;
                }
                else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt78=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 78, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt78=1;
                }
                break;
            case LCURLY:
                {
                alt78=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;

            }

            switch (alt78) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3713);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:54: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3721);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3723); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionContains3729);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:857:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==COMMA) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:857:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3738); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3744);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3746); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains3752);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:857:64: ( COMMA percent= booleanExpression )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==COMMA) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:857:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3755); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3761);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3778); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:863:1: conditionContextCount returns [TextMarkerCondition cond = null] : name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionContextCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression typeExpr =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:864:5: (name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:865:5: name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount3814); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount3816); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount3822);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:867:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==COMMA) ) {
                int LA81_1 = input.LA(2);

                if ( ((LA81_1 >= EXP && LA81_1 <= TAN)||LA81_1==DecimalLiteral||LA81_1==FloatingPointLiteral||LA81_1==LPAREN||LA81_1==MINUS) ) {
                    alt81=1;
                }
                else if ( (LA81_1==Identifier) ) {
                    int LA81_4 = input.LA(3);

                    if ( (LA81_4==LPAREN||LA81_4==COMMA||(LA81_4 >= PLUS && LA81_4 <= SLASH)||LA81_4==PERCENT) ) {
                        alt81=1;
                    }
                }
            }
            switch (alt81) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:867:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3836); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3842);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3844); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3850);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:869:5: ( COMMA var= numberVariable )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:869:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3865); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount3871);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount3886); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:874:1: conditionCount returns [TextMarkerCondition cond = null] options {backtrack=true; } : (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN |name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final TextMarkerCondition conditionCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression a =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:878:6: (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN |name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==COUNT) ) {
                int LA87_1 = input.LA(2);

                if ( (synpred13_TextMarkerParser()) ) {
                    alt87=1;
                }
                else if ( (true) ) {
                    alt87=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 87, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;

            }
            switch (alt87) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:879:6: name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount3937); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3939); if (state.failed) return cond;

                    pushFollow(FOLLOW_listExpression_in_conditionCount3945);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount3960); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionCount3966);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:883:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:883:7: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3982); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount3988);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3990); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount3996);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:885:6: ( COMMA var= numberVariable )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==COMMA) ) {
                        alt84=1;
                    }
                    switch (alt84) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:885:7: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4014); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4020);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4036); if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:889:5: name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount4052); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4054); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount4060);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:891:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:891:6: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4074); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4080);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4082); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4088);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:5: ( COMMA var= numberVariable )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==COMMA) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4103); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4109);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4126); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:898:1: conditionCurrentCount returns [TextMarkerCondition cond = null] : name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionCurrentCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:899:5: (name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:900:5: name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4166); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4168); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4174);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:902:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COMMA) ) {
                int LA88_1 = input.LA(2);

                if ( ((LA88_1 >= EXP && LA88_1 <= TAN)||LA88_1==DecimalLiteral||LA88_1==FloatingPointLiteral||LA88_1==LPAREN||LA88_1==MINUS) ) {
                    alt88=1;
                }
                else if ( (LA88_1==Identifier) ) {
                    int LA88_4 = input.LA(3);

                    if ( (LA88_4==LPAREN||LA88_4==COMMA||(LA88_4 >= PLUS && LA88_4 <= SLASH)||LA88_4==PERCENT) ) {
                        alt88=1;
                    }
                }
            }
            switch (alt88) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:902:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4188); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4194);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4196); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4202);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:904:5: ( COMMA var= numberVariable )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==COMMA) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:904:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4218); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4224);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4239); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:909:1: conditionTotalCount returns [TextMarkerCondition cond = null] : name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionTotalCount() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:910:5: (name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:911:5: name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4278); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4280); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4286);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:913:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:913:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4300); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4306);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4308); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4314);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:915:5: ( COMMA var= numberVariable )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:915:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4329); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4335);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4350); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:919:1: conditionInList returns [TextMarkerCondition cond = null] options {backtrack=true; } : name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionInList() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression list2 =null;

        Expression list1 =null;

        Expression dist =null;

        Expression rel =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:923:5: (name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:924:5: name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,INLIST,FOLLOW_INLIST_in_conditionInList4391); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4393); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:924:26: ( (list2= stringListExpression )=>list2= stringListExpression |list1= wordListExpression )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==LCURLY) && (synpred14_TextMarkerParser())) {
                alt92=1;
            }
            else if ( (LA92_0==Identifier) ) {
                int LA92_2 = input.LA(2);

                if ( (((synpred14_TextMarkerParser()&&synpred14_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt92=1;
                }
                else if ( (true) ) {
                    alt92=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 92, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA92_0==RessourceLiteral) ) {
                alt92=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;

            }
            switch (alt92) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:924:27: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4408);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:924:90: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4416);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:925:5: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==COMMA) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:925:6: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4425); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4431);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:925:36: ( COMMA rel= booleanExpression )?
                    int alt93=2;
                    int LA93_0 = input.LA(1);

                    if ( (LA93_0==COMMA) ) {
                        alt93=1;
                    }
                    switch (alt93) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:925:37: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4434); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4440);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4458); if (state.failed) return cond;

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



    // $ANTLR start "conditionIsInTag"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:932:1: conditionIsInTag returns [TextMarkerCondition cond = null] : name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final TextMarkerCondition conditionIsInTag() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression id =null;

        Expression id1 =null;

        Expression id2 =null;



        List<Expression> list1 = new ArrayList<Expression>();
        List<Expression> list2 = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:937:5: (name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:938:5: name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            name=(Token)match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4501); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4503); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4509);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:938:49: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==COMMA) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:938:50: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4512); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4518);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4520); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4526);
            	    id2=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    if ( state.backtracking==0 ) {list1.add(id1);list2.add(id2);}

            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);


            if ( state.backtracking==0 ) {List exprs = new ArrayList();
                exprs.add(id);
                exprs.addAll(list1);
                exprs.addAll(list2);
                cond = ConditionFactory.createCondition(name, exprs);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4545); if (state.failed) return cond;

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
    // $ANTLR end "conditionIsInTag"



    // $ANTLR start "conditionLast"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:947:1: conditionLast returns [TextMarkerCondition cond = null] : name= LAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerCondition conditionLast() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:948:5: (name= LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:949:5: name= LAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,LAST,FOLLOW_LAST_in_conditionLast4585); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4587); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionLast4593);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4606); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:954:1: conditionMofN returns [TextMarkerCondition cond = null] : name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final TextMarkerCondition conditionMofN() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression min =null;

        Expression max =null;

        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:955:5: (name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:956:5: name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            name=(Token)match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4642); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4644); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4650);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4652); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4658);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4660); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionMofN4666);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {List exprs = new ArrayList();
                exprs.add(min);
                exprs.add(max);
                exprs.addAll(conds);
                cond = ConditionFactory.createCondition(name, exprs);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4681); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:965:1: conditionNear returns [TextMarkerCondition cond = null] : name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionNear() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression direction =null;

        Expression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:966:5: (name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:967:5: name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,NEAR,FOLLOW_NEAR_in_conditionNear4713); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4715); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionNear4721);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4723); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4729);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4731); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4737);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:968:5: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:968:6: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4745); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4751);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:968:42: ( COMMA filtered= booleanExpression )?
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==COMMA) ) {
                        alt96=1;
                    }
                    switch (alt96) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:968:43: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4754); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4760);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4780); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:972:1: conditionNot returns [TextMarkerCondition cond = null] : ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) ;
    public final TextMarkerCondition conditionNot() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:973:5: ( ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==MINUS) ) {
                alt98=1;
            }
            else if ( (LA98_0==NOT) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:6: (name= MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:6: (name= MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:7: name= MINUS c= condition
                    {
                    name=(Token)match(input,MINUS,FOLLOW_MINUS_in_conditionNot4813); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4819);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:38: (name= NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:38: (name= NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:39: name= NOT LPAREN c= condition RPAREN
                    {
                    name=(Token)match(input,NOT,FOLLOW_NOT_in_conditionNot4830); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4832); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot4838);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4840); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:977:1: conditionOr returns [TextMarkerCondition cond = null] : name= OR LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionOr() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:978:5: (name= OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:979:5: name= OR LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,OR,FOLLOW_OR_in_conditionOr4880); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4882); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionOr4888);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, conds);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4901); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:983:1: conditionPartOf returns [TextMarkerCondition cond = null] : name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOf() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:984:5: (name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:985:5: name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4929); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4931); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:985:26: (type= typeExpression |type= typeListExpression )
            int alt99=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA99_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt99=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt99=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 99, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt99=1;
                }
                break;
            case LCURLY:
                {
                alt99=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;

            }

            switch (alt99) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:985:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4938);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:985:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4944);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4961); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:990:1: conditionPartOfNeq returns [TextMarkerCondition cond = null] : name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:991:5: (name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:992:5: name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4994); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4996); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:992:29: (type= typeExpression |type= typeListExpression )
            int alt100=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA100_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt100=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt100=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 100, 1, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
                {
                alt100=1;
                }
                break;
            case LCURLY:
                {
                alt100=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;

            }

            switch (alt100) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:992:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq5003);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:992:52: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq5009);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq5026); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:998:1: conditionPosition returns [TextMarkerCondition cond = null] : name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final TextMarkerCondition conditionPosition() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:999:5: (name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1000:5: name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            name=(Token)match(input,POSITION,FOLLOW_POSITION_in_conditionPosition5063); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition5065); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionPosition5071);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition5073); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionPosition5079);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, pos);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition5092); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1004:1: conditionRegExp returns [TextMarkerCondition cond = null] : name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final TextMarkerCondition conditionRegExp() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression pattern =null;

        Expression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1005:5: (name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:5: name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            name=(Token)match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp5120); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp5122); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp5128);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:53: ( COMMA caseSensitive= booleanExpression )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==COMMA) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1006:54: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp5131); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp5137);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, pattern, caseSensitive);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp5155); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:1: conditionScore returns [TextMarkerCondition cond = null] : name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final TextMarkerCondition conditionScore() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1012:5: (name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:5: name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            name=(Token)match(input,SCORE,FOLLOW_SCORE_in_conditionScore5189); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore5191); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore5197);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:48: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==COMMA) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1013:49: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore5200); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore5206);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1014:5: ( COMMA var= numberVariable )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==COMMA) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1014:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore5215); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionScore5221);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore5238); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1019:1: conditionVote returns [TextMarkerCondition cond = null] : name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final TextMarkerCondition conditionVote() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type1 =null;

        Expression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1020:5: (name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1021:5: name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            name=(Token)match(input,VOTE,FOLLOW_VOTE_in_conditionVote5270); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote5272); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5278);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionVote5280); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5286);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type1, type2);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote5299); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1025:1: conditionIf returns [TextMarkerCondition cond = null] : name= IF LPAREN e= booleanExpression RPAREN ;
    public final TextMarkerCondition conditionIf() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1026:5: (name= IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1027:5: name= IF LPAREN e= booleanExpression RPAREN
            {
            name=(Token)match(input,IF,FOLLOW_IF_in_conditionIf5333); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf5335); if (state.failed) return cond;

            pushFollow(FOLLOW_booleanExpression_in_conditionIf5341);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, e);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf5354); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1032:1: conditionFeature returns [TextMarkerCondition cond = null] : name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerCondition conditionFeature() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression se =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1033:5: (name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1034:5: name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5393); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5395); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionFeature5401);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5403); if (state.failed) return cond;

            pushFollow(FOLLOW_argument_in_conditionFeature5409);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, se, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5422); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1038:1: conditionParse returns [TextMarkerCondition cond = null] : name= PARSE LPAREN var= genericVariableReference RPAREN ;
    public final TextMarkerCondition conditionParse() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1039:5: (name= PARSE LPAREN var= genericVariableReference RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1040:5: name= PARSE LPAREN var= genericVariableReference RPAREN
            {
            name=(Token)match(input,PARSE,FOLLOW_PARSE_in_conditionParse5453); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5455); if (state.failed) return cond;

            pushFollow(FOLLOW_genericVariableReference_in_conditionParse5464);
            var=genericVariableReference();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5477); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1047:1: conditionIs returns [TextMarkerCondition cond = null] : name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionIs() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1048:5: (name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1049:5: name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,IS,FOLLOW_IS_in_conditionIs5507); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5509); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1049:22: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1049:23: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5516);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1049:45: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5522);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5536); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1054:1: conditionBefore returns [TextMarkerCondition cond = null] : name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionBefore() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1055:5: (name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1056:5: name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5565); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5567); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1056:26: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1056:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5574);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1056:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5580);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5594); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1061:1: conditionAfter returns [TextMarkerCondition cond = null] : name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionAfter() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1062:5: (name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1063:5: name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5623); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5625); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1063:25: (type= typeExpression |type= typeListExpression )
            int alt106=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA106_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt106=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1063:26: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5632);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1063:48: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5638);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5652); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1068:1: conditionStartsWith returns [TextMarkerCondition cond = null] : name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionStartsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1069:5: (name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:5: name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5685); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5687); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:30: (type= typeExpression |type= typeListExpression )
            int alt107=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA107_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt107=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:31: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5694);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:53: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5700);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5714); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1075:1: conditionEndsWith returns [TextMarkerCondition cond = null] : name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionEndsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1076:5: (name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:5: name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5747); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5749); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:28: (type= typeExpression |type= typeListExpression )
            int alt108=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA108_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt108=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:29: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5756);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:51: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5762);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5776); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1082:1: conditionSize returns [TextMarkerCondition cond = null] : name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionSize() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression list =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1083:5: (name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:5: name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,SIZE,FOLLOW_SIZE_in_conditionSize5809); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5811); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize5817);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:46: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==COMMA) ) {
                int LA109_1 = input.LA(2);

                if ( ((LA109_1 >= EXP && LA109_1 <= TAN)||LA109_1==DecimalLiteral||LA109_1==FloatingPointLiteral||LA109_1==LPAREN||LA109_1==MINUS) ) {
                    alt109=1;
                }
                else if ( (LA109_1==Identifier) ) {
                    int LA109_4 = input.LA(3);

                    if ( (LA109_4==LPAREN||LA109_4==COMMA||(LA109_4 >= PLUS && LA109_4 <= SLASH)||LA109_4==PERCENT) ) {
                        alt109=1;
                    }
                }
            }
            switch (alt109) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:47: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5820); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5826);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5828); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize5834);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:107: ( COMMA var= numberVariable )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==COMMA) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:108: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5839); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionSize5845);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, list, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5860); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1090:1: action returns [TextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction ) ;
    public final TextMarkerAction action() throws RecognitionException {
        TextMarkerAction result =  null;


        TextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1091:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1092:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1092:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand | (a= externalAction )=>a= externalAction |a= variableAction )
            int alt111=37;
            switch ( input.LA(1) ) {
            case COLOR:
                {
                alt111=1;
                }
                break;
            case DEL:
                {
                alt111=2;
                }
                break;
            case LOG:
                {
                alt111=3;
                }
                break;
            case MARK:
                {
                alt111=4;
                }
                break;
            case MARKSCORE:
                {
                alt111=5;
                }
                break;
            case MARKFAST:
                {
                alt111=6;
                }
                break;
            case MARKLAST:
                {
                alt111=7;
                }
                break;
            case REPLACE:
                {
                alt111=8;
                }
                break;
            case RETAINMARKUP:
                {
                alt111=9;
                }
                break;
            case RETAINTYPE:
                {
                alt111=10;
                }
                break;
            case FILTERMARKUP:
                {
                alt111=11;
                }
                break;
            case FILTERTYPE:
                {
                alt111=12;
                }
                break;
            case CREATE:
                {
                alt111=13;
                }
                break;
            case FILL:
                {
                alt111=14;
                }
                break;
            case CALL:
                {
                alt111=15;
                }
                break;
            case ASSIGN:
                {
                alt111=16;
                }
                break;
            case SETFEATURE:
                {
                alt111=17;
                }
                break;
            case GETFEATURE:
                {
                alt111=18;
                }
                break;
            case UNMARK:
                {
                alt111=19;
                }
                break;
            case UNMARKALL:
                {
                alt111=20;
                }
                break;
            case TRANSFER:
                {
                alt111=21;
                }
                break;
            case MARKONCE:
                {
                alt111=22;
                }
                break;
            case TRIE:
                {
                alt111=23;
                }
                break;
            case GATHER:
                {
                alt111=24;
                }
                break;
            case EXEC:
                {
                alt111=25;
                }
                break;
            case MARKTABLE:
                {
                alt111=26;
                }
                break;
            case ADD:
                {
                alt111=27;
                }
                break;
            case REMOVE:
                {
                alt111=28;
                }
                break;
            case REMOVEDUPLICATE:
                {
                alt111=29;
                }
                break;
            case MERGE:
                {
                alt111=30;
                }
                break;
            case GET:
                {
                alt111=31;
                }
                break;
            case GETLIST:
                {
                alt111=32;
                }
                break;
            case MATCHEDTEXT:
                {
                alt111=33;
                }
                break;
            case CLEAR:
                {
                alt111=34;
                }
                break;
            case EXPAND:
                {
                alt111=35;
                }
                break;
            case Identifier:
                {
                int LA111_36 = input.LA(2);

                if ( (LA111_36==LPAREN) && (synpred15_TextMarkerParser())) {
                    alt111=36;
                }
                else if ( (LA111_36==RPAREN||LA111_36==RCURLY||LA111_36==COMMA) ) {
                    alt111=37;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 111, 36, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 111, 0, input);

                throw nvae;

            }

            switch (alt111) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1093:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5888);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1094:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5897);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1095:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5906);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1096:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5915);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1097:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5924);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1098:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5933);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1099:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5942);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1100:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5951);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1101:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5960);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1102:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5969);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1103:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5978);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1104:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5987);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5996);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1106:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action6005);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1107:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action6014);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1108:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action6023);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1109:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action6032);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1110:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action6041);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1111:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action6050);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1112:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action6059);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1113:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action6068);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1114:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action6077);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1115:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action6086);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1116:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action6095);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1117:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action6105);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1118:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action6114);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1119:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action6123);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1120:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action6132);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action6141);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1122:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action6150);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1123:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action6159);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1124:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action6169);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1125:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action6178);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1126:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action6187);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1127:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action6196);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1128:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action6214);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1129:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action6223);
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



    // $ANTLR start "variableAction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1134:1: variableAction returns [TextMarkerAction action = null] : id= Identifier ;
    public final TextMarkerAction variableAction() throws RecognitionException {
        TextMarkerAction action =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1135:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1138:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction6254); if (state.failed) return action;

            if ( state.backtracking==0 ) {
            		action = ActionFactory.createAction(id);
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
    // $ANTLR end "variableAction"



    // $ANTLR start "externalAction"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1143:1: externalAction returns [TextMarkerAction action = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerAction externalAction() throws RecognitionException {
        TextMarkerAction action =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1144:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1145:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "ACTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return action;}
                throw new FailedPredicateException(input, "externalAction", "isVariableOfType(input.LT(1).getText(), \"ACTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction6278); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction6282); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction6291);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction6296); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1155:1: actionCreate returns [TextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1161:5: (name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1162:5: name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate6332); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate6334); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate6340);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1163:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==COMMA) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1163:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6347); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt113=2;
                    int LA113_0 = input.LA(1);

                    if ( ((LA113_0 >= EXP && LA113_0 <= TAN)||LA113_0==DecimalLiteral||LA113_0==FloatingPointLiteral||LA113_0==LPAREN||LA113_0==MINUS) ) {
                        alt113=1;
                    }
                    else if ( (LA113_0==Identifier) ) {
                        int LA113_2 = input.LA(2);

                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {
                            alt113=1;
                        }
                    }
                    switch (alt113) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate6368);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop112:
                            do {
                                int alt112=2;
                                int LA112_0 = input.LA(1);

                                if ( (LA112_0==COMMA) ) {
                                    int LA112_1 = input.LA(2);

                                    if ( (synpred16_TextMarkerParser()) ) {
                                        alt112=1;
                                    }


                                }


                                switch (alt112) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:89: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:90: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6385); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate6391);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop112;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate6397); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1167:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==REMOVESTRING||LA115_0==StringLiteral||LA115_0==Identifier) ) {
                        alt115=1;
                    }
                    switch (alt115) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1167:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate6415);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6417); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate6423);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1168:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop114:
                            do {
                                int alt114=2;
                                int LA114_0 = input.LA(1);

                                if ( (LA114_0==COMMA) ) {
                                    alt114=1;
                                }


                                switch (alt114) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1168:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6433); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate6439);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6441); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate6447);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                            	    }
                            	    break;

                            	default :
                            	    break loop114;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6478); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1176:1: actionMarkTable returns [TextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1181:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1182:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6513); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6515); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6526);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6528); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6539);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6541); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6551);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1186:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==COMMA) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1186:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6559); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6570);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6572); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6578);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1188:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop117:
                    do {
                        int alt117=2;
                        int LA117_0 = input.LA(1);

                        if ( (LA117_0==COMMA) ) {
                            alt117=1;
                        }


                        switch (alt117) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1188:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6588); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6594);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6596); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6602);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop117;
                        }
                    } while (true);


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, index, table, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6626); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1194:1: actionGather returns [TextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1200:5: (name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1201:5: name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6660); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6662); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6668);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1203:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==COMMA) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1203:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6682); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( ((LA120_0 >= EXP && LA120_0 <= TAN)||LA120_0==DecimalLiteral||LA120_0==FloatingPointLiteral||LA120_0==LPAREN||LA120_0==MINUS) ) {
                        alt120=1;
                    }
                    else if ( (LA120_0==Identifier) ) {
                        int LA120_2 = input.LA(2);

                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {
                            alt120=1;
                        }
                    }
                    switch (alt120) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6694);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop119:
                            do {
                                int alt119=2;
                                int LA119_0 = input.LA(1);

                                if ( (LA119_0==COMMA) ) {
                                    int LA119_1 = input.LA(2);

                                    if ( (synpred17_TextMarkerParser()) ) {
                                        alt119=1;
                                    }


                                }


                                switch (alt119) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:88: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:89: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6710); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6716);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop119;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6723); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1205:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt124=2;
                    int LA124_0 = input.LA(1);

                    if ( (LA124_0==REMOVESTRING||LA124_0==StringLiteral||LA124_0==Identifier) ) {
                        alt124=1;
                    }
                    switch (alt124) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1205:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6736);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6738); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1205:44: (obj1= numberExpression |obj2= numberListExpression )
                            int alt121=2;
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
                                alt121=1;
                                }
                                break;
                            case Identifier:
                                {
                                int LA121_2 = input.LA(2);

                                if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {
                                    alt121=1;
                                }
                                else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))) ) {
                                    alt121=2;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return action;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 121, 2, input);

                                    throw nvae;

                                }
                                }
                                break;
                            case LCURLY:
                                {
                                alt121=2;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return action;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 121, 0, input);

                                throw nvae;

                            }

                            switch (alt121) {
                                case 1 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1205:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6745);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1205:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6753);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {left.add(fname); right.add(obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop123:
                            do {
                                int alt123=2;
                                int LA123_0 = input.LA(1);

                                if ( (LA123_0==COMMA) ) {
                                    alt123=1;
                                }


                                switch (alt123) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6764); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6770);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6772); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:50: (obj1= numberExpression |obj2= numberListExpression )
                            	    int alt122=2;
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
                            	        alt122=1;
                            	        }
                            	        break;
                            	    case Identifier:
                            	        {
                            	        int LA122_2 = input.LA(2);

                            	        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {
                            	            alt122=1;
                            	        }
                            	        else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))) ) {
                            	            alt122=2;
                            	        }
                            	        else {
                            	            if (state.backtracking>0) {state.failed=true; return action;}
                            	            NoViableAltException nvae =
                            	                new NoViableAltException("", 122, 2, input);

                            	            throw nvae;

                            	        }
                            	        }
                            	        break;
                            	    case LCURLY:
                            	        {
                            	        alt122=2;
                            	        }
                            	        break;
                            	    default:
                            	        if (state.backtracking>0) {state.failed=true; return action;}
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 122, 0, input);

                            	        throw nvae;

                            	    }

                            	    switch (alt122) {
                            	        case 1 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6779);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6787);
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
                            	    break loop123;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6819); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1214:1: actionFill returns [TextMarkerAction action = null] : name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN ;
    public final TextMarkerAction actionFill() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression structure =null;

        Expression fname =null;

        Expression obj1 =null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1219:5: (name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1220:5: name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN
            {
            name=(Token)match(input,FILL,FOLLOW_FILL_in_actionFill6854); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6856); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill6862);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, null, left, right);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1222:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+
            int cnt126=0;
            loop126:
            do {
                int alt126=2;
                int LA126_0 = input.LA(1);

                if ( (LA126_0==COMMA) ) {
                    alt126=1;
                }


                switch (alt126) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1223:5: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6880); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill6886);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6888); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionFill6898);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

            	    }
            	    break;

            	default :
            	    if ( cnt126 >= 1 ) break loop126;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(126, input);
                        throw eee;
                }
                cnt126++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, null, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6920); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1231:1: actionColor returns [TextMarkerAction action = null] : name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final TextMarkerAction actionColor() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression bgcolor =null;

        Expression fgcolor =null;

        Expression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1232:5: (name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1233:5: name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,COLOR,FOLLOW_COLOR_in_actionColor6957); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6959); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor6965);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            match(input,COMMA,FOLLOW_COMMA_in_actionColor6979); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor6990);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1238:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==COMMA) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1238:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor7004); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor7014);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1241:5: ( COMMA selected= booleanExpression )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==COMMA) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1241:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor7028); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor7038);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor7054); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1247:1: actionDel returns [TextMarkerAction action = null] : name= DEL ;
    public final TextMarkerAction actionDel() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1248:5: (name= DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1249:5: name= DEL
            {
            name=(Token)match(input,DEL,FOLLOW_DEL_in_actionDel7086); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1253:1: actionLog returns [TextMarkerAction action = null] : name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final TextMarkerAction actionLog() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token log=null;
        Expression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1254:5: (name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1255:5: name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            name=(Token)match(input,LOG,FOLLOW_LOG_in_actionLog7132); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog7134); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog7140);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1255:46: ( COMMA log= LogLevel )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==COMMA) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1255:47: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog7143); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog7149); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createLogAction(name, lit, log);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog7165); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1260:1: actionMark returns [TextMarkerAction action = null] : name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMark() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1264:5: (name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1265:5: name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARK,FOLLOW_MARK_in_actionMark7203); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark7205); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark7216);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1268:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( (LA130_0==COMMA) ) {
                    alt130=1;
                }


                switch (alt130) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1269:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark7234); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark7250);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop130;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark7272); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1275:1: actionExpand returns [TextMarkerAction action = null] : name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionExpand() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1279:5: (name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1280:5: name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand7309); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand7311); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand7322);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1283:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop131:
            do {
                int alt131=2;
                int LA131_0 = input.LA(1);

                if ( (LA131_0==COMMA) ) {
                    alt131=1;
                }


                switch (alt131) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1284:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand7340); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand7356);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop131;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand7378); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1290:1: actionMarkScore returns [TextMarkerAction action = null] : name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkScore() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression score =null;

        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1294:5: (name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1295:5: name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore7415); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore7417); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore7423);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7425); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore7431);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(score); list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1297:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==COMMA) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1298:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7449); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore7465);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore7487); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1304:1: actionMarkOnce returns [TextMarkerAction action = null] : name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkOnce() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression score =null;

        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1308:5: (name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:5: name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7524); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7526); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:28: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==MINUS) && (synpred21_TextMarkerParser())) {
                alt133=1;
            }
            else if ( (LA133_0==Identifier) ) {
                int LA133_2 = input.LA(2);

                if ( (((((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))&&((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))))&&synpred21_TextMarkerParser())) ) {
                    alt133=1;
                }
            }
            else if ( (LA133_0==DecimalLiteral) && (synpred21_TextMarkerParser())) {
                alt133=1;
            }
            else if ( (LA133_0==FloatingPointLiteral) && (synpred21_TextMarkerParser())) {
                alt133=1;
            }
            else if ( (LA133_0==LPAREN) && (synpred21_TextMarkerParser())) {
                alt133=1;
            }
            else if ( ((LA133_0 >= EXP && LA133_0 <= TAN)) && (synpred21_TextMarkerParser())) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:29: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7543);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7545); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7563);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(score); list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1311:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==COMMA) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1312:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7581); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7597);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7619); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1318:1: actionMarkFast returns [TextMarkerAction action = null] : name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN ;
    public final TextMarkerAction actionMarkFast() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression list =null;

        Expression ignore =null;

        Expression numExpr =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1319:5: (name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1320:5: name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7651); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7653); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7659);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7672); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7678);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1324:5: ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==COMMA) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1324:6: COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7692); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7698);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1324:39: ( COMMA numExpr= numberExpression )?
                    int alt135=2;
                    int LA135_0 = input.LA(1);

                    if ( (LA135_0==COMMA) ) {
                        alt135=1;
                    }
                    switch (alt135) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1324:40: COMMA numExpr= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7701); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7707);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7725); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1329:1: actionMarkLast returns [TextMarkerAction action = null] : name= MARKLAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerAction actionMarkLast() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1330:5: (name= MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1331:5: name= MARKLAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7757); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7759); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7765);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7778); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1337:1: actionReplace returns [TextMarkerAction action = null] : name= REPLACE LPAREN lit= stringExpression RPAREN ;
    public final TextMarkerAction actionReplace() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1338:5: (name= REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1339:5: name= REPLACE LPAREN lit= stringExpression RPAREN
            {
            name=(Token)match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7811); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7813); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace7819);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, lit);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7832); if (state.failed) return action;

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



    // $ANTLR start "actionRetainMarkup"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1344:1: actionRetainMarkup returns [TextMarkerAction action = null] : name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainMarkup() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:5: (name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1349:5: name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7869); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1349:25: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==LPAREN) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1349:26: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7872); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7878);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1351:5: ( COMMA id= stringExpression )*
                    loop137:
                    do {
                        int alt137=2;
                        int LA137_0 = input.LA(1);

                        if ( (LA137_0==COMMA) ) {
                            alt137=1;
                        }


                        switch (alt137) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1351:6: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7894); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7900);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop137;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7917); if (state.failed) return action;

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
    // $ANTLR end "actionRetainMarkup"



    // $ANTLR start "actionRetainType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1357:1: actionRetainType returns [TextMarkerAction action = null] : name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainType() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1361:5: (name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1362:5: name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7970); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1362:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==LPAREN) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1362:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7973); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7979);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1364:5: ( COMMA id= typeExpression )*
                    loop139:
                    do {
                        int alt139=2;
                        int LA139_0 = input.LA(1);

                        if ( (LA139_0==COMMA) ) {
                            alt139=1;
                        }


                        switch (alt139) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1364:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7995); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType8001);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop139;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType8018); if (state.failed) return action;

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



    // $ANTLR start "actionFilterMarkup"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1370:1: actionFilterMarkup returns [TextMarkerAction action = null] : name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterMarkup() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1374:5: (name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1375:5: name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup8067); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1375:25: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==LPAREN) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1375:26: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup8070); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup8076);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1377:5: ( COMMA id= stringExpression )*
                    loop141:
                    do {
                        int alt141=2;
                        int LA141_0 = input.LA(1);

                        if ( (LA141_0==COMMA) ) {
                            alt141=1;
                        }


                        switch (alt141) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1377:6: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup8092); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup8098);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop141;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup8115); if (state.failed) return action;

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
    // $ANTLR end "actionFilterMarkup"



    // $ANTLR start "actionFilterType"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1383:1: actionFilterType returns [TextMarkerAction action = null] : name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterType() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1387:5: (name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:5: name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType8160); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==LPAREN) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType8163); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType8169);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1390:5: ( COMMA id= typeExpression )*
                    loop143:
                    do {
                        int alt143=2;
                        int LA143_0 = input.LA(1);

                        if ( (LA143_0==COMMA) ) {
                            alt143=1;
                        }


                        switch (alt143) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1390:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType8185); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType8191);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop143;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType8208); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1396:1: actionCall returns [TextMarkerAction action = null] : name= CALL lp= LPAREN ns= dottedComponentReference RPAREN ;
    public final TextMarkerAction actionCall() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;



        String string = "";


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1401:5: (name= CALL lp= LPAREN ns= dottedComponentReference RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1402:5: name= CALL lp= LPAREN ns= dottedComponentReference RPAREN
            {
            name=(Token)match(input,CALL,FOLLOW_CALL_in_actionCall8257); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionCall8263); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}

            pushFollow(FOLLOW_dottedComponentReference_in_actionCall8285);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns);}}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall8299); if (state.failed) return action;

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



    // $ANTLR start "actionExec"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1410:1: actionExec returns [TextMarkerAction action = null] : name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionExec() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;

        Expression tl =null;



        String string = "";

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1414:5: (name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1415:5: name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN
            {
            name=(Token)match(input,EXEC,FOLLOW_EXEC_in_actionExec8333); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionExec8339); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}

            pushFollow(FOLLOW_dottedComponentReference_in_actionExec8357);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, null);}}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1419:6: ( COMMA tl= typeListExpression )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==COMMA) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1419:7: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec8373); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec8379);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, tl);}}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec8395); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1425:1: actionAssign returns [TextMarkerAction action = null] : name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN ;
    public final TextMarkerAction actionAssign() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token id=null;
        Expression e =null;



            VariableReference ref = null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1429:5: (name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1430:5: name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign8437); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign8439); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1431:5: (id= Identifier COMMA e= argument )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1431:6: id= Identifier COMMA e= argument
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8450); if (state.failed) return action;

            if ( state.backtracking==0 ) {
                ref = ExpressionFactory.createGenericVariableReference(id);
                action = ActionFactory.createAction(name, ref, e);}

            match(input,COMMA,FOLLOW_COMMA_in_actionAssign8468); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionAssign8474);
            e=argument();

            state._fsp--;
            if (state.failed) return action;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8482); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1444:1: actionSetFeature returns [TextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerAction actionSetFeature() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1445:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1446:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8519); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8521); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8527);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8541); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature8547);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8560); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1453:1: actionGetFeature returns [TextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final TextMarkerAction actionGetFeature() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1454:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1455:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8589); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8591); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8597);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8610); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature8616);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8629); if (state.failed) return action;

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



    // $ANTLR start "actionUnmark"
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1463:1: actionUnmark returns [TextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionUnmark() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1464:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1465:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8659); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8661); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark8667);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8680); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1470:1: actionUnmarkAll returns [TextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionUnmarkAll() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1471:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1472:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8709); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8711); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8717);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1474:5: ( COMMA list= typeListExpression )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==COMMA) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1474:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8731); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8737);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8752); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1482:1: actionTransfer returns [TextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionTransfer() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1483:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1484:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8784); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8786); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer8792);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8805); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1489:1: actionTrie returns [TextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1495:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1496:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8843); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8845); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie8859);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {left.add(key);}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8862); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie8877);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {right.add(value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1499:9: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop147:
            do {
                int alt147=2;
                int LA147_0 = input.LA(1);

                if ( (LA147_0==COMMA) ) {
                    int LA147_1 = input.LA(2);

                    if ( (LA147_1==Identifier) ) {
                        int LA147_2 = input.LA(3);

                        if ( (LA147_2==LPAREN||LA147_2==PLUS||LA147_2==ASSIGN_EQUAL) ) {
                            alt147=1;
                        }


                    }
                    else if ( (LA147_1==REMOVESTRING||LA147_1==StringLiteral) ) {
                        alt147=1;
                    }


                }


                switch (alt147) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1499:10: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8890); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8896);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {left.add(key);}

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8900); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8915);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {right.add(value);}

            	    }
            	    break;

            	default :
            	    break loop147;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8929); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie8935);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8951); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8957);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8964); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8970);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8977); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie8983);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8990); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie8996);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9003); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie9009);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie9032); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1523:1: actionAdd returns [TextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionAdd() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression a =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1527:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1528:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd9070); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd9072); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd9078);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1530:5: ( COMMA a= argument )+
            int cnt148=0;
            loop148:
            do {
                int alt148=2;
                int LA148_0 = input.LA(1);

                if ( (LA148_0==COMMA) ) {
                    alt148=1;
                }


                switch (alt148) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1530:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd9092); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd9098);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt148 >= 1 ) break loop148;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(148, input);
                        throw eee;
                }
                cnt148++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd9115); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1535:1: actionRemove returns [TextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionRemove() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression a =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1539:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1540:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove9149); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove9151); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove9157);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1542:5: ( COMMA a= argument )+
            int cnt149=0;
            loop149:
            do {
                int alt149=2;
                int LA149_0 = input.LA(1);

                if ( (LA149_0==COMMA) ) {
                    alt149=1;
                }


                switch (alt149) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1542:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove9171); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove9177);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt149 >= 1 ) break loop149;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(149, input);
                        throw eee;
                }
                cnt149++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove9194); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1548:1: actionRemoveDuplicate returns [TextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final TextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1549:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1550:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9224); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate9226); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate9232);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate9245); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1555:1: actionMerge returns [TextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final TextMarkerAction actionMerge() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression join =null;

        Expression t =null;

        Expression f =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1559:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1560:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge9282); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge9284); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge9290);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9304); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge9310);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9324); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge9330);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1565:5: ( COMMA f= listExpression )+
            int cnt150=0;
            loop150:
            do {
                int alt150=2;
                int LA150_0 = input.LA(1);

                if ( (LA150_0==COMMA) ) {
                    alt150=1;
                }


                switch (alt150) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1565:6: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge9340); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge9346);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(f);}

            	    }
            	    break;

            	default :
            	    if ( cnt150 >= 1 ) break loop150;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(150, input);
                        throw eee;
                }
                cnt150++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge9363); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1570:1: actionGet returns [TextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGet() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression var =null;

        Expression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1571:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1572:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet9392); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet9394); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet9400);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9413); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet9419);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9432); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet9438);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet9451); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1582:1: actionGetList returns [TextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGetList() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;

        Expression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1583:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1584:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList9481); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList9483); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList9489);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList9502); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList9508);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, op);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList9521); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1591:1: actionMatchedText returns [TextMarkerAction action = null] : name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMatchedText() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1595:5: (name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1596:5: name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText9558); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText9560); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText9571);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1598:5: ( COMMA index= numberExpression )*
            loop151:
            do {
                int alt151=2;
                int LA151_0 = input.LA(1);

                if ( (LA151_0==COMMA) ) {
                    alt151=1;
                }


                switch (alt151) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1599:5: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText9583); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText9589);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop151;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText9611); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1605:1: actionClear returns [TextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final TextMarkerAction actionClear() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1606:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1607:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear9644); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear9646); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear9652);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear9665); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1614:1: varArgumentList returns [List<Expression> args = new ArrayList<Expression>()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List<Expression> varArgumentList() throws RecognitionException {
        List<Expression> args =  new ArrayList<Expression>();


        Expression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1615:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1616:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1616:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==LPAREN) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1616:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList9687); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList9693);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1616:42: ( COMMA arg= argument )*
                    loop152:
                    do {
                        int alt152=2;
                        int LA152_0 = input.LA(1);

                        if ( (LA152_0==COMMA) ) {
                            alt152=1;
                        }


                        switch (alt152) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1616:43: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList9698); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList9704);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;

                    	    if ( state.backtracking==0 ) {args.add(arg);}

                    	    }
                    	    break;

                    	default :
                    	    break loop152;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList9710); if (state.failed) return args;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1620:1: argument returns [Expression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final Expression argument() throws RecognitionException {
        Expression expr =  null;


        Expression a4 =null;

        Expression a2 =null;

        Expression a3 =null;

        Expression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1624:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
            int alt154=4;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
            case StringLiteral:
                {
                alt154=1;
                }
                break;
            case Identifier:
                {
                int LA154_2 = input.LA(2);

                if ( (((((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "STRING")))&&((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "STRING"))))&&synpred24_TextMarkerParser())) ) {
                    alt154=1;
                }
                else if ( (synpred25_TextMarkerParser()) ) {
                    alt154=2;
                }
                else if ( (((synpred26_TextMarkerParser()&&synpred26_TextMarkerParser())&&((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))))) ) {
                    alt154=3;
                }
                else if ( (true) ) {
                    alt154=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 154, 2, input);

                    throw nvae;

                }
                }
                break;
            case XOR:
            case TRUE:
            case FALSE:
                {
                alt154=2;
                }
                break;
            case BasicAnnotationType:
                {
                int LA154_6 = input.LA(2);

                if ( (synpred25_TextMarkerParser()) ) {
                    alt154=2;
                }
                else if ( (true) ) {
                    alt154=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 154, 6, input);

                    throw nvae;

                }
                }
                break;
            case LPAREN:
                {
                int LA154_7 = input.LA(2);

                if ( (synpred25_TextMarkerParser()) ) {
                    alt154=2;
                }
                else if ( (synpred26_TextMarkerParser()) ) {
                    alt154=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 154, 7, input);

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
                alt154=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 154, 0, input);

                throw nvae;

            }

            switch (alt154) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1625:3: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument9747);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1626:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument9758);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1627:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9769);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1628:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9780);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1639:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1640:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1641:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9817); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1642:2: (dot= DOT idn= Identifier )*
            loop155:
            do {
                int alt155=2;
                int LA155_0 = input.LA(1);

                if ( (LA155_0==DOT) ) {
                    alt155=1;
                }


                switch (alt155) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1643:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9830); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9840); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop155;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1649:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1651:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1652:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9872); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1655:2: (dot= DOT id= Identifier )*
            loop156:
            do {
                int alt156=2;
                int LA156_0 = input.LA(1);

                if ( (LA156_0==DOT) ) {
                    alt156=1;
                }


                switch (alt156) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1656:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9885); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9895); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop156;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1665:1: dottedComponentReference returns [ComponentReference ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentReference dottedComponentReference() throws RecognitionException {
        ComponentReference ref =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1667:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1668:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference9930); if (state.failed) return ref;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		//if (ct.getText().equals("<missing Identifier>")) {
            	        //    CommonTokenStream cts = (CommonTokenStream) input;
            	        //    Token lt = cts.LT(1);
            	        //    ct = new CommonToken(lt);
            	        //  }
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1676:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop157:
            do {
                int alt157=2;
                int LA157_0 = input.LA(1);

                if ( (LA157_0==DOT||LA157_0==MINUS) ) {
                    alt157=1;
                }


                switch (alt157) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1677:3: dot= ( DOT | MINUS ) id= Identifier
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

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference9959); if (state.failed) return ref;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop157;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1685:1: dottedComponentDeclaration returns [ComponentDeclaration ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentDeclaration dottedComponentDeclaration() throws RecognitionException {
        ComponentDeclaration ref =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1687:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1688:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration9993); if (state.failed) return ref;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1691:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop158:
            do {
                int alt158=2;
                int LA158_0 = input.LA(1);

                if ( (LA158_0==DOT||LA158_0==MINUS) ) {
                    alt158=1;
                }


                switch (alt158) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1692:3: dot= ( DOT | MINUS ) id= Identifier
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

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration10022); if (state.failed) return ref;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop158;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1701:1: annotationType returns [Expression at = null] : (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType ) ;
    public final Expression annotationType() throws RecognitionException {
        Expression at =  null;


        Token basicAT=null;
        Expression atRef =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1702:2: ( (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1703:2: (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1703:2: (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType )
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==Identifier) ) {
                alt159=1;
            }
            else if ( (LA159_0==BasicAnnotationType) ) {
                alt159=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return at;}
                NoViableAltException nvae =
                    new NoViableAltException("", 159, 0, input);

                throw nvae;

            }
            switch (alt159) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1704:2: atRef= annotationTypeVariableReference
                    {
                    pushFollow(FOLLOW_annotationTypeVariableReference_in_annotationType10056);
                    atRef=annotationTypeVariableReference();

                    state._fsp--;
                    if (state.failed) return at;

                    if ( state.backtracking==0 ) {at = atRef;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1705:4: basicAT= BasicAnnotationType
                    {
                    basicAT=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType10067); if (state.failed) return at;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1709:1: annotationTypeVariableReference returns [Expression typeVar = null] : atRef= dottedId ;
    public final Expression annotationTypeVariableReference() throws RecognitionException {
        Expression typeVar =  null;


        Token atRef =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1710:3: (atRef= dottedId )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1711:3: atRef= dottedId
            {
            pushFollow(FOLLOW_dottedId_in_annotationTypeVariableReference10096);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1715:1: wordListExpression returns [Expression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final Expression wordListExpression() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1716:2: (id= Identifier |path= RessourceLiteral )
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==Identifier) ) {
                alt160=1;
            }
            else if ( (LA160_0==RessourceLiteral) ) {
                alt160=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 160, 0, input);

                throw nvae;

            }
            switch (alt160) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1717:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression10120); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListVariableReference(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1720:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression10133); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1725:1: wordTableExpression returns [Expression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final Expression wordTableExpression() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1726:2: (id= Identifier |path= RessourceLiteral )
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==Identifier) ) {
                alt161=1;
            }
            else if ( (LA161_0==RessourceLiteral) ) {
                alt161=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 161, 0, input);

                throw nvae;

            }
            switch (alt161) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1727:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression10157); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTableVariableReference(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1730:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression10170); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1735:1: numberExpression returns [Expression expr = null] : e= additiveExpression ;
    public final Expression numberExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1736:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1737:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression10194);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1742:1: additiveExpression returns [Expression root = null] : expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* ;
    public final Expression additiveExpression() throws RecognitionException {
        Expression root =  null;


        Token op=null;
        Expression expr1 =null;

        Expression expr2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1743:5: (expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1743:9: expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10220);
            expr1=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return root;

            if ( state.backtracking==0 ) {root=expr1;}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1744:2: (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            loop162:
            do {
                int alt162=2;
                int LA162_0 = input.LA(1);

                if ( ((LA162_0 >= PLUS && LA162_0 <= MINUS)) ) {
                    alt162=1;
                }


                switch (alt162) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1744:4: op= ( PLUS | MINUS ) expr2= multiplicativeExpression
            	    {
            	    op=(Token)input.LT(1);

            	    if ( (input.LA(1) >= PLUS && input.LA(1) <= MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return root;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10239);
            	    expr2=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return root;

            	    if ( state.backtracking==0 ) {root=ExpressionFactory.createBinaryArithmeticExpr(root,expr2,op);}

            	    }
            	    break;

            	default :
            	    break loop162;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1748:1: multiplicativeExpression returns [Expression root = null] : (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction ) ;
    public final Expression multiplicativeExpression() throws RecognitionException {
        Expression root =  null;


        Token op=null;
        Expression expr1 =null;

        Expression sNE =null;

        Expression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1749:5: ( (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1750:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1750:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction )
            int alt164=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt164=1;
                }
                break;
            case Identifier:
                {
                int LA164_2 = input.LA(2);

                if ( (LA164_2==LPAREN) ) {
                    alt164=2;
                }
                else if ( (LA164_2==EOF||LA164_2==RPAREN||LA164_2==RBRACK||(LA164_2 >= COMMA && LA164_2 <= SLASH)||(LA164_2 >= LESS && LA164_2 <= GREATER)||LA164_2==PERCENT||(LA164_2 >= EQUAL && LA164_2 <= NOTEQUAL)||(LA164_2 >= LESSEQUAL && LA164_2 <= GREATEREQUAL)) ) {
                    alt164=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return root;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 164, 2, input);

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
                alt164=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return root;}
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;

            }

            switch (alt164) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1750:3: expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10268);
                    expr1=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return root;

                    if ( state.backtracking==0 ) {root=expr1;}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1751:2: (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    loop163:
                    do {
                        int alt163=2;
                        int LA163_0 = input.LA(1);

                        if ( ((LA163_0 >= STAR && LA163_0 <= SLASH)||LA163_0==PERCENT) ) {
                            alt163=1;
                        }


                        switch (alt163) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1751:4: op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression
                    	    {
                    	    op=(Token)input.LT(1);

                    	    if ( (input.LA(1) >= STAR && input.LA(1) <= SLASH)||input.LA(1)==PERCENT ) {
                    	        input.consume();
                    	        state.errorRecovery=false;
                    	        state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return root;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }


                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10295);
                    	    sNE=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return root;

                    	    if ( state.backtracking==0 ) {root=ExpressionFactory.createBinaryArithmeticExpr(root,sNE,op);}

                    	    }
                    	    break;

                    	default :
                    	    break loop163;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1752:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression10311);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1756:1: numberExpressionInPar returns [TextMarkerExpression expr = null] : lp= LPAREN numE= numberExpression rp= RPAREN ;
    public final TextMarkerExpression numberExpressionInPar() throws RecognitionException {
        TextMarkerExpression expr =  null;


        Token lp=null;
        Token rp=null;
        Expression numE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1757:2: (lp= LPAREN numE= numberExpression rp= RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1758:2: lp= LPAREN numE= numberExpression rp= RPAREN
            {
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar10335); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_numberExpressionInPar10341);
            numE=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            rp=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar10347); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1766:1: simpleNumberExpression returns [Expression expr = null] : ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral |numExprPar= numberExpressionInPar );
    public final Expression simpleNumberExpression() throws RecognitionException {
        Expression expr =  null;


        Token m=null;
        Token decLit=null;
        Token fpLit=null;
        Expression numVarRef =null;

        TextMarkerExpression numExprPar =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1767:2: ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral |numExprPar= numberExpressionInPar )
            int alt168=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case Identifier:
                    {
                    alt168=1;
                    }
                    break;
                case DecimalLiteral:
                    {
                    alt168=2;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt168=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 168, 1, input);

                    throw nvae;

                }

                }
                break;
            case Identifier:
                {
                alt168=1;
                }
                break;
            case DecimalLiteral:
                {
                alt168=2;
                }
                break;
            case FloatingPointLiteral:
                {
                alt168=3;
                }
                break;
            case LPAREN:
                {
                alt168=4;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1768:2: (m= MINUS )? numVarRef= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1768:4: (m= MINUS )?
                    int alt165=2;
                    int LA165_0 = input.LA(1);

                    if ( (LA165_0==MINUS) ) {
                        alt165=1;
                    }
                    switch (alt165) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1768:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10372); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression10379);
                    numVarRef=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {if(m == null) {expr = numVarRef;} else {expr = ExpressionFactory.createNegatedNumberExpression(m, numVarRef);}}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1770:4: (m= MINUS )? decLit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1770:4: (m= MINUS )?
                    int alt166=2;
                    int LA166_0 = input.LA(1);

                    if ( (LA166_0==MINUS) ) {
                        alt166=1;
                    }
                    switch (alt166) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1770:5: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10394); if (state.failed) return expr;

                            }
                            break;

                    }


                    decLit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression10402); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDecimalLiteral(decLit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1772:4: (m= MINUS )? fpLit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1772:6: (m= MINUS )?
                    int alt167=2;
                    int LA167_0 = input.LA(1);

                    if ( (LA167_0==MINUS) ) {
                        alt167=1;
                    }
                    switch (alt167) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1772:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10416); if (state.failed) return expr;

                            }
                            break;

                    }


                    fpLit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10423); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createFloatingPointLiteral(fpLit,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1775:4: numExprPar= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression10439);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1780:1: numberFunction returns [Expression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final Expression numberFunction() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        TextMarkerExpression numExprP =null;

        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1781:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( ((LA169_0 >= EXP && LA169_0 <= TAN)) ) {
                alt169=1;
            }
            else if ( (LA169_0==Identifier) && (synpred27_TextMarkerParser())) {
                alt169=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 169, 0, input);

                throw nvae;

            }
            switch (alt169) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1782:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1782:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1782:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
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


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction10486);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createUnaryArithmeticExpr(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1785:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction10510);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1789:1: externalNumberFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalNumberFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1790:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1791:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalNumberFunction", "isVariableOfType(input.LT(1).getText(), \"NUMBERFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction10535); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction10539); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction10546);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction10549); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1801:1: numberVariable returns [Expression expr = null] : ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier ) ;
    public final Expression numberVariable() throws RecognitionException {
        Expression expr =  null;


        Token numVarRef=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1802:2: ( ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1803:5: ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1803:5: ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier )
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==Identifier) ) {
                int LA170_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                    alt170=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                    alt170=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 170, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 170, 0, input);

                throw nvae;

            }
            switch (alt170) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1803:7: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"INT\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10580); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1804:5: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"DOUBLE\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10593); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1810:1: stringExpression returns [Expression expr = null] : (e= stringFunction |strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )* );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1812:2: (e= stringFunction |strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )* )
            int alt173=2;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
                {
                alt173=1;
                }
                break;
            case Identifier:
                {
                int LA173_2 = input.LA(2);

                if ( (LA173_2==LPAREN) ) {
                    alt173=1;
                }
                else if ( (LA173_2==EOF||LA173_2==RPAREN||(LA173_2 >= COMMA && LA173_2 <= PLUS)||LA173_2==ASSIGN_EQUAL) ) {
                    alt173=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 173, 2, input);

                    throw nvae;

                }
                }
                break;
            case StringLiteral:
                {
                alt173=2;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1813:2: e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression10631);
                    e=stringFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1815:2: strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10644);
                    strExpr1=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {if (strExpr1!=null) exprList.add(strExpr1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:2: ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )*
                    loop172:
                    do {
                        int alt172=2;
                        int LA172_0 = input.LA(1);

                        if ( (LA172_0==PLUS) ) {
                            alt172=1;
                        }


                        switch (alt172) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:3: PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression10650); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:8: (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression )
                    	    int alt171=5;
                    	    int LA171_0 = input.LA(1);

                    	    if ( (LA171_0==StringLiteral) ) {
                    	        alt171=1;
                    	    }
                    	    else if ( (LA171_0==Identifier) ) {
                    	        int LA171_2 = input.LA(2);

                    	        if ( ((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                    	            alt171=1;
                    	        }
                    	        else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt171=3;
                    	        }
                    	        else if ( (((((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))&&((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST"))))&&synpred28_TextMarkerParser())) ) {
                    	            alt171=4;
                    	        }
                    	        else if ( (true) ) {
                    	            alt171=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 171, 2, input);

                    	            throw nvae;

                    	        }
                    	    }
                    	    else if ( (LA171_0==LPAREN) ) {
                    	        alt171=2;
                    	    }
                    	    else if ( ((LA171_0 >= TRUE && LA171_0 <= FALSE)) ) {
                    	        alt171=3;
                    	    }
                    	    else if ( (LA171_0==LCURLY) && (synpred28_TextMarkerParser())) {
                    	        alt171=4;
                    	    }
                    	    else if ( (LA171_0==BasicAnnotationType) ) {
                    	        alt171=5;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 171, 0, input);

                    	        throw nvae;

                    	    }
                    	    switch (alt171) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:9: nextstrExpr= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10657);
                    	            nextstrExpr=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (nextstrExpr!=null) exprList.add(nextstrExpr);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1817:5: ne= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression10669);
                    	            ne=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (ne!=null) exprList.add(ne);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1818:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression10681);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (be!=null) exprList.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1819:5: ( listExpression )=>le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression10698);
                    	            le=listExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (le!=null) exprList.add(le);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1820:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression10710);
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
                    	    break loop172;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1827:1: stringFunction returns [Expression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final Expression stringFunction() throws RecognitionException {
        Expression expr =  null;


        Token name=null;
        Expression var =null;

        Expression s =null;

        Expression e =null;


        List<Expression> list = new ArrayList<Expression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1829:2: (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==REMOVESTRING) ) {
                alt175=1;
            }
            else if ( (LA175_0==Identifier) && (synpred29_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1830:2: name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction10747); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction10749); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction10755);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1830:44: ( COMMA s= stringExpression )+
                    int cnt174=0;
                    loop174:
                    do {
                        int alt174=2;
                        int LA174_0 = input.LA(1);

                        if ( (LA174_0==COMMA) ) {
                            alt174=1;
                        }


                        switch (alt174) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1830:45: COMMA s= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction10758); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction10764);
                    	    s=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {list.add(s);}

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt174 >= 1 ) break loop174;
                    	    if (state.backtracking>0) {state.failed=true; return expr;}
                                EarlyExitException eee =
                                    new EarlyExitException(174, input);
                                throw eee;
                        }
                        cnt174++;
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction10769); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringFunction(name,var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1833:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction10791);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1837:1: externalStringFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalStringFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1838:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1839:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalStringFunction", "isVariableOfType(input.LT(1).getText(), \"STRINGFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction10816); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction10820); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction10827);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction10830); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1849:1: simpleStringExpression returns [Expression expr = null] : (lit= StringLiteral |{...}?variableId= Identifier );
    public final Expression simpleStringExpression() throws RecognitionException {
        Expression expr =  null;


        Token lit=null;
        Token variableId=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1850:2: (lit= StringLiteral |{...}?variableId= Identifier )
            int alt176=2;
            int LA176_0 = input.LA(1);

            if ( (LA176_0==StringLiteral) ) {
                alt176=1;
            }
            else if ( (LA176_0==Identifier) ) {
                alt176=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 176, 0, input);

                throw nvae;

            }
            switch (alt176) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1851:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression10855); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleString(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1852:5: {...}?variableId= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType(input.LT(1).getText(), \"STRING\")");
                    }

                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression10870); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1863:1: booleanExpression returns [Expression expr = null] : (bcE= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final Expression booleanExpression() throws RecognitionException {
        Expression expr =  null;


        Expression bcE =null;

        Expression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1864:2: (bcE= composedBooleanExpression |sbE= simpleBooleanExpression )
            int alt177=2;
            switch ( input.LA(1) ) {
            case TRUE:
                {
                int LA177_1 = input.LA(2);

                if ( ((LA177_1 >= EQUAL && LA177_1 <= NOTEQUAL)) ) {
                    alt177=1;
                }
                else if ( (LA177_1==EOF||LA177_1==RPAREN||(LA177_1 >= COMMA && LA177_1 <= SEMI)) ) {
                    alt177=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 177, 1, input);

                    throw nvae;

                }
                }
                break;
            case FALSE:
                {
                int LA177_2 = input.LA(2);

                if ( ((LA177_2 >= EQUAL && LA177_2 <= NOTEQUAL)) ) {
                    alt177=1;
                }
                else if ( (LA177_2==EOF||LA177_2==RPAREN||(LA177_2 >= COMMA && LA177_2 <= SEMI)) ) {
                    alt177=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 177, 2, input);

                    throw nvae;

                }
                }
                break;
            case Identifier:
                {
                int LA177_3 = input.LA(2);

                if ( (LA177_3==LPAREN||LA177_3==DOT||(LA177_3 >= EQUAL && LA177_3 <= NOTEQUAL)) ) {
                    alt177=1;
                }
                else if ( (LA177_3==EOF||LA177_3==RPAREN||(LA177_3 >= COMMA && LA177_3 <= SEMI)) ) {
                    alt177=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 177, 3, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
            case XOR:
            case LPAREN:
                {
                alt177=1;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1865:2: bcE= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10903);
                    bcE=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bcE;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1866:4: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10914);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1869:1: simpleBooleanExpression returns [Expression expr = null] : (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) ) ;
    public final Expression simpleBooleanExpression() throws RecognitionException {
        Expression expr =  null;


        Token variableId=null;
        BooleanLiteral lbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1870:2: ( (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1871:3: (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1871:3: (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) )
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( ((LA178_0 >= TRUE && LA178_0 <= FALSE)) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1871:4: lbE= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10939);
                    lbE=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = lbE;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1872:4: {...}? (variableId= Identifier )
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1872:57: (variableId= Identifier )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1872:58: variableId= Identifier
                    {
                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression10952); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1879:1: composedBooleanExpression returns [Expression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final Expression composedBooleanExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e2 =null;

        Expression bte =null;

        Expression bne =null;

        Expression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1880:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
            int alt179=4;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==TRUE) && (synpred30_TextMarkerParser())) {
                alt179=1;
            }
            else if ( (LA179_0==FALSE) && (synpred30_TextMarkerParser())) {
                alt179=1;
            }
            else if ( (LA179_0==Identifier) ) {
                int LA179_3 = input.LA(2);

                if ( (((synpred30_TextMarkerParser()&&synpred30_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt179=1;
                }
                else if ( (synpred31_TextMarkerParser()) ) {
                    alt179=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                    alt179=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 179, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA179_0==BasicAnnotationType) && (synpred31_TextMarkerParser())) {
                alt179=2;
            }
            else if ( (LA179_0==LPAREN) && (synpred32_TextMarkerParser())) {
                alt179=3;
            }
            else if ( (LA179_0==XOR) ) {
                alt179=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 179, 0, input);

                throw nvae;

            }
            switch (alt179) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1881:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression10998);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1882:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression11018);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1883:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression11037);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1884:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression11047);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1888:1: booleanFunction returns [Expression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final Expression booleanFunction() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;

        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1890:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==XOR) ) {
                alt180=1;
            }
            else if ( (LA180_0==Identifier) && (synpred33_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1891:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1891:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1891:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction11072); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction11074); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11080);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction11082); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11088);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction11090); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1893:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction11112);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1897:1: externalBooleanFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalBooleanFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1898:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1899:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalBooleanFunction", "isVariableOfType(input.LT(1).getText(), \"BOOLEANFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction11138); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction11141); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction11148);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction11152); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1909:1: booleanCompare returns [Expression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final Expression booleanCompare() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1910:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1911:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1911:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1911:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare11177);
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


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare11195);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1916:1: literalBooleanExpression returns [BooleanLiteral expr = null] : (value= TRUE |value= FALSE ) ;
    public final BooleanLiteral literalBooleanExpression() throws RecognitionException {
        BooleanLiteral expr =  null;


        Token value=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1917:2: ( (value= TRUE |value= FALSE ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1918:2: (value= TRUE |value= FALSE )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1918:2: (value= TRUE |value= FALSE )
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==TRUE) ) {
                alt181=1;
            }
            else if ( (LA181_0==FALSE) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1918:3: value= TRUE
                    {
                    value=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression11222); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1919:4: value= FALSE
                    {
                    value=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression11232); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1924:1: booleanTypeExpression returns [Expression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final Expression booleanTypeExpression() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1925:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1926:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11259);
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


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11279);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1934:1: booleanNumberExpression returns [Expression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final Expression booleanNumberExpression() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1935:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1936:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression11302); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11309);
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


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11345);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression11348); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1944:1: genericVariableReference returns [Expression varRef] : id= Identifier ;
    public final Expression genericVariableReference() throws RecognitionException {
        Expression varRef = null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1945:3: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1946:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericVariableReference11368); if (state.failed) return varRef;

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
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:579:3: ( ruleElementType VBAR )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:579:4: ruleElementType VBAR
        {
        pushFollow(FOLLOW_ruleElementType_in_synpred1_TextMarkerParser1961);
        ruleElementType();

        state._fsp--;
        if (state.failed) return ;

        match(input,VBAR,FOLLOW_VBAR_in_synpred1_TextMarkerParser1963); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_TextMarkerParser

    // $ANTLR start synpred2_TextMarkerParser
    public final void synpred2_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:582:4: ( ruleElements )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:582:5: ruleElements
        {
        pushFollow(FOLLOW_ruleElements_in_synpred2_TextMarkerParser2005);
        ruleElements();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_TextMarkerParser

    // $ANTLR start synpred5_TextMarkerParser
    public final void synpred5_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:622:2: ( booleanListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:622:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred5_TextMarkerParser2364);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_TextMarkerParser

    // $ANTLR start synpred6_TextMarkerParser
    public final void synpred6_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:623:4: ( intListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:623:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred6_TextMarkerParser2380);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred6_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:624:4: ( doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:624:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred7_TextMarkerParser2396);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:625:4: ( stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:625:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred8_TextMarkerParser2412);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred9_TextMarkerParser
    public final void synpred9_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:626:4: ( typeListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:626:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred9_TextMarkerParser2428);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_TextMarkerParser

    // $ANTLR start synpred10_TextMarkerParser
    public final void synpred10_TextMarkerParser_fragment() throws RecognitionException {
        Expression e1 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:668:2: (e1= doubleListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:668:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred10_TextMarkerParser2641);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {
        TextMarkerCondition c =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:819:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:819:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred12_TextMarkerParser3511);
        c=externalCondition();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred13_TextMarkerParser
    public final void synpred13_TextMarkerParser_fragment() throws RecognitionException {
        Token name=null;
        Expression type =null;

        Expression a =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:879:6: (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:879:6: name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        name=(Token)match(input,COUNT,FOLLOW_COUNT_in_synpred13_TextMarkerParser3937); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred13_TextMarkerParser3939); if (state.failed) return ;

        pushFollow(FOLLOW_listExpression_in_synpred13_TextMarkerParser3945);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,COMMA,FOLLOW_COMMA_in_synpred13_TextMarkerParser3960); if (state.failed) return ;

        pushFollow(FOLLOW_argument_in_synpred13_TextMarkerParser3966);
        a=argument();

        state._fsp--;
        if (state.failed) return ;

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:883:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
        int alt182=2;
        int LA182_0 = input.LA(1);

        if ( (LA182_0==COMMA) ) {
            int LA182_1 = input.LA(2);

            if ( ((LA182_1 >= EXP && LA182_1 <= TAN)||LA182_1==DecimalLiteral||LA182_1==FloatingPointLiteral||LA182_1==LPAREN||LA182_1==MINUS) ) {
                alt182=1;
            }
            else if ( (LA182_1==Identifier) ) {
                int LA182_4 = input.LA(3);

                if ( (LA182_4==LPAREN||LA182_4==COMMA||(LA182_4 >= PLUS && LA182_4 <= SLASH)||LA182_4==PERCENT) ) {
                    alt182=1;
                }
            }
        }
        switch (alt182) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:883:7: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred13_TextMarkerParser3982); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred13_TextMarkerParser3988);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                match(input,COMMA,FOLLOW_COMMA_in_synpred13_TextMarkerParser3990); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred13_TextMarkerParser3996);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:885:6: ( COMMA var= numberVariable )?
        int alt183=2;
        int LA183_0 = input.LA(1);

        if ( (LA183_0==COMMA) ) {
            alt183=1;
        }
        switch (alt183) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:885:7: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred13_TextMarkerParser4014); if (state.failed) return ;

                pushFollow(FOLLOW_numberVariable_in_synpred13_TextMarkerParser4020);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        match(input,RPAREN,FOLLOW_RPAREN_in_synpred13_TextMarkerParser4036); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {
        Expression list2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:924:27: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:924:28: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred14_TextMarkerParser4401);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {
        TextMarkerAction a =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1128:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1128:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred15_TextMarkerParser6206);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred16_TextMarkerParser
    public final void synpred16_TextMarkerParser_fragment() throws RecognitionException {
        Expression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred16_TextMarkerParser6374); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred16_TextMarkerParser6380);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {
        Expression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred17_TextMarkerParser6700); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred17_TextMarkerParser6706);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_TextMarkerParser

    // $ANTLR start synpred21_TextMarkerParser
    public final void synpred21_TextMarkerParser_fragment() throws RecognitionException {
        Expression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:29: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:30: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred21_TextMarkerParser7534);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {
        Expression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:92: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1309:93: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred22_TextMarkerParser7554);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred24_TextMarkerParser
    public final void synpred24_TextMarkerParser_fragment() throws RecognitionException {
        Expression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1625:3: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1625:3: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred24_TextMarkerParser9747);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred24_TextMarkerParser

    // $ANTLR start synpred25_TextMarkerParser
    public final void synpred25_TextMarkerParser_fragment() throws RecognitionException {
        Expression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1626:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1626:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred25_TextMarkerParser9758);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred25_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {
        Expression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1627:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1627:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred26_TextMarkerParser9769);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1785:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1785:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred27_TextMarkerParser10502);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1819:5: ( listExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1819:6: listExpression
        {
        pushFollow(FOLLOW_listExpression_in_synpred28_TextMarkerParser10690);
        listExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1833:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1833:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred29_TextMarkerParser10783);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {
        Expression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1881:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1881:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred30_TextMarkerParser10990);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        Expression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1882:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1882:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred31_TextMarkerParser11010);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        Expression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1883:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1883:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred32_TextMarkerParser11029);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred33_TextMarkerParser
    public final void synpred33_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1893:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1893:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred33_TextMarkerParser11104);
        e=externalBooleanFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred33_TextMarkerParser

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
    public final boolean synpred25_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_TextMarkerParser_fragment(); // can never throw exception
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
        "\1\11\1\114\3\172\1\173\1\uffff\1\172\1\uffff";
    static final String DFA28_maxS =
        "\1\11\1\172\1\u0086\2\172\1\u0086\1\uffff\1\u0083\1\uffff";
    static final String DFA28_acceptS =
        "\6\uffff\1\1\1\uffff\1\2";
    static final String DFA28_specialS =
        "\11\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\1",
            "\1\3\55\uffff\1\2",
            "\1\5\10\uffff\1\4\1\uffff\2\6",
            "\1\5",
            "\1\7",
            "\1\10\11\uffff\2\6",
            "",
            "\1\5\10\uffff\1\4",
            ""
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
            return "444:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI |declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
        }
    }
 

    public static final BitSet FOLLOW_packageDeclaration_in_file_input73 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003FBFF1000L});
    public static final BitSet FOLLOW_globalStatements_in_file_input87 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_statements_in_file_input94 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file_input100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PackageString_in_packageDeclaration121 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedId_in_packageDeclaration132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements163 = new BitSet(new long[]{0x0000000000000E02L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_globalStatement_in_globalStatements189 = new BitSet(new long[]{0x0000000000000002L,0x0000000002030000L});
    public static final BitSet FOLLOW_importStatement_in_globalStatement213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_statement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockDeclaration_in_statement261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStatement_in_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement303 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_importStatement323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement333 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_importStatement353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement363 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_importStatement383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntString_in_variableDeclaration410 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration416 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration423 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration438 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration463 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration469 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration477 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration494 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration519 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration533 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration550 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration575 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration581 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration589 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration606 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration631 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration637 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration645 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration651 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008060L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration701 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration710 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration754 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration760 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration763 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration808 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration817 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration862 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration871 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration917 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration926 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration932 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration979 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration985 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration988 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration1041 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1047 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008040L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1050 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration1056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONDITION_in_conditionDeclaration1128 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_conditionDeclaration1134 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1142 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionDeclaration1149 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionDeclaration1155 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionDeclaration1157 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_conditionDeclaration1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_actionDeclaration1195 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionDeclaration1201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1209 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDeclaration1215 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_actionDeclaration1221 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDeclaration1223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_actionDeclaration1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1260 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1266 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1276 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_COMMA_in_declaration1288 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_SEMI_in_declaration1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1330 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1334 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1341 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1348 = new BitSet(new long[]{0x0000000000000000L,0x0400000001E01000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1363 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_StringString_in_declaration1376 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1389 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_IntString_in_declaration1402 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1414 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1434 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_declaration1446 = new BitSet(new long[]{0x0000000000000000L,0x0400000001E01000L});
    public static final BitSet FOLLOW_annotationType_in_declaration1461 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_StringString_in_declaration1474 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1487 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_IntString_in_declaration1499 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1511 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_declaration1530 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_declaration1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1602 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_AutomataBlockString_in_blockDeclaration1610 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1614 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1621 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1629 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1636 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1644 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L,0x0000000000000001L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1686 = new BitSet(new long[]{0x0000000000000000L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1692 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1705 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1711 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1715 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithoutCA1769 = new BitSet(new long[]{0x0000000000000002L,0x2000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithoutCA1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1817 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1847 = new BitSet(new long[]{0x0000000000000002L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1856 = new BitSet(new long[]{0x0000000000000002L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElementType_in_blockRuleElement1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed1953 = new BitSet(new long[]{0x0000000000000000L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed1971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementComposed1977 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed1983 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementComposed1989 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed1995 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed2012 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed2018 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed2024 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed2028 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed2034 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2038 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2082 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2088 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2101 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2111 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementType2117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2178 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2184 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2197 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2203 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2207 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2213 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2275 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditions2280 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditions2286 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_action_in_actions2323 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actions2328 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_action_in_actions2334 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2481 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2493 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2571 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2578 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2583 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2705 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2712 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2717 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression2795 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression2807 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression2822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression2839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression2864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression2885 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression2897 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression2912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression2929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression2955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression2966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction3000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction3025 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3029 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3036 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3141 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3159 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3177 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3192 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3198 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3201 = new BitSet(new long[]{0x0000000000000000L,0x4C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3208 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3214 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3588 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3591 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3601 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3636 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3638 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3644 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3704 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3706 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3713 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3723 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionContains3729 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3738 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3746 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3752 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3755 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3761 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount3814 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount3816 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount3822 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3836 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3844 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3850 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3865 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount3871 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount3886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3937 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3939 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount3945 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3960 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionCount3966 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3982 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3990 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3996 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4014 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4020 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4052 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4054 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount4060 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4074 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4082 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4088 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4103 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4109 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4166 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4168 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4174 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4188 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4196 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4202 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4218 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4224 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4278 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4280 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4286 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4300 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4308 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4314 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4329 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4335 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4391 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4393 = new BitSet(new long[]{0x0000000000000000L,0x8420000000000000L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4408 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4416 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4425 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4431 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4434 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4440 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4501 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4503 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4509 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4512 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4518 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4520 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4526 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4585 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4587 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4593 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4642 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4644 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4652 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4658 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4660 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4666 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4713 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4715 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4723 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4731 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4737 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4745 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4751 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4754 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4760 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4813 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4830 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4832 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4838 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4880 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4882 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4888 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4929 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4931 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4938 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4944 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4994 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4996 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq5003 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq5009 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq5026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition5063 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition5065 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition5071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition5073 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition5079 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition5092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp5120 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp5122 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp5128 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp5131 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp5137 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp5155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore5189 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore5191 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5197 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5200 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5206 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5215 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore5221 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore5238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote5270 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote5272 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5278 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote5280 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5286 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote5299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf5333 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf5335 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf5341 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf5354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5393 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5395 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5401 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5403 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5409 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5453 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5455 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_genericVariableReference_in_conditionParse5464 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5507 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5509 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5516 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5522 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5565 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5567 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5574 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5580 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5623 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5625 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5632 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5638 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5685 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5687 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5694 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5700 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5747 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5749 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5756 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5762 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5809 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5811 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5817 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5820 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5828 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5834 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5839 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5845 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action5960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action5978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action6005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action6014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action6023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action6032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action6041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action6050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action6059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action6068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action6077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action6086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action6095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action6105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action6114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action6123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action6132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action6141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action6150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action6159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action6169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action6178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action6187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action6196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action6214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action6223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction6254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction6278 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction6282 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction6291 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction6296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate6332 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate6334 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate6340 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6347 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6385 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6391 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6397 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6417 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate6423 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6433 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6441 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate6447 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6513 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6515 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6526 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6528 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6541 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6551 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6559 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6570 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6572 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6578 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6588 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6596 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6602 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6660 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6662 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6668 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6682 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6710 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6723 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6736 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6738 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6745 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6753 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6764 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6770 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6772 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6779 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6787 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6854 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6856 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6862 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6880 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6886 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6888 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionFill6898 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6957 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6959 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6979 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6990 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor7004 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor7014 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor7028 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor7038 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor7054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel7086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog7132 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog7134 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog7140 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionLog7143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog7149 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog7165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark7203 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark7205 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark7216 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMark7234 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark7250 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark7272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand7309 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand7311 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand7322 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand7340 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand7356 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand7378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore7415 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore7417 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7425 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore7431 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7449 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7465 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore7487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7524 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7526 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00001000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7545 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7563 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7581 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7597 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7651 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7653 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7659 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7672 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7678 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7692 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7698 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7701 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7707 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7757 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7759 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7765 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7811 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7813 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7819 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7869 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7872 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7878 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7894 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7900 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7970 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7973 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7979 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7995 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType8001 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType8018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup8067 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup8070 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup8076 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup8092 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup8098 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup8115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType8160 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType8163 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8169 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType8185 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8191 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType8208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall8257 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall8263 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionCall8285 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall8299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec8333 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec8339 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionExec8357 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExec8373 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec8379 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec8395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign8437 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign8439 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8468 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionAssign8474 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8519 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8521 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8541 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8547 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8589 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8591 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8597 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8610 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8616 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8659 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8661 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8667 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8709 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8711 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8717 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8731 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8737 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8784 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8786 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8792 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8843 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8845 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8862 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8890 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8900 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8929 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8935 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8951 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8957 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8964 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8970 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8977 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8990 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9003 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie9009 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie9032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd9070 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd9072 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd9078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd9092 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionAdd9098 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd9115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove9149 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove9151 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove9157 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove9171 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionRemove9177 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove9194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9224 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate9226 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate9232 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate9245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge9282 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge9284 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge9290 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9304 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge9310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9324 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9330 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9340 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9346 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge9363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet9392 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet9394 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_actionGet9400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9413 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGet9419 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9432 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet9438 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet9451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList9481 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList9483 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList9489 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList9502 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList9508 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList9521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText9558 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText9560 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText9571 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText9583 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText9589 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText9611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear9644 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear9646 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionClear9652 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear9665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList9687 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9693 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList9698 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9704 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList9710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument9747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument9758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9817 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9830 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9840 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9872 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedId9885 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9895 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference9930 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_set_in_dottedComponentReference9943 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference9959 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration9993 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_set_in_dottedComponentDeclaration10006 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration10022 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_annotationTypeVariableReference_in_annotationType10056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType10067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationTypeVariableReference10096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression10120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression10133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression10157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression10170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression10194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10220 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_set_in_additiveExpression10229 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10239 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10268 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression10277 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10295 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression10311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar10335 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_numberExpressionInPar10341 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar10347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10372 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression10379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10394 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression10402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10416 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression10439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction10464 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction10486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction10510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction10535 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction10539 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction10546 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction10549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression10631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10644 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression10650 = new BitSet(new long[]{0x0000000000000000L,0x8C10030000001000L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10657 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression10669 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression10681 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression10698 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression10710 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction10747 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction10749 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_stringFunction10755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction10758 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction10764 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction10769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction10791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction10816 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction10820 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction10827 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction10830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression10855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression10870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression10952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression10998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression11018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression11037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression11047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction11072 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction11074 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction11082 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11088 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction11090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction11112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction11138 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction11141 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction11148 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction11152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare11177 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanCompare11183 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare11195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression11222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression11232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11259 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression11266 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression11302 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000006C6000L});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression11316 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11345 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression11348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_genericVariableReference11368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_synpred1_TextMarkerParser1961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VBAR_in_synpred1_TextMarkerParser1963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_synpred2_TextMarkerParser2005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred5_TextMarkerParser2364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred6_TextMarkerParser2380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred7_TextMarkerParser2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred8_TextMarkerParser2412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred9_TextMarkerParser2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred10_TextMarkerParser2641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred12_TextMarkerParser3511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred13_TextMarkerParser3937 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred13_TextMarkerParser3939 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_listExpression_in_synpred13_TextMarkerParser3945 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred13_TextMarkerParser3960 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_synpred13_TextMarkerParser3966 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred13_TextMarkerParser3982 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred13_TextMarkerParser3988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred13_TextMarkerParser3990 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred13_TextMarkerParser3996 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred13_TextMarkerParser4014 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_numberVariable_in_synpred13_TextMarkerParser4020 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred13_TextMarkerParser4036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred14_TextMarkerParser4401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred15_TextMarkerParser6206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser6374 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred16_TextMarkerParser6380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred17_TextMarkerParser6700 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred17_TextMarkerParser6706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred21_TextMarkerParser7534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred22_TextMarkerParser7554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred24_TextMarkerParser9747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred25_TextMarkerParser9758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred26_TextMarkerParser9769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred27_TextMarkerParser10502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_listExpression_in_synpred28_TextMarkerParser10690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred29_TextMarkerParser10783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred30_TextMarkerParser10990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred31_TextMarkerParser11010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred32_TextMarkerParser11029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred33_TextMarkerParser11104 = new BitSet(new long[]{0x0000000000000002L});

}